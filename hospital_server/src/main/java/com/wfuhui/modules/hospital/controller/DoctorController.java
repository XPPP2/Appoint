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

import com.wfuhui.modules.hospital.entity.DoctorEntity;
import com.wfuhui.modules.hospital.service.DoctorService;
import com.wfuhui.common.utils.Query;
import com.wfuhui.common.utils.R;


/**
 * 医生
 * 
 * @author lizhengle
 * @email 2803180149@qq.com
 */
@RestController
@RequestMapping("doctor")
public class DoctorController {
	@Autowired
	private DoctorService doctorService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/listAll")
	public R listAll(@RequestParam Map<String, Object> params){
		//查询列表数据
		List<DoctorEntity> doctorList = doctorService.queryList(params);
		
		return R.ok().put("doctorList", doctorList);
	}
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<DoctorEntity> doctorList = doctorService.queryList(query);
		int total = doctorService.queryTotal(query);
		
		return R.ok().put("rows", doctorList).put("total", total);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	public R info(@PathVariable("id") Integer id){
		DoctorEntity doctor = doctorService.queryObject(id);
		
		return R.ok().put("doctor", doctor);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public R save(@RequestBody DoctorEntity doctor){
		doctor.setCreateTime(new Date());
		doctorService.save(doctor);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public R update(@RequestBody DoctorEntity doctor){
		doctorService.update(doctor);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public R delete(@RequestBody Integer[] ids){
		doctorService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
