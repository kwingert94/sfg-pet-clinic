package guru.spring.framework.sfgpetclinic.repositories;

import guru.spring.framework.sfgpetclinic.model.PetType;
import org.springframework.data.repository.CrudRepository;

public interface PetTypeRepository extends CrudRepository<PetType,Long> {
}
