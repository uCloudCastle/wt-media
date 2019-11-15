package com.wt.media.controller;

import com.winston.ssm.web.BaseController;
import com.wt.media.domain.Camera;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by winston on 2019-11-13.
 */
@RestController
@RequestMapping("/Camera")
public class CameraController extends BaseController<Camera>{

}
