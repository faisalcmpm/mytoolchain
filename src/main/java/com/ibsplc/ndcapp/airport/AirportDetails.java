package com.ibsplc.ndcapp.airport;
public class AirportDetails

{
    private String errorMessage;

    private Airports[] airports;

    private String authorisedAPI;

    private String processingDurationMillis;

    private String success;

    private String airline;

    public String getErrorMessage ()
    {
        return errorMessage;
    }

    public void setErrorMessage (String errorMessage)
    {
        this.errorMessage = errorMessage;
    }

    public Airports[] getAirports ()
    {
        return airports;
    }

    public void setAirports (Airports[] airports)
    {
        this.airports = airports;
    }

    public String getAuthorisedAPI ()
    {
        return authorisedAPI;
    }

    public void setAuthorisedAPI (String authorisedAPI)
    {
        this.authorisedAPI = authorisedAPI;
    }

    public String getProcessingDurationMillis ()
    {
        return processingDurationMillis;
    }

    public void setProcessingDurationMillis (String processingDurationMillis)
    {
        this.processingDurationMillis = processingDurationMillis;
    }

    public String getSuccess ()
    {
        return success;
    }

    public void setSuccess (String success)
    {
        this.success = success;
    }

    public String getAirline ()
    {
        return airline;
    }

    public void setAirline (String airline)
    {
        this.airline = airline;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [errorMessage = "+errorMessage+", airports = "+airports+", authorisedAPI = "+authorisedAPI+", processingDurationMillis = "+processingDurationMillis+", success = "+success+", airline = "+airline+"]";
    }
}