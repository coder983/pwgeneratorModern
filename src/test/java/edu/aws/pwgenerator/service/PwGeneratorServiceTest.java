package edu.aws.pwgenerator.service;

import edu.aws.pwgenerator.service.builder.PasswordData;
import edu.aws.pwgenerator.service.builder.PasswordDataLoader;
import edu.aws.pwgenerator.service.datasource.EventDataSource;
import edu.aws.pwgenerator.service.datasource.NameDataSource;
import edu.aws.pwgenerator.service.datasource.PlaceDataSource;
import edu.aws.pwgenerator.service.manager.StatusManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PwGeneratorServiceTest {

    PwGeneratorService service;

    Status status;
    PasswordData passwordData = new PasswordData();
    @Mock
    PasswordDataLoader loader;
    @Mock
    StatusManager initializer;

    private PlaceDataSource placeDataSource = new PlaceDataSource();
    private NameDataSource nameDataSource = new NameDataSource();
    private EventDataSource eventDataSource = new EventDataSource();

    @BeforeEach
    void setUp() {
        service = new PwGeneratorService();
        status = new Status();
        passwordData.setFirstname("John");
        passwordData.setLastname("Smith");
        passwordData.setYear("2001");
        passwordData.setSeperator("$");
        passwordData.setType(3);

    }


    @Test
    void getAPasswordTest() {
     /*   String pwd = "";

        doReturn(passwordData).when(loader.loadPasswordData(status, placeDataSource, nameDataSource, eventDataSource));
        doReturn(status).when(initializer.init());

        pwd = service.getAPassword(initializer, loader);
        assertEquals(pwd, "2001$JohnSmith");

      */
    }
}