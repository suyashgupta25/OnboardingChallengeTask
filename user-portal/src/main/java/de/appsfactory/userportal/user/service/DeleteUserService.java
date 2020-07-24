package de.appsfactory.userportal.user.service;

import de.appsfactory.userportal.error.exception.EntityNotFoundException;
import de.appsfactory.userportal.user.User;
import de.appsfactory.userportal.user.port.in.DeleteUserUseCase;
import de.appsfactory.userportal.user.port.out.DeleteUserPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

@RequiredArgsConstructor
@Service
@Slf4j
class DeleteUserService implements DeleteUserUseCase {

    private final DeleteUserPort deleteUserPort;

    @Override
    public void deleteUser(User.UserId userId) {
        if (!deleteUserPort.userExists(userId)) {
            log.error("id not found:"+userId.getValue());
            throw new EntityNotFoundException(User.class, "id", Objects.toString(userId.getValue()));
        }
        deleteUserPort.deleteUser(userId);
    }
}
