package client.util;

import net.minecraft.block.AuxiliaryBlockPosAdapter;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;

public class BaseFloatEntryListener97Entry extends Listener97Entry {
   @Override
   public BaseFloatEntry resolveBaseFloatEntry(Entity entity) {
      return this.resolveBaseFloatEntry(
         MathHelper.ceiling_double_int(entity.getBoundingBox2().minX),
         MathHelper.ceiling_double_int(entity.getBoundingBox2().minY + 0.5),
         MathHelper.ceiling_double_int(entity.getBoundingBox2().minZ)
      );
   }

   @Override
   public void applyIBlockAccess(IBlockAccess iBlockAccess, Entity entity) {
      super.applyIBlockAccess(iBlockAccess, entity);
   }

   private BaseFloatEntry resolveBaseFloatEntry(Entity entity, int count, int count2, int count3) {
      return this.computeCount(entity, count, count2, count3) == -1 ? this.resolveBaseFloatEntry(count, count2, count3) : null;
   }

   private int computeCount(Entity entity, int count, int count2, int count3) {
      AuxiliaryBlockPosAdapter auxiliaryBlockPosAdapter = new AuxiliaryBlockPosAdapter();

      for (int index = count; index < count + this.count; index++) {
         for (int index2 = count2; index2 < count2 + this.count3; index2++) {
            for (int index3 = count3; index3 < count3 + this.count2; index3++) {
               if (this.iBlockAccess.getBlockState(auxiliaryBlockPosAdapter.resolveAuxiliaryBlockPosAdapter(index, index2, index3)).getBlock().getMaterial() != Material.coreItemEntry1) {
                  return 0;
               }
            }
         }
      }

      return -1;
   }

   @Override
   public int computeCount(BaseFloatEntry[] baseFloatEntryArray, Entity entity, BaseFloatEntry baseFloatEntry, BaseFloatEntry baseFloatEntry2, float factor) {
      int count = 0;

      for (EnumFacing enumFacing : EnumFacing.getEntries()) {
         BaseFloatEntry baseFloatEntry3 = this.resolveBaseFloatEntry(entity, baseFloatEntry.count + enumFacing.getInt2(), baseFloatEntry.count5 + enumFacing.getInt3(), baseFloatEntry.count3 + enumFacing.getInt());
         if (baseFloatEntry3 != null && !baseFloatEntry3.enabled && baseFloatEntry3.computeFactorByBaseFloatEntry(baseFloatEntry2) < factor) {
            baseFloatEntryArray[count++] = baseFloatEntry3;
         }
      }

      return count;
   }

   @Override
   public BaseFloatEntry resolveBaseFloatEntry(Entity entity, double posX, double posY, double posZ) {
      return this.resolveBaseFloatEntry(
         MathHelper.ceiling_double_int(posX - (double)(entity.width / 2.0F)),
         MathHelper.ceiling_double_int(posY + 0.5),
         MathHelper.ceiling_double_int(posZ - (double)(entity.width / 2.0F))
      );
   }

   @Override
   public void updateState() {
      super.updateState();
   }
}
