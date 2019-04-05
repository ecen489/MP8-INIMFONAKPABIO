package com.example.inimfonakpabio.mp8;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PushActivity extends AppCompatActivity {

    public int studentID = -1;
    EditText courseidEditText, coursenameEditText, gradeEditText;

    DatabaseReference db_ref;
    FirebaseAuth firebase_auth;
    FirebaseUser user = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_push);

        firebase_auth = FirebaseAuth.getInstance();
        user = firebase_auth.getCurrentUser();
        if (user == null) {
            Toast.makeText(getApplicationContext(), "User not authenticated", Toast.LENGTH_SHORT).show();
            finish();
        }

        db_ref = FirebaseDatabase.getInstance().getReference();

        courseidEditText = (EditText) findViewById(R.id.courseidEditText);
        coursenameEditText = (EditText) findViewById(R.id.coursenameEditText);
        gradeEditText = (EditText) findViewById(R.id.gradeEditText);
    }

    public void RadioClick(View view) {
        switch (view.getId()) {
            case R.id.rbBart:
                studentID = 123;
                break;

            case R.id.rbMilhouse:
                studentID = 456;
                break;

            case R.id.rbLisa:
                studentID = 888;
                break;

            case R.id.rbRalph:
                studentID = 404;
                break;

            default:
                break;
        }
    }

    public void PushEntry(View view) {
        final String crsid = courseidEditText.getText().toString().trim();
        final String coursename = coursenameEditText.getText().toString();
        final String grade = gradeEditText.getText().toString();
        int courseid = 0;

        if(crsid.equals("") || coursename.equals("") || grade.equals("")) {
            return;
        } else {
            try {
                courseid = Integer.parseInt(crsid);
            } catch (Exception e) {
                Log.d("NINI", "Unable to parse integer");
                return;
            }

            if (studentID == -1) {
                Toast.makeText(getApplicationContext(), "Select a student", Toast.LENGTH_SHORT).show();
                return;
            }

            DatabaseReference insert = db_ref.child("simpsons/grades/").push();
            Grade gradeobj = new Grade(courseid, coursename, grade, studentID);
            insert.setValue(gradeobj, new DatabaseReference.CompletionListener() {
                @Override
                public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                    if (databaseError == null) {
                        Toast.makeText(getApplicationContext(), "Successfully uploaded to database", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "Couldn't upload to database", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
            });

        }
    }
}
