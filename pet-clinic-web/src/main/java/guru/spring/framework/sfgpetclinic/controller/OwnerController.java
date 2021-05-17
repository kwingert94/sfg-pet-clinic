package guru.spring.framework.sfgpetclinic.controller;

import guru.spring.framework.sfgpetclinic.model.Owner;
import guru.spring.framework.sfgpetclinic.services.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class OwnerController {

    private static final String VIEW_OWNER_CREATE_OR_UPDATE_FORM = "owners/createOrUpdateOwnerForm";

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }


     @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

/*    @RequestMapping({ "/owners/index", "owners/index.html"})
    public String listOwners(Model model){

        model.addAttribute( "owners", ownerService.findAll());

        return "owners/index";
    }*/

    @RequestMapping({"/owners/find"})
    public String findOwners(Model model){
        Owner owner = new Owner();
        model.addAttribute("owner", Owner.builder().build());
        return "owners/findOwners";
    }

    @GetMapping("/owners")
    public String processFindForm(Owner owner, BindingResult result, Model model) {
        //allow parameterless GET requests for /owners to return all records
        if(owner.getLastName() == null) {
            owner.setLastName(""); // empty string signifies broadest possible search
        }
        List<Owner> results = ownerService.findByLastNameContainingIgnoreCase(owner.getLastName().toLowerCase());

        if(results.isEmpty()) {
            // no owners found
            result.rejectValue("lastName", "notFound", "not found");
            return "owners/findOwners";
        }
        else if (results.size() == 1) {
            // 1 owner found
            owner = results.get(0);
            return "redirect:/owners/" + owner.getId();
        }
        else {
            //multiple owners found
            model.addAttribute("selections",results);
            return "owners/ownersList";
        }

    }

    @GetMapping("/owners/{ownerId}")
    public ModelAndView showOwner(@PathVariable("ownerId") Long ownerId) {
        ModelAndView mav = new ModelAndView("owners/ownerDetails");
        mav.addObject(ownerService.findByID(ownerId));
        return mav;
    }


    @GetMapping("/owners/new")
    public String initCreationForm(Model model) {
        Owner owner = Owner.builder().build();
        model.addAttribute("owner", owner);
        return VIEW_OWNER_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/owners/new")
    public String processCreationForm(@Validated Owner owner, BindingResult result) {
        if(result.hasErrors()) {
            return VIEW_OWNER_CREATE_OR_UPDATE_FORM;
        }
        else {
            Owner savedOwner = ownerService.save(owner);
            return "redirect:/owners/" + savedOwner.getId();
        }
    }

    @GetMapping("/owners/{ownerId}/edit")
    public String initialUpdateOwnerForm(@PathVariable("ownerId") Long ownerId, Model model) {
        Owner owner = ownerService.findByID(ownerId);
        model.addAttribute(owner);
        return VIEW_OWNER_CREATE_OR_UPDATE_FORM;

    }

    @PostMapping("/owners/{ownerId}/edit")
    public String processUpdateOwnerForm(@Validated Owner owner, BindingResult result, @PathVariable("ownerId") long ownerId) {
       if(result.hasErrors()) {
           return VIEW_OWNER_CREATE_OR_UPDATE_FORM;
       } else {
           owner.setId(ownerId);
           Owner savedOwner = ownerService.save(owner);
           return "redirect:/owners/" + savedOwner.getId();
       }

    }
}
