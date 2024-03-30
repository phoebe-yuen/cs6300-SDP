package edu.gatech.seclass.sdpencryptor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText plaintextID;
    private EditText alphaKeyID;
    private EditText betaKeyID;
    private TextView ciphertextID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        plaintextID = (EditText)findViewById(R.id.plaintextID);
        alphaKeyID = (EditText)findViewById(R.id.alphaKeyID);
        betaKeyID = (EditText)findViewById(R.id.betaKeyID);
        ciphertextID = (TextView)findViewById(R.id.ciphertextID);

    }

    public void handleClick(View view){
        switch (view.getId()){
            case R.id.encipherButtonID:
                String plaintext = plaintextID.getText().toString();
                boolean isInputError = false;
                if (plaintext.trim().isEmpty()){
                    plaintextID.setError("Invalid Plaintext");
                    isInputError = true;
                }
                int alphaKey = 0;
                try {
                    alphaKey = Integer.parseInt(alphaKeyID.getText().toString().trim());
                    if (!(alphaKey >=1 && alphaKey <= 25 && alphaKey != 13 && (alphaKey %2) == 1)){
                        alphaKeyID.setError("Invalid Alpha Key");
                        isInputError = true;
                    }
                }
                catch (Exception e){
                    alphaKeyID.setError("Invalid Alpha Key");
                    isInputError = true;
                }
                int betaKey =0;
                try {
                    betaKey = Integer.parseInt(betaKeyID.getText().toString().trim());
                    if (!(betaKey >=1 && betaKey < 26)){
                        betaKeyID.setError("Invalid Beta Key");
                        isInputError = true;
                    }
                }
                catch (Exception e){
                    betaKeyID.setError("Invalid Beta Key");
                    isInputError = true;
                }
                String output = "";
                try {
                    output = calculateEncipher(plaintext,alphaKey,betaKey);
                }
                catch (Exception e){
                    isInputError = true;
                }
                if (!isInputError){
                    ciphertextID.setText(output);
                }
                else {
                    ciphertextID.setText("");

                }

        }
    }

    public String calculateEncipher(String plaintext, int alphaKey, int betaKey){
        StringBuilder output = new StringBuilder();
        boolean containLetter = false;
        for (int i = 0; i< plaintext.length(); i++){
            char tempChar = plaintext.charAt(i);
            int tempAscii = (int) tempChar;
            if (tempAscii >=65 && tempAscii <=90){
                containLetter = true;
                int encryptedValue = (alphaKey * (tempAscii - 65) + betaKey) % 26;
                tempAscii = encryptedValue + 65;
            }
            if (tempAscii >=97 && tempAscii <=122){
                containLetter = true;
                int encryptedValue = (alphaKey * (tempAscii - 97) + betaKey) % 26;
                tempAscii = encryptedValue + 97;
            }
            char outputChar = (char)tempAscii;
            output.append(outputChar);
        }
        if (!containLetter) {
            plaintextID.setError("Invalid Plaintext");
            return "";
        }
        return output.toString();
    }
}