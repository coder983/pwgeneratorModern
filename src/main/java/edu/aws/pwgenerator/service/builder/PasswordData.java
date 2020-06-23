package edu.aws.pwgenerator.service.builder;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@EqualsAndHashCode
public class PasswordData {

    private String year;
    private String firstname;
    private String lastname;
    private String place;
    private String event;
    private String seperator;
    private int type;

}
