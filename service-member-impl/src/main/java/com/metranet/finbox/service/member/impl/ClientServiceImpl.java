package com.metranet.finbox.service.member.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.metranet.finbox.service.member.api.ClientService;
import com.metranet.finbox.service.member.dao.ClientDao;
import com.metranet.finbox.service.member.dto.ClientDto;

@RestController
public class ClientServiceImpl implements ClientService {

    Logger logger = LoggerFactory.getLogger(ClientServiceImpl.class);
    
    @Autowired
    ClientDao clientDao;
    
    @Override
    public ClientDto findByClientId(String clientId) {
        return clientDao.findByClientId(clientId);
    }

}
