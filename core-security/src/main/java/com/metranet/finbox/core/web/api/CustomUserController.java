package com.metranet.finbox.core.web.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.metranet.finbox.core.util.SecurityUtil;
import com.metranet.finbox.lib.common.JsonConverterUtil;
import com.metranet.finbox.service.member.api.MemberService;
import com.metranet.finbox.service.member.dto.MemberDto;

@RestController
@RequestMapping("/auth/member")
public class CustomUserController {
    
    Logger logger = LoggerFactory.getLogger(CustomUserController.class);
    
    @Autowired
    MemberService memberService;
    
    @PostMapping(value = "/register", consumes = "application/json")
    public ResponseEntity<String> register(@RequestBody MemberDto member) {
        
        // Encrypt password and Save Data,
        MemberDto param = new MemberDto();
        param.setUsername(member.getUsername());
        param.setEmail(member.getEmail());
        param.setPhone(member.getPhone());
        param.setStatus("ACTIVE");
        param.setType("BUYER");
        param.setImage(member.getImage());
        param.setPassword(SecurityUtil.encrypt(member.getPassword()));
        
        Long id = memberService.registerMember(param);
        if(id > 0) {
            param.setId(id);
        }
        
        // Show the response
        return new ResponseEntity<String>(
                JsonConverterUtil.convertObjectToJson(param), 
                HttpStatus.OK);
    }
}