package net.augustus.modules.modes.skinderp;

import net.augustus.events.listeners.TickListener;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import net.augustus.modules.fun.SkinDerp;
import net.augustus.settings.SettingGroup;
import net.augustus.utils.TimeHelper;
import net.minecraft.entity.player.EnumPlayerModelParts;

public class SequentialSkinDerpMode extends SkinDerpMode implements TickListener {
   private final TimeHelper timeHelper = new TimeHelper();
   private int currentPartIndex = 0;
   private boolean enablingPhase = false;
   private List enabledParts = new ArrayList();

   public SequentialSkinDerpMode(SkinDerp skinDerp, SettingGroup doubleValue) {
      super(skinDerp, doubleValue, "Sequential");
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(40);
   }

   @Override
   public void onEnable() {
      this.timeHelper.reset();
      this.currentPartIndex = 0;
      this.enablingPhase = false;
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
               this.setModelPartEnabled((EnumPlayerModelParts)this.enabledParts.get(this.currentPartIndex), this.enablingPhase);
               this.currentPartIndex++;
               if (this.currentPartIndex >= this.enabledParts.size()) {
                  this.currentPartIndex = 0;
                  this.enablingPhase = !this.enablingPhase;
               }
            }
         }
      }
   }

   private void refreshEnabledParts() {
      this.enabledParts.clear();
      EnumPlayerModelParts[] modelParts = this.getModelParts();
      int length = modelParts.length;

      for (int i = 0; i < length; i++) {
         EnumPlayerModelParts enumPlayerModelParts = modelParts[i];
         if (this.isPartEnabled(enumPlayerModelParts)) {
            this.enabledParts.add(enumPlayerModelParts);
         }
      }
   }

   public static BiFunction factory() {
      return (obj, obj2) -> new SequentialSkinDerpMode((SkinDerp)obj, (SettingGroup)obj2);
   }

}
