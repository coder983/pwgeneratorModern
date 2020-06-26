package edu.aws.pwgenerator.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "names")
@Component
@Getter
@Setter
public class Name {

    @Id
    private Long idnames;
    private String year;
    private String firstname;
    private String lastname;

}
