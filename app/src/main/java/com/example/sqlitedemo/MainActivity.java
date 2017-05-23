package com.example.sqlitedemo;

import android.content.Intent;
import android.os.Build;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.sqlitedemo.adapter.StudentAdapter;
import com.example.sqlitedemo.db.DBHelper;
import com.example.sqlitedemo.model.Student;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private int RC_ADD_UPDATE_STUDENT = 99;
    private List<Student> mStudentList = new ArrayList<>();
    private StudentAdapter mStudentAdapter;
    private ListView lvStudent;
    private DBHelper dbHelper;
    private TextView tvNoRecord;

    public static String KEY = "w4op394t[srhgjs[";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initControls();

        String data = new EncryptUtils().b("LoKCQCqsZ24MsBW69STatpprNSC5RCYVgol1GvliG0JOph2vpLJ++YTHebv4fN38IErijGyTpyhDP+CnoSmwyw==", KEY);

        Log.v("log_tag", "Data " + data);
    }


    public void initControls() {

        dbHelper = new DBHelper(this);

        tvNoRecord = (TextView) findViewById(R.id.tvNoRecord);
        tvNoRecord.setVisibility(View.GONE);
        mStudentAdapter = new StudentAdapter(this, mStudentList);
        lvStudent = (ListView) findViewById(R.id.lvStudent);
        lvStudent.setAdapter(mStudentAdapter);

        //Implemented Student item click listener
        mStudentAdapter.setStudentItemClickListener(new StudentAdapter.onStudentItemClickListener() {
            @Override
            public void onUpdateStudentClick(int position) {

                Intent iUpdateStudent = new Intent(MainActivity.this, AddUpdateStudentActivity.class);
                iUpdateStudent.putExtra("operation", "edit");
                iUpdateStudent.putExtra("student_id", mStudentList.get(position).getId());
                startActivityForResult(iUpdateStudent, RC_ADD_UPDATE_STUDENT);

            }

            @Override
            public void onDeleteStudentClick(int position) {

                deleteRecord(mStudentList.get(position));

                mStudentList.remove(position);
                mStudentAdapter.notifyDataSetChanged();

                if (mStudentList.size() > 0) {
                    tvNoRecord.setVisibility(View.GONE);
                } else {
                    tvNoRecord.setVisibility(View.VISIBLE);
                }

            }
        });


        mStudentList.clear();
        mStudentList.addAll(getAllStudent());
        mStudentAdapter.notifyDataSetChanged();

        final ImageView ivProfile = (ImageView) findViewById(R.id.ivProfile);

        ivProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddUpdateStudentActivity.class);

                ActivityOptionsCompat options = ActivityOptionsCompat.
                        makeSceneTransitionAnimation(MainActivity.this, ivProfile, "profile");

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    startActivity(intent, options.toBundle());
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add, menu);

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.action_add:
                Intent iAddStudent = new Intent(MainActivity.this, AddUpdateStudentActivity.class);
                startActivityForResult(iAddStudent, RC_ADD_UPDATE_STUDENT);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (resultCode == RESULT_OK && requestCode == RC_ADD_UPDATE_STUDENT) {

            mStudentList.clear();
            mStudentList.addAll(getAllStudent());
            mStudentAdapter.notifyDataSetChanged();
        }

    }

    public List<Student> getAllStudent() {
        List<Student> mStudentList = new ArrayList<>();
        try {
            HashMap<String,Object> aMap=new HashMap<>();
            aMap.put("first_name","dhaval");
            mStudentList.addAll(dbHelper.query(Student.class,aMap));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (mStudentList.size() > 0) {
            tvNoRecord.setVisibility(View.GONE);
        } else {
            tvNoRecord.setVisibility(View.VISIBLE);
        }
        return mStudentList;
    }

    public void deleteRecord(Student student) {
        try {
            dbHelper.deleteById(Student.class, student.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
