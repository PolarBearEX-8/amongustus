package client.event;

import client.setting.AuxiliaryOptionType;
import net.minecraft.client.gui.ScaledResolution;

public interface RenderListener {
   EventRender3D eventRender3D = new EventRender3D();

   default void onRender2D(float factor, ScaledResolution scaledResolution) {
   }

   static void applyScaledResolution(float factor, ScaledResolution scaledResolution, AuxiliaryOptionType auxiliaryOptionType) {
      EventRender3D.onRender3D(eventRender3D, factor, scaledResolution, auxiliaryOptionType);
      EventBusManager.getDietrichEvents2().post(32, eventRender3D);
   }

   default void onRenderOverlay(float factor, ScaledResolution scaledResolution) {
   }

   default void onRender3D(float factor) {
   }
}
