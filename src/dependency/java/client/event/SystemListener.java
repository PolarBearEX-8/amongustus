package client.event;

import net.minecraft.entity.EntityLivingBase;

public interface SystemListener {
   void applyEntityLivingBaseByEntityLivingBase(EntityLivingBase livingEntity, float factor);
}
