package com.metranet.finbox.service.member.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="t_mem_member")
public class Member {
    
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable=false, unique=true)
    private String username;
    
    @Column(nullable=false, unique=true)
    private String email;
    
    @Column(nullable=false, unique=true)
    private String phone;
    
    @Column(nullable=false)
    private String password;
    
    @Column(nullable=false)
    private String type;
    
    @Column(nullable=true)
    private String image; 
    
    @Column(nullable=false)
    private String status;
}
