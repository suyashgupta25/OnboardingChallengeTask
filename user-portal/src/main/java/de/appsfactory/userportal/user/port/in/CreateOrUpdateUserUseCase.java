package de.appsfactory.userportal.user.port.in;


import de.appsfactory.userportal.user.User;

public interface CreateOrUpdateUserUseCase {

    User create(User user);

    User update(User user);
}
