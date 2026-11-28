package net.augustus.cosmetics.model;

import net.augustus.cosmetics.MoreCosmeticsCore;
import net.labymod.Converter;
import net.labymod.RawGeoModel;

public class GeoModelParser {

   public GeoModel loadModel(String str, String str2) {

      try {
         RawGeometryTree rawGeometryTree = RawGeometryTree.parseHierarchy(Converter.fromJsonString(str2), "");
         return DefaultGeoModelBuilder.getGeoBuilder(str).constructGeoModel(rawGeometryTree);
      } catch (Exception e) {
         MoreCosmeticsCore.LOGGER.error(String.format("Error parsing %S", str), e);
         throw new RuntimeException(e);
      }
   }

}
