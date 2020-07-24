package de.appsfactory.userportal.user;

import lombok.Getter;
import lombok.NonNull;
import lombok.Value;

import javax.validation.constraints.NotBlank;

@Value
public class UserPassword {

    @NonNull
    @Getter
    @NotBlank
    private String password;

    public static UserPassword from(String password) {
        return new UserPassword(password);
    }
}
