package rebelFlagship.entities.abilities;

import arc.*;
import arc.math.Angles;
import arc.math.Mathf;
import mindustry.entities.abilities.Ability;
import mindustry.gen.*;
import mindustry.input.Binding;

public class FTLJumpAbility extends Ability {

    float distance = 10;
    float cooldown = 10;

    public FTLJumpAbility(float distance, float cooldown) {
        this.distance = distance;
        this.cooldown = cooldown;
    }

    @Override
    public void update(Unit unit) {
        if (Core.input.keyDown(Binding.boost)) {
            // Visual effect and SFX goes here

            // Jump is done after a delay

            // Need to limit effect to executing unit
            unit.x(Angles.trnsx(unit.rotation, distance) + unit.x);
            unit.y(Angles.trnsy(unit.rotation, distance) + unit.y);
        }
    }
}
