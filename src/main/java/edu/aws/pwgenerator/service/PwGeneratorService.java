package edu.aws.pwgenerator.service;

import edu.aws.pwgenerator.domain.NamesRepository;
import edu.aws.pwgenerator.service.builder.PasswordBuilder;
import edu.aws.pwgenerator.service.builder.PasswordData;
import edu.aws.pwgenerator.service.builder.PasswordDataLoader;
import edu.aws.pwgenerator.service.datasource.EventDataSource;
import edu.aws.pwgenerator.service.datasource.NameDataSource;
import edu.aws.pwgenerator.service.datasource.PlaceDataSource;
import edu.aws.pwgenerator.service.manager.StatusManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PwGeneratorService {

    private Status status;
    private PasswordData passwordData;

    @Autowired
    NamesRepository namesRepository;

    private PlaceDataSource placeDataSource = new PlaceDataSource();
    private NameDataSource nameDataSource = new NameDataSource();
    private EventDataSource eventDataSource = new EventDataSource();

    public String getAPassword(StatusManager initializer, PasswordDataLoader loader) {

        status = initializer.init();

        passwordData = loader.loadPasswordData(status, placeDataSource, nameDataSource, eventDataSource );

        PasswordBuilder pwBuilder = new PasswordBuilder(passwordData);

        return pwBuilder.builder();
    }




}
