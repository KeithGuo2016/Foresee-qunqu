package com.foresee.ctrl;

import org.springframework.web.bind.annotation.RestController;

import com.foresee.utils.CreateQrcore;
import com.foresee.utils.JSONResult;

import java.io.IOException;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.Api;

@RestController
@Api(value="获取小程序二维码",tags= {"获取小程序二维码接口"})
public class CoreCtrl {
	
	/**
     * 接收二维码
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping(value="/twoCode",method= RequestMethod.POST,produces="text/html;charset=utf-8")
    @ResponseBody
    public JSONResult twoCode() throws IOException{

        String accessToken = CreateQrcore.getToken();
        String twoCodeUrl = CreateQrcore.getminiqrQr(accessToken,"290","280");
        
        return JSONResult.ok(twoCodeUrl);
    }

}
