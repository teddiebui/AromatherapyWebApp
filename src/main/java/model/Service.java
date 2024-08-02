package model;

import java.math.BigDecimal;
import java.sql.Date;

public class Service implements Model {
    private int serviceId;
    private int employeeId;
    private String serviceTitle;
    private String serviceInfo;
    private String serviceImgSrc;
    private BigDecimal servicePrice;
    private Date serviceCreateTime;
    
    
    public Service() {
		super();
	}
    
	public int getServiceId() {
		return serviceId;
	}
	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public String getServiceTitle() {
		return serviceTitle;
	}
	public void setServiceTitle(String serviceTitle) {
		this.serviceTitle = serviceTitle;
	}
	public String getServiceInfo() {
		return serviceInfo;
	}
	public void setServiceInfo(String serviceInfo) {
		this.serviceInfo = serviceInfo;
	}
	public String getServiceImgSrc() {
		return serviceImgSrc;
	}
	public void setServiceImgSrc(String serviceImgSrc) {
		this.serviceImgSrc = serviceImgSrc;
	}
	public BigDecimal getServicePrice() {
		return servicePrice;
	}
	public void setServicePrice(BigDecimal servicePrice) {
		this.servicePrice = servicePrice;
	}
	public Date getServiceCreateTime() {
		return serviceCreateTime;
	}
	public void setServiceCreateTime(Date serviceCreateTime) {
		this.serviceCreateTime = serviceCreateTime;
	}
	
	@Override
	public String toString() {
		return "Service [serviceId=" + serviceId + ", employeeId=" + employeeId
				+ ", serviceTitle=" + serviceTitle + ", serviceInfo="
				+ serviceInfo + ", serviceImgSrc=" + serviceImgSrc
				+ ", servicePrice=" + servicePrice + ", serviceCreateTime="
				+ serviceCreateTime + "]";
	}
	
	
	
    
}
