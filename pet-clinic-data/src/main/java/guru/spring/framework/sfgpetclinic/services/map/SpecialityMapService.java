package guru.spring.framework.sfgpetclinic.services.map;

import guru.spring.framework.sfgpetclinic.model.Speciality;
import guru.spring.framework.sfgpetclinic.services.SpecialtyService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
@Profile({"default","map"})
public class SpecialityMapService extends AbstractMapService<Speciality,Long> implements SpecialtyService {

    @Override
    public Set<Speciality> findAll() {
        return super.findAll();
    }

    @Override
    public Speciality findByID(Long id) {
        return super.findByID(id);
    }

    @Override
    public Speciality save(Speciality object) {
        return super.save(object);
    }

    @Override
    public void delete(Speciality object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }
}
