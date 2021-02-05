package be.fooda.backend.store.client;

import be.fooda.backend.store.model.entity.ContactEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Component
public class ContactClient {

    public List<ContactEntity> getContacts(UUID externalUserId) {
        List<ContactEntity> contacts = new ArrayList<>();

        ContactEntity contactHome = new ContactEntity();
        ContactEntity contactWork = new ContactEntity();

        contactHome.setId(UUID.randomUUID());
        contactHome.setEmail("ahmet.ozdemir@student@intecbrussel.be");
        contactHome.setEContactId(UUID.randomUUID());
        contactHome.setLastName("Ozdemir");
        contactHome.setFirstName("Ahmet");
        contactHome.setPhone("+32488490509");

        contactWork.setId(UUID.randomUUID());
        contactWork.setEmail("ahmet.ozdemir@student@intecbrussel.be");
        contactWork.setEContactId(UUID.randomUUID());
        contactWork.setLastName("Ozdemir");
        contactWork.setFirstName("Ahmet");
        contactWork.setPhone("+322345456");


        contacts.add(contactHome);
        contacts.add(contactWork);

        return contacts;
    }

    public boolean existsById(UUID ContactId) {
        return true;
    }

}
