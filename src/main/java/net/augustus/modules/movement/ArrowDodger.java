package net.augustus.modules.movement;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Comparator;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.events.listeners.ClickListener;
import net.augustus.utils.rotation.RotationUtil;
import net.augustus.events.listeners.SilentSlotListener;
import net.augustus.events.EventClick;
import net.augustus.settings.Setting;
import net.augustus.events.listeners.MoveInputListener;
import net.augustus.events.EventMoveInput;
import net.augustus.events.MoveInputStage;
import net.augustus.utils.player.PlayerUtil;
import net.augustus.utils.simulation.ProjectileSimulator;
import net.augustus.utils.player.SilentSlotManager;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.DoubleValue;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Vec3;

public class ArrowDodger extends Module implements MoveInputListener, ClickListener, SilentSlotListener {
   private final BooleanValue doge;
   private final DoubleValue arrowHitboxExpand;
   private final BooleanValue releaseItemAtCollisionIncoming;
   private final BooleanValue notWhileEating;
   private final BooleanValue inInv;
   private int dodgeForward;
   private int dodgeStrafe;
   private boolean arrowIncoming;

   public ArrowDodger() {
       super("ArrowDodger", "PfeilAusweicher", new Color(49, 234, 247), Categorys.MOVEMENT);
      this.doge = (BooleanValue)(new BooleanValue(this, "Doge", true)).markVisualOnly();
      DoubleValue arrowHitboxExpandSetting = new DoubleValue(this, "Arrow hitbox expand", 0.2, 0.0, 2.0, 2);
      BooleanValue doge2 = this.doge;
      this.arrowHitboxExpand = (DoubleValue)arrowHitboxExpandSetting.visibleWhen(() -> doge2.getValue()).markVisualOnly();
      this.releaseItemAtCollisionIncoming = (BooleanValue)(new BooleanValue(this, "Release item at collision incoming", true)).markVisualOnly();
      this.notWhileEating = (BooleanValue)(new BooleanValue(this, "NotWhileEating", false)).markVisualOnly();
      this.inInv = (BooleanValue)(new BooleanValue(this, "InInv", false)).markVisualOnly();
      this.dodgeForward = -2;
      this.dodgeStrafe = -2;
      this.arrowIncoming = false;
   }

   @Override
   public void onEnable() {
      this.arrowIncoming = false;
   }

   @Override
   public void onDisable() {
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(21);
      this.toggleEventSubscription(7);
      this.toggleEventSubscription(34);
   }

   @Override
   public void onPreMoveInput(EventMoveInput event) {
      if (this.dodgeForward != -2 && this.doge.getValue()) {
         event.setMoveStrafe((float)this.dodgeStrafe);
         event.setMoveForward((float)this.dodgeForward);
      }
   }

   @Override
   public void onClick(EventClick event) {
      if (this.releaseItemAtCollisionIncoming.getValue() && this.arrowIncoming && mc.thePlayer.getCurrentEquippedItem() != null && !SilentSlotManager.isOwnedBy(this)) {
         event.setAllowRightClick(false);
         event.setRightMouseDown(false);
      }
   }

   @Override
   public void onPostSilentSlot() {
      if ((!this.notWhileEating.getValue() || !mc.thePlayer.isEating()) && (this.inInv.getValue() || mc.isNotInGui())) {
         this.dodgeForward = -2;
         this.dodgeStrafe = -2;
         int maxvalue = Integer.MAX_VALUE;
         ArrayList list = new ArrayList();

         for (Entity entity : (Iterable<Entity>)(mc.theWorld.loadedEntityList)) {
            if (entity instanceof EntityArrow && entity.ticksExisted >= 1) {
               ProjectileSimulator projectileSimulator = new ProjectileSimulator();
               projectileSimulator.initFromArrow((EntityArrow)entity);
               ArrayList list2 = projectileSimulator.simulateArrowTrajectoryWithExpand((short)200, (float)this.arrowHitboxExpand.getValue());
               if (projectileSimulator.getHitResult() != null && projectileSimulator.getHitResult().entityHit instanceof EntityPlayerSP) {
                  list.add(list2);
                  if (projectileSimulator.hitTick < maxvalue) {
                     maxvalue = projectileSimulator.hitTick;
                  }
               }
            }
         }

         if (!list.isEmpty() && this.doge.getValue()) {
            list.sort(Comparator.comparingDouble(obj -> ((ArrayList)obj).size()));
            double minvalue = Double.MIN_VALUE;

            for (int i = -1; i < 2; i++) {
               for (int j = -1; j < 2; j++) {
                  double[] doubles = PlayerUtil.predictPlayerPosition(maxvalue, mc.thePlayer, mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ, mc.thePlayer.rotationYaw, new EventMoveInput(
                     (float)j, (float)i, mc.thePlayer.movementInput.jump, mc.thePlayer.movementInput.sneak, MoveInputStage.PRE
                  ));
                  float f3 = mc.thePlayer.width / 2.0F + 0.1F;
                  AxisAlignedBB box = new AxisAlignedBB(
                     doubles[0] - (double)f3,
                     doubles[1] - 0.1F,
                     doubles[2] - (double)f3,
                     doubles[0] + (double)f3,
                     doubles[1] + (double)mc.thePlayer.height + 0.1F,
                     doubles[2] + (double)f3
                  );
                  double d4 = 0.0;

                  for (ArrayList list3 : (Iterable<ArrayList>)(list)) {
                     Vec3 vec = (Vec3)(list3.stream().min(Comparator.comparingDouble(obj2 -> ((Vec3)obj2).distanceTo(RotationUtil.clampPointToBox((Vec3)obj2, box)))).orElse(null));
                     if (vec != null) {
                        d4 += vec.distanceTo(RotationUtil.clampPointToBox(vec, box));
                     }
                  }

                  if (d4 > minvalue) {
                     this.dodgeForward = i;
                     this.dodgeStrafe = j;
                     minvalue = d4;
                  }
               }
            }
         }

         this.arrowIncoming = !list.isEmpty();
         if (this.releaseItemAtCollisionIncoming.getValue() && this.arrowIncoming && mc.thePlayer.getCurrentEquippedItem() != null && mc.thePlayer.getItemInUseCount() > 0) {
            SilentSlotManager.requestSlot(this, (byte)6, mc.thePlayer.inventory.currentItem == 8 ? mc.thePlayer.inventory.currentItem - 1 : mc.thePlayer.inventory.currentItem + 1);
         }
      } else {
         this.arrowIncoming = false;
         this.dodgeForward = -2;
      }
   }

}
