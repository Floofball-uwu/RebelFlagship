package rebelFlagship.content;

import arc.Core;
import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.Fill;
import arc.graphics.g2d.TextureRegion;
import arc.math.Mathf;
import arc.math.geom.Vec2;
import arc.util.Log;
import arc.util.Tmp;
import mindustry.entities.Effect;
import mindustry.gen.Unit;
import mindustry.graphics.Pal;

import static arc.graphics.g2d.Draw.color;
import static arc.graphics.g2d.Lines.lineAngle;
import static arc.graphics.g2d.Lines.stroke;
import static arc.math.Angles.randLenVectors;

public class RebelFx {

    public static final Effect ftlstar = new Effect(60f, e-> {
        // Thanks sk
        if(e.data instanceof Unit u){
            float f = e.fin(), s = u.hitSize() / 3.4f;
            float f3 = f * f * (1f - f);
            Tmp.v1.trns(u.rotation, (-0.5f + f) * u.hitSize()).add(u);
            TextureRegion region = Core.atlas.find("rebel-flagship-ftlstar");
            Draw.color(200, 170, 25);
            Draw.alpha(20);
            Draw.rect(region, Tmp.v1.x, Tmp.v1.y, s * f3 * region.width * (Draw.scl / 1.5f), s * f3 * region.height * (Draw.scl / 1.5f), u.rotation);
        }

    });

    public static final Effect ftlstarRev = new Effect(60f, e-> {
        // Thanks sk
        if(e.data instanceof Unit u){
            float f = e.fin(), s = u.hitSize() / 3.4f;
            float f3 = f * f * (1f - f);
            Tmp.v1.trns(u.rotation, (0.5f - f) * u.hitSize()).add(u);
            TextureRegion region = Core.atlas.find("rebel-flagship-ftlstar");
            Draw.color(200, 170, 25);
            Draw.alpha(20);
            Draw.rect(region, Tmp.v1.x, Tmp.v1.y, s * f3 * region.width * (Draw.scl / 1.5f), s * f3 * region.height * (Draw.scl / 1.5f), u.rotation);
        }

    });
}
