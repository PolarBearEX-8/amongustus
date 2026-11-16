package client.util;

import net.minecraft.entity.Entity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;

public class OWKNOWSManagerEntry {
   public OWKNOWSManager oWKNOWSManager = new OWKNOWSManager();
   public BaseFloatEntry[] baseFloatEntryArray = new BaseFloatEntry[32];
   public Listener97Entry listener97Entry;

   public InterpolationConverter resolveInterpolationConverterByIBlockAccess(IBlockAccess iBlockAccess, Entity entity, Entity entity2, float factor) {
      return this.resolveInterpolationConverter(iBlockAccess, entity, entity2.posX, entity2.getBoundingBox2().minY, entity2.posZ, factor);
   }

   private InterpolationConverter resolveInterpolationConverter(IBlockAccess iBlockAccess, Entity entity, double posX, double posY, double posZ, float factor) {
      this.oWKNOWSManager.updateState();
      this.listener97Entry.applyIBlockAccess(iBlockAccess, entity);
      BaseFloatEntry baseFloatEntry = this.listener97Entry.resolveBaseFloatEntry(entity);
      BaseFloatEntry baseFloatEntry2 = this.listener97Entry.resolveBaseFloatEntry(entity, posX, posY, posZ);
      InterpolationConverter interpolationConverter = this.resolveInterpolationConverter(entity, baseFloatEntry, baseFloatEntry2, factor);
      this.listener97Entry.updateState();
      return interpolationConverter;
   }

   public InterpolationConverter resolveInterpolationConverter(IBlockAccess iBlockAccess, Entity entity, BlockPos blockPos, float factor) {
      return this.resolveInterpolationConverter(
         iBlockAccess, entity, (double)((float)blockPos.getX() + 0.5F), (double)((float)blockPos.getY() + 0.5F), (double)((float)blockPos.getZ() + 0.5F), factor
      );
   }

   private InterpolationConverter resolveInterpolationConverter(Entity entity, BaseFloatEntry baseFloatEntry, BaseFloatEntry baseFloatEntry2, float factor) {
      baseFloatEntry.factor = 0.0F;
      baseFloatEntry.factor3 = baseFloatEntry.computeFactor(baseFloatEntry2);
      baseFloatEntry.factor2 = baseFloatEntry.factor3;
      this.oWKNOWSManager.updateState();
      this.oWKNOWSManager.resolveBaseFloatEntry(baseFloatEntry);
      BaseFloatEntry baseFloatEntry3 = baseFloatEntry;

      while (!this.oWKNOWSManager.isActive()) {
         BaseFloatEntry baseFloatEntry4 = this.oWKNOWSManager.getBaseFloatEntry();
         if (baseFloatEntry4.equals(baseFloatEntry2)) {
            return this.resolveInterpolationConverter(baseFloatEntry, baseFloatEntry2);
         }

         if (baseFloatEntry4.computeFactor(baseFloatEntry2) < baseFloatEntry3.computeFactor(baseFloatEntry2)) {
            baseFloatEntry3 = baseFloatEntry4;
         }

         baseFloatEntry4.enabled = true;
         int count = this.listener97Entry.computeCount(this.baseFloatEntryArray, entity, baseFloatEntry4, baseFloatEntry2, factor);

         for (int index = 0; index < count; index++) {
            BaseFloatEntry baseFloatEntry5 = this.baseFloatEntryArray[index];
            float factor2 = baseFloatEntry4.factor + baseFloatEntry4.computeFactor(baseFloatEntry5);
            if (factor2 < factor * 2.0F && (!baseFloatEntry5.isActive() || factor2 < baseFloatEntry5.factor)) {
               baseFloatEntry5.baseFloatEntry = baseFloatEntry4;
               baseFloatEntry5.factor = factor2;
               baseFloatEntry5.factor3 = baseFloatEntry5.computeFactor(baseFloatEntry2);
               if (baseFloatEntry5.isActive()) {
                  this.oWKNOWSManager.applyBaseFloatEntry(baseFloatEntry5, baseFloatEntry5.factor + baseFloatEntry5.factor3);
               } else {
                  baseFloatEntry5.factor2 = baseFloatEntry5.factor + baseFloatEntry5.factor3;
                  this.oWKNOWSManager.resolveBaseFloatEntry(baseFloatEntry5);
               }
            }
         }
      }

      return baseFloatEntry3 == baseFloatEntry ? null : this.resolveInterpolationConverter(baseFloatEntry, baseFloatEntry3);
   }

   private InterpolationConverter resolveInterpolationConverter(BaseFloatEntry baseFloatEntry, BaseFloatEntry baseFloatEntry2) {
      int count = 1;

      for (BaseFloatEntry baseFloatEntry3 = baseFloatEntry2; baseFloatEntry3.baseFloatEntry != null; baseFloatEntry3 = baseFloatEntry3.baseFloatEntry) {
         count++;
      }

      BaseFloatEntry[] baseFloatEntryArray2 = new BaseFloatEntry[count];
      BaseFloatEntry baseFloatEntry4 = baseFloatEntry2;
      count--;

      for (baseFloatEntryArray2[count] = baseFloatEntry2; baseFloatEntry4.baseFloatEntry != null; baseFloatEntryArray2[count] = baseFloatEntry4) {
         baseFloatEntry4 = baseFloatEntry4.baseFloatEntry;
         count--;
      }

      return new InterpolationConverter(baseFloatEntryArray2);
   }

   public OWKNOWSManagerEntry(Listener97Entry listener97Entry2) {
      this.listener97Entry = listener97Entry2;
   }
}
