package com.metranet.finbox.core.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.metranet.finbox.core.dto.CustomUserDetails;
import com.metranet.finbox.core.exception.UserNotFoundException;
import com.metranet.finbox.service.member.api.MemberService;
import com.metranet.finbox.service.member.dto.MemberDto;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    
    @Autowired
    MemberService memberService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        MemberDto user = memberService.findByEmailOrUsernameOrPhone(userName, userName, userName);
        if(null == user) {
            throw new UserNotFoundException("Email/Username " + userName + " tidak ditemukan");
        }
        return new CustomUserDetails(user, getAuthorities(user));
    }

    private static Collection<? extends GrantedAuthority> getAuthorities(MemberDto user) {
        return AuthorityUtils.createAuthorityList(user.getType());
    }
}
