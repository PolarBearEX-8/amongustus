package net.augustus.modules.combat;

import java.awt.Color;
import java.util.function.BiFunction;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.settings.ModeSetting;
import net.augustus.modules.modes.criticals.PacketCriticalsMode;
import net.augustus.modules.modes.criticals.PacketEditCriticalsMode;
import net.augustus.modules.modes.criticals.LagCriticalsMode;
import net.augustus.modules.modes.criticals.NoGroundCriticalsMode;
import net.augustus.modules.modes.criticals.IntaveLowHopCriticalsMode;
import net.augustus.modules.modes.criticals.CustomPacketCriticalsMode;
import net.augustus.events.listeners.TickListener;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.settings.BooleanValue;

public class Criticals extends Module implements TickListener {
   public final ModeSetting mode;
   public final BooleanValue onlyWhileJumpKeyPressed;
   public final BooleanValue onlyWhileKillAuraTarget;
   public final BooleanValue criticalsInAir;
   public final BooleanValue criticalsOnGround;

   public Criticals() {
       super("Criticals", "KritischeTreffer", Color.RED, Categorys.COMBAT);
      BiFunction[] biFunctions = new BiFunction[]{
         NoGroundCriticalsMode.factory(),
         PacketCriticalsMode.factory(),
         CustomPacketCriticalsMode.factory(),
         PacketEditCriticalsMode.factory(),
         IntaveLowHopCriticalsMode.factory(),
         LagCriticalsMode.factory()
      };
      this.mode = new ModeSetting(this, "Mode", "NoGround", biFunctions);
      BooleanValue booleanValue = new BooleanValue(this, "OnlyWhileJumpKeyPressed", false);
      SettingVisibilityCondition condition = () -> {
         return !this.mode.getSelectedValue().equals("Lag");
      };
      this.onlyWhileJumpKeyPressed = (BooleanValue)booleanValue.withVisibility(condition);
      this.onlyWhileKillAuraTarget = (BooleanValue)new BooleanValue(this, "onlyWhileKillAuraTarget", false).withVisibility(() -> this.isKillAuraTargetSettingVisible());
      this.criticalsInAir = (BooleanValue)new BooleanValue(this, "CriticalsInAir", true).withVisibility(() -> this.isInAirSettingVisible());
      this.criticalsOnGround = (BooleanValue)new BooleanValue(this, "CriticalsOnGround", true).withVisibility(() -> this.isOnGroundSettingVisible());
   }

   @Override
   public void onEnable() {
   }

   @Override
   public void onDisable() {
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(40);
   }

   @Override
   public void onEarlyTick() {
      this.setSuffix(this.mode.getSelectedValue());
   }

   private boolean isOnGroundSettingVisible() {
      return !this.mode.getSelectedValue().equals("Lag");
   }

   private boolean isInAirSettingVisible() {
      return !this.mode.getSelectedValue().equals("Lag");
   }

   private boolean isKillAuraTargetSettingVisible() {
      return !this.mode.getSelectedValue().equals("Lag");
   }


}
