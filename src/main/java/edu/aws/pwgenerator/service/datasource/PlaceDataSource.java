package edu.aws.pwgenerator.service.datasource;

import edu.aws.pwgenerator.domain.Name;
import edu.aws.pwgenerator.domain.NamesRepository;
import edu.aws.pwgenerator.domain.Place;
import edu.aws.pwgenerator.domain.PlacesRepository;
import edu.aws.pwgenerator.service.Status;
import edu.aws.pwgenerator.service.builder.PasswordData;

public class PlaceDataSource  {

    public PasswordData fetchPlaceData(Status st, NamesRepository namesRepository, PlacesRepository placesRepository) {
        PasswordData data = new PasswordData();
        Name name = namesRepository.findById(st.getNameTracker()).orElseThrow(() -> new IllegalArgumentException("Invalid Name ID"));
        data.setFirstname(name.getFirstname());
        data.setLastname(name.getLastname());
        data.setYear(name.getYear());
        Place place = placesRepository.findById(st.getPlaceTracker()).orElseThrow(() -> new IllegalArgumentException("Invalid Place ID"));
        data.setPlace(place.getPlace());

        if (st.getNameTracker() == namesRepository.count()) {
            st.setNameTracker(1L);
        } else {
            st.setNameTracker(st.getNameTracker() + 1L);
        }

        if (st.getPlaceTracker() == placesRepository.count()) {
            st.setPlaceTracker(1L);
        } else {
            st.setPlaceTracker(st.getPlaceTracker() + 1L);
        }

        return data;
    }
}
