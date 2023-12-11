package com.wfuhui.modules.appoint.api;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wfuhui.common.annotation.AuthIgnore;
import com.wfuhui.common.utils.DateUtils;
import com.wfuhui.common.utils.R;
import com.wfuhui.modules.appoint.entity.AppointEntity;
import com.wfuhui.modules.appoint.service.AppointService;
import com.wfuhui.modules.hospital.entity.DoctorEntity;
import com.wfuhui.modules.appoint.entity.AppointEntity;

/**
 * 挂号预约
 * @author lzl
 *
 */
@RestController
@RequestMapping("/api/appoint/")
public class ApiAppintController {
	
	@Autowired
	private AppointService appointService;
	
	/**
	 * 列表
	 * @param params
	 * @param userId
	 * @return
	 */
    @GetMapping("list")
    public R list(@RequestParam Map<String, Object> params, @RequestAttribute Long userId) {
    	params.put("userId", userId);
    	List<AppointEntity> appointList = appointService.queryList(params);
    	return R.ok().put("appointList", appointList);
    }
	
    /**
     * 保存
     * @param appoint
     * @param userId
     * @return
     */
	@PostMapping("save")
    public R save(@RequestBody AppointEntity appoint, @RequestAttribute Long userId) {
		appoint.setUserId(userId);
		appoint.setCreateTime(new Date());
    	appointService.save(appoint);
    	return R.ok().put("id", appoint.getId());
    }
	
	/**
	 * 详情
	 * @param id
	 * @return
	 */
    @GetMapping("detail")
    public R detail(Integer id){
    	AppointEntity appoint = appointService.queryObject(id);
        return R.ok().put("appoint", appoint);
    }
	
    /**
     * 取消
     * @param id
     * @return
     */
	@GetMapping("cancel")
	public R cancel(Integer id) {
		AppointEntity appoint = new AppointEntity();
    	appoint.setId(id);
    	appoint.setStatus(0);
    	appointService.update(appoint);
		return R.ok();
	}
	
	/**
	 * 支付
	 * @param id
	 * @return
	 */
	@GetMapping("pay")
    public R pay(Integer id){
    	AppointEntity appoint = new AppointEntity();
    	appoint.setId(id);
    	appoint.setStatus(2);
    	appointService.update(appoint);
        return R.ok();
    }
}
