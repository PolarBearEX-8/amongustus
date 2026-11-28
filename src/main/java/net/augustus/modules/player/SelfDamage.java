package net.augustus.modules.player;

import java.awt.Color;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.events.EventPacket;
import net.augustus.events.EventPacketDelay;
import net.augustus.events.listeners.PacketDelayListener;
import net.augustus.events.listeners.UpdateListener;
import net.augustus.utils.player.PlayerUtil;
import net.augustus.events.listeners.PacketListener;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.StringValue;
import net.augustus.settings.DoubleValue;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C03PacketPlayer;

public class SelfDamage extends Module implements UpdateListener, PacketListener, PacketDelayListener {
   private final StringValue modes;
   private final DoubleValue setPositionYAdd;
   private final DoubleValue motion;
   private final DoubleValue loops;
   private final DoubleValue yAddInLoopFirst;
   private final BooleanValue secondPacket;
   private final DoubleValue yAddInLoopSecond;
   private final BooleanValue finishPacket;
   private final DoubleValue yAddFinish;
   private final BooleanValue onlyOnGround;
   private final BooleanValue repeat;
   private final DoubleValue repeatTicks;
   private int ticksSinceDamage;
   private boolean damageTriggered;
   private int jumpCount;

   public SelfDamage() {
       super("SelfDamage", "SelbstSchaden", new Color(235, 64, 52), Categorys.PLAYER);
      this.modes = new StringValue(this, "Modes", "Motion", new String[]{"Motion", "Packet1", "Packet2", "Custom", "Fake", "SetPosition", "3Jump"});
      this.setPositionYAdd = new DoubleValue(this, "SetPositionYAdd", 3.1, -4.0, 4.0, 4).named("YAdd").visibleWhen(() -> this.isSetPositionYAddVisible());
      DoubleValue motionSetting = new DoubleValue(this, "Motion", 5.0, 0.0, 10.0, 2);
      SettingVisibilityCondition condition2 = () -> {
         return this.modes.getSelectedValue().equals("Motion");
      };
      this.motion = motionSetting.visibleWhen(condition2);
      this.loops = new DoubleValue(this, "Loops", 51.0, 1.0, 100.0, 0).visibleWhen(() -> this.isLoopsVisible());
      this.yAddInLoopFirst = new DoubleValue(this, "YAddInLoopFirst", 0.02, 0.0, 1.0, 4).visibleWhen(() -> this.isFirstPacketYAddVisible());
      this.secondPacket = (BooleanValue)new BooleanValue(this, "SecondPacket", true).withVisibility(() -> this.isSecondPacketVisible());
      this.yAddInLoopSecond = new DoubleValue(this, "YAddInLoopSecond", 0.02, 0.0, 1.0, 4).visibleWhen(() -> this.isSecondPacketYAddVisible());
      this.finishPacket = (BooleanValue)new BooleanValue(this, "FinishPacket", true).withVisibility(() -> this.isFinishPacketVisible());
      DoubleValue yAddFinishSetting = new DoubleValue(this, "YAddFinish", 0.0, 0.0, 1.0, 4);
      SettingVisibilityCondition condition8 = () -> {
         return this.modes.getSelectedValue().equals("Custom") && this.finishPacket.getValue();
      };
      this.yAddFinish = yAddFinishSetting.visibleWhen(condition8);
      this.onlyOnGround = (BooleanValue)new BooleanValue(this, "OnlyOnGround", false).withVisibility(() -> this.isOnlyOnGroundVisible());
      this.repeat = (BooleanValue)new BooleanValue(this, "Repeat", false).withVisibility(() -> this.isRepeatVisible());
      this.repeatTicks = new DoubleValue(this, "RepeatTicks", 140.0, 0.0, 200.0, 0).visibleWhen(() -> this.isRepeatTicksVisible());
      this.ticksSinceDamage = 0;
      this.damageTriggered = false;
      this.jumpCount = 0;
   }

   @Override
   public void onEnable() {
      this.damageTriggered = false;
      this.ticksSinceDamage = 0;
      this.jumpCount = 0;
   }

   @Override
   public void onDisable() {
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(43);
      this.toggleEventSubscription(26);
      this.toggleEventSubscription(25);
   }

   @Override
   public void onPostUpdate() {
      this.setSuffix(this.modes.getSelectedValue());
      if (!this.damageTriggered || this.modes.getSelectedValue().equals("3Jump")) {
         if (mc.thePlayer == null || this.onlyOnGround.getValue() && !mc.thePlayer.onGround && !this.modes.getSelectedValue().equals("3Jump")) {
            return;
         }

         this.applySelfDamage();
         this.damageTriggered = true;
      }

      if (this.damageTriggered && !this.modes.getSelectedValue().equals("3Jump")) {
         if (this.repeat.getValue() && (double)this.ticksSinceDamage > this.repeatTicks.getValue()) {
            this.ticksSinceDamage = 0;
            this.applySelfDamage();
         }

         this.ticksSinceDamage++;
      }
   }

   @Override
   public void onOutgoingPacketDelay(EventPacketDelay event) {
      if (this.modes.getSelectedValue().equals("3Jump") && this.jumpCount < 4) {
         event.setDelayed(true);
      }
   }

   private void applySelfDamage() {
      switch (this.modes.getSelectedValue()) {
         case "Motion":
            mc.thePlayer.motionY = (double)((float)this.motion.getValue());
            break;
         case "Packet1":
            PlayerUtil.damageSelfShort();
            break;
         case "Packet2":
            PlayerUtil.damageSelfPacket();
            break;
         case "Fake":
            mc.thePlayer.handleStatusUpdate((byte)2);
            break;
         case "Custom":
            for (int i = 0; (double)i < this.loops.getValue(); i++) {
               mc.thePlayer.sendQueue.addToSendQueueUnchecked(new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY + this.yAddInLoopFirst.getValue(), mc.thePlayer.posZ, false));
               if (this.secondPacket.getValue()) {
                  mc.thePlayer.sendQueue.addToSendQueueUnchecked(new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY + this.yAddInLoopSecond.getValue(), mc.thePlayer.posZ, false));
               }
            }

            if (this.finishPacket.getValue()) {
               mc.thePlayer.sendQueue.addToSendQueueUnchecked(new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY + this.yAddFinish.getValue(), mc.thePlayer.posZ, true));
            }
            break;
         case "SetPosition":
            mc.thePlayer.setPosition(mc.thePlayer.posX, mc.thePlayer.posY + this.setPositionYAdd.getValue(), mc.thePlayer.posZ);
            break;
         case "3Jump":
            if (mc.thePlayer.onGround) {
               if (this.jumpCount < 3) {
                  mc.thePlayer.jump();
                  PlayerUtil.triggerJumpStat();
               }

               this.jumpCount++;
            }
      }
   }

   @Override
   public void onPacketSend(EventPacket event) {
      if (this.modes.getSelectedValue().equals("3Jump") && this.jumpCount < 4) {
         Packet packet = event.getPacket();
         if (packet instanceof C03PacketPlayer) {
            ((C03PacketPlayer)packet).setOnGround(false);
         }
      }
   }

   private boolean isRepeatTicksVisible() {
      return this.repeat.getValue() && this.repeat.isVisible();
   }

   private boolean isRepeatVisible() {
      return !this.modes.getSelectedValue().equals("3Jump");
   }

   private boolean isOnlyOnGroundVisible() {
      return !this.modes.getSelectedValue().equals("3Jump");
   }

   private boolean isFinishPacketVisible() {
      return this.modes.getSelectedValue().equals("Custom");
   }

   private boolean isSecondPacketYAddVisible() {
      return this.modes.getSelectedValue().equals("Custom") && this.secondPacket.getValue();
   }

   private boolean isSecondPacketVisible() {
      return this.modes.getSelectedValue().equals("Custom");
   }

   private boolean isFirstPacketYAddVisible() {
      return this.modes.getSelectedValue().equals("Custom");
   }

   private boolean isLoopsVisible() {
      return this.modes.getSelectedValue().equals("Custom");
   }

   private boolean isSetPositionYAddVisible() {
      return this.modes.getSelectedValue().equals("SetPosition");
   }

}
