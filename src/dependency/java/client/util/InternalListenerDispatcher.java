package client.util;

import java.util.List;
import net.minecraft.client.model.ModelClientManager;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.item.SpecialItemEntry;
import net.minecraft.util.EnumFacing;

public class InternalListenerDispatcher implements IBakedModel {
   public final int count;
   public final List list;
   public final IBakedModel iBakedModel;

   @Override
   public SpecialItemEntry getSpecialItemEntry() {
      return this.iBakedModel.getSpecialItemEntry();
   }

   @Override
   public boolean isActive() {
      return this.iBakedModel.isActive();
   }

   @Override
   public List getList() {
      return this.iBakedModel.getList();
   }

   @Override
   public boolean isEnabled() {
      return this.iBakedModel.isEnabled();
   }

   public IBakedModel resolveIBakedModel(long value) {
      return ((ModelClientManager)SmoothSystem.resolveDampenerSorter(this.list, Math.abs((int)value >> 16) % this.count)).iBakedModel;
   }

   @Override
   public boolean canExecute() {
      return this.iBakedModel.canExecute();
   }

   @Override
   public ServiceStringEntry getServiceStringEntry() {
      return this.iBakedModel.getServiceStringEntry();
   }

   @Override
   public List resolveList(EnumFacing enumFacing) {
      return this.iBakedModel.resolveList(enumFacing);
   }

   public InternalListenerDispatcher(List list2) {
      this.list = list2;
      this.count = SmoothSystem.computeCount(list2);
      this.iBakedModel = ((ModelClientManager)list2.get(0)).iBakedModel;
   }
}
