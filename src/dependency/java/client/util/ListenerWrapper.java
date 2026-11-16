package client.util;

import java.util.List;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.item.SpecialItemEntry;
import net.minecraft.util.EnumFacing;

public class ListenerWrapper implements IBakedModel {
   public final List list;
   public final List list2;
   public final boolean enabled;
   public final boolean enabled2;
   public final ServiceStringEntry serviceStringEntry;
   public final SpecialItemEntry specialItemEntry;

   @Override
   public SpecialItemEntry getSpecialItemEntry() {
      return this.specialItemEntry;
   }

   @Override
   public boolean isActive() {
      return false;
   }

   @Override
   public List getList() {
      return this.list;
   }

   @Override
   public boolean isEnabled() {
      return this.enabled;
   }

   @Override
   public boolean canExecute() {
      return this.enabled2;
   }

   @Override
   public ServiceStringEntry getServiceStringEntry() {
      return this.serviceStringEntry;
   }

   @Override
   public List resolveList(EnumFacing enumFacing) {
      return (List)this.list2.get(enumFacing.ordinal());
   }

   public ListenerWrapper(List list3, List list4, boolean flag, boolean flag2, ServiceStringEntry serviceStringEntry2, SpecialItemEntry specialItemEntry2) {
      this.list = list3;
      this.list2 = list4;
      this.enabled = flag;
      this.enabled2 = flag2;
      this.serviceStringEntry = serviceStringEntry2;
      this.specialItemEntry = specialItemEntry2;
   }
}
