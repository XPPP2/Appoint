package com.wfuhui.modules.hospital.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 医生
 * 
 * @author lizhengle
 * @email 2803180149@qq.com
 */
public class DoctorEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	//
	private Integer id;
	// 医生名称
	private String realName;
	// 电话
	private String mobile;
	// 医院
	private Integer hospitalId;
	// 创建时间
	private Date createTime;

	private HospitalEntity hospital;

	private String specialty;

	private String remark;
	
	private String picUrl;
	
	private Integer deptId;
	
	private DeptEntity dept;

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
	 * 设置：电话
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * 获取：电话
	 */
	public String getMobile() {
		return mobile;
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

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public HospitalEntity getHospital() {
		return hospital;
	}

	public void setHospital(HospitalEntity hospital) {
		this.hospital = hospital;
	}

	public String getSpecialty() {
		return specialty;
	}

	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public Integer getDeptId() {
		return deptId;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}

	public DeptEntity getDept() {
		return dept;
	}

	public void setDept(DeptEntity dept) {
		this.dept = dept;
	}
	
}
