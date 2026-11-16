package net.minecraft.entity;

import client.render.WorldRenderer;
import client.util.IBlockState;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class EntityBoggedAdapter extends TertiaryEntityAdapter {
   public IBlockState iBlockState;
   public BlockPos blockPos2;

   @Override
   public int computeCount(float factor) {
      int count = super.computeCount(factor);
      int count2 = 0;
      if (this.worldObj.checkBlockPos12(this.blockPos2)) {
         count2 = this.worldObj.computeCount(this.blockPos2.getX(), this.blockPos2.getY(), this.blockPos2.getZ(), 0);
      }

      return count == 0 ? count2 : count;
   }

   @Override
   public int getCount4() {
      return 1;
   }

   public EntityBoggedAdapter resolveEntityBoggedAdapter(BlockPos blockPos) {
      this.blockPos2 = blockPos;
      if (this.iBlockState.getBlock() == Blocks.blockGrass) {
         return this;
      } else {
         int count = this.iBlockState.getBlock().processAction2((IBlockAccess)this.worldObj, blockPos);
         this.factor8 *= (float)(count >> 16 & 0xFF) / 255.0F;
         this.factor11 *= (float)(count >> 8 & 0xFF) / 255.0F;
         this.factor5 *= (float)(count & 0xFF) / 255.0F;
         return this;
      }
   }

   public EntityBoggedAdapter getEntityBoggedAdapter() {
      this.blockPos2 = new BlockPos(this.posX, this.posY, this.posZ);
      Block block = this.iBlockState.getBlock();
      if (block == Blocks.blockGrass) {
         return this;
      } else {
         int count = block.processAction2(this.iBlockState);
         this.factor8 *= (float)(count >> 16 & 0xFF) / 255.0F;
         this.factor11 *= (float)(count >> 8 & 0xFF) / 255.0F;
         this.factor5 *= (float)(count & 0xFF) / 255.0F;
         return this;
      }
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

   public EntityBoggedAdapter(World world, double posX, double posX2, double posX3, double posX4, double posY, double posZ, IBlockState blockState) {
      super(world, posX, posX2, posX3, posX4, posY, posZ);
      this.iBlockState = blockState;
      this.applyServiceStringEntry(Minecraft.getMinecraft().getGoldenCrystal().getCommonMapEntry().resolveServiceStringEntry(blockState));
      this.factor6 = blockState.getBlock().scaleFactor14;
      this.factor8 = this.factor11 = this.factor5 = 0.6F;
      this.factor12 /= 2.0F;
   }
}
