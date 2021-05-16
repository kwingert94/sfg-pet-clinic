package guru.spring.framework.sfgpetclinic.services.map;


import guru.spring.framework.sfgpetclinic.model.Visit;
import guru.spring.framework.sfgpetclinic.services.VisitService;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public class VisitMapService extends AbstractMapService<Visit,Long> implements VisitService {

    @Override
    public Set<Visit> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);

    }

    @Override
    public void delete(Visit object) {
        super.delete(object);
    }

    @Override
    public Visit save(Visit visit) {

        if(visit.getPet() == null || visit.getPet().getOwner() == null || visit.getPet().getId() == null ||
        visit.getPet().getOwner().getId() == null){

            throw new RuntimeException("Invalid Visits");

        }

        return super.save(visit);
    }

    @Override
    public Visit findByID(Long id) {
        return super.findByID(id);
    }
}
