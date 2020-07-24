package de.appsfactory.userportal.user;

import de.appsfactory.userportal.common.validator.SelfValidating;
import lombok.*;

import java.util.Optional;

@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class User extends SelfValidating<User> {

    private final UserId id;

    @Getter
    @NonNull
    private final UserFullName userFullName;

    @Getter
    @NonNull
    private final UserDob userDob;

    @Getter
    @NonNull
    private final UserAddress userAddress;

    @Getter
    @NonNull
    private final UserEmail userEmail;

    private final UserPassword userPassword;

    @Getter
    private final UserHomePage userHomePage;

    public static User withId(UserId id,
                              UserFullName userFullName,
                              UserDob userDob,
                              UserAddress userAddress,
                              UserEmail userEmail,
                              UserPassword userPassword,
                              UserHomePage userHomePage) {
        return new User(id, userFullName, userDob, userAddress, userEmail, userPassword, userHomePage);
    }

    public static User withoutId(UserFullName userFullName,
                                 UserDob userDob,
                                 UserAddress userAddress,
                                 UserEmail userEmail,
                                 UserPassword userPassword,
                                 UserHomePage userHomePage) {
        return new User(null, userFullName, userDob, userAddress, userEmail, userPassword, userHomePage);
    }

    public Optional<UserId> getId() {
        return Optional.ofNullable(this.id);
    }

    public Optional<UserPassword> getUserPassword() {
        return Optional.ofNullable(this.userPassword);
    }

    @Value
    public static class UserId {
        private Long value;
    }
}
