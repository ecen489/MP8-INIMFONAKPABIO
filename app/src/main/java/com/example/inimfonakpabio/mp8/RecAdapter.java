package com.example.inimfonakpabio.mp8;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

public class RecAdapter extends RecyclerView.Adapter<RecAdapter.ViewHolder> {

    private List<Grade> gradeList;
    private Context mContext;
    private Map<Integer, String> studentIDMap;

    public RecAdapter(List<Grade> listitems, Map<Integer, String> nameMap, Context context) {
        gradeList = listitems;
        mContext = context;
        studentIDMap = nameMap;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Grade grade = gradeList.get(position);

        holder.textName.setText(studentIDMap.get(grade.getstudent_id()));
        holder.textCourse.setText(grade.getcourse_name());
        holder.textGrade.setText(grade.getgrade());
    }

    @Override
    public int getItemCount() {
        return gradeList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textName, textCourse, textGrade;

        public ViewHolder(View itemView) {
            super(itemView);

            textName = (TextView) itemView.findViewById(R.id.textName);
            textCourse = (TextView) itemView.findViewById(R.id.textCourse);
            textGrade = (TextView) itemView.findViewById(R.id.textGrade);
        }
    }
}
