package rebelFlagship.entities.abilities;

import arc.*;
import arc.math.Angles;
import arc.math.Mathf;
import arc.util.Log;
import mindustry.Vars;
import mindustry.content.StatusEffects;
import mindustry.entities.abilities.Ability;
import mindustry.gen.*;
import mindustry.input.Binding;
import mindustry.type.StatusEffect;

import static mindustry.Vars.*;
import static rebelFlagship.content.RebelStatusEffects.FTL_cooldown;

public class FTLJumpAbility extends Ability {
    // Jump distance in tiles
    float distance = 10f;
    // Cooldown time in seconds
    float cooldown = 10f;

    private float unitX, unitY;

    public FTLJumpAbility(float distance, float cooldown) {
        this.distance = distance;
        this.cooldown = cooldown;
    }

    @Override
    public void update(Unit unit) {
        // Add support for commanded units that got the same ability and an effect check
        if (Core.input.keyDown(Binding.boost) && player.unit() == unit && !unit.hasEffect(FTL_cooldown)) {
            // Visual effect and SFX goes here

            // Jump is done after a delay

            // Need to prevent unit from jumping outside of map bounds
            unitX = (Angles.trnsx(unit.rotation, distance * 8) + unit.x);
            unitY = (Angles.trnsy(unit.rotation, distance * 8) + unit.y);
            Log.info("FTL coordinates set!");
            if(unitX < state.map.width && unitY < state.map.height) {
                unit.x(unitX);
                unit.y(unitY);
                unit.apply(FTL_cooldown, cooldown * 60 );
                Log.info("Jump complete");
            }
            //unitDest = distance / unit.drag;

            // After jump, play new SFX and FX
        }
    }
}
