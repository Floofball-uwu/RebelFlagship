package rebelFlagship.content;

import arc.*;
import arc.assets.*;
import arc.assets.loaders.*;
import arc.audio.*;
import arc.struct.*;
import mindustry.*;

public class RebelMusic{
    protected static Music loadMusic(String musicName, boolean ambient, boolean dark, boolean boss){
        if (!Vars.headless){
            String name = "music/" + musicName;
            String path = Vars.tree.get(name + ".mp3").exists() ? name + ".ogg" : name + ".mp3";

            Music music = new Music();

            AssetDescriptor<?> desc = Core.assets.load(path, Music.class, new MusicLoader.MusicParameter(music));
            desc.errored = Throwable::printStackTrace;

            if(ambient) rebelAmbientMusic.add(music);
            if(dark) rebelDarkMusic.add(music);
            if(boss) rebelBossMusic.add(music);
            return music;
        }else{
            return new Music();
        }
    }

    public static Seq<Music>
        rebelAmbientMusic = new Seq<>(),
        rebelDarkMusic = new Seq<>(),
        rebelBossMusic = new Seq<>();

    public static Music
        LastBoss1;

    public void load(){
        LastBoss1 = loadMusic("Last_Boss1", false, false, true);
    }
}
