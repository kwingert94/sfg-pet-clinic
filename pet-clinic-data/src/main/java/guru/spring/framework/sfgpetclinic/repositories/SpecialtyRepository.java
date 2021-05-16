package guru.spring.framework.sfgpetclinic.repositories;

import guru.spring.framework.sfgpetclinic.model.Speciality;
import org.springframework.data.repository.CrudRepository;

public interface SpecialtyRepository extends CrudRepository<Speciality,Long> {
}
