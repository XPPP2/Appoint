package com.wfuhui.modules.settings.entity;

import java.io.Serializable;
import java.util.Date;

import com.wfuhui.modules.hospital.entity.DoctorEntity;

public class SettingEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//医院id
	private Integer doctorId;
	//开始时间
	private String startTime;
	//结束时间
	private String endTime;
	//预约人数
	private Integer maxPeople;
	
	private DoctorEntity doctor;

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
	 * 设置：医院id
	 */
	public void setDoctorId(Integer doctorId) {
		this.doctorId = doctorId;
	}
	/**
	 * 获取：医院id
	 */
	public Integer getDoctorId() {
		return doctorId;
	}
	/**
	 * 设置：开始时间
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	/**
	 * 获取：开始时间
	 */
	public String getStartTime() {
		return startTime;
	}
	/**
	 * 设置：结束时间
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	/**
	 * 获取：结束时间
	 */
	public String getEndTime() {
		return endTime;
	}
	/**
	 * 设置：预约人数
	 */
	public void setMaxPeople(Integer maxPeople) {
		this.maxPeople = maxPeople;
	}
	/**
	 * 获取：预约人数
	 */
	public Integer getMaxPeople() {
		return maxPeople;
	}
	
	public DoctorEntity getDoctor() {
		return doctor;
	}
	
	public void setDoctor(DoctorEntity doctor) {
		this.doctor = doctor;
	}
	
}
