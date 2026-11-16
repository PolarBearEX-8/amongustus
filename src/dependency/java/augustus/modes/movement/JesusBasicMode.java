package augustus.modes.movement;

import augustus.modules.combat.Velocity;
import client.event.EventPacket;
import client.event.PacketListener;
import client.setting.BooleanSetting;
import client.setting.NumberSetting;
import client.setting.SettingGroup;
import client.util.BooleanSupplier;
import client.util.InternalStringArrayEntry;
import client.util.Packet;
import java.util.function.BiFunction;
import net.minecraft.network.play.server.S12PacketEntityVelocity;
import net.minecraft.network.play.server.S27PacketExplosion;

public class JesusBasicMode extends InternalStringArrayEntry implements PacketListener {
   public final SettingGroup settingVelocityPacket;
   public final NumberSetting settingXZVelocityInAir;
   public final NumberSetting settingYVelocityInAir;
   public final BooleanSetting settingCancelAir;
   public final NumberSetting settingXZVelocityOnGround;
   public final NumberSetting settingYVelocityOnGround;
   public final BooleanSetting settingCancelGround;
   public final SettingGroup settingExplosionPacket;
   public final BooleanSetting settingExplosion;
   public final NumberSetting settingXZVelocityInAirExplosion;
   public final NumberSetting settingYVelocityInAirExplosion;
   public final BooleanSetting settingCancelAirExplosion;
   public final NumberSetting settingXZVelocityOnGroundExplosion;
   public final NumberSetting settingYVelocityOnGroundExplosion;
   public final BooleanSetting settingCancelGroundExplosion;
   public final SettingGroup settingIntervalOptions;
   public final BooleanSetting settingIntervals;
   public final NumberSetting settingIntervalVelocity;
   public final NumberSetting settingIntervalExplosion;
   public int count2;
   public int count3;

   @Override
   public void updateState() {
   }

   private boolean isActive2() {
      return this.settingXZVelocityInAir.getValue() == 0.0 || this.settingYVelocityInAir.getValue() == 0.0;
   }

   private boolean isActiveAsFlag() {
      return this.settingXZVelocityOnGround.getValue() == 0.0 || this.settingYVelocityOnGround.getValue() == 0.0;
   }

   private boolean isActive5() {
      return (this.settingXZVelocityOnGroundExplosion.getValue() == 0.0 || this.settingYVelocityOnGroundExplosion.getValue() == 0.0)
         && this.settingExplosion.isEnabled();
   }

   @Override
   public void clear() {
   }

   public static BiFunction getBiFunction() {
      return ((item, group) -> new JesusBasicMode((Velocity)item, (SettingGroup)group));
   }

   private boolean isActive3() {
      return this.settingIntervals.isEnabled() && this.settingExplosion.isEnabled();
   }

   private boolean isActive4() {
      return (this.settingXZVelocityInAirExplosion.getValue() == 0.0 || this.settingYVelocityInAirExplosion.getValue() == 0.0)
         && this.settingExplosion.isEnabled();
   }

   @Override
   public void onPacketPostSend(EventPacket packet) {
      if (this.isActive()) {
         Packet packet2 = packet.getPacket();
         if (packet2 instanceof S12PacketEntityVelocity) {
            S12PacketEntityVelocity packet3 = (S12PacketEntityVelocity)packet2;
            if (packet3.getEntityId() == minecraft.thePlayer.getEntityId()) {
               this.count2++;
               if ((double)this.count2 % this.settingIntervalVelocity.getValue() != 0.0 && this.settingIntervals.isEnabled()) {
                  return;
               }

               if (minecraft.thePlayer.onGround) {
                  if (this.settingCancelGround.isEnabled()) {
                     if (this.settingXZVelocityOnGround.getValue() == 0.0) {
                        packet3.setOnGround1(false);
                     }

                     if (this.settingYVelocityOnGround.getValue() == 0.0) {
                        packet3.setOnGround(false);
                     }
                  }

                  packet3.setEntityId3((int)((double)packet3.getEntityId3() * this.settingXZVelocityOnGround.getValue() / 100.0));
                  packet3.setEntityId2((int)((double)packet3.getEntityId2() * this.settingYVelocityOnGround.getValue() / 100.0));
                  packet3.setEntityId4((int)((double)packet3.getEntityId4() * this.settingXZVelocityOnGround.getValue() / 100.0));
               } else {
                  if (this.settingCancelAir.isEnabled()) {
                     if (this.settingXZVelocityInAir.getValue() == 0.0) {
                        packet3.setOnGround1(false);
                     }

                     if (this.settingYVelocityInAir.getValue() == 0.0) {
                        packet3.setOnGround(false);
                     }
                  }

                  packet3.setEntityId3((int)((double)packet3.getEntityId3() * this.settingXZVelocityInAir.getValue() / 100.0));
                  packet3.setEntityId2((int)((double)packet3.getEntityId2() * this.settingYVelocityInAir.getValue() / 100.0));
                  packet3.setEntityId4((int)((double)packet3.getEntityId4() * this.settingXZVelocityInAir.getValue() / 100.0));
               }
            }
         } else if (packet2 instanceof S27PacketExplosion && this.settingExplosion.isEnabled()) {
            this.count3++;
            if ((double)this.count3 % this.settingIntervalExplosion.getValue() != 0.0 && this.settingIntervals.isEnabled()) {
               return;
            }

            S27PacketExplosion packet4 = (S27PacketExplosion)packet2;
            if (minecraft.thePlayer.onGround) {
               packet4.processAction3((float)((double)packet4.getScaleFactor2() * this.settingXZVelocityOnGroundExplosion.getValue() / 100.0));
               packet4.processAction2((float)((double)packet4.getScaleFactor3() * this.settingYVelocityOnGroundExplosion.getValue() / 100.0));
               packet4.processAction((float)((double)packet4.getScaleFactor() * this.settingXZVelocityOnGroundExplosion.getValue() / 100.0));
               if (!this.settingCancelGroundExplosion.isEnabled()) {
                  if (this.settingXZVelocityOnGroundExplosion.getValue() == 0.0) {
                     packet4.setEnabled2(true);
                  }

                  if (this.settingYVelocityOnGroundExplosion.getValue() == 0.0) {
                     packet4.setEnabled(true);
                  }
               }
            } else {
               packet4.processAction3((float)((double)packet4.getScaleFactor2() * this.settingXZVelocityInAirExplosion.getValue() / 100.0));
               packet4.processAction2((float)((double)packet4.getScaleFactor3() * this.settingYVelocityInAirExplosion.getValue() / 100.0));
               packet4.processAction((float)((double)packet4.getScaleFactor() * this.settingXZVelocityInAirExplosion.getValue() / 100.0));
               if (!this.settingCancelAirExplosion.isEnabled()) {
                  if (this.settingXZVelocityInAirExplosion.getValue() == 0.0) {
                     packet4.setEnabled2(true);
                  }

                  if (this.settingYVelocityInAirExplosion.getValue() == 0.0) {
                     packet4.setEnabled(true);
                  }
               }
            }
         }
      }
   }

   @Override
   public void updateState2() {
      byte byteValue = 26;
      this.applyIndex(byteValue);
   }

   public JesusBasicMode(Velocity velocity, SettingGroup settingGroup) {
        super(velocity, settingGroup, "Basic");
      
      SettingGroup settingGroup2 = new SettingGroup(this.module, "VelocityPacket", true, this.settingGroup);
      BooleanSupplier booleanSupplier = this::booleanValue;
      BooleanSupplier booleanSupplier2 = booleanSupplier;
      this.settingVelocityPacket = settingGroup2.getSettingGroup(booleanSupplier2);
      this.settingXZVelocityInAir = new NumberSetting(this.module, "XZVelocityInAir", 0.0, -100.0, 100.0, 0, this.settingVelocityPacket);
      this.settingYVelocityInAir = new NumberSetting(this.module, "YVelocityInAir", 0.0, -100.0, 100.0, 0, this.settingVelocityPacket);
      BooleanSetting setting = new BooleanSetting(this.module, "CancelAir", true, this.settingVelocityPacket);
      BooleanSupplier booleanSupplier3 = this::isActive2;
      this.settingCancelAir = (BooleanSetting)setting.getSetting(booleanSupplier3);
      this.settingXZVelocityOnGround = new NumberSetting(this.module, "XZVelocityOnGround", 0.0, -100.0, 100.0, 0, this.settingVelocityPacket);
      this.settingYVelocityOnGround = new NumberSetting(this.module, "YVelocityOnGround", 0.0, -100.0, 100.0, 0, this.settingVelocityPacket);
      BooleanSetting setting2 = new BooleanSetting(this.module, "CancelGround", true, this.settingVelocityPacket);
      BooleanSupplier booleanSupplier4 = this::isActiveAsFlag;
      this.settingCancelGround = (BooleanSetting)setting2.getSetting(booleanSupplier4);
      SettingGroup settingGroup3 = new SettingGroup(this.module, "ExplosionPacket", true, this.settingGroup);
      booleanSupplier = this::booleanValue;
      BooleanSupplier booleanSupplier5 = booleanSupplier;
      this.settingExplosionPacket = settingGroup3.getSettingGroup(booleanSupplier5);
      this.settingExplosion = new BooleanSetting(this.module, "Explosion", true, this.settingExplosionPacket);
      NumberSetting setting3 = new NumberSetting(this.module, "XZVelocityInAirExplosion", 0.0, -100.0, 100.0, 0, this.settingExplosionPacket);
      BooleanSupplier booleanSupplier6 = this.settingExplosion::isEnabled;
      this.settingXZVelocityInAirExplosion = setting3.getNumberSetting(booleanSupplier6);
      NumberSetting setting4 = new NumberSetting(this.module, "YVelocityInAirExplosion", 0.0, -100.0, 100.0, 0, this.settingExplosionPacket);
      BooleanSupplier booleanSupplier7 = this.settingExplosion::isEnabled;
      this.settingYVelocityInAirExplosion = setting4.getNumberSetting(booleanSupplier7);
      BooleanSetting setting5 = new BooleanSetting(this.module, "CancelAirExplosion", true, this.settingExplosionPacket);
      BooleanSupplier booleanSupplier8 = this::isActive4;
      this.settingCancelAirExplosion = (BooleanSetting)setting5.getSetting(booleanSupplier8);
      NumberSetting setting6 = new NumberSetting(this.module, "XZVelocityOnGroundExplosion", 0.0, -100.0, 100.0, 0, this.settingExplosionPacket);
      BooleanSupplier booleanSupplier9 = this.settingExplosion::isEnabled;
      this.settingXZVelocityOnGroundExplosion = setting6.getNumberSetting(booleanSupplier9);
      NumberSetting setting7 = new NumberSetting(this.module, "YVelocityOnGroundExplosion", 0.0, -100.0, 100.0, 0, this.settingExplosionPacket);
      BooleanSupplier booleanSupplier10 = this.settingExplosion::isEnabled;
      this.settingYVelocityOnGroundExplosion = setting7.getNumberSetting(booleanSupplier10);
      BooleanSetting setting8 = new BooleanSetting(this.module, "CancelGroundExplosion", true, this.settingExplosionPacket);
      BooleanSupplier booleanSupplier11 = this::isActive5;
      this.settingCancelGroundExplosion = (BooleanSetting)setting8.getSetting(booleanSupplier11);
      SettingGroup settingGroup4 = new SettingGroup(this.module, "IntervalOptions", true, this.settingGroup);
      booleanSupplier = this::booleanValue;
      BooleanSupplier booleanSupplier12 = booleanSupplier;
      this.settingIntervalOptions = settingGroup4.getSettingGroup(booleanSupplier12);
      this.settingIntervals = new BooleanSetting(this.module, "Intervals", false, this.settingIntervalOptions);
      NumberSetting setting9 = new NumberSetting(this.module, "IntervalVelocity", 1.0, 1.0, 10.0, 0, this.settingIntervalOptions);
      BooleanSupplier booleanSupplier13 = this.settingIntervals::isEnabled;
      this.settingIntervalVelocity = setting9.getNumberSetting(booleanSupplier13);
      NumberSetting setting10 = new NumberSetting(this.module, "IntervalExplosion", 1.0, 1.0, 10.0, 0, this.settingIntervalOptions);
      BooleanSupplier booleanSupplier14 = this::isActive3;
      this.settingIntervalExplosion = setting10.getNumberSetting(booleanSupplier14);
      this.count2 = 0;
      this.count3 = 0;
   }
}
