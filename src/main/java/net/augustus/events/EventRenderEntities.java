package net.augustus.events;

import net.augustus.events.listeners.RenderEntitiesListener;
import de.florianmichael.dietrichevents2.AbstractEvent;
import net.minecraft.client.renderer.culling.ICamera;

public class EventRenderEntities extends AbstractEvent<RenderEntitiesListener> {
   private double viewerPosY;
   private boolean shadersEnabled;
   private double viewerPosZ;
   private ICamera camera;
   private double viewerPosX;
   public static final int ID = 57;
   private float partialTicks;

   public boolean isShadersEnabled() {
      return this.shadersEnabled;
   }

   public double getViewerPosY() {
      return this.viewerPosY;
   }


   public ICamera getCamera() {
      return this.camera;
   }

   public void reset(ICamera camera2, double d, double d2, double d3, float f, boolean flag) {
      this.camera = camera2;
      this.viewerPosX = d;
      this.viewerPosY = d2;
      this.viewerPosZ = d3;
      this.partialTicks = f;
      this.shadersEnabled = flag;
   }

   public double getViewerPosZ() {
      return this.viewerPosZ;
   }

   @Override
   public void call(RenderEntitiesListener listener) {
      listener.onRenderEntities(this);
   }

   public float getPartialTicks() {
      return this.partialTicks;
   }

   public double getViewerPosX() {
      return this.viewerPosX;
   }
}
