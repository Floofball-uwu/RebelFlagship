package rebelFlagship.entities.abilities;

import arc.*;
import arc.graphics.g2d.Draw;
import arc.math.Angles;
import arc.math.Mathf;
import arc.util.Log;
import arc.util.Time;
import arc.util.Timer;
import arc.util.Tmp;
import mindustry.entities.Effect;
import mindustry.entities.abilities.Ability;
import mindustry.gen.*;
import mindustry.graphics.Pal;
import mindustry.input.Binding;
import rebelFlagship.content.RebelFx;
import rebelFlagship.content.RebelSounds;

import static mindustry.Vars.*;
import static mindustry.content.StatusEffects.unmoving;
import static rebelFlagship.content.RebelStatusEffects.FTL_cooldown;

public class FTLJumpAbility extends Ability {
    // Jump distance in tiles
    float distance;
    // Cooldown time in seconds
    float cooldown;
    // Delay in seconds after which the jump is done, used to visually sync jump
    float delay;

    private float unitX, unitY;
    private boolean jumping = false;

    public FTLJumpAbility(float distance, float cooldown, float delay) {
        this.distance = distance;
        this.cooldown = cooldown;
        this.delay = delay;
    }

    @Override
    public void update(Unit unit) {
        // Add support for commanded units that got the same ability and an effect check
        if (Core.input.keyDown(Binding.boost) && player.unit() == unit && !unit.hasEffect(FTL_cooldown)) {

            unitX = (Angles.trnsx(unit.rotation, distance * 8) + unit.x);
            unitY = (Angles.trnsy(unit.rotation, distance * 8) + unit.y);
            if (unitX < state.map.width * 8 && unitX > 0 && unitY < state.map.height * 8 && unitY > 0 && !jumping) {
                RebelSounds.sfxFTLjump.at(unit.x, unit.y, 1f, 0.5f);
                RebelFx.ftlstar.at(unit.x, unit.y, unit.rotation, unit);
                jumping = true;
                unit.apply(unmoving, delay * 60);

                // Delay before actual jump goes here
                Timer.schedule(() -> {
                    unit.x(unitX);
                    unit.y(unitY);
                    Log.info("ftlstarRev start here");
                    RebelFx.ftlstarRev.at(unit.x, unit.y, unit.rotation, unit);
                    unit.apply(FTL_cooldown, cooldown * 60);
                    jumping = false;
                }, delay);
            }
        }
    }
}
