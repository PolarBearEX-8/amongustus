package augustus.modes.combat;

import augustus.modules.combat.Velocity;
import client.event.EventMotionUpdate;
import client.event.EventPacket;
import client.event.MotionUpdateListener;
import client.event.PacketListener;
import client.event.UpdateStateListener;
import client.setting.ActionSetting;
import client.setting.BooleanSetting;
import client.setting.NumberSetting;
import client.setting.SettingGroup;
import client.util.BooleanSupplier;
import client.util.InternalStringArrayEntry;
import client.util.MoveUtil;
import client.util.Packet;
import java.util.function.BiFunction;
import net.minecraft.network.play.server.S12PacketEntityVelocity;

public class VelocityJumpMode extends InternalStringArrayEntry implements UpdateStateListener, MotionUpdateListener, PacketListener {
   public final NumberSetting settingXZVelocityStrength;
   public final NumberSetting settingYVelocityStrength;
   public final NumberSetting settingReverseStart;
   public final BooleanSetting settingReverseStrafe;
   public final ActionSetting actionSetting;

   @Override
   public void updateState() {
   }

   @Override
   public void onMotionUpdate(EventMotionUpdate event) {
   }

   private boolean isActive2() {
      return this.settingReverseStrafe.isEnabled()
         && (double)minecraft.thePlayer.hurtTime <= this.actionSetting.getDouble6()
         && (double)minecraft.thePlayer.hurtTime >= this.actionSetting.getDouble3()
         && minecraft.thePlayer.hurtTime > 0;
   }

   @Override
   public void clear() {
   }

   @Override
   public void onPacketPostSend(EventPacket packet) {
      Packet packet2 = packet.getPacket();
      if (minecraft.thePlayer != null && packet2 instanceof S12PacketEntityVelocity) {
         S12PacketEntityVelocity packet3 = (S12PacketEntityVelocity)packet2;
         if (packet3.getEntityId() == minecraft.thePlayer.getEntityId()) {
            packet3.setEntityId3((int)((double)packet3.getEntityId3() * (this.settingXZVelocityStrength.getDouble2(true) / 100.0)));
            packet3.setEntityId2((int)((double)packet3.getEntityId3() * (this.settingYVelocityStrength.getDouble2(true) / 100.0)));
            packet3.setEntityId4((int)((double)packet3.getEntityId4() * (this.settingXZVelocityStrength.getDouble2(true) / 100.0)));
         }
      }
   }

   private boolean isActiveAsFlag() {
      return this.booleanValue() && this.settingReverseStrafe.isEnabled();
   }

   public static BiFunction getBiFunction() {
      return ((item, group) -> new VelocityJumpMode((Velocity)item, (SettingGroup)group));
   }

   @Override
   public void updateState2() {
      byte byteValue = 43;
      this.applyIndex(byteValue);
      byte byteValue2 = 18;
      this.applyIndex(byteValue2);
   }

   @Override
   public void onPostUpdateState() {
      if (this.isActive()) {
         if ((double)minecraft.thePlayer.hurtTime == this.settingReverseStart.getValue()) {
            minecraft.thePlayer.motionX *= -1.0;
            minecraft.thePlayer.motionZ *= -1.0;
            if (this.isActive2()) {
               MoveUtil.processFloat((float)MoveUtil.getValue2());
            }
         }
      }
   }

   @Override
   public void onPreMotionUpdate() {
      if (this.isActive()) {
         if (this.isActive2()) {
            MoveUtil.updateState();
         }
      }
   }

   public VelocityJumpMode(Velocity velocity, SettingGroup settingGroup) {
        super(velocity, settingGroup, "Reverse");
      
      NumberSetting setting = new NumberSetting(this.module, "XZ-Velocity Strength", 100.0, 0.0, 100.0, 0);
      BooleanSupplier booleanSupplier = this::booleanValue;
      this.settingXZVelocityStrength = setting.getNumberSetting(booleanSupplier);
      setting = new NumberSetting(this.module, "Y-Velocity Strength", 100.0, 0.0, 100.0, 0);
      BooleanSupplier booleanSupplier2 = this::booleanValue;
      this.settingYVelocityStrength = setting.getNumberSetting(booleanSupplier2);
      setting = new NumberSetting(this.module, "ReverseStart", 9.0, 1.0, 10.0, 0, this.settingGroup);
      BooleanSupplier booleanSupplier3 = this::booleanValue;
      this.settingReverseStart = setting.getNumberSetting(booleanSupplier3);
      BooleanSetting setting2 = new BooleanSetting(this.module, "ReverseStrafe", false, this.settingGroup);
      BooleanSupplier booleanSupplier4 = this::booleanValue;
      this.settingReverseStrafe = (BooleanSetting)setting2.getSetting(booleanSupplier4);
      ActionSetting actionSetting2 = (new ActionSetting(this.module, "ReverseStrafeEnd", "ReverseStrafeStart", 9.0, 7.0, 1.0, 10.0, 0, this.settingGroup))
         .getActionSetting();
      BooleanSupplier booleanSupplier5 = this::isActiveAsFlag;
      this.actionSetting = actionSetting2.getActionSetting(booleanSupplier5);
   }
}
