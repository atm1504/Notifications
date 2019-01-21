package com.grobo.notifications.fragments;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.customtabs.CustomTabsIntent;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.grobo.notifications.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LinksFragment extends Fragment {

    private TextView tvWebMail, tvLibRes, tvLibCat, tvReg;

    public LinksFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_links, container, false);

        tvWebMail = (TextView) rootView.findViewById(R.id.tv_webmail);
        tvWebMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                browserIntent("https://www.facebook.com");
            }
        });

        tvLibRes = (TextView) rootView.findViewById(R.id.tv_lib_res);
        tvLibRes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                browserIntent("https://www.facebook.com");
            }
        });

        tvLibCat = (TextView) rootView.findViewById(R.id.tv_lib_catalogue);
        tvLibCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                browserIntent("https://www.facebook.com");
            }
        });

        tvReg = (TextView) rootView.findViewById(R.id.tv_reg);
        tvReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                browserIntent("https://www.facebook.com");
            }
        });

        return rootView;
    }

    private void browserIntent(String url) {
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        builder.setToolbarColor(getResources().getColor(R.color.colorPrimary));

        CustomTabsIntent customTabsIntent = builder.build();
        customTabsIntent.launchUrl(getContext(), Uri.parse(url));
    }

}
