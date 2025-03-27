package org.polimi.nexbuy.service.def;

import org.polimi.nexbuy.DTO.request.AddressRequest;
import org.polimi.nexbuy.model.Address;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface AddressService {

    Address addAddress(Address address);
    Optional<Address> getAddressById(Long id);
    List<Address> getAllAddresses();
    Address updateAddress(Address address);
    void deleteAddress(Long id);
    Address findAddressesByStreetAndCityAndState(AddressRequest addressRequest);
}
