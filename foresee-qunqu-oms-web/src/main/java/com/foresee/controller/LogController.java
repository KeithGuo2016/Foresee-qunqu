package com.foresee.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foresee.model.Log;
import com.foresee.service.LogService;
import com.foresee.util.ListCopyUtil;
import com.foresee.utils.ResultUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 日志管理
 * @author wrh
 *
 */
@RestController

@RequestMapping("/log")
@Validated
public class LogController {
	@Resource LogService LogService;
	/**
	 * 查询日志列表
	 * @param request
	 * @param log
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/selectlist")
	public Object selectAccountsList(HttpServletRequest request, Log log) {
		PageHelper.startPage(log.getPage(), log.getLimit());
		List<Log> list = LogService.selectList(log);
		List<com.foresee.vo.Log> listvo = ListCopyUtil.CopyList(list, new com.foresee.vo.Log());
		PageInfo<Log> pageInfo = new PageInfo<>(list);
		return ResultUtils.successlist(listvo,pageInfo.getTotal());
	}
}
