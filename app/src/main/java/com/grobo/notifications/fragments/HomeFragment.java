package com.grobo.notifications.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
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

        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle("Aman Jee (1801EE03)");

//        Fade exitFade = new Fade();
//        exitFade.setDuration(1000);

        View qrFragment = rootView.findViewById(R.id.home_fr_mess_qr);
        qrFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MessFragment frag = new MessFragment();
                transactFragment(frag);
            }
        });

        View notifFragment = rootView.findViewById(R.id.home_fr_notif);
        notifFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationsFragment frag = new NotificationsFragment();
                transactFragment(frag);
            }
        });

        return rootView;
    }

    private void transactFragment(Fragment frag){
        FragmentTransaction fragmentManager = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentManager.setCustomAnimations(R.anim.right_in, R.anim.right_out, R.anim.right_in, R.anim.right_out)
                .replace(R.id.frame_layout_main, frag, frag.getTag())
                .addToBackStack(frag.getTag())
                .commit();
    }

}
