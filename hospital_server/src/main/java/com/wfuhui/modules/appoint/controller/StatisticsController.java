package com.wfuhui.modules.appoint.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wfuhui.common.utils.DateUtils;
import com.wfuhui.common.utils.R;
import com.wfuhui.modules.hospital.service.DoctorService;
import com.wfuhui.modules.member.service.MemberService;
import com.wfuhui.modules.appoint.service.AppointService;



@RestController
@RequestMapping("statistics")
public class StatisticsController {
	@Autowired
	private AppointService appointService;
	@Autowired
	private DoctorService doctorService;
	@Autowired
	private MemberService memberService;
	
	/**
	 * 
	 */
	@RequestMapping("/query")
	public R query(@RequestParam Map<String, Object> params){
		Map<String, Object> map = new HashMap<String, Object>();
		Integer userTotal = memberService.queryTotal(map);
		Integer doctorTotal = doctorService.queryTotal(map);
		Integer appointTotal = appointService.queryTotal(map);

		Map<String, Object> result = new HashMap<String, Object>();
		result.put("userTotal", userTotal);
		result.put("appointTotal", appointTotal);
		result.put("doctorTotal", doctorTotal);
		
		// 近7天订单数
		List<Map<String, String>> appointCountList = appointService.queryAppointCount();
		List<Map<String, String>> newAppointCountList = new ArrayList<>();
		if (appointCountList == null || appointCountList.size() < 7) {
			for (int i = -7; i < 0; i++) {
				// 补齐近7天数据，值为0
				Date now = new Date();
				Map<String, String> c = hasDate(DateUtils.format(DateUtils.add(now, i)), appointCountList);
				if(c == null) {
					Map<String, String> m = new HashMap<>();
					m.put("createTime", DateUtils.format(DateUtils.add(now, i)));
					m.put("count", "0");
					newAppointCountList.add(m);
				}else {
					newAppointCountList.add(c);
				}
			}
			result.put("appointCountList", newAppointCountList);
		}else {
			result.put("appointCountList", appointCountList);
		}
		return R.ok().put("statistics", result);
	}
	
	private Map<String, String> hasDate(String date, List<Map<String, String>> list) {
		for (Map<String, String> map : list) {
			if(date.equals(map.get("createTime"))) {
				return map;
			}
		}
		return null;
	}
}
