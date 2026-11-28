package net.augustus.modules.player;

import java.awt.Color;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.events.EventPacket;
import net.augustus.utils.rotation.RotationUtil;
import net.augustus.ClientManagers;
import net.augustus.utils.rotation.RotationController;
import net.augustus.events.listeners.UpdateListener;
import net.augustus.events.listeners.TickListener;
import net.augustus.events.listeners.PacketListener;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.utils.rotation.Rotation;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.StringValue;
import net.augustus.settings.DoubleValue;
import net.augustus.utils.TimeHelper;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C03PacketPlayer;

public class Regen extends Module implements UpdateListener, PacketListener, TickListener {
   public final StringValue mode;
   public final DoubleValue health;
   public final DoubleValue minHunger;
   public final DoubleValue packets;
   public final BooleanValue groundCheck;
   public final BooleanValue stopSendPacketsIfHpReached;
   public final DoubleValue minDelay;
   private final BooleanValue notWhileKillAura;
   private final BooleanValue notWhileScaffold;
   private final TimeHelper timeHelper;

   public Regen() {
       super("Regen", "Regeneration", new Color(224, 111, 49), Categorys.PLAYER);
      this.mode = new StringValue(this, "Mode", "Default", new String[]{"Default", "Legit", "Grim-1.17", "/heal"});
      this.health = new DoubleValue(this, "Health", 20.0, 0.0, 20.0, 0);
      this.minHunger = new DoubleValue(this, "MinHunger", 5.0, 0.0, 20.0, 0).visibleWhen(() -> this.isMinHungerVisible());
      this.packets = new DoubleValue(this, "Packets", 100.0, 0.0, 200.0, 0).visibleWhen(() -> this.isPacketsVisible());
      this.groundCheck = (BooleanValue)new BooleanValue(this, "GroundCheck", false).withVisibility(() -> this.isGroundCheckVisible());
      BooleanValue booleanValue2 = new BooleanValue(this, "StopSendPacketsIfHpReached", false);
      SettingVisibilityCondition condition4 = () -> {
         return !this.mode.getSelectedValue().equals("/heal");
      };
      this.stopSendPacketsIfHpReached = (BooleanValue)booleanValue2.withVisibility(condition4);
      DoubleValue minDelaySetting = new DoubleValue(this, "MinDelay", 30000.0, 0.0, 60000.0, 0);
      SettingVisibilityCondition condition5 = () -> {
         return this.mode.getSelectedValue().equals("/heal");
      };
      this.minDelay = minDelaySetting.visibleWhen(condition5);
      this.notWhileKillAura = new BooleanValue(this, "NotWhileKillAura", false);
      this.notWhileScaffold = new BooleanValue(this, "NotWhileScaffold", true);
      this.timeHelper = new TimeHelper();
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
      this.toggleEventSubscription(26);
      this.toggleEventSubscription(40, (short)10000);
   }

   @Override
   public void onPostUpdate() {
      this.setSuffix(this.mode.getSelectedValue());
      if (this.shouldRegenerate()) {
         for (int i = 0; (double)i < this.packets.getValue() && (this.shouldRegenerate() || !this.stopSendPacketsIfHpReached.getValue()); i++) {
            if (!this.groundCheck.getValue() || mc.thePlayer.onGround) {
               i += this.sendRegenPacket();
            }
         }
      }
   }

   @Override
   public void onPreTick() {
      if (mc.thePlayer != null) {
         if (this.mode.getSelectedValue().equals("/heal")) {
            if (this.timeHelper.reached(this.minDelay.getValue())
               && (double)mc.thePlayer.getCurrentHealth() < this.health.getValue()
               && (!ClientManagers.moduleManager.killAura.isToggled() || ClientManagers.moduleManager.killAura.target == null || !this.notWhileKillAura.getValue())
               && (!ClientManagers.moduleManager.scaffold.isToggled() || !this.notWhileScaffold.getValue())) {
               mc.thePlayer.sendChatMessage("/heal");
               this.timeHelper.reset();
               return;
            }
         }
      }
   }

   @Override
   public void onEarlyTick() {
      if (this.mode.getSelectedValue().equals("Grim-1.17") && this.shouldRegenerate()) {
         if (ClientManagers.rotationController.getPreviousRotation().equalsRotation(ClientManagers.rotationController.getCurrentRotation())) {
            RotationController rotationController = ClientManagers.rotationController;
            float yaw = ClientManagers.rotationController.getPreviousRotation().yaw;
            float f = ClientManagers.rotationController.getPreviousRotation().pitch > 0.0F ? ClientManagers.rotationController.getPreviousRotation().pitch + 0.2F : ClientManagers.rotationController.getPreviousRotation().pitch - 0.2F;
            float f2;
            rotationController.requestRotation(RotationUtil.applyGcdFix(yaw, f, f2 = ClientManagers.rotationController.getPreviousRotation().pitch, f2), (byte)1, this);
         }
      }
   }

   @Override
   public void onPacketSend(EventPacket event) {
      Packet packet = event.getPacket();
      if (mc.thePlayer != null && mc.theWorld != null) {
         if (this.mode.getSelectedValue().equals("Grim-1.17") && this.shouldRegenerate()) {
            if (packet instanceof C03PacketPlayer.C06PacketPlayerPosLook) {
               for (int i = 0; (double)i < this.packets.getValue() && (this.shouldRegenerate() || !this.stopSendPacketsIfHpReached.getValue()); i++) {
                  if (!this.groundCheck.getValue() || mc.thePlayer.onGround) {
                     mc.thePlayer.sendQueue.addToSendQueueUnchecked(packet);
                  }
               }
            }
         }
      }
   }

   private int sendRegenPacket() {
      switch (this.mode.getSelectedValue()) {
         case "Default":
            mc.thePlayer.sendQueue.addToSendQueueUnchecked(new C03PacketPlayer(true));
            break;
         case "Legit":
            mc.thePlayer.onUpdateWalkingPlayer(false);
      }

      return 0;
   }

   private boolean shouldRegenerate() {
      return (double)mc.thePlayer.getFoodStats().getFoodLevel() > this.minHunger.getValue()
         && (double)mc.thePlayer.getCurrentHealth() < this.health.getValue()
         && (!ClientManagers.moduleManager.killAura.isToggled() || ClientManagers.moduleManager.killAura.target == null || !this.notWhileKillAura.getValue())
         && (!ClientManagers.moduleManager.scaffold.isToggled() || !this.notWhileScaffold.getValue());
   }

   private boolean isGroundCheckVisible() {
      return !this.mode.getSelectedValue().equals("/heal");
   }

   private boolean isPacketsVisible() {
      return !this.mode.getSelectedValue().equals("/heal");
   }

   private boolean isMinHungerVisible() {
      return !this.mode.getSelectedValue().equals("/heal");
   }

}
