package client.util;

import client.event.PostRenderObserver;
import java.util.ArrayList;
import java.util.Properties;
import net.minecraft.util.ResourceLocation;

public class CurveEngine {
   public String labelText = null;
   public String labelText2 = null;
   public ResourceLocation[] resourceLocationArray = null;
   public WeightsBiomesController[] weightsBiomesControllerArray = null;

   private WeightsBiomesController[] resolveWeightsBiomesControllerArray(Properties properties, String text, ResourceLocation location, NetherManager netherManager) {
      ArrayList list = new ArrayList();
      int size = properties.size();

      for (int index = 0; index < size; index++) {
         int count = index + 1;
         String property = properties.getProperty("textures." + count);
         if (property == null) {
            property = properties.getProperty("skins." + count);
         }

         if (property != null) {
            WeightsBiomesController weightsBiomesController = new WeightsBiomesController(properties, text, location, count, property, netherManager);
            if (weightsBiomesController.matchesText(text)) {
               list.add(weightsBiomesController);
            }
         }
      }

      return (WeightsBiomesController[]) list.toArray(new WeightsBiomesController[list.size()]);
   }

   public boolean isActive() {
      return this.weightsBiomesControllerArray != null ? false : this.resourceLocationArray == null;
   }

   public CurveEngine(Properties properties, String text, ResourceLocation location) {
      NetherManager netherManager = new NetherManager("RandomEntities");
      this.labelText = netherManager.resolveText(text);
      this.labelText2 = netherManager.resolveTextByText(text);
      this.weightsBiomesControllerArray = this.resolveWeightsBiomesControllerArray(properties, text, location, netherManager);
   }

   public boolean matchesText(String text) {
      if (this.resourceLocationArray == null && this.weightsBiomesControllerArray == null) {
         BuildManager.applyText3("No skins specified: " + text);
         return false;
      } else {
         if (this.weightsBiomesControllerArray != null) {
            for (int index = 0; index < this.weightsBiomesControllerArray.length; index++) {
               if (!this.weightsBiomesControllerArray[index].matchesText(text)) {
                  return false;
               }
            }
         }

         if (this.resourceLocationArray != null) {
            for (int index2 = 0; index2 < this.resourceLocationArray.length; index2++) {
               ResourceLocation location = this.resourceLocationArray[index2];
               if (!BuildManager.matchesResourceLocation3(location)) {
                  BuildManager.applyText3("Texture not found: " + location.getText1());
                  return false;
               }
            }
         }

         return true;
      }
   }

   public CurveEngine(String text, ResourceLocation[] locationArray) {
      NetherManager netherManager = new NetherManager("RandomEntities");
      this.labelText = netherManager.resolveText(text);
      this.labelText2 = netherManager.resolveTextByText(text);
      this.resourceLocationArray = locationArray;
   }

   public ResourceLocation resolveResourceLocation(ResourceLocation location, PostRenderObserver postRenderObserver) {
      if (this.weightsBiomesControllerArray != null) {
         for (int index = 0; index < this.weightsBiomesControllerArray.length; index++) {
            WeightsBiomesController weightsBiomesController = this.weightsBiomesControllerArray[index];
            if (weightsBiomesController.matchesPostRenderObserver(postRenderObserver)) {
               return weightsBiomesController.resolveResourceLocation(location, postRenderObserver.getCountAsIndex());
            }
         }
      }

      if (this.resourceLocationArray != null) {
         int count = postRenderObserver.getCountAsIndex() % this.resourceLocationArray.length;
         return this.resourceLocationArray[count];
      } else {
         return location;
      }
   }
}
