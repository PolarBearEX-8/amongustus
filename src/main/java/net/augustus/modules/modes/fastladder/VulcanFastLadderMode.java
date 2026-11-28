package net.augustus.modules.modes.fastladder;

import net.augustus.events.EventLadder;
import net.augustus.events.listeners.LadderListener;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.modules.movement.FastLadder;
import net.augustus.settings.SettingGroup;
import net.augustus.settings.DoubleValue;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;

public class VulcanFastLadderMode extends FastLadderMode implements LadderListener {
   public final DoubleValue vulcanMotionY;

   public VulcanFastLadderMode(FastLadder fastLadder, SettingGroup doubleValue) {
       super(fastLadder, doubleValue, "Vulcan");
      this.vulcanMotionY = new DoubleValue(this.parentModule, "VulcanMotionY", 5.0, 0.2, 10.0, 3, this.modeSetting).named("MotionY").visibleWhen(() -> this.isSelected());
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(15);
   }

   @Override
   public void onEnable() {
   }

   @Override
   public void onDisable() {
   }

   @Override
   public void onLadder(EventLadder event) {
      if (((FastLadder)this.parentModule).isOnClimbable()) {
         double d = 0.0;

         for (double i = this.vulcanMotionY.getValue() % 1.0; i <= this.vulcanMotionY.getValue(); d = i++) {
            if (!this.isClimbableAtYOffset(i)) {
               if (!this.isClimbableAtYOffset(i - 1.0)) {
                  break;
               }
            }
         }

         event.setMotionY(d);
         mc.thePlayer
            .sendQueue
            .addToSendQueue(
               new C07PacketPlayerDigging(C07PacketPlayerDigging.Action.STOP_DESTROY_BLOCK, new BlockPos(mc.thePlayer.posX, mc.thePlayer.posY + 1.0, mc.thePlayer.posZ), EnumFacing.DOWN)
            );
      }
   }

}
