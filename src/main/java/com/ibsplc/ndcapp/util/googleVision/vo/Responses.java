package com.ibsplc.ndcapp.util.googleVision.vo;
public class Responses
{
    private WebDetection webDetection;

    private LandmarkAnnotations[] landmarkAnnotations;

    public WebDetection getWebDetection ()
    {
        return webDetection;
    }

    public void setWebDetection (WebDetection webDetection)
    {
        this.webDetection = webDetection;
    }

    public LandmarkAnnotations[] getLandmarkAnnotations ()
    {
        return landmarkAnnotations;
    }

    public void setLandmarkAnnotations (LandmarkAnnotations[] landmarkAnnotations)
    {
        this.landmarkAnnotations = landmarkAnnotations;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [webDetection = "+webDetection+", landmarkAnnotations = "+landmarkAnnotations+"]";
    }
}