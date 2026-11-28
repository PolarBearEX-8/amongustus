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

public class DiscoSkinDerpMode extends SkinDerpMode implements TickListener {
   private final TimeHelper timeHelper = new TimeHelper();
   private final Random random = new Random();
   private final Map partStates = new HashMap();
   private int stepCounter;

   public DiscoSkinDerpMode(SkinDerp skinDerp, SettingGroup doubleValue) {
      super(skinDerp, doubleValue, "Disco");
      int n = 0;
      this.stepCounter = 0;
      if (null != null) {
         n++;
      }
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
         if (this.timeHelper.reached(this.getDelayMs())) {
            this.timeHelper.reset();
            this.stepCounter++;
            EnumPlayerModelParts[] modelParts = this.getModelParts();
            if (this.stepCounter % 4 == 0) {
               boolean flag = this.random.nextBoolean();

               for (EnumPlayerModelParts enumPlayerModelParts : modelParts) {
                  if (this.isPartEnabled(enumPlayerModelParts)) {
                     this.setModelPartEnabled(enumPlayerModelParts, flag);
                     this.partStates.put(enumPlayerModelParts, flag);
                  }
               }
            } else if (this.stepCounter % 2 == 0) {
               for (int i = 0; i < modelParts.length; i++) {
                  EnumPlayerModelParts enumPlayerModelParts2 = modelParts[i];
                  if (this.isPartEnabled(enumPlayerModelParts2)) {
                     boolean flag2 = i % 2 == 0;
                     this.setModelPartEnabled(enumPlayerModelParts2, flag2);
                     this.partStates.put(enumPlayerModelParts2, flag2);
                  }
               }
            } else {
               for (EnumPlayerModelParts enumPlayerModelParts3 : modelParts) {
                  if (this.isPartEnabled(enumPlayerModelParts3) && this.random.nextInt(100) < 40) {
                     boolean orDefault = (Boolean)(this.partStates.getOrDefault(enumPlayerModelParts3, true));
                     boolean flag3 = !orDefault;
                     this.setModelPartEnabled(enumPlayerModelParts3, flag3);
                     this.partStates.put(enumPlayerModelParts3, flag3);
                  }
               }
            }
         }
      }
   }

   public static BiFunction factory() {
      return (obj, obj2) -> new DiscoSkinDerpMode((SkinDerp)obj, (SettingGroup)obj2);
   }

}
