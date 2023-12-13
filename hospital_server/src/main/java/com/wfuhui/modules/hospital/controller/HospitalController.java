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

import com.wfuhui.modules.hospital.entity.HospitalEntity;
import com.wfuhui.modules.hospital.service.HospitalService;
import com.wfuhui.common.utils.Query;
import com.wfuhui.common.utils.R;



@RestController
@RequestMapping("hospital")
public class HospitalController {
	@Autowired
	private HospitalService hospitalService;
	
	@RequestMapping("/listAll")
	public R listAll(@RequestParam Map<String, Object> params){
		//查询列表数据
		List<HospitalEntity> hospitalList = hospitalService.queryList(params);
		
		return R.ok().put("hospitalList", hospitalList);
	}
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<HospitalEntity> hospitalList = hospitalService.queryList(query);
		int total = hospitalService.queryTotal(query);
		
		return R.ok().put("rows", hospitalList).put("total", total);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	public R info(@PathVariable("id") Integer id){
		HospitalEntity hospital = hospitalService.queryObject(id);
		
		return R.ok().put("hospital", hospital);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public R save(@RequestBody HospitalEntity hospital){
		hospital.setCreateTime(new Date());
		hospitalService.save(hospital);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public R update(@RequestBody HospitalEntity hospital){
		hospitalService.update(hospital);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public R delete(@RequestBody Integer[] ids){
		hospitalService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
