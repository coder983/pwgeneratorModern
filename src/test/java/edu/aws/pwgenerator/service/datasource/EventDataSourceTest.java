package edu.aws.pwgenerator.service.datasource;

import edu.aws.pwgenerator.domain.Event;
import edu.aws.pwgenerator.domain.EventsRepository;
import edu.aws.pwgenerator.domain.Name;
import edu.aws.pwgenerator.domain.NamesRepository;
import edu.aws.pwgenerator.service.Status;
import edu.aws.pwgenerator.service.builder.PasswordData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static edu.aws.pwgenerator.PWGeneratorTestUtils.*;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;


class EventDataSourceTest {

    private Event mockEvent;
    private Status mockStatus;
    private PasswordData expectedPWData;
    private PasswordData actualPWData;

    @Mock
    EventsRepository eventsRepository;

    EventDataSource source;

    @BeforeEach
    void setUp(){

        MockitoAnnotations.initMocks(this);
        mockEvent = buildMockEvent();
        mockStatus = buildMockStatus();
        expectedPWData = buildExpectedPWData();
    }

    @Test
    void fetchNameDataTest(){
        source = new EventDataSource();
        when(eventsRepository.findById(1L)).thenReturn(java.util.Optional.ofNullable(mockEvent));
        actualPWData = source.fetchEventData(mockStatus, eventsRepository);
        assertTrue(expectedPWData.equals(actualPWData));
    }



    private PasswordData buildExpectedPWData() {
        PasswordData pwData = new PasswordData();
        pwData.setEvent("Shiloh");
        pwData.setYear("1862");
        return pwData;
    }

}