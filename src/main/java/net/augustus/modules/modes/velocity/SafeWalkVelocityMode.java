package net.augustus.modules.modes.velocity;

import net.augustus.events.EventSafeWalk;
import net.augustus.events.listeners.SafeWalkListener;
import java.util.function.BiFunction;
import net.augustus.modules.combat.Velocity;
import net.augustus.settings.SettingGroup;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;

public class SafeWalkVelocityMode extends VelocityMode implements SafeWalkListener {

   public SafeWalkVelocityMode(Velocity velocity, SettingGroup doubleValue) {
      super(velocity, doubleValue, "SafeWalk");
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(33);
   }

   @Override
   public void onEnable() {
   }

   @Override
   public void onDisable() {
   }

   public static BiFunction factory() {
      return (obj, obj2) -> new SafeWalkVelocityMode((Velocity)obj, (SettingGroup)obj2);
   }

   @Override
   public void onPreSafeWalk(EventSafeWalk event) {
      if (this.canApplyVelocity()) {
         if (mc.theWorld.getBlockState(mc.thePlayer.posX, mc.thePlayer.posY - 0.2, mc.thePlayer.posZ).getBlock() instanceof BlockAir && mc.thePlayer.hurtTime > 0) {
            event.setSafeWalk(true);
         }
      }
   }

}
