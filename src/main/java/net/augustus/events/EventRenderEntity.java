package net.augustus.events;

import net.augustus.events.listeners.RenderEntityListener;
import de.florianmichael.dietrichevents2.AbstractEvent;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.entity.EntityLivingBase;

public class EventRenderEntity extends AbstractEvent<RenderEntityListener> {
   private RendererLivingEntity renderer;
   private double y;
   private EntityLivingBase entity;
   private float partialTicks;
   public static final int ID = 31;
   private double x;
   private double z;

   public double getY() {
      return this.y;
   }

   public float getPartialTicks() {
      return this.partialTicks;
   }

   public double getX() {
      return this.x;
   }

   public EntityLivingBase getEntity() {
      return this.entity;
   }

   public static void reset(EventRenderEntity event, EntityLivingBase entity2, RendererLivingEntity rendererLivingEntity, float f, double d, double d2, double d3) {
      event.reset(entity2, rendererLivingEntity, f, d, d2, d3);
   }


   @Override
   public void call(RenderEntityListener listener) {
      listener.onRenderEntity(this);
   }

   public RendererLivingEntity getRenderer() {
      return this.renderer;
   }

   private void reset(EntityLivingBase entity2, RendererLivingEntity rendererLivingEntity, float f, double d, double d2, double d3) {
      this.entity = entity2;
      this.partialTicks = f;
      this.renderer = rendererLivingEntity;
      this.x = d;
      this.y = d2;
      this.z = d3;
   }

   public void renderEntityModel(EntityLivingBase entity2, RendererLivingEntity rendererLivingEntity, double d, double d2, double d3, boolean flag) {
      if (entity2.isSneaking() && !(entity2 instanceof EntityPlayerSP)) {
         d2 -= 0.125;
      }

      rendererLivingEntity.renderEntityModel(entity2, this.partialTicks, d, d2, d3, flag);
   }

   public double getZ() {
      return this.z;
   }
}
