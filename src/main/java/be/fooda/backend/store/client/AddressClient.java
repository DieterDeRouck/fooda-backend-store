package be.fooda.backend.store.client;

import be.fooda.backend.store.model.entity.AddressEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Component
public class AddressClient {
    public List<AddressEntity> getAddresses(UUID externalUserId) {
        List<AddressEntity> addresses = new ArrayList<>();

        AddressEntity addressHome = new AddressEntity();
        AddressEntity addressWork = new AddressEntity();

        addressHome.setId(UUID.randomUUID());
        addressHome.setEAddressId(UUID.randomUUID());
        addressHome.setMunicipality("Leuven");
        addressHome.setPostcode("3000");

        addressWork.setId(UUID.randomUUID());
        addressWork.setEAddressId(UUID.randomUUID());
        addressWork.setMunicipality("Brussels");
        addressWork.setPostcode("1000");

        addresses.add(addressHome);
        addresses.add(addressWork);

        return addresses;
    }

    public boolean existsById(UUID addressId) {
        return true;
    }
}
