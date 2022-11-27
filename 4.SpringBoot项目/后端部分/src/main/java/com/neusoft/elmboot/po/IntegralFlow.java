package com.neusoft.elmboot.po;

public class IntegralFlow {
    private String userId;
    private Integer integralChange;
    private String flowDate;
    private String flowDetails;
    private String expirationDate;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getIntegralChange() {
        return integralChange;
    }

    public void setIntegralChange(Integer integralChange) {
        this.integralChange = integralChange;
    }

    public String getFlowDate() {
        return flowDate;
    }

    public void setFlowDate(String flowDate) {
        this.flowDate = flowDate;
    }

    public String getFlowDetails() {
        return flowDetails;
    }

    public void setFlowDetails(String flowDetails) {
        this.flowDetails = flowDetails;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }
}
