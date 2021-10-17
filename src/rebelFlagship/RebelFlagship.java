package rebelFlagship;

import arc.util.*;
import mindustry.mod.*;
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
    }
}
