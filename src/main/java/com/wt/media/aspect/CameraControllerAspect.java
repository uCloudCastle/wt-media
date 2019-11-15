package com.wt.media.aspect;

import com.wt.media.domain.Camera;
import com.wt.media.mapper.ServerMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 *  CameraController控制器中新增、更新方法切面.
 *  当Camera实体存在serverId但serverTypeId未赋值时，运行时根据serverId查询serverTypeId并赋值. camera表中server_type_id为冗余属性.
 */
@Aspect
@Component
public class CameraControllerAspect {

    @Pointcut("target(com.wt.media.controller.CameraController) && (execution(* add*(..)) || execution(* update*(..)))")
    private void pointCut(){}

    @Autowired
    ServerMapper serverMapper;

    @Before(value = "pointCut()")
    public void before(JoinPoint joinPoint) {
        Camera camera = (Camera) joinPoint.getArgs()[0];
        Long serverId = camera.getServerId();
        if (serverId != null && camera.getServerTypeId() ==null) {
            Short serverTypeId = serverMapper.selectByPrimaryKey(serverId).getTypeId();
            camera.setServerTypeId(serverTypeId);
        }
    }

    @AfterReturning(returning = "ret",pointcut = "pointCut()")
    public void after(JoinPoint joinPoint,Object ret) {
        //Can modify result value here

    }




}
