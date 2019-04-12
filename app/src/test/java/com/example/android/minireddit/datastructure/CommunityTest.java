package com.example.android.minireddit.datastructure;

import com.example.android.minireddit.R;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CommunityTest {
    @Test
    public void constructor_isCorrect() throws Exception {
        Community community = new Community(1,"Testing Community","This community has no rules","This is the description of the community","https://files.catbox.moe/gfu0sz.jpg","https://banner2.kisspng.com/20171201/dcb/superman-logo-png-hd-5a219b596c0785.5547984215121518974425.jpg");
        assertEquals(community.getCommunityID(),1);
        assertEquals(community.getCommunityName(),"Testing Community");
        assertEquals(community.getRulesContent(),"This community has no rules");
        assertEquals(community.getDescriptionContent(),"This is the description of the community");
        assertEquals(community.getBannerURL(),"https://files.catbox.moe/gfu0sz.jpg");
        assertEquals(community.getLogoURL(),"https://banner2.kisspng.com/20171201/dcb/superman-logo-png-hd-5a219b596c0785.5547984215121518974425.jpg");
    }

    @Test
    public void setCommunityID_isCorrect() throws Exception {
        Community community = new Community(0,null,null,null,null,null);
        community.setCommunityID(1);
        assertEquals(community.getCommunityID(),1);
    }

    @Test
    public void setCommunityName_isCorrect() throws Exception {
        Community community = new Community(0,null,null,null,null,null);
        community.setCommunityName("Testing Community");
        assertEquals(community.getCommunityName(),"Testing Community");
    }

    @Test
    public void setRulesContent_isCorrect() throws Exception {
        Community community = new Community(0,null,null,null,null,null);
        community.setRulesContent("This community has no rules");
        assertEquals(community.getRulesContent(),"This community has no rules");
    }

    @Test
    public void setDescriptionContent_isCorrect() throws Exception {
        Community community = new Community(0,null,null,null,null,null);
        community.setDescriptionContent("This is the description of the community");
        assertEquals(community.getDescriptionContent(),"This is the description of the community");
    }

    @Test
    public void setBannerURL_isCorrect() throws Exception {
        Community community = new Community(0,null,null,null,null,null);
        community.setBannerURL("https://files.catbox.moe/gfu0sz.jpg");
        assertEquals(community.getBannerURL(),"https://files.catbox.moe/gfu0sz.jpg");
    }

    @Test
    public void setLogoURL_isCorrect() throws Exception {
        Community community = new Community(0,null,null,null,null,null);
        community.setLogoURL("https://banner2.kisspng.com/20171201/dcb/superman-logo-png-hd-5a219b596c0785.5547984215121518974425.jpg");
        assertEquals(community.getLogoURL(),"https://banner2.kisspng.com/20171201/dcb/superman-logo-png-hd-5a219b596c0785.5547984215121518974425.jpg");
    }
}
