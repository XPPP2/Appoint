package com.wfuhui.modules.hospital.controller;

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

import com.wfuhui.modules.hospital.entity.DeptEntity;
import com.wfuhui.modules.hospital.service.DeptService;
import com.wfuhui.common.utils.Query;
import com.wfuhui.common.utils.R;


@RestController
@RequestMapping("dept")
public class DeptController {
	@Autowired
	private DeptService deptService;
	
	
	@RequestMapping("/listAll")
	public R listAll(@RequestParam Map<String, Object> params){
		//查询列表数据
		List<DeptEntity> deptList = deptService.queryList(params);
		
		return R.ok().put("deptList", deptList);
	}
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<DeptEntity> deptList = deptService.queryList(query);
		int total = deptService.queryTotal(query);
		
		return R.ok().put("rows", deptList).put("total", total);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	public R info(@PathVariable("id") Integer id){
		DeptEntity dept = deptService.queryObject(id);
		
		return R.ok().put("dept", dept);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public R save(@RequestBody DeptEntity dept){
		dept.setCreateTime(new Date());
		deptService.save(dept);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public R update(@RequestBody DeptEntity dept){
		deptService.update(dept);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public R delete(@RequestBody Integer[] ids){
		deptService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
