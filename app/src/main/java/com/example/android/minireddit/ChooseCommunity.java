package com.example.android.minireddit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.android.minireddit.abs.GetListOfCommunities;
import com.example.android.minireddit.adapters.CommunityAdapter;
import com.example.android.minireddit.datastructure.*;
import com.example.android.minireddit.datastructure.Community;
import com.example.android.minireddit.networking.DependentClass;

import java.util.ArrayList;

public class ChooseCommunity extends AppCompatActivity {
    ListView communityList;
    EditText search;
    private ArrayList<com.example.android.minireddit.datastructure.Community> mCommunityArrayList = new ArrayList<com.example.android.minireddit.datastructure.Community>();
    private CommunityAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_community);
        communityList=(ListView)findViewById(R.id.communities);
        search=(EditText)findViewById(R.id.search_bar) ;

        this.setTitle("Choose a community");
        Constants.COMMUNITIES=new GetListOfCommunities() {
            @Override
            public void ListOfCommunities(ArrayList<Community> communities) {
                mCommunityArrayList.addAll(communities);
                mAdapter = new CommunityAdapter(ChooseCommunity.this, mCommunityArrayList);
                communityList.setAdapter(mAdapter);

            }
        };
        DependentClass.getInstance().getListOfCommunities(getApplicationContext());




        communityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Constants.CHOOSEN_COMMUNITY.returnedCommunity(mAdapter.getItem(position));
                onBackPressed();
            }
        });

        search.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Call back the Adapter with current character to Filter
                mAdapter.getFilter().filter(s.toString());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {




            case android.R.id.home:
                onBackPressed();


                return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
