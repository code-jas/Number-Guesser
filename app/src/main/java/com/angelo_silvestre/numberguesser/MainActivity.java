package com.angelo_silvestre.numberguesser;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private EditText numInput;
    private TextView guessResult, guessCount, guessesNumbers;
    private Button guessBtn,retryBtn;
    private String Tag = "MainActivity";

    private int gCount = 0;
    private int randomNumber;
    private ArrayList<Integer> numberGuessList = new ArrayList<Integer>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initviews();
        generateRandomNumber();

        guessBtn.setOnClickListener(view ->guessTheNumber());
        retryBtn.setOnClickListener(view -> {
            gCount = 0;
            generateRandomNumber();
            numInput.setText("");
            guessResult.setText("");
            guessCount.setText("Number of guesses:");
            guessesNumbers.setText("Guesses number are :");
            retryBtn.setVisibility(View.GONE);
            guessBtn.setEnabled(true);
            guessResult.setTextColor(Color.parseColor("#757575"));
        });

    }

    @SuppressLint("SetTextI18n")
    private void guessTheNumber() {
        Log.d(Tag, "Guess the number: Started");
        String inp =  numInput.getText().toString();

        int numberGuess = Integer.parseInt(inp);
        if(numberGuess > 100 ||numberGuess < 0){
            guessResult.setText("Invalid Input! 1-100 number only!!!!");
            guessResult.setTextColor(Color.parseColor("#FE5339"));
        }else {
            guessResult.setTextColor(Color.parseColor("#757575"));
            if(numberGuess == getRandomNumber()) {
                guessResult.setText("Yippee! Your Guess is right!");
                guessResult.setTextColor(Color.parseColor("#4BE1AB"));
                guessBtn.setEnabled(false);
                guessCount.setText("Number of guesses: "+Integer.toString(++gCount));
                guessesNumbers.append(" "+ inp+" ");
                retryBtn.setVisibility(View.VISIBLE);

            } else {
                if(numberGuess > getRandomNumber()) {
                    guessResult.setText("Your guess is too high!");
                    Toast.makeText(this, "Input another number!", Toast.LENGTH_SHORT).show();
                    guessCount.setText("Number of guesses: "+Integer.toString(++gCount));
                    guessesNumbers.append(" "+ inp+" ");

                } else {
                    guessResult.setText("Your guess is too low!");
                    Toast.makeText(this, "Input another number!", Toast.LENGTH_SHORT).show();
                    guessCount.setText("Number of guesses: "+Integer.toString(++gCount));
                    guessesNumbers.append(" "+ inp+" ");
                }

            }
        }


    }

    private void generateRandomNumber(){
        Log.d(Tag, "generateRandomNumber Views: Started");
        Random rand = new Random();
        //generate random values from 0-100
         int upperbound = 101;
         int randomNum = rand.nextInt(upperbound);
         setRandomNumber(randomNum);
        Log.d(Tag, "Random Number is : " + this.getRandomNumber());
        //Toast.makeText(this, "Random Number is : " + this.getRandomNumber(), Toast.LENGTH_SHORT).show();
    }

    private void initviews(){
        Log.d(Tag, "Initial Views: Started");

        // Display Result
        numInput = findViewById(R.id.numInput);

        guessResult = findViewById(R.id.guessResult);
        guessCount = findViewById(R.id.guessCount);
        guessesNumbers = findViewById(R.id.guessesNumbers);

        guessBtn = findViewById(R.id.btnGuess);
        retryBtn = findViewById(R.id.btnReset);
    }



    public int getRandomNumber() {
        return randomNumber;
    }

    public void setRandomNumber(int randomNumber) {
        this.randomNumber = randomNumber;
    }
}