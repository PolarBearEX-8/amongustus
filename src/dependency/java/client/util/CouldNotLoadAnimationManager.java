package client.util;

import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.util.Iterator;
import java.util.Map.Entry;
import net.minecraft.item.DefaultItemEntry;

public class CouldNotLoadAnimationManager {
   public ExtendedHashMapHolder resolveExtendedHashMapHolder(DefaultItemEntry defaultItemEntry, JsonObject jsonObject) {
      ExtendedHashMapHolder extendedHashMapHolder = new ExtendedHashMapHolder();
      Iterator iterator = CouldNotFindKeyManager.resolveSetByJsonObject(jsonObject).iterator();

      while (iterator.hasNext()) {
         String key = (String)((Entry)iterator.next()).getKey();

         try {
            SupplementalStringEntry supplementalStringEntry = CouldNotFindKeyManager.resolveSupplementalStringEntry(CouldNotFindKeyManager.resolveEntry(jsonObject, key), defaultItemEntry);
            extendedHashMapHolder.put(key, supplementalStringEntry);
         } catch (JsonParseException jsonParseException) {
            MoreCosmeticsManager.logger.error("Could not load animation: {}", new Object[]{key, jsonParseException});
            throw new RuntimeException(jsonParseException);
         }
      }

      return extendedHashMapHolder;
   }
}
