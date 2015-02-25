package com.example.syed.quiz_test;

/**
 * Created by Syed on 25-Feb-2015.
 */
public class controller {
    private int mQuestion;
    private boolean mTrue;
    private boolean mSeen;

    public controller(int question,boolean Answer)
    {
        mQuestion= question;
        mTrue=Answer;
    }
    public void setSeen(boolean check)
    {
        mSeen=check;
    }

    public boolean getSeen(){
        return mSeen;
    }

    public int getQuestion()
    {
        return mQuestion;
    }
    public boolean getAnswer()
    {
        return mTrue;
    }
}
