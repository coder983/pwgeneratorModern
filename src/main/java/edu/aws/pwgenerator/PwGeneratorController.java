package edu.aws.pwgenerator;

import edu.aws.pwgenerator.domain.NamesRepository;
import edu.aws.pwgenerator.service.builder.PasswordDataLoader;
import edu.aws.pwgenerator.service.PwGeneratorService;
import edu.aws.pwgenerator.service.manager.StatusManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class PwGeneratorController {

    @Autowired
    PwGeneratorService service;

    @Autowired
    NamesRepository namesRepository;

    @Autowired
    PasswordDataLoader loader;

    StatusManager initializer = new StatusManager();

    @RequestMapping
    public String showMain(){

        return "mainview";
    }

    @RequestMapping("/password")
    public String getNewPassword(ModelMap model) {
        String pw = service.getAPassword(initializer, loader);
        model.put("password", pw);
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
