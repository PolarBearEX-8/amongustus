package augustus.modes.movement;

import augustus.modules.movement.Jesus;
import client.event.EventMove;
import client.event.EventStrafe;
import client.event.MoveListener;
import client.event.StrafeListener;
import client.event.TickListener;
import client.setting.BooleanSetting;
import client.setting.SettingGroup;
import client.util.BooleanSupplier;
import client.util.CoreStringEntry;
import client.util.MoveUtil;
import client.util.SimulatedPlayer;

public class FlyIntavenewMode extends CoreStringEntry implements TickListener, MoveListener, StrafeListener {
   public final BooleanSetting settingHideSneakAnimation;

   @Override
   public void updateState() {
   }

   @Override
   public void onStrafe(EventStrafe event) {
      if (this.isActive() && minecraft.thePlayer.isInWater() && this.settingHideSneakAnimation.isEnabled()) {
         float factor = 1.62F;
         event.processAction3(factor);
      }
   }

   @Override
   public void clear() {
   }

   @Override
   public void onMove(EventMove event) {
      if (this.isActive()
         && minecraft.thePlayer.isInWater()
         && minecraft.thePlayer.hurtTime < 7
         && SimulatedPlayer.checkCondition((float)Math.toDegrees(MoveUtil.computeValue(minecraft.thePlayer.rotationYaw)))
         && (event.getScaleFactor7() != 0.0F || event.getScaleFactor2() != 0.0F)) {
         event.setState4(true);
         double offset = 0.6;
         event.setCoordOffset6(offset);
      }
   }

   @Override
   public void updateState2() {
      byte byteValue = 40;
      this.applyIndex(byteValue);
      byte byteValue2 = 21;
      this.applyIndex(byteValue2);
      byte byteValue3 = 24;
      this.applyIndex(byteValue3);
   }

   @Override
   public void onRunTick() {
      if (this.isActive()
         && minecraft.thePlayer.onGround
         && !minecraft.thePlayer.isCollidedHorizontally
         && minecraft.thePlayer.isInWater()
         && SimulatedPlayer.checkCondition((float)Math.toDegrees(MoveUtil.computeValue(minecraft.thePlayer.rotationYaw)))
         && MoveUtil.canExecute()
         && minecraft.thePlayer.hurtTime < 7) {
         minecraft.thePlayer.applyFloat(minecraft.thePlayer.scaleFactor24, minecraft.thePlayer.scaleFactor3, 0.0995F);
      }
   }

   public FlyIntavenewMode(Jesus jesus, SettingGroup settingGroup) {
        super(jesus, settingGroup, "IntaveNew");
      
      BooleanSetting setting = new BooleanSetting(this.module, "HideSneakAnimation", true, this.settingGroup);
      BooleanSupplier booleanSupplier = this::booleanValue;
      this.settingHideSneakAnimation = (BooleanSetting)setting.getSetting(booleanSupplier);
   }
}
