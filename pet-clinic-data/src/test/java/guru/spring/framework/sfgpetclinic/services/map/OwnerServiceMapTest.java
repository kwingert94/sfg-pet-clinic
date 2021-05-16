package guru.spring.framework.sfgpetclinic.services.map;

import guru.spring.framework.sfgpetclinic.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class OwnerServiceMapTest {

    OwnerServiceMap ownerServiceMap;

    final Long ownerId = 1L;

    @BeforeEach
    void setUp() {

        ownerServiceMap = new OwnerServiceMap(new PetTypeMapService(), new PetServiceMap());

        Owner testOwner = new Owner();
        testOwner.setId(1L);
        testOwner.setFirstName("test");
        testOwner.setLastName("tested");

        ownerServiceMap.save(testOwner);
    }

    @Test
    void findAll() {
        Set<Owner> ownerSet = ownerServiceMap.findAll();

        assertEquals(1,ownerSet.size());
    }

    @Test
    void findByID() {
        Owner owner = ownerServiceMap.findByID(ownerId);

        assertEquals(ownerId, owner.getId());
    }

    @Test
    void saveExistingId() {
        Owner testOwner2 = new Owner();
        testOwner2.setId(2L);
        testOwner2.setFirstName("test");
        testOwner2.setLastName("tested");

        Owner savedOwner = ownerServiceMap.save(testOwner2);

        assertEquals(2L,savedOwner.getId());
    }

    @Test
    void saveNoId() {
        Owner testOwner2 = new Owner();
        testOwner2.setFirstName("test");
        testOwner2.setLastName("tested");

        Owner savedOwner = ownerServiceMap.save(testOwner2);

        assertNotNull(savedOwner);
        assertNotNull(savedOwner.getId());
    }

    @Test
    void delete() {
        ownerServiceMap.delete(ownerServiceMap.findByID(ownerId));
        assertEquals(0,ownerServiceMap.findAll().size());
    }

    @Test
    void deleteById() {

        ownerServiceMap.deleteById(ownerId);
        assertEquals(0,ownerServiceMap.findAll().size());

    }

    @Test
    void findByLastName() {
        assertEquals(1L,ownerServiceMap.findByLastName("tested").getId());
    }

    @Test
    void findByLastNameNotFound() {
        assertNull(ownerServiceMap.findByLastName("foo"));
    }
}