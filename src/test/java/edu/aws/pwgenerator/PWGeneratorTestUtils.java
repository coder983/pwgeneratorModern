package edu.aws.pwgenerator;

import edu.aws.pwgenerator.domain.Event;
import edu.aws.pwgenerator.domain.Name;
import edu.aws.pwgenerator.domain.Place;
import edu.aws.pwgenerator.service.Status;
import edu.aws.pwgenerator.service.builder.PasswordData;

public class PWGeneratorTestUtils {

    public static Place buildMockPlace() {
        Place place = new Place();
        place.setIdplaces(1);
        place.setPlace("Seattle");
        return place;
    }

    public static Status buildMockStatus() {
        Status status = new Status();
        status.setNameTracker(1);
        status.setEventTracker(1);
        status.setPlaceTracker(1);
        status.setPwTypeTracker(1);
        status.setSpecialCharacterTracker(1);
        return status;
    }

    public static Name buildMockName() {
        Name name = new Name();
        name.setFirstname("Pete");
        name.setLastname("Soloninka");
        name.setIdnames(1);
        name.setYear("1993");
        return name;
    }

    public static Event buildMockEvent(){
        Event event = new Event();
        event.setEvent("Shiloh");
        event.setEventyear("1862");
        event.setIdevents(1);
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
