package client.event;

import net.minecraft.entity.EntityLivingBase;

public interface PreMotionDispatcher {
   void applyEntityLivingBase(EntityLivingBase livingEntity, float posX, float posX2, float posX3, float posX4, float posX5, float posY, float posZ);

   boolean isActive();
}
