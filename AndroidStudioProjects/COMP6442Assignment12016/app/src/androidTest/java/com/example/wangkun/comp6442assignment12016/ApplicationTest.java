package com.example.wangkun.comp6442assignment12016;

import android.app.Application;
import android.test.ApplicationTestCase;

import junit.framework.TestResult;

import org.junit.Test;
import java.util.ArrayList;


/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }


    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected TestResult createResult() {
        return super.createResult();
    }


    @Test
    public void dataTest() {

        assertTrue(2 + 2 == 4);
        ArrayList<String> titles = new ArrayList<>();
        titles.add("Basketball");
        titles.add("Swim");


        assertEquals(true, TestActivity.getInserTest());
        assertEquals(true, TestActivity.getTitlesTest().equals(titles));
        assertEquals(true, TestActivity.getContentTest().equals("I want to play basketball this Monday"));




    }


}