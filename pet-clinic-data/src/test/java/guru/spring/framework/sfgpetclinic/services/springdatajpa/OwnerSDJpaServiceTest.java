package guru.spring.framework.sfgpetclinic.services.springdatajpa;

import guru.spring.framework.sfgpetclinic.model.Owner;
import guru.spring.framework.sfgpetclinic.repositories.OwnerRepository;
import guru.spring.framework.sfgpetclinic.repositories.PetRepository;
import guru.spring.framework.sfgpetclinic.repositories.PetTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OwnerSDJpaServiceTest {

    @Mock
    OwnerRepository ownerRepository;
    @Mock
    PetRepository petRepository;
    @Mock
    PetTypeRepository petTypeRepository;
    @InjectMocks
    OwnerSDJpaService service;

    Owner testOwner;
    Owner testOwner2;

    @BeforeEach
    void setUp() {

        testOwner = new Owner();
        testOwner.setFirstName("test");
        testOwner.setLastName("tested");
        testOwner.setId(1L);
        ownerRepository.save(testOwner);

        testOwner2 = new Owner();
        testOwner2.setFirstName("smith");
        testOwner2.setLastName("jack");
        testOwner2.setId(2L);
        ownerRepository.save(testOwner2);


    }

    @Test
    void findByLastName() {

        when(ownerRepository.findByLastName(any())).thenReturn(testOwner);
        Owner smith = ownerRepository.findByLastName("tested");
        assertEquals(1L,smith.getId());

        verify(ownerRepository).findByLastName(any());
    }

    @Test
    void findAll() {
        Set<Owner> returnOwnersSet = new HashSet<>();
        returnOwnersSet.add(testOwner);
        returnOwnersSet.add(testOwner2);
        when(ownerRepository.findAll()).thenReturn(returnOwnersSet);

        Set<Owner> owners = service.findAll();

        assertNotNull(owners);
        assertEquals(2,owners.size());

    }

    @Test
    void findByID() {
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.of(testOwner));

        Owner owner = service.findByID(1L);

        assertNotNull(owner);

    }

    @Test
    void save() {
        Owner testOwner3 = new Owner();
        testOwner3.setFirstName("jack");
        testOwner3.setLastName("wayson");
        testOwner3.setId(3L);
        when(ownerRepository.save(any())).thenReturn(testOwner);

        Owner savedOwner = service.save(testOwner3);

        assertNotNull(savedOwner);


    }

    @Test
    void delete() {
        service.delete(testOwner);

        verify(ownerRepository).delete(any());
    }

    @Test
    void deleteById() {
        service.deleteById(1L);
        verify(ownerRepository).deleteById(anyLong());
    }
}