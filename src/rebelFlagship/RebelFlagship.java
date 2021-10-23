package rebelFlagship;

import arc.Events;
import arc.util.*;
import mindustry.Vars;
import mindustry.game.EventType;
import mindustry.mod.*;
import rebelFlagship.content.*;

public class RebelFlagship extends Mod{

    public RebelFlagship() {
        Events.on(EventType.FileTreeInitEvent.class, e -> RebelMusic.load());
        Events.on(EventType.FileTreeInitEvent.class, e -> RebelSounds.load());
    }

    @Override
    public void loadContent(){
        new RebelStatusEffects().load();
        new RebelUnits().load();
        new RebelLastStand().load();
    }
}
