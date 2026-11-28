package net.augustus.modules.render;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.utils.TuplePair;
import net.augustus.events.EventAttackEntity;
import net.augustus.events.listeners.AttackEntityListener;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.DoubleValue;
import net.minecraft.util.EnumParticleTypes;

public class AttackEffects extends Module implements AttackEntityListener {
   private final Map particleSettingsMap;
   public BooleanValue noColorFilterOnCrits;

   @Override
   public void onEnable() {
   }

   @Override
   public void onDisable() {
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(1);
   }

   public AttackEffects() {
       super("AttackEffects", "AngriffEffekte", new Color(36, 169, 176), Categorys.RENDER);
      this.particleSettingsMap = new HashMap();

      for (EnumParticleTypes enumParticleTypes : EnumParticleTypes.values()) {
         if (enumParticleTypes.getParticleID() >= 36 && enumParticleTypes.getParticleID() <= 38) {
            break;
         }

         StringBuilder sb = new StringBuilder().append("Use ");
         String str = enumParticleTypes.name();
         BooleanValue booleanValue = new BooleanValue(this, sb.append(this.formatEnumName(str)).toString(), false);
         sb = new StringBuilder();
         String str2 = enumParticleTypes.name();
         DoubleValue doubleSetting = new DoubleValue(
            this, sb.append(this.formatEnumName(str2)).append(" Count").toString(), 1.0, 1.0, 20.0, 0
         );
         DoubleValue doubleSetting2 = doubleSetting.visibleWhen(() -> booleanValue.getValue());
         this.particleSettingsMap.put(enumParticleTypes, new TuplePair(booleanValue, doubleSetting2));
      }

      this.noColorFilterOnCrits = new BooleanValue(this, "No color filter on crits", false);
   }

   @Override
   public void onAttackEntity(EventAttackEntity event) {
      if (event.getTarget() != null) {
         for (Entry entry : (Iterable<Entry>)(this.particleSettingsMap.entrySet())) {
            if (((BooleanValue)((TuplePair)entry.getValue()).getFirst()).getValue()) {
               try {
                  for (int i = 0; (double)i < ((DoubleValue)((TuplePair)entry.getValue()).getSecond()).getValue(); i++) {
                     mc.effectRenderer.emitParticleAtEntity(event.getTarget(), (EnumParticleTypes)entry.getKey());
                  }
               } catch (Exception e) {
               }
            }
         }
      }
   }

   private String formatEnumName(String str) {
      str = str.replace("_", " ");
      StringBuilder sb = new StringBuilder();
      char[] chars = str.toCharArray();

      for (int i = 0; i < chars.length; i++) {
         char ch = chars[i];
         if (i != 0 && chars[i - 1] != ' ') {
            sb.append(String.valueOf(ch).toLowerCase());
         } else {
            sb.append(ch);
         }
      }

      return sb.toString();
   }

}
