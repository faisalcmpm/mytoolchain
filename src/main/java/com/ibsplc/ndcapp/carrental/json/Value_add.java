package com.ibsplc.ndcapp.carrental.json;

public class Value_add
{
   
	private Insurance insurance;

    private String free_cancellation;

    private Additional_drivers additional_drivers;

    private Included_mileage included_mileage;

    private Fuel fuel;

    private String free_breakdown_assistance;

    public Insurance getInsurance ()
    {
        return insurance;
    }

    public void setInsurance (Insurance insurance)
    {
        this.insurance = insurance;
    }

    public String getFree_cancellation ()
    {
        return free_cancellation;
    }

    public void setFree_cancellation (String free_cancellation)
    {
        this.free_cancellation = free_cancellation;
    }

    public Additional_drivers getAdditional_drivers ()
    {
        return additional_drivers;
    }

    public void setAdditional_drivers (Additional_drivers additional_drivers)
    {
        this.additional_drivers = additional_drivers;
    }

    public Included_mileage getIncluded_mileage ()
    {
        return included_mileage;
    }

    public void setIncluded_mileage (Included_mileage included_mileage)
    {
        this.included_mileage = included_mileage;
    }

    public Fuel getFuel ()
    {
        return fuel;
    }

    public void setFuel (Fuel fuel)
    {
        this.fuel = fuel;
    }

    public String getFree_breakdown_assistance ()
    {
        return free_breakdown_assistance;
    }

    public void setFree_breakdown_assistance (String free_breakdown_assistance)
    {
        this.free_breakdown_assistance = free_breakdown_assistance;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [insurance = "+insurance+", free_cancellation = "+free_cancellation+", additional_drivers = "+additional_drivers+", included_mileage = "+included_mileage+", fuel = "+fuel+", free_breakdown_assistance = "+free_breakdown_assistance+"]";
    }
}