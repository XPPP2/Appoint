package com.wfuhui.modules.appoint.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wfuhui.modules.appoint.entity.CaseEntity;
import com.wfuhui.modules.appoint.service.CaseService;
import com.wfuhui.common.utils.Query;
import com.wfuhui.common.utils.R;


/**
 * 病例
 * 
 * @author lizhengle
 * @email 2803180149@qq.com
 * @date 2020-12-10 22:11:38
 */
@RestController
@RequestMapping("case")
public class CaseController{
	@Autowired
	private CaseService caseService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<CaseEntity> caseList = caseService.queryList(query);
		int total = caseService.queryTotal(query);
		
		return R.ok().put("rows", caseList).put("total", total);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	public R info(@PathVariable("id") Integer id){
		CaseEntity cas = caseService.queryObject(id);
		
		return R.ok().put("cas", cas);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public R save(@RequestBody CaseEntity cas){
		cas.setCreateTime(new Date());
		caseService.save(cas);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public R update(@RequestBody CaseEntity cas){
		caseService.update(cas);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public R delete(@RequestBody Integer[] ids){
		caseService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
