package client.event;

import net.minecraft.client.renderer.RendererLivingEntity;
import net.minecraft.entity.EntityLivingBase;

public interface RenderEntityListener {
   EventRenderEntity eventRenderEntity = new EventRenderEntity();

   static void applyEntityLivingBase(
      EntityLivingBase livingEntity, RendererLivingEntity rendererLivingEntity, int count, float factor, double offset, int count2, byte byteValue, double offset2, double offset3
   ) {
      EventRenderEntity.onRenderEntityByEventRenderEntit(26148, eventRenderEntity, livingEntity, rendererLivingEntity, factor, offset, offset2, 6555, offset3, (char)13986);
      EventBusManager.getDietrichEvents2().post(31, eventRenderEntity);
   }

   void onRenderEntity(EventRenderEntity event);
}
