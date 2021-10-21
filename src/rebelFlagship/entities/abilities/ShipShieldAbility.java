package rebelFlagship.entities.abilities;

import arc.Core;
import arc.func.Cons;
import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.Fill;
import arc.graphics.g2d.Lines;
import arc.math.Mathf;
import arc.math.geom.Intersector;
import arc.scene.ui.layout.Table;
import arc.util.Time;
import mindustry.content.Fx;
import mindustry.entities.abilities.Ability;
import mindustry.gen.Bullet;
import mindustry.gen.Groups;
import mindustry.gen.Unit;
import mindustry.graphics.Layer;
import mindustry.graphics.Pal;
import mindustry.ui.Bar;

public class ShipShieldAbility extends Ability {
    /** Shield Radius. */
    public float radius = 60f;
    /** Shield regen speed in damage/tick. */
    public float regen = 0.1f;
    /** Maximum shield. */
    public float max = 200f;
    /** Cooldown after the shield is broken, in ticks. */
    public float cooldown = 60f * 5;
    /** Color of shield in hex.*/
    public static String color = "75a2e0";

    /** State. */
    protected float radiusScale, alpha;

    private static float realRad;
    private static Unit paramUnit;
    private static ShipShieldAbility paramField;
    private static final Cons<Bullet> shieldConsumer = trait -> {
        if(trait.team != paramUnit.team && trait.type.absorbable && Intersector.isInsideHexagon(paramUnit.x, paramUnit.y, realRad * 2f, trait.x(), trait.y()) && paramUnit.shield > 0){
            trait.absorb();
            Fx.absorb.at(trait);

            //break shield
            if(paramUnit.shield <= trait.damage()){
                paramUnit.shield -= paramField.cooldown * paramField.regen;

                Fx.shieldBreak.at(paramUnit.x, paramUnit.y, paramField.radius, Color.valueOf(color));
            }

            paramUnit.shield -= trait.damage();
            paramField.alpha = 1f;
        }
    };

    public ShipShieldAbility(float radius, float regen, float max, float cooldown, String color){
        this.radius = radius;
        this.regen = regen;
        this.max = max;
        this.cooldown = cooldown;
        this.color = color;
    }

    @Override
    public void update(Unit unit){
        if(unit.shield < max){
            unit.shield += Time.delta * regen;
        }

        alpha = Math.max(alpha - Time.delta/10f, 0f);

        if(unit.shield > 0){
            radiusScale = Mathf.lerpDelta(radiusScale, 1f, 0.06f);
            paramUnit = unit;
            paramField = this;
            checkRadius(unit);

            Groups.bullet.intersect(unit.x - realRad, unit.y - realRad, realRad * 2f, realRad * 2f, shieldConsumer);
        }else{
            radiusScale = 0f;
        }
    }

    @Override
    public void draw(Unit unit){
        checkRadius(unit);

        if(unit.shield > 0){
            Draw.z(Layer.shields);

            Draw.color(Color.valueOf(color), Color.white, Mathf.clamp(alpha));

            if(Core.settings.getBool("animatedshields")){
                Fill.poly(unit.x, unit.y, 8, realRad);
            }else{
                Lines.stroke(1.5f);
                Draw.alpha(0.09f);
                Fill.poly(unit.x, unit.y, 8, radius);
                Draw.alpha(1f);
                Lines.poly(unit.x, unit.y, 8, radius);
            }
        }
    }

    @Override
    public void displayBars(Unit unit, Table bars){
        bars.add(new Bar("stat.shieldhealth", Pal.accent, () -> unit.shield / max)).row();
    }

    public void checkRadius(Unit unit){
        //timer2 is used to store radius scale as an effect
        realRad = radiusScale * radius;
    }
}
