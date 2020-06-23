package edu.aws.pwgenerator.service.builder;

import edu.aws.pwgenerator.domain.*;
import edu.aws.pwgenerator.service.Status;
import edu.aws.pwgenerator.service.builder.PasswordData;
import edu.aws.pwgenerator.service.datasource.EventDataSource;
import edu.aws.pwgenerator.service.datasource.NameDataSource;
import edu.aws.pwgenerator.service.datasource.PlaceDataSource;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;

@Component
public class PasswordDataLoader {

    private PasswordData passwordData  = new PasswordData();
    @Autowired
    private EventsRepository eventsRepository;
    @Autowired
    private NamesRepository namesRepository;
    @Autowired
    private PlacesRepository placesRepository;


    public PasswordData loadPasswordData(Status st,
                                         PlaceDataSource placeDataSource,
                                         NameDataSource nameDataSource,
                                         EventDataSource eventDataSource) {
        //load password data into status
        int type = st.getPwTypeTracker();

        switch (type) {
            case 0: case 1:
                placeDataSource.fetchPlaceData(st, namesRepository, placesRepository);
                break;
            case 2: case 3: case 4: case 5:
                nameDataSource.fetchNameData(st, namesRepository);
            case 6: case 7:
                eventDataSource.fetchEventData(st, eventsRepository);
            default:
                break;
        }
        //set type in password data object
        passwordData.setType(type);
        //Update Password Type and Special Character trackers
        st.setPwTypeTracker(st.getPwTypeTracker() + 1);
        st.setSpecialCharacterTracker(st.getSpecialCharacterTracker() + 1);
        //write status to json
        writeStatusToJson(st);

        return passwordData;
    }

    private void writeStatusToJson(Status st) {
        JSONObject statusjson = new JSONObject();
        statusjson.put("name", st.getNameTracker());
        statusjson.put("event", st.getEventTracker());
        statusjson.put("place", st.getPlaceTracker());
        statusjson.put("type", st.getPwTypeTracker());
        statusjson.put("spec", st.getSpecialCharacterTracker());
        try(FileWriter fw = new FileWriter("status.json")) {
            fw.write(statusjson.toJSONString());
            fw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void fetchNameData(Status st) {

    }

    private void fetchEventData(Status st) {

    }

}
