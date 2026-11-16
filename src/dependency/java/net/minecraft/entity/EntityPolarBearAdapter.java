package net.minecraft.entity;

import client.render.WorldRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class EntityPolarBearAdapter extends TertiaryEntityAdapter {
   @Override
   public int getCount4() {
      return 1;
   }

   @Override
   public void applyWorldRendererByWorldRenderer(WorldRenderer worldRenderer, Entity entity, float posX, float posX2, float posX3, float posX4, float posY, float posZ) {
      float factor = this.serviceStringEntry.getFactor5();
      float factor2 = this.serviceStringEntry.getFactor2();
      float factor3 = this.serviceStringEntry.getFactor();
      float factor4 = this.serviceStringEntry.getFactor6();
      float factor5 = (float)(this.prevPosX + (this.posX - this.prevPosX) * (double)posX - value8);
      float factor6 = (float)(this.prevPosY + (this.posY - this.prevPosY) * (double)posX - value6);
      float factor7 = (float)(this.prevPosZ + (this.posZ - this.prevPosZ) * (double)posX - value7);
      int count = this.computeCount(posX);
      int count2 = count >> 16 & 65535;
      int count3 = count & 65535;
      worldRenderer.getWorldRenderer((double)(factor5 - posX2 * 0.5F - posY * 0.5F), (double)(factor6 - posX3 * 0.5F), (double)(factor7 - posX4 * 0.5F - posZ * 0.5F))
         .getWorldRenderer6((double)factor2, (double)factor4)
         .resolveWorldRenderer(this.factor8, this.factor11, this.factor5, 1.0F)
         .resolveWorldRenderer(count2, count3)
         .updateState();
      worldRenderer.getWorldRenderer((double)(factor5 - posX2 * 0.5F + posY * 0.5F), (double)(factor6 + posX3 * 0.5F), (double)(factor7 - posX4 * 0.5F + posZ * 0.5F))
         .getWorldRenderer6((double)factor2, (double)factor3)
         .resolveWorldRenderer(this.factor8, this.factor11, this.factor5, 1.0F)
         .resolveWorldRenderer(count2, count3)
         .updateState();
      worldRenderer.getWorldRenderer((double)(factor5 + posX2 * 0.5F + posY * 0.5F), (double)(factor6 + posX3 * 0.5F), (double)(factor7 + posX4 * 0.5F + posZ * 0.5F))
         .getWorldRenderer6((double)factor, (double)factor3)
         .resolveWorldRenderer(this.factor8, this.factor11, this.factor5, 1.0F)
         .resolveWorldRenderer(count2, count3)
         .updateState();
      worldRenderer.getWorldRenderer((double)(factor5 + posX2 * 0.5F - posY * 0.5F), (double)(factor6 - posX3 * 0.5F), (double)(factor7 + posX4 * 0.5F - posZ * 0.5F))
         .getWorldRenderer6((double)factor, (double)factor4)
         .resolveWorldRenderer(this.factor8, this.factor11, this.factor5, 1.0F)
         .resolveWorldRenderer(count2, count3)
         .updateState();
   }

   public EntityPolarBearAdapter(World world, double posX, double posY, double posZ, Item item) {
      super(world, posX, posY, posZ, 0.0, 0.0, 0.0);
      this.applyServiceStringEntry(Minecraft.getMinecraft().getRenderItem().getSecondaryMapEntry().resolveServiceStringEntry(item));
      this.factor8 = this.factor11 = this.factor5 = 1.0F;
      this.motionX = this.motionY = this.motionZ = 0.0;
      this.factor6 = 0.0F;
      this.count14 = 80;
   }
}
