package com.techprimers.db.resource;

import com.techprimers.db.model.Address;
import com.techprimers.db.model.Skills;
import com.techprimers.db.repository.AddressRepository;
import com.techprimers.db.repository.UsersRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/rest/address")
public class AddressResource {

    @Autowired
    private static AddressRepository addressRepository;

    @GetMapping(value = "/all")
    public List<Address> getAll() {
        return addressRepository.findAll();
    }

    @PostMapping(value = "/load")
    public List<Address> persist(@RequestBody final Address address) {
    	addressRepository.save(address);
        return addressRepository.findAll();
    }
    
    public static Address checkOrAddAddress(String address) {
    	Address res = addressRepository.findByAddress(address);
		if(res == null) {
			Address addr = new Address();
			addr.setAddress(address);
			addressRepository.save(addr);
			res = addressRepository.findByAddress(address);
		}
		return res;
	}
	
	public static Integer getAddressId(String address) {
		int addressId = checkOrAddAddress(address).getAddressId();
		return addressId;
	}

	public static void setRepo(AddressRepository inAddressRepository) {
    	addressRepository = inAddressRepository;
    }
	
	public static boolean deleteAddress(Integer addressId) {
		if(addressRepository == null) {
			return false;
		}
		addressRepository.delete(addressId);
		return true;
	}
}
