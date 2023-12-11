package com.wfuhui.modules.sys.api;

import com.wfuhui.common.annotation.AuthIgnore;
import com.wfuhui.common.utils.R;
import com.wfuhui.modules.sys.entity.NoticeEntity;
import com.wfuhui.modules.sys.service.NoticeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 公告
 * 
 * @author lzl
 * @email 2803180149@qq.com
 */
@RestController
@RequestMapping("/api/notice")
public class ApiNoticeController {
	
	@Autowired
	private NoticeService noticeService;

	@AuthIgnore
	@GetMapping("list")
	public R list(@RequestParam Map<String, Object> params) {
		List<NoticeEntity> noticeList = noticeService.queryList(params);
		return R.ok().put("noticeList", noticeList);
	}
	
	@AuthIgnore
	@GetMapping("detail")
    public R detail(Integer id){
		 NoticeEntity notice = noticeService.queryObject(id);
		return R.ok().put("notice", notice);
    }
	
}
