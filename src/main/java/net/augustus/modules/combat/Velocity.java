package net.augustus.modules.combat;

import java.awt.Color;
import java.util.function.BiFunction;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.events.EventPacket;
import net.augustus.ClientManagers;
import net.augustus.settings.MultiModeSetting;
import net.augustus.settings.MultiOptionSetting;
import net.augustus.events.EventPacketDelay;
import net.augustus.settings.options.VelocityLagTimingOption;
import net.augustus.modules.modes.velocity.PushGroundVelocityMode;
import net.augustus.modules.modes.velocity.KarhuVelocityMode;
import net.augustus.modules.modes.velocity.FenceVelocityMode;
import net.augustus.modules.modes.velocity.PushVelocityMode;
import net.augustus.modules.modes.velocity.PolarBlockVelocityMode;
import net.augustus.modules.modes.velocity.ReverseVelocityMode;
import net.augustus.modules.modes.velocity.SpoofVelocityMode;
import net.augustus.modules.modes.velocity.HypixelTestVelocityMode;
import net.augustus.modules.modes.velocity.BasicVelocityMode;
import net.augustus.modules.modes.velocity.IntavePolarFlagVelocityMode;
import net.augustus.modules.modes.velocity.LegitVelocityMode;
import net.augustus.modules.modes.velocity.IntaveVelocityMode;
import net.augustus.modules.modes.velocity.WaterVelocityMode;
import net.augustus.modules.modes.velocity.LadderVelocityMode;
import net.augustus.modules.modes.velocity.OldMinemenVelocityMode;
import net.augustus.modules.modes.velocity.Grim117VelocityMode;
import net.augustus.modules.modes.velocity.BoundingBoxVelocityMode;
import net.augustus.modules.modes.velocity.Intave13VelocityMode;
import net.augustus.modules.modes.velocity.SafeWalkVelocityMode;
import net.augustus.modules.modes.velocity.CustomMotionVelocityMode;
import net.augustus.events.listeners.PacketDelayListener;
import net.augustus.events.listeners.MoveInputListener;
import net.augustus.events.EventMoveInput;
import net.augustus.events.listeners.TickListener;
import net.augustus.settings.options.VelocityCancelPacketOption;
import net.augustus.events.listeners.PacketListener;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.StringValue;
import net.augustus.settings.SettingGroup;
import net.augustus.settings.DoubleValue;
import net.augustus.utils.TimeHelper;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S08PacketPlayerPosLook;
import net.minecraft.network.play.server.S12PacketEntityVelocity;
import net.minecraft.network.play.server.S27PacketExplosion;
import net.minecraft.network.play.server.S29PacketSoundEffect;

public class Velocity extends Module implements MoveInputListener, PacketListener, TickListener, PacketDelayListener {
   public final MultiModeSetting mode;
   public final BooleanValue notWhileSPressed;
   public final BooleanValue notWhileLongJump;
   public final BooleanValue ignoreFire;
   public final BooleanValue cancelDamageSoundsFromOtherPlayers;
   private final SettingGroup lag;
   private final DoubleValue lagDuration;
   private final DoubleValue lagDurationExplosion;
   private final MultiOptionSetting lagTimings;
   private final SettingGroup packetCancelingGroup;
   private final MultiOptionSetting packetToCancel;
   private final TimeHelper velocityLagTimer;
   private final TimeHelper explosionLagTimer;
   public int attackSlowdownCount;
   public boolean movingBackward;

   public Velocity() {
       super("Velocity", "Rückstoß", new Color(83, 102, 109, 255), Categorys.COMBAT);
      BiFunction[] biFunctions = new BiFunction[]{
         BasicVelocityMode.factory(),
         LegitVelocityMode.factory(),
         PushGroundVelocityMode.factory(),
         PushVelocityMode.factory(),
         IntavePolarFlagVelocityMode.factory(),
         IntaveVelocityMode.factory(),
         Intave13VelocityMode.factory(),
         WaterVelocityMode.factory(),
         LadderVelocityMode.factory(),
         ReverseVelocityMode.factory(),
         SpoofVelocityMode.factory(),
         SafeWalkVelocityMode.factory(),
         OldMinemenVelocityMode.factory(),
         KarhuVelocityMode.factory(),
         CustomMotionVelocityMode.factory(),
         BoundingBoxVelocityMode.factory(),
         Grim117VelocityMode.factory(),
         HypixelTestVelocityMode.factory(),
         PolarBlockVelocityMode.factory(),
         FenceVelocityMode.factory()
      };
      this.mode = new MultiModeSetting(this, "Mode", biFunctions);
      this.notWhileSPressed = (BooleanValue)(new BooleanValue(this, "NotWhileSPressed", false)).markVisualOnly();
      this.notWhileLongJump = (BooleanValue)(new BooleanValue(this, "NotWhileLongJump", false)).markVisualOnly();
      this.ignoreFire = new BooleanValue(this, "IgnoreFire", true);
      this.cancelDamageSoundsFromOtherPlayers = (BooleanValue)(new BooleanValue(this, "CancelDamageSoundsFromOtherPlayers", false)).markVisualOnly();
      this.lag = new SettingGroup(this, "Lag");
      this.lagDuration = new DoubleValue(this, "LagDuration", 0.0, 0.0, 400.0, 0, this.lag);
      this.lagDurationExplosion = new DoubleValue(this, "LagDurationExplosion", 0.0, 0.0, 400.0, 0, this.lag);
      this.lagTimings = new MultiOptionSetting(this, "LagTimings", VelocityLagTimingOption.values(), this.lag);
      this.packetCancelingGroup = (SettingGroup)new SettingGroup(this, "PacketCancelingGroup", true).withDisplayName("PacketCanceling");
      this.packetToCancel = new MultiOptionSetting(this, "PacketToCancel", VelocityCancelPacketOption.values(), this.packetCancelingGroup).named("PacketsToCancel");
      this.velocityLagTimer = new TimeHelper();
      this.explosionLagTimer = new TimeHelper();
      this.attackSlowdownCount = 0;
      this.movingBackward = false;

      for (VelocityCancelPacketOption option : VelocityCancelPacketOption.values()) {
         SettingGroup settingGroup = new SettingGroup(this, option.getName(), this.packetCancelingGroup).visibleWhen(option::isSelected);
         VelocityCancelPacketOption.setCancelTypeSetting(option, (StringValue)new StringValue(
            this, option.getName() + "CancelType", "None", new String[]{"None", "OnlyOnGround", "OnlyInAir", "Always"}, settingGroup
         ).withDisplayName("CancelType"));
         VelocityCancelPacketOption.setPacketCountSetting(option, new DoubleValue(this, option.getName() + "PacketCount", 6.0, 1.0, 10.0, 0, settingGroup).named("PacketCount").visibleWhen(() -> isPacketCountVisible(option)));
      }
   }

   @Override
   public void onEnable() {
   }

   @Override
   public void onDisable() {
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(21);
      this.toggleEventSubscription(26);
      this.toggleEventSubscription(40);
      this.toggleEventSubscription(25);
   }

   private boolean canProcessVelocity(EntityPlayerSP player) {
      return player == null
         ? false
         : (!this.notWhileSPressed.getValue() || !this.movingBackward)
            && (!player.isBurning() || !this.ignoreFire.getValue())
            && (!ClientManagers.moduleManager.longJump.isToggled() || !this.notWhileLongJump.getValue());
   }

   @Override
   public void onTick() {
      this.attackSlowdownCount = 0;
   }

   @Override
   public void onIncomingPacketDelay(EventPacketDelay event) {
      EntityPlayerSP thePlayer = mc.thePlayer;
      if (this.canProcessVelocity(thePlayer)) {
         Packet packet = event.getPacket();
         if (this.lagDuration.getValue() > 0.0 && packet instanceof S12PacketEntityVelocity && ((S12PacketEntityVelocity)packet).getEntityID() == thePlayer.getEntityId()) {
            this.velocityLagTimer.reset();
         }

         if (this.lagDurationExplosion.getValue() > 0.0 && packet instanceof S27PacketExplosion) {
            this.explosionLagTimer.reset();
         }

         if (thePlayer.onGround && VelocityLagTimingOption.isOptionSelected(VelocityLagTimingOption.ON_GROUND) || !thePlayer.onGround && VelocityLagTimingOption.isOptionSelected(VelocityLagTimingOption.IN_AIR)) {
            TimeHelper velocityLagTimer2 = this.velocityLagTimer;
            event.setDelayed(!velocityLagTimer2.reached(this.lagDuration.getValue()) && thePlayer.ticksExisted > 20);
            velocityLagTimer2 = this.explosionLagTimer;
            event.setDelayed(!velocityLagTimer2.reached(this.lagDurationExplosion.getValue()) && thePlayer.ticksExisted > 20);
         }
      }
   }

   @Override
   public void onPacketReceive(EventPacket event) {
      if (this.canProcessVelocity(mc.thePlayer)) {
         Packet packet = event.getPacket();
         if (mc.thePlayer != null && mc.thePlayer.ticksExisted > 20) {
            this.handlePacketCanceling(event);
         }

         if (packet instanceof S29PacketSoundEffect && this.cancelDamageSoundsFromOtherPlayers.getValue()) {
            S29PacketSoundEffect packet2 = (S29PacketSoundEffect)packet;
            if (packet2.getSoundName().equalsIgnoreCase("game.player.hurt") || packet2.getSoundName().equalsIgnoreCase("game.player.die")) {
               event.cancel();
            }
         }
      }
   }

   @Override
   public void onPreMoveInput(EventMoveInput event) {
      this.movingBackward = event.getMoveForward() < 0.0F;
   }

   public void handlePacketCanceling(EventPacket event) {
      Packet packet = event.getPacket();
      if (packet instanceof S12PacketEntityVelocity) {
         S12PacketEntityVelocity packet2 = (S12PacketEntityVelocity)packet;
         if (packet2.getEntityID() == mc.thePlayer.getEntityId() && packet2.getMotionX() != 0 && packet2.getMotionZ() != 0) {
            for (VelocityCancelPacketOption option : VelocityCancelPacketOption.values()) {
               if (this.shouldCancelPacketOption(option)) {
                  VelocityCancelPacketOption.setRemainingCancelCount(option, (int)VelocityCancelPacketOption.getPacketCountSetting(option).getValue());
               }
            }

            return;
         }
      } else if (packet instanceof S08PacketPlayerPosLook) {
         for (VelocityCancelPacketOption option2 : VelocityCancelPacketOption.values()) {
            VelocityCancelPacketOption.setRemainingCancelCount(option2, (byte)0);
         }

         return;
      }

      for (VelocityCancelPacketOption option3 : VelocityCancelPacketOption.values()) {
         if (packet.getClass().equals(VelocityCancelPacketOption.getPacketClass(option3))) {
            if (this.shouldCancelPacketOption(option3) && VelocityCancelPacketOption.getRemainingCancelCount(option3) > 0) {
               event.cancel();
               VelocityCancelPacketOption.decrementRemainingCancelCount(option3);
            }

            return;
         }
      }
   }

   private boolean shouldCancelPacketOption(VelocityCancelPacketOption option) {
      return option.isSelected()
         && (
            VelocityCancelPacketOption.getCancelTypeSetting(option).getSelectedValue().equals("Always")
               || VelocityCancelPacketOption.getCancelTypeSetting(option).getSelectedValue().equals("OnlyInAir") && !mc.thePlayer.onGround
               || VelocityCancelPacketOption.getCancelTypeSetting(option).getSelectedValue().equals("OnlyOnGround") && mc.thePlayer.onGround
         );
   }

   private static boolean isPacketCountVisible(VelocityCancelPacketOption option) {
      return !VelocityCancelPacketOption.getCancelTypeSetting(option).getSelectedValue().equals("None");
   }


}
