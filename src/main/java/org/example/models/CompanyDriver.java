package org.example.models;

public class CompanyDriver implements HasId {
    private int driverId;
    private int companyId;

    public CompanyDriver(int driverId, int companyId) {
        this.driverId = driverId;
        this.companyId = companyId;
    }

    public int getDriverId() {
        return driverId;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    @Override
    public String toString() {
        return "CompanyDriver{" +
                "driverId=" + driverId +
                ", companyId=" + companyId +
                '}';
    }

    @Override
    public Integer getId() {
        return null;
    }
}
