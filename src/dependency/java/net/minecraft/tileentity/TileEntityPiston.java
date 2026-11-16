package net.minecraft.tileentity;

import client.event.PreUpdateConsumer;
import client.util.IBlockState;
import client.util.RotationConverter;
import com.google.common.collect.Lists;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.EnumFacing;

public class TileEntityPiston extends TileEntity implements PreUpdateConsumer {
   public IBlockState iBlockState;
   public EnumFacing enumFacing;
   public boolean enabled2;
   public boolean enabled3;
   public float factor2;
   public float factor;
   public List list = Lists.newArrayList();

   public boolean isEnabled2() {
      return this.enabled2;
   }

   public float computeFactor(float factor3) {
      return this.enabled2
         ? (this.computeFactor3(factor3) - 1.0F) * (float)this.enumFacing.getInt()
         : (1.0F - this.computeFactor3(factor3)) * (float)this.enumFacing.getInt();
   }

   private void applyFloat(float factor3, float factor4) {
      float factor5;
      if (this.enabled2) {
         factor5 = 1.0F - factor3;
      } else {
         factor5 = factor3 - 1.0F;
      }

      AxisAlignedBB boundingBox = Blocks.blockPistonExtension
         .resolveAxisAlignedBBByWorld(this.world, this.blockPos.getX(), this.blockPos.getY(), this.blockPos.getZ(), this.iBlockState, factor5, this.enumFacing);
      if (boundingBox != null) {
         List list2 = this.world.resolveListByEntity((Entity)null, boundingBox);
         if (!list2.isEmpty()) {
            this.list.addAll(list2);

            for (Entity entity : (Iterable<Entity>)(Iterable<?>)this.list) {
               if (this.iBlockState.getBlock() == Blocks.block71 && this.enabled2) {
                  switch (RotationConverter.countArray[this.enumFacing.getEnumFacingXType().ordinal()]) {
                     case 1:
                        entity.motionX = (double)this.enumFacing.getInt2();
                        break;
                     case 2:
                        entity.motionY = (double)this.enumFacing.getInt3();
                        break;
                     case 3:
                        entity.motionZ = (double)this.enumFacing.getInt();
                  }
               } else {
                  entity.applyDouble(
                     (double)(factor4 * (float)this.enumFacing.getInt2()),
                     (double)(factor4 * (float)this.enumFacing.getInt3()),
                     (double)(factor4 * (float)this.enumFacing.getInt())
                  );
               }
            }

            this.list.clear();
         }
      }
   }

   public float computeFactorByFloat(float factor3) {
      return this.enabled2
         ? (this.computeFactor3(factor3) - 1.0F) * (float)this.enumFacing.getInt3()
         : (1.0F - this.computeFactor3(factor3)) * (float)this.enumFacing.getInt3();
   }

   public void updateState2() {
      if (this.factor < 1.0F && this.world != null) {
         this.factor = this.factor2 = 1.0F;
         this.world.applyBlockPosByBlockPos(this.blockPos);
         this.updateState3();
         if (this.world.getBlockState(this.blockPos).getBlock() == Blocks.blockPistonExtension) {
            this.world.matchesBlockPos(this.blockPos, this.iBlockState, 3);
            this.world.applyBlockPos2(this.blockPos, this.iBlockState.getBlock());
         }
      }
   }

   @Override
   public void applyNBTTagCompoundByNBTTagCompound(NBTTagCompound tagCompound) {
      super.applyNBTTagCompoundByNBTTagCompound(tagCompound);
      this.iBlockState = Block.processAction3(tagCompound.computeCount("blockId")).processAction(tagCompound.computeCount("blockData"));
      this.enumFacing = EnumFacing.getEnumFacing6(tagCompound.computeCount("facing"));
      this.factor = this.factor2 = tagCompound.computeFactor("progress");
      this.enabled2 = tagCompound.matchesText("extending");
   }

   public float computeFactor3(float factor3) {
      float factor4 = factor3;
      if (factor3 > 1.0F) {
         factor4 = 1.0F;
      }

      return this.factor + (this.factor2 - this.factor) * factor4;
   }

   public EnumFacing getEnumFacing() {
      return this.enumFacing;
   }

   public IBlockState getIBlockState() {
      return this.iBlockState;
   }

   public boolean isEnabled3() {
      return this.enabled3;
   }

   public float computeFactor2(float factor3) {
      return this.enabled2
         ? (this.computeFactor3(factor3) - 1.0F) * (float)this.enumFacing.getInt2()
         : (1.0F - this.computeFactor3(factor3)) * (float)this.enumFacing.getInt2();
   }

   @Override
   public void updateState() {
      this.factor = this.factor2;
      if (this.factor >= 1.0F) {
         this.applyFloat(1.0F, 0.25F);
         this.world.applyBlockPosByBlockPos(this.blockPos);
         this.updateState3();
         if (this.world.getBlockState(this.blockPos).getBlock() == Blocks.blockPistonExtension) {
            this.world.matchesBlockPos(this.blockPos, this.iBlockState, 3);
            this.world.applyBlockPos2(this.blockPos, this.iBlockState.getBlock());
         }
      } else {
         this.factor2 += 0.5F;
         if (this.factor2 >= 1.0F) {
            this.factor2 = 1.0F;
         }

         if (this.enabled2) {
            this.applyFloat(this.factor2, this.factor2 - this.factor + 0.0625F);
         }
      }
   }

   @Override
   public int getCount() {
      return 0;
   }

   @Override
   public void applyNBTTagCompound(NBTTagCompound tagCompound) {
      super.applyNBTTagCompound(tagCompound);
      tagCompound.put("blockId", Block.getIdFromBlock(this.iBlockState.getBlock()));
      tagCompound.put("blockData", this.iBlockState.getBlock().getIndex(this.iBlockState));
      tagCompound.put("facing", this.enumFacing.getIndexOffset1());
      tagCompound.put("progress", this.factor);
      tagCompound.applyText("extending", this.enabled2);
   }

   public TileEntityPiston() {
   }

   public TileEntityPiston(IBlockState blockState, EnumFacing enumFacing2, boolean flag, boolean flag2) {
      this.iBlockState = blockState;
      this.enumFacing = enumFacing2;
      this.enabled2 = flag;
      this.enabled3 = flag2;
   }
}
