package net.optifine;

import java.util.ArrayList;
import java.util.Properties;
import net.minecraft.util.ResourceLocation;
import net.optifine.config.Config;
import net.optifine.config.ConnectedParser;

public class RandomEntityProperties {
   public String name = null;
   public String basePath = null;
   public ResourceLocation[] resourceLocations = null;
   public RandomEntityRule[] rules = null;

   public RandomEntityProperties(String str, ResourceLocation[] locations) {
      ConnectedParser connectedParser = new ConnectedParser("RandomEntities");
      this.name = connectedParser.parseName(str);
      this.basePath = connectedParser.parseBasePath(str);
      this.resourceLocations = locations;
   }

   public boolean isDefault() {
      return this.rules != null ? false : this.resourceLocations == null;
   }

   public RandomEntityProperties(Properties properties, String str, ResourceLocation location) {
      ConnectedParser connectedParser = new ConnectedParser("RandomEntities");
      this.name = connectedParser.parseName(str);
      this.basePath = connectedParser.parseBasePath(str);
      this.rules = this.parseRules(properties, str, location, connectedParser);
   }

   public boolean isValid(String str) {
      if (this.resourceLocations == null && this.rules == null) {
         Config.warn("No skins specified: " + str);
         return false;
      } else {
         if (this.rules != null) {
            for (int i = 0; i < this.rules.length; i++) {
               if (!this.rules[i].isValid(str)) {
                  return false;
               }
            }
         }

         if (this.resourceLocations != null) {
            for (int j = 0; j < this.resourceLocations.length; j++) {
               ResourceLocation location = this.resourceLocations[j];
               if (!Config.hasResource(location)) {
                  Config.warn("Texture not found: " + location.getResourcePath());
                  return false;
               }
            }
         }

         return true;
      }
   }

   public ResourceLocation getTextureLocation(ResourceLocation location, IRandomEntity randomEntity) {
      if (this.rules != null) {
         for (int i = 0; i < this.rules.length; i++) {
            RandomEntityRule randomEntityRule = this.rules[i];
            if (randomEntityRule.matches(randomEntity)) {
               return randomEntityRule.getTextureLocation(location, randomEntity.getId());
            }
         }
      }

      if (this.resourceLocations != null) {
         int n = randomEntity.getId() % this.resourceLocations.length;
         return this.resourceLocations[n];
      } else {
         return location;
      }
   }

   private RandomEntityRule[] parseRules(Properties properties, String str, ResourceLocation location, ConnectedParser connectedParser) {
      ArrayList list = new ArrayList();
      int size = properties.size();

      for (int i = 0; i < size; i++) {
         int n = i + 1;
         String property = properties.getProperty("textures." + n);
         if (property == null) {
            property = properties.getProperty("skins." + n);
         }

         if (property != null) {
            RandomEntityRule randomEntityRule = new RandomEntityRule(properties, str, location, n, property, connectedParser);
            if (randomEntityRule.isValid(str)) {
               list.add(randomEntityRule);
            }
         }
      }

      return (RandomEntityRule[])list.toArray(new RandomEntityRule[list.size()]);
   }
}
