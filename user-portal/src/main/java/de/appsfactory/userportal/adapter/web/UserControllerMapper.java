package de.appsfactory.userportal.adapter.web;

import de.appsfactory.userportal.adapter.web.response.GetUserResponse;
import de.appsfactory.userportal.user.User;
import org.springframework.stereotype.Component;

@Component
public class UserControllerMapper {

    GetUserResponse mapToResponseUser(User user) {
        Long id = user.getId().map(User.UserId::getValue).orElse(null);
        return new GetUserResponse(id, user.getUserFullName().getFirstName(), user.getUserFullName().getLastName(),
                user.getUserDob().getDateOfBirth(), user.getUserAddress().getAddress1(), user.getUserAddress().getAddress2(),
                user.getUserAddress().getAddress3(), user.getUserAddress().getUserPostalCode().getPostcode(),
                user.getUserAddress().getCity(), user.getUserAddress().getCountryName(), user.getUserAddress().getCountryCode().getCountryCodeISO(),
                user.getUserEmail().getEmail(), user.getUserHomePage().getHomepage());
    }

}
