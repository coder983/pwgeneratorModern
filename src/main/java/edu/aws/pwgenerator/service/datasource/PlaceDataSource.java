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
        st.setNameTracker(st.getNameTracker() + 1);
        st.setPlaceTracker(st.getPlaceTracker() + 1);

        return data;
    }
}
