package guru.spring.framework.sfgpetclinic.services.springdatajpa;


import guru.spring.framework.sfgpetclinic.model.Speciality;
import guru.spring.framework.sfgpetclinic.repositories.SpecialtyRepository;
import guru.spring.framework.sfgpetclinic.services.SpecialtyService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("springdatajpa")
public class SpecialtySDJavaService implements SpecialtyService {

    private final SpecialtyRepository specialtiyRepository;

    public SpecialtySDJavaService(SpecialtyRepository specialtiyRepository) {
        this.specialtiyRepository = specialtiyRepository;
    }

    @Override
    public Set<Speciality> findAll() {
        Set<Speciality> specialities = new HashSet<>();
        specialtiyRepository.findAll().forEach(specialities::add);
        return specialities;
    }

    @Override
    public Speciality findByID(Long aLong) {
        return specialtiyRepository.findById(aLong).orElse(null);
    }

    @Override
    public Speciality save(Speciality object) {
        return specialtiyRepository.save(object);
    }

    @Override
    public void delete(Speciality object) {
        specialtiyRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        specialtiyRepository.deleteById(aLong);
    }
}
