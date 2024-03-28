package com.example.basicfragmentbottomnavigation;

public class Upload1{
    private String mName;
    private String mImageUrl;
    private String mDesc;
    private String mLoc;
    private String mBranch;
    private int mRank;
    private String mLink;
    private String mDoc;

    public Upload1() {
        //empty constructor needed
    }

    public Upload1(String doc) {
       /* if (name.trim().equals("")) {
            name = "No Name";
        }
      *//*  mDesc = desc;
        mName = name;
        mImageUrl = imageUrl;
        mLoc = loc;
        mBranch = branch;
        mRank= rank;
        mLink =link;*/
        mDoc = doc;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        mImageUrl = imageUrl;
    }

    public String getDesc() {
        return mDesc;
    }
    public void setDesc(String desc) {
        mDesc = desc;
    }


    public String getLoc() {
        return mLoc;
    }
    public void setLoc(String loc) {
        mLoc = loc;
    }

    public String getBranch() {
        return mBranch;
    }
    public void setBranch(String branch) {
        mBranch = branch;
    }

    public int getRank() {
        return mRank;
    }
    public void setRank(int rank) {
        mRank = rank;
    }

    public String getLink() {
        return mLink;
    }
    public void setLink(String link) {
        mLink = link;
    }
    public String getDoc() {
        return mDoc;
    }
    public void setDoc(String doc) {
        mDoc = doc;
    }
}