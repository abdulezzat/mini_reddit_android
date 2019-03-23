package com.example.android.minireddit.datastructure;

import com.example.android.minireddit.R;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Unit testing for all User class constructors and setters.
 *
 * Created by karashily on 23/03/19.
 */

public class UserTest {


    @Test
    public void constructor_isCorrect() throws Exception {
        User user=new User("karashily","Ahmed ElKarashily", "www.kesho.com/pi", "karashily@gmail.com", "just testing this", 5, "5 march, 2015", false);
        assertEquals(user.getmUserName(),"karashily");
        assertEquals(user.getmDisplayName(),"Ahmed ElKarashily");
        assertEquals(user.getmProfileImage(),"www.kesho.com/pi");
        assertEquals(user.getmEmail(),"karashily@gmail.com");
        assertEquals(user.getmAbout(),"just testing this");
        assertEquals(user.getmKarma(),5);
        assertEquals(user.getmCakeDay(),"5 march, 2015");
        assertEquals(user.ismDeleted(),false);
    }

    @Test
    public void requestConstructor_isCorrect() throws Exception {
        User user=new User("karashily",5 , "5 march, 2015", "just testing this");
        assertEquals(user.getmUserName(),"karashily");
        assertEquals(user.getmDisplayName(),"karashily");
        assertEquals(user.getmAbout(),"just testing this");
        assertEquals(user.getmKarma(),5);
        assertEquals(user.getmCakeDay(),"5 march, 2015");
    }

    @Test
    public void setmUserName_isCorrect() throws Exception {
        User user=new User("",0,"","");
        user.setmUserName("koko");

        assertEquals(user.getmUserName(),"koko");
    }

    @Test
    public void setmDisplayName_isCorrect() throws Exception {
        User user=new User("",0,"","");
        user.setmDisplayName("koko");

        assertEquals(user.getmDisplayName(),"koko");
    }

    @Test
    public void setmHeaderImage_isCorrect() throws Exception {
        User user=new User("",0,"","");
        user.setmHeaderImage("www.koko.com");

        assertEquals(user.getmHeaderImage(),"www.koko.com");
    }



    @Test
    public void setmEmail_isCorrect() throws Exception {
        User user=new User("",0,"","");
        user.setmEmail("koko@gmail.com");

        assertEquals(user.getmEmail(),"koko@gmail.com");
    }

    @Test
    public void setmAbout_isCorrect() throws Exception {
        User user=new User("",0,"","");
        user.setmAbout("just testing");

        assertEquals(user.getmAbout(),"just testing");
    }

    @Test
    public void setmKarma_isCorrect() throws Exception {
        User user=new User("",0,"","");
        user.setmKarma(5);

        assertEquals(user.getmKarma(),5);
    }

    @Test
    public void setmCakeDay_isCorrect() throws Exception {
        User user=new User("",0,"","");
        user.setmCakeDay("12 may, 2011");

        assertEquals(user.getmCakeDay(),"12 may, 2011");
    }

    @Test
    public void setmDeleted_isCorrect() throws Exception {
        User user=new User("",0,"","");
        user.setmDeleted(false);

        assertEquals(user.ismDeleted(),false);
    }

    @Test
    public void setmFollowers_isCorrect() throws Exception {
        User user=new User("",0,"","");

        ArrayList<String> testList=new ArrayList<>();
        testList.add("ahmed");
        testList.add("mohamed");
        testList.add("sheko");
        user.setmFollowers(testList);

        assertEquals(user.getmFollowers(),testList);
    }

    @Test
    public void addFollowers_isCorrect() throws Exception {
        User user=new User("",0,"","");

        ArrayList<String> testList1=new ArrayList<>();
        ArrayList<String> testList2=user.getmFollowing();
        testList2.add("ahmed");
        testList2.add("mohamed");
        testList2.add("sheko");

        testList1.add("ahmed");
        testList1.add("mohamed");
        testList1.add("sheko");
        user.addFollowers(testList1);

        assertEquals(user.getmFollowers(),testList2);
    }

    @Test
    public void setmFollowing_isCorrect() throws Exception {
        User user=new User("",0,"","");
        ArrayList<String> testList=new ArrayList<>();
        testList.add("ahmed");
        testList.add("mohamed");
        testList.add("sheko");
        user.setmFollowing(testList);

        assertEquals(user.getmFollowing(),testList);
    }

    @Test
    public void addFollowing_isCorrect() throws Exception {
        User user=new User("",0,"","");

        ArrayList<String> testList1=new ArrayList<>();
        ArrayList<String> testList2=user.getmFollowing();
        testList2.add("ahmed");
        testList2.add("mohamed");
        testList2.add("sheko");

        testList1.add("ahmed");
        testList1.add("mohamed");
        testList1.add("sheko");
        user.addFollowing(testList1);

        assertEquals(testList2,user.getmFollowing());
    }

}
