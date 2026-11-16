package client.util;

import client.render.EntitySkinLocation;
import client.render.RenderManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.item.StandardItemArrayEntry;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;

public class ExtraResourceLocationEntryAdapter extends EntitySkinLocation {
   public static final ResourceLocation resourceLocation3 = new ResourceLocation("textures/entity/minecart.png");
   public PassiveFloatEntry passiveFloatEntry = new StandardItemArrayEntry();

   public ResourceLocation resolveResourceLocationByEntityMinecart(EntityMinecart entityMinecart) {
      return resourceLocation3;
   }

   public void applyEntityMinecart(EntityMinecart entityMinecart, double posX, double posY, double posZ, float factor, float factor2) {
      double offset = posX;
      double offset2 = posY;
      double offset3 = posZ;
      float factor3 = factor;
      GlStateManager.pushMatrix();
      this.matchesEntity3(entityMinecart);
      long value = (long)entityMinecart.getEntityId() * 493286711L;
      long value2 = value * value * 4392167121L + value * 98761L;
      float factor4 = (((float)(value2 >> 16 & 7L) + 0.5F) / 8.0F - 0.5F) * 0.004F;
      float factor5 = (((float)(value2 >> 20 & 7L) + 0.5F) / 8.0F - 0.5F) * 0.004F;
      float factor6 = (((float)(value2 >> 24 & 7L) + 0.5F) / 8.0F - 0.5F) * 0.004F;
      GlStateManager.translate(factor4, factor5, factor6);
      double offset4 = entityMinecart.lastTickPosX + (entityMinecart.posX - entityMinecart.lastTickPosX) * (double)factor2;
      double offset5 = entityMinecart.lastTickPosY + (entityMinecart.posY - entityMinecart.lastTickPosY) * (double)factor2;
      double offset6 = entityMinecart.lastTickPosZ + (entityMinecart.posZ - entityMinecart.lastTickPosZ) * (double)factor2;
      double offset7 = 0.3F;
      Vec3 vec = entityMinecart.resolveVec3(offset4, offset5, offset6);
      float factor7 = entityMinecart.prevRotationPitch + (entityMinecart.rotationPitch - entityMinecart.prevRotationPitch) * factor2;
      if (vec != null) {
         Vec3 vec2 = entityMinecart.resolveVec3(offset4, offset5, offset6, offset7);
         Vec3 vec3 = entityMinecart.resolveVec3(offset4, offset5, offset6, -offset7);
         if (vec2 == null) {
            vec2 = vec;
         }

         if (vec3 == null) {
            vec3 = vec;
         }

         offset = posX + (vec.xCoord - offset4);
         offset2 = posY + ((vec2.yCoord + vec3.yCoord) / 2.0 - offset5);
         offset3 = posZ + (vec.zCoord - offset6);
         Vec3 vec32 = vec3.getVec312(-vec2.xCoord, -vec2.yCoord, -vec2.zCoord);
         if (vec32.getValueAsDouble() != 0.0) {
            Vec3 vec4 = vec32.normalize();
            factor3 = (float)(Math.atan2(vec4.zCoord, vec4.xCoord) * 180.0 / Math.PI);
            factor7 = (float)(Math.atan(vec4.yCoord) * 73.0);
         }
      }

      GlStateManager.translate((float)offset, (float)offset2 + 0.375F, (float)offset3);
      GlStateManager.rotate(180.0F - factor3, 0.0F, 1.0F, 0.0F);
      GlStateManager.rotate(-factor7, 0.0F, 0.0F, 1.0F);
      float factor8 = (float)entityMinecart.getCount6() - factor2;
      float factor9 = entityMinecart.getFloat() - factor2;
      if (factor9 < 0.0F) {
         factor9 = 0.0F;
      }

      if (factor8 > 0.0F) {
         GlStateManager.rotate(MathHelper.abs(factor8) * factor8 * factor9 / 10.0F * (float)entityMinecart.getCount5(), 1.0F, 0.0F, 0.0F);
      }

      int count = entityMinecart.getCount7();
      IBlockState iBlockStateAsIBlockState = entityMinecart.getIBlockStateAsIBlockState();
      if (iBlockStateAsIBlockState.getBlock().processAction13() != -1) {
         GlStateManager.pushMatrix();
         this.applyResourceLocation2(ListenerAdapter27.resourceLocation);
         float factor10 = 0.75F;
         GlStateManager.translate3f3(factor10, factor10, factor10);
         GlStateManager.translate(-0.5F, (float)(count - 8) / 16.0F, 0.5F);
         this.applyEntityMinecart(entityMinecart, factor2, iBlockStateAsIBlockState);
         GlStateManager.popMatrix();
         GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
         this.matchesEntity3(entityMinecart);
      }

      GlStateManager.translate3f3(-1.0F, -1.0F, 1.0F);
      this.passiveFloatEntry.applyEntity(entityMinecart, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F, true);
      GlStateManager.popMatrix();
      super.applyEntity(entityMinecart, offset, offset2, offset3, factor3, factor2);
   }

   public void applyEntityMinecart(EntityMinecart entityMinecart, float factor, IBlockState blockState) {
      GlStateManager.pushMatrix();
      Minecraft.getMinecraft().getGoldenCrystal().applyIBlockState(blockState, entityMinecart.computeFactor(factor));
      GlStateManager.popMatrix();
   }

   @Override
   public void applyEntity(Entity entity, double posX, double posY, double posZ, float factor, float factor2) {
      this.applyEntityMinecart((EntityMinecart)entity, posX, posY, posZ, factor, factor2);
   }

   @Override
   public ResourceLocation resolveResourceLocation(Entity entity) {
      return this.resolveResourceLocationByEntityMinecart((EntityMinecart)entity);
   }

   public ExtraResourceLocationEntryAdapter(RenderManager renderManager) {
      super(renderManager);
      this.factor = 0.5F;
   }
}
