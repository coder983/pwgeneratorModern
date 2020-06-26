package edu.aws.pwgenerator.service.builder;

import edu.aws.pwgenerator.domain.*;
import edu.aws.pwgenerator.service.Status;
import edu.aws.pwgenerator.service.builder.PasswordData;
import edu.aws.pwgenerator.service.datasource.EventDataSource;
import edu.aws.pwgenerator.service.datasource.NameDataSource;
import edu.aws.pwgenerator.service.datasource.PlaceDataSource;
import edu.aws.pwgenerator.service.manager.StatusManager;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;

@Component
public class PasswordDataLoader {

    private PasswordData passwordData  = new PasswordData();
    @Autowired
    StatusManager manager;
    @Autowired
    private EventsRepository eventsRepository;
    @Autowired
    private NamesRepository namesRepository;
    @Autowired
    private PlacesRepository placesRepository;

    private static final String SPECIAL[] = {"!", "@", "#", "$", "%", "&", "*", "_"};
    private static final long MAXTYPES = 8L;

    public PasswordData loadPasswordData(Status st,
                                         PlaceDataSource placeDataSource,
                                         NameDataSource nameDataSource,
                                         EventDataSource eventDataSource) {
        //load password data into status
        long type = st.getPwTypeTracker() - 1;

        switch ((int)type) {
            case 0: case 1:
                passwordData = placeDataSource.fetchPlaceData(st, namesRepository, placesRepository);
                break;
            case 2: case 3: case 4: case 5:
                passwordData = nameDataSource.fetchNameData(st, namesRepository);
                break;
            case 6: case 7:
                passwordData = eventDataSource.fetchEventData(st, eventsRepository);
                break;
            default:
                break;
        }
        //set type and special character in password data object
        passwordData.setType(type);
        passwordData.setSeperator(SPECIAL[(int) st.getSpecialCharacterTracker() - 1]);

        //update special character and Password type
        manager.increment(st, SPECIAL.length, MAXTYPES);

        //write status to json
        manager.update(st);

        return passwordData;
    }

}
