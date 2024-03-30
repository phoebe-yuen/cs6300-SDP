package edu.gatech.seclass.jobcompare6300;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

// The "JobOffer" Java activity class is associated with the "Job Offer" UI
public class JobOffer extends AppCompatActivity {

    // Initialize two buttons, nine text editing fields, the job database, and a Job class variable
    Button saveButton;
    Button cancelButton;
    EditText title, company, location, costOfLivingIndex, remoteWorkTime, yearlySalary, yearlyBonus, retirementBenefitsPercentage, leaveTime;
    JobDataBaseHelper jobDataBaseHelper;
    Job newOffer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_offer);

        // Associate the buttons and text editing fields with corresponding UI elements
        title = (EditText)findViewById(R.id.JobOfferTitle);
        company = (EditText)findViewById(R.id.JobOfferCompany);
        location = (EditText)findViewById(R.id.JobOfferLocation);
        costOfLivingIndex = (EditText)findViewById(R.id.JobOfferCostOfLivingIndex);
        remoteWorkTime = (EditText)findViewById(R.id.JobOfferRemoteWorkTime);
        yearlySalary = (EditText)findViewById(R.id.JobOfferYearlySalary);
        yearlyBonus = (EditText)findViewById(R.id.JobOfferYearlyBonus);
        retirementBenefitsPercentage = (EditText)findViewById(R.id.JobOfferRetirementBenefitsPercentage);
        leaveTime = (EditText)findViewById(R.id.JobOfferLeaveTime);
        saveButton = (Button)findViewById(R.id.JobOfferSaveButton);
        cancelButton = (Button)findViewById(R.id.JobOfferCancelButton);

        // Open the job database
        jobDataBaseHelper = new JobDataBaseHelper(JobOffer.this);

        // Navigate to the "JobOfferUtil" Java activity class when the "Save" button is clicked
        saveButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                // Collect current values in the text editing fields with error handling
                String newTitle = "";
                boolean titleFlag = false;
                if (title.getText().toString().length() == 0) {
                    title.setError("The title field is empty!");
                }
                else {
                    newTitle = title.getText().toString();
                    titleFlag = true;
                }

                String newCompany = "";
                boolean companyFlag = false;
                if (company.getText().toString().length() == 0) {
                    company.setError("The company field is empty!");
                }
                else {
                    newCompany = company.getText().toString();
                    companyFlag = true;
                }

                String newLocation = "";
                boolean locationFlag = false;
                if (location.getText().toString().length() == 0) {
                    location.setError("The location field is empty!");
                }
                else {
                    newLocation = location.getText().toString();
                    locationFlag = true;
                }

                int newCostOfLivingIndex = 0;
                boolean costOfLivingIndexFlag = false;
                if (costOfLivingIndex.getText().toString().length() == 0) {
                    costOfLivingIndex.setError("The cost of living index field is empty!");
                }
                else {
                    newCostOfLivingIndex = Integer.parseInt(costOfLivingIndex.getText().toString());
                    if (newCostOfLivingIndex == 0) {
                        costOfLivingIndex.setError("The cost of living index must be a positive integer!");
                    }
                    else {
                        costOfLivingIndexFlag = true;
                    }
                }

                int newRemoteWorkTime = 0;
                boolean remoteWorkTimeFlag = false;
                if (remoteWorkTime.getText().toString().length() == 0) {
                    remoteWorkTime.setError("The remote work time field is empty!");
                }
                else {
                    newRemoteWorkTime = Integer.parseInt(remoteWorkTime.getText().toString());
                    if (newRemoteWorkTime < 1 || newRemoteWorkTime > 5) {
                        remoteWorkTime.setError("Remote work time must be an integer from 1 to 5!");
                    }
                    else {
                        remoteWorkTimeFlag = true;
                    }
                }

                double newYearlySalary = 0.0;
                boolean yearlySalaryFlag = false;
                if (yearlySalary.getText().toString().length() == 0) {
                    yearlySalary.setError("The yearly salary field is empty!");
                }
                else {
                    newYearlySalary = Double.parseDouble(yearlySalary.getText().toString());
                    yearlySalaryFlag = true;
                }

                double newYearlyBonus = 0.0;
                boolean yearlyBonusFlag = false;
                if (yearlyBonus.getText().toString().length() == 0) {
                    yearlyBonus.setError("The yearly bonus field is empty!");
                }
                else {
                    newYearlyBonus = Double.parseDouble(yearlyBonus.getText().toString());
                    yearlyBonusFlag = true;
                }

                double newRetirementBenefitsPercentage = 0.0;
                boolean retirementBenefitsPercentageFlag = false;
                if (retirementBenefitsPercentage.getText().toString().length() == 0) {
                    retirementBenefitsPercentage.setError("The retirement benefits field is empty!");
                }
                else {
                    newRetirementBenefitsPercentage = Double.parseDouble(retirementBenefitsPercentage.getText().toString());
                    if (newRetirementBenefitsPercentage > 1.0) {
                        retirementBenefitsPercentage.setError("The retirement benefits should be a decimal numeral between 0 and 1!");
                    }
                    else {
                        retirementBenefitsPercentageFlag = true;
                    }
                }

                int newLeaveTime = 0;
                boolean leaveTimeFlag = false;
                if (leaveTime.getText().toString().length() == 0) {
                    leaveTime.setError("The leave time field is empty!");
                }
                else {
                    newLeaveTime = Integer.parseInt(leaveTime.getText().toString());
                    if (newLeaveTime > 365) {
                        leaveTime.setError("The leave time must be an integer less or equal to 365!");
                    }
                    else {
                        leaveTimeFlag = true;
                    }
                }

                if (titleFlag && companyFlag && locationFlag && costOfLivingIndexFlag && yearlySalaryFlag && yearlyBonusFlag && remoteWorkTimeFlag && retirementBenefitsPercentageFlag && leaveTimeFlag) {
                    // Create a new job that associates with the values in the text editing fields, and get it's ID in the job database
                    newOffer = new Job(-1, newTitle, newCompany, newLocation, newCostOfLivingIndex, newYearlySalary, newYearlyBonus, newRemoteWorkTime, newRetirementBenefitsPercentage, newLeaveTime, 0);
                    int newOfferID = jobDataBaseHelper.addJob(newOffer);

                    // Pass the ID of the job offer just saved to the "JobOfferUtil" class
                    Intent intent = new Intent(JobOffer.this, JobOfferUtil.class);
                    intent.putExtra("ID1", newOfferID);
                    startActivity(intent);
                }
            }
        });

        // Navigate to the "MainMenu" Java activity class when the "Cancel" button is clicked
        cancelButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                Intent intent = new Intent(JobOffer.this, MainMenu.class);
                startActivity(intent);
            }
        });
    }
}