package guru.spring.framework.sfgpetclinic.repositories;

import guru.spring.framework.sfgpetclinic.model.Owner;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface OwnerRepository extends CrudRepository<Owner,Long> {

    Owner findByLastName(String lastName);

    List<Owner> findByLastNameLike (String lastName);



}
