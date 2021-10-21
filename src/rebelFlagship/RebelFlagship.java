package rebelFlagship;

import arc.util.*;
import mindustry.mod.*;
import rebelFlagship.content.RebelLastStand;
import rebelFlagship.content.RebelMusic;
import rebelFlagship.content.RebelStatusEffects;
import rebelFlagship.content.RebelUnits;

public class RebelFlagship extends Mod{

    public RebelFlagship() {
        Log.info("ASB");
    }

    @Override
    public void loadContent(){
        new RebelStatusEffects().load();
        new RebelUnits().load();
        new RebelMusic().load();
        new RebelLastStand().load();
    }
}
