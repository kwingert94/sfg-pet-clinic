package guru.spring.framework.sfgpetclinic.services.map;

import guru.spring.framework.sfgpetclinic.model.PetType;
import guru.spring.framework.sfgpetclinic.services.PetTypeService;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public class PetTypeMapService extends AbstractMapService<PetType, Long> implements PetTypeService {


    @Override
    public Set<PetType> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(PetType object) {
        super.delete(object);

    }

    @Override
    public PetType save(PetType object) {
        return super.save(object);
    }

    @Override
    public PetType findByID(Long id) {
        return super.findByID(id);
    }
}
