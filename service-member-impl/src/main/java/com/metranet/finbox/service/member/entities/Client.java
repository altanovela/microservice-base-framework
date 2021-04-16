package com.metranet.finbox.service.member.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="oauth_client_details")
public class Client {

    @Id
    @Column(nullable = false, unique = true, name = "client_id")
    private String clientId;
    
    @Column(nullable = false, unique = false, name = "client_secret")
    private String clientSecret;
    
    @Column(nullable = true, unique = false, name = "resource_ids")
    private String resourceIds;
    
    @Column(nullable = true, unique = false, name = "scope")
    private String scope;
    
    @Column(nullable = true, unique = false, name = "authorized_grant_types")
    private String grantTypes;
    
    @Column(nullable = true, unique = false, name = "web_server_redirect_uri")
    private String redirectUrl;
    
    @Column(nullable = true, unique = false, name = "authorities")
    private String authorities;
    
    @Column(nullable = true, unique = false, name = "access_token_validity")
    private Integer accessTokenValidity;
    
    @Column(nullable = true, unique = false, name = "refresh_token_validity")
    private Integer refreshTokenValidity;
    
    @Column(nullable = true, unique = false, name = "additional_information")
    private String additionalInfo;
    
    @Column(nullable = true, unique = false, name = "autoapprove")
    private String autoApprove;
}
