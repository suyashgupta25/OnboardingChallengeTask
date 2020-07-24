package de.appsfactory.userportal.user.service;

import de.appsfactory.userportal.error.exception.EntityNotFoundException;
import de.appsfactory.userportal.user.User;
import de.appsfactory.userportal.user.port.in.CreateOrUpdateUserUseCase;
import de.appsfactory.userportal.user.port.out.CreateOrUpdateUserPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

@RequiredArgsConstructor
@Service
@Slf4j
class CreateOrUpdateUserService implements CreateOrUpdateUserUseCase {

    private final CreateOrUpdateUserPort createOrUpdateUserPort;

    @Override
    public User create(User user) {
        return createOrUpdateUserPort.create(user);
    }

    @Override
    public User update(User user) {
        if (user.getId().isPresent() && !createOrUpdateUserPort.userExists(user.getId().get())) {
            User.UserId userId = user.getId().get();
            log.error("id not found:"+userId.getValue());
            throw new EntityNotFoundException(User.class, "id", Objects.toString(userId.getValue()));
        }
        return createOrUpdateUserPort.update(user);
    }
}
