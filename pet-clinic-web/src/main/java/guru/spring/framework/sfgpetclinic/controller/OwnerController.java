package guru.spring.framework.sfgpetclinic.controller;

import guru.spring.framework.sfgpetclinic.services.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class OwnerController {

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }


    @RequestMapping({"/owners", "/owners/index", "owners/index.html", "owners/find"})
    public String listOwners(Model model){

        model.addAttribute( "owners", ownerService.findAll());

        return "owners/index";
    }

    @RequestMapping({"/find","/oups"})
    public String findOwners(){
        return "notimplemented";
    }

}
