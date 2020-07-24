package de.appsfactory.userportal.adapter.persistence;

import de.appsfactory.userportal.user.*;
import org.springframework.stereotype.Component;

@Component
class UserMapper {

    User mapToDomainEntity(UserJpaEntity entity) {
        return User.withId(new User.UserId(entity.getId()),
                UserFullName.from(entity.getFirstName(), entity.getLastName()),
                UserDob.from(entity.getDob()),
                UserAddress.from(entity.getAddress1(), entity.getAddress2(), entity.getAddress3(),
                        UserPostalCode.from(entity.getPostcode()), entity.getCity(), entity.getCountryName(),
                        UserCountryCode.from(entity.getCountryISOCode())),
                UserEmail.from(entity.getEmail()),
                UserPassword.from(entity.getPassword()),
                UserHomePage.from(entity.getHomepage()));
    }

    UserJpaEntity mapToJpaEntity(User user) {
        Long id = user.getId().map(User.UserId::getValue).orElse(null);
        String password = user.getUserPassword().map(UserPassword::getPassword).orElse(null);
        return new UserJpaEntity(id,
                user.getUserFullName().getFirstName(), user.getUserFullName().getLastName(),
                user.getUserDob().getDateOfBirth(), user.getUserAddress().getAddress1(),
                user.getUserAddress().getAddress2(), user.getUserAddress().getAddress3(),
                user.getUserAddress().getUserPostalCode().getPostcode(), user.getUserAddress().getCity(),
                user.getUserAddress().getCountryName(), user.getUserAddress().getCountryCode().getCountryCodeISO(), user.getUserEmail().getEmail(),
                password,
                user.getUserHomePage().getHomepage());
    }
}
