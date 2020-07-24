package de.appsfactory.userportal.user.port.out;


import de.appsfactory.userportal.user.User;

public interface LoadUserPort {

    User loadUser(User.UserId userId);

}
