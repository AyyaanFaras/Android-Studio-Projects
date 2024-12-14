package com.example.tictactoev1;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    
    boolean activePlayer = true;
    int[] gamespace = {2,2,2,2,2,2,2,2,2};
    boolean gameActive = true;

    int[][] winPosition = {
            {0,3,6},{1,4,7},{2,5,8},
            {0,1,2},{3,4,5},{6,7,8},
            {0,4,8},{2,4,6}
    };
    ArrayList<Integer> list = new ArrayList<>();

    @SuppressLint("SetTextI18n")
    public void tapaction(View view){
        TextView txt = (TextView) view;
        int tappedPosition = Integer.parseInt(txt.getTag().toString());
        if(!gameActive){
            gameReset(view);
            gameActive = true;
            list.clear();
        }
        else if(gamespace[tappedPosition]==2){

            if(activePlayer){
                gamespace[tappedPosition] = 1;
                txt.setText("X");
                list.add(tappedPosition);    //new
                TextView status = findViewById(R.id.status);
                status.setText("O's Turn. tap to play!");
            }
            else{
                gamespace[tappedPosition] = 0;
                txt.setText("O");
                list.add(tappedPosition);    //new
                TextView status = findViewById(R.id.status);
                status.setText("X's Turn. tap to play!");
            }



            activePlayer = !activePlayer;

        }

        for(int[] winpos : winPosition){
            if(gamespace[winpos[0]] == gamespace[winpos[1]] && gamespace[winpos[2]] == gamespace[winpos[1]] && gamespace[winpos[0]]!=2){
                TextView status = findViewById(R.id.status);
                if(gamespace[winpos[0]] == 1){
                    status.setText("X has won the game!");
                    gameActive = false;
//                    gameReset(view);
                }
                else{
                    status.setText("O has won the game!");
                    gameActive = false;
//                    gameReset(view);
                }
            }


        }
//        if(list.size()>5){

            if(list.size()>7){

                int a = list.remove(0);
                gamespace[a] = 2;
                if(a == 0){
                    ((TextView) findViewById(R.id.t1)).setText("");
                }
                else if(a == 1){
                    ((TextView) findViewById(R.id.t2)).setText("");
                }
                else if(a == 2){
                    ((TextView) findViewById(R.id.t3)).setText("");
                }
                else if(a == 3){
                    ((TextView) findViewById(R.id.t4)).setText("");
                }
                else if(a == 4){
                    ((TextView) findViewById(R.id.t5)).setText("");
                }
                else if(a == 5){
                    ((TextView) findViewById(R.id.t6)).setText("");
                }
                else if(a == 6){
                    ((TextView) findViewById(R.id.t7)).setText("");
                }
                else if(a == 7){
                    ((TextView) findViewById(R.id.t8)).setText("");
                }
                else if(a == 8){
                    ((TextView) findViewById(R.id.t9)).setText("");
                }
            }



    }

    public TextView textid(int b) {

        if(b==0) return findViewById(R.id.t1);
        else if(b==1) return findViewById(R.id.t2);
        else if(b==2) return findViewById(R.id.t3);
        else if(b==3) return findViewById(R.id.t4);
        else if(b==4) return findViewById(R.id.t5);
        else if(b==5) return findViewById(R.id.t6);
        else if(b==6) return findViewById(R.id.t7);
        else if(b==7) return findViewById(R.id.t8);
        else return findViewById(R.id.t9);


    }

    public void gameReset(View view){

//        Text
        gamespace[0] = 2;
        gamespace[1] = 2;
        gamespace[2] = 2;
        gamespace[3] = 2;
        gamespace[4] = 2;
        gamespace[5] = 2;
        gamespace[6] = 2;
        gamespace[7] = 2;
        gamespace[8] = 2;

        ((TextView) findViewById(R.id.t1)).setText("");
        ((TextView) findViewById(R.id.t2)).setText("");
        ((TextView) findViewById(R.id.t3)).setText("");
        ((TextView) findViewById(R.id.t4)).setText("");
        ((TextView) findViewById(R.id.t5)).setText("");
        ((TextView) findViewById(R.id.t6)).setText("");
        ((TextView) findViewById(R.id.t7)).setText("");
        ((TextView) findViewById(R.id.t8)).setText("");
        ((TextView) findViewById(R.id.t9)).setText("");

        TextView status = findViewById(R.id.status);
        if(activePlayer) status.setText("X's Turn. Tap to play!");
        else status.setText("O's Turn. Tap to play!");


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}