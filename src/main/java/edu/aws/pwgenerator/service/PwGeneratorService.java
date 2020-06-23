package edu.aws.pwgenerator.service;

import edu.aws.pwgenerator.service.builder.PasswordBuilder;
import edu.aws.pwgenerator.service.builder.PasswordData;
import edu.aws.pwgenerator.service.builder.PasswordDataLoader;
import edu.aws.pwgenerator.service.datasource.EventDataSource;
import edu.aws.pwgenerator.service.datasource.NameDataSource;
import edu.aws.pwgenerator.service.datasource.PlaceDataSource;
import edu.aws.pwgenerator.service.manager.StatusManager;
import org.springframework.stereotype.Component;

@Component
public class PwGeneratorService {

    private Status status;
    private PasswordData passwordData;

    private PlaceDataSource placeDataSource = new PlaceDataSource();
    private NameDataSource nameDataSource = new NameDataSource();
    private EventDataSource eventDataSource = new EventDataSource();

    private static final String SPECIAL[] = {"!", "@", "#", "$", "%", "&", "*", "_"};

    public String getAPassword(StatusManager initializer, PasswordDataLoader loader) {

        status = initializer.init();
        passwordData = loader.loadPasswordData(status, placeDataSource, nameDataSource, eventDataSource );

        PasswordBuilder pwBuilder = new PasswordBuilder(passwordData);

        return pwBuilder.builder();
    }


}
