package com.metranet.finbox.service.member.dto;

import lombok.Data;

@Data
public class MemberDto {

    private Long id;
    
    private String username;
    
    private String email;
    
    private String password;
    
    private String type;
    
    private String image; 
    
    private String status;
    
    private String phone;
    
}
