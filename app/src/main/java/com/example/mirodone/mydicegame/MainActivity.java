package com.example.mirodone.mydicegame;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ImageView view_dice_p1, view_dice_p2, imgView_livesp1, imgView_livesp2;
    TextView text_player_1, text_player_2;

    Random mRandom;

    int livesP1, livesP2;
    int rolledP1, rolledP2;

    Animation mAnimation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRandom = new Random();
        mAnimation = AnimationUtils.loadAnimation(this, R.anim.rotate);

        view_dice_p1 = findViewById(R.id.view_dice_p1);
        view_dice_p2 = findViewById(R.id.view_dice_p2);

        imgView_livesp1 = findViewById(R.id.imgView_livesp1);
        imgView_livesp2 = findViewById(R.id.imgView_livesp2);

        text_player_1 = findViewById(R.id.tv_p1);
        text_player_2 = findViewById(R.id.tv_p2);


        text_player_1.setText(R.string.text_p1_roll);
        text_player_2.setText(R.string.text_p2_roll);

        livesP1 = 6;
        livesP2 = 6;

        setDiceImage(livesP1, imgView_livesp1);
        setDiceImage(livesP2, imgView_livesp2);


        view_dice_p1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//roll the dice
                rolledP1 = mRandom.nextInt(6) + 1;
                setDiceImage(rolledP1, view_dice_p1);

                //see if the other player rolled the dice
                if (rolledP2 != 0) {
                    text_player_1.setText(R.string.text_p1_roll);
                    text_player_2.setText(R.string.text_p2_roll);

//calculate the winner
                    if (rolledP1 > rolledP2) {
                        livesP2--;
                        setDiceImage(livesP2, imgView_livesp2);

                        Toast.makeText(MainActivity.this, "Player 1 Wins!", Toast.LENGTH_SHORT).show();

                    }
                    if (rolledP2 > rolledP1) {
                        livesP1--;
                        setDiceImage(livesP1, imgView_livesp1);

                        Toast.makeText(MainActivity.this, "Player 2 Wins!", Toast.LENGTH_SHORT).show();
                    }
                    if (rolledP2 == rolledP1) {

                        Toast.makeText(MainActivity.this, "Draw !", Toast.LENGTH_SHORT).show();
                    }

                    rolledP1 = 0;
                    rolledP2 = 0;

                    view_dice_p1.setEnabled(true);
                    view_dice_p2.setEnabled(true);

                    //check method - player with 0 lives
                    checkEndGame();

                } else {

                    text_player_1.setText(R.string.text_p1_rolled);

                }
            }
        });

        view_dice_p2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                rolledP2 = mRandom.nextInt(6) + 1;
                setDiceImage(rolledP2, view_dice_p2);

                if (rolledP1 != 0) {
                    text_player_1.setText(R.string.text_p1_roll);
                    text_player_2.setText(R.string.text_p2_roll);


                    if (rolledP1 > rolledP2) {
                        livesP2--;
                        setDiceImage(livesP2, imgView_livesp2);
                        Toast.makeText(MainActivity.this, "Player 1 Wins!", Toast.LENGTH_SHORT).show();
                    }
                    if ((rolledP2 > rolledP1)) {
                        livesP1--;
                        setDiceImage(livesP1, imgView_livesp1);
                        Toast.makeText(MainActivity.this, "Player 2 Wins!", Toast.LENGTH_SHORT).show();
                    }
                    if (rolledP2 == rolledP1) {

                        Toast.makeText(MainActivity.this, "Draw !", Toast.LENGTH_SHORT).show();
                    }

                    rolledP1 = 0;
                    rolledP2 = 0;

                    view_dice_p1.setEnabled(true);
                    view_dice_p2.setEnabled(true);

                    checkEndGame();
                } else {

                    text_player_2.setText(R.string.text_p2_rolled);
                    view_dice_p2.setEnabled(false);

                }

            }
        });

    }

    private void setDiceImage(int dice, ImageView image) {

        switch (dice) {
            case 1:
                image.setImageResource(R.drawable.dice_1);
                image.startAnimation(mAnimation);
                break;
            case 2:
                image.setImageResource(R.drawable.dice_2);
                image.startAnimation(mAnimation);
                break;
            case 3:
                image.setImageResource(R.drawable.dice_3);
                image.startAnimation(mAnimation);
                break;
            case 4:
                image.setImageResource(R.drawable.dice_4);
                image.startAnimation(mAnimation);
                break;
            case 5:
                image.setImageResource(R.drawable.dice_5);
                image.startAnimation(mAnimation);
                break;
            case 6:
                image.setImageResource(R.drawable.dice_6);
                image.startAnimation(mAnimation);
                break;
            default:
                image.setImageResource(R.drawable.dice_0);
        }

    }

    private void checkEndGame() {

        if (livesP1 == 0 || livesP2 == 0) {

            view_dice_p1.setEnabled(false);
            view_dice_p2.setEnabled(false);

            String text = "";
            if (livesP1 != 0) {
                text = "Game over ! Player 1 Wins !";
            }
            if (livesP2 != 0) {
                text = "Game over ! Player 2 Wins !";
            }

            AlertDialog.Builder alertDialogB = new AlertDialog.Builder(this);
            alertDialogB.setCancelable(false);
            alertDialogB.setMessage(text);

            alertDialogB.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });

            AlertDialog alertDialog1 = alertDialogB.create();
            alertDialog1.show();
        }

    }
}
