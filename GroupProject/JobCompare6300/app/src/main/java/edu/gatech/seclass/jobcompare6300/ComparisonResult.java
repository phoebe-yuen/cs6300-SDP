package edu.gatech.seclass.jobcompare6300;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

// The "ComparisonResult" Java activity class is associated with the "Comparison Result" UI
public class ComparisonResult extends AppCompatActivity {

    // Initialize two buttons, fourteen text showing fields, and the job database
    Button performAnotherComparison;
    Button returnMainMenu;
    TextView title1, title2, company1, company2, location1, location2, AYS1, AYS2, AYB1, AYB2, RBP1, RBP2, LT1, LT2;
    JobDataBaseHelper jobDataBaseHelper;

    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comparison_result);

        // Associate the buttons and text showing fields with corresponding UI elements
        performAnotherComparison = (Button)findViewById(R.id.ComparisonResultPerformAnotherComparisonButton);
        returnMainMenu = (Button)findViewById(R.id.ComparisonResultReturnMainMenuButton);
        title1 = (TextView)findViewById(R.id.ComparisonResultTitle1);
        title2 = (TextView)findViewById(R.id.ComparisonResultTitle2);
        company1 = (TextView)findViewById(R.id.ComparisonResultCompany1);
        company2 = (TextView)findViewById(R.id.ComparisonResultCompany2);
        location1 = (TextView)findViewById(R.id.ComparisonResultLocation1);
        location2 = (TextView)findViewById(R.id.ComparisonResultLocation2);
        AYS1 = (TextView)findViewById(R.id.ComparisonResultAdjustedYearlySalary1);
        AYS2 = (TextView)findViewById(R.id.ComparisonResultAdjustedYearlySalary2);
        AYB1 = (TextView)findViewById(R.id.ComparisonResultAdjustedYearlyBonus1);
        AYB2 = (TextView)findViewById(R.id.ComparisonResultAdjustedYearlyBonus2);
        RBP1 = (TextView)findViewById(R.id.ComparisonResultRetirementBenefitsPercentage1);
        RBP2 = (TextView)findViewById(R.id.ComparisonResultRetirementBenefitsPercentage2);
        LT1 = (TextView)findViewById(R.id.ComparisonResultLeaveTime1);
        LT2 = (TextView)findViewById(R.id.ComparisonResultLeaveTime2);

        // Open the job database
        jobDataBaseHelper = new JobDataBaseHelper(ComparisonResult.this);

        // Catch the IDs of two jobs, and retrieve them from the job table
        int ID1 = getIntent().getIntExtra("ID1", 0);
        int ID2 = getIntent().getIntExtra("ID2", 0);
        Job job1 = jobDataBaseHelper.findJobByID(ID1);
        Job job2 = jobDataBaseHelper.findJobByID(ID2);

        // Display the job details of the two retrieved jobs on the screen
        title1.setText(job1.getTitle());
        title2.setText(job2.getTitle());
        company1.setText(job1.getCompany());
        company2.setText(job2.getCompany());
        location1.setText(job1.getLocation());
        location2.setText(job2.getLocation());
        AYS1.setText(String.format("%.2f", job1.getAdjustedYearlySalary()));
        AYS2.setText(String.format("%.2f", job2.getAdjustedYearlySalary()));
        AYB1.setText(String.format("%.2f", job1.getAdjustedYearlyBonus()));
        AYB2.setText(String.format("%.2f", job2.getAdjustedYearlyBonus()));
        RBP1.setText(String.valueOf(job1.getRetirementBenefitsPercentage()));
        RBP2.setText(String.valueOf(job2.getRetirementBenefitsPercentage()));
        LT1.setText(String.valueOf(job1.getLeaveTime()));
        LT2.setText(String.valueOf(job2.getLeaveTime()));

        // Navigate to the "CompareJobs" Java activity class when the "Perform Another Comparison" button is clicked
        performAnotherComparison.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                Intent intent = new Intent(ComparisonResult.this, CompareJobs.class);
                startActivity(intent);
            }
        });

        // Navigate to the "MainMenu" Java activity class when the "Return to Main Menu" button is clicked
        returnMainMenu.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                Intent intent = new Intent(ComparisonResult.this, MainMenu.class);
                startActivity(intent);
            }
        });
    }
}