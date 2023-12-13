package com.wfuhui.modules.appoint.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wfuhui.modules.hospital.entity.DeptEntity;
import com.wfuhui.modules.hospital.entity.DoctorEntity;
import com.wfuhui.modules.hospital.entity.HospitalEntity;
import com.wfuhui.modules.member.entity.MemberEntity;


public class AppointEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	//
	private Integer id;
	// 用户
	private Long userId;
	// 医院
	private Integer hospitalId;
	// 科室
	private Integer deptId;
	// 备注
	private String remark;
	// 创建时间
	private Date createTime;

	private MemberEntity user;

	private DeptEntity dept;

	private HospitalEntity hospital;
	
	private Integer doctorId;
	
	private DoctorEntity doctor;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date appointTime;
	
	private Integer status;
	
	private Integer timeId;
	
	private String time;
	
	private BigDecimal totalAmount;
	
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
	 * 设置：用户
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * 获取：用户
	 */
	public Long getUserId() {
		return userId;
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
	 * 设置：科室
	 */
	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}

	/**
	 * 获取：科室
	 */
	public Integer getDeptId() {
		return deptId;
	}

	/**
	 * 设置：备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * 获取：备注
	 */
	public String getRemark() {
		return remark;
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

	public DeptEntity getDept() {
		return dept;
	}

	public void setDept(DeptEntity dept) {
		this.dept = dept;
	}

	public HospitalEntity getHospital() {
		return hospital;
	}

	public void setHospital(HospitalEntity hospital) {
		this.hospital = hospital;
	}

	public MemberEntity getUser() {
		return user;
	}

	public void setUser(MemberEntity user) {
		this.user = user;
	}

	public Integer getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Integer doctorId) {
		this.doctorId = doctorId;
	}

	public DoctorEntity getDoctor() {
		return doctor;
	}

	public void setDoctor(DoctorEntity doctor) {
		this.doctor = doctor;
	}

	public Date getAppointTime() {
		return appointTime;
	}

	public void setAppointTime(Date appointTime) {
		this.appointTime = appointTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getTimeId() {
		return timeId;
	}

	public void setTimeId(Integer timeId) {
		this.timeId = timeId;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

}
