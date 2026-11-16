package client.util;

import client.event.PreMotionDispatcher;
import client.setting.LocalOptionType;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntitySheep;
import net.minecraft.entity.EntityWolf;
import net.minecraft.util.ResourceLocation;

public class ExtendedResourceLocationEntry implements PreMotionDispatcher {
   public static final ResourceLocation resourceLocation = new ResourceLocation("textures/entity/wolf/wolf_collar.png");
   public final ManagedResourceLocationEntry managedResourceLocationEntry;

   @Override
   public void applyEntityLivingBase(EntityLivingBase livingEntity, float posX, float posX2, float posX3, float posX4, float posX5, float posY, float posZ) {
      this.applyEntityWolf((EntityWolf)livingEntity, posX, posX2, posX3, posX4, posX5, posY, posZ);
   }

   public void applyEntityWolf(EntityWolf entityWolf, float posX, float posX2, float posX3, float posX4, float posX5, float posY, float posZ) {
      if (entityWolf.isActive32() && !entityWolf.isActive12()) {
         this.managedResourceLocationEntry.applyResourceLocation2(resourceLocation);
         LocalOptionType localOptionType = LocalOptionType.resolveLocalOptionType(entityWolf.getLocalOptionType().getCount());
         float[] factorArray = EntitySheep.get(localOptionType);
         if (BuildManager.isActive33()) {
            factorArray = LoadingManager.resolveFloatArrayByLocalOptionType(localOptionType, factorArray);
         }

         GlStateManager.translate3f2(factorArray[0], factorArray[1], factorArray[2]);
         this.managedResourceLocationEntry.getPassiveFloatEntry().applyEntity(entityWolf, posX, posX2, posX4, posX5, posY, posZ, true);
      }
   }

   @Override
   public boolean isActive() {
      return true;
   }

   public ExtendedResourceLocationEntry(ManagedResourceLocationEntry managedResourceLocationEntry2) {
      this.managedResourceLocationEntry = managedResourceLocationEntry2;
   }
}
