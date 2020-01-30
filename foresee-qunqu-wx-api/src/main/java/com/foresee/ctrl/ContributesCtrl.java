package com.foresee.ctrl;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.foresee.finals.BasicsFinal;
import com.foresee.pojo.Contributes;
import com.foresee.pojo.UserApply;
import com.foresee.service.ContributeDeliveryService;
import com.foresee.service.ContributesService;
import com.foresee.service.UserApplyService;
import com.foresee.utils.DateUtils;
import com.foresee.utils.JSONResult;
import com.foresee.utils.QueryParameter;
import com.foresee.utils.StringUtil;
import com.foresee.utils.UploadUtil;
import com.foresee.vo.ContributesVo;
import com.github.pagehelper.PageInfo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 征稿
 * @author DELL
 *
 */
@RestController
@Api(value="征稿业务API",tags= {"征稿业务API"})
@RequestMapping("/contribute")
public class ContributesCtrl {

	@Autowired
	private ContributesService contributesService;
	@Autowired
	private ContributeDeliveryService contributeDeliveryService;
	@Autowired
	private UserApplyService userApplyService;
	
	@ApiOperation(value="发布征稿需求",notes="发布征稿需求接口")
	@RequestMapping(value="/insert",method = RequestMethod.POST)
	public JSONResult insert(@RequestBody Contributes contributes) {
		if(StringUtil.isBlank(contributes.getCommunityId()) || StringUtil.isBlank(contributes.getCreateUserid())) {
			return JSONResult.error("请先授权登录");
		}
		Date d =new Date();
		contributes.setCreatedDate(d);
		contributes.setCreatedBy(contributes.getCreateUserid()+"");
		contributesService.insert(contributes);
		Contributes cb = new Contributes();
		cb.setContributeTitle(contributes.getContributeTitle());
		cb.setContributeIcon(contributes.getContributeIcon());
		List<Contributes> list=contributesService.selectList(cb);
		if(list.size()>0) {
			contributes =list.get(0);
		}
		UserApply userApply=new UserApply();
		
		userApply.setCreatedDate(d);
		userApply.setCreatedBy(contributes.getCreateUserid()+"");
		
		userApply.setApplyType("5");
		userApply.setApplyDesc(contributes.getContributeTitle());
		userApply.setApplySts("0");
		userApply.setApplyTargetId(contributes.getId());
		userApply.setIsDeleted(false);
		userApply.setUserid(contributes.getCreateUserid());
		userApplyService.insert(userApply);
		
		return JSONResult.ok();
	}
	
	@ApiOperation(value="上传封面",notes="上传封面接口")
	@ApiImplicitParams({
		@ApiImplicitParam(name="userId", value="用户ID",required = true,
				dataType = "String",paramType = "form"),
		@ApiImplicitParam(name="imageWidth", value="图片宽度",required = false,
		dataType = "int",paramType = "form"),
		@ApiImplicitParam(name="imageHeight", value="图片高度",required = false,
		dataType = "int",paramType = "form"),
	})
	@RequestMapping(value="/uploadIcon",method = RequestMethod.POST,headers = "content-type=multipart/form-data")
	public JSONResult uploadIcon(String userId,int imageWidth, int imageHeight,@ApiParam(value="封面图",required = true) MultipartFile file) throws IOException {
		if(StringUtil.isBlank(userId)) {
			return JSONResult.error("请先授权登录");
		}
		
		
		BufferedImage image = ImageIO.read(file.getInputStream());
		if (image == null) {
			return JSONResult.errorMsg("请选择图片上传");
			//System.out.println(image.getWidth());//获取图片宽度，单位px
			//System.out.println(image.getHeight());//获取图片高度，单位px
		}else {
			
			if(image.getWidth()!=imageWidth || image.getHeight()!=imageHeight)  {
				return JSONResult.errorMsg("请选择尺寸是"+imageWidth+":"+imageHeight+"的图片");
			}
		}
		String path = BasicsFinal.WX_CONTRIBUTE_ICON;
		String uploadPath =UploadUtil.uploadImage(path, file, userId);
		return JSONResult.ok(uploadPath);
	}
	
	
	@ApiOperation(value="查询全网征稿列表（包含自己社群的和全网的）",notes="查询全网征稿列表接口")
	
	@ApiImplicitParams({
		@ApiImplicitParam(name="communityId", value="登录用户所属社群ID",required = false,
				dataType = "String",paramType = "query"),
		@ApiImplicitParam(name="page", value="当前第几页",required = true,
				dataType = "int",paramType = "query"),
		@ApiImplicitParam(name="pageSize", value="每页显示条数",required = true,
				dataType = "int",paramType = "query")
	})
	@RequestMapping(value="/selectAllList",method = RequestMethod.GET)
	public JSONResult selectAllList(String communityId,int page,int pageSize) {
		if(StringUtil.isBlank(communityId)) {
			communityId = null;
		}
		PageInfo<ContributesVo> info =contributesService.selectAllListHome(communityId, page, pageSize);
		
		return JSONResult.ok(info);
	}
	
	@ApiOperation(value="查询群内征稿列表",notes="查询群内征稿列表接口")
	@ApiImplicitParams({
		@ApiImplicitParam(name="communityid", value="登录用户所属社群ID",required = true,
				dataType = "int",paramType = "query"),
		@ApiImplicitParam(name="page", value="当前第几页",required = true,
				dataType = "int",paramType = "query"),
		@ApiImplicitParam(name="pageSize", value="每页显示条数",required = true,
				dataType = "int",paramType = "query")
	})
	
	@RequestMapping(value="/selectInCommunityList",method = RequestMethod.GET)
	public JSONResult selectInCommunityList(int communityid,int page,int pageSize) {
		PageInfo<ContributesVo> info = contributesService.selectListCommunityId(communityid, page, pageSize);
		return JSONResult.ok(info);
	}
	
	@ApiOperation(value="根据id查询征稿详情",notes="根据id查询征稿详情接口")
	@ApiImplicitParams({
		@ApiImplicitParam(name="id", value="征稿表id",required = true,
				dataType = "int",paramType = "query"),
		@ApiImplicitParam(name="pageSize", value="显示条数",required = true,
		dataType = "int",paramType = "query")
	})
	
	@RequestMapping(value="/selectById",method = RequestMethod.GET)
	public JSONResult selectById(int id,int pageSize) {
		if(StringUtil.isBlank(id)) {
			
			return JSONResult.errorMsg("找不到征稿详情");
		}
		ContributesVo vo =contributesService.selectVoById(id);
		vo.setInfo(contributeDeliveryService.selectByContributesId(id, 1, pageSize));
		return JSONResult.ok(vo);
	}
	
	@ApiOperation(value="根据id查询投稿列表",notes="根据id查询投稿列表接口")
	@ApiImplicitParams({
		@ApiImplicitParam(name="id", value="征稿表id",required = true,
				dataType = "int",paramType = "query"),
		@ApiImplicitParam(name="page", value="页码",required = true,
		dataType = "int",paramType = "query"),
		@ApiImplicitParam(name="pageSize", value="显示条数",required = true,
		dataType = "int",paramType = "query")
	})
	
	@RequestMapping(value="/selectDeliveryBycontributeId",method = RequestMethod.GET)
	public JSONResult selectDeliveryBycontributeId(int id,int page,int pageSize) {
		if(StringUtil.isBlank(id)) {
			
			return JSONResult.errorMsg("找不到征稿详情");
		}
		
		return JSONResult.ok(contributeDeliveryService.selectByContributesId(id, page, pageSize));
	}
	
}
