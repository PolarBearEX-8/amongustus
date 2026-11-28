package net.augustus.modules.combat;

import java.awt.Color;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.events.listeners.AttackSlowdownListener;
import net.augustus.events.EventAttackSlowdown;
import net.augustus.utils.combat.CombatOptimizationUtil;
import net.augustus.utils.combat.CombatTargetUtil;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.DoubleValue;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MovingObjectPosition;

public class KeepSprint extends Module implements AttackSlowdownListener {
   private final DoubleValue moveSlowdownOnGround;
   private final BooleanValue sprintOnGround;
   private final DoubleValue moveSlowdownInAir;
   private final BooleanValue sprintOnGroundInAir;
   private final BooleanValue smart;

   public KeepSprint() {
       super("KeepSprint", "BeibehaltenSprinten", new Color(66, 132, 245), Categorys.COMBAT);
      this.moveSlowdownOnGround = new DoubleValue(this, "MoveSlowdownOnGround", 1.0, 0.0, 1.0, 2);
      this.sprintOnGround = new BooleanValue(this, "SprintOnGround", true);
      this.moveSlowdownInAir = new DoubleValue(this, "MoveSlowdownInAir", 1.0, 0.0, 1.0, 2);
      this.sprintOnGroundInAir = new BooleanValue(this, "SprintOnGroundInAir", true);
      this.smart = new BooleanValue(this, "Smart", true);
   }

   public void onEnable() {
   }

   public void onDisable() {
   }

   public void updateEventSubscriptions() {
      this.toggleEventSubscription(2, 10);
   }

   public void onAttackSlowdownFirst(EventAttackSlowdown event) {
      if (this.smart.getValue()) {
         if (event.isSimulated()) {
            return;
         }

         MovingObjectPosition objectMouseOver = mc.objectMouseOver;
         if (objectMouseOver != null && objectMouseOver.entityHit != null) {
            EntityLivingBase activeTargetOrDefault = CombatTargetUtil.getActiveTargetOrDefault(objectMouseOver.entityHit instanceof EntityLivingBase ? (EntityLivingBase)objectMouseOver.entityHit : null);
            if (activeTargetOrDefault != null) {
               if (!CombatOptimizationUtil.shouldReduceSprintKnockback(activeTargetOrDefault, mc.thePlayer.rotationYaw)) {
                  return;
               }
            }
         }
      }

      if (mc.thePlayer.onGround) {
         event.setSlowDownFactor(this.moveSlowdownOnGround.getValue());
         event.setSprint(this.sprintOnGround.getValue());
      } else {
         event.setSlowDownFactor(this.moveSlowdownInAir.getValue());
         event.setSprint(this.sprintOnGroundInAir.getValue());
      }
   }

}
