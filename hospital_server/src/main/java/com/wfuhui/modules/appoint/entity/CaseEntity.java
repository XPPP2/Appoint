package com.wfuhui.modules.appoint.entity;

import java.io.Serializable;
import java.util.Date;

import com.wfuhui.modules.hospital.entity.DoctorEntity;
import com.wfuhui.modules.member.entity.MemberEntity;


public class CaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//挂号单
	private Integer appointId;
	//医生
	private Long doctorId;
	//结果
	private String result;
	//建议
	private String proposal;
	//
	private Date createTime;
	
	private DoctorEntity doctor;
	
	private Integer memberId;
	
	private MemberEntity member;

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
	 * 设置：挂号单
	 */
	public void setAppointId(Integer appointId) {
		this.appointId = appointId;
	}
	/**
	 * 获取：挂号单
	 */
	public Integer getAppointId() {
		return appointId;
	}
	/**
	 * 设置：医生
	 */
	public void setDoctorId(Long doctorId) {
		this.doctorId = doctorId;
	}
	/**
	 * 获取：医生
	 */
	public Long getDoctorId() {
		return doctorId;
	}
	/**
	 * 设置：结果
	 */
	public void setResult(String result) {
		this.result = result;
	}
	/**
	 * 获取：结果
	 */
	public String getResult() {
		return result;
	}
	/**
	 * 设置：建议
	 */
	public void setProposal(String proposal) {
		this.proposal = proposal;
	}
	/**
	 * 获取：建议
	 */
	public String getProposal() {
		return proposal;
	}
	/**
	 * 设置：
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：
	 */
	public Date getCreateTime() {
		return createTime;
	}
	
	public DoctorEntity getDoctor() {
		return doctor;
	}
	
	public void setDoctor(DoctorEntity doctor) {
		this.doctor = doctor;
	}
	public Integer getMemberId() {
		return memberId;
	}
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
	
	public MemberEntity getMember() {
		return member;
	}
	
	public void setMember(MemberEntity member) {
		this.member = member;
	}
	
}
