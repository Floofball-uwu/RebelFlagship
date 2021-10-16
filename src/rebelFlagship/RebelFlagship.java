package rebelFlagship;

import arc.*;
import arc.util.*;
import mindustry.*;
import mindustry.content.*;
import mindustry.game.EventType.*;
import mindustry.gen.*;
import mindustry.mod.*;
import mindustry.ui.dialogs.*;
import rebelFlagship.content.RebelUnits;

public class RebelFlagship extends Mod{

    public RebelFlagship() {
        Log.info("ASB");
    }

    @Override
    public void loadContent(){
        new RebelUnits().load();
    }
}
