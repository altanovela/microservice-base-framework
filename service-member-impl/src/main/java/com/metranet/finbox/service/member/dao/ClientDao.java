package com.metranet.finbox.service.member.dao;

import org.apache.ibatis.annotations.Mapper;

import com.metranet.finbox.service.member.dto.ClientDto;

@Mapper
public interface ClientDao {

    ClientDto findByClientId(String clientId);
}
