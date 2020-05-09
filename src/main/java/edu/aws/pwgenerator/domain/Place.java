package edu.aws.pwgenerator.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "places")
@Component
@Getter
@Setter
public class Place {

    @Id
    private Integer idplaces;
    private String place;

}
