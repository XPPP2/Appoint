package com.wfuhui.modules.hospital.api;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wfuhui.common.annotation.AuthIgnore;
import com.wfuhui.common.utils.Query;
import com.wfuhui.common.utils.R;
import com.wfuhui.modules.hospital.entity.HospitalEntity;
import com.wfuhui.modules.hospital.service.HospitalService;

@RestController
@RequestMapping("/api/hospital/")
public class ApiHospitalController {
	
	@Autowired
	private HospitalService hospitalService;
	
	@AuthIgnore
    @GetMapping("list")
    public R list(@RequestParam Map<String, Object> params) {
		Query query = new Query(params);
    	List<HospitalEntity> hospitalList = hospitalService.queryList(query);
    	int total = hospitalService.queryTotal(query);
    	return R.ok().put("hospitalList", hospitalList).put("total", total);
    }
	
	@AuthIgnore
    @GetMapping("detail")
    public R detail(Integer id) {
    	HospitalEntity hospital = hospitalService.queryObject(id);
    	return R.ok().put("hospital", hospital);
    }
	
}
