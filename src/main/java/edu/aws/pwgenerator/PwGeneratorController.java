package edu.aws.pwgenerator;

import edu.aws.pwgenerator.service.PwGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class PwGeneratorController {

    @Autowired
    PwGeneratorService service;

    @RequestMapping
    public String showMain(){

        return "mainview";
    }

    @RequestMapping("/password")
    public String getNewPassword(ModelMap model) {
        String pw = service.getAPassword();
        model.put("password", "Password Test");
        return "mainview";
    }

    @RequestMapping("/updatesc")
    public String updateSpecialCharacters(ModelMap model) {

        return "specchars";
    }

    @RequestMapping("/procsc")
    public String processSpecialCharacters(ModelMap model){

        return "mainview";
    }


}
