package guru.spring.framework.sfgpetclinic.services;

import guru.spring.framework.sfgpetclinic.model.Owner;

import java.util.List;


public interface OwnerService extends CrudService<Owner, Long> {

    Owner findByLastName(String lastName);

    List<Owner> findByLastNameLike(String lastName);

}
