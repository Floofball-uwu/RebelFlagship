package rebelFlagship.entities;

import arc.Core;
import arc.graphics.Blending;
import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.TextureRegion;
import arc.math.Angles;
import arc.math.Mathf;
import mindustry.entities.units.WeaponMount;
import mindustry.gen.Unit;
import mindustry.graphics.Drawf;
import mindustry.type.Weapon;

public class RebelWeapon extends Weapon {
    public TextureRegion heatRegion2;

    public Color heatColor = Color.valueOf("#cccccc");

    public float secondHeatDelay;

    public RebelWeapon(String name){
        this.name = name;
    }

    @Override
    public void draw(Unit unit, WeaponMount mount){
        float
            rotation = unit.rotation - 90,
            weaponRotation  = rotation + (rotate ? mount.rotation : 0),
            recoil = -((mount.reload) / reload * this.recoil),
            wx = unit.x + Angles.trnsx(rotation, x, y) + Angles.trnsx(weaponRotation, 0, recoil),
            wy = unit.y + Angles.trnsy(rotation, x, y) + Angles.trnsy(weaponRotation, 0, recoil);

        if(shadow > 0){
            Drawf.shadow(wx, wy, shadow);
        }

        if(outlineRegion.found() && top){
            Draw.rect(outlineRegion,
                wx, wy,
                outlineRegion.width * Draw.scl * -Mathf.sign(flipSprite),
                region.height * Draw.scl,
                weaponRotation);
        }

        Draw.rect(region,
            wx, wy,
            region.width * Draw.scl * -Mathf.sign(flipSprite),
            region.height * Draw.scl,
            weaponRotation);

        if(heatRegion.found() && mount.heat < 1){
            Draw.color(heatColor, 1-mount.heat);
            Draw.blend(Blending.additive);
            Draw.rect(heatRegion,
                wx, wy,
                heatRegion.width * Draw.scl * -Mathf.sign(flipSprite),
                heatRegion.height * Draw.scl,
                weaponRotation);
            Draw.blend();
            Draw.color();
        }
        if(heatRegion2.found() && mount.heat + secondHeatDelay < 1){
            Draw.color(heatColor, 1f-mount.heat);
            Draw.blend(Blending.additive);
            Draw.rect(heatRegion2,
                wx, wy,
                heatRegion2.width * Draw.scl * -Mathf.sign(flipSprite),
                heatRegion2.height * Draw.scl,
                weaponRotation);
            Draw.blend();
            Draw.color();
        }
    }

    @Override
    public void load(){
        region = Core.atlas.find(name, Core.atlas.find("clear"));
        heatRegion = Core.atlas.find(name + "-heat");
        // Try adding N heat sprites in the future
        heatRegion2 = Core.atlas.find(name + "-heat2");
        outlineRegion = Core.atlas.find(name + "-outline");
    }
}
