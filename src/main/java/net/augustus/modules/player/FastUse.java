package net.augustus.modules.player;

import java.awt.Color;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.events.EventPacket;
import net.augustus.utils.rotation.RotationUtil;
import net.augustus.ClientManagers;
import net.augustus.settings.MultiOptionSetting;
import net.augustus.utils.MoveUtil;
import net.augustus.events.listeners.TimerListener;
import net.augustus.utils.rotation.RotationController;
import net.augustus.events.EventTimer;
import net.augustus.events.listeners.UpdateListener;
import net.augustus.events.listeners.TickListener;
import net.augustus.settings.options.FastUseItemOption;
import net.augustus.events.listeners.PacketListener;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.utils.rotation.Rotation;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.StringValue;
import net.augustus.settings.DoubleValue;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemBucketMilk;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemPotion;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.util.MathHelper;

public class FastUse extends Module implements UpdateListener, TimerListener, PacketListener, TickListener {
   public final StringValue mode;
   public final StringValue packetMode;
   public final BooleanValue onlyWhileMoving;
   private final DoubleValue packetLoops;
   public final DoubleValue packets;
   public final DoubleValue grimPackets;
   public final DoubleValue newPackets;
   public final DoubleValue timePackets;
   public final BooleanValue onlyAtFullLife;
   private final BooleanValue onlyOnGround;
   private final MultiOptionSetting items;
   private int sentPacketCount;

   public FastUse() {
       super("FastUse", "SchnellBenutzen", new Color(173, 121, 81), Categorys.PLAYER);
      this.mode = new StringValue(this, "Mode", "Packet", new String[]{"Packet", "TimeManipulation", "NewPacket", "Grim-1.17"});
      this.packetMode = (StringValue)new StringValue(this, "PacketMode", "Default", new String[]{"Default", "Legit"}).withVisibility(() -> this.isPacketModeVisible());
      this.onlyWhileMoving = new BooleanValue(this, "OnlyWhileMoving", false);
      DoubleValue packetLoopsSetting = new DoubleValue(this, "PacketLoops", 1.0, 1.0, 10.0, 0);
      SettingVisibilityCondition condition2 = () -> {
         return this.mode.getSelectedValue().equals("Packet");
      };
      this.packetLoops = packetLoopsSetting.visibleWhen(condition2);
      this.packets = new DoubleValue(this, "Packets", 20.0, 2.0, 31.0, 0).visibleWhen(() -> this.isPacketsVisible());
      DoubleValue grimPacketsSetting = new DoubleValue(this, "GrimPackets", 20.0, 2.0, 31.0, 0).named("Packets");
      SettingVisibilityCondition condition4 = () -> {
         return this.mode.getSelectedValue().equals("Grim-1.17");
      };
      this.grimPackets = grimPacketsSetting.visibleWhen(condition4);
      this.newPackets = new DoubleValue(this, "NewPackets", 20.0, 2.0, 31.0, 0).named("Packets").visibleWhen(() -> this.isNewPacketsVisible());
      this.timePackets = new DoubleValue(this, "TimePackets", 20.0, 2.0, 31.0, 0).named("Packets").visibleWhen(() -> this.isTimePacketsVisible());
      this.onlyAtFullLife = new BooleanValue(this, "OnlyAtFullLife", false);
      this.onlyOnGround = new BooleanValue(this, "OnlyOnGround", false);
      this.items = new MultiOptionSetting(this, "Items", FastUseItemOption.values());
      this.sentPacketCount = 0;
   }

   @Override
   public void onEnable() {
   }

   @Override
   public void onDisable() {
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(43);
      this.toggleEventSubscription(41);
      this.toggleEventSubscription(26);
      this.toggleEventSubscription(40, (short)10001);
   }

   @Override
   public void onEarlyTick() {
      if (this.mode.getSelectedValue().equals("Grim-1.17")) {
         if (this.canFastUse()) {
            if (ClientManagers.rotationController.getPreviousRotation().equalsRotation(ClientManagers.rotationController.getCurrentRotation())) {
               RotationController rotationController = ClientManagers.rotationController;
               float yaw = ClientManagers.rotationController.getPreviousRotation().yaw;
               float f = ClientManagers.rotationController.getPreviousRotation().pitch > 0.0F ? ClientManagers.rotationController.getPreviousRotation().pitch + 0.2F : ClientManagers.rotationController.getPreviousRotation().pitch - 0.2F;
               float f2;
               rotationController.requestRotation(RotationUtil.applyGcdFix(yaw, f, f2 = ClientManagers.rotationController.getPreviousRotation().pitch, f2), (byte)1, this);
            }

            return;
         }
      }
   }

   @Override
   public void onPacketSend(EventPacket event) {
      Packet packet = event.getPacket();
      if (this.mode.getSelectedValue().equals("Grim-1.17")) {
         if (this.canFastUse()) {
            if (packet instanceof C03PacketPlayer.C06PacketPlayerPosLook) {
               int value = (int)this.grimPackets.getValue();
               if (mc.thePlayer.getItemInUseCount() < value) {
                  value = mc.thePlayer.getItemInUseCount();
               }

               mc.thePlayer.setItemInUseCount(mc.thePlayer.getItemInUseCount() - value);

               for (int i = 0; i < value; i++) {
                  mc.thePlayer.sendQueue.addToSendQueueUnchecked(packet);
               }
            }

            return;
         }
      }
   }

   @Override
   public void onPreUpdate() {
      if (!this.canFastUse()) {
         this.sentPacketCount = 0;
      } else {
         System.out.println(mc.thePlayer.getItemInUseCount());
         switch (this.mode.getSelectedValue()) {
            case "Packet":
               int n = MathHelper.clamp_int((int)this.packets.getValue(), 2, 31);
               boolean flag = false;
               if ((double)mc.thePlayer.getItemInUseDuration() > 32.0 - (double)n * this.packetLoops.getValue()) {
                  for (int i = 0; i < n; i++) {
                     i += this.sendUseTickPacket();
                     this.sentPacketCount++;
                     if (mc.thePlayer.getItemInUseDuration() + this.sentPacketCount >= 32) {
                        flag = true;
                     }

                     if (flag) {
                        break;
                     }
                  }

                  if (flag) {
                     mc.playerController.onStoppedUsingItem(mc.thePlayer);
                     this.sentPacketCount = 0;
                  }
               }
               break;
            case "NewPacket":
               int value = (int)this.newPackets.getValue();
               if (mc.thePlayer.getItemInUseCount() < value) {
                  value = mc.thePlayer.getItemInUseCount();
               }

               mc.thePlayer.setItemInUseCount(mc.thePlayer.getItemInUseCount() - value);

               for (int j = 0; j < value; j++) {
                  this.sendUseTickPacket();
               }
         }
      }
   }

   @Override
   public void onTimerSpeed(EventTimer event) {
   }

   @Override
   public void onTimerTick(EventTimer event) {
      if (this.mode.getSelectedValue().equalsIgnoreCase("TimeManipulation")) {
         if (!this.canFastUse()) {
            return;
         }

         int n = MathHelper.clamp_int((int)this.timePackets.getValue(), 2, 31);
         if (mc.thePlayer.getItemInUseDuration() == 32 - n) {
            event.setExtraTicks(n);
         }
      }
   }

   private int sendUseTickPacket() {
      switch (this.packetMode.getSelectedValue()) {
         case "Default":
            mc.thePlayer.sendQueue.addToSendQueueUnchecked(new C03PacketPlayer(true));
            break;
         case "Legit":
            mc.thePlayer.onUpdateWalkingPlayer(false);
      }

      return 0;
   }

   private boolean canFastUse() {
      return mc.theWorld != null
         && mc.thePlayer != null
         && (mc.thePlayer.onGround || !this.onlyOnGround.getValue())
         && mc.thePlayer.isUsingItem()
         && mc.thePlayer.getCurrentEquippedItem() != null
         && (
            mc.thePlayer.getCurrentEquippedItem().getItem() instanceof ItemFood && FastUseItemOption.isOptionSelected(FastUseItemOption.FOOD)
               || (
                     mc.thePlayer.getCurrentEquippedItem().getItem() instanceof ItemPotion
                        || mc.thePlayer.getCurrentEquippedItem().getItem() instanceof ItemBucketMilk
                  )
                  && FastUseItemOption.isOptionSelected(FastUseItemOption.OTHER)
               || mc.thePlayer.getCurrentEquippedItem().getItem() instanceof ItemBow && FastUseItemOption.isOptionSelected(FastUseItemOption.BOW)
         )
         && (MoveUtil.isMoving() || !this.onlyWhileMoving.getValue())
         && (!mc.thePlayer.shouldHeal() || !this.onlyAtFullLife.getValue());
   }

   private boolean isTimePacketsVisible() {
      return this.mode.getSelectedValue().equals("TimeManipulation");
   }

   private boolean isNewPacketsVisible() {
      return this.mode.getSelectedValue().equals("NewPacket");
   }

   private boolean isPacketsVisible() {
      return this.mode.getSelectedValue().equals("Packet");
   }

   private boolean isPacketModeVisible() {
      return this.mode.getSelectedValue().equals("Packet") || this.mode.getSelectedValue().equals("NewPacket");
   }

}
