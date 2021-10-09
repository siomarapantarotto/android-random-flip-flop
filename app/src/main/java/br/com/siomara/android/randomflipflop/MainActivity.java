package br.com.siomara.android.randomflipflop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private final String[] coinFaces = {"HEADS", "TAILS"};
    private RadioGroup radioGroupHeadsOrTails;
    private RadioButton radioButtonUserChoice;
    private ImageView imageClickToPlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeComponents();

        // Toast which coin face user has checked.
        radioGroupHeadsOrTails.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                // Finds which radio button is selected.
                if (checkedId == R.id.radioButtonHeads) {
                    Toast.makeText(getApplicationContext(), R.string.you_have_chosen_heads,
                            Toast.LENGTH_LONG).show();
                } else if (checkedId == R.id.radioButtonTails) {
                    Toast.makeText(getApplicationContext(), R.string.you_have_chosen_tails,
                            Toast.LENGTH_LONG).show();
                }
            }
        });

        // User made a choice and is ready to play. Check the log to understand whats is going on.
        imageClickToPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Identifies, stores user's selection and open activity with game result.
                int idCheckedRadioButton = radioGroupHeadsOrTails.getCheckedRadioButtonId();
                if (idCheckedRadioButton > 0) {
                    radioButtonUserChoice = findViewById(idCheckedRadioButton);
                    Log.d("1 ===> RANDOM FLIP FLOP", "radioButtonSelected.getText() = " + radioButtonUserChoice.getText());

                    // Generates random number between 0 (HEADS) and 1 (TAILS) to play against the user.
                    Random random = new Random();
                    int randomNumber = random.nextInt(coinFaces.length);
                    Log.d("2 ===> RANDOM FLIP FLOP", "randomNumber = " + randomNumber);

                    // Stores parameters to be passed between activities.
                    Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                    intent.putExtra("coinFace", coinFaces[randomNumber]);
                    Log.d("3 ===> RANDOM FLIP FLOP", "coinFaces[randomNumber] = " + coinFaces[randomNumber]);

                    // Redirects user to the result activity.
                    intent.putExtra("userChoice", radioButtonUserChoice.getText());
                    Log.d("4 ===> RANDOM FLIP FLOP", "radioButtonSelected.getText() = " + radioButtonUserChoice.getText());
                    startActivity(intent);
                } else {
                    // Toast that user must pick either HEADS or TAILS.
                    Toast.makeText(getApplicationContext(), R.string.nothing_has_been_selected,
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // Initializes/connects GUI components.
    public void initializeComponents() {
        imageClickToPlay = findViewById(R.id.imageViewClickToPlay);
        radioGroupHeadsOrTails = findViewById(R.id.radioGroupHeadTail);
    }
}