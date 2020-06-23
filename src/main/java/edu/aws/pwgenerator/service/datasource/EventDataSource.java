package edu.aws.pwgenerator.service.datasource;

import edu.aws.pwgenerator.domain.Event;
import edu.aws.pwgenerator.domain.EventsRepository;
import edu.aws.pwgenerator.service.Status;
import edu.aws.pwgenerator.service.builder.PasswordData;

public class EventDataSource {

    public PasswordData fetchEventData(Status st, EventsRepository eventsRepository){
        PasswordData passwordData = new PasswordData();
        Event event = eventsRepository.findById(st.getEventTracker()).orElseThrow(() -> new IllegalArgumentException("Invalid Event ID"));
        passwordData.setEvent(event.getEvent());
        passwordData.setYear(event.getEventyear());
        st.setEventTracker(st.getEventTracker() + 1);

        return passwordData;
    }
}
