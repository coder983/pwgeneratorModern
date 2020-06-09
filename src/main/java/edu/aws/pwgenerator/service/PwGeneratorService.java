package edu.aws.pwgenerator.service;

import edu.aws.pwgenerator.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PwGeneratorService {
    @Autowired
    private Status status;
    @Autowired
    private PasswordData passwordData;
    @Autowired
    private EventsRepository eventsRepository;
    @Autowired
    private NamesRepository namesRepository;
    @Autowired
    private PlacesRepository placesRepository;

    private static final String SPECIAL[] = {"!", "@", "#", "$", "%", "&", "*", "_"};

    private void initStatus() {
        //read status.json
        //set fields in status
    }

    private void loadPasswordData() {
        //load password data into status
        int type = status.getPwTypeTracker();
        switch (type) {
            case 0: case 1:
                fetchNamePlaceData();
                break;
            case 2: case 3: case 4: case 5:
                fetchNameData();
            case 6: case 7:
                fetchEventData();
            default:
                break;
        }
        //write status to json
    }

    public String getAPassword() {
        initStatus();
        loadPasswordData();

        PasswordBuilder pwBuilder = new PasswordBuilder(passwordData);

        return pwBuilder.builder();
    }

    private void fetchNameData() {
        Name name = namesRepository.findById(status.getNameTracker()).orElseThrow(() -> new IllegalArgumentException("Invalid Name ID"));
        passwordData.setFirstname(name.getFirstname());
        passwordData.setLastname(name.getLastname());
        passwordData.setYear(name.getYear());
        status.setNameTracker(status.getNameTracker() + 1);
    }

    private void fetchNamePlaceData() {
        Name name = namesRepository.findById(status.getNameTracker()).orElseThrow(() -> new IllegalArgumentException("Invalid Name ID"));
        passwordData.setFirstname(name.getFirstname());
        passwordData.setLastname(name.getLastname());
        Place place = placesRepository.findById(status.getPlaceTracker()).orElseThrow(() -> new IllegalArgumentException("Invalid Place ID"));
        status.setNameTracker(status.getNameTracker() + 1);
        status.setPlaceTracker(status.getPlaceTracker() + 1);
    }

    private void fetchEventData() {
        Event event = eventsRepository.findById(status.getEventTracker()).orElseThrow(() -> new IllegalArgumentException("Invalid Event ID"));
        passwordData.setEvent(event.getEvent());
        passwordData.setYear(event.getEventyear());
        status.setEventTracker(status.getEventTracker() + 1);
    }

}
