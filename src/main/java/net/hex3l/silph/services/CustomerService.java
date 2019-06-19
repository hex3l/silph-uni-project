package net.hex3l.silph.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.hex3l.silph.model.Customer;
import net.hex3l.silph.model.UsageRequest;
import net.hex3l.silph.repository.CustomerRepository;

@Service
public class CustomerService {
	@Autowired
	private CustomerRepository customerRepository;
	
	@Transactional
	public Customer add(Customer customer) {
		return this.customerRepository.save(customer);
	}

}
