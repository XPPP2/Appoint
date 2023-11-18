package com.wfuhui.modules.hospital.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 科室
 * 
 * @author lizhengle
 * @email 2803180149@qq.com
 */
public class DeptEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//科室名称
	private String deptName;
	//医院
	private Integer hospitalId;
	//创建时间
	private Date createTime;
	
	private HospitalEntity hospital;

	/**
	 * 设置：
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：科室名称
	 */
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	/**
	 * 获取：科室名称
	 */
	public String getDeptName() {
		return deptName;
	}
	/**
	 * 设置：医院
	 */
	public void setHospitalId(Integer hospitalId) {
		this.hospitalId = hospitalId;
	}
	/**
	 * 获取：医院
	 */
	public Integer getHospitalId() {
		return hospitalId;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}
	
	public HospitalEntity getHospital() {
		return hospital;
	}
	
	public void setHospital(HospitalEntity hospital) {
		this.hospital = hospital;
	}
	
}
