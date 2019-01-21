package com.grobo.notifications.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.transition.Fade;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.grobo.notifications.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

//        Fade exitFade = new Fade();
//        exitFade.setDuration(1000);

        View qrFragment = rootView.findViewById(R.id.home_fr_mess_qr);
        qrFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentManager = getActivity().getSupportFragmentManager().beginTransaction();
                MessFragment.QRFragment frag = new MessFragment.QRFragment();
                fragmentManager.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right, R.anim.slide_in_right, R.anim.slide_out_left)
                        .replace(R.id.frame_layout_main, frag, frag.getTag())
                        .addToBackStack(frag.getTag())
                        .commit();
            }
        });

        return rootView;
    }

}
