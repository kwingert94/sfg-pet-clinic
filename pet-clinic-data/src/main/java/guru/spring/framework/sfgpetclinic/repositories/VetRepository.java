package guru.spring.framework.sfgpetclinic.repositories;

import guru.spring.framework.sfgpetclinic.model.Vet;
import org.springframework.data.repository.CrudRepository;

public interface VetRepository extends CrudRepository<Vet,Long> {
}
