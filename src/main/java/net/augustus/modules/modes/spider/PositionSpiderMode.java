package net.augustus.modules.modes.spider;

import net.minecraft.network.play.client.C03PacketPlayer;
import net.augustus.events.EventMotion;
import net.augustus.events.listeners.MotionListener;
import net.augustus.settings.SettingVisibilityCondition;
import java.util.function.BiFunction;
import net.augustus.modules.movement.Spider;
import net.augustus.settings.SettingGroup;
import net.augustus.settings.DoubleValue;

public class PositionSpiderMode extends AbstractSpiderMode implements MotionListener {
   public final DoubleValue positionSpeed;
   public final DoubleValue tickDelayPosition;

   public PositionSpiderMode(Spider spider, SettingGroup doubleValue) {
       super(spider, doubleValue, "Position");
      this.positionSpeed = new DoubleValue(this.parentModule, "PositionSpeed", 0.4, 0.2, 2.0, 3, this.modeSetting).named("Speed").visibleWhen(() -> this.isSelected());
      this.tickDelayPosition = new DoubleValue(this.parentModule, "TickDelayPosition", 0.0, 0.0, 20.0, 0, this.modeSetting).named("TickDelay").visibleWhen(() -> this.isSelected());
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

   public static BiFunction factory() {
      return (obj, obj2) -> new PositionSpiderMode((Spider)obj, (SettingGroup)obj2);
   }

   @Override
   public void onPreMotion(EventMotion event) {
      if (this.canClimbWall()) {
         if ((double)mc.thePlayer.ticksExisted % (this.tickDelayPosition.getValue() + 1.0) == 0.0) {
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
