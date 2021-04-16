package com.metranet.finbox.lib;

import java.util.Base64;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

public class BaseController {

    Logger logger = LoggerFactory.getLogger(BaseController.class);
    
    /**
     * Get Decoded JWT with Base64 Encoded
     * @param request
     * @return
     */
    private String getContent(HttpServletRequest request) {
        String decodedJwt = request.getHeader("X-jwt-sub");
        if(StringUtils.isBlank(decodedJwt)) {
            return "";
        }
        try {
            return new String(Base64.getDecoder().decode(decodedJwt));
        } catch (Exception e) {
            logger.error("Error", e);
        }
        return "";
    }
    
    /**
     * Get Member Detail
     * @param request
     * @return
     */
    public MemberDetail getMemberDetail(HttpServletRequest request){
        String content = getContent(request);
        if(StringUtils.isBlank(content)) {
            return null;
        }
        
        MemberDetail memberDetail = null;
        try {
            memberDetail = new ObjectMapper().readValue(content, MemberDetail.class);
        }catch (Exception e) {
            logger.error("Error", e);
        }
        return memberDetail;
    }
}
