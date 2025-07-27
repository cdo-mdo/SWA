package org.edu.miu.cs590de.ordersystem.repository;

import org.edu.miu.cs590de.ordersystem.entity.Address;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends CrudRepository<Address, Integer> {
}
