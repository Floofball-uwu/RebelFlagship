package rebelFlagship.content;

import mindustry.content.*;
import mindustry.ctype.*;
import mindustry.entities.abilities.ForceFieldAbility;
import mindustry.gen.*;
import mindustry.type.*;
import mindustry.type.ammo.ItemAmmoType;
import mindustry.world.meta.*;

public class RebelUnits implements ContentList {

    public static UnitType rebelFlagship;

    @Override
    public void load() {
        rebelFlagship = new UnitType("rebelFlagship") {{
            constructor = UnitEntity::create;
            flying = true;
            lowAltitude = false;

            speed = 0.35f;
            accel = 0.07f;
            drag = 0.02f;
            rotateSpeed = 0.6f;

            engineOffset = 54;
            engineSize = 10f;

            destructibleWreck = true;
            health = 780000;
            hitSize = 100f;
            armor = 25f;
            targetFlags = new BlockFlag[]{BlockFlag.reactor, BlockFlag.generator, BlockFlag.turret, BlockFlag.factory, BlockFlag.core, null};
            ammoType = new ItemAmmoType(Items.surgeAlloy);

            abilities.add(new ForceFieldAbility(90f, 7f, 12000f, 60f * 6.5f));
            drawShields = false;
            }
        };
    }
}
