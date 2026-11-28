package net.augustus.modules.modes.velocity;

import net.augustus.events.listeners.UpdateListener;
import net.augustus.utils.MoveUtil;
import net.augustus.utils.world.BlockUtil;
import java.util.function.BiFunction;
import net.augustus.modules.combat.Velocity;
import net.augustus.settings.SettingGroup;
import net.minecraft.util.BlockPos;

public class KarhuVelocityMode extends VelocityMode implements UpdateListener {
   private int strafeCounter = 0;

   public KarhuVelocityMode(Velocity velocity, SettingGroup doubleValue) {
      super(velocity, doubleValue, "Karhu");
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(43);
   }

   @Override
   public void onEnable() {
   }

   @Override
   public void onDisable() {
   }

   public static BiFunction factory() {
      return (obj, obj2) -> new KarhuVelocityMode((Velocity)obj, (SettingGroup)obj2);
   }

   @Override
   public void onPreUpdate() {
      if (this.canApplyVelocity()) {
         if (mc.thePlayer.hurtTime > 0
            && mc.thePlayer.motionY <= 0.0
            && (
               BlockUtil.hasCollisionBox(new BlockPos(mc.thePlayer.posX, mc.thePlayer.posY - 1.0, mc.thePlayer.posZ))
                  || BlockUtil.hasCollisionBox(new BlockPos(mc.thePlayer.posX, mc.thePlayer.posY - 2.0, mc.thePlayer.posZ))
            )) {
            mc.thePlayer.motionY = -10.0;
         }

         if (mc.thePlayer.hurtTime == 5 && this.strafeCounter++ % 2 == 0) {
            MoveUtil.setSpeed(0.15F);
         }
      }
   }

}
