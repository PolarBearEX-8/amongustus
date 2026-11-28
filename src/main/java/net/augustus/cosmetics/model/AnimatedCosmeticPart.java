package net.augustus.cosmetics.model;

import java.util.HashMap;

public abstract class AnimatedCosmeticPart {
   private HashMap effects;

   public boolean hasEffects() {
      return this.effects != null;
   }

   public boolean hasEffect(CosmeticEffectType cosmeticEffectType) {
      return this.hasEffects() && this.effects.containsKey(cosmeticEffectType);
   }

   private void ensureEffectsMap() {
      if (this.effects == null) {
         this.effects = new HashMap();
      }
   }

   public void addEffect(CosmeticEffect cosmeticEffect) {
      this.ensureEffectsMap();
      this.effects.put(cosmeticEffect.getType(), cosmeticEffect);
   }

   public HashMap getEffects() {
      this.ensureEffectsMap();
      return this.effects;
   }

   public void removeEffect(CosmeticEffectType cosmeticEffectType) {
      if (this.hasEffects()) {
         this.effects.remove(cosmeticEffectType);
      }
   }

   public CosmeticEffect getOrCreateEffect(CosmeticEffectType cosmeticEffectType) {
      return this.hasEffect(cosmeticEffectType) ? (CosmeticEffect)this.effects.get(cosmeticEffectType) : new CosmeticEffect(cosmeticEffectType);
   }




}
