package rebelFlagship.content;

import arc.Core;
import arc.assets.AssetDescriptor;
import arc.assets.loaders.MusicLoader;
import arc.assets.loaders.SoundLoader;
import arc.audio.Music;
import arc.audio.Sound;
import arc.struct.Seq;
import mindustry.Vars;
import mindustry.gen.Sounds;

public class RebelSounds {
    protected static Sound loadSound(String soundName){
        if (!Vars.headless){
            String name = "sounds/" + soundName;
            String path = Vars.tree.get(name + ".ogg").exists() ? name + ".ogg" : name + ".mp3";

            Sound sound = new Sound();

            AssetDescriptor<?> desc = Core.assets.load(path, Sound.class, new SoundLoader.SoundParameter(sound));
            desc.errored = Throwable::printStackTrace;

            return sound;
        }else{
            return new Sound();
        }
    }

    public static Sound
        sfxFTLjump;

    public static void load(){
        if(Vars.headless) return;

        sfxFTLjump = loadSound("sfxFTLjump");
    }
}
