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
            status.setNameTracker((int) jsonObject.get("name"));
            status.setEventTracker((int) jsonObject.get("event"));
            status.setPlaceTracker((int) jsonObject.get("place"));
            status.setPwTypeTracker((int) jsonObject.get("type"));


        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return status;
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
