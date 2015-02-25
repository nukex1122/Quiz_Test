package com.example.syed.quiz_test;

/**
 * Created by Syed on 25-Feb-2015.
 */
public class SubjectiveController {
    private int mQuestion;
    private int mAnswer;

    public SubjectiveController(int question,int answer){
        mQuestion=question;
        mAnswer=answer;
    }

    public int getQuestion()
    {
        return mQuestion;
    }
    public int getAnswer()
    {
        return mAnswer;
    }
}
