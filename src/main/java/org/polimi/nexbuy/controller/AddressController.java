package org.polimi.nexbuy.controller;


import lombok.RequiredArgsConstructor;
import org.polimi.nexbuy.DTO.request.AddressRequest;
import org.polimi.nexbuy.DTO.response.AddressResponse;
import org.polimi.nexbuy.exception.AddressAlreadyExistsException;
import org.polimi.nexbuy.exception.AddressNotFoundException;
import org.polimi.nexbuy.model.Address;
import org.polimi.nexbuy.repository.AddressRepository;
import org.polimi.nexbuy.service.def.AddressService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Ecommerce/address")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;

    //creare un nuovo indirizzo
    @PostMapping("/add")
    public ResponseEntity<AddressResponse> addAddress(@RequestBody AddressRequest addressRequest) {
        try{

            if(addressRequest.equals(null)){
                throw new RuntimeException("AddressRequest is null");
            }

            if(addressService.findAddressesByStreetAndCityAndState(addressRequest) != null) {
                throw new AddressAlreadyExistsException("Indirizzo già esistente");
            }

            Address address = new Address();
            address.setStreet(addressRequest.getStreet());
            address.setCity(addressRequest.getCity());
            address.setState(addressRequest.getState());
            address.setZipCode(addressRequest.getZipCode());
            address.setCountry(addressRequest.getCountry());

            addressService.addAddress(address);

            return ResponseEntity.ok(new AddressResponse(address.getId(), address.getStreet(), address.getCity(), address.getState(), address.getZipCode(), address.getCountry()));  //status 200: OK

        } catch(AddressAlreadyExistsException e){
            AddressResponse response = new AddressResponse();
            response.setStreet("Indirizzo già esistente");
            response.setId(-1L);
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response); //status 409: Conflict
        }catch (Exception e){
            return ResponseEntity.badRequest().build();  //status 400: Bad Request
        }
    }

    //ottieni un indirizzo per ID
    @GetMapping("/{id}")
    public ResponseEntity<AddressResponse> getAddressById(@PathVariable Long id) {
        Address address = addressService.getAddressById(id)
                .orElseThrow(() -> new AddressNotFoundException("Indirizzo con ID " + id + " non trovato"));

        AddressResponse response = new AddressResponse();
        response.setId(address.getId());
        response.setStreet(address.getStreet());
        response.setCity(address.getCity());
        response.setState(address.getState());
        response.setZipCode(address.getZipCode());
        response.setCountry(address.getCountry());

        return ResponseEntity.ok(response);  //status 200: OK
    }

    //ottieni tutti gli indirizzi
    @GetMapping("/all")
    public ResponseEntity<List<AddressResponse>> getAllAddresses() {
        List<Address> addresses = addressService.getAllAddresses();

        List<AddressResponse> responses = addresses.stream()
                .map(address -> {
                    AddressResponse response = new AddressResponse();
                    response.setId(address.getId());
                    response.setStreet(address.getStreet());
                    response.setCity(address.getCity());
                    response.setState(address.getState());
                    response.setZipCode(address.getZipCode());
                    response.setCountry(address.getCountry());
                    return response;
                })
                .toList();

        return ResponseEntity.ok(responses);  // Status 200: OK
    }

    //aggiorna un indirizzo esistente
    @PutMapping("/update/{id}")
    public ResponseEntity<AddressResponse> updateAddress(@PathVariable Long id, @RequestBody AddressRequest addressRequest) {
        Address address = new Address();
        address.setId(id);
        address.setStreet(addressRequest.getStreet());
        address.setCity(addressRequest.getCity());
        address.setState(addressRequest.getState());
        address.setZipCode(addressRequest.getZipCode());
        address.setCountry(addressRequest.getCountry());

        Address updatedAddress = addressService.updateAddress(address);

        AddressResponse response = new AddressResponse();
        response.setId(updatedAddress.getId());
        response.setStreet(updatedAddress.getStreet());
        response.setCity(updatedAddress.getCity());
        response.setState(updatedAddress.getState());
        response.setZipCode(updatedAddress.getZipCode());
        response.setCountry(updatedAddress.getCountry());

        return ResponseEntity.ok(response);  // Status 200: OK
    }

    //elimina un indirizzo
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable Long id) {
        addressService.deleteAddress(id);
        return ResponseEntity.noContent().build();  //status 204: No Content
    }
}
