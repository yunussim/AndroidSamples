package yunussimulya.gmail.com.mysample;

import android.arch.core.executor.testing.InstantTaskExecutorRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

import static org.junit.Assert.assertEquals;

import yunussimulya.gmail.com.mysample.mvvm.CounterViewModel;

/**
 * Created by Yunus on 4/17/2018.
 */

public class MvvmSimpleTest {

    CounterViewModel model;

    @Rule
    public TestRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void setUp() {
        model = new CounterViewModel();
    }

    @Test
    public void clickButton() {
        //given

        //when
        model.increaseCounter();
        assertEquals(model.getNumber(), 1);

        model.increaseCounter();
        assertEquals(model.getNumber(), 2);

        model.increaseCounter();
        assertEquals(model.getNumber(), 3);
    }

}
