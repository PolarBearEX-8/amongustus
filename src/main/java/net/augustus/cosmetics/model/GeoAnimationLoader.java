package net.augustus.cosmetics.model;

import net.augustus.cosmetics.MoreCosmeticsCore;
import net.augustus.cosmetics.molang.MolangParser;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.util.Map.Entry;

public class GeoAnimationLoader {

   public GeoAnimationFile loadAllAnimations(MolangParser molangParser, JsonObject json) {
      GeoAnimationFile geoAnimationFile = new GeoAnimationFile();

      for (Entry entry : (Iterable<Entry>)(GeoAnimationJsonParser.getAnimations(json))) {
         String key = (String)entry.getKey();

         try {
            geoAnimationFile.putAnimation(key, GeoAnimationJsonParser.deserializeJsonToAnimation(GeoAnimationJsonParser.getAnimation(json, key), molangParser));
         } catch (JsonParseException e) {
            MoreCosmeticsCore.LOGGER.error("Could not load animation: {}", new Object[]{key, e});
            throw new RuntimeException(e);
         }
      }

      return geoAnimationFile;
   }

}
