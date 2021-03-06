package com.example.wordwallet;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;

public class Quiz1_retry extends AppCompatActivity {
    long first_time;
    long second_time;

    public void onBackPressed() {

        second_time = System.currentTimeMillis();
        Toast.makeText(Quiz1_retry.this, "한번 더 누르면 퀴즈가 종료됩니다", Toast.LENGTH_SHORT).show();
        if(second_time - first_time < 2000){
            super.onBackPressed();
            Intent intent = new Intent(Quiz1_retry.this, WWmainActivity.class);
            intent.putExtra("Quiz",1);
            startActivity(intent);
        }
        first_time = System.currentTimeMillis();
    }


    Button q, btn1, btn2, btn3;
    TextView current;
    int cIndex = 0;
    int lIndex = -1;
    int cCount = 0;
    ImageView home;

    ArrayList<ArrayList<String>> wData = new ArrayList<ArrayList<String>>();
    ArrayList<String> Q = new ArrayList<String>();
    ArrayList<String> A = new ArrayList<String>();

    ArrayList<String> wrongQ  = new ArrayList<String>();
    ArrayList<String> wrongA  = new ArrayList<String>();

    MediaPlayer correct, incorrect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz1);

        q = findViewById(R.id.q);
        btn1 = findViewById(R.id.shortcut_btn);
        btn2 = findViewById(R.id.bookmark);
        btn3 = findViewById(R.id.button3);
        current = findViewById(R.id.QuestionIndex1);

        correct = MediaPlayer.create(this, R.raw.correct);
        incorrect = MediaPlayer.create(this, R.raw.incorrect);
        home = findViewById(R.id.menu);
        Intent intent = getIntent();

        lIndex = intent.getIntExtra("wrongCount", -1);
        Q = intent.getStringArrayListExtra("wrongQ");
        A = intent.getStringArrayListExtra("wrongA");

        for(int j = 0; j<= lIndex; j++) {
            //n번째 단어의 스키마
            ArrayList<String> word = new ArrayList<String>();
            word.add(Q.get(j));
            word.add(A.get(j));
            wData.add(word);
        }

        Collections.shuffle(wData);

        displayQuestion1(cIndex);


        btn1.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){

                if(btn1.getText().toString().equalsIgnoreCase(wData.get(cIndex).get(1))){
                    cCount++;
                    correct.start();

                    if(cIndex == lIndex){  // 만약 index == 최종 -> 결과페이지


                        Intent intent = new Intent(Quiz1_retry.this, QuizResult.class);
                        intent.putExtra("wrongDataQ", Q);
                        intent.putExtra("wrongDataA", A);
                        intent.putExtra("lastIndex",(lIndex +1)); // 총 문제 수
                        intent.putExtra("Correct", cCount); // 정답 수
                        intent.putExtra("QuizNumber",1);
                        intent.putExtra("retry",1);
                        startActivity(intent);
                    }

                    else{ // 마지막 문제 아니면 다음 문제
                        cIndex++;
                        displayQuestion1(cIndex);
                    }
                }

                else{ //오답 -> 오답 Toast 띄우고 answer 초기화 및 wrongData에 저장
                    incorrect.start();

                    wrongQ.add(wData.get(cIndex).get(0));
                    wrongA.add(wData.get(cIndex).get(1));

                    if(cIndex == lIndex){  // 만약 index == 최종 -> 결과페이지


                        Intent intent = new Intent(Quiz1_retry.this, QuizResult.class);
                        intent.putExtra("wrongDataQ", Q);
                        intent.putExtra("wrongDataA", A);
                        intent.putExtra("lastIndex",(lIndex +1)); // 총 문제 수
                        intent.putExtra("Correct", cCount); // 정답 수
                        intent.putExtra("QuizNumber",1);
                        intent.putExtra("retry",1);
                        startActivity(intent);
                    }

                    else{
                        cIndex++;
                        displayQuestion1(cIndex);
                    }
                }
            }
        });

        btn2.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){

                if(btn2.getText().toString().equalsIgnoreCase(wData.get(cIndex).get(1))){


                    cCount++;
                    correct.start();

                    if(cIndex == lIndex){  // 만약 index == 최종 -> 결과페이지

                        Intent intent = new Intent(Quiz1_retry.this, QuizResult.class);
                        intent.putExtra("wrongDataQ", Q);
                        intent.putExtra("wrongDataA", A);
                        intent.putExtra("lastIndex",(lIndex +1)); // 총 문제 수
                        intent.putExtra("Correct", cCount); // 정답 수
                        intent.putExtra("QuizNumber", 1);
                        intent.putExtra("retry",1);

                        startActivity(intent);
                    }

                    else{ // 마지막 문제 아니면 다음 문제
                        cIndex++;
                        displayQuestion1(cIndex);
                    }
                }

                else{ //오답 -> 오답 Toast 띄우고 answer 초기화 및 wrongData에 저장
                    incorrect.start();

                    wrongQ.add(wData.get(cIndex).get(0));
                    wrongA.add(wData.get(cIndex).get(1));

                    if(cIndex == lIndex){  // 만약 index == 최종 -> 결과페이지

                        Intent intent = new Intent(Quiz1_retry.this, QuizResult.class);
                        intent.putExtra("wrongDataQ", Q);
                        intent.putExtra("wrongDataA", A);
                        intent.putExtra("lastIndex",(lIndex +1)); // 총 문제 수
                        intent.putExtra("Correct", cCount); // 정답 수
                        intent.putExtra("QuizNumber", 1);
                        intent.putExtra("retry",1);

                        startActivity(intent);
                    }

                    else{ // 마지막 문제 아니면 다음 문제
                        cIndex++;
                        displayQuestion1(cIndex);
                    }

                }
            }
        });

        btn3.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){

                if(btn3.getText().toString().equalsIgnoreCase(wData.get(cIndex).get(1))){

                    cCount++;
                    correct.start();

                    if(cIndex == lIndex){  // 만약 index == 최종 -> 결과페이지

                        Intent intent = new Intent(Quiz1_retry.this, QuizResult.class);
                        intent.putExtra("wrongDataQ", Q);
                        intent.putExtra("wrongDataA", A);
                        intent.putExtra("lastIndex",(lIndex +1)); // 총 문제 수
                        intent.putExtra("Correct", cCount); // 정답 수
                        intent.putExtra("QuizNumber", 1);
                        intent.putExtra("retry",1);
                        startActivity(intent);
                    }

                    else{ // 마지막 문제 아니면 다음 문제

                        cIndex++;
                        displayQuestion1(cIndex);
                    }
                }

                else{ //오답 -> 오답 Toast 띄우고 answer 초기화 및 wrongData에 저장
                    incorrect.start();


                    wrongQ.add(wData.get(cIndex).get(0));
                    wrongA.add(wData.get(cIndex).get(1));

                    if(cIndex == lIndex){  // 만약 index == 최종 -> 결과페이지

                        Intent intent = new Intent(Quiz1_retry.this, QuizResult.class);
                        intent.putExtra("wrongDataQ", Q);
                        intent.putExtra("wrongDataA", A);
                        intent.putExtra("lastIndex",(lIndex +1)); // 총 문제 수
                        intent.putExtra("Correct", cCount); // 정답 수
                        intent.putExtra("QuizNumber", 1);
                        intent.putExtra("retry",1);

                        startActivity(intent);
                    }

                    else{ // 마지막 문제 아니면 다음 문제
                        cIndex++;
                        displayQuestion1(cIndex);
                    }
                }
            }
        });
    }

    public void displayQuestion1(int index){ // 문제와 답 출력

        if(lIndex>1) {
            current.setText((index+1) + "/" + (lIndex +1));

            q.setText(wData.get(index).get(0));

            int correct = (int)((Math.random()*3)+1); // 옳은 답의 위치를 지정하는 난수

            int incorrectIndex1, incorrectIndex2; // 틀린 답을 출력하기 위한 wordData의 인덱스

            switch (correct) {
                case 1:
                    btn1.setText(wData.get(index).get(1));

                    do {
                        incorrectIndex1 = (int) (Math.random() * (lIndex+1));
                    } while (index == incorrectIndex1);

                    do {
                        incorrectIndex2 = (int) (Math.random() * (lIndex+1));
                    } while (index == incorrectIndex2 || incorrectIndex1 == incorrectIndex2);

                    btn2.setText(wData.get(incorrectIndex1).get(1));
                    btn3.setText(wData.get(incorrectIndex2).get(1));
                    break;

                case 2:
                    btn2.setText(wData.get(index).get(1));

                    do {
                        incorrectIndex1 = (int) (Math.random() * (lIndex+1));
                    } while (index == incorrectIndex1);

                    do {
                        incorrectIndex2 = (int) (Math.random() * (lIndex+1));
                    } while (index == incorrectIndex2 || incorrectIndex1 == incorrectIndex2);

                    btn1.setText(wData.get(incorrectIndex1).get(1));
                    btn3.setText(wData.get(incorrectIndex2).get(1));
                    break;

                case 3:
                    btn3.setText(wData.get(index).get(1));

                    do {
                        incorrectIndex1 = (int) (Math.random() * (lIndex+1));
                    } while (index == incorrectIndex1);

                    do {
                        incorrectIndex2 = (int) (Math.random() * (lIndex+1));
                    } while (index == incorrectIndex2 || incorrectIndex1 == incorrectIndex2);

                    btn1.setText(wData.get(incorrectIndex1).get(1));
                    btn2.setText(wData.get(incorrectIndex2).get(1));
                    break;
            }
        }



        if(lIndex==1){

            current.setText((index+1) + "/" + (lIndex+1));


            q.setText(wData.get(index).get(0));


            int correct = (int)((Math.random()*3)+1); // 옳은 답의 위치를 지정하는 난수
            int wW;

            do{
                wW = (int)((Math.random()*3)+1);
            }while(correct == wW );

            int incorrectIndex ;




            switch (correct) {
                case 1:
                    btn1.setText(wData.get(index).get(1));

                    switch(wW){
                        case 2:
                            btn2.setText("개인적인");

                            do {
                                incorrectIndex = (int) (Math.random() * (lIndex+1));
                            } while (index == incorrectIndex);; // 틀린 답을 출력하기 위한 wordData의 인덱스

                            btn3.setText(wData.get(incorrectIndex).get(1));
                            break;
                        case 3:
                            btn3.setText("인권");
                            do {
                                incorrectIndex = (int) (Math.random() * (lIndex+1));
                            } while (index == incorrectIndex);; // 틀린 답을 출력하기 위한 wordData의 인덱스
                            btn2.setText(wData.get(incorrectIndex).get(1));
                            break;

                    }

                    break;


                case 2:
                    btn2.setText(wData.get(index).get(1));


                    switch(wW){
                        case 1:
                            btn1.setText("상승");
                            do {
                                incorrectIndex = (int) (Math.random() * (lIndex+1));
                            } while (index == incorrectIndex);; // 틀린 답을 출력하기 위한 wordData의 인덱스

                            btn3.setText(wData.get(incorrectIndex).get(1));
                            break;

                        case 3:
                            btn3.setText("야기하다");
                            do {
                                incorrectIndex = (int) (Math.random() * (lIndex+1));
                            } while (index == incorrectIndex);; // 틀린 답을 출력하기 위한 wordData의 인덱스


                            btn1.setText(wData.get(incorrectIndex).get(1));
                            break;
                    }
                    break;

                case 3:
                    btn3.setText(wData.get(index).get(1));

                    switch(wW){
                        case 1:
                            btn1.setText("자금");

                            do {
                                incorrectIndex = (int) (Math.random() * (lIndex+1));
                            } while (index == incorrectIndex);; // 틀린 답을 출력하기 위한 wordData의 인덱스
                            btn2.setText(wData.get(incorrectIndex).get(1));
                            break;
                        case 2:
                            btn2.setText("환경적인");

                            do {
                                incorrectIndex = (int) (Math.random() * (lIndex+1));
                            } while (index == incorrectIndex);; // 틀린 답을 출력하기 위한 wordData의 인덱스
                            btn1.setText(wData.get(incorrectIndex).get(1));
                            break;

                    }
                    break;
            }


        }

        if(lIndex==0) {

            current.setText((index + 1) + "/" + (lIndex + 1));

            q.setText(wData.get(index).get(0));

            int correct = (int) ((Math.random() * 3) + 1); // 옳은 답의 위치를 지정하는 난수

            switch (correct) {
                case 1:
                    btn1.setText(wData.get(index).get(1));
                    btn2.setText("파괴되다");
                    btn3.setText("명성");
                    break;


                case 2:
                    btn2.setText(wData.get(index).get(1));
                    btn1.setText("극도의");
                    btn3.setText("물리적인");
                    break;


                case 3:
                    btn3.setText(wData.get(index).get(1));

                    btn1.setText("문제");
                    btn2.setText("자유");
                    break;
            }
        }

        if(lIndex==-1){
            q. setText("단어를 등록해주세요");

            btn1.setVisibility(View.GONE);
            btn2.setVisibility(View.GONE);
            btn3.setVisibility(View.GONE);
        }

    }

}