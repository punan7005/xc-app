package com.api.business.bean;

/** 
 * 类说明 :authToken信息实体
 * @author  joker 
 * 创建时间：2015-1-21
 */
public class AuthToken {
	
//	uuid
	private String uuid;
//	所属id
	private String parentId;
//	创建时间毫秒
	private Long startTime;
//	状态（0：正常；1：过期）
	private int status;
//	当前登录设备类型（0：未知；1：ios；2：android）
	private int type;
	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public Long getStartTime() {
		return startTime;
	}
	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}
}
