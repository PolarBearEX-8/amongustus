package net.augustus.modules.render;

import java.awt.Color;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.events.EventOrientCamera;
import net.augustus.events.listeners.OrientCameraListener;
import net.augustus.events.listeners.TickListener;
import net.augustus.settings.DoubleValue;
import net.minecraft.util.Vec3;

public class CustomCamera extends Module implements OrientCameraListener, TickListener {
   public final DoubleValue customHeight;
   public final DoubleValue customHeightF5;
   public final DoubleValue customX;
   public final DoubleValue customXF5;
   public final DoubleValue customZ;
   public final DoubleValue customZF5;
   public final DoubleValue customThirdPersonDistance;

   public CustomCamera() {
       super("CustomCamera", "EinstellbareKamera", new Color(105, 66, 245), Categorys.RENDER);
      this.customHeight = new DoubleValue(this, "CustomHeight", 0.0, -5.0, 10.0, 2);
      this.customHeightF5 = new DoubleValue(this, "CustomHeightF5", 0.0, -5.0, 10.0, 2);
      this.customX = new DoubleValue(this, "CustomX", 0.0, -5.0, 5.0, 2);
      this.customXF5 = new DoubleValue(this, "CustomXF5", 0.0, -5.0, 5.0, 2);
      this.customZ = new DoubleValue(this, "CustomZ", 0.0, -5.0, 5.0, 2);
      this.customZF5 = new DoubleValue(this, "CustomZF5", 0.0, -5.0, 5.0, 2);
      this.customThirdPersonDistance = new DoubleValue(this, "CustomThirdPersonDistance", 4.0, 0.0, 30.0, 1);
   }

   public void onEnable() {
   }

   public void onDisable() {
      if (mc.entityRenderer != null) {
         mc.entityRenderer.thirdPersonDistance = 4.0F;
      }
   }

   public void updateEventSubscriptions() {
      this.toggleEventSubscription(24, 11);
      this.toggleEventSubscription(40);
   }

   public void onTick() {
      mc.entityRenderer.thirdPersonDistance = (float)this.customThirdPersonDistance.getValue();
   }

   public void onOrientCamera(EventOrientCamera event) {
      if (mc.gameSettings.thirdPersonView == 1) {
         event.setEyeHeight((float)((double)event.getEyeHeight() + this.customHeightF5.getValue()));
         event.setOffset(new Vec3(this.customXF5.getValue(), 0.0, this.customZF5.getValue()));
      } else if (mc.gameSettings.thirdPersonView == 0) {
         event.setEyeHeight((float)((double)event.getEyeHeight() + this.customHeight.getValue()));
         event.setOffset(new Vec3(this.customX.getValue(), 0.0, this.customZ.getValue()));
      }
   }

}
