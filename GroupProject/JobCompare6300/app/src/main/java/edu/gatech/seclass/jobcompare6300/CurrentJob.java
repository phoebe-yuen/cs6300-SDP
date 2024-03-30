package edu.gatech.seclass.jobcompare6300;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

// The "CurrentJob" Java activity class is associated with the "Current Job" UI
public class CurrentJob extends AppCompatActivity {

    // Initialize two buttons, nine text editing fields, the job database, and two Job class variables
    Button saveButton;
    Button cancelButton;
    EditText title, company, location, costOfLivingIndex, remoteWorkTime, yearlySalary, yearlyBonus, retirementBenefitsPercentage, leaveTime;
    JobDataBaseHelper jobDataBaseHelper;
    Job job, newJob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_job);

        // Associate the buttons and text editing fields with corresponding UI elements
        saveButton = (Button)findViewById(R.id.CurrentJobSaveButton);
        cancelButton = (Button)findViewById(R.id.CurrentJobCancelButton);
        title = (EditText)findViewById(R.id.CurrentJobTitle);
        company = (EditText)findViewById(R.id.CurrentJobCompany);
        location = (EditText)findViewById(R.id.CurrentJobLocation);
        costOfLivingIndex = (EditText)findViewById(R.id.CurrentJobCostOfLivingIndex);
        remoteWorkTime = (EditText)findViewById(R.id.CurrentJobRemoteWorkTime);
        yearlySalary = (EditText)findViewById(R.id.CurrentJobYearlySalary);
        yearlyBonus = (EditText)findViewById(R.id.CurrentJobYearlyBonus);
        retirementBenefitsPercentage = (EditText)findViewById(R.id.CurrentJobRetirementBenefitsPercentage);
        leaveTime = (EditText)findViewById(R.id.CurrentJobLeaveTime);

        // Open the job database
        jobDataBaseHelper = new JobDataBaseHelper(CurrentJob.this);

        // Display current job details in the text editing fields if there is a current job stored in the job table
        if (jobDataBaseHelper.hasCurrentJob()) {
            job = jobDataBaseHelper.findCurrentJob();
            title.setText(job.getTitle());
            company.setText(job.getCompany());
            location.setText(job.getLocation());
            costOfLivingIndex.setText(String.valueOf(job.getCostOfLivingIndex()));
            remoteWorkTime.setText(String.valueOf(job.getRemoteWorkTime()));
            yearlySalary.setText(String.valueOf(job.getYearlySalary()));
            yearlyBonus.setText(String.valueOf(job.getYearlyBonus()));
            retirementBenefitsPercentage.setText(String.valueOf(job.getRetirementBenefitsPercentage()));
            leaveTime.setText(String.valueOf(job.getLeaveTime()));
        }

        // Navigate to the "MainMenu" Java activity class when the "Save" button is clicked
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
                    // Create a new job that associates with the values in the text editing fields
                    newJob = new Job(-1, newTitle, newCompany, newLocation, newCostOfLivingIndex, newYearlySalary, newYearlyBonus, newRemoteWorkTime, newRetirementBenefitsPercentage, newLeaveTime, 1);

                    // Update the current job details in the job table if there is a current job or add them as a new current job otherwise
                    if (jobDataBaseHelper.hasCurrentJob()) {
                        jobDataBaseHelper.updateCurrentJob(newJob);
                    }
                    else {
                        jobDataBaseHelper.addJob(newJob);
                    }

                    Intent intent = new Intent(CurrentJob.this, MainMenu.class);
                    startActivity(intent);
                }
            }
        });

        // Navigate to the "MainMenu" Java activity class when the "Cancel" button is clicked
        cancelButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                Intent intent = new Intent(CurrentJob.this, MainMenu.class);
                startActivity(intent);
            }
        });
    }
}