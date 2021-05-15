package guru.spring.framework.sfgpetclinic.services;

import guru.spring.framework.sfgpetclinic.model.Owner;




public interface OwnerService extends CrudService<Owner, Long> {

    Owner findByLastName(String lastName);

}
