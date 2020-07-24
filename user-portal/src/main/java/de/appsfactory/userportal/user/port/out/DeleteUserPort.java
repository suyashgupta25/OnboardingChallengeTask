package de.appsfactory.userportal.user.port.out;


import de.appsfactory.userportal.user.User;

public interface DeleteUserPort {

    void deleteUser(User.UserId userId);

    Boolean userExists(User.UserId userId);

}
