package client.render;

import client.util.ExtendedFloatEntryAdapter;
import client.util.SecondaryListenerHandler;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityEnderman;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityMob;
import net.minecraft.item.DiamondMaterial;
import net.minecraft.util.ResourceLocation;

public class GeometryPipeline extends InternalRendererLivingEntityAdapter {
   public static final ResourceLocation resourceLocation3 = new ResourceLocation("textures/entity/enderman/enderman.png");
   public ExtendedFloatEntryAdapter extendedFloatEntryAdapter;
   public Random random = new Random();

   @Override
   public void applyEntityLivingBase(EntityLivingBase livingEntity, double posX, double posY, double posZ, float factor, float factor2) {
      this.applyEntityEnderman((EntityEnderman)livingEntity, posX, posY, posZ, factor, factor2);
   }

   public ResourceLocation resolveResourceLocationByEntityEnderman(EntityEnderman entityEnderman) {
      return resourceLocation3;
   }

   public void applyEntityEnderman(EntityEnderman entityEnderman, double posX, double posY, double posZ, float factor, float factor2) {
      double offset = posX;
      double offset2 = posZ;
      this.extendedFloatEntryAdapter.enabled8 = entityEnderman.getIBlockState().getBlock().getMaterial() != Material.coreItemEntry;
      this.extendedFloatEntryAdapter.enabled7 = entityEnderman.isActive32();
      if (entityEnderman.isActive32()) {
         double offset3 = 0.02;
         offset = posX + this.random.nextGaussian() * offset3;
         offset2 = posZ + this.random.nextGaussian() * offset3;
      }

      super.applyEntityMob(entityEnderman, offset, posY, offset2, factor, factor2);
   }

   @Override
   public void applyEntityMob(EntityMob entityMob, double posX, double posY, double posZ, float factor, float factor2) {
      this.applyEntityEnderman((EntityEnderman)entityMob, posX, posY, posZ, factor, factor2);
   }

   @Override
   public void applyEntity(Entity entity, double posX, double posY, double posZ, float factor, float factor2) {
      this.applyEntityEnderman((EntityEnderman)entity, posX, posY, posZ, factor, factor2);
   }

   @Override
   public ResourceLocation resolveResourceLocation(Entity entity) {
      return this.resolveResourceLocationByEntityEnderman((EntityEnderman)entity);
   }

   public GeometryPipeline(RenderManager renderManager) {
      super(renderManager, new ExtendedFloatEntryAdapter(0.0F), 0.5F);
      this.extendedFloatEntryAdapter = (ExtendedFloatEntryAdapter)super.passiveFloatEntry;
      this.add(new SecondaryListenerHandler(this));
      this.add(new DiamondMaterial(this));
   }
}
