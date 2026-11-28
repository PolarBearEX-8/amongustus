package net.augustus.cosmetics.model;


import net.augustus.cosmetics.molang.MolangParser;
import net.augustus.cosmetics.molang.MolangQueryRegistrar;
public class GeckoLibRegistry {
   public static final GeoAnimationLoader ANIMATION_LOADER = new GeoAnimationLoader();
   public static final MolangParser MOLANG_PARSER;
   public static final GeoModelParser MODEL_PARSER = new GeoModelParser();

   public static void initialize() {
      MolangQueryRegistrar.registerVars(MOLANG_PARSER);
      GeoAnimationController.addModelFetcher(obj -> {
         return obj instanceof CosmeticAnimatable ? ((CosmeticAnimatable)obj).getAnimatedModel() : null;
      });
   }

   static {
      MOLANG_PARSER = new MolangParser();
   }
}
