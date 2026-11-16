package net.minecraft.entity;

import client.render.WorldRenderer;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class EntityBeeAdapter extends TertiaryEntityAdapter {
   @Override
   public int getCount4() {
      return 1;
   }

   @Override
   public void applyWorldRendererByWorldRenderer(WorldRenderer worldRenderer, Entity entity, float posX, float posX2, float posX3, float posX4, float posY, float posZ) {
      float factor = ((float)this.count12 + this.factor7 / 4.0F) / 16.0F;
      float factor2 = factor + 0.015609375F;
      float factor3 = ((float)this.count11 + this.factor10 / 4.0F) / 16.0F;
      float factor4 = factor3 + 0.015609375F;
      float factor5 = 0.1F * this.factor12;
      if (this.serviceStringEntry != null) {
         factor = this.serviceStringEntry.computeFactorByDouble((double)(this.factor7 / 4.0F * 16.0F));
         factor2 = this.serviceStringEntry.computeFactorByDouble((double)((this.factor7 + 1.0F) / 4.0F * 16.0F));
         factor3 = this.serviceStringEntry.computeFactor((double)(this.factor10 / 4.0F * 16.0F));
         factor4 = this.serviceStringEntry.computeFactor((double)((this.factor10 + 1.0F) / 4.0F * 16.0F));
      }

      float factor6 = (float)(this.prevPosX + (this.posX - this.prevPosX) * (double)posX - value8);
      float factor7 = (float)(this.prevPosY + (this.posY - this.prevPosY) * (double)posX - value6);
      float factor8 = (float)(this.prevPosZ + (this.posZ - this.prevPosZ) * (double)posX - value7);
      int count = this.computeCount(posX);
      int count2 = count >> 16 & 65535;
      int count3 = count & 65535;
      worldRenderer.getWorldRenderer((double)(factor6 - posX2 * factor5 - posY * factor5), (double)(factor7 - posX3 * factor5), (double)(factor8 - posX4 * factor5 - posZ * factor5))
         .getWorldRenderer6((double)factor, (double)factor4)
         .resolveWorldRenderer(this.factor8, this.factor11, this.factor5, 1.0F)
         .resolveWorldRenderer(count2, count3)
         .updateState();
      worldRenderer.getWorldRenderer((double)(factor6 - posX2 * factor5 + posY * factor5), (double)(factor7 + posX3 * factor5), (double)(factor8 - posX4 * factor5 + posZ * factor5))
         .getWorldRenderer6((double)factor, (double)factor3)
         .resolveWorldRenderer(this.factor8, this.factor11, this.factor5, 1.0F)
         .resolveWorldRenderer(count2, count3)
         .updateState();
      worldRenderer.getWorldRenderer((double)(factor6 + posX2 * factor5 + posY * factor5), (double)(factor7 + posX3 * factor5), (double)(factor8 + posX4 * factor5 + posZ * factor5))
         .getWorldRenderer6((double)factor2, (double)factor3)
         .resolveWorldRenderer(this.factor8, this.factor11, this.factor5, 1.0F)
         .resolveWorldRenderer(count2, count3)
         .updateState();
      worldRenderer.getWorldRenderer((double)(factor6 + posX2 * factor5 - posY * factor5), (double)(factor7 - posX3 * factor5), (double)(factor8 + posX4 * factor5 - posZ * factor5))
         .getWorldRenderer6((double)factor2, (double)factor4)
         .resolveWorldRenderer(this.factor8, this.factor11, this.factor5, 1.0F)
         .resolveWorldRenderer(count2, count3)
         .updateState();
   }

   public EntityBeeAdapter(World world, double posX, double posY, double posZ, Item item) {
      this(world, posX, posY, posZ, item, 0);
   }

   public EntityBeeAdapter(World world, double posX, double posX2, double posX3, double posX4, double posY, double posZ, Item item, int count) {
      this(world, posX, posX2, posX3, item, count);
      this.motionX *= 0.1F;
      this.motionY *= 0.1F;
      this.motionZ *= 0.1F;
      this.motionX += posX4;
      this.motionY += posY;
      this.motionZ += posZ;
   }

   public EntityBeeAdapter(World world, double posX, double posY, double posZ, Item item, int count) {
      super(world, posX, posY, posZ, 0.0, 0.0, 0.0);
      this.applyServiceStringEntry(Minecraft.getMinecraft().getRenderItem().getSecondaryMapEntry().resolveServiceStringEntry(item, count));
      this.factor8 = this.factor11 = this.factor5 = 1.0F;
      this.factor6 = Blocks.block61.scaleFactor14;
      this.factor12 /= 2.0F;
   }
}
