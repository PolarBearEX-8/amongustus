package net.augustus.modules.combat;

import java.awt.Color;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.events.EventGameLoop;
import net.augustus.events.listeners.GameLoopListener;
import net.augustus.modules.combat.helper.MurderWeaponSlotUtil;
import net.augustus.events.EventAttackEntity;
import net.augustus.events.listeners.TickListener;
import net.augustus.events.listeners.AttackEntityListener;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.DoubleValue;
import net.minecraft.network.play.client.C09PacketHeldItemChange;

public class SilentMurder extends Module implements AttackEntityListener, GameLoopListener, TickListener {
   private final DoubleValue tickDelay;
   private final BooleanValue timer;
   private final BooleanValue bypassBalanceChecks;
   private int lastAttackTick;
   private int previousHotbarSlot;
   private boolean pendingBalanceSkip;
   private boolean usedTimerBoost;

   public SilentMurder() {
       super("SilentMurder", "LautloserMord", new Color(209, 140, 171), Categorys.COMBAT);
      this.tickDelay = new DoubleValue(this, "TickDelay", 10.0, 0.0, 20.0, 0);
      this.timer = new BooleanValue(this, "Timer", false);
      BooleanValue booleanValue = new BooleanValue(this, "Bypass balance checks", true);
      BooleanValue timer2 = this.timer;
      this.bypassBalanceChecks = (BooleanValue)booleanValue.withVisibility(() -> timer2.getValue());
      this.lastAttackTick = 0;
      this.previousHotbarSlot = -1;
      this.pendingBalanceSkip = false;
      this.usedTimerBoost = false;
   }

   @Override
   public void onEnable() {
   }

   @Override
   public void onDisable() {
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(1);
      this.toggleEventSubscription(16);
      this.toggleEventSubscription(40);
   }

   @Override
   public void onEarlyTick() {
      if (mc.thePlayer != null && this.previousHotbarSlot != -1 && this.timer.getValue()) {
         mc.thePlayer.sendQueue.addToSendQueue(new C09PacketHeldItemChange(this.previousHotbarSlot));
         this.previousHotbarSlot = -1;
         if (this.usedTimerBoost) {
            if (this.bypassBalanceChecks.getValue()) {
               this.pendingBalanceSkip = true;
            }

            this.usedTimerBoost = false;
         }
      }
   }

   @Override
   public void onAttackEntity(EventAttackEntity event) {
      this.previousHotbarSlot = -1;
      int n = MurderWeaponSlotUtil.findMurderWeaponSlot();
      if (n != -1
         && n != mc.thePlayer.inventory.currentItem
         && (this.lastAttackTick > mc.thePlayer.ticksExisted || (double)Math.abs(mc.thePlayer.ticksExisted - this.lastAttackTick) > this.tickDelay.getValue())) {
         this.lastAttackTick = mc.thePlayer.ticksExisted;
         this.previousHotbarSlot = mc.thePlayer.inventory.currentItem;
         mc.thePlayer.sendQueue.addToSendQueue(new C09PacketHeldItemChange(n));
         if (this.timer.getValue() && mc.getTimer().elapsedTicks == 1) {
            mc.getTimer().elapsedTicks = 2;
            this.usedTimerBoost = true;
         }
      }
   }

   @Override
   public void onPostAttackEntity() {
      if (!this.timer.getValue()) {
         if (MurderWeaponSlotUtil.findMurderWeaponSlot() != -1 && this.previousHotbarSlot != -1) {
            mc.thePlayer.sendQueue.addToSendQueue(new C09PacketHeldItemChange(this.previousHotbarSlot));
         }

         this.previousHotbarSlot = -1;
      }
   }

   @Override
   public void onGameLoop(EventGameLoop event) {
      if (this.pendingBalanceSkip && event.getElapsedTicks() > 0) {
         event.setElapsedTicks(event.getElapsedTicks() - 1);
         event.setRenderPartialTicks(0.0F);
         this.pendingBalanceSkip = false;
      }
   }

}
