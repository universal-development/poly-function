package com.unidev.service;

import com.unidev.platform.j2ee.common.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController("/public")
public class PublicService {

    private static Logger LOG = LoggerFactory.getLogger(PublicService.class);

    @Autowired
    private HttpServletRequest servletRequest;

    @Autowired
    private WebUtils webUtils;

    @PostMapping
    public String handle() {
        LOG.info("Public request {} {}", webUtils.listAllHeaders(servletRequest),  servletRequest);
        return "Public page";
    }

}
