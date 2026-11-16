package client.util;

import net.minecraft.entity.Entity;
import net.minecraft.item.AltItemArrayEntry;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;

public abstract class Listener97Entry {
   public IBlockAccess iBlockAccess;
   public AltItemArrayEntry altItemArrayEntry = new AltItemArrayEntry();
   public int count;
   public int count3;
   public int count2;

   public abstract int computeCount(BaseFloatEntry[] baseFloatEntryArray, Entity entity, BaseFloatEntry baseFloatEntry, BaseFloatEntry baseFloatEntry2, float factor);

   public BaseFloatEntry resolveBaseFloatEntry(int count4, int count5, int count6) {
      int count7 = BaseFloatEntry.computeCount(count4, count5, count6);
      BaseFloatEntry baseFloatEntry = (BaseFloatEntry)this.altItemArrayEntry.resolveTargetByIndex(count7);
      if (baseFloatEntry == null) {
         baseFloatEntry = new BaseFloatEntry(count4, count5, count6);
         this.altItemArrayEntry.applyTarget(count7, baseFloatEntry);
      }

      return baseFloatEntry;
   }

   public void applyIBlockAccess(IBlockAccess iBlockAccess2, Entity entity) {
      this.iBlockAccess = iBlockAccess2;
      this.altItemArrayEntry.updateState();
      this.count = MathHelper.floor_float(entity.width + 1.0F);
      this.count3 = MathHelper.floor_float(entity.height + 1.0F);
      this.count2 = MathHelper.floor_float(entity.width + 1.0F);
   }

   public abstract BaseFloatEntry resolveBaseFloatEntry(Entity entity, double posX, double posY, double posZ);

   public void updateState() {
      this.iBlockAccess = null;
   }

   public abstract BaseFloatEntry resolveBaseFloatEntry(Entity entity);
}
