package be.fooda.backend.store.view.client;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Component
public class ContactClient {

    public boolean existsById(Long id) {
        return true;
    }

}
