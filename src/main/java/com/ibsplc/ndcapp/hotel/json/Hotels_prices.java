package com.ibsplc.ndcapp.hotel.json;

public class Hotels_prices
{
    private String id;

    private Agent_prices[] agent_prices;

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public Agent_prices[] getAgent_prices ()
    {
        return agent_prices;
    }

    public void setAgent_prices (Agent_prices[] agent_prices)
    {
        this.agent_prices = agent_prices;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [id = "+id+", agent_prices = "+agent_prices+"]";
    }
}
			
