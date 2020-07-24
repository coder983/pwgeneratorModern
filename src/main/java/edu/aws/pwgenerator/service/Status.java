package edu.aws.pwgenerator.service;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Getter
@Setter
@Component
public class Status {

    private long nameTracker;
    private long eventTracker;
    private long placeTracker;
    private long pwTypeTracker;
    private long specialCharacterTracker;
    private HashMap<String, String> padding;

}

