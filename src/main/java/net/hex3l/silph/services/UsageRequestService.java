package net.hex3l.silph.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.hex3l.silph.model.UsageRequest;
import net.hex3l.silph.repository.UsageRequestRepository;

@Service
public class UsageRequestService {
	@Autowired
	private UsageRequestRepository usageRequestRepository;
	
	@Transactional
	public UsageRequest add(UsageRequest usageRequest) {
		return this.usageRequestRepository.save(usageRequest);
	}
	
	public List<UsageRequest> all() {
		return (List<UsageRequest>) usageRequestRepository.findAll();
	}
	
	public UsageRequest findById(Long id) {
		return usageRequestRepository.findById(id).get();
	}


}
