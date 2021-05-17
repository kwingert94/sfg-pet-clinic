package guru.spring.framework.sfgpetclinic.controller;

import guru.spring.framework.sfgpetclinic.model.Owner;
import guru.spring.framework.sfgpetclinic.model.Pet;
import guru.spring.framework.sfgpetclinic.model.PetType;
import guru.spring.framework.sfgpetclinic.services.OwnerService;
import guru.spring.framework.sfgpetclinic.services.PetService;
import guru.spring.framework.sfgpetclinic.services.PetTypeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class PetControllerTest {
    @Mock
    PetService petService;
    @Mock
    OwnerService ownerService;
    @Mock
    PetTypeService petTypeService;

    @InjectMocks
    PetController controller;

    Owner owner;
    Set<PetType> petTypes;
    Set<Pet> pets;

    MockMvc mockMvc;

    Pet testPet;
    Pet testPet2;

    @BeforeEach
    void setUp() {

        petTypes = new HashSet<>();
        owner = new Owner();
        owner.setId(1L);
        //ownerService.save(owner);

        PetType testPetType = new PetType();
        testPetType.setName("Dog");
        testPetType.setId(1L);
        petTypes.add(testPetType);

        PetType testPetType2 = new PetType();
        testPetType2.setName("Cat");
        testPetType2.setId(2L);
        petTypes.add(testPetType2);


        pets = new HashSet<>();
        testPet = new Pet();
        testPet.setName("spigles");
        testPet.setId(1L);
        pets.add(testPet);

        testPet2 = new Pet();
        testPet2.setName("sadie");
        testPet2.setId(2L);
        pets.add(testPet2);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }


    @Test
    void initCreationForm() throws Exception {
        when(ownerService.findByID(anyLong())).thenReturn(owner);
        when(petTypeService.findAll()).thenReturn(petTypes);

        mockMvc.perform(get("/owners/1/pets/new"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("owner"))
                .andExpect(model().attributeExists("pet"))
                .andExpect(view().name("pets/createOrUpdatePetForm"));
    }

    @Test
    void processCreationForm() throws Exception {
        when(ownerService.findByID(anyLong())).thenReturn(owner);
        when(petTypeService.findAll()).thenReturn(petTypes);

        mockMvc.perform(post("/owners/1/pets/new"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/1"));

        verify(petService).save(any());
    }

    @Test
    void initUpdateForm() throws Exception {
        when(ownerService.findByID(anyLong())).thenReturn(owner);
        when(petTypeService.findAll()).thenReturn(petTypes);
        when(petService.findByID(anyLong())).thenReturn(testPet2);

        mockMvc.perform(get("/owners/1/pets/2/edit"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("owner"))
                .andExpect(model().attributeExists("pet"))
                .andExpect(view().name("pets/createOrUpdatePetForm"));
    }

    @Test
    void processUpdateForm() throws Exception {
        when(ownerService.findByID(anyLong())).thenReturn(owner);
        when(petTypeService.findAll()).thenReturn(petTypes);

        mockMvc.perform(post("/owners/1/pets/2/edit"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/1"));

        verify(petService).save(any());
    }

    @Test
    void populatePetTypes() {
        //todo impl
    }

    @Test
    void findOwner() {
        //todo impl
    }

    @Test
    void initOwnerBinder() {
        //todo impl
    }


}
