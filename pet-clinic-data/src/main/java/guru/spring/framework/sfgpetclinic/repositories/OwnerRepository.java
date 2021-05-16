package guru.spring.framework.sfgpetclinic.repositories;

import guru.spring.framework.sfgpetclinic.model.Owner;
import org.springframework.data.repository.CrudRepository;

public interface OwnerRepository extends CrudRepository<Owner,Long> {
}
