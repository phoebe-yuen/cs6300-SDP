package edu.gatech.seclass.jobcompare6300;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

// The "MainMenu" Java activity class is associated with the "Main Menu" UI
public class MainMenu extends AppCompatActivity {

    // Initialize four buttons and two databases
    Button currentJobButton;
    Button jobOfferButton;
    Button comparisonSettingsButton;
    Button compareJobsButton;
    WeightDataBaseHelper weightDataBaseHelper;
    JobDataBaseHelper jobDataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        // Associate buttons with corresponding UI elements
        currentJobButton = (Button)findViewById(R.id.MainMenuCurrentJobButton);
        jobOfferButton = (Button)findViewById(R.id.MainMenuJobOfferButton);
        comparisonSettingsButton = (Button)findViewById(R.id.MainMenuComparisonSettingsButton);
        compareJobsButton = (Button)findViewById(R.id.MainMenuCompareJobsButton);

        // Open the weight and the job databases
        weightDataBaseHelper = new WeightDataBaseHelper(MainMenu.this);
        jobDataBaseHelper = new JobDataBaseHelper(MainMenu.this);

        // Initialize the weight table if it does not exist
        if (weightDataBaseHelper.checkEmpty()) {
            Weight weight = new Weight(-1, 1, 1, 1, 1, 1);
            weightDataBaseHelper.addWeight(weight);
        }

        // Disable the "Compare Jobs" button if the job table has less than 2 entries
        compareJobsButton.setEnabled(jobDataBaseHelper.hasJobs());

        // Navigate to the "CurrentJob" Java activity class when the "Current Job" button is clicked
        currentJobButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                Intent intent = new Intent(MainMenu.this, CurrentJob.class);
                startActivity(intent);
            }
        });

        // Navigate to the "JobOffer" Java activity class when the "Job Offer" button is clicked
        jobOfferButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                Intent intent = new Intent(MainMenu.this, JobOffer.class);
                startActivity(intent);
            }
        });

        // Navigate to the "ComparisonSettings" Java activity class when the "Comparison Settings" button is clicked
        comparisonSettingsButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                Intent intent = new Intent(MainMenu.this, ComparisonSettings.class);
                startActivity(intent);
            }
        });

        // Navigate to the "CompareJobs" Java activity class when the "Compare Jobs" button is clicked
        compareJobsButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                Intent intent = new Intent(MainMenu.this, CompareJobs.class);
                startActivity(intent);
            }
        });
    }
}