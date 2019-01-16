package com.grobo.notifications.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.firestore.auth.User;
import com.grobo.notifications.R;
import com.grobo.notifications.models.TimetableItem;

import java.util.List;

public class TimetableRecyclerAdapter extends RecyclerView.Adapter<TimetableRecyclerAdapter.TimetableViewHolder> {

    private List<TimetableItem> timetableItemList;
    private Context mContext;

    public TimetableRecyclerAdapter(Context context,List<TimetableItem> timetableItemList){
        this.mContext = context;
        this.timetableItemList = timetableItemList;
    }

    @NonNull
    @Override
    public TimetableViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.timetable_item, viewGroup, false);
        TimetableViewHolder viewHolder = new TimetableViewHolder(view);

        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull TimetableViewHolder timetableViewHolder, int i) {


        if(timetableItemList.get(i) != null) {

 //           Log.e("mylogmessage11", timetableItemList.get(i).getsubject());


        TimetableItem timetableItem = timetableItemList.get(i);

        timetableViewHolder.subjectTextView.setText(timetableItem.getsubject());
        timetableViewHolder.timeTextView.setText(timetableItem.gettime());

        }

    }

    @Override
    public int getItemCount() {
        return timetableItemList.size();
    }

    public class TimetableViewHolder extends RecyclerView.ViewHolder{

        public final TextView timeTextView;
        public final TextView subjectTextView;

        public TimetableViewHolder(@NonNull View itemView) {
            super(itemView);
            timeTextView = (TextView) itemView.findViewById(R.id.tt_time_view);
            subjectTextView = (TextView) itemView.findViewById(R.id.tt_subject_view);
        }

    }

}
