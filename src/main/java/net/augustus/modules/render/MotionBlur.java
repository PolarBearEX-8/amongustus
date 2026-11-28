package net.augustus.modules.render;

import java.awt.Color;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.render.GpuVendorDetector;
import net.augustus.events.listeners.TickListener;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.DoubleValue;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

public class MotionBlur extends Module implements TickListener {
   public final DoubleValue frameMultiplier;
   public final DoubleValue additionalFramePrecision;
   public final DoubleValue brightness;
   public final BooleanValue onlyWhenFocused;
   public final BooleanValue unsupportedDeviceWarningMessage;
   private boolean pendingGpuWarning;

   public MotionBlur() {
       super("MotionBlur", "BewegungsUnschärfe", new Color(50, 168, 151), Categorys.RENDER);
      this.frameMultiplier = new DoubleValue(this, "Frame Multiplier", 50.0, 0.0, 100.0, 1).visibleWhen(() -> GpuVendorDetector.isNvidia());
      this.additionalFramePrecision = new DoubleValue(this, "Additional frame precision", 0.0, 0.0, 100.0, 1).visibleWhen(() -> GpuVendorDetector.isNvidia());
      this.brightness = new DoubleValue(this, "Brightness", 50.0, 5.0, 100.0, 1).visibleWhen(() -> GpuVendorDetector.isNvidia());
      this.onlyWhenFocused = (BooleanValue)new BooleanValue(this, "Only when focused", true).withVisibility(() -> GpuVendorDetector.isNvidia());
      this.unsupportedDeviceWarningMessage = new BooleanValue(this, "UnsupportedDeviceWarningMessage", true);
      this.pendingGpuWarning = false;
   }

   @Override
   public void onEnable() {
      if (!GpuVendorDetector.isNvidia()) {
         this.pendingGpuWarning = true;
      }
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
      if (this.pendingGpuWarning && this.unsupportedDeviceWarningMessage.getValue()) {
         this.sendChatMessage("§cWe detected that your GPU may not support Motion Blur properly. This could lead to graphical issues or crashes.");
         this.pendingGpuWarning = false;
      }
   }

   public void applyMotionBlur() {
      if (!this.onlyWhenFocused.getValue() || Display.isActive()) {
         float blurStrength = this.getBlurStrength();
         GL11.glAccum(259, blurStrength);
         if (this.brightness.getValue() == 50.0) {
            GL11.glAccum(256, 1.0F - blurStrength);
         } else {
            GL11.glAccum(256, (float)((double)((1.0F - blurStrength) / 100.0F) * this.brightness.getValue() * 2.0));
         }

         GL11.glAccum(258, 1.0F);
      }
   }

   public float getBlurStrength() {
      float f = (float)(this.frameMultiplier.getValue() * 10.0);
      if (this.additionalFramePrecision.getValue() != 0.0) {
         f = (float)((double)f + this.additionalFramePrecision.getValue() / 5.0);
      }

      if (f > 990.0F) {
         f = 990.0F;
      }

      return f / 1000.0F;
   }

}
