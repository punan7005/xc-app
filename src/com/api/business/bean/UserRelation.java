package com.api.business.bean;
/** 
 * 类说明 :用户关系实体
 * @author  joker 
 * 创建时间：2015-05-26
 */
public class UserRelation {
//	id
	private String uuid;
//	用户id
	private String userId;
//	关系人id
	private String relationId;
//	创建时间
	private String createTime;
//	更改时间
	private String updateTime;
//	创建人
	private String createId;
//	更改人
	private String updateId;
//	状态（0：正常；1：删除）
	private int status;
//	关系人名称
	private String relationName;
//	关系人头像
	private String relationPhoto;
	
	
	public String getRelationPhoto() {
		return relationPhoto;
	}
	public void setRelationPhoto(String relationPhoto) {
		this.relationPhoto = relationPhoto;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getRelationId() {
		return relationId;
	}
	public void setRelationId(String relationId) {
		this.relationId = relationId;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public String getCreateId() {
		return createId;
	}
	public void setCreateId(String createId) {
		this.createId = createId;
	}
	public String getUpdateId() {
		return updateId;
	}
	public void setUpdateId(String updateId) {
		this.updateId = updateId;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getRelationName() {
		return relationName;
	}
	public void setRelationName(String relationName) {
		this.relationName = relationName;
	}
	
	
}
