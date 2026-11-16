package net.minecraft.item;

import client.setting.MovementNumberProperty;
import client.util.IBlockState;
import client.util.PassiveStringEntry;
import client.util.TargetMatcher;
import net.minecraft.block.Blocks;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class DiamondWeapon extends EntityMinecart {
   public final PassiveStringEntry passiveStringEntry = new TargetMatcher(this);

   @Override
   public void applyNBTTagCompound(NBTTagCompound tagCompound) {
      super.applyNBTTagCompound(tagCompound);
      this.passiveStringEntry.applyNBTTagCompoundByNBTTagCompound(tagCompound);
   }

   @Override
   public MovementNumberProperty getFURNACE() {
      return MovementNumberProperty.SPAWNER;
   }

   public PassiveStringEntry getPassiveStringEntry() {
      return this.passiveStringEntry;
   }

   @Override
   public void processByte(byte byteValue) {
      this.passiveStringEntry.checkIndex(byteValue);
   }

   @Override
   public IBlockState getIBlockState() {
      return Blocks.block29.getDefaultState();
   }

   @Override
   public void applyNBTTagCompoundByNBTTagCompound(NBTTagCompound tagCompound) {
      super.applyNBTTagCompoundByNBTTagCompound(tagCompound);
      this.passiveStringEntry.applyNBTTagCompound(tagCompound);
   }

   @Override
   public void updateState() {
      super.updateState();
      this.passiveStringEntry.updateState();
   }

   public DiamondWeapon(World world) {
      super(world);
   }

   public DiamondWeapon(World world, double posX, double posY, double posZ) {
      super(world, posX, posY, posZ);
   }
}
