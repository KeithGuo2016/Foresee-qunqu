package com.foresee.ctrl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.foresee.utils.CreateQrcore;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.foresee.pojo.ArticlesComment;
import com.foresee.service.ArticlesCommentService;
import com.foresee.utils.JSONResult;
import com.foresee.utils.StringUtil;
import com.foresee.vo.ArticlesCommentVo;
import com.github.pagehelper.PageInfo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 文章评论
 * @author DELL
 *
 */
@RestController
@Api(value="文章评论业务API",tags= {"文章评论业务API"})
@RequestMapping("/comment")
public class ArticlesCommentCtrl {
	@Autowired
	private ArticlesCommentService articlesCommentService;
	
	@ApiOperation(value="新建用户评论",notes="新建用户评论接口")
	@RequestMapping(value="/insert",method = RequestMethod.POST)
	public JSONResult insert(@RequestBody ArticlesComment comment) {
		if(StringUtil.isBlank(comment.getUserid())) {
			return JSONResult.error("请先授权登录");
		}
		if(StringUtil.isBlank(comment.getArticlesId())) {
			return JSONResult.errorMsg("请选择需要评论的文章（文章id不能为空）");
		}
		if(StringUtil.isBlank(comment.getContent())) {
			return JSONResult.errorMsg("请输入评论内容");
		}
		String accessToken = CreateQrcore.getToken();
		try {
			CloseableHttpClient httpclient = HttpClients.createDefault();
			CloseableHttpResponse response = null;
			HttpPost request = new HttpPost("https://api.weixin.qq.com/wxa/msg_sec_check?access_token=" + accessToken);
			request.addHeader("Content-Type", "application/json");
			Map<String, String> map = new HashMap<>();
			map.put("content", comment.getContent());
			String body = JSONObject.toJSONString(map);
			request.setEntity(new StringEntity(body, ContentType.create("text/json", "UTF-8")));
			response = httpclient.execute(request);
			HttpEntity httpEntity = response.getEntity();
			String result = EntityUtils.toString(httpEntity, "UTF-8");// 转成string
			JSONObject jso = JSONObject.parseObject(result);

			if(!getResult(jso)){
				return JSONResult.errorMsg("评论内容不合规");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		comment.setCreatedDate(new Date());
		comment.setCreatedBy(comment.getUserid().toString());
		articlesCommentService.insert(comment);
		List<ArticlesCommentVo> list=articlesCommentService.selectCommentByDate(comment.getUserid(), comment.getArticlesId(), comment.getContent());
		if(list.size()>0) {
			return JSONResult.ok(list.get(0));
		}
		return JSONResult.ok();
	}
	
	@ApiOperation(value="根据文章id查询用户评论",notes="根据文章id查询用户评论接口")
	@ApiImplicitParams({
		@ApiImplicitParam(name="articlesId", value="文章id",required = true,
				dataType = "int",paramType = "query"),
		@ApiImplicitParam(name="page", value="当前第几页",required = true,
		dataType = "int",paramType = "query"),
		@ApiImplicitParam(name="pageSize", value="每页显示条数",required = true,
		dataType = "int",paramType = "query")
	})
	@RequestMapping(value="/selectComment",method = RequestMethod.GET)
	public JSONResult selectComment(int articlesId,int page, int pageSize) {
		if(StringUtil.isBlank(articlesId)) {
			return JSONResult.errorMsg("文章不存在");
		}
		
		PageInfo<ArticlesCommentVo> pageInfo =articlesCommentService.selectCommentList(articlesId, page, pageSize);
		List<ArticlesCommentVo> list = pageInfo.getList();
		for(ArticlesCommentVo comment:list) {
			PageInfo<ArticlesCommentVo> toList= articlesCommentService.selectToCommentList(comment.getId());
			comment.setList(toList.getList());
			if(toList != null){
				comment.setToCommentNum((int)toList.getTotal());
			}else {
				comment.setToCommentNum(0);
			}
			
		}
		return JSONResult.ok(pageInfo);
	} 
	
	
	@ApiOperation(value="评论详情查询",notes="评论详情查询接口")
	@ApiImplicitParams({
		@ApiImplicitParam(name="id", value="id:文章id或者文章评论id",required = true,
				dataType = "int",paramType = "query"),
		@ApiImplicitParam(name="type", value="id参数类型：comment表示评论，article表示文章",required = true,
		dataType = "Sting",paramType = "query"),
		@ApiImplicitParam(name="page", value="当前第几页",required = true,
		dataType = "int",paramType = "query"),
		@ApiImplicitParam(name="pageSize", value="每页显示条数",required = true,
		dataType = "int",paramType = "query")
	})
	@RequestMapping(value="/selectCommentByType",method = RequestMethod.GET)
	public JSONResult selectCommentByType(int id,String type,int page, int pageSize) {
		if(StringUtil.isBlank(id)) {
			return JSONResult.errorMsg("参数不存在");
		}
		//根据评论id，查询
		if(type.equals("comment")) {
			ArticlesCommentVo vo =articlesCommentService.selectCommentByid(id);
			List<ArticlesCommentVo> toList= articlesCommentService.selectToComment(vo.getId());
			vo.setList(toList);
			return JSONResult.ok(vo);
		}
		//根据文章id查询
		if(type.equals("article")) {
			PageInfo<ArticlesCommentVo> pageInfo =articlesCommentService.selectCommentList(id, page, pageSize);
			List<ArticlesCommentVo> list = pageInfo.getList();
			for(ArticlesCommentVo comment:list) {
				PageInfo<ArticlesCommentVo> toList= articlesCommentService.selectToCommentList(comment.getId());
				comment.setList(toList.getList());
				if(toList != null){
					comment.setToCommentNum((int)toList.getTotal());
				}else {
					comment.setToCommentNum(0);
				}
				
			}
			return JSONResult.ok(pageInfo);
		}
		
		return JSONResult.ok();
	} 
	
	@ApiOperation(value="根据评论id查询回复信息",notes="根据评论id查询回复信息接口")
	
	@ApiImplicitParam(name="id", value="评论id",required = true,
			dataType = "int",paramType = "query")
	@RequestMapping(value="/selectToComment",method = RequestMethod.GET)
	public JSONResult selectToComment(int id) {
		if(StringUtil.isBlank(id)) {
			return JSONResult.errorMsg("参数不存在");
		}
		List<ArticlesCommentVo> toList= articlesCommentService.selectToComment(id);
		return JSONResult.ok(toList);
		
	} 
	@ApiOperation(value="根据用户id查询评论信息",notes="根据用户id查询评论信息接口")
	
	@ApiImplicitParams({
		@ApiImplicitParam(name="userid", value="用户id",required = true,
				dataType = "int",paramType = "query"),
		@ApiImplicitParam(name="page", value="当前第几页",required = true,
		dataType = "int",paramType = "query"),
		@ApiImplicitParam(name="pageSize", value="每页显示条数",required = true,
		dataType = "int",paramType = "query")
	})
	@RequestMapping(value="/selectMyComment",method = RequestMethod.GET)
	public JSONResult selectMyComment(int userid,int page, int pageSize) {
		
		PageInfo<ArticlesCommentVo> pageInfo =articlesCommentService.selectMyComment(userid, page, pageSize);
		//List<ArticlesCommentVo> list = pageInfo.getList();
		/*for(ArticlesCommentVo comment:list) {
			PageInfo<ArticlesCommentVo> toList= articlesCommentService.selectToCommentList(comment.getId());
			comment.setList(toList.getList());
			if(toList != null){
				comment.setToCommentNum((int)toList.getTotal());
			}else {
				comment.setToCommentNum(0);
			}
			
		}*/
		return JSONResult.ok(pageInfo);
	}
	private static Boolean  getResult(JSONObject jso){
		Object errcode = jso.get("errcode");
		int errCode = (int) errcode;
		if (errCode == 0) {
			return true;
		} else if (errCode == 87014) {
			return false;
		}
		return true;
	}
}
