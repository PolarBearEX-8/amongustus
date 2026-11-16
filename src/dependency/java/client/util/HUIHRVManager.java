package client.util;

import client.setting.GameSettingsPeacefulType;
import java.util.List;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SecondaryEntityAdapter;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class HUIHRVManager extends SecondaryEntityAdapter {
   public int count11;
   public long timestamp2;
   public int count12;

   @Override
   public void applyNBTTagCompound(NBTTagCompound tagCompound) {
   }

   @Override
   public void applyNBTTagCompoundByNBTTagCompound(NBTTagCompound tagCompound) {
   }

   @Override
   public void updateState() {
      super.updateState();
      if (this.count11 == 2) {
         this.worldObj.applyText(this.posX, this.posY, this.posZ, "ambient.weather.thunder", 10000.0F, 0.8F + this.random.nextFloat() * 0.2F);
         this.worldObj.applyText(this.posX, this.posY, this.posZ, "random.explode", 2.0F, 0.5F + this.random.nextFloat() * 0.2F);
      }

      this.count11--;
      if (this.count11 < 0) {
         if (this.count12 == 0) {
            this.updateState9();
         } else if (this.count11 < -this.random.nextInt(10)) {
            this.count12--;
            this.count11 = 1;
            this.timestamp2 = this.random.nextLong();
            BlockPos blockPos = new BlockPos(this);
            if (!this.worldObj.state
               && this.worldObj.getDoFireTickManager().matchesText("doFireTick")
               && this.worldObj.checkIndex(blockPos.getX(), blockPos.getY(), blockPos.getZ(), 10)
               && this.worldObj.getBlockState(blockPos).getBlock().getMaterial() == Material.coreItemEntry
               && Blocks.blockFire.processAction7(this.worldObj, blockPos)) {
               this.worldObj.matchesBlockPos(blockPos, Blocks.blockFire.getDefaultState());
            }
         }
      }

      if (this.count11 >= 0) {
         if (this.worldObj.state) {
            this.worldObj.setIndexOffset2(2);
         } else {
            double offset = 3.0;
            List list = this.worldObj
               .resolveListByEntity(
                  this,
                  new AxisAlignedBB(
                     this.posX - offset, this.posY - offset, this.posZ - offset, this.posX + offset, this.posY + 6.0 + offset, this.posZ + offset
                  )
               );

            for (int index = 0; index < list.size(); index++) {
               ((Entity)list.get(index)).applyHUIHRVManager(this);
            }
         }
      }
   }

   @Override
   public void updateState7() {
   }

   public HUIHRVManager(World world, double posX, double posY, double posZ) {
      super(world);
      this.applyDoubleByDouble(posX, posY, posZ, 0.0F, 0.0F);
      this.count11 = 2;
      this.timestamp2 = this.random.nextLong();
      this.count12 = this.random.nextInt(3) + 1;
      BlockPos blockPos = new BlockPos(this);
      if (!world.state
         && world.getDoFireTickManager().matchesText("doFireTick")
         && (world.getGameSettingsPeacefulType() == GameSettingsPeacefulType.NORMAL || world.getGameSettingsPeacefulType() == GameSettingsPeacefulType.HARD)
         && world.checkIndex(blockPos.getX(), blockPos.getY(), blockPos.getZ(), 10)) {
         if (world.getBlockState(blockPos).getBlock().getMaterial() == Material.coreItemEntry && Blocks.blockFire.processAction7(world, blockPos)) {
            world.matchesBlockPos(blockPos, Blocks.blockFire.getDefaultState());
         }

         for (int index = 0; index < 4; index++) {
            BlockPos blockPos2 = blockPos.add(this.random.nextInt(3) - 1, this.random.nextInt(3) - 1, this.random.nextInt(3) - 1);
            if (world.getBlockState(blockPos2).getBlock().getMaterial() == Material.coreItemEntry && Blocks.blockFire.processAction7(world, blockPos2)) {
               world.matchesBlockPos(blockPos2, Blocks.blockFire.getDefaultState());
            }
         }
      }
   }
}
