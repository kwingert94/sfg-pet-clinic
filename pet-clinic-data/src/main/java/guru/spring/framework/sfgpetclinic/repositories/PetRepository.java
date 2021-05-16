package guru.spring.framework.sfgpetclinic.repositories;

import guru.spring.framework.sfgpetclinic.model.Pet;
import org.springframework.data.repository.CrudRepository;

public interface PetRepository extends CrudRepository<Pet,Long> {}
