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
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;


class NameDataSourceTest {

    private Name mockName;
    private Status mockStatus;
    private PasswordData expectedPWData;
    private PasswordData actualPWData;

    @Mock
    NamesRepository namesRepository;

    NameDataSource source;

    @BeforeEach
    void setUp(){

        MockitoAnnotations.initMocks(this);
        mockName = buildMockName();
        mockStatus = buildMockStatus();
        expectedPWData = buildExpectedPWData();
    }

    @Test
    void fetchNameDataTest(){
        source = new NameDataSource();
        when(namesRepository.findById(1)).thenReturn(java.util.Optional.ofNullable(mockName));
        actualPWData = source.fetchNameData(mockStatus, namesRepository);
        assertTrue(expectedPWData.equals(actualPWData));
    }



    private PasswordData buildExpectedPWData() {
        PasswordData pwData = new PasswordData();
        pwData.setFirstname("Pete");
        pwData.setLastname("Soloninka");
        pwData.setYear("1993");
        return pwData;
    }

}