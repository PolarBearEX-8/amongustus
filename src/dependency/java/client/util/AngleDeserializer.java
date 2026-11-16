package client.util;

import client.event.IndirectListener;
import client.event.PreUpdateObserver;
import client.render.ShadersManager;
import java.util.HashMap;
import java.util.Map;

public class AngleDeserializer implements IndirectListener {
   public Map map = new HashMap();

   public boolean containsKey(String text) {
      return this.map.containsKey(text);
   }

   public boolean matchesText(String text, PreUpdateObserver preUpdateObserver) {
      if (this.map.containsKey(text)) {
         ShadersManager.applyText3("Expression already defined: " + text);
         return false;
      } else {
         this.map.put(text, preUpdateObserver);
         return true;
      }
   }

   private void updateState() {
      BiomeTemperatureType[] values = BiomeTemperatureType.values();

      for (int index = 0; index < values.length; index++) {
         BiomeTemperatureType biomeTemperatureType = values[index];
         this.applyMap(this.map, biomeTemperatureType);
      }

      HurtInvisibleEnum[] values2 = HurtInvisibleEnum.values();

      for (int index2 = 0; index2 < values2.length; index2++) {
         HurtInvisibleEnum hurtInvisibleEnum = values2[index2];
         this.map.put(hurtInvisibleEnum.getLabelText(), hurtInvisibleEnum);
      }

      for (BiomeIDIsOutOfBoundsManager biomeIDIsOutOfBoundsManager : (Iterable<BiomeIDIsOutOfBoundsManager>) (Iterable) BiomeIDIsOutOfBoundsManager.map.values()) {
         String text = biomeIDIsOutOfBoundsManager.labelText.trim();
         String text2 = "BIOME_" + text.toUpperCase().replace(' ', '_');
         int count = biomeIDIsOutOfBoundsManager.count;
         AimSorterAimValidatorImpl aimSorterAimValidatorImpl = new AimSorterAimValidatorImpl((float)count);
         this.matchesText(text2, aimSorterAimValidatorImpl);
      }
   }

   @Override
   public PreUpdateObserver get(String text) {
      return (PreUpdateObserver)this.map.get(text);
   }

   public AngleDeserializer(Map map2) {
      this.updateState();

      for (String text : (Iterable<String>) (Iterable) map2.keySet()) {
         PreUpdateObserver preUpdateObserver = (PreUpdateObserver)map2.get(text);
         this.matchesText(text, preUpdateObserver);
      }
   }

   private void applyMap(Map map2, BiomeTemperatureType biomeTemperatureType) {
      String[] stringArray = biomeTemperatureType.getStringArray();
      if (stringArray == null) {
         map2.put(biomeTemperatureType.getLabelText(), new AimSorterAimValidator(biomeTemperatureType));
      } else {
         for (int index = 0; index < stringArray.length; index++) {
            String text = stringArray[index];
            String[] stringArray2 = biomeTemperatureType.getStringArray2();
            if (stringArray2 == null) {
               map2.put(biomeTemperatureType.getLabelText() + "." + text, new AimSorterAimValidator(biomeTemperatureType, index));
            } else {
               for (int index2 = 0; index2 < stringArray2.length; index2++) {
                  String text2 = stringArray2[index2];
                  map2.put(biomeTemperatureType.getLabelText() + "." + text + "." + text2, new AimSorterAimValidator(biomeTemperatureType, index, index2));
               }
            }
         }
      }
   }
}
