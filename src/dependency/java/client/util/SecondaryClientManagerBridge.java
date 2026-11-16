package client.util;

import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public abstract class SecondaryClientManagerBridge extends RotationPool {
   public final AuxiliaryUUIDEntry auxiliaryUUIDEntry;
   public final double value;
   public int count2;
   public int count4;
   public int count5;
   public BlockPos blockPos = BlockPos.origin;
   public boolean enabled;
   public int count3;

   @Override
   public void updateState() {
   }

   public boolean isEnabled() {
      return this.enabled;
   }

   private boolean isActive3() {
      int count = this.count3;
      BlockPos blockPos2 = new BlockPos(this.auxiliaryUUIDEntry);

      for (int index = 0; index <= 1; index = index > 0 ? -index : 1 - index) {
         for (int index2 = 0; index2 < count; index2++) {
            for (int index3 = 0; index3 <= index2; index3 = index3 > 0 ? -index3 : 1 - index3) {
               for (int index4 = index3 < index2 && index3 > -index2 ? index2 : 0; index4 <= index2; index4 = index4 > 0 ? -index4 : 1 - index4) {
                  BlockPos blockPos3 = blockPos2.add(index3, index - 1, index4);
                  if (this.auxiliaryUUIDEntry.matchesBlockPos(blockPos3) && this.matchesWorld(this.auxiliaryUUIDEntry.worldObj, blockPos3)) {
                     this.blockPos = blockPos3;
                     return true;
                  }
               }
            }
         }
      }

      return false;
   }

   public abstract boolean matchesWorld(World world, BlockPos blockPos2);

   @Override
   public void updateState3() {
      this.auxiliaryUUIDEntry
         .getPathfindManager()
         .checkDouble((double)((float)this.blockPos.getX()) + 0.5, (double)(this.blockPos.getY() + 1), (double)((float)this.blockPos.getZ()) + 0.5, this.value);
      this.count4 = 0;
      this.count5 = this.auxiliaryUUIDEntry.getRandom().nextInt(this.auxiliaryUUIDEntry.getRandom().nextInt(1200) + 1200) + 1200;
   }

   @Override
   public void updateState2() {
      if (this.auxiliaryUUIDEntry.getCoordinate(this.blockPos.up()) > 1.0) {
         this.enabled = false;
         this.count4++;
         if (this.count4 % 40 == 0) {
            this.auxiliaryUUIDEntry
               .getPathfindManager()
               .checkDouble(
                  (double)((float)this.blockPos.getX()) + 0.5, (double)(this.blockPos.getY() + 1), (double)((float)this.blockPos.getZ()) + 0.5, this.value
               );
         }
      } else {
         this.enabled = true;
         this.count4--;
      }
   }

   @Override
   public boolean isActiveAsFlag() {
      return this.count4 >= -this.count5 && this.count4 <= 1200 && this.matchesWorld(this.auxiliaryUUIDEntry.worldObj, this.blockPos);
   }

   @Override
   public boolean isActive() {
      if (this.count2 > 0) {
         this.count2--;
         return false;
      } else {
         this.count2 = 200 + this.auxiliaryUUIDEntry.getRandom().nextInt(200);
         return this.isActive3();
      }
   }

   public SecondaryClientManagerBridge(AuxiliaryUUIDEntry auxiliaryUUIDEntry2, double offset, int count) {
      this.auxiliaryUUIDEntry = auxiliaryUUIDEntry2;
      this.value = offset;
      this.count3 = count;
      this.setCount(5);
   }
}
