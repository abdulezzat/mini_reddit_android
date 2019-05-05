package com.example.android.minireddit.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Telephony;
import android.support.annotation.DrawableRes;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.minireddit.R;
import com.example.android.minireddit.adapters.InboxPagerAdapter;
import com.example.android.minireddit.adapters.ViewPagerAdapter;


public class InboxFragment extends Fragment //implements NotificationTab.OnFragmentInteractionListener,MessagesTab.OnFragmentInteractionListener,ModMailTab.OnFragmentInteractionListener
{

    private ViewPager viewPager;
    private InboxPagerAdapter inboxPagerAdapter;
    private TabLayout tabLayout;

    public InboxFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_inbox, container, false);

        viewPager = (ViewPager) rootView.findViewById(R.id.inbox_fragment_viewpager);
        inboxPagerAdapter = new InboxPagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(inboxPagerAdapter);
        tabLayout = (TabLayout)rootView.findViewById(R.id.inbox_fragment_tablayout);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.setupWithViewPager(viewPager,true);
        tabLayout.setSelected(true);
        tabLayout.setTabTextColors(getResources().getColor(R.color.black), getResources().getColor(R.color.black));



//        TabLayout tabLayout = (TabLayout) rootView.findViewById(R.id.inbox_fragment_tablayout);
//        tabLayout.addTab(tabLayout.newTab().setText("Notifications"));
//        tabLayout.addTab(tabLayout.newTab().setText("Messages"));
//        tabLayout.addTab(tabLayout.newTab().setText("Mod mail"));
//        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
//
//        final ViewPager viewPager = (ViewPager) rootView.findViewById(R.id.inbox_fragment_viewpager);
//        final InboxPagerAdapter inboxPagerAdapter = new InboxPagerAdapter(getFragmentManager(),tabLayout.getTabCount());
//        viewPager.setAdapter(inboxPagerAdapter);
//        viewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
//        tabLayout.setOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                viewPager.setCurrentItem(tab.getPosition());
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });

        return rootView;
    }


//    @Override
//    public void onFragmentInteraction(Uri uri) {
//
//    }
}
