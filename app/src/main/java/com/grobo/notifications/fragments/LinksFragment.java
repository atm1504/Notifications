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

    private TextView tvWebMail, tvLibRes, tvLibCat, tvReg, tvPrevQuest, tvIntranet, tvLateFee, tvInstiRepo, tvInterRel;

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
                browserIntent("https://mail.iitp.ac.in");
            }
        });

        tvLibRes = (TextView) rootView.findViewById(R.id.tv_lib_res);
        tvLibRes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                browserIntent("http://library.iitp.ac.in/");
            }
        });

        tvLibCat = (TextView) rootView.findViewById(R.id.tv_lib_catalogue);
        tvLibCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                browserIntent("http://172.16.1.6:8080/webopac/html/SearchForm");
            }
        });

        tvReg = (TextView) rootView.findViewById(R.id.tv_reg);
        tvReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                browserIntent("https://172.16.1.230/student/login2.asp");
            }
        });

        tvPrevQuest = (TextView) rootView.findViewById(R.id.tv_prev_ques);
        tvPrevQuest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                browserIntent("http://172.16.52.180/oldpapers");
            }
        });

        tvIntranet = (TextView) rootView.findViewById(R.id.tv_intranet);
        tvIntranet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                browserIntent("http://172.16.1.6/");
            }
        });

        tvLateFee = (TextView) rootView.findViewById(R.id.tv_late_fee);
        tvLateFee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                browserIntent("https://www.onlinesbi.com/sbicollect/icollecthome.htm");
            }
        });

        tvInstiRepo = (TextView) rootView.findViewById(R.id.tv_institute_repo);
        tvInstiRepo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                browserIntent("http://idr.iitp.ac.in/jspui");
            }
        });

        tvInterRel = (TextView) rootView.findViewById(R.id.tv_inter_rel);
        tvInterRel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                browserIntent("https://172.16.1.4/ir/");
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
