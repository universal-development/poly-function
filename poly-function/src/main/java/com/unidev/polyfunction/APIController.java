package com.unidev.polyfunction;

import com.unidev.platform.j2ee.common.WebUtils;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Public exposed polyfunction endpoint
 */
@RestController
@RequestMapping("/api/v1")
public class APIController {

    private static Logger LOG = LoggerFactory.getLogger(APIController.class);

    @Autowired
    private HttpServletRequest servletRequest;

    @Autowired
    private WebUtils webUtils;

    @GetMapping("public")
    public String handle() {
        LOG.info("Public request {} {}", webUtils.listAllHeaders(servletRequest),  servletRequest);
        return "Public page";
    }

}
