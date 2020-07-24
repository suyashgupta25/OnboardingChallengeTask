package de.appsfactory.userportal.adapter.web;

import de.appsfactory.userportal.adapter.web.request.CreateUserRequest;
import de.appsfactory.userportal.adapter.web.request.UpdateUserRequest;
import de.appsfactory.userportal.adapter.web.response.GetUserResponse;
import de.appsfactory.userportal.error.exception.EntityNotFoundException;
import de.appsfactory.userportal.user.*;
import de.appsfactory.userportal.user.port.in.CreateOrUpdateUserUseCase;
import de.appsfactory.userportal.user.port.in.DeleteUserUseCase;
import de.appsfactory.userportal.user.port.in.GetUserQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "*")
class UserController {

    private final GetUserQuery getUserQuery;
    private final CreateOrUpdateUserUseCase createOrUpdateUserUseCase;
    private final DeleteUserUseCase deleteUserUseCase;
    private final PasswordEncoder passwordEncoder;
    private final UserControllerMapper userControllerMapper;

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public ResponseEntity<GetUserResponse> getUser(@PathVariable @Valid Long id) throws EntityNotFoundException {
        log.debug("getting a User id:"+id);
        User user = getUserQuery.getUser(new User.UserId(id));
        GetUserResponse getUserResponse = userControllerMapper.mapToResponseUser(user);
        return ResponseEntity.ok(getUserResponse);
    }

    @PostMapping("/user")
    public ResponseEntity<GetUserResponse> createUser(@RequestBody @Valid final CreateUserRequest request) {
        log.debug("creating User={}", request);

        User user = User.withoutId(UserFullName.from(request.getFirstName(), request.getLastName()),
                UserDob.from(request.getDob()),
                UserAddress.from(request.getAddress1(), request.getAddress2(), request.getAddress3(),
                        UserPostalCode.from(request.getPostcode()), request.getCity(), request.getCountryName(),
                        UserCountryCode.from(request.getCountryISOCode())),
                UserEmail.from(request.getEmail()),
                UserPassword.from(passwordEncoder.encode(request.getPassword())),
                UserHomePage.from(request.getHomepage()));
        User createdUser = createOrUpdateUserUseCase.create(user);
        GetUserResponse getUserResponse = userControllerMapper.mapToResponseUser(createdUser);
        return new ResponseEntity<>(getUserResponse, HttpStatus.CREATED);
    }

    @PutMapping(value = "/user")
    public ResponseEntity<GetUserResponse> updateUser(@RequestBody @Valid final UpdateUserRequest request) throws EntityNotFoundException {
        log.debug("updating User={}", request);
        User user = User.withId(new User.UserId(request.getId()),
                UserFullName.from(request.getFirstName(), request.getLastName()),
                UserDob.from(request.getDob()),
                UserAddress.from(request.getAddress1(), request.getAddress2(), request.getAddress3(),
                        UserPostalCode.from(request.getPostcode()), request.getCity(), request.getCountryName(),
                        UserCountryCode.from(request.getCountryISOCode())),
                UserEmail.from(request.getEmail()),
                null,
                UserHomePage.from(request.getHomepage()));
        User updatedUser = createOrUpdateUserUseCase.update(user);
        GetUserResponse getUserResponse = userControllerMapper.mapToResponseUser(updatedUser);
        return ResponseEntity.ok(getUserResponse);
    }

    @DeleteMapping(value = "/user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable @Valid Long id) throws EntityNotFoundException {
        log.debug("deleting User");
        deleteUserUseCase.deleteUser(new User.UserId(id));
    }

}
