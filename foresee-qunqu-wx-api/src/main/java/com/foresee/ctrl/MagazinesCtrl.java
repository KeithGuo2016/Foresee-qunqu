package com.foresee.ctrl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.foresee.pojo.MagazineFollow;
import com.foresee.pojo.Magazines;
import com.foresee.service.ArticlesService;
import com.foresee.service.MagazineArticlesService;
import com.foresee.service.MagazineFollowService;
import com.foresee.service.MagazinesService;
import com.foresee.utils.JSONResult;
import com.foresee.utils.QueryCondition;
import com.foresee.utils.QueryParameter;
import com.foresee.utils.StringUtil;
import com.foresee.vo.MagazinesVo;
import com.github.pagehelper.PageInfo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 社刊
 * @author DELL
 *
 */
@RestController
@Api(value="社刊业务API",tags= {"社刊业务API"})
@RequestMapping("/magazine")
public class MagazinesCtrl {
	
	@Autowired
	private  MagazinesService magazinesService;
	
	@Autowired
	private  MagazineFollowService magazineFollowService;
	@Autowired
	private  MagazineArticlesService magazineArticlesService;
	@Autowired
	private ArticlesService articlesService;
	
	
	@ApiOperation(value="社长创建社刊",notes="社长创建社刊接口")
	@RequestMapping(value="/insert",method = RequestMethod.POST)
	public JSONResult insert(@RequestBody Magazines magazine) {
		magazine.setCreatedDate(new Date());
		magazinesService.insert(magazine);
		return JSONResult.ok();
	}
	
	
	//查询群内社刊列表
	@ApiOperation(value="查询群内社刊列表",notes="查询群内社刊列表接口")
	@ApiImplicitParams({
		@ApiImplicitParam(name="communityid", value="社群id",required = true,
				dataType = "int",paramType = "query"),
		@ApiImplicitParam(name="page", value="当前第几页",required = true,
				dataType = "int",paramType = "query"),
		@ApiImplicitParam(name="pageSize", value="每页显示条数",required = true,
				dataType = "int",paramType = "query")
	})
	@RequestMapping(value="/selectByCommunityId",method = RequestMethod.GET)
	public JSONResult selectByCommunityId(int communityid, int page,int pageSize) {
	
		return JSONResult.ok(magazinesService.selectByCommunitysId(communityid, page, pageSize));
	}
	
	
	//查询社刊列表
	
	@ApiOperation(value="查询社刊列表",notes="查询社刊列表接口")
	@ApiImplicitParams({
		@ApiImplicitParam(name="page", value="当前第几页",required = true,
				dataType = "int",paramType = "query"),
		@ApiImplicitParam(name="pageSize", value="每页显示条数",required = true,
				dataType = "int",paramType = "query")
	})
	@RequestMapping(value="/selectList",method = RequestMethod.GET)
	public JSONResult selectList(int page,int pageSize) {
		
		return JSONResult.ok(magazinesService.selectList(page, pageSize));
	}
	
	
	//查询社刊文章
	@ApiOperation(value="查询社刊文章",notes="查询社刊文章接口")
	@ApiImplicitParam(name="id", value="社刊id",required = true,
	dataType = "int",paramType = "query")
	@RequestMapping(value="/selectArticles",method = RequestMethod.GET)
	public JSONResult selectArticles(int id) {
		
		MagazinesVo mag=magazinesService.selectById(id);
		Magazines m= new Magazines();
		m.setReadCount(mag.getReadCount()+1);
		m.setId(mag.getId());
		magazinesService.update(m);
		
		mag.setList(magazineArticlesService.selectListByMagazinesId(id));
		
		return JSONResult.ok(mag);
	}
	
	
	//收藏社刊
	@ApiOperation(value="收藏社刊",notes="收藏社刊接口")
	@RequestMapping(value="/insertFollow",method = RequestMethod.POST)
	public JSONResult insertFollow(@RequestBody MagazineFollow follow) {
		if(StringUtil.isBlank(follow.getUserid())) {
			return JSONResult.error("请先授权登录");
		}
		MagazineFollow mf = new MagazineFollow();
		mf.setUserid(follow.getUserid());
		mf.setMagazineId(follow.getMagazineId());
		MagazineFollow magazineFollow =magazineFollowService.selectOne(mf);
		if(magazineFollow == null) {
			follow.setCreatedDate(new Date());
			magazineFollowService.insert(follow);
		}else {
			magazineFollow.setIsDeleted(false);
			magazineFollowService.update(magazineFollow);
		}
		
		return JSONResult.ok();
	}
	
	@ApiOperation(value="取消收藏社刊",notes="取消收藏社刊接口")
	@RequestMapping(value="/notFollow",method = RequestMethod.POST)
	public JSONResult notFollow(@RequestBody MagazineFollow follow) {
		if(StringUtil.isBlank(follow.getUserid())) {
			return JSONResult.error("请先授权登录");
		}
		follow.setIsDeleted(true);
		magazineFollowService.update(follow);
		return JSONResult.ok();
	}
	
	
	
	

}
