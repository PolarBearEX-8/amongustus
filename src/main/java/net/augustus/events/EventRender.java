package net.augustus.events;

import net.augustus.events.listeners.RenderListener;
import de.florianmichael.dietrichevents2.AbstractEvent;
import net.minecraft.client.gui.ScaledResolution;

public class EventRender extends AbstractEvent<RenderListener> {
   private float partialTicks;
   public static final int ID = 32;
   private RenderPassType passType;
   private ScaledResolution scaledResolution;

   private void reset(float f, ScaledResolution sr, RenderPassType renderPassType) {
      this.partialTicks = f;
      this.passType = renderPassType;
      this.scaledResolution = sr;
   }

   public static void reset(EventRender event, float f, ScaledResolution sr, RenderPassType renderPassType) {
      event.reset(f, sr, renderPassType);
   }

   public float getPartialTicks() {
      return this.partialTicks;
   }


   @Override
   public void call(RenderListener listener) {
      switch (this.passType) {
         case RENDER3D:
            listener.onRender3D(this.partialTicks);
            break;
         case RENDER2D_PRE:
            ScaledResolution scaledResolution2 = this.scaledResolution;
            listener.onRender2DPre(this.partialTicks, scaledResolution2);
            break;
         case RENDER2D_POST:
            ScaledResolution scaledResolution3 = this.scaledResolution;
            listener.onRender2DPost(this.partialTicks, scaledResolution3);
      }
   }
}
