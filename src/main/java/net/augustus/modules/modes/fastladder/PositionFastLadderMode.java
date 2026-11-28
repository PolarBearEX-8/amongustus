package net.augustus.modules.modes.fastladder;

import net.minecraft.network.play.client.C03PacketPlayer;
import net.augustus.events.EventMotion;
import net.augustus.events.listeners.MotionListener;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.modules.movement.FastLadder;
import net.augustus.settings.SettingGroup;
import net.augustus.settings.DoubleValue;

public class PositionFastLadderMode extends FastLadderMode implements MotionListener {
   public final DoubleValue positionSpeed;
   public final DoubleValue positionTickDelay;

   public PositionFastLadderMode(FastLadder fastLadder, SettingGroup doubleValue) {
       super(fastLadder, doubleValue, "Position");
      DoubleValue positionSpeedSetting = new DoubleValue(this.parentModule, "PositionSpeed", 0.4, 0.2, 2.0, 3, this.modeSetting);
      positionSpeedSetting = positionSpeedSetting.named("Speed");
      this.positionSpeed = positionSpeedSetting.visibleWhen(() -> this.isSelected());
      this.positionTickDelay = new DoubleValue(this.parentModule, "PositionTickDelay", 0.0, 0.0, 20.0, 0, this.modeSetting).named("TickDelay").visibleWhen(() -> this.isSelected());
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(18);
   }

   @Override
   public void onEnable() {
   }

   @Override
   public void onDisable() {
   }

   @Override
   public void onPreMotion(EventMotion event) {
      if (((FastLadder)this.parentModule).isClimbing()) {
         if ((double)mc.thePlayer.ticksExisted % (this.positionTickDelay.getValue() + 1.0) == 0.0) {
            mc.thePlayer
               .sendQueue
               .addToSendQueue(
                  new C03PacketPlayer.C06PacketPlayerPosLook(
                     mc.thePlayer.posX,
                     mc.thePlayer.posY + (double)((float)this.positionSpeed.getValue()),
                     mc.thePlayer.posZ,
                     mc.thePlayer.rotationYaw,
                     mc.thePlayer.rotationPitch,
                     false
                  )
               );
            mc.thePlayer.setPosition(mc.thePlayer.posX, mc.thePlayer.posY + (double)((float)this.positionSpeed.getValue()), mc.thePlayer.posZ);
         }
      }
   }

}
