package client.util;

import com.google.common.collect.Lists;
import java.util.List;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.item.GoldenLeggings;
import net.minecraft.item.SpecialItemEntry;
import net.minecraft.util.EnumFacing;

public class MissingParticleManager {
   public final List list = Lists.newArrayList();
   public final List list2 = Lists.newArrayListWithCapacity(6);
   public final boolean enabled2;
   public ServiceStringEntry serviceStringEntry;
   public final boolean enabled;
   public final SpecialItemEntry specialItemEntry;

   public MissingParticleManager add(LocalIntArrayEntry localIntArrayEntry) {
      this.list.add(localIntArrayEntry);
      return this;
   }

   private void applyIBakedModel(IBakedModel iBakedModel, ServiceStringEntry serviceStringEntry2, EnumFacing enumFacing) {
      for (LocalIntArrayEntry localIntArrayEntry : (Iterable<LocalIntArrayEntry>)(Iterable<?>)iBakedModel.resolveList(enumFacing)) {
         this.resolveMissingParticleManager(enumFacing, new GoldenLeggings(localIntArrayEntry, serviceStringEntry2));
      }
   }

   public MissingParticleManager resolveMissingParticleManager(EnumFacing enumFacing, LocalIntArrayEntry localIntArrayEntry) {
      ((List)this.list2.get(enumFacing.ordinal())).add(localIntArrayEntry);
      return this;
   }

   private void applyIBakedModel(IBakedModel iBakedModel, ServiceStringEntry serviceStringEntry2) {
      for (LocalIntArrayEntry localIntArrayEntry : (Iterable<LocalIntArrayEntry>)(Iterable<?>)iBakedModel.getList()) {
         this.add(new GoldenLeggings(localIntArrayEntry, serviceStringEntry2));
      }
   }

   public IBakedModel getIBakedModel() {
      if (this.serviceStringEntry == null) {
         throw new RuntimeException("Missing particle!");
      } else {
         return new ListenerWrapper(this.list, this.list2, this.enabled2, this.enabled, this.serviceStringEntry, this.specialItemEntry);
      }
   }

   public MissingParticleManager withServiceStringEntry(ServiceStringEntry serviceStringEntry2) {
      this.serviceStringEntry = serviceStringEntry2;
      return this;
   }

   public MissingParticleManager(IBakedModel iBakedModel, ServiceStringEntry serviceStringEntry2) {
      this(iBakedModel.isEnabled(), iBakedModel.canExecute(), iBakedModel.getSpecialItemEntry());
      this.serviceStringEntry = iBakedModel.getServiceStringEntry();

      for (EnumFacing enumFacing : EnumFacing.getEntries()) {
         this.applyIBakedModel(iBakedModel, serviceStringEntry2, enumFacing);
      }

      this.applyIBakedModel(iBakedModel, serviceStringEntry2);
   }

   public MissingParticleManager(NativeLoggerEntry nativeLoggerEntry) {
      this(nativeLoggerEntry.isActive(), nativeLoggerEntry.isEnabled2(), nativeLoggerEntry.getSpecialItemEntry());
   }

   private MissingParticleManager(boolean flag, boolean flag2, SpecialItemEntry specialItemEntry2) {
      for (EnumFacing enumFacing : EnumFacing.getEntries()) {
         this.list2.add(Lists.newArrayList());
      }

      this.enabled2 = flag;
      this.enabled = flag2;
      this.specialItemEntry = specialItemEntry2;
   }
}
