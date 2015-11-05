package io.mycat.ep.user.model;

import java.util.Date;

import me.jor.util.Help;

public class User {
	private long id;
	private String mac;
	private String openUdid;
	private String idfa;
	private int os;
	private String osVersion;
	private String phone;
	private String password;
	private String realName;
	private String nickName;
	private String idCard;
	private Date registTime;
	private Date uptime;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getMac() {
		return Help.convert(mac,"");
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public String getOpenUdid() {
		return Help.convert(openUdid,"");
	}
	public void setOpenUdid(String openUdid) {
		this.openUdid = openUdid;
	}
	public String getIdfa() {
		return Help.convert(idfa,"");
	}
	public void setIdfa(String idfa) {
		this.idfa = idfa;
	}
	public int getOs() {
		return os;
	}
	public void setOs(int os) {
		this.os = os;
	}
	public String getOsVersion() {
		return Help.convert(osVersion,"");
	}
	public void setOsVersion(String osVersion) {
		this.osVersion = osVersion;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRealName() {
		return Help.convert( realName,"");
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getNickName() {
		return Help.convert(nickName,"");
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getIdCard() {
		return Help.convert(idCard,"");
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public Date getRegistTime() {
		return registTime;
	}
	public void setRegistTime(Date registTime) {
		this.registTime = registTime;
	}
	public Date getUptime() {
		return Help.convert(uptime,registTime);
	}
	public void setUptime(Date uptime) {
		this.uptime = uptime;
	}
}
