package net.augustus.cosmetics.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import net.labymod.ModelProperties;

public class GeoModel {
   public List topLevelBones = new ArrayList();
   public ModelProperties properties;

   public Optional getBone(String str) {

      for (GeoBone geoBone : (Iterable<GeoBone>)(this.topLevelBones)) {
         GeoBone boneRecursively = this.getBoneRecursively(str, geoBone);
         if (boneRecursively != null) {
            return Optional.of(boneRecursively);
         }
      }

      return Optional.empty();
   }

   private GeoBone getBoneRecursively(String str, GeoBone geoBone) {
      if (geoBone.name.equals(str)) {
         return geoBone;
      } else {
         for (GeoBone geoBone2 : (Iterable<GeoBone>)(geoBone.childBones)) {
            if (geoBone2.name.equals(str)) {
               return geoBone2;
            }

            GeoBone boneRecursively = this.getBoneRecursively(str, geoBone2);
            if (boneRecursively != null) {
               return boneRecursively;
            }
         }

         return null;
      }
   }
}
