package yunussimulya.gmail.com.mysample.mvvm;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

/**
 * Created by Yunus on 4/17/2018.
 */

public class CounterViewModel extends ViewModel {

    MutableLiveData<Integer> counter = new MutableLiveData<>();

    int number = 0;

    public void increaseCounter() {
        counter.setValue(++number);
    }

    public int getNumber() {
        return number;
    }

}
