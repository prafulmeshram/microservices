/**
 * 
 */
package com.infybuzz.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.infybuzz.response.AddressResponse;

/**
 * @author jack
 *
 */
@FeignClient(value = "api-gateway")
public interface AddressFeignClient {
	@GetMapping("/address-service/getById/{id}")
	public AddressResponse getById(@PathVariable long id);
}
