package com.api.business.bean;
/** 
 * 类说明 :项目实体
 * @author  joker 
 * 创建时间：2015-1-21
 */
public class Project {
	
//	id
	private String uuid;
//	拟融资额
	private String expectedFinance;
//	拟融资期限
	private String expectedFinanceTime;
//	拟融资发方式（0：暂定；1：信用贷；2：抵押贷）
	private int expectedFinanceType;
//	客户姓氏
	private String userName;
//	性别（0：男；1：女）
	private int sex;
//	年龄
	private int age;
//	工作性质（0：未知；1：上班族；2：个体户；3：法人；4：股东；5：实际控制人）
	private int workType;
//	公司
	private String company;
//	现单位工作年限
	private String thisWorkTime;
//	社保
	private String socialSecurity;
//	公积金
	private String providentFund;
//	其他现金补助
	private String otherSubsidy;
//	是否有房（0：未知；1：有；2：没有）
	private int isHaveHouse;
//	房屋性质（0：未知；1：商品房房改房；2：存量房；3：集资房；4：廉租房；5：房花园式住宅；6：公寓式住宅；7：安居工程住房；）
	private int houseType;
//	房屋规划用途（0：未知；1：住宅；2：工业；3：商业；4：办公；5：物业服务；6：其他；）
	private int houseUse;
//	共有情况（0:未知；1：单独；2：夫妻双方；）
	private int commonType;
//	小区名称
	private String community;
//	房屋面积评估值
	private String floorSpace;
//	是否有抵押（0：否；1：是）
	private int isPledge;
//	登记时间
	private String registTime;
//	是否有第二套以上住房（0：否；1：是；）
	private int isHaveOtherHouse;
//	是否有车（0：否；1：是；）
	private int isHaveCar;
//	购车类型（0:未知；1：全款；2：贷款）
	private int carType;
//	车贷月供
	private String carMonthlyPayments;
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
//	项目参与人
	private String partner;
	
	public String getPartner() {
		return partner;
	}
	public void setPartner(String partner) {
		this.partner = partner;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getExpectedFinance() {
		return expectedFinance;
	}
	public void setExpectedFinance(String expectedFinance) {
		this.expectedFinance = expectedFinance;
	}
	public String getExpectedFinanceTime() {
		return expectedFinanceTime;
	}
	public void setExpectedFinanceTime(String expectedFinanceTime) {
		this.expectedFinanceTime = expectedFinanceTime;
	}
	public int getExpectedFinanceType() {
		return expectedFinanceType;
	}
	public void setExpectedFinanceType(int expectedFinanceType) {
		this.expectedFinanceType = expectedFinanceType;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getWorkType() {
		return workType;
	}
	public void setWorkType(int workType) {
		this.workType = workType;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getThisWorkTime() {
		return thisWorkTime;
	}
	public void setThisWorkTime(String thisWorkTime) {
		this.thisWorkTime = thisWorkTime;
	}
	public String getSocialSecurity() {
		return socialSecurity;
	}
	public void setSocialSecurity(String socialSecurity) {
		this.socialSecurity = socialSecurity;
	}
	public String getProvidentFund() {
		return providentFund;
	}
	public void setProvidentFund(String providentFund) {
		this.providentFund = providentFund;
	}
	public String getOtherSubsidy() {
		return otherSubsidy;
	}
	public void setOtherSubsidy(String otherSubsidy) {
		this.otherSubsidy = otherSubsidy;
	}
	public int getIsHaveHouse() {
		return isHaveHouse;
	}
	public void setIsHaveHouse(int isHaveHouse) {
		this.isHaveHouse = isHaveHouse;
	}
	public int getHouseType() {
		return houseType;
	}
	public void setHouseType(int houseType) {
		this.houseType = houseType;
	}
	public int getHouseUse() {
		return houseUse;
	}
	public void setHouseUse(int houseUse) {
		this.houseUse = houseUse;
	}
	public int getCommonType() {
		return commonType;
	}
	public void setCommonType(int commonType) {
		this.commonType = commonType;
	}
	public String getCommunity() {
		return community;
	}
	public void setCommunity(String community) {
		this.community = community;
	}
	public String getFloorSpace() {
		return floorSpace;
	}
	public void setFloorSpace(String floorSpace) {
		this.floorSpace = floorSpace;
	}
	public int getIsPledge() {
		return isPledge;
	}
	public void setIsPledge(int isPledge) {
		this.isPledge = isPledge;
	}
	public String getRegistTime() {
		return registTime;
	}
	public void setRegistTime(String registTime) {
		this.registTime = registTime;
	}
	public int getIsHaveOtherHouse() {
		return isHaveOtherHouse;
	}
	public void setIsHaveOtherHouse(int isHaveOtherHouse) {
		this.isHaveOtherHouse = isHaveOtherHouse;
	}
	public int getIsHaveCar() {
		return isHaveCar;
	}
	public void setIsHaveCar(int isHaveCar) {
		this.isHaveCar = isHaveCar;
	}
	public int getCarType() {
		return carType;
	}
	public void setCarType(int carType) {
		this.carType = carType;
	}
	public String getCarMonthlyPayments() {
		return carMonthlyPayments;
	}
	public void setCarMonthlyPayments(String carMonthlyPayments) {
		this.carMonthlyPayments = carMonthlyPayments;
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
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
}
