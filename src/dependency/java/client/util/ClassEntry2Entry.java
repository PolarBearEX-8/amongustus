package client.util;

import com.google.common.collect.Lists;
import java.util.List;
import java.util.Random;
import net.minecraft.item.GoldenEssence;
import net.minecraft.item.InternalItemArrayEntry;
import net.minecraft.nbt.NBTTagCompound;

public class ClassEntry2Entry extends GoldenEssence {
   public AuxiliaryClassEntry auxiliaryClassEntry;
   public List list3;
   public List list4;
   public List list2 = Lists.newArrayList();

   public ClassEntry2Entry() {
   }

   @Override
   public void applyNBTTagCompoundByNBTTagCompound(NBTTagCompound tagCompound) {
      super.applyNBTTagCompoundByNBTTagCompound(tagCompound);
   }

   public ClassEntry2Entry(Random random, int count, int count2) {
      super(random, count, count2);
      this.list3 = Lists.newArrayList();

      for (AuxiliaryClassEntry auxiliaryClassEntry2 : InternalItemArrayEntry.getAuxiliaryClassEntryArray()) {
         auxiliaryClassEntry2.count2 = 0;
         this.list3.add(auxiliaryClassEntry2);
      }

      this.list4 = Lists.newArrayList();

      for (AuxiliaryClassEntry auxiliaryClassEntry3 : InternalItemArrayEntry.getAuxiliaryClassEntryArray2()) {
         auxiliaryClassEntry3.count2 = 0;
         this.list4.add(auxiliaryClassEntry3);
      }
   }

   @Override
   public void applyNBTTagCompound(NBTTagCompound tagCompound) {
      super.applyNBTTagCompound(tagCompound);
   }
}
