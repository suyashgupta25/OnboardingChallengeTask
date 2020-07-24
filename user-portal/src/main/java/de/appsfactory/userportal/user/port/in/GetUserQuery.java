package de.appsfactory.userportal.user.port.in;

import de.appsfactory.userportal.user.User;

public interface GetUserQuery {

    User getUser(User.UserId userId);

}
