package net.augustus.modules.player;

import java.awt.Color;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.settings.RandomizedRangeSettings;
import net.augustus.events.listeners.MouseMoveListener;
import net.augustus.utils.rotation.RotationUtil;
import net.augustus.ClientManagers;
import net.augustus.events.EventMouseMove;
import net.augustus.Augustus;
import net.augustus.events.listeners.TickListener;
import net.augustus.utils.rotation.Rotation;

public class QuickTurn extends Module implements MouseMoveListener, TickListener {
   private final RandomizedRangeSettings yawOffset;
   private final RandomizedRangeSettings pitchOffset;

   public QuickTurn() {
       super("QuickTurn", "SchnellDrehung", new Color(209, 171, 59), Categorys.PLAYER);
      this.yawOffset = new RandomizedRangeSettings(this, "Yaw offset", 180.0, 180.0, -190.0, 190.0, 2);
      this.pitchOffset = new RandomizedRangeSettings(this, "pitch offset", 0.0, 0.0, -90.0, 90.0, 1);
   }

   @Override
   public void onEnable() {
   }

   @Override
   public void onDisable() {
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(19);
      this.toggleEventSubscription(40);
   }

   @Override
   public void onMouseMove(EventMouseMove event) {
      Rotation rotation = RotationUtil.applyGcdFix(
         Augustus.getInstance().getYawPitchHelper().realYaw + (float)this.yawOffset.nextValue(),
         Augustus.getInstance().getYawPitchHelper().realPitch + (float)this.pitchOffset.nextValue(),
         ClientManagers.rotationController.getPreviousRotation()
      );
      int[] ints = RotationUtil.computeMouseDeltas(rotation.yaw, rotation.pitch, ClientManagers.rotationController.getPreviousRotation().yaw, ClientManagers.rotationController.getPreviousRotation().pitch);
      event.setDeltaX(ints[0]);
      event.setDeltaY(ints[1]);
      this.setToggled(false);
   }
}
