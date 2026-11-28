package net.augustus.modules.modes.skinderp;

import net.augustus.events.listeners.TickListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.function.BiFunction;
import net.augustus.modules.fun.SkinDerp;
import net.augustus.settings.SettingGroup;
import net.augustus.utils.TimeHelper;
import net.minecraft.entity.player.EnumPlayerModelParts;

public class GlitchSkinDerpMode extends SkinDerpMode implements TickListener {
   private final TimeHelper timeHelper = new TimeHelper();
   private final Random random = new Random();
   private final Map partStates = new HashMap();
   private int stepCounter = 0;

   public GlitchSkinDerpMode(SkinDerp skinDerp, SettingGroup doubleValue) {
      super(skinDerp, doubleValue, "Glitch");
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(40);
   }

   @Override
   public void onEnable() {
      this.timeHelper.reset();
      this.stepCounter = 0;

      for (EnumPlayerModelParts enumPlayerModelParts : this.getModelParts()) {
         this.partStates.put(enumPlayerModelParts, true);
      }
   }

   @Override
   public void onDisable() {
      this.enableAllParts();
      this.partStates.clear();
   }

   @Override
   public void onPreTick() {
      if (mc.thePlayer != null && mc.theWorld != null) {
         double d = Math.max(1.0, this.getDelayMs() * 0.3);
         if (this.timeHelper.reached(d)) {
            this.timeHelper.reset();
            this.stepCounter++;

            for (EnumPlayerModelParts enumPlayerModelParts : this.getModelParts()) {
               if (this.isPartEnabled(enumPlayerModelParts) && this.random.nextInt(100) < 70) {
                  boolean orDefault = (Boolean)(this.partStates.getOrDefault(enumPlayerModelParts, true));
                  boolean flag = !orDefault;
                  this.partStates.put(enumPlayerModelParts, flag);
                  this.setModelPartEnabled(enumPlayerModelParts, flag);
               }
            }

            if (this.stepCounter % 10 == 0) {
               for (EnumPlayerModelParts enumPlayerModelParts2 : this.getModelParts()) {
                  if (this.isPartEnabled(enumPlayerModelParts2)) {
                     this.setModelPartEnabled(enumPlayerModelParts2, true);
                     this.partStates.put(enumPlayerModelParts2, true);
                  }
               }
            }
         }
      }
   }

   public static BiFunction factory() {
      return (obj, obj2) -> new GlitchSkinDerpMode((SkinDerp)obj, (SettingGroup)obj2);
   }

}
