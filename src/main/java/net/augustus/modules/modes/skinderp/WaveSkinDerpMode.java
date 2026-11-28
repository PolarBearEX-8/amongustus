package net.augustus.modules.modes.skinderp;

import net.augustus.events.listeners.TickListener;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import net.augustus.modules.fun.SkinDerp;
import net.augustus.settings.SettingGroup;
import net.augustus.utils.TimeHelper;
import net.minecraft.entity.player.EnumPlayerModelParts;

public class WaveSkinDerpMode extends SkinDerpMode implements TickListener {
   private final TimeHelper timeHelper = new TimeHelper();
   private int currentPartIndex = 0;
   private List enabledParts = new ArrayList();

   public WaveSkinDerpMode(SkinDerp skinDerp, SettingGroup doubleValue) {
      super(skinDerp, doubleValue, "Wave");
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(40);
   }

   @Override
   public void onEnable() {
      this.timeHelper.reset();
      this.currentPartIndex = 0;
      this.refreshEnabledParts();
   }

   @Override
   public void onDisable() {
      this.enableAllParts();
      this.enabledParts.clear();
   }

   @Override
   public void onPreTick() {
      if (mc.thePlayer != null && mc.theWorld != null) {
         if (this.timeHelper.reached(this.getDelayMs())) {
            this.timeHelper.reset();
            this.refreshEnabledParts();
            if (!this.enabledParts.isEmpty()) {
               for (EnumPlayerModelParts enumPlayerModelParts : (Iterable<EnumPlayerModelParts>)(this.enabledParts)) {
                  this.setModelPartEnabled(enumPlayerModelParts, false);
               }

               this.setModelPartEnabled((EnumPlayerModelParts)this.enabledParts.get(this.currentPartIndex), true);
               this.currentPartIndex = (this.currentPartIndex + 1) % this.enabledParts.size();
            }
         }
      }
   }

   private void refreshEnabledParts() {
      this.enabledParts.clear();

      for (EnumPlayerModelParts enumPlayerModelParts : this.getModelParts()) {
         if (this.isPartEnabled(enumPlayerModelParts)) {
            this.enabledParts.add(enumPlayerModelParts);
         }
      }
   }

   public static BiFunction factory() {
      return (obj, obj2) -> new WaveSkinDerpMode((SkinDerp)obj, (SettingGroup)obj2);
   }

}
