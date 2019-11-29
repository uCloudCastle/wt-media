package com.wt.media.controller;

import com.winston.db.PageInfo;
import com.winston.db.Result;
import com.winston.query.Query;
import com.winston.ssm.web.BaseController;
import com.wt.media.domain.Camera;
import com.wt.media.domain.Server;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by winston on 2019-11-13.
 */
@RestController
@RequestMapping("/Server")
public class ServerController extends BaseController<Server>{
    @Override
    public Result get(PageInfo info, Server entity, Query assit) {
        if(StringUtils.isEmpty(entity.getName()))
            entity.setName(null);

        return super.get(info, entity, assit);
    }
}
