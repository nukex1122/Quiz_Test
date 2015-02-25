package com.example.syed.quiz_test;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class QuizActivity extends Activity {


    private static final String TAG = "QuizActivity";
    private Button mTrueButton;
    private Button mFalseButton;
    private TextView mQuestionText;
    private Button mNxtButton;
    private controller mQuestionBank[]= new controller[]{
            new controller(R.string.question_oceans,true),
            new controller(R.string.question_mideast,false),
            new controller(R.string.question_africa,false),
            new controller(R.string.question_americas,true),
            new controller(R.string.question_asia,true),
    };
    //Subjective Things Start Here, s means Subjective
    private SubjectiveController sQuestionBank[]=new SubjectiveController[]{
            new SubjectiveController(R.string.question_tech_1,R.string.q_t_1_ans),
            new SubjectiveController(R.string.question_tech_2,R.string.q_t_2_ans),
    };
    private int count = 0 ;
    private int score = 0;
    private TextView mScore;
    private EditText mAnswerBox;
    private TextView mSubjectiveQuestionBox;
    private Button mCheckButton;
    private int s_count=0;
    private Button mSNext;
    private String text;
    public void setScore(){
        mScore.setText(String.valueOf(score));
    }

    public void updateSubjectiveQuestion()
    {
        int question = sQuestionBank[s_count].getQuestion();
        mSubjectiveQuestionBox.setText(question);
    }

    public void updateQuestion()
    {
        int question = mQuestionBank[count].getQuestion();
        mQuestionText.setText(question);
    }

    public void checkAnswer(boolean userPressed)
    {
        boolean correctAnswer = mQuestionBank[count].getAnswer();
        int messageResId=0;
        if(userPressed==correctAnswer)
        {
            if(mQuestionBank[count].getSeen()==false){
                score++;
                messageResId=R.string.correct_toast;
            }
            else
            {
                messageResId=R.string.already_done_toast;
            }
        }
        else
        {
            messageResId=R.string.wrong_toast;

        }
        Toast.makeText(QuizActivity.this,messageResId,Toast.LENGTH_SHORT).show();
    }

    public void Subjective_Check(String user_answer){

        int correctAnswer = sQuestionBank[s_count].getAnswer();
        Log.d(TAG, String.valueOf(correctAnswer));
        String aString = Integer.toString(correctAnswer);
        int messageResId=0;
        int converted=Integer.parseInt(user_answer);
        if(converted==correctAnswer){
            messageResId=R.string.correct_toast;
        }
        else
        {
            messageResId=R.string.wrong_toast;

        }
        Toast.makeText(QuizActivity.this,messageResId,Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        mScore=(TextView) findViewById(R.id.score_view);
        mQuestionBank[count].setSeen(false);
        mQuestionText=(TextView) findViewById(R.id.question);
        mTrueButton=(Button) findViewById(R.id.true_button);
        mFalseButton=(Button) findViewById(R.id.false_button);
        mNxtButton=(Button) findViewById(R.id.next_button);
        //Subjective
        mAnswerBox=(EditText) findViewById(R.id.subjective_answer);
        mSubjectiveQuestionBox=(TextView)findViewById(R.id.subjective_questions);
        mCheckButton=(Button) findViewById(R.id.check_button);
        mSNext=(Button) findViewById(R.id.s_Next_Button);


        updateQuestion();
        updateSubjectiveQuestion();
        setScore();

        for(int i=0;i<mQuestionBank.length;i++){
            mQuestionBank[i].setSeen(false);
        }

        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
                mQuestionBank[count].setSeen(true);
                setScore();
            }
        });
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
                mQuestionBank[count].setSeen(true);
                setScore();
            }
        });

        mNxtButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count=(count+1)%mQuestionBank.length;
                updateQuestion();
            }
        });
        //Subjective
        mSNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s_count=(s_count+1)%sQuestionBank.length;
                updateSubjectiveQuestion();
            }
        });

        mCheckButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String UserAnswer = mAnswerBox.getText().toString();
                Subjective_Check(UserAnswer);
            }
        });
    }

    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_quiz, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/
}
