package com.foresee.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.foresee.baseCtrl.BasicsAPICtrl;
import com.foresee.init.InitUtils;
import com.foresee.service.CarouselsService;
import com.foresee.utils.JSONResult;
import com.foresee.utils.QueryParameter;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value="轮播图业务API",tags= {"轮播图业务API"})
@RequestMapping("/carousel")
public class CarouselsCtrl extends BasicsAPICtrl{
	
	@Autowired
	private CarouselsService carouselsService;
	
	@ApiOperation(value="轮播图页面轮播图显示列表查询",notes="轮播图页面轮播图显示列表查询接口")
	@RequestMapping(value="/list",method = RequestMethod.GET)
	public JSONResult selectList() {
		if(InitUtils.carouselsList.size()>0) {
			return JSONResult.ok(InitUtils.carouselsList);
		}
		
		QueryParameter qp = new QueryParameter();
		qp.addParamter("isDeleted", false);
		qp.setSortField("order_Num");
		qp.setSortOrder("ASC");
		
		return JSONResult.ok(carouselsService.selectList(qp));
	}
	
	@ApiOperation(value="根据id查询轮播图详情",notes="根据id查询轮播图详情口")
	@ApiImplicitParam(name="id", value="轮播图id",required = true,
	dataType = "String",paramType = "query")
	@RequestMapping(value="/selectById",method = RequestMethod.GET)
	public JSONResult selectById(String id) {
		return JSONResult.ok(carouselsService.selectByid(id));
	}
	
	

}
