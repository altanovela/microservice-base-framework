package com.metranet.finbox.service.member.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.metranet.finbox.service.member.dto.ClientDto;

@FeignClient(contextId="ClientService", name="service-member")
public interface ClientService {

    @GetMapping(value = "findByClientId/{clientId}", produces = "application/json")
    public ClientDto findByClientId(@PathVariable("clientId") String clientId);
}
