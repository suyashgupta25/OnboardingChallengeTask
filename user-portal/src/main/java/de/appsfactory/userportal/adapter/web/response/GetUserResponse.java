package de.appsfactory.userportal.adapter.web.response;

import lombok.Data;
import lombok.NonNull;

import java.util.Date;

@Data
public class GetUserResponse {

    @NonNull
    private Long id;

    @NonNull
    private String firstName;

    @NonNull
    private String lastName;

    @NonNull
    private Date dob;

    @NonNull
    private String address1;

    @NonNull
    private String address2;

    @NonNull
    private String address3;

    @NonNull
    private String postcode;

    @NonNull
    private String city;

    @NonNull
    private String countryName;

    @NonNull
    private String countryISOCode;

    @NonNull
    private String email;

    @NonNull
    private String homepage;
}
