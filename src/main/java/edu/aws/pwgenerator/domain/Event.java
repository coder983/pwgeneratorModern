package edu.aws.pwgenerator.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="events")
@Component
@Getter
@Setter
public class Event {

    @Id
    private Integer idevents;
    private String event;
    private String eventyear;

}
