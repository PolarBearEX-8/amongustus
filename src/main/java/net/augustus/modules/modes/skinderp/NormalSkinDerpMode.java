package net.augustus.modules.modes.skinderp;

import net.augustus.events.listeners.TickListener;
import java.util.function.BiFunction;
import net.augustus.modules.fun.SkinDerp;
import net.augustus.settings.SettingGroup;
import net.augustus.utils.TimeHelper;
import net.minecraft.entity.player.EnumPlayerModelParts;

public class NormalSkinDerpMode extends SkinDerpMode implements TickListener {
   private final TimeHelper timeHelper = new TimeHelper();
   private boolean partsVisible = true;

   public NormalSkinDerpMode(SkinDerp skinDerp, SettingGroup doubleValue) {
      super(skinDerp, doubleValue, "Normal");
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(40);
   }

   @Override
   public void onEnable() {
      this.partsVisible = true;
      this.timeHelper.reset();
   }

   @Override
   public void onDisable() {
      this.enableAllParts();
   }

   @Override
   public void onPreTick() {
      if (mc.thePlayer != null && mc.theWorld != null) {
         if (this.timeHelper.reached(this.getDelayMs())) {
            this.timeHelper.reset();
            this.partsVisible = !this.partsVisible;

            for (EnumPlayerModelParts enumPlayerModelParts : this.getModelParts()) {
               if (this.isPartEnabled(enumPlayerModelParts)) {
                  this.setModelPartEnabled(enumPlayerModelParts, this.partsVisible);
               }
            }
         }
      }
   }

   public static BiFunction factory() {
      return (obj, obj2) -> new NormalSkinDerpMode((SkinDerp)obj, (SettingGroup)obj2);
   }

}
