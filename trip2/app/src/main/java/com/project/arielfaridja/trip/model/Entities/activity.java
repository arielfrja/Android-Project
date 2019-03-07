package com.project.arielfaridja.trip.model.Entities;
/**
 * Created by c plus on 05/12/2016.
 */

public class activity {
    ActDes description;
    String country;
    float cost;
    Date startDate;
    Date EndDate;
    String LongDescription;
    int BusinessId;

    public activity(ActDes description, String country, float cost, Date startDate, Date EndDate, String longDescription, int businessId) {
        this.description = description;
        this.country = country;
        this.cost = cost;
        this.startDate = startDate;
        this.EndDate = EndDate;
        LongDescription = longDescription;
        BusinessId = businessId;
    }
    public activity() {
        this.description = null;
        this.country = "";
        this.cost = 0;
        startDate = new Date();
        EndDate = new Date();
        LongDescription = "";
        BusinessId = 0;
    }
    public ActDes getDescription() {
        return description;
    }

    public void setDescription(ActDes description) {
        this.description = description;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public String getLongDescription() {
        return LongDescription;
    }

    public void setLongDescription(String longDescription) {
        LongDescription = longDescription;
    }

    public int getBusinessId() {
        return BusinessId;
    }

    public void setBusinessId(int businessId) {
        BusinessId = businessId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return EndDate;
    }

    public void setEndDate(Date endDate) {
        EndDate = endDate;
    }
}
