package com.chengxugo.pojo.BasicInfo;


//班级表
public class Class {
    private  long cid;
    private  String cname;
    private  long maid;
    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public long getCid() {
        return cid;
    }

    public void setCid(long cid) {
        this.cid = cid;
    }

    public long getMaid() {
        return maid;
    }

    public void setMaid(long maid) {
        this.maid = maid;
    }
}
