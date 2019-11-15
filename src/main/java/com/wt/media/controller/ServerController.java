package com.wt.media.controller;

import com.winston.ssm.web.BaseController;
import com.wt.media.domain.Server;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by winston on 2019-11-13.
 */
@RestController
@RequestMapping("/Server")
public class ServerController extends BaseController<Server>{

}
