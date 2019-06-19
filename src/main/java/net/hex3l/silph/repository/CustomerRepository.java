package net.hex3l.silph.repository;

import org.springframework.data.repository.CrudRepository;

import net.hex3l.silph.model.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

}
