package com.wfuhui.modules.appoint.api;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wfuhui.common.annotation.AuthIgnore;
import com.wfuhui.common.utils.R;
import com.wfuhui.modules.appoint.entity.CaseEntity;
import com.wfuhui.modules.appoint.service.CaseService;

@RestController
@RequestMapping("/api/case/")
public class ApiCaseController {
	
	@Autowired
	private CaseService caseService;
	
    @GetMapping("list")
    public R list(@RequestParam Map<String, Object> params, @RequestAttribute Long userId) {
    	params.put("memberId", userId);
    	List<CaseEntity> caseList = caseService.queryList(params);
    	return R.ok().put("caseList", caseList);
    }
}
