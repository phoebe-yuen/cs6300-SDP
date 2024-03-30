package edu.gatech.seclass.jobcompare6300;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

// The "ComparisonSettings" Java activity class is associated with the "Comparison Settings" UI
public class ComparisonSettings extends AppCompatActivity {

    // Initialize two buttons, five text editing fields, and the weight database
    Button saveButton;
    Button cancelButton;
    EditText AYS, AYB, RBP, LT, RWT;
    WeightDataBaseHelper weightDataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comparison_settings);

        // Associate the buttons and text editing fields with corresponding UI elements
        saveButton = (Button)findViewById(R.id.ComparisonSettingsSaveButton);
        cancelButton = (Button)findViewById(R.id.ComparisonSettingsCancelButton);
        AYS = (EditText)findViewById(R.id.ComparisonSettingsAYSWeight);
        AYB = (EditText)findViewById(R.id.ComparisonSettingsAYBWeight);
        RBP = (EditText)findViewById(R.id.ComparisonSettingsRBPWeight);
        LT = (EditText)findViewById(R.id.ComparisonSettingsLTWeight);
        RWT = (EditText)findViewById(R.id.ComparisonSettingsRWTWeight);

        // Open the weight database, retrieve the weight values from the weight table, and display them on screen
        weightDataBaseHelper = new WeightDataBaseHelper(ComparisonSettings.this);
        Weight weight = weightDataBaseHelper.getWeight();
        AYS.setText(String.valueOf(weight.getAYSWeight()));
        AYB.setText(String.valueOf(weight.getAYBWeight()));
        RBP.setText(String.valueOf(weight.getRBPWeight()));
        LT.setText(String.valueOf(weight.getLTWeight()));
        RWT.setText(String.valueOf(weight.getRWTWeight()));

        // Update the weights in the weight database when the "Save" button is clicked
        saveButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                // Collect new weights in the text editing fields with error handling
                int newAYS = 0;
                boolean AYSFlag = false;
                if (AYS.getText().toString().length() == 0) {
                    AYS.setError("The AYS Weight field is empty!");
                }
                else {
                    newAYS = Integer.parseInt(AYS.getText().toString());
                    if (newAYS == 0) {
                        AYS.setError("The AYS Weight must be a positive integer!");
                    }
                    else {
                        AYSFlag = true;
                    }
                }

                int newAYB = 0;
                boolean AYBFlag = false;
                if (AYB.getText().toString().length() == 0) {
                    AYB.setError("The AYB Weight field is empty!");
                }
                else {
                    newAYB = Integer.parseInt(AYB.getText().toString());
                    if (newAYB == 0) {
                        AYB.setError("The AYB Weight must be a positive integer!");
                    }
                    else {
                        AYBFlag = true;
                    }
                }

                int newRBP = 0;
                boolean RBPFlag = false;
                if (RBP.getText().toString().length() == 0) {
                    RBP.setError("The RBP Weight field is empty!");
                }
                else {
                    newRBP = Integer.parseInt(RBP.getText().toString());
                    if (newRBP == 0) {
                        RBP.setError("The RBP Weight must be a positive integer!");
                    }
                    else {
                        RBPFlag = true;
                    }
                }

                int newLT = 0;
                boolean LTFlag = false;
                if (LT.getText().toString().length() == 0) {
                    LT.setError("The LT Weight field is empty!");
                }
                else {
                    newLT = Integer.parseInt(LT.getText().toString());
                    if (newLT == 0) {
                        LT.setError("The LT Weight must be a positive integer!");
                    }
                    else {
                        LTFlag = true;
                    }
                }

                int newRWT = 0;
                boolean RWTFlag = false;
                if (RWT.getText().toString().length() == 0) {
                    RWT.setError("The RWT Weight field is empty!");
                }
                else {
                    newRWT = Integer.parseInt(RWT.getText().toString());
                    if (newRWT == 0) {
                        RWT.setError("The RWT Weight must be a positive integer!");
                    }
                    else {
                        RWTFlag = true;
                    }
                }

                if (AYSFlag && AYBFlag && RBPFlag && LTFlag && RWTFlag) {
                    // Update the weights stored in the weight database and toast a message
                    weightDataBaseHelper.updateWeight(new Weight(1, newAYS, newAYB, newRBP, newLT, newRWT));
                    Toast.makeText(ComparisonSettings.this, "Weights updated successfully!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Navigate to the "MainMenu" Java activity class when the "Cancel" button is clicked
        cancelButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                Intent intent = new Intent(ComparisonSettings.this, MainMenu.class);
                startActivity(intent);
            }
        });
    }
}