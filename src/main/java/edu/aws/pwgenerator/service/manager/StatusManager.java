package edu.aws.pwgenerator.service.manager;

import edu.aws.pwgenerator.service.Status;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

@Component
public class StatusManager implements Manager{

    private Status status = new Status();

    public Status init() {

        //read status.json
        JSONParser jsonParser = new JSONParser();
        try {
            //Parsing the contents of the JSON file
            JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader("status.json"));

            //set fields in status
            status.setNameTracker((long) jsonObject.get("name"));
            status.setEventTracker((long) jsonObject.get("event"));
            status.setPlaceTracker((long) jsonObject.get("place"));
            status.setPwTypeTracker((long) jsonObject.get("type"));
            status.setSpecialCharacterTracker((long) jsonObject.get("spec"));


        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return status;
    }

    @Override
    public Status increment(Status st, int spec, long type) {
        
        if (st.getSpecialCharacterTracker() == spec) {
            st.setSpecialCharacterTracker(1L);
        } else {
            st.setSpecialCharacterTracker(st.getSpecialCharacterTracker() + 1L);
        }
        if (st.getPwTypeTracker() == type) {
            st.setPwTypeTracker(1L);
        } else {
            st.setPwTypeTracker(st.getPwTypeTracker() + 1L);
        }
        return st;
    }

    @Override
    public void update(Status st) {
        JSONObject statusjson = new JSONObject();
        statusjson.put("name", st.getNameTracker());
        statusjson.put("event", st.getEventTracker());
        statusjson.put("place", st.getPlaceTracker());
        statusjson.put("type", st.getPwTypeTracker());
        statusjson.put("spec", st.getSpecialCharacterTracker());
        try(FileWriter fw = new FileWriter("status.json")) {
            fw.write(statusjson.toJSONString());
            fw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
