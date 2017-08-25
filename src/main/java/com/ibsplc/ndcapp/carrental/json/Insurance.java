package com.ibsplc.ndcapp.carrental.json;

public class Insurance
{
   
	private String theft_protection;

    private String free_collision_waiver;

    private String third_party_cover;

    public String getTheft_protection ()
    {
        return theft_protection;
    }

    public void setTheft_protection (String theft_protection)
    {
        this.theft_protection = theft_protection;
    }

    public String getFree_collision_waiver ()
    {
        return free_collision_waiver;
    }

    public void setFree_collision_waiver (String free_collision_waiver)
    {
        this.free_collision_waiver = free_collision_waiver;
    }

    public String getThird_party_cover ()
    {
        return third_party_cover;
    }

    public void setThird_party_cover (String third_party_cover)
    {
        this.third_party_cover = third_party_cover;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [theft_protection = "+theft_protection+", free_collision_waiver = "+free_collision_waiver+", third_party_cover = "+third_party_cover+"]";
    }
}