package rebelFlagship.content;

import arc.audio.Music;
import arc.util.Log;
import arc.util.Reflect;
import arc.util.Timer;
import mindustry.audio.SoundControl;
import mindustry.content.UnitTypes;
import mindustry.game.*;
import mindustry.gen.*;

import static rebelFlagship.content.RebelUnits.rebelFlagship;

public class RebelLastStand {
    public static void load() {
        RebelMusic.LastBoss1.setLooping(true);
        Timer.schedule(() -> {
            if (Groups.unit.contains(e -> e.type == rebelFlagship)){
                if (RebelMusic.LastBoss1.isPlaying()) {
                    //Lazy way of stopping overlapping music
                    Musics.boss1.stop();
                    Musics.boss2.stop();
                    Musics.game1.stop();
                    Musics.game2.stop();
                    Musics.game3.stop();
                    Musics.game4.stop();
                    Musics.game5.stop();
                    Musics.game6.stop();
                    Musics.game7.stop();
                    Musics.game8.stop();
                    Musics.game9.stop();
                } else if (!RebelMusic.LastBoss1.isPlaying()) {
                    Log.info("Playing LastBoss1");
                    RebelMusic.LastBoss1.setVolume(1.2f);
                    RebelMusic.LastBoss1.play();
                }
            }
            if (RebelMusic.LastBoss1.isPlaying() && Groups.unit.contains(e -> e.type != rebelFlagship) || Musics.menu.isPlaying()) {
                // Cheap fading because I'm too lazy to reflect lmao
                Log.info("Fading LastBoss1");
                RebelMusic.LastBoss1.setVolume(RebelMusic.LastBoss1.getVolume() - 0.1f);
                if (RebelMusic.LastBoss1.getVolume() <= 0) RebelMusic.LastBoss1.stop();
            }
        }, 0f, 0.5f);
    }
}
