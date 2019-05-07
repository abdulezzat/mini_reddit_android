package com.example.android.minireddit.datastructure;

import com.example.android.minireddit.R;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MessageTest {
    @Test
    public void constructor_isCorrect() throws Exception {
        Message message=new Message(1,"This is test text for message class","Sherif","Youssef","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQLDe67SbfbhckGj1euPiJhv87qZCjF8WvOcOfVzBwIXup16UBo","https://coubsecure-s.akamaihd.net/get/b175/p/coub/simple/cw_timeline_pic/f402d723108/97f0ead4bcf38cd0a19c1/big_1517875369_image.jpg");
        assertEquals(message.getMessageID(),1);
        assertEquals(message.getMessageContent(),"This is test text for message class");
        assertEquals(message.getReceiverName(),"Sherif");
        assertEquals(message.getSenderName(),"Youssef");
        assertEquals(message.getReceiverPhotoURL(),"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQLDe67SbfbhckGj1euPiJhv87qZCjF8WvOcOfVzBwIXup16UBo");
        assertEquals(message.getSenderPhotoURL(),"https://coubsecure-s.akamaihd.net/get/b175/p/coub/simple/cw_timeline_pic/f402d723108/97f0ead4bcf38cd0a19c1/big_1517875369_image.jpg");
    }

    @Test
    public void setMessageID_isCorrect(){
        Message message=new Message(0,null,null,null,null,null);
        message.setMessageID(1);
        assertEquals(message.getMessageID(),1);
    }

    @Test
    public void setMessageContent_isCorrect(){
        Message message=new Message(0,null,null,null,null,null);
        message.setMessageContent("This is test text for message class");
        assertEquals(message.getMessageContent(),"This is test text for message class");
    }

    @Test
    public void setReceiverName_isCorrect(){
        Message message=new Message(0,null,null,null,null,null);
        message.setReceiverName("Sherif");
        assertEquals(message.getReceiverName(),"Sherif");
    }

    @Test
    public void setSenderName_isCorrect(){
        Message message=new Message(0,null,null,null,null,null);
        message.setSenderName("Youssef");
        assertEquals(message.getSenderName(),"Youssef");
    }

    @Test
    public void setReceiverPhotoURL_isCorrect(){
        Message message=new Message(0,null,null,null,null,null);
        message.setReceiverPhotoURL("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQLDe67SbfbhckGj1euPiJhv87qZCjF8WvOcOfVzBwIXup16UBo");
        assertEquals(message.getReceiverPhotoURL(),"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQLDe67SbfbhckGj1euPiJhv87qZCjF8WvOcOfVzBwIXup16UBo");
    }

    @Test
    public void setSenderPhotoURL_isCorrect(){
        Message message=new Message(0,null,null,null,null,null);
        message.setSenderPhotoURL("https://coubsecure-s.akamaihd.net/get/b175/p/coub/simple/cw_timeline_pic/f402d723108/97f0ead4bcf38cd0a19c1/big_1517875369_image.jpg");
        assertEquals(message.getSenderPhotoURL(),"https://coubsecure-s.akamaihd.net/get/b175/p/coub/simple/cw_timeline_pic/f402d723108/97f0ead4bcf38cd0a19c1/big_1517875369_image.jpg");
    }
}
