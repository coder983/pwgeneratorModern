package edu.aws.pwgenerator.service.manager;

import edu.aws.pwgenerator.service.Status;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

@Component
public class StatusManager implements Manager{

    private Status status = new Status();

    private static final int MAPPADDING = 6;

    public Status init() {

        //read status.json
        JSONParser jsonParser = new JSONParser();
        try {
            //Parsing the contents of the JSON file
            JSONObject statusObject = (JSONObject) jsonParser.parse(new FileReader("status.json"));
            JSONObject paddingObject = (JSONObject) jsonParser.parse(new FileReader("padding.json"));

            //set fields in status
            status.setNameTracker((long) statusObject.get("name"));
            status.setEventTracker((long) statusObject.get("event"));
            status.setPlaceTracker((long) statusObject.get("place"));
            status.setPwTypeTracker((long) statusObject.get("type"));
            status.setSpecialCharacterTracker((long) statusObject.get("spec"));

            //set up padding HashMap
            HashMap<String, String> pdg = new HashMap<>();
            for (int x = 1; x < (MAPPADDING + 1); x++) {
                String key = String.valueOf(x);
                pdg.put(key, (String)paddingObject.get(key));
            }

            status.setPadding(pdg);

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
