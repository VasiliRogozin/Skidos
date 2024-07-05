package com.example.buhality;

public class Char {

    String id;
    String name;
    String mony;
    String rez;
    float rezy;
    Char (String _id, String _name, String _mony, String _rez, float _rezy){
        id = _id;
        name = _name;
        mony = _mony;
        rez =_rez;
        rezy = _rezy;
    }
    public String getName(){
        return name;
    }
    public String getMony() { return mony; }
    public String getRez() { return rez; }

    public void setName(String str)
    {
        name = str;
    }
    public void setMony(String str)
    {
        mony = str;
    }



}
