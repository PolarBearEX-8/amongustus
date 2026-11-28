package net.augustus.modules.modes.criticals;

import net.minecraft.util.MovingObjectPosition;
import net.augustus.events.EventClick;
import net.augustus.events.EventLoadWorld;
import net.augustus.events.EventPacketDelay;
import net.augustus.events.listeners.ClickListener;
import net.augustus.events.listeners.LoadWorldListener;
import net.augustus.events.listeners.PacketDelayListener;
import net.augustus.events.listeners.RayCastListener;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.utils.combat.CombatTargetUtil;
import java.util.function.BiFunction;
import net.augustus.modules.combat.Criticals;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.SettingGroup;
import net.augustus.settings.DoubleValue;
import net.augustus.utils.TimeHelper;
import net.minecraft.entity.EntityLivingBase;

public class LagCriticalsMode extends CriticalsMode implements PacketDelayListener, RayCastListener, LoadWorldListener, ClickListener {
   private final DoubleValue lagDuration;
   private final BooleanValue forceHit;
   private static boolean delayingPackets = true;
   private final TimeHelper lagDurationTimer;
   private final TimeHelper targetTimeoutTimer;
   private EntityLivingBase currentTarget;
   private int queuedClickCount;
   private boolean forceLagActive;

   public LagCriticalsMode(Criticals criticals, SettingGroup doubleValue) {
       super(criticals, doubleValue, "Lag");
      this.lagDuration = new DoubleValue(this.parentModule, "LagDuration", 200.0, 0.0, 400.0, 0, this.modeSetting).visibleWhen(() -> this.isSelected());
      this.forceHit = (BooleanValue)new BooleanValue(this.parentModule, "ForceHit", true, this.modeSetting).withVisibility(() -> this.isSelected());
      this.lagDurationTimer = new TimeHelper();
      this.targetTimeoutTimer = new TimeHelper();
      this.currentTarget = null;
      this.queuedClickCount = 0;
      this.forceLagActive = false;
   }

   public static BiFunction factory() {
      return (obj, obj2) -> new LagCriticalsMode((Criticals)obj, (SettingGroup)obj2);
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(25);
      this.toggleEventSubscription(29);
      this.toggleEventSubscription(7, (short)10000);
      this.toggleEventSubscription(17);
   }

   @Override
   public void onEnable() {
   }

   @Override
   public void onDisable() {
      this.currentTarget = null;
      this.stopLagging();
   }

   @Override
   public void onOutgoingPacketDelay(EventPacketDelay event) {
      event.setDelayed(delayingPackets);
   }

   @Override
   public void onRayCast() {
      if (mc.objectMouseOver == null) {
         this.stopLagging();
      } else {
         EntityLivingBase activeTargetOrDefault = CombatTargetUtil.getActiveTargetOrDefault(mc.objectMouseOver.entityHit instanceof EntityLivingBase ? (EntityLivingBase)mc.objectMouseOver.entityHit : null);
         if (activeTargetOrDefault != null) {
            this.currentTarget = activeTargetOrDefault;
            this.targetTimeoutTimer.reset();
         }

         if (this.targetTimeoutTimer.reached(500L)) {
            this.currentTarget = null;
         }

         if (this.currentTarget == null) {
            this.stopLagging();
         } else {
            if ((this.currentTarget.hurtTime > 2 || this.forceLagActive)
               && mc.objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.ENTITY
               && mc.objectMouseOver.entityHit != null
               && mc.objectMouseOver.entityHit.getEntityId() == this.currentTarget.getEntityId()
               && this.shouldCrit()) {
               if (!delayingPackets) {
                  this.lagDurationTimer.reset();
               }

               delayingPackets = true;
            }

            if ((this.currentTarget.hurtTime == 0 || this.currentTarget.hurtTime == 10) && !this.forceLagActive) {
               this.stopLagging();
            }

            if (this.lagDurationTimer.reached(this.lagDuration.getValue())) {
               this.stopLagging();
            }
         }
      }
   }

   @Override
   public void onLoadWorld(EventLoadWorld event) {
      this.currentTarget = null;
   }

   public static boolean isDelayingPackets() {
      return delayingPackets;
   }

   @Override
   public void onClick(EventClick event) {
      if (this.currentTarget != null) {
         int n = event.getLeftClicks() + (event.isIgnoreVanillaClicks() ? 0 : mc.gameSettings.keyBindAttack.getPressTime());
         if (n > 0 && this.currentTarget.hurtTime < 2 && !delayingPackets) {
         }

         if (delayingPackets) {
            if (n <= 0 && this.queuedClickCount == 0) {
               event.addLeftClicks((byte)1);
               n++;
            }

            this.queuedClickCount += n;
         }
      }
   }

   private void stopLagging() {
      delayingPackets = false;
      this.queuedClickCount = 0;
      this.forceLagActive = false;
   }

}
