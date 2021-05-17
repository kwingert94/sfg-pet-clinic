package guru.spring.framework.sfgpetclinic.controller;

import guru.spring.framework.sfgpetclinic.model.Owner;
import guru.spring.framework.sfgpetclinic.services.OwnerService;
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

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {
    @Mock
    OwnerService ownerService;

    @InjectMocks
    OwnerController controller;

    Set<Owner> owners;

    MockMvc mockMVC;

    @BeforeEach
    void setUp() {
        owners = new HashSet<>();
        Owner testOwner = new Owner();
        testOwner.setFirstName("test");
        testOwner.setLastName("tested");
        testOwner.setId(1L);
        owners.add(testOwner);

        Owner testOwner2 = new Owner();
        testOwner2.setFirstName("smith");
        testOwner2.setLastName("jack");
        testOwner2.setId(2L);
        owners.add(testOwner2);

        mockMVC = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void listOwners() throws Exception {

        when(ownerService.findAll()).thenReturn(owners);

        mockMVC.perform(get("/owners"))
                .andExpect(status().isOk())
        .andExpect(view().name("owners/index"))
        .andExpect(model().attribute("owners",hasSize(2)));
    }

    @Test
    void findOwners() throws Exception {

        mockMVC.perform(get("/find"))
                .andExpect(status().isOk())
                .andExpect(view().name("notimplemented"));
        verifyNoInteractions(ownerService);
    }
}