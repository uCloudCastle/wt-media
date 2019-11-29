package com.wt.media.controller;

import com.winston.db.IMapper;
import com.winston.db.PageInfo;
import com.winston.db.Result;
import com.winston.db.ServiceInvoker;
import com.winston.query.Query;
import com.winston.ssm.web.BaseController;
import com.wt.media.domain.Camera;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by winston on 2019-11-13.
 */
@RestController
@RequestMapping("/Camera")
public class CameraController extends BaseController<Camera>{
    @Autowired
    @Override
    public void setMapper(IMapper<Camera> mapper) {
//        super.setMapper(mapper);
        this.mapper=mapper;
        this.invoker=new ServiceInvoker<Camera>(){

        };
        this.invoker.setMapper(mapper);
    }

    @Override
    public Result get(PageInfo info, Camera entity, Query assit) {
        if(StringUtils.isEmpty(entity.getName()))
            entity.setName(null);

        return super.get(info, entity, assit);
    }
}
