package client.util;

import net.minecraft.util.ResourceLocation;

public class AuxiliaryResourceLocationHolder {
   public final ResourceLocation resourceLocation;
   public final boolean enabled;
   public double value;
   public double value2;

   public double getValue2() {
      return this.value2;
   }

   public void setValue2(double value3) {
      this.value2 = value3;
   }

   public ResourceLocation getResourceLocation() {
      return this.resourceLocation;
   }

   public double getValue() {
      return this.value;
   }

   public boolean isEnabled() {
      return this.enabled;
   }

   public void setValue(double value3) {
      this.value = value3;
   }

   public AuxiliaryResourceLocationHolder(AuxiliaryResourceLocationHolder auxiliaryResourceLocationHolder) {
      this.resourceLocation = auxiliaryResourceLocationHolder.resourceLocation;
      this.value = auxiliaryResourceLocationHolder.value;
      this.value2 = auxiliaryResourceLocationHolder.value2;
      this.enabled = auxiliaryResourceLocationHolder.enabled;
   }

   public AuxiliaryResourceLocationHolder(ResourceLocation location, double offset, double offset2, boolean flag) {
      this.resourceLocation = location;
      this.value = offset;
      this.value2 = offset2;
      this.enabled = flag;
   }
}
