package net.minecraft.entity;

import client.util.BezierProfiler;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class EntityPainting extends CustomEntityAdapter {
   public BezierProfiler bezierProfiler;

   @Override
   public void applyEntity9(Entity entity) {
      if (this.worldObj.getDoFireTickManager().matchesText("doEntityDrops")) {
         if (entity instanceof EntityPlayer && ((EntityPlayer)entity).invulnerableManager.enabled2) {
            return;
         }

         this.resolveEntityItem(new ItemStack(Items.painting), 0.0F);
      }
   }

   @Override
   public void applyNBTTagCompound(NBTTagCompound tagCompound) {
      String text = tagCompound.resolveText("Motive");

      for (BezierProfiler bezierProfiler2 : BezierProfiler.values()) {
         if (bezierProfiler2.title.equals(text)) {
            this.bezierProfiler = bezierProfiler2;
         }
      }

      if (this.bezierProfiler == null) {
         this.bezierProfiler = BezierProfiler.KEBAB;
      }

      super.applyNBTTagCompound(tagCompound);
   }

   @Override
   public void applyNBTTagCompoundByNBTTagCompound(NBTTagCompound tagCompound) {
      tagCompound.put("Motive", this.bezierProfiler.title);
      super.applyNBTTagCompoundByNBTTagCompound(tagCompound);
   }

   @Override
   public int getCount5() {
      return this.bezierProfiler.sizeX;
   }

   @Override
   public void applyDouble(double posX, double posY, double posZ, float factor, float factor2, int count, boolean flag) {
      BlockPos blockPos = this.blockPos2.resolveBlockPos(posX - this.posX, posY - this.posY, posZ - this.posZ);
      this.processDouble2((double)blockPos.getX(), (double)blockPos.getY(), (double)blockPos.getZ());
   }

   @Override
   public int getCount4() {
      return this.bezierProfiler.sizeY;
   }

   @Override
   public void applyDoubleByDouble(double posX, double posY, double posZ, float factor, float factor2) {
      BlockPos blockPos = this.blockPos2.resolveBlockPos(posX - this.posX, posY - this.posY, posZ - this.posZ);
      this.processDouble2((double)blockPos.getX(), (double)blockPos.getY(), (double)blockPos.getZ());
   }

   public EntityPainting(World world) {
      super(world);
   }

   public EntityPainting(World world, BlockPos blockPos, EnumFacing enumFacing, String text) {
      this(world, blockPos, enumFacing);

      for (BezierProfiler bezierProfiler2 : BezierProfiler.values()) {
         if (bezierProfiler2.title.equals(text)) {
            this.bezierProfiler = bezierProfiler2;
            break;
         }
      }

      this.applyEnumFacing(enumFacing);
   }

   public EntityPainting(World world, BlockPos blockPos, EnumFacing enumFacing) {
      super(world, blockPos);
      ArrayList list = Lists.newArrayList();

      for (BezierProfiler bezierProfiler2 : BezierProfiler.values()) {
         this.bezierProfiler = bezierProfiler2;
         this.applyEnumFacing(enumFacing);
         if (this.isActive16()) {
            list.add(bezierProfiler2);
         }
      }

      if (!list.isEmpty()) {
         this.bezierProfiler = (BezierProfiler)list.get(this.random.nextInt(list.size()));
      }

      this.applyEnumFacing(enumFacing);
   }
}
