package com.example.android.minireddit.datastructure;

import java.util.ArrayList;

/**
 * The Community Data.
 * Created By Sherif Essam.
 */
public class Community {
    /**
     * Unique Community ID
     */
    private int CommunityID;

    /**
     * The name of the community.
     */
    private String CommunityName;

    /**
     * The rules of the community.
     */
    private String RulesContent;

    /**
     * The description of the community.
     */
    private String DescriptionContent;

    /**
     * The banner of the community.
     */
    private String BannerURL;

    /**
     * The logo of the community.
     */
    private String LogoURL;


    /**
     * The moderators of the community.
     */
    private ArrayList<User> CommunityModerators;


    /**
     * Constructing a new Message.
     * @param communityID           the id of the community
     * @param communityName         the name of the community
     * @param rulesContent          the rules of the community
     * @param descriptionContent    the description of the community
     * @param bannerURL             the URL of the banner of the community
     * @param logoURL               the URL of the logo of the community
     */
    public Community(int communityID,String communityName, String rulesContent, String descriptionContent, String bannerURL, String logoURL) {
        CommunityID = communityID;
        CommunityName = communityName;
        RulesContent = rulesContent;
        DescriptionContent = descriptionContent;
        BannerURL = bannerURL;
        LogoURL = logoURL;
    }


    /**
     * Gets Community ID
     * @return community ID
     */
    public int getCommunityID() {
        return CommunityID;
    }


    /**
     * Sets Community ID
     * @param communityID community ID
     */
    public void setCommunityID(int communityID) {
        CommunityID = communityID;
    }


    /**
     * Gets the name of community
     * @return community name
     */
    public String getCommunityName() {
        return CommunityName;
    }


    /**
     * Sets the name of community
     * @param communityName community name
     */
    public void setCommunityName(String communityName) {
        CommunityName = communityName;
    }

    /**
     * Gets the rules of the community
     * @return community rules
     */
    public String getRulesContent() {
        return RulesContent;
    }


    /**
     * Sets the rules of the community
     * @param rulesContent community rules
     */
    public void setRulesContent(String rulesContent) {
        RulesContent = rulesContent;
    }


    /**
     * Gets the description of the community
     * @return community description content
     */
    public String getDescriptionContent() {
        return DescriptionContent;
    }


    /**
     * Sets the description of the community
     * @param descriptionContent community description content
     */
    public void setDescriptionContent(String descriptionContent) {
        DescriptionContent = descriptionContent;
    }


    /**
     * Gets the URL of the community banner
     * @return community banner URL
     */
    public String getBannerURL() {
        return BannerURL;
    }


    /**
     * Sets the URL of the community banner
     * @param bannerURL community banner URL
     */
    public void setBannerURL(String bannerURL) {
        BannerURL = bannerURL;
    }


    /**
     * Gets the URL of the community logo
     * @return community logo URL
     */
    public String getLogoURL() {
        return LogoURL;
    }


    /**
     * Sets the URL of the community logo
     * @param logoURL community logo URL
     */
    public void setLogoURL(String logoURL) {
        LogoURL = logoURL;
    }
}
