package com.wfuhui.modules.hospital.api;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wfuhui.common.annotation.AuthIgnore;
import com.wfuhui.common.utils.R;
import com.wfuhui.modules.hospital.entity.DeptEntity;
import com.wfuhui.modules.hospital.service.DeptService;

@RestController
@RequestMapping("/api/dept/")
public class ApiDeptController {
	
	@Autowired
	private DeptService deptService;
	
	@AuthIgnore
    @GetMapping("list")
    public R list(@RequestParam Map<String, Object> params) {
    	List<DeptEntity> deptList = deptService.queryList(params);
    	return R.ok().put("deptList", deptList);
    }
	
	@AuthIgnore
    @GetMapping("detail")
    public R detail(Integer id){
		DeptEntity dept = deptService.queryObject(id);
        return R.ok().put("dept", dept);
    }
}
