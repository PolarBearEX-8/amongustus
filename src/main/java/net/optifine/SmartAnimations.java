package net.optifine;

import java.util.BitSet;
import net.optifine.config.Config;
import net.optifine.shaders.Shaders;

public class SmartAnimations {
   private static boolean active;
   private static BitSet spritesRendered = new BitSet();
   private static BitSet texturesRendered = new BitSet();

   public static void spriteRendered(int n) {
      if (n >= 0) {
         spritesRendered.set(n);
      }
   }

   public static boolean isActive() {
      return active && !Shaders.isShadowPass;
   }

   public static void update() {
      active = Config.getGameSettings().ofSmartAnimations;
   }

   public static void resetSpritesRendered() {
      spritesRendered.clear();
   }

   public static void resetTexturesRendered() {
      texturesRendered.clear();
   }

   public static boolean isTextureRendered(int n) {
      return n < 0 ? false : texturesRendered.get(n);
   }

   public static void textureRendered(int n) {
      if (n >= 0) {
         texturesRendered.set(n);
      }
   }

   public static boolean isSpriteRendered(int n) {
      return n < 0 ? false : spritesRendered.get(n);
   }

   public static void spritesRendered(BitSet bitSet) {
      if (bitSet != null) {
         spritesRendered.or(bitSet);
      }
   }
}
