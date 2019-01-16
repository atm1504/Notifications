package com.grobo.notifications.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.grobo.notifications.R;
import com.grobo.notifications.models.TimetableItem;

public class TimetableRecyclerAdapter extends RecyclerView.Adapter<TimetableRecyclerAdapter.TimetableViewHolder> {

    private TimetableItem timetableItem;

    public TimetableRecyclerAdapter(){}

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

        timetableViewHolder.subjectTextView.setText(timetableItem.getsubject());
        timetableViewHolder.timeTextView.setText(timetableItem.gettime());

    }

    @Override
    public int getItemCount() {
        return 0;
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

    public void setTimetableItem(TimetableItem timetableItem) {
        this.timetableItem = timetableItem;
        notifyDataSetChanged();
    }
}
