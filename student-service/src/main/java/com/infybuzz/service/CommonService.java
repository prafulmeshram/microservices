/**
 * 
 */
package com.infybuzz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infybuzz.feignclients.AddressFeignClient;
import com.infybuzz.response.AddressResponse;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

/**
 * @author jack
 *
 */
@Service
public class CommonService {

	@Autowired
	private AddressFeignClient addressFeignClient;

	@CircuitBreaker(name = "addressService", fallbackMethod = "fallbackGetAddressById")
	public AddressResponse getAddressById(long addressId) {
		AddressResponse addressResponse = this.addressFeignClient.getById(addressId);

		return addressResponse;
	}

	public AddressResponse fallbackGetAddressById(long addressId, Throwable throwable) {

		return new AddressResponse();
	}

}
