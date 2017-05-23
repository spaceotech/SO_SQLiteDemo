package com.example.sqlitedemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.sqlitedemo.R;
import com.example.sqlitedemo.model.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sotsys-217 on 19/8/16.
 */
public class StudentAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private List<Student> mStudentList = new ArrayList<>();
    private Context mContext;
    private onStudentItemClickListener mStudentItemClickListener;


    // Student Item Click Listener
    public interface onStudentItemClickListener {

        void onUpdateStudentClick(int position);

        void onDeleteStudentClick(int position);
    }

    // Getter and Setter of Student Item Click Listener interface
    public onStudentItemClickListener getStudentItemClickListener() {
        return mStudentItemClickListener;
    }

    public void setStudentItemClickListener(onStudentItemClickListener mStudentItemClickListener) {
        this.mStudentItemClickListener = mStudentItemClickListener;
    }

    // Constructors
    public StudentAdapter(Context context, List<Student> mStudentList) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
        this.mStudentList = mStudentList;
    }

    @Override
    public int getCount() {
        return mStudentList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;

        if (convertView == null) {

            convertView = mInflater.inflate(R.layout.item_student, null);

            TextView tvStudentName = (TextView) convertView.findViewById(R.id.tvStudentName);
            TextView tvMobileNumber = (TextView) convertView.findViewById(R.id.tvMobileNumber);
            TextView tvAddress = (TextView) convertView.findViewById(R.id.tvAddress);
            ImageView ivEdit = (ImageView) convertView.findViewById(R.id.ivEdit);
            ImageView ivDelete = (ImageView) convertView.findViewById(R.id.ivDelete);

            viewHolder = new ViewHolder();
            viewHolder.tvStudentName = tvStudentName;
            viewHolder.tvMobileNumber = tvMobileNumber;
            viewHolder.tvAddress = tvAddress;
            viewHolder.ivEdit = ivEdit;
            viewHolder.ivDelete = ivDelete;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Student student = mStudentList.get(position);

        viewHolder.tvStudentName.setText(student.getFirstName() + " " + student.getLastName());
        viewHolder.tvMobileNumber.setText(student.getMobileNumber());
        viewHolder.tvAddress.setText(student.getAddress());


        viewHolder.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mStudentItemClickListener != null) {
                    mStudentItemClickListener.onDeleteStudentClick(position);
                }

            }
        });

        viewHolder.ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mStudentItemClickListener != null) {
                    mStudentItemClickListener.onUpdateStudentClick(position);
                }
            }
        });


        return convertView;
    }


    private static class ViewHolder {
        public TextView tvStudentName;
        public TextView tvMobileNumber;
        public TextView tvAddress;
        public ImageView ivEdit;
        public ImageView ivDelete;
    }
}
