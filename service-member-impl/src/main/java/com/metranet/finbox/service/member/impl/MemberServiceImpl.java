package com.metranet.finbox.service.member.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.metranet.finbox.service.member.api.MemberService;
import com.metranet.finbox.service.member.dao.MemberDao;
import com.metranet.finbox.service.member.dto.MemberDto;

@RestController
public class MemberServiceImpl implements MemberService {

    Logger logger = LoggerFactory.getLogger(MemberServiceImpl.class);

    @Autowired
    MemberDao memberDao;
    
    @Override
    public MemberDto findByEmailOrUsername(String email, String username) {
        return memberDao.findByEmailOrUsername(email, username);
    }

    @Override
    public MemberDto findByEmailOrUsernameOrPhone(String email, String username, String phone) {
        return memberDao.findByEmailOrUsernameOrPhone(email, username, phone);
    }
    
    @Override
    public MemberDto findByUsername(String username) {
        return memberDao.findByUsername(username);
    }

    @Override
    public Long registerMember(MemberDto member) {
        if(memberDao.registerMember(member) > 0) {
            return member.getId();
        }
        return -1L;
    }
    
    @Override
    public Long updateMember(MemberDto member) {
    	if(memberDao.updateMember(member) > 0) {
            return member.getId();
        }
        return -1L;
    }
}
