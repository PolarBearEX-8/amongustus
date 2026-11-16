package client.util;

import net.labymod.Converter;
import net.labymod.RawGeoModel;

public class ErrorParsingSManager {
   public GenericListEntry resolveGenericListEntry(String text, String text2) {
      try {
         RawGeoModel rawGeoModel = Converter.fromJsonString(text2);
         String text3 = "";
         ExtraHashMapEntry extraHashMapEntry = ExtraHashMapEntry.resolveExtraHashMapEntry(rawGeoModel, text3);
         return ListenerAdapter2.get(text).resolveGenericListEntry(extraHashMapEntry);
      } catch (Exception exception) {
         MoreCosmeticsManager.logger.error(String.format("Error parsing %S", text), exception);
         throw new RuntimeException(exception);
      }
   }
}
