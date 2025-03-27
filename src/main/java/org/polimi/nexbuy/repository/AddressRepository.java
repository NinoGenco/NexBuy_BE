package org.polimi.nexbuy.repository;

import org.polimi.nexbuy.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    Address findByStreetAndCityAndStateAndZipCodeAndCountry(String street, String city, String state, String zipCode, String country);

    List<Address> findAddressesByCityAndState(String city, String state);

    //Query personalizzata che ricerca un indirizzo per via, città e stato
    Address findByStreetAndCityAndState(String street, String city, String state);
}
