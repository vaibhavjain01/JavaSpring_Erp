package com.techprimers.db.repository;

import com.techprimers.db.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {
	Address findByAddress(String address);
}
