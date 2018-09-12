package yunussimulya.gmail.com.mysample.activity.dagger.object;

import javax.inject.Inject;

import yunussimulya.gmail.com.mysample.activity.dagger.House;

public class Boltons implements House {

    @Inject
    public Boltons() {

    }

    @Override
    public void prepareForWar() {
        System.out.println(this.getClass().getSimpleName() + " prepared for war");
    }

    @Override
    public void reportForWar() {
        System.out.println(this.getClass().getSimpleName() + " reporting");
    }
}
