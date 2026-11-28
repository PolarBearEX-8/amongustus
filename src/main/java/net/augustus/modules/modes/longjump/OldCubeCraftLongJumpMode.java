package net.augustus.modules.modes.longjump;

import net.augustus.events.listeners.UpdateListener;
import net.augustus.utils.MoveUtil;
import net.augustus.utils.player.PlayerUtil;
import java.util.function.BiFunction;
import net.augustus.modules.movement.LongJump;
import net.augustus.settings.SettingGroup;

public class OldCubeCraftLongJumpMode extends AbstractLongJumpMode implements UpdateListener {
   private int airTicks = 0;

   public OldCubeCraftLongJumpMode(LongJump longJump, SettingGroup doubleValue) {
      super(longJump, doubleValue, "OldCubeCraft");
   }

   @Override
   public void updateEventSubscriptions() {
      super.updateEventSubscriptions();
      this.toggleEventSubscription(43);
   }

   @Override
   public void onEnable() {
      if (mc.thePlayer != null && mc.thePlayer.onGround) {
         PlayerUtil.damageSelfPacket();
      }
   }

   @Override
   public void onDisable() {
   }

   public static BiFunction factory() {
      return (obj, obj2) -> new OldCubeCraftLongJumpMode((LongJump)obj, (SettingGroup)obj2);
   }

   @Override
   public void onPreUpdate() {
      mc.thePlayer.jumpMovementFactor = 0.028F;
      if (mc.thePlayer.onGround) {
         if (mc.thePlayer.hurtTime != 0) {
            this.airTicks = 0;
            mc.thePlayer.motionY = 0.6;
            MoveUtil.addMotion(0.3, false);
         }
      } else {
         if (this.airTicks == 1) {
            MoveUtil.addMotion(0.2, false);
         }

         mc.getTimer().timerSpeed = 0.7F;
         if (mc.thePlayer.motionY > 0.0) {
            mc.thePlayer.motionY += 0.06F;
         } else if (mc.thePlayer.motionY > -0.4) {
            mc.thePlayer.motionY += 0.042;
         }
      }

      this.airTicks++;
   }

}
