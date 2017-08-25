package com.ibsplc.ndcapp.util.googleVision.vo;
public class WebEntities
{
    private String description;

    private String score;

    private String entityId;

    public String getDescription ()
    {
        return description;
    }

    public void setDescription (String description)
    {
        this.description = description;
    }

    public String getScore ()
    {
        return score;
    }

    public void setScore (String score)
    {
        this.score = score;
    }

    public String getEntityId ()
    {
        return entityId;
    }

    public void setEntityId (String entityId)
    {
        this.entityId = entityId;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [description = "+description+", score = "+score+", entityId = "+entityId+"]";
    }
}