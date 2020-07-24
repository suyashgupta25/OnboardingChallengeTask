package de.appsfactory.userportal.user;

import de.appsfactory.userportal.error.exception.InvalidParameterException;
import de.appsfactory.userportal.util.IsoUtil;
import lombok.Getter;
import lombok.NonNull;
import lombok.Value;

@Value
public class UserCountryCode {

    @NonNull
    @Getter
    private final String countryCodeISO;

    public static UserCountryCode from(String value) {
        if(IsoUtil.isValidISOCountry(value)) {
            return new UserCountryCode(value);
        } else {
            throw new InvalidParameterException(User.class, "countryCodeISO", value);
        }
    }
}
