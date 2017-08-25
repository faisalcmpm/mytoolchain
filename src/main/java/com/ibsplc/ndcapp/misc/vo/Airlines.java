package com.ibsplc.ndcapp.misc.vo;
public class Airlines
{
    private String count;

    private String name;

    private String code;

    public String getCount ()
    {
        return count;
    }

    public void setCount (String count)
    {
        this.count = count;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getCode ()
    {
        return code;
    }

    public void setCode (String code)
    {
        this.code = code;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [count = "+count+", name = "+name+", code = "+code+"]";
    }
}