package de.appsfactory.userportal.user.port.in;

import de.appsfactory.userportal.user.User;

public interface DeleteUserUseCase {
    void deleteUser(User.UserId userId);
}
