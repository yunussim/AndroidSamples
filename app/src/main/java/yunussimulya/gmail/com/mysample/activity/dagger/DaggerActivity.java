package yunussimulya.gmail.com.mysample.activity.dagger;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import yunussimulya.gmail.com.mysample.R;
import yunussimulya.gmail.com.mysample.activity.dagger.object.War;

public class DaggerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dagger);

        BattleComponent component = DaggerBattleComponent.create();
        War war = component.getWar();
        war.prepare();
        war.report();

    }
}
