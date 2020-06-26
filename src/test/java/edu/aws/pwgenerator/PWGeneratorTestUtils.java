package edu.aws.pwgenerator;

import edu.aws.pwgenerator.domain.Event;
import edu.aws.pwgenerator.domain.Name;
import edu.aws.pwgenerator.domain.Place;
import edu.aws.pwgenerator.service.Status;
import edu.aws.pwgenerator.service.builder.PasswordData;

public class PWGeneratorTestUtils {

    public static Place buildMockPlace() {
        Place place = new Place();
        place.setIdplaces(1L);
        place.setPlace("Seattle");
        return place;
    }

    public static Status buildMockStatus() {
        Status status = new Status();
        status.setNameTracker(1L);
        status.setEventTracker(1L);
        status.setPlaceTracker(1L);
        status.setPwTypeTracker(1L);
        status.setSpecialCharacterTracker(1L);
        return status;
    }

    public static Name buildMockName() {
        Name name = new Name();
        name.setFirstname("Pete");
        name.setLastname("Soloninka");
        name.setIdnames(1L);
        name.setYear("1993");
        return name;
    }

    public static Event buildMockEvent(){
        Event event = new Event();
        event.setEvent("Shiloh");
        event.setEventyear("1862");
        event.setIdevents(1L);
        return event;

    }

    public static PasswordData buildExpectedPlacePWData() {
        PasswordData pwData = new PasswordData();
        pwData.setFirstname("Pete");
        pwData.setLastname("Soloninka");
        pwData.setYear("1993");
        pwData.setPlace("Seattle");
        return pwData;
    }
}
