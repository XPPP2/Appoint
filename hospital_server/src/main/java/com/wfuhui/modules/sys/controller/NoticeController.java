package com.wfuhui.modules.sys.controller;

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

import com.wfuhui.modules.sys.entity.NoticeEntity;
import com.wfuhui.modules.sys.service.NoticeService;
import com.wfuhui.common.utils.Query;
import com.wfuhui.common.utils.R;




/**
 * 公告
 * 
 * @author lizhengle
 * @email 2803180149@qq.com
 */
@RestController
@RequestMapping("notice")
public class NoticeController {
	@Autowired
	private NoticeService noticeService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<NoticeEntity> noticeList = noticeService.queryList(query);
		int total = noticeService.queryTotal(query);
		
		return R.ok().put("rows", noticeList).put("total", total);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	public R info(@PathVariable("id") Integer id){
		NoticeEntity notice = noticeService.queryObject(id);
		
		return R.ok().put("notice", notice);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public R save(@RequestBody NoticeEntity notice){
		notice.setCreateTime(new Date());
		noticeService.save(notice);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public R update(@RequestBody NoticeEntity notice){
		noticeService.update(notice);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public R delete(@RequestBody Integer[] ids){
		noticeService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
