package net.augustus.modules.modes.spider;

import net.augustus.events.listeners.TickListener;
import net.augustus.settings.SettingVisibilityCondition;
import java.util.function.BiFunction;
import net.augustus.modules.movement.Spider;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.SettingGroup;
import net.augustus.settings.DoubleValue;
import net.minecraft.network.play.client.C03PacketPlayer;

public class JumpSpiderMode extends AbstractSpiderMode implements TickListener {
   public final DoubleValue motionToJump;
   public final BooleanValue customMotionAtJump;
   public final DoubleValue jumpMotion;
   public final BooleanValue groundPacket;

   public JumpSpiderMode(Spider spider, SettingGroup doubleValue) {
       super(spider, doubleValue, "Jump");
      this.motionToJump = new DoubleValue(this.parentModule, "MotionToJump", -0.2, -0.42, 0.42, 2, this.modeSetting).visibleWhen(() -> this.isSelected());
      this.customMotionAtJump = (BooleanValue)new BooleanValue(this.parentModule, "CustomMotionAtJump", false, this.modeSetting).withVisibility(() -> this.isSelected());
      this.jumpMotion = new DoubleValue(this.parentModule, "JumpMotion", 0.42, 0.0, 2.0, 3, this.modeSetting).visibleWhen(() -> this.isJumpMotionVisible());
      this.groundPacket = (BooleanValue)new BooleanValue(this.parentModule, "GroundPacket", false, this.modeSetting).withVisibility(() -> this.isSelected());
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(40);
   }

   @Override
   public void onEnable() {
   }

   @Override
   public void onDisable() {
   }

   public static BiFunction factory() {
      return (obj, obj2) -> new JumpSpiderMode((Spider)obj, (SettingGroup)obj2);
   }

   @Override
   public void onTick() {
      if (this.canClimbWall()) {
         if (mc.thePlayer.onGround) {
            mc.thePlayer.jump();
         } else if (!(mc.thePlayer.motionY >= this.motionToJump.getValue())) {
            if (this.customMotionAtJump.getValue()) {
               mc.thePlayer.motionY = this.jumpMotion.getValue();
            } else {
               mc.thePlayer.jump();
            }

            if (this.groundPacket.getValue()) {
               mc.thePlayer.sendQueue.addToSendQueueUnchecked(new C03PacketPlayer(true));
            }
         }
      }
   }

   private boolean isJumpMotionVisible() {
      return this.isSelected() && this.customMotionAtJump.getValue();
   }

}
