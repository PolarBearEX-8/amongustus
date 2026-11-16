package client.util;

import net.minecraft.util.ResourceLocation;

public class ResourceLocationRecord {
   public String labelText;
   public ResourceLocation resourceLocation;
   public AuxiliaryGsonEntry auxiliaryGsonEntry;

   public ResourceLocationRecord(String text, ResourceLocation location, AuxiliaryGsonEntry auxiliaryGsonEntry2) {
      this.labelText = text;
      this.resourceLocation = location;
      this.auxiliaryGsonEntry = auxiliaryGsonEntry2;
   }
}
