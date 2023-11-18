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
import com.wfuhui.modules.hospital.entity.DoctorEntity;
import com.wfuhui.modules.hospital.service.DoctorService;

@RestController
@RequestMapping("/api/doctor/")
public class ApiDoctorController {
	
	@Autowired
	private DoctorService doctorService;
	
    @AuthIgnore
    @GetMapping("list")
    public R list(@RequestParam Map<String, Object> params){
    	Query query = new Query(params);
    	List<DoctorEntity> doctorList = doctorService.queryList(query);
    	int total = doctorService.queryTotal(query);
        return R.ok().put("doctorList", doctorList).put("total", total);
    }
    
    
    @AuthIgnore
    @GetMapping("detail")
    public R detail(Integer id){
    	DoctorEntity doctor = doctorService.queryObject(id);
        return R.ok().put("doctor", doctor);
    }
}
