package com.unidev.polyfunction.controller;

import com.unidev.platform.j2ee.common.WebUtils;
import com.unidev.polyfunction.FunctionResponse;
import com.unidev.polyfunction.HTTPFunctionRequest;
import com.unidev.polyfunction.PolyFunctionCore;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Public exposed poly function endpoint.
 */
@RestController
@RequestMapping("/api/v1")
public class APIController {

    private static Logger LOG = LoggerFactory.getLogger(APIController.class);

    @Autowired
    private HttpServletRequest servletRequest;

    @Autowired
    private WebUtils webUtils;

    @Autowired
    private PolyFunctionCore polyFunctionCore;

    @RequestMapping(value = "function", method = RequestMethod.POST)
    @ResponseBody
    public FunctionResponse handle(@RequestBody HTTPFunctionRequest httpFunctionRequest) {
        LOG.info("function request {} {}", webUtils.listAllHeaders(servletRequest), servletRequest);
        return polyFunctionCore.evaluateHTTPRequest(httpFunctionRequest, servletRequest);
    }

}
