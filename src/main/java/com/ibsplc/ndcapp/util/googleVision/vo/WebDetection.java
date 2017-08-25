package com.ibsplc.ndcapp.util.googleVision.vo;
public class WebDetection
{
    private PartialMatchingImages[] partialMatchingImages;

    private VisuallySimilarImages[] visuallySimilarImages;

    private PagesWithMatchingImages[] pagesWithMatchingImages;

    private WebEntities[] webEntities;

    private FullMatchingImages[] fullMatchingImages;

    public PartialMatchingImages[] getPartialMatchingImages ()
    {
        return partialMatchingImages;
    }

    public void setPartialMatchingImages (PartialMatchingImages[] partialMatchingImages)
    {
        this.partialMatchingImages = partialMatchingImages;
    }

    public VisuallySimilarImages[] getVisuallySimilarImages ()
    {
        return visuallySimilarImages;
    }

    public void setVisuallySimilarImages (VisuallySimilarImages[] visuallySimilarImages)
    {
        this.visuallySimilarImages = visuallySimilarImages;
    }

    public PagesWithMatchingImages[] getPagesWithMatchingImages ()
    {
        return pagesWithMatchingImages;
    }

    public void setPagesWithMatchingImages (PagesWithMatchingImages[] pagesWithMatchingImages)
    {
        this.pagesWithMatchingImages = pagesWithMatchingImages;
    }

    public WebEntities[] getWebEntities ()
    {
        return webEntities;
    }

    public void setWebEntities (WebEntities[] webEntities)
    {
        this.webEntities = webEntities;
    }

    public FullMatchingImages[] getFullMatchingImages ()
    {
        return fullMatchingImages;
    }

    public void setFullMatchingImages (FullMatchingImages[] fullMatchingImages)
    {
        this.fullMatchingImages = fullMatchingImages;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [partialMatchingImages = "+partialMatchingImages+", visuallySimilarImages = "+visuallySimilarImages+", pagesWithMatchingImages = "+pagesWithMatchingImages+", webEntities = "+webEntities+", fullMatchingImages = "+fullMatchingImages+"]";
    }
}