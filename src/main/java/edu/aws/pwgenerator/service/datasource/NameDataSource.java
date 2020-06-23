package edu.aws.pwgenerator.service.datasource;

import edu.aws.pwgenerator.domain.Name;
import edu.aws.pwgenerator.domain.NamesRepository;
import edu.aws.pwgenerator.service.Status;
import edu.aws.pwgenerator.service.builder.PasswordData;

public class NameDataSource {

    public PasswordData fetchNameData(Status st, NamesRepository namesRepository) {
        PasswordData data = new PasswordData();
        Name name = namesRepository.findById(st.getNameTracker()).orElseThrow(() -> new IllegalArgumentException("Invalid Name ID"));
        data.setFirstname(name.getFirstname());
        data.setLastname(name.getLastname());
        data.setYear(name.getYear());
        st.setNameTracker(st.getNameTracker() + 1);

        return data;
    }
}
