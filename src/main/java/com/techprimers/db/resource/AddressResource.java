package com.techprimers.db.resource;

import com.techprimers.db.model.Address;
import com.techprimers.db.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/rest/address")
public class AddressResource {

    @Autowired
    AddressRepository addressRepository;

    @GetMapping(value = "/all")
    public List<Address> getAll() {
        return addressRepository.findAll();
    }

    @PostMapping(value = "/load")
    public List<Address> persist(@RequestBody final Address address) {
    	addressRepository.save(address);
        return addressRepository.findAll();
    }

}
