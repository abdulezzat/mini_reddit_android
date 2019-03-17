package com.example.android.minireddit.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.android.minireddit.R;

import org.w3c.dom.Text;

/**
 * Fragment for User About shown in the third tab of his/her profile
 * <p>
 * Created by karashily on 17/03/19.
 */

public class ProfileAboutFragment extends Fragment {

    /**
     * default constructor.
     */
    public ProfileAboutFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_profile_about, container, false);

        TextView about = (TextView) rootView.findViewById(R.id.userAbout);
        TextView karma = (TextView) rootView.findViewById(R.id.karma);
        TextView redditAge = (TextView) rootView.findViewById(R.id.redditAge);

        //TODO: Get currentUser and set about and karma and redditAge from his data.

        return rootView;
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
