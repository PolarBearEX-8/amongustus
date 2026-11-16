package client.util;

import client.event.PreMotionDispatcher;
import client.render.VertexArrayRenderer;
import client.setting.LocalOptionType;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntitySheep;
import net.minecraft.item.ItemEntryHandler;
import net.minecraft.util.ResourceLocation;

public class CustomListenerHandler implements PreMotionDispatcher {
   public static final ResourceLocation resourceLocation = new ResourceLocation("textures/entity/sheep/sheep_fur.png");
   public final VertexArrayRenderer vertexArrayRenderer;
   public ItemEntryHandler itemEntryHandler = new ItemEntryHandler();

   @Override
   public void applyEntityLivingBase(EntityLivingBase livingEntity, float posX, float posX2, float posX3, float posX4, float posX5, float posY, float posZ) {
      this.applyEntitySheep((EntitySheep)livingEntity, posX, posX2, posX3, posX4, posX5, posY, posZ);
   }

   @Override
   public boolean isActive() {
      return true;
   }

   public void applyEntitySheep(EntitySheep entitySheep, float posX, float posX2, float posX3, float posX4, float posX5, float posY, float posZ) {
      if (!entitySheep.isActive32() && !entitySheep.isActive12()) {
         this.vertexArrayRenderer.applyResourceLocation2(resourceLocation);
         if (entitySheep.processAction() && "jeb_".equals(entitySheep.getText2())) {
            int count = entitySheep.ticksExisted / 25 + entitySheep.getEntityId();
            int length = LocalOptionType.values().length;
            int count2 = count % length;
            int count3 = (count + 1) % length;
            float factor = ((float)(entitySheep.ticksExisted % 25) + posX3) / 25.0F;
            float[] factorArray = EntitySheep.get(LocalOptionType.resolveLocalOptionType(count2));
            float[] factorArray2 = EntitySheep.get(LocalOptionType.resolveLocalOptionType(count3));
            if (BuildManager.isActive33()) {
               factorArray = LoadingManager.resolveFloatArray(LocalOptionType.resolveLocalOptionType(count2), factorArray);
               factorArray2 = LoadingManager.resolveFloatArray(LocalOptionType.resolveLocalOptionType(count3), factorArray2);
            }

            GlStateManager.translate3f2(
               factorArray[0] * (1.0F - factor) + factorArray2[0] * factor, factorArray[1] * (1.0F - factor) + factorArray2[1] * factor, factorArray[2] * (1.0F - factor) + factorArray2[2] * factor
            );
         } else {
            float[] factorArray3 = EntitySheep.get(entitySheep.getLocalOptionType());
            if (BuildManager.isActive33()) {
               factorArray3 = LoadingManager.resolveFloatArray(entitySheep.getLocalOptionType(), factorArray3);
            }

            GlStateManager.translate3f2(factorArray3[0], factorArray3[1], factorArray3[2]);
         }

         this.itemEntryHandler.applyPassiveFloatEntry(this.vertexArrayRenderer.getPassiveFloatEntry());
         this.itemEntryHandler.applyEntityLivingBase(entitySheep, posX, posX2, posX3);
         this.itemEntryHandler.applyEntity(entitySheep, posX, posX2, posX4, posX5, posY, posZ, true);
      }
   }

   public CustomListenerHandler(VertexArrayRenderer vertexArrayRenderer2) {
      this.vertexArrayRenderer = vertexArrayRenderer2;
   }
}
