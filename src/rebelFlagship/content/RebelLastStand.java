package rebelFlagship.content;

import arc.Events;
import arc.util.Interval;
import arc.util.Timer;
import mindustry.game.EventType.*;
import mindustry.gen.*;

import static rebelFlagship.content.RebelUnits.rebelFlagship;

public class RebelLastStand {
    public static void load() {
        Timer.schedule(() -> {
            Interval interval = new Interval(1);
            Events.on(UnitCreateEvent.class, e -> {
                if(e.unit.type == rebelFlagship){
                    // try removing time parameter, mess around with "isPlaying" (if it exists) and logical operators
                if (interval.get(0, (60 * 20) + (60 * 60 * 5))) {
                    RebelMusic.LastBoss1.play();
                }
                }});
        }, 0f);
    }
}
