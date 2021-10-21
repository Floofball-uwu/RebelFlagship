package rebelFlagship.content;

import arc.audio.Music;
import arc.util.Reflect;
import arc.util.Timer;
import mindustry.audio.SoundControl;
import mindustry.gen.*;

import static rebelFlagship.content.RebelUnits.rebelFlagship;

public class RebelLastStand {
    public static void load() {
        RebelMusic.LastBoss1.setVolume(1.2f);
        RebelMusic.LastBoss1.setLooping(true);
        Timer.schedule(() -> {
            Groups.unit.each(e -> {
                if (e.type == rebelFlagship) {
                    if (RebelMusic.LastBoss1.isPlaying()) {
                        //Lazy way of stopping overlapping music
                        Musics.boss1.stop(); Musics.boss2.stop();
                        Musics.game1.stop(); Musics.game2.stop(); Musics.game3.stop();
                        Musics.game4.stop(); Musics.game5.stop(); Musics.game6.stop();
                        Musics.game7.stop(); Musics.game8.stop(); Musics.game9.stop();
                    } else if (!RebelMusic.LastBoss1.isPlaying()) {
                        RebelMusic.LastBoss1.play();
                    }
                }
            });
            if (Musics.menu.isPlaying()) {
                RebelMusic.LastBoss1.stop();
            }
        }, 0f, 0.5f);
    }
}
