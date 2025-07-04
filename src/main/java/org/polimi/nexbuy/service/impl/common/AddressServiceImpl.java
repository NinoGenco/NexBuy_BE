package org.polimi.nexbuy.service.impl.common;

import lombok.RequiredArgsConstructor;
import org.polimi.nexbuy.DTO.request.common.AddressRequest;
import org.polimi.nexbuy.model.common.Address;
import org.polimi.nexbuy.repository.common.AddressRepository;
import org.polimi.nexbuy.service.def.common.AddressService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;


    public Address addAddress(Address address) {
        return addressRepository.save(address);
    }

    public Optional<Address> getAddressById(Long id) {
        return addressRepository.findById(id);
    }

    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

    public Address updateAddress(Address address) {
        return null;
    }

    public void deleteAddress(Long id) {
        addressRepository.deleteById(id);
    }

    public  Address findAddressesByStreetAndCityAndState(AddressRequest addressRequest) {
        return addressRepository.findByStreetAndCityAndState(addressRequest.getStreet(), addressRequest.getCity(), addressRequest.getState());
    }


}
