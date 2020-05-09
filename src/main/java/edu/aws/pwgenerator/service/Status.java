package edu.aws.pwgenerator.service;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class Status {

    private int nameTracker;
    private int eventTracker;
    private int placeTracker;

}

