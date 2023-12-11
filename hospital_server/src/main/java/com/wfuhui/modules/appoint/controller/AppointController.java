package com.wfuhui.modules.appoint.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wfuhui.modules.appoint.entity.AppointEntity;
import com.wfuhui.modules.hospital.service.DoctorService;
import com.wfuhui.modules.appoint.service.AppointService;
import com.wfuhui.modules.member.service.MemberService;
import com.wfuhui.common.utils.Query;
import com.wfuhui.common.utils.R;


/**
 * 挂号
 * 
 * @author lizhengle
 * @email 2803180149@qq.com
 */
@RestController
@RequestMapping("appoint")
public class AppointController {
	@Autowired
	private AppointService appointService;
	@Autowired
	private MemberService memberService;
	@Autowired
	private DoctorService doctorService;
	
	/*
	 * 根据条件查询全部
	 */
	@RequestMapping("/listAll")
	public R listAll(@RequestParam Map<String, Object> params){
		//查询列表数据
		List<AppointEntity> appointList = appointService.queryList(params);
		return R.ok().put("appointList", appointList);
	}
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params, @RequestAttribute Long userId){
		//查询列表数据
        Query query = new Query(params);

		List<AppointEntity> appointList = appointService.queryList(query);
		int total = appointService.queryTotal(query);
		
		return R.ok().put("rows", appointList).put("total", total);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	public R info(@PathVariable("id") Integer id){
		AppointEntity appoint = appointService.queryObject(id);
		
		return R.ok().put("appoint", appoint);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public R save(@RequestBody AppointEntity appoint){
		appointService.save(appoint);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public R update(@RequestBody AppointEntity appoint){
		appointService.update(appoint);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public R delete(@RequestBody Integer[] ids){
		appointService.deleteBatch(ids);
		
		return R.ok();
	}
	
	/**
	 * 完成预约
	 * @param id
	 * @return
	 */
	@RequestMapping("/complete")
	public R complete(Integer id){
		AppointEntity appoint = new AppointEntity();
		appoint.setId(id);
		appoint.setStatus(3);
		appointService.update(appoint);
		return R.ok();
	}
	
}
