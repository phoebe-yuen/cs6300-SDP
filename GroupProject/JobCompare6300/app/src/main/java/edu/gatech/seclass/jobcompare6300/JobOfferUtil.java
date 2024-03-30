package edu.gatech.seclass.jobcompare6300;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

// The "JobOfferUtil" Java activity class is associated with the "Job Offer Utility" UI
public class JobOfferUtil extends AppCompatActivity {

    // Initialize three buttons, nine text showing fields, and the job database
    Button addAnotherOffer;
    Button returnMainMenu;
    Button compareWithCurrent;
    TextView title, company, location, costOfLivingIndex, remoteWorkTime, yearlySalary, yearlyBonus, retirementBenefitsPercentage, leaveTime;
    JobDataBaseHelper jobDataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_offer_util);

        // Associate the buttons and text showing fields with corresponding UI elements
        addAnotherOffer = (Button)findViewById(R.id.JobOfferUtilAddJobOfferButton);
        returnMainMenu = (Button)findViewById(R.id.JobOfferUtilReturnMainMenuButton);
        compareWithCurrent = (Button)findViewById(R.id.JobOfferUtilCompareWithCurrentButton);
        title = (TextView)findViewById(R.id.JobOfferUtilTitle);
        company = (TextView)findViewById(R.id.JobOfferUtilCompany);
        location = (TextView)findViewById(R.id.JobOfferUtilLocation);
        costOfLivingIndex = (TextView)findViewById(R.id.JobOfferUtilCostOfLivingIndex);
        remoteWorkTime = (TextView)findViewById(R.id.JobOfferUtilRemoteWorkTime);
        yearlySalary = (TextView)findViewById(R.id.JobOfferUtilYearlySalary);
        yearlyBonus = (TextView)findViewById(R.id.JobOfferUtilYearlyBonus);
        retirementBenefitsPercentage = (TextView)findViewById(R.id.JobOfferUtilRetirementBenefitsPercentage);
        leaveTime = (TextView)findViewById(R.id.JobOfferUtilLeaveTime);

        // Open the job database
        jobDataBaseHelper = new JobDataBaseHelper(JobOfferUtil.this);

        // Disable the "Compare with Current Job" button if there is not a current job stored in the job table
        compareWithCurrent.setEnabled(jobDataBaseHelper.hasCurrentJob());

        // Catch the ID of the newly added job offer, find it in the job table, and display it on the screen
        int ID1 = getIntent().getIntExtra("ID1", 0);
        Job job1 = jobDataBaseHelper.findJobByID(ID1);
        title.setText(job1.getTitle());
        company.setText(job1.getCompany());
        location.setText(job1.getLocation());
        costOfLivingIndex.setText(String.valueOf(job1.getCostOfLivingIndex()));
        remoteWorkTime.setText(String.valueOf(job1.getRemoteWorkTime()));
        yearlySalary.setText(String.valueOf(job1.getYearlySalary()));
        yearlyBonus.setText(String.valueOf(job1.getYearlyBonus()));
        retirementBenefitsPercentage.setText(String.valueOf(job1.getRetirementBenefitsPercentage()));
        leaveTime.setText(String.valueOf(job1.getLeaveTime()));

        // Navigate to the "JobOffer" Java activity class when the "Add Another Offer" button is clicked
        addAnotherOffer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                Intent intent = new Intent(JobOfferUtil.this, JobOffer.class);
                startActivity(intent);
            }
        });

        // Navigate to the "MainMenu" Java activity class when the "Return to Main Menu" button is clicked
        returnMainMenu.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                Intent intent = new Intent(JobOfferUtil.this, MainMenu.class);
                startActivity(intent);
            }
        });

        // Navigate to the "ComparisonResult" Java activity class when the "Compare with Current Job" button is clicked
        compareWithCurrent.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                // Find the ID of the current job in the job table
                int ID2 = jobDataBaseHelper.findCurrentJobID();

                // Pass the ID of the job offer shown on the screen and the ID of the current job to the "ComparisonResult" class
                Intent intent = new Intent(JobOfferUtil.this, ComparisonResult.class);
                intent.putExtra("ID1", ID1);
                intent.putExtra("ID2", ID2);
                startActivity(intent);
            }
        });
    }
}