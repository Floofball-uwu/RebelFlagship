package rebelFlagship.content;

import arc.graphics.Color;
import arc.graphics.g2d.Fill;
import arc.graphics.g2d.Lines;
import arc.math.Angles;
import arc.math.Mathf;
import mindustry.content.*;
import mindustry.ctype.*;
import mindustry.entities.Effect;
import mindustry.entities.abilities.ForceFieldAbility;
import mindustry.entities.bullet.BasicBulletType;
import mindustry.entities.bullet.EmpBulletType;
import mindustry.entities.bullet.LaserBulletType;
import mindustry.entities.bullet.MissileBulletType;
import mindustry.gen.*;
import mindustry.graphics.Drawf;
import mindustry.graphics.Pal;
import mindustry.type.*;
import mindustry.type.ammo.ItemAmmoType;
import mindustry.world.meta.*;
import rebelFlagship.entities.abilities.FTLJumpAbility;
import rebelFlagship.entities.abilities.ShipShieldAbility;

import static arc.graphics.g2d.Draw.color;
import static arc.graphics.g2d.Lines.stroke;

public class RebelUnits implements ContentList {

    // Until proper sprites are added, this will stay for easy reconfig of mounts
    float rebelFlagshipWeapons1X = 45f;
    float rebelFlagshipWeapons1Y = 24f;
    float rebelFlagshipWeapons2X = 24f;
    float rebelFlagshipWeapons2Y = 36f;

    public static UnitType rebelFlagship;

    @Override
    public void load() {
        rebelFlagship = new UnitType("rebelFlagship") {
            {
                constructor = UnitEntity::create;
                flying = true;
                lowAltitude = true;
                singleTarget = false;

                speed = 0.35f;
                accel = 0.07f;
                drag = 0.02f;
                rotateSpeed = 0.6f;
                rotateShooting = false;
                range = 260f;

                engineOffset = 54;
                engineSize = 10f;

                destructibleWreck = false;
                health = 380000;
                hitSize = 100f;
                armor = 25f;
                targetFlags = new BlockFlag[]{BlockFlag.reactor, BlockFlag.generator, BlockFlag.turret, BlockFlag.factory, BlockFlag.core, null};
                ammoCapacity = 400;
                ammoType = new ItemAmmoType(Items.surgeAlloy, 10);

                abilities.add(new ShipShieldAbility(90f, 7f, 9000f, 60f * 6.5f, "75a2e0"),
                    new FTLJumpAbility(100f, 10f, 1f));
                drawShields = false;

                // Ion Cannon
                // PLACEHOLDER SPRITE

                weapons.add(new Weapon("emp-cannon-mount") {{
                                mirror = false;
                                rotate = true;

                                x = rebelFlagshipWeapons1X * -1;
                                y = rebelFlagshipWeapons1Y;

                                shots = 3;
                                shotDelay = 10f;
                                inaccuracy = 15f;
                                reload = 150f;
                                shake = 3f;
                                rotateSpeed = 1.1f;
                                shadow = 30f;
                                shootY = 7f;
                                recoil = 4f;
                                cooldownTime = reload - 20f;
                                shootSound = Sounds.laser;

                                bullet = new EmpBulletType() {{
                                    float rad = 80f;

                                    scaleVelocity = true;
                                    lightOpacity = 0.7f;
                                    unitDamageScl = 0.9f;
                                    timeIncrease = 0f;
                                    timeDuration = 60 * 10f;
                                    powerDamageScl = 5f;
                                    damage = 160;
                                    hitColor = lightColor = Pal.lancerLaser;
                                    lightRadius = 60f;
                                    clipSize = 100f;
                                    shootEffect = Fx.hitEmpSpark;
                                    smokeEffect = Fx.shootBigSmoke2;
                                    lifetime = 57f;
                                    sprite = "circle-bullet";
                                    backColor = Pal.lancerLaser;
                                    frontColor = Color.white;
                                    width = height = 16f;
                                    speed = 6f;
                                    trailLength = 20;
                                    trailWidth = 6f;
                                    trailColor = Pal.lancerLaser;
                                    trailInterval = 3f;
                                    splashDamage = 90f;
                                    splashDamageRadius = rad;
                                    hitShake = 5f;
                                    trailRotation = true;
                                    status = StatusEffects.electrified;
                                    hitSound = Sounds.plasmaboom;

                                    trailEffect = new Effect(16f, e -> {
                                        color(Pal.lancerLaser);
                                        for (int s : Mathf.signs) {
                                            Drawf.tri(e.x, e.y, 4f, 30f * e.fslope(), e.rotation + 90f * s);
                                        }
                                    });

                                    hitEffect = new Effect(50f, 100f, e -> {
                                        e.scaled(7f, b -> {
                                            color(Pal.lancerLaser, b.fout());
                                            Fill.circle(e.x, e.y, rad);
                                        });

                                        color(Pal.lancerLaser);
                                        stroke(e.fout() * 3f);
                                        Lines.circle(e.x, e.y, rad);

                                        int points = 10;
                                        float offset = Mathf.randomSeed(e.id, 360f);
                                        for (int i = 0; i < points; i++) {
                                            float angle = i * 360f / points + offset;
                                            //for(int s : Mathf.zeroOne){
                                            Drawf.tri(e.x + Angles.trnsx(angle, rad), e.y + Angles.trnsy(angle, rad), 6f, 50f * e.fout(), angle/* + s*180f*/);
                                            //}
                                        }

                                        Fill.circle(e.x, e.y, 12f * e.fout());
                                        color();
                                        Fill.circle(e.x, e.y, 6f * e.fout());
                                        Drawf.light(e.x, e.y, rad * 1.6f, Pal.lancerLaser, e.fout());
                                    });
                                }};
                            }},

                    // Laser Beam
                    // PLACEHOLDER SPRITE

                    new Weapon("large-laser-mount") {{
                        mirror = false;
                        rotate = true;

                        x = rebelFlagshipWeapons1X;
                        y = rebelFlagshipWeapons1Y;

                        shake = 4f;
                        shootY = 9f;
                        rotateSpeed = 1f;
                        reload = 80f;
                        recoil = 4f;
                        shootSound = Sounds.laser;
                        shadow = 20f;

                        bullet = new LaserBulletType() {{
                            damage = 248f;
                            sideAngle = 20f;
                            sideWidth = 1.5f;
                            sideLength = 80f;
                            width = 48f;
                            length = 360f;
                            shootEffect = Fx.shockwave;
                            colors = new Color[]{Color.valueOf("ec7458aa"), Color.valueOf("ff9c5a"), Color.white};
                        }};
                    }},
                    // Triple Heavy ""Laser""
                    // PLACEHOLDER SPRITE
                    new Weapon("toxopid-cannon") {
                        {
                            mirror = false;
                            rotate = true;
                            rotateSpeed = 1.2f;

                            x = rebelFlagshipWeapons2X * -1;
                            y = rebelFlagshipWeapons2Y;

                            shootY = 24f;
                            shots = 3;
                            shotDelay = 6f;
                            inaccuracy = 1f;
                            reload = 35f;
                            recoil = 5f;
                            shake = 2f;
                            ejectEffect = Fx.casing4;
                            shootSound = Sounds.bang;

                            bullet = new BasicBulletType(10f, 175) {
                                {
                                    pierce = true;
                                    pierceCap = 2;
                                    width = 14f;
                                    height = 24f;
                                    lifetime = 45f;
                                    shootEffect = Fx.shootBig;

                                    hitEffect = Fx.blastExplosion;
                                    splashDamage = 56f;
                                    splashDamageRadius = 15f;
                                }
                            };
                        }
                    },
                    // Missile Launcher
                    // PLACEHOLDER SPRITE
                    new Weapon("sei-launcher") {
                        {
                            mirror = false;

                            x = rebelFlagshipWeapons2X;
                            y = rebelFlagshipWeapons2Y;
                            rotate = true;
                            rotateSpeed = 0.8f;

                            shadow = 20f;

                            shootY = 2f;
                            recoil = 4f;
                            reload = 180f;
                            shots = 3;
                            spacing = 0f;
                            velocityRnd = 0.1f;
                            inaccuracy = 12f;
                            ejectEffect = Fx.none;
                            shake = 3f;
                            shootSound = Sounds.missile;
                            xRand = 8f;
                            shotDelay = 6f;

                            bullet = new MissileBulletType(6.4f, 450) {{
                                homingPower = 0.12f;
                                width = 13f;
                                height = 15f;
                                shrinkX = shrinkY = 0f;
                                drag = -0.008f;
                                homingRange = 100f;
                                keepVelocity = true;
                                splashDamageRadius = 43f;
                                splashDamage = 120f;
                                lifetime = 65f;
                                trailColor = Pal.bulletYellowBack;
                                backColor = Pal.bulletYellowBack;
                                frontColor = Pal.bulletYellow;
                                hitEffect = Fx.blastExplosion;
                                despawnEffect = Fx.blastExplosion;
                                weaveScale = 3f;
                                weaveMag = 1f;

                                status = RebelStatusEffects.breached;
                            }};
                        }
                    });
            }
        };
    }
}