package com.wfuhui.modules.hospital.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 医院
 * 
 * @author lizhengle
 * @email 2803180149@qq.com
 */
public class HospitalEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	//
	private Integer id;
	// 医院名称
	private String hospitalName;
	// 图片
	private String picUrl;
	// 地区
	private Integer areaId;
	// 备注
	private String remark;
	// 创建时间
	private Date createTime;

	private String address;

	private String mobile;

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
	 * 设置：医院名称
	 */
	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	/**
	 * 获取：医院名称
	 */
	public String getHospitalName() {
		return hospitalName;
	}

	/**
	 * 设置：图片
	 */
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	/**
	 * 获取：图片
	 */
	public String getPicUrl() {
		return picUrl;
	}

	/**
	 * 设置：地区
	 */
	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	/**
	 * 获取：地区
	 */
	public Integer getAreaId() {
		return areaId;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

}
