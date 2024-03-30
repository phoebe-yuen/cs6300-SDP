package edu.gatech.seclass.jobcompare6300;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;

// The "CompareJobs" Java activity class is associated with the "Compare Jobs" UI
public class CompareJobs extends AppCompatActivity {

    // Initialize two buttons, one list view, two databases, one Job class variable, an array adapter, and an array list of the String type
    Button compareButton;
    Button cancelButton;
    ListView jobListView;
    WeightDataBaseHelper weightDataBaseHelper;
    JobDataBaseHelper jobDataBaseHelper;
    Job job;
    ArrayAdapter jobArrayAdapter;
    ArrayList<String> selectedItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compare_jobs);

        // Associate the buttons and list view with corresponding UI elements
        compareButton = (Button)findViewById(R.id.CompareJobsCompareButton);
        cancelButton = (Button)findViewById(R.id.CompareJobsCancelButton);
        jobListView = (ListView)findViewById(R.id.CompareJobsListView);

        // Set the choice mode of the list view
        jobListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        // Open the weight database and computer the actual weights used in the job score calculation
        weightDataBaseHelper = new WeightDataBaseHelper(CompareJobs.this);
        Weight weight = weightDataBaseHelper.getWeight();
        int total = weight.getAYSWeight() + weight.getAYBWeight() + weight.getRBPWeight() + weight.getLTWeight() + weight.getRWTWeight();
        double AYSWeight = (double) weight.getAYSWeight() / (double) total;
        double AYBWeight = (double) weight.getAYBWeight() / (double) total;
        double RBPWeight = (double) weight.getRBPWeight() / (double) total;
        double LTWeight = (double) weight.getLTWeight() / (double) total;
        double RWTWeight = (double) weight.getRWTWeight() / (double) total;

        // Open the job database and retrieve all the entries in a job list, while calculating each job's score
        jobDataBaseHelper = new JobDataBaseHelper(CompareJobs.this);
        List<Job> jobList = jobDataBaseHelper.getJobs();
        int count = 0;
        while (jobList.size() > count) {
            job = jobList.get(count);
            double AYS = job.getAdjustedYearlySalary();
            double AYB = job.getAdjustedYearlyBonus();
            double RBP = job.getRetirementBenefitsPercentage();
            double LT = job.getLeaveTime();
            double RWT = job.getRemoteWorkTime();
            job.setJobScore(AYSWeight * AYS + AYBWeight * AYB + RBPWeight * (RBP*AYS) + LTWeight * (LT*AYS/260) - RWTWeight * ((260-52*RWT)*(AYS/260)/8));
            count++;
        }

        // Sort the job list in descending order of job score
        jobList.sort(new JobScoreSorter().reversed());

        // Display the job list on screen with checkbox
        selectedItems = new ArrayList<>();
        compareButton.setEnabled(false);
        jobArrayAdapter = new ArrayAdapter<Job>(CompareJobs.this, R.layout.rowlayout, R.id.txt_lan, jobList);
        jobListView.setAdapter(jobArrayAdapter);
        jobListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = String.valueOf(position);
                if (selectedItems.contains(selectedItem)) {
                    selectedItems.remove(selectedItem);
                }
                else {
                    selectedItems.add(selectedItem);
                }

                // Enable the "Compare" button only if there are exactly two items selected
                compareButton.setEnabled(selectedItems.size() == 2);
            }
        });

        // Navigate to the "ComparisonResult" Java activity class when the "Compare" button is clicked
        compareButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                // Collect the IDs of the two selected items and pass them to the "ComparisonResult" class
                int pos1 = Integer.parseInt(selectedItems.get(0));
                int pos2 = Integer.parseInt(selectedItems.get(1));
                int ID1 = jobList.get(pos1).getID();
                int ID2 = jobList.get(pos2).getID();
                Intent intent = new Intent(CompareJobs.this, ComparisonResult.class);
                intent.putExtra("ID1", ID1);
                intent.putExtra("ID2", ID2);
                startActivity(intent);
            }
        });

        // Navigate to the "MainMenu" Java activity class when the "Cancel" button is clicked
        cancelButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                Intent intent = new Intent(CompareJobs.this, MainMenu.class);
                startActivity(intent);
            }
        });
    }
}