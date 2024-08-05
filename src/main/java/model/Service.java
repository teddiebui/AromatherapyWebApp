package model;

import java.math.BigDecimal;
import java.sql.Date;

/**
 * Represents a service in the system.
 */
public class Service implements Model {
    
    /**
     * The unique identifier of the service.
     */
    private int serviceId;
    
    /**
     * The identifier of the employee who provides the service.
     */
    private int employeeId;
    
    /**
     * The title of the service.
     */
    private String serviceTitle;
    
    /**
     * Information about the service.
     */
    private String serviceInfo;
    
    /**
     * The URL of the image representing the service.
     */
    private String serviceImgSrc;
    
    /**
     * The price of the service.
     */
    private BigDecimal servicePrice;
    
    /**
     * The timestamp of when the service was created.
     */
    private Date serviceCreateTime;

    // Constructors

    /**
     * Default constructor.
     */
    public Service() {
        super();
    }
    
    // Getters and Setters
    
    /**
     * Gets the unique identifier of the service.
     * @return the serviceId
     */
    public int getServiceId() {
        return serviceId;
    }

    /**
     * Sets the unique identifier of the service.
     * @param newServiceId the serviceId to set
     */
    public void setServiceId(int newServiceId) {
        this.serviceId = newServiceId;
    }

    /**
     * Gets the identifier of the employee who provides the service.
     * @return the employeeId
     */
    public int getEmployeeId() {
        return employeeId;
    }

    /**
     * Sets the identifier of the employee who provides the service.
     * @param newEmployeeId the employeeId to set
     */
    public void setEmployeeId(int newEmployeeId) {
        this.employeeId = newEmployeeId;
    }

    /**
     * Gets the title of the service.
     * @return the serviceTitle
     */
    public String getServiceTitle() {
        return serviceTitle;
    }

    /**
     * Sets the title of the service.
     * @param newServiceTitle the serviceTitle to set
     */
    public void setServiceTitle(String newServiceTitle) {
        this.serviceTitle = newServiceTitle;
    }

    /**
     * Gets information about the service.
     * @return the serviceInfo
     */
    public String getServiceInfo() {
        return serviceInfo;
    }

    /**
     * Sets information about the service.
     * @param newServiceInfo the serviceInfo to set
     */
    public void setServiceInfo(String newServiceInfo) {
        this.serviceInfo = newServiceInfo;
    }

    /**
     * Gets the URL of the image representing the service.
     * @return the serviceImgSrc
     */
    public String getServiceImgSrc() {
        return serviceImgSrc;
    }

    /**
     * Sets the URL of the image representing the service.
     * @param newServiceImgSrc the serviceImgSrc to set
     */
    public void setServiceImgSrc(String newServiceImgSrc) {
        this.serviceImgSrc = newServiceImgSrc;
    }

    /**
     * Gets the price of the service.
     * @return the servicePrice
     */
    public BigDecimal getServicePrice() {
        return servicePrice;
    }

    /**
     * Sets the price of the service.
     * @param newServicePrice the servicePrice to set
     */
    public void setServicePrice(BigDecimal newServicePrice) {
        this.servicePrice = newServicePrice;
    }

    /**
     * Gets the timestamp of when the service was created.
     * @return the serviceCreateTime
     */
    public Date getServiceCreateTime() {
        return serviceCreateTime;
    }

    /**
     * Sets the timestamp of when the service was created.
     * @param newServiceCreateTime the serviceCreateTime to set
     */
    public void setServiceCreateTime(Date newServiceCreateTime) {
        this.serviceCreateTime = newServiceCreateTime;
    }
    
    /**
     * Returns a string representation of the service.
     * @return a string representation of the service
     */
    @Override
    public String toString() {
        return "Service [serviceId=" + serviceId + ", employeeId=" + employeeId
                + ", serviceTitle=" + serviceTitle + ", serviceInfo="
                + serviceInfo + ", serviceImgSrc=" + serviceImgSrc
                + ", servicePrice=" + servicePrice + ", serviceCreateTime="
                + serviceCreateTime + "]";
    }
}
