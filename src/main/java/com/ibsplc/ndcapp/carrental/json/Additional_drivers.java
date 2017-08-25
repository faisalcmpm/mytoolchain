package com.ibsplc.ndcapp.carrental.json;

public class Additional_drivers
{
	private Paid paid;

    public Paid getPaid ()
    {
        return paid;
    }

    public void setPaid (Paid paid)
    {
        this.paid = paid;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [paid = "+paid+"]";
    }
}