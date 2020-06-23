package edu.aws.pwgenerator.service.datasource;

import edu.aws.pwgenerator.domain.Name;
import edu.aws.pwgenerator.domain.NamesRepository;
import edu.aws.pwgenerator.domain.Place;
import edu.aws.pwgenerator.domain.PlacesRepository;
import edu.aws.pwgenerator.service.Status;
import edu.aws.pwgenerator.service.builder.PasswordData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static edu.aws.pwgenerator.PWGeneratorTestUtils.*;
import static org.mockito.Mockito.when;
import static org.junit.Assert.*;


class PlaceDataSourceTest {

    private Name mockName;
    private Place mockPlace;
    private Status mockStatus;
    private PasswordData expectedPWData;
    private PasswordData actualPWData;

    @Mock
    NamesRepository namesRepository;
    @Mock
    PlacesRepository placesRepository;

    PlaceDataSource source;

    @BeforeEach
    void setUp(){

        MockitoAnnotations.initMocks(this);
        mockName = buildMockName();
        mockPlace = buildMockPlace();
        mockStatus = buildMockStatus();
        expectedPWData =buildExpectedPlacePWData();
    }

    @Test
    void fetchPlacesDataTest(){
        source = new PlaceDataSource();
        when(namesRepository.findById(1)).thenReturn(java.util.Optional.ofNullable(mockName));
        when(placesRepository.findById(1)).thenReturn(java.util.Optional.ofNullable(mockPlace));
        actualPWData = source.fetchPlaceData(mockStatus, namesRepository, placesRepository);
        assertTrue(expectedPWData.equals(actualPWData));
    }
}