package client.util;

import java.util.List;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.item.SpecialItemEntry;
import net.minecraft.util.EnumFacing;

public class AuxiliaryListenerTracker implements IBakedModel {
   public SpecialItemEntry specialItemEntry;

   @Override
   public SpecialItemEntry getSpecialItemEntry() {
      return this.specialItemEntry;
   }

   @Override
   public boolean isActive() {
      return true;
   }

   @Override
   public List getList() {
      return null;
   }

   @Override
   public boolean isEnabled() {
      return false;
   }

   @Override
   public boolean canExecute() {
      return true;
   }

   @Override
   public ServiceStringEntry getServiceStringEntry() {
      return null;
   }

   @Override
   public List resolveList(EnumFacing enumFacing) {
      return null;
   }

   public AuxiliaryListenerTracker(SpecialItemEntry specialItemEntry2) {
      this.specialItemEntry = specialItemEntry2;
   }
}
