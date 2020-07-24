package de.appsfactory.userportal.user.port.out;


import de.appsfactory.userportal.user.User;

public interface CreateOrUpdateUserPort {

    User create(User user);

    User update(User user);

    Boolean userExists(User.UserId userId);
}
