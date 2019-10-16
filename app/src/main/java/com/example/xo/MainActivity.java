package com.example.xo;

import android.support.annotation.RestrictTo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //state 0 for empty
    //state 1 for player 1
    //state 2 for player 2

    int scorex, scoreo = 0;

    int[] gameState = {0, 0, 0, 0, 0, 0, 0, 0, 0};

    int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

    int activePlayer = 1;

    boolean gameActive = true;


    public void apper(View view) {

        ImageView x = (ImageView) view;
        Button btn = findViewById(R.id.button);
        TextView score = findViewById(R.id.TextView);


        int tappedtag = Integer.parseInt(x.getTag().toString());


        if (gameState[tappedtag] == 0 && gameActive) {
            gameState[tappedtag] = activePlayer;

            if (activePlayer == 1) {
                x.setImageResource(R.drawable.xx);
                activePlayer = 2;
            } else {
                x.setImageResource(R.drawable.oo);
                activePlayer = 1;
            }


            for (int[] wp : winningPositions) {
                if (gameState[wp[0]] == gameState[wp[1]] && gameState[wp[1]] == gameState[wp[2]] && gameState[wp[0]] != 0) {

                    gameActive = false;

                    String winner;

                    if (activePlayer == 2) {
                        winner = "X";
                        scorex++;
                    } else {
                        winner = "O";
                        scoreo++;
                    }
                    Toast.makeText(this, winner + "has won the game!", Toast.LENGTH_SHORT).show();


                    score.setText("X score is : " + scorex + "           " + "O score is : " + scoreo);



                    btn.setVisibility(View.VISIBLE);
                    score.setVisibility(View.VISIBLE);
                }

                boolean check = true;

                for (int i =0;i<gameState.length;i++){
                    if (gameState[i] == 0){
                        check = false;
                    }
                }
                if (check && gameActive){
                    btn.setVisibility(View.VISIBLE);
                    score.setText("DRAW!");
                    score.setVisibility(View.VISIBLE);
                }



            }



        }

        }




    public void NewGame(View view) {

        Button btn = findViewById(R.id.button);
        TextView score = findViewById(R.id.TextView);
        btn.setVisibility(View.INVISIBLE);
        score.setVisibility(View.INVISIBLE);

        GridLayout gridLayout = findViewById(R.id.gridLayout2);


        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            ImageView image = (ImageView) gridLayout.getChildAt(i);
            image.setImageDrawable(null);
        }


        for (int i = 0; i < gameState.length; i++) {
            gameState[i] = 0;
        }


        activePlayer = 1;

        gameActive = true;


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
