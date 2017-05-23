package com.example.sqlitedemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.sqlitedemo.db.DBHelper;
import com.example.sqlitedemo.model.Student;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;

public class AddUpdateStudentActivity extends AppCompatActivity {

    private EditText edFirstName, edLastName, edMobileNumber, edAddress;
    private String operation = "add";
    private Student student;
    private int studentId;
    private DBHelper dbHelper;
    private Button btnAddUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_update_student);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {

            if (extras.containsKey("operation")) {
                operation = extras.getString("operation");
            }

            if (extras.containsKey("student_id")) {
                studentId = extras.getInt("student_id");
            }

        }

        initControls();
    }


    public void initControls() {

        dbHelper = new DBHelper(this);

        edFirstName = (EditText) findViewById(R.id.edFirstName);
        edLastName = (EditText) findViewById(R.id.edLastName);
        edMobileNumber = (EditText) findViewById(R.id.edMobileNumber);
        edAddress = (EditText) findViewById(R.id.edAddress);
        btnAddUpdate = (Button) findViewById(R.id.btnAddUpdate);

        if (operation.equals("add")) {
            student = new Student();
            btnAddUpdate.setText("Add Student");

            setTitle("Add Student");
        } else if (operation.equals("edit")) {
            try {

                student = dbHelper.getById(Student.class, studentId);

                edFirstName.setText(student.getFirstName());
                edLastName.setText(student.getLastName());
                edMobileNumber.setText(student.getMobileNumber());
                edAddress.setText(student.getAddress());

                btnAddUpdate.setText("Update Student");
                setTitle("Update Student");

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void onAddStudentClick(View view) {

        student.setFirstName(edFirstName.getText().toString().trim());
        student.setLastName(edLastName.getText().toString().trim());
        student.setMobileNumber(edMobileNumber.getText().toString().trim());
        student.setAddress(edAddress.getText().toString().trim());


        try {
            dbHelper.createOrUpdate(student);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        setResult(RESULT_OK);
        finish();
    }


}
