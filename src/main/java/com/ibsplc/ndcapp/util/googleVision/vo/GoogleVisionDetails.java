package com.ibsplc.ndcapp.util.googleVision.vo;
public class GoogleVisionDetails
{
    private Responses[] responses;

    public Responses[] getResponses ()
    {
        return responses;
    }

    public void setResponses (Responses[] responses)
    {
        this.responses = responses;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [responses = "+responses+"]";
    }
}