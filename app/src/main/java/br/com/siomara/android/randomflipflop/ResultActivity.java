package br.com.siomara.android.randomflipflop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        ImageView imgGameResult = findViewById(R.id.imageViewGameResult);
        ImageView imgUserChoice = findViewById(R.id.imageViewUserChoice);
        ImageView imgPlayAgain = findViewById(R.id.imageViewPlayAgain);
        TextView txtGameMessage = findViewById(R.id.textViewGameMessage);

        // Retrieves stored parameters and process result.
        Bundle extra = getIntent().getExtras();
        if (extra != null) {
            // Retrieves random game result.
            String coinFace = extra.getString("coinFace");
            Log.d("5 ===> RANDOM FLIP FLOP", "coinFace = " + coinFace);

            // Retrieves user's choice.
            String userChoice = extra.getString("userChoice");
            Log.d("6 ===> RANDOM FLIP FLOP", "userChoice = " + userChoice);

            // Compares values to display game's result. The == operator checks to see if two objects
            // are exactly the same object. Two strings may be different objects, but have the same
            // value (have exactly the same characters in them). Use the .equals() method to compare
            // strings for equality. Prefers .equalsIgnoreCase() in this lab.

            // Displays random coin face.
            if (coinFace.equalsIgnoreCase("heads")) {
                imgGameResult.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.heads));
            } else {
                imgGameResult.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.tails));
            }

            // Displays user's choice.
            if (userChoice.equalsIgnoreCase("heads")) {
                imgUserChoice.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.heads));
            } else {
                imgUserChoice.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.tails));
            }

            // Displays if user won or lost.
            if (coinFace.equalsIgnoreCase(userChoice)) {
                txtGameMessage.setText(R.string.you_won);
            } else {
                txtGameMessage.setText(R.string.you_lost);
            }
        }

        // Button that allows user to play again.
        // TODO: implement an exit button.
        imgPlayAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ResultActivity.this, MainActivity.class));
            }
        });
    }

}