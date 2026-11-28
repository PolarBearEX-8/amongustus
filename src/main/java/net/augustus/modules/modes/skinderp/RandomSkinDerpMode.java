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

public class RandomSkinDerpMode extends SkinDerpMode implements TickListener {
   private final TimeHelper timeHelper = new TimeHelper();
   private final Random random = new Random();
   private final Map partStates = new HashMap();

   public RandomSkinDerpMode(SkinDerp skinDerp, SettingGroup doubleValue) {
      super(skinDerp, doubleValue, "Random");
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(40);
   }

   @Override
   public void onEnable() {
      this.timeHelper.reset();

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

            for (EnumPlayerModelParts enumPlayerModelParts : this.getModelParts()) {
               if (this.isPartEnabled(enumPlayerModelParts) && this.random.nextBoolean()) {
                  boolean orDefault = (Boolean)(this.partStates.getOrDefault(enumPlayerModelParts, true));
                  boolean flag = !orDefault;
                  this.partStates.put(enumPlayerModelParts, flag);
                  this.setModelPartEnabled(enumPlayerModelParts, flag);
               }
            }
         }
      }
   }

   public static BiFunction factory() {
      return (obj, obj2) -> new RandomSkinDerpMode((SkinDerp)obj, (SettingGroup)obj2);
   }

}
