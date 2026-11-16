package client.event;

import de.florianmichael.dietrichevents2.AbstractEvent;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.RendererLivingEntity;
import net.minecraft.entity.EntityLivingBase;

public class EventRenderEntity extends AbstractEvent {
   public double value;
   public RendererLivingEntity rendererLivingEntity;
   public double value2;
   public double value3;
   public EntityLivingBase entityLivingBase;
   public static final int count = 31;
   public float factor;

   public float getFactor() {
      return this.factor;
   }

   public void applyRenderEntityListener(RenderEntityListener renderEntityListener) {
      renderEntityListener.onRenderEntity(this);
   }

   public static void onRenderEntityByEventRenderEntit(
      int count2,
      EventRenderEntity event,
      EntityLivingBase livingEntity,
      RendererLivingEntity rendererLivingEntity2,
      float factor2,
      double offset,
      double offset2,
      int count3,
      double offset3,
      char character
   ) {
      event.applyEntityLivingBase(livingEntity, rendererLivingEntity2, factor2, offset, offset2, offset3);
   }

   public double getCoordinate() {
      return this.value2;
   }

   public void applyEntityLivingBase(EntityLivingBase livingEntity, RendererLivingEntity rendererLivingEntity2, double posX, double posY, double posZ, boolean flag) {
      double offset = posY;
      if (livingEntity.isSprinting() && !(livingEntity instanceof EntityPlayerSP)) {
         offset = posY - 0.125;
      }

      rendererLivingEntity2.applyEntityLivingBaseByEntityLivingBase(livingEntity, this.factor, posX, offset, posZ, flag);
   }

   public EntityLivingBase getTargetEntity() {
      return this.entityLivingBase;
   }

   public double getCoordinate2() {
      return this.value3;
   }

   public void call(Object entry) {
      RenderEntityListener renderEntityListener = (RenderEntityListener)entry;
      this.applyRenderEntityListener(renderEntityListener);
   }

   public double getCoordinate3() {
      return this.value;
   }

   private void applyEntityLivingBase(EntityLivingBase livingEntity, RendererLivingEntity rendererLivingEntity2, float factor2, double posX, double posY, double posZ) {
      this.entityLivingBase = livingEntity;
      this.factor = factor2;
      this.rendererLivingEntity = rendererLivingEntity2;
      this.value = posX;
      this.value2 = posY;
      this.value3 = posZ;
   }

   public RendererLivingEntity getRendererLivingEntity() {
      return this.rendererLivingEntity;
   }
}
