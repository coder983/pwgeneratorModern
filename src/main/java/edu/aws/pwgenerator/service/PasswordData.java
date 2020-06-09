package edu.aws.pwgenerator.service;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class PasswordData {

    private String year;
    private String firstname;
    private String lastname;
    private String place;
    private String event;
    private String seperator;
    private int type;

}
