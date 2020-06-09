package edu.aws.pwgenerator.service;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PasswordBuilderTest {

    private Status status;
    //private PasswordData passwordData;
    //private PasswordBuilder pwbuilder;
    private List<PasswordBuilder> pwbuilders = new ArrayList<>();
    private static final int MAX_TEST_TYPE = 8;
    private static final String SEPERATOR = "$";

    @BeforeEach
    void setUp() {

        for (int x = 0; x <= MAX_TEST_TYPE; x++) {
            PasswordData passwordData = new PasswordData();

            passwordData.setPlace("Raleigh");
            passwordData.setFirstname("Adam");
            passwordData.setLastname("Smith");
            passwordData.setYear("1757");
            passwordData.setEvent("War");
            passwordData.setSeperator(SEPERATOR);
            passwordData.setType(x);

            PasswordBuilder pwbuilder = new PasswordBuilder(passwordData);
            pwbuilders.add(x, pwbuilder);
        }
    }

    @Test
    void builderFirstNamePlaceTest() {
        assertEquals( "Adam$Raleigh", pwbuilders.get(0).builder());
    }

    @Test
    void builderFullNamePlaceTest() {
        assertEquals( "AdamSmith$Raleigh", pwbuilders.get(1).builder());
    }

    @Test
    void builderYearFirstNameTest() {
        assertEquals("1757$Adam", pwbuilders.get(2).builder());
    }

    @Test
    void builderYearFullNameTest() {
        assertEquals("1757$AdamSmith", pwbuilders.get(3).builder());
    }

    @Test
    void builderSplitYearFirstNameTest(){
        assertEquals("17$Adam$57", pwbuilders.get(4).builder());
    }

    @Test
    void builderSplitYearFullNameTest(){
        assertEquals("17$AdamSmith$57", pwbuilders.get(5).builder());
    }

    @Test
    void builderYearEvent() {
        assertEquals("1757$War", pwbuilders.get(6).builder());
    }

    @Test
    void builderSplitYearEvent() {
        assertEquals("17$War$57", pwbuilders.get(7).builder());
    }

    @Test
    void builderInvalidPasswordType() {
        assertEquals("Invalid Password Type", pwbuilders.get(8).builder());
    }
}