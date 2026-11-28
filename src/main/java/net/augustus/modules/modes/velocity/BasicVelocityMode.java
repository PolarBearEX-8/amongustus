package net.augustus.modules.modes.velocity;

import net.augustus.events.EventPacket;
import net.augustus.events.listeners.PacketListener;
import net.augustus.settings.SettingVisibilityCondition;
import java.util.function.BiFunction;
import net.augustus.modules.combat.Velocity;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.SettingGroup;
import net.augustus.settings.DoubleValue;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S12PacketEntityVelocity;
import net.minecraft.network.play.server.S27PacketExplosion;

public class BasicVelocityMode extends VelocityMode implements PacketListener {
   private final SettingGroup velocityPacket;
   public final DoubleValue xzVelocityInAir;
   public final DoubleValue yVelocityInAir;
   public final BooleanValue cancelAir;
   public final DoubleValue xzVelocityOnGround;
   public final DoubleValue yVelocityOnGround;
   public final BooleanValue cancelGround;
   private final SettingGroup explosionPacket;
   public final BooleanValue explosion;
   public final DoubleValue xzVelocityInAirExplosion;
   public final DoubleValue yVelocityInAirExplosion;
   public final BooleanValue cancelAirExplosion;
   public final DoubleValue xzVelocityOnGroundExplosion;
   public final DoubleValue yVelocityOnGroundExplosion;
   public final BooleanValue cancelGroundExplosion;
   private final SettingGroup intervalOptions;
   private final BooleanValue intervals;
   private final DoubleValue intervalVelocity;
   private final DoubleValue intervalExplosion;
   private int velocityPacketCount;
   private int explosionPacketCount;

   public BasicVelocityMode(Velocity velocity, SettingGroup doubleValue) {
       super(velocity, doubleValue, "Basic");
      this.velocityPacket = new SettingGroup(this.parentModule, "VelocityPacket", true, this.modeSetting).visibleWhen(() -> this.isSelected());
      this.xzVelocityInAir = new DoubleValue(this.parentModule, "XZVelocityInAir", 0.0, -100.0, 100.0, 0, this.velocityPacket);
      this.yVelocityInAir = new DoubleValue(this.parentModule, "YVelocityInAir", 0.0, -100.0, 100.0, 0, this.velocityPacket);
      this.cancelAir = (BooleanValue)new BooleanValue(this.parentModule, "CancelAir", true, this.velocityPacket).withVisibility(() -> this.isCancelAirVisible());
      this.xzVelocityOnGround = new DoubleValue(this.parentModule, "XZVelocityOnGround", 0.0, -100.0, 100.0, 0, this.velocityPacket);
      this.yVelocityOnGround = new DoubleValue(this.parentModule, "YVelocityOnGround", 0.0, -100.0, 100.0, 0, this.velocityPacket);
      this.cancelGround = (BooleanValue)new BooleanValue(this.parentModule, "CancelGround", true, this.velocityPacket).withVisibility(() -> this.isCancelGroundVisible());
      this.explosionPacket = new SettingGroup(this.parentModule, "ExplosionPacket", true, this.modeSetting).visibleWhen(() -> this.isSelected());
      this.explosion = new BooleanValue(this.parentModule, "Explosion", true, this.explosionPacket);
      DoubleValue xZVelocityInAirExplosion = new DoubleValue(this.parentModule, "XZVelocityInAirExplosion", 0.0, -100.0, 100.0, 0, this.explosionPacket);
      BooleanValue explosion2 = this.explosion;
      BooleanValue booleanValue3 = explosion2;
      this.xzVelocityInAirExplosion = xZVelocityInAirExplosion.visibleWhen(() -> booleanValue3.getValue());
      DoubleValue yVelocityInAirExplosion2 = new DoubleValue(this.parentModule, "YVelocityInAirExplosion", 0.0, -100.0, 100.0, 0, this.explosionPacket);
      explosion2 = this.explosion;
      BooleanValue booleanValue4 = explosion2;
      this.yVelocityInAirExplosion = yVelocityInAirExplosion2.visibleWhen(() -> booleanValue4.getValue());
      this.cancelAirExplosion = (BooleanValue)new BooleanValue(this.parentModule, "CancelAirExplosion", true, this.explosionPacket).withVisibility(() -> this.isCancelAirExplosionVisible());
      DoubleValue xZVelocityOnGroundExplosion = new DoubleValue(this.parentModule, "XZVelocityOnGroundExplosion", 0.0, -100.0, 100.0, 0, this.explosionPacket);
      explosion2 = this.explosion;
      BooleanValue booleanValue6 = explosion2;
      this.xzVelocityOnGroundExplosion = xZVelocityOnGroundExplosion.visibleWhen(() -> booleanValue6.getValue());
      DoubleValue yVelocityOnGroundExplosion2 = new DoubleValue(this.parentModule, "YVelocityOnGroundExplosion", 0.0, -100.0, 100.0, 0, this.explosionPacket);
      explosion2 = this.explosion;
      BooleanValue booleanValue7 = explosion2;
      this.yVelocityOnGroundExplosion = yVelocityOnGroundExplosion2.visibleWhen(() -> booleanValue7.getValue());
      this.cancelGroundExplosion = (BooleanValue)new BooleanValue(this.parentModule, "CancelGroundExplosion", true, this.explosionPacket).withVisibility(() -> this.isCancelGroundExplosionVisible());
      this.intervalOptions = new SettingGroup(this.parentModule, "IntervalOptions", true, this.modeSetting).visibleWhen(() -> this.isSelected());
      this.intervals = new BooleanValue(this.parentModule, "Intervals", false, this.intervalOptions);
      DoubleValue intervalVelocitySetting = new DoubleValue(this.parentModule, "IntervalVelocity", 1.0, 1.0, 10.0, 0, this.intervalOptions);
      explosion2 = this.intervals;
      BooleanValue booleanValue9 = explosion2;
      this.intervalVelocity = intervalVelocitySetting.visibleWhen(() -> booleanValue9.getValue());
      this.intervalExplosion = new DoubleValue(this.parentModule, "IntervalExplosion", 1.0, 1.0, 10.0, 0, this.intervalOptions).visibleWhen(() -> this.isExplosionIntervalVisible());
      this.velocityPacketCount = 0;
      this.explosionPacketCount = 0;
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(26);
   }

   @Override
   public void onEnable() {
   }

   @Override
   public void onDisable() {
   }

   public static BiFunction factory() {
      return (obj, obj2) -> new BasicVelocityMode((Velocity)obj, (SettingGroup)obj2);
   }

   @Override
   public void onPacketReceive(EventPacket event) {
      if (this.canApplyVelocity()) {
         Packet packet = event.getPacket();
         if (packet instanceof S12PacketEntityVelocity) {
            S12PacketEntityVelocity packet2 = (S12PacketEntityVelocity)packet;
            if (packet2.getEntityID() == mc.thePlayer.getEntityId()) {
               this.velocityPacketCount++;
               if ((double)this.velocityPacketCount % this.intervalVelocity.getValue() != 0.0 && this.intervals.getValue()) {
                  return;
               }

               if (mc.thePlayer.onGround) {
                  if (this.cancelGround.getValue()) {
                     if (this.xzVelocityOnGround.getValue() == 0.0) {
                        packet2.setApplyHorizontalVelocity(false);
                     }

                     if (this.yVelocityOnGround.getValue() == 0.0) {
                        packet2.setApplyVerticalVelocity(false);
                     }
                  }

                  packet2.setMotionX((int)((double)packet2.getMotionX() * this.xzVelocityOnGround.getValue() / 100.0));
                  packet2.setMotionY((int)((double)packet2.getMotionY() * this.yVelocityOnGround.getValue() / 100.0));
                  packet2.setMotionZ((int)((double)packet2.getMotionZ() * this.xzVelocityOnGround.getValue() / 100.0));
               } else {
                  if (this.cancelAir.getValue()) {
                     if (this.xzVelocityInAir.getValue() == 0.0) {
                        packet2.setApplyHorizontalVelocity(false);
                     }

                     if (this.yVelocityInAir.getValue() == 0.0) {
                        packet2.setApplyVerticalVelocity(false);
                     }
                  }

                  packet2.setMotionX((int)((double)packet2.getMotionX() * this.xzVelocityInAir.getValue() / 100.0));
                  packet2.setMotionY((int)((double)packet2.getMotionY() * this.yVelocityInAir.getValue() / 100.0));
                  packet2.setMotionZ((int)((double)packet2.getMotionZ() * this.xzVelocityInAir.getValue() / 100.0));
               }
            }
         } else if (packet instanceof S27PacketExplosion && this.explosion.getValue()) {
            this.explosionPacketCount++;
            if ((double)this.explosionPacketCount % this.intervalExplosion.getValue() != 0.0 && this.intervals.getValue()) {
               return;
            }

            S27PacketExplosion packet3 = (S27PacketExplosion)packet;
            if (mc.thePlayer.onGround) {
               packet3.setMotionX((float)((double)packet3.getMotionX() * this.xzVelocityOnGroundExplosion.getValue() / 100.0));
               packet3.setMotionY((float)((double)packet3.getMotionY() * this.yVelocityOnGroundExplosion.getValue() / 100.0));
               packet3.setMotionZ((float)((double)packet3.getMotionZ() * this.xzVelocityOnGroundExplosion.getValue() / 100.0));
               if (!this.cancelGroundExplosion.getValue()) {
                  if (this.xzVelocityOnGroundExplosion.getValue() == 0.0) {
                     packet3.setResetHorizontalMotion(true);
                  }

                  if (this.yVelocityOnGroundExplosion.getValue() == 0.0) {
                     packet3.setResetVerticalMotion(true);
                  }
               }
            } else {
               packet3.setMotionX((float)((double)packet3.getMotionX() * this.xzVelocityInAirExplosion.getValue() / 100.0));
               packet3.setMotionY((float)((double)packet3.getMotionY() * this.yVelocityInAirExplosion.getValue() / 100.0));
               packet3.setMotionZ((float)((double)packet3.getMotionZ() * this.xzVelocityInAirExplosion.getValue() / 100.0));
               if (!this.cancelAirExplosion.getValue()) {
                  if (this.xzVelocityInAirExplosion.getValue() == 0.0) {
                     packet3.setResetHorizontalMotion(true);
                  }

                  if (this.yVelocityInAirExplosion.getValue() == 0.0) {
                     packet3.setResetVerticalMotion(true);
                  }
               }
            }
         }
      }
   }

   private boolean isExplosionIntervalVisible() {
      return this.intervals.getValue() && this.explosion.getValue();
   }

   private boolean isCancelGroundExplosionVisible() {
      return (this.xzVelocityOnGroundExplosion.getValue() == 0.0 || this.yVelocityOnGroundExplosion.getValue() == 0.0) && this.explosion.getValue();
   }

   private boolean isCancelAirExplosionVisible() {
      return (this.xzVelocityInAirExplosion.getValue() == 0.0 || this.yVelocityInAirExplosion.getValue() == 0.0) && this.explosion.getValue();
   }

   private boolean isCancelGroundVisible() {
      return this.xzVelocityOnGround.getValue() == 0.0 || this.yVelocityOnGround.getValue() == 0.0;
   }

   private boolean isCancelAirVisible() {
      return this.xzVelocityInAir.getValue() == 0.0 || this.yVelocityInAir.getValue() == 0.0;
   }

}
