package be.fooda.backend.store.dao;

import be.fooda.backend.store.model.entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface AddressRepository extends JpaRepository<AddressEntity, UUID> {

    @Query("SELECT s FROM AddressEntity s WHERE s.eAddressId IN :address")
    List<AddressEntity> findByExternalAddressId(@Param("address") final UUID address);

    //List<FoodaStoreAddress> findAllByName(String name);
    @Query("SELECT s FROM AddressEntity s WHERE s.municipality IN :municipality")
    List<AddressEntity> findByMunicipality(@Param("municipality") final String municipality);

    @Query("SELECT s FROM AddressEntity s WHERE s.postcode IN :postcode")
    List<AddressEntity> findByPostcode(@Param("postcode") final String postcode);

    @Query("SELECT s FROM AddressEntity s WHERE s.municipality IN :municipality AND s.postcode IN :postcode")
    List<AddressEntity> findByMunicipalityAndPostcode(String municipality, String postcode);

    @Query("SELECT s FROM AddressEntity s WHERE s.city IN :city ")
    List<AddressEntity> findByCity(String city);
}
