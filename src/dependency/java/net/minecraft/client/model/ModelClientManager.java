package net.minecraft.client.model;

import client.util.DampenerSorter;
import com.google.common.collect.ComparisonChain;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.util.EnumFacing;

public class ModelClientManager extends DampenerSorter implements Comparable {
   public final IBakedModel iBakedModel;

   public int computeCount(ModelClientManager modelClientManager) {
      return ComparisonChain.start().compare(modelClientManager.count, this.count).compare(this.getCount(), modelClientManager.getCount()).result();
   }

   public int getCount() {
      int size = this.iBakedModel.getList().size();

      for (EnumFacing enumFacing : EnumFacing.getEntries()) {
         size += this.iBakedModel.resolveList(enumFacing).size();
      }

      return size;
   }

   public ModelClientManager(IBakedModel iBakedModel2, int count) {
      super(count);
      this.iBakedModel = iBakedModel2;
   }

   @Override
   public String toString() {
      return "MyWeighedRandomItem{weight=" + this.count + ", model=" + this.iBakedModel + '}';
   }

   @Override
   public int compareTo(Object entry) {
      return this.computeCount((ModelClientManager)entry);
   }
}
