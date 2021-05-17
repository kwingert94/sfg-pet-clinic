package guru.spring.framework.sfgpetclinic.services.map;

import guru.spring.framework.sfgpetclinic.model.Owner;
import guru.spring.framework.sfgpetclinic.model.Pet;
import guru.spring.framework.sfgpetclinic.services.OwnerService;
import guru.spring.framework.sfgpetclinic.services.PetService;
import guru.spring.framework.sfgpetclinic.services.PetTypeService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
@Profile({"default","map"})
public class OwnerServiceMap extends AbstractMapService<Owner, Long> implements OwnerService {


    private final PetTypeService petTypeService;
    private final PetService petService;

    public OwnerServiceMap(PetTypeService petTypeService, PetService petService) {
        this.petTypeService = petTypeService;
        this.petService = petService;
    }


    @Override
    public Set<Owner> findAll() {
        return super.findAll();
    }

    @Override
    public Owner findByID(Long id) {
        return super.findByID(id);
    }

    @Override
    public Owner save(Owner object) {

        if(object != null){
            if(object.getPets() != null) {
                object.getPets().forEach(pet -> {
                    if(pet.getPetType() != null) {
                        if (pet.getPetType().getId() == null) {
                            pet.setPetType(petTypeService.save(pet.getPetType()));
                        }
                    } else{
                        throw new RuntimeException("Pet Type is required");
                    }
                    if(pet.getId() == null) {
                        Pet savedPet = petService.save(pet);
                        pet.setId(savedPet.getId());
                    }
                });
            }
            return super.save(object);
        }
        else{
            return null;
        }
    }

    @Override
    public void delete(Owner object) {
        super.delete(object);

    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public Owner findByLastName(String lastName) {
        return this.findAll()
                .stream()
                .filter(owner -> owner.getLastName().equalsIgnoreCase(lastName))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Owner> findByLastNameContainingIgnoreCase(String lastName) {
        return null;
    }
}
