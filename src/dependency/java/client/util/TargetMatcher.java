package client.util;

import net.minecraft.item.DiamondWeapon;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class TargetMatcher extends PassiveStringEntry {
   public final DiamondWeapon diamondWeapon;

   @Override
   public World getWorld() {
      return this.diamondWeapon.worldObj;
   }

   @Override
   public BlockPos getBlockPos() {
      return new BlockPos(this.diamondWeapon);
   }

   public TargetMatcher(DiamondWeapon diamondWeapon2) {
      this.diamondWeapon = diamondWeapon2;
   }

   @Override
   public void applyIndex(int count) {
      this.diamondWeapon.worldObj.applyEntity(this.diamondWeapon, (byte)count);
   }
}
