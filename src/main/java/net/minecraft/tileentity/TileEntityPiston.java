package net.minecraft.tileentity;

import com.google.common.collect.Lists;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;

public class TileEntityPiston extends TileEntity implements ITickable {
   private IBlockState pistonState;
   private EnumFacing pistonFacing;
   private boolean extending;
   private boolean shouldHeadBeRendered;
   private float progress;
   private float lastProgress;
   private List pushedEntities = Lists.newArrayList();

   public TileEntityPiston() {
   }

   public float getOffsetZ(float f) {
      return this.extending
         ? (this.getProgress(f) - 1.0F) * (float)this.pistonFacing.getFrontOffsetZ()
         : (1.0F - this.getProgress(f)) * (float)this.pistonFacing.getFrontOffsetZ();
   }

   public void clearPistonTileEntity() {
      if (this.lastProgress < 1.0F && this.worldObj != null) {
         this.lastProgress = this.progress = 1.0F;
         this.worldObj.removeTileEntity(this.pos);
         this.invalidate();
         if (this.worldObj.getBlockState(this.pos).getBlock() == Blocks.piston_extension) {
            this.worldObj.setBlockState(this.pos, this.pistonState, 3);
            this.worldObj.notifyBlockOfStateChange(this.pos, this.pistonState.getBlock());
         }
      }
   }

   public EnumFacing getFacing() {
      return this.pistonFacing;
   }

   public float getOffsetY(float f) {
      return this.extending
         ? (this.getProgress(f) - 1.0F) * (float)this.pistonFacing.getFrontOffsetY()
         : (1.0F - this.getProgress(f)) * (float)this.pistonFacing.getFrontOffsetY();
   }

   public float getOffsetX(float f) {
      return this.extending
         ? (this.getProgress(f) - 1.0F) * (float)this.pistonFacing.getFrontOffsetX()
         : (1.0F - this.getProgress(f)) * (float)this.pistonFacing.getFrontOffsetX();
   }

   public TileEntityPiston(IBlockState state, EnumFacing facing, boolean flag, boolean flag2) {
      this.pistonState = state;
      this.pistonFacing = facing;
      this.extending = flag;
      this.shouldHeadBeRendered = flag2;
   }

   @Override
   public void writeToNBT(NBTTagCompound tag) {
      super.writeToNBT(tag);
      tag.setInteger("blockId", Block.getIdFromBlock(this.pistonState.getBlock()));
      tag.setInteger("blockData", this.pistonState.getBlock().getMetaFromState(this.pistonState));
      tag.setInteger("facing", this.pistonFacing.getIndex());
      tag.setFloat("progress", this.lastProgress);
      tag.setBoolean("extending", this.extending);
   }

   @Override
   public int getBlockMetadata() {
      return 0;
   }

   public IBlockState getPistonState() {
      return this.pistonState;
   }

   public boolean shouldPistonHeadBeRendered() {
      return this.shouldHeadBeRendered;
   }

   private void launchWithSlimeBlock(float f, float f2) {
      if (this.extending) {
         f = 1.0F - f;
      } else {
         f--;
      }

      AxisAlignedBB boundingBox = Blocks.piston_extension
         .getBoundingBox(this.worldObj, this.pos.getX(), this.pos.getY(), this.pos.getZ(), this.pistonState, f, this.pistonFacing);
      if (boundingBox != null) {
         List list = this.worldObj.getEntitiesWithinAABBExcludingEntity((Entity)null, boundingBox);
         if (!list.isEmpty()) {
            this.pushedEntities.addAll(list);

            for (Entity entity : (Iterable<Entity>)(this.pushedEntities)) {
               if (this.pistonState.getBlock() == Blocks.slime_block && this.extending) {
                  switch (this.pistonFacing.getAxis()) {
                     case X:
                        entity.motionX = (double)this.pistonFacing.getFrontOffsetX();
                        break;
                     case Y:
                        entity.motionY = (double)this.pistonFacing.getFrontOffsetY();
                        break;
                     case Z:
                        entity.motionZ = (double)this.pistonFacing.getFrontOffsetZ();
                  }
               } else {
                  entity.moveEntity(
                     (double)(f2 * (float)this.pistonFacing.getFrontOffsetX()),
                     (double)(f2 * (float)this.pistonFacing.getFrontOffsetY()),
                     (double)(f2 * (float)this.pistonFacing.getFrontOffsetZ())
                  );
               }
            }

            this.pushedEntities.clear();
         }
      }
   }

   public float getProgress(float f) {
      if (f > 1.0F) {
         f = 1.0F;
      }

      return this.lastProgress + (this.progress - this.lastProgress) * f;
   }

   @Override
   public void update() {
      this.lastProgress = this.progress;
      if (this.lastProgress >= 1.0F) {
         this.launchWithSlimeBlock(1.0F, 0.25F);
         this.worldObj.removeTileEntity(this.pos);
         this.invalidate();
         if (this.worldObj.getBlockState(this.pos).getBlock() == Blocks.piston_extension) {
            this.worldObj.setBlockState(this.pos, this.pistonState, 3);
            this.worldObj.notifyBlockOfStateChange(this.pos, this.pistonState.getBlock());
         }
      } else {
         this.progress += 0.5F;
         if (this.progress >= 1.0F) {
            this.progress = 1.0F;
         }

         if (this.extending) {
            this.launchWithSlimeBlock(this.progress, this.progress - this.lastProgress + 0.0625F);
         }
      }
   }

   @Override
   public void readFromNBT(NBTTagCompound tag) {
      super.readFromNBT(tag);
      this.pistonState = Block.getBlockById(tag.getInteger("blockId")).getStateFromMeta(tag.getInteger("blockData"));
      this.pistonFacing = EnumFacing.getFront(tag.getInteger("facing"));
      this.lastProgress = this.progress = tag.getFloat("progress");
      this.extending = tag.getBoolean("extending");
   }

   public boolean isExtending() {
      return this.extending;
   }

}
