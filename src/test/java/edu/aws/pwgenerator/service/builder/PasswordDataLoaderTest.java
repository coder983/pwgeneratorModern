package edu.aws.pwgenerator.service.builder;

import edu.aws.pwgenerator.PWGeneratorTestUtils;
import edu.aws.pwgenerator.domain.EventsRepository;
import edu.aws.pwgenerator.domain.NamesRepository;
import edu.aws.pwgenerator.domain.PlacesRepository;
import edu.aws.pwgenerator.service.Status;
import edu.aws.pwgenerator.service.datasource.EventDataSource;
import edu.aws.pwgenerator.service.datasource.NameDataSource;
import edu.aws.pwgenerator.service.datasource.PlaceDataSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

import static edu.aws.pwgenerator.PWGeneratorTestUtils.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class PasswordDataLoaderTest {

    Status mockStatus;
    PasswordDataLoader loader;

    @Autowired
    NamesRepository namesRepository;
    @Autowired
    PlacesRepository placesRepository;
    @Autowired
    EventsRepository eventsRepository;

    @Mock
    private PlaceDataSource placeDataSource;
    @Mock
    private NameDataSource nameDataSource;
    @Mock
    private EventDataSource eventDataSource;

    @BeforeEach
    void setUp() {
        mockStatus = buildMockStatus();
        loader = new PasswordDataLoader();
    }

    @Test
    void loadPasswordDataforPlaceTest() {

        PasswordData expectedPasswordData = buildExpectedPlacePWData();
        when(placeDataSource.fetchPlaceData(mockStatus, namesRepository, placesRepository)).thenReturn(buildExpectedPlacePWData());
        PasswordData actualPasswordData = loader.loadPasswordData(mockStatus, placeDataSource, nameDataSource, eventDataSource);

        assertTrue(actualPasswordData.equals(expectedPasswordData));

    }
}