package client.util;

import client.setting.OptionType;
import net.minecraft.util.ResourceLocation;

public class DynamicResourceLocationEntry {
   public final ResourceLocation resourceLocation;
   public final OptionType optionType;
   public final boolean enabled;
   public final int count;

   public int getCount() {
      return this.count;
   }

   public ResourceLocation getResourceLocation() {
      return this.resourceLocation;
   }

   public OptionType getOptionType() {
      return this.optionType;
   }

   public boolean isEnabled() {
      return this.enabled;
   }

   public DynamicResourceLocationEntry(ResourceLocation location, OptionType optionType2, boolean flag, int count2) {
      this.resourceLocation = location;
      this.optionType = optionType2;
      this.enabled = flag;
      this.count = count2;
   }

   @Override
   public boolean equals(Object other) {
      if (this == other) {
         return true;
      } else if (!(other instanceof DynamicResourceLocationEntry)) {
         return false;
      } else {
         DynamicResourceLocationEntry dynamicResourceLocationEntry = (DynamicResourceLocationEntry)other;
         return this.resourceLocation.equals(dynamicResourceLocationEntry.resourceLocation) && this.optionType == dynamicResourceLocationEntry.optionType && this.enabled == dynamicResourceLocationEntry.enabled;
      }
   }

   @Override
   public int hashCode() {
      int hash = this.resourceLocation.hashCode();
      int count2 = 31 * hash + (this.optionType != null ? this.optionType.hashCode() : 0);
      return 31 * count2 + (this.enabled ? 1 : 0);
   }
}
