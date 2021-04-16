package com.metranet.finbox.service.member.dto;

import lombok.Data;

@Data
public class ClientDto {

    private String clientId;
    
    private String resourceIds;
    
    private String clientSecret;
    
    private String scope;
    
    private String grantTypes;
    
    private String redirectUrl;
    
    private String authorities;
    
    private Integer accessTokenValidity;
    
    private Integer refreshTokenValidity;
    
    private String additionalInfo;
    
    private String autoApprove;
    
}
