package com.foresee.ctrl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.foresee.init.InitUtils;
import com.foresee.pojo.Articles;
import com.foresee.pojo.CommunityFamily;
import com.foresee.pojo.Communitys;
import com.foresee.pojo.WechatUser;
import com.foresee.service.ArticlesService;
import com.foresee.service.CommunityFamilyService;
import com.foresee.service.CommunitysService;
import com.foresee.service.WechatUserService;
import com.foresee.utils.JSONResult;
import com.foresee.utils.QueryParameter;
import com.foresee.vo.CommunityListVo;
import com.foresee.vo.FamilyVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value="社群类别业务",tags= {"社群类别业务接口"})
@RequestMapping("/community")
public class CommunityFamilyCtrl {
	
	@Autowired
	private CommunityFamilyService communityFamilyService;
	@Autowired
	private CommunitysService communitysService;
	
	@Autowired
	private WechatUserService  wechatUserService;
	
	@Autowired
	private ArticlesService articlesService;
	
	@ApiOperation(value="查询社群类型列表和列表下的社群，全部查询出来",notes="查询全部社群类型接口")
	@RequestMapping(value="/selectList",method = RequestMethod.GET)
	public JSONResult selectList() {
		
		if(InitUtils.communityFamilyList.getList().size() > 0) {
			return JSONResult.ok(InitUtils.communityFamilyList);
		}
		CommunityListVo listvo= new CommunityListVo();
		List<CommunityFamily> list =communityFamilyService.selectListHaveCommunity();
		for(CommunityFamily c:list) {
			QueryParameter qpc = new QueryParameter();
			qpc.addParamter("isDeleted", false);
			qpc.addParamter("communitySts", "1");
			qpc.addParamter("communityType", c.getId());
			List<Communitys> community =communitysService.pageList(qpc,1,10);
			
			for(Communitys ct:community) {
				WechatUser user = new WechatUser();
				user.setCommunityid(ct.getId());
				ct.setUserNum(wechatUserService.selectCount(user));
				Articles article = new Articles();
				article.setCommunityId(ct.getId());
				article.setIsDraft("0");
				article.setIsDeleted(false);
				List<Articles> listA = articlesService.selectList(article);
				ct.setArticleNum(listA.size());
				int x=0;
				for(Articles a:listA) {
					x=x+a.getReadCount();
				}
				ct.setReadNum(x);
			}
			
			c.setList(community);
			
		}
		listvo.setList(list);
		listvo.setListAther(communitysService.selectHome());
		
		return JSONResult.ok(listvo);
	}
	
	@ApiOperation(value="查询社群类型",notes="查询社群类型接口")
	@RequestMapping(value="/selectFamilyAll",method = RequestMethod.GET)
	public JSONResult selectFamilyAll() {
		
		QueryParameter qpc = new QueryParameter();
		qpc.addParamter("isDeleted", false);
		qpc.setSortField("order_num");
		qpc.setSortOrder(" ASC");
		List<CommunityFamily> list =communityFamilyService.selectList(qpc);
		CommunityListVo vo = new CommunityListVo();
		List<String> names =new ArrayList<String>();
		List<FamilyVo> fv= new ArrayList<FamilyVo>();
		for(CommunityFamily c:list) {
			FamilyVo f=new FamilyVo();
			names.add(c.getFamilyName());
			f.setId(c.getId());
			f.setName(c.getFamilyName());
			fv.add(f);
		}
		vo.setNames(names);
		vo.setFamilys(fv);
		System.out.println(vo.getNames());
		System.out.println(vo.getFamilys());
		return JSONResult.ok(vo);
	}

}
