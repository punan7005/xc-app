package com.api.business.bean;
/** 
 * 类说明 :考位实体
 * @author  joker 
 * 创建时间：2015-1-21
 */
public class User {
//	id
	private String uuid;
//	姓名
	private String name;
//	密码
	private String passWord;
//	性别（0：男；1：女）
	private int sex;
//	手机号
	private String phoneNo;
//	公司
	private String company;
//	职位
	private String title;
//	身份证
	private String idCardNo;
//	工龄
	private String workingAge;
//	创建时间
	private String createTime;
//	创建人
	private String createId;
//	更新时间
	private String updateTime;
//	更新人
	private String updateId;
//	状态（0：正常；1：停用）
	private int status;
//	用户类型(0：业务员；1：银行人员；2：客户)
	private int type;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getCreateId() {
		return createId;
	}
	public void setCreateId(String createId) {
		this.createId = createId;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public String getUpdateId() {
		return updateId;
	}
	public void setUpdateId(String updateId) {
		this.updateId = updateId;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getIdCardNo() {
		return idCardNo;
	}
	public void setIdCardNo(String idCardNo) {
		this.idCardNo = idCardNo;
	}
	public String getWorkingAge() {
		return workingAge;
	}
	public void setWorkingAge(String workingAge) {
		this.workingAge = workingAge;
	}
	
}
