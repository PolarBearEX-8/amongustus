package net.augustus.modules.render;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.events.EventGameLoop;
import net.augustus.utils.MoveUtil;
import net.augustus.utils.TuplePair;
import net.augustus.events.listeners.GameLoopListener;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.DoubleValue;
import net.augustus.utils.TimeHelper;
import net.minecraft.util.EnumParticleTypes;

public class Particle extends Module implements GameLoopListener {
   private final Map particleSettingsMap;
   private final BooleanValue onlyWhileMoving;

   public Particle() {
       super("Particle", "PartkelBeimLaufenUnterEinem", new Color(247, 186, 106), Categorys.RENDER);
      this.particleSettingsMap = new HashMap();
      this.onlyWhileMoving = new BooleanValue(this, "OnlyWhileMoving", true);

      for (EnumParticleTypes enumParticleTypes : EnumParticleTypes.values()) {
         if (enumParticleTypes.getParticleID() >= 36 && enumParticleTypes.getParticleID() <= 38) {
            return;
         }

         StringBuilder sb = new StringBuilder().append("Use ");
         String str = enumParticleTypes.name();
         BooleanValue booleanValue = new BooleanValue(this, sb.append(this.formatEnumName(str)).toString(), false);
         sb = new StringBuilder();
         String str2 = enumParticleTypes.name();
         DoubleValue doubleSetting = new DoubleValue(
            this, sb.append(this.formatEnumName(str2)).append("Delay ").toString(), 250.0, 1.0, 1000.0, 0
         );
         DoubleValue doubleSetting2 = doubleSetting.visibleWhen(() -> booleanValue.getValue());
         this.particleSettingsMap.put(enumParticleTypes, new TuplePair(booleanValue, new TuplePair(doubleSetting2, new TimeHelper())));
      }
   }

   @Override
   public void onEnable() {
   }

   @Override
   public void onDisable() {
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(16);
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

   @Override
   public void onGameLoop(EventGameLoop event) {
      if (MoveUtil.hasHorizontalMotion() || !this.onlyWhileMoving.getValue()) {
         for (Entry entry : (Iterable<Entry>)(this.particleSettingsMap.entrySet())) {
            if (((BooleanValue)((TuplePair)entry.getValue()).getFirst()).getValue()) {
               try {
                  if (((TimeHelper)((TuplePair)((TuplePair)entry.getValue()).getSecond()).getSecond()).reached(((DoubleValue)((TuplePair)((TuplePair)entry.getValue()).getSecond()).getFirst()).getValue())) {
                     ((TimeHelper)((TuplePair)((TuplePair)entry.getValue()).getSecond()).getSecond()).reset();
                     mc.effectRenderer.emitParticleAtEntity(mc.thePlayer, (EnumParticleTypes)entry.getKey(), mc.thePlayer.height / -2.0F + 0.1F);
                  }
               } catch (Exception e) {
               }
            }
         }
      }
   }

}
