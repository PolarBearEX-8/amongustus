package net.augustus.modules.combat;

import java.awt.Color;
import java.util.OptionalInt;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.events.EventPacket;
import net.augustus.events.listeners.ClickListener;
import net.augustus.settings.RandomizedRangeSettings;
import net.augustus.ClientManagers;
import net.augustus.events.listeners.SilentSlotListener;
import net.augustus.events.EventClick;
import net.augustus.utils.world.BlockUtil;
import net.augustus.events.listeners.MoveInputListener;
import net.augustus.utils.combat.CombatTargetUtil;
import net.augustus.events.EventMoveInput;
import net.augustus.utils.player.ContainerSlotQuery;
import net.augustus.utils.player.SilentSlotManager;
import net.augustus.events.listeners.PacketListener;
import net.augustus.settings.BooleanValue;
import net.augustus.utils.TimeHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S07PacketRespawn;
import net.minecraft.network.play.server.S08PacketPlayerPosLook;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class AutoGapple extends Module implements ClickListener, PacketListener, SilentSlotListener, MoveInputListener {
   private final RandomizedRangeSettings health;
   private final BooleanValue considerTargetHealth;
   private final BooleanValue notWhileKillAura;
   private final BooleanValue whileRegeneration;
   private final BooleanValue jumpAndPressS;
   private final BooleanValue silentSlotSwitching;
   private final TimeHelper timeHelper;
   private int gappleSlot;

   public AutoGapple() {
       super("AutoGapple", "AutoApfel", new Color(100, 217, 203), Categorys.COMBAT);
      this.health = (new RandomizedRangeSettings(this, "Health", 13.0, 15.0, 0.0, 20.0, 0)).withoutNotInARow();
      this.considerTargetHealth = new BooleanValue(this, "ConsiderTargetHealth", false);
      this.notWhileKillAura = new BooleanValue(this, "NotWhileKillAura", false);
      this.whileRegeneration = new BooleanValue(this, "WhileRegeneration", false);
      this.jumpAndPressS = new BooleanValue(this, "JumpAndPressS", false);
      this.silentSlotSwitching = new BooleanValue(this, "SilentSlotSwitching", true);
      this.timeHelper = new TimeHelper();
      this.gappleSlot = -1;
      this.health.nextValue();
   }

   public void onEnable() {
   }

   public void onDisable() {
   }

   public void updateEventSubscriptions() {
      this.toggleEventSubscription(7);
      this.toggleEventSubscription(26);
      this.toggleEventSubscription(34);
      this.toggleEventSubscription(21, 100);
   }

   public void onPreMoveInput(EventMoveInput event) {
      if (mc.thePlayer.inventory.currentItem == this.gappleSlot || this.gappleSlot <= -1) {
         if (this.gappleSlot >= 0 && mc.isNotInGui() && mc.getTicksWithoutScreen() > 3 && this.jumpAndPressS.getValue()) {
            event.setMoveForward(-1.0F);
            event.setJump(true);
         }
      }
   }

   public void onClick(EventClick event) {
      if (mc.thePlayer.inventory.currentItem == this.gappleSlot || this.gappleSlot <= -1) {
         if (this.gappleSlot >= 0 && mc.isNotInGui() && mc.getTicksWithoutScreen() > 3) {
            event.setRightMouseDown(true);
         }
      }
   }

   public void onPacketReceive(EventPacket event) {
      Packet packet = event.getPacket();
      if (packet instanceof S07PacketRespawn || packet instanceof S08PacketPlayerPosLook) {
         this.timeHelper.reset();
      }
   }

   private boolean shouldEatGapple() {
      boolean flag = false;
      boolean flag2 = false;

      for (PotionEffect potionEffect : (Iterable<PotionEffect>)(mc.thePlayer.getActivePotionEffects())) {
         if (potionEffect.getPotionID() == Potion.regeneration.id) {
            flag = true;
         } else if (potionEffect.getPotionID() == Potion.absorption.id) {
            flag2 = true;
         }
      }

      boolean flag3 = true;
      if (this.considerTargetHealth.getValue()) {
         EntityLivingBase activeTarget = CombatTargetUtil.getActiveTarget();
         if (activeTarget != null && activeTarget.getCurrentHealth() < mc.thePlayer.getCurrentHealth()) {
            flag3 = false;
         }
      }

      return flag3
         && mc.thePlayer.getCurrentHealth() < (float)this.health.getLastIntValue()
         && (!this.notWhileKillAura.getValue() || !ClientManagers.moduleManager.killAura.isToggled() || ClientManagers.moduleManager.killAura.target == null)
         && !BlockUtil.isScaffoldActiveAndValid()
         && (!flag && !flag2 || flag2 && !flag || mc.thePlayer.getAbsorptionAmount() == 0.0F && this.whileRegeneration.getValue());
   }

   public int getGappleSlot() {
      return this.gappleSlot;
   }

   public void onPostSilentSlot() {
      this.gappleSlot = -1;
      if (this.shouldEatGapple() && mc.thePlayer.ticksExisted > 10 && mc.isNotInGui() && mc.getTicksWithoutScreen() > 3) {
         ContainerSlotQuery containerSlotQuery = ContainerSlotQuery.of(mc.thePlayer.inventoryContainer);
         containerSlotQuery = containerSlotQuery.fromSlot((byte)36);
         OptionalInt optionalInt = containerSlotQuery.filterItemExact(Item.getByNameOrId("golden_apple")).findFirstSlotIndex();
         if (optionalInt.isPresent()) {
            this.gappleSlot = optionalInt.getAsInt() - 36;
         }
      } else {
         this.health.nextValue();
      }

      if (!this.timeHelper.reached(250L)) {
         this.gappleSlot = -1;
      }

      if (this.gappleSlot >= 0) {
         SilentSlotManager.requestSlot(this, (byte)3, this.gappleSlot, this.silentSlotSwitching.getValue());
      }
   }


}
