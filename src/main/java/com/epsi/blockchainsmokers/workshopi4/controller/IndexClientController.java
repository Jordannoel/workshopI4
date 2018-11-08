package com.epsi.blockchainsmokers.workshopi4.controller;

import com.epsi.blockchainsmokers.workshopi4.config.ApplicationUrl;
import com.epsi.blockchainsmokers.workshopi4.config.PageMapping;
import com.epsi.blockchainsmokers.workshopi4.service.ReclamationService;
import com.epsi.blockchainsmokers.workshopi4.service.SouscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexClientController {

    private static final String REDIRECT = "redirect:";

    @Autowired
    public IndexClientController() {
    }

    @RequestMapping(value = ApplicationUrl.INDEX_CLIENT, method = RequestMethod.GET)
    public String presentation() {
        return PageMapping.INDEX_CLIENT;
    }
}
