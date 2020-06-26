package edu.aws.pwgenerator.service;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class Status {

    private long nameTracker;
    private long eventTracker;
    private long placeTracker;
    private long pwTypeTracker;
    private long specialCharacterTracker;

}

