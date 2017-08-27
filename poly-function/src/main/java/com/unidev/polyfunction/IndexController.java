package com.unidev.polyfunction;

import org.springframework.stereotype.Controller;

/**
 * Controller for serving index description page
 */
@Controller
public class IndexController {

    public String index() {
        return "index";
    }

}
