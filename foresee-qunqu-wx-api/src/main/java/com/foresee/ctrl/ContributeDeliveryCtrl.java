package com.foresee.ctrl;

import java.io.IOException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.foresee.finals.BasicsFinal;
import com.foresee.pojo.ContributeDelivery;
import com.foresee.service.ContributeDeliveryService;
import com.foresee.utils.JSONResult;
import com.foresee.utils.QueryParameter;
import com.foresee.utils.StringUtil;
import com.foresee.utils.UploadUtil;
import com.foresee.vo.ContributeDeliveryVo;
import com.github.pagehelper.PageInfo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 投稿
 * @author DELL
 *
 */
@RestController
@Api(value="投稿业务API",tags= {"投稿业务API"})
@RequestMapping("/delivery")
public class ContributeDeliveryCtrl {
	
	@Autowired
	private ContributeDeliveryService contributeDeliveryService;
	
	@ApiOperation(value="用户投稿",notes="用户投稿接口")
	@RequestMapping(value="/insert",method = RequestMethod.POST)
	public JSONResult insert(@RequestBody ContributeDelivery delivery) {
		if(StringUtil.isBlank(delivery.getUserid())) {
			return JSONResult.error("请先授权登录");
		}
		if(StringUtil.isBlank(delivery.getContributesId())) {
			return JSONResult.errorMsg("请选择征稿需求");
		}
		delivery.setIsPay(0);
		delivery.setReadNum(0);
		delivery.setIsSelect(0);
		delivery.setCreatedDate(new Date());
		delivery.setCreatedBy(delivery.getUserid()+"");
		contributeDeliveryService.insert(delivery);
		
		return JSONResult.ok();
	}
	
	@ApiOperation(value="根据约稿需求id查询投稿列表",notes="根据约稿需求id查询投稿列表接口")
	@ApiImplicitParams({
		@ApiImplicitParam(name="contributeId", value="约稿需求id",required = true,
				dataType = "String",paramType = "query"),
		@ApiImplicitParam(name="page", value="当前第几页",required = true,
				dataType = "int",paramType = "query"),
		@ApiImplicitParam(name="pageSize", value="每页显示条数",required = true,
				dataType = "int",paramType = "query")
	})
	@RequestMapping(value="/selectByContributeId",method = RequestMethod.GET)
	public JSONResult selectByContributeId(String contributeId,int page,int pageSize) {
		
		QueryParameter qp = new QueryParameter();
		qp.addParamter("contributesId", contributeId);
		qp.addParamter("isDeleted", false);
		qp.setSortField("created_date");
		qp.setSortOrder(" desc");
		PageInfo<ContributeDelivery> info = contributeDeliveryService.pageInfoList(qp, page, pageSize);
		return JSONResult.ok(info);
	}
	
	@ApiOperation(value="查询投稿总数",notes="查询投稿总数接口")
	@ApiImplicitParam(name="contributeId", value="约稿需求id",required = true,
	dataType = "int",paramType = "query")
	@RequestMapping(value="/selectCount",method = RequestMethod.GET)
	public JSONResult selectCount(int contributeId) {
		ContributeDelivery delivery = new ContributeDelivery();
		delivery.setContributesId(contributeId);
		
		return JSONResult.ok(contributeDeliveryService.selectCount(delivery));
	}
	
	@ApiOperation(value="上传封面",notes="上传封面接口")
	@ApiImplicitParam(name="userId", value="用户ID",required = true,
	dataType = "String",paramType = "form")
	@RequestMapping(value="/uploadIcon",method = RequestMethod.POST,headers = "content-type=multipart/form-data")
	public JSONResult uploadIcon(String userId,@ApiParam(value="封面图",required = true) MultipartFile file) throws IOException {
		if(StringUtil.isBlank(userId)) {
			return JSONResult.error("请先授权登录");
		}
		String path = BasicsFinal.WX_CONTRIBUTE_ICON;
		String uploadPath =UploadUtil.uploadImage(path, file, userId);
		return JSONResult.ok(uploadPath);
	}
	
	@ApiOperation(value="根据id查询明细",notes="根据id查询明细接口")
	@ApiImplicitParam(name="id", value="投稿id",required = true,
	dataType = "int",paramType = "query")
	@RequestMapping(value="/selectByid",method = RequestMethod.GET)
	public JSONResult selectByid(int id) {
		ContributeDeliveryVo vo =contributeDeliveryService.selectVoById(id);
		ContributeDelivery delivery = new ContributeDelivery();
		delivery.setId(id);
		delivery.setReadNum(vo.getReadNum()+1);
		
		contributeDeliveryService.update(delivery);
		return JSONResult.ok(vo);
	}
	
	@ApiOperation(value="阅读投稿稿件",notes="阅读投稿稿件接口")
	@ApiImplicitParam(name="id", value="投稿id",required = true,
	dataType = "int",paramType = "query")
	@RequestMapping(value="/reading",method = RequestMethod.POST)
	public JSONResult reading(int id,int readNum) {
		ContributeDelivery delivery = new ContributeDelivery();
		delivery.setId(id);
		delivery.setReadNum(readNum);
		contributeDeliveryService.update(delivery);
		return JSONResult.ok();
	}
	
}
