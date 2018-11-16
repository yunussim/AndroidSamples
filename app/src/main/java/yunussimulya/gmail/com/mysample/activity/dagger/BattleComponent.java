package yunussimulya.gmail.com.mysample.activity.dagger;

import dagger.Component;
import yunussimulya.gmail.com.mysample.activity.dagger.object.Boltons;
import yunussimulya.gmail.com.mysample.activity.dagger.object.Starks;
import yunussimulya.gmail.com.mysample.activity.dagger.object.War;

@Component
public interface BattleComponent {
    War getWar();
    Boltons getBoltons();
    Starks getStarks();
}
