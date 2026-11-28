package net.augustus.modules.render;

import java.awt.Color;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.events.listeners.NightVisionListener;
import net.augustus.events.EventNightVision;
import net.augustus.settings.SettingChangeListener;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.StringValue;
import net.minecraft.client.settings.GameSettings;

public class FullBright extends Module implements NightVisionListener {
   public final StringValue mode;
   public final BooleanValue other;
   private float savedGamma;

   public FullBright() {
       super("FullBright", "VolleHelligkeit", new Color(188, 213, 25), Categorys.RENDER);
      this.mode = (StringValue)new StringValue(this, "Mode", "NightVision", new String[]{"NightVision", "Gamma"}).withChangeListener(() -> this.onModeChanged());
      this.other = (BooleanValue)new BooleanValue(this, "Other", true).withVisibility(() -> this.isOtherNightVisionVisible());
      this.savedGamma = 1.0F;
   }

   @Override
   public void onEnable() {
      this.savedGamma = mc.gameSettings.gammaSetting;
      if (this.mode.getSelectedValue().equals("Gamma")) {
         mc.gameSettings.gammaSetting = 10.0F;
      }
   }

   @Override
   public void onDisable() {
      ((GameSettings)null).gammaSetting = Math.min(this.savedGamma, 1.0F);
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(22);
   }

   @Override
   public void onNightVision(EventNightVision event) {
      if (!this.mode.getSelectedValue().equals("Gamma")) {
         event.setEnabled(true);
      }
   }

   @Override
   public void onFogNightVision(EventNightVision event) {
      if (!this.other.getValue()) {
         event.setEnabled(true);
      }
   }

   private boolean isOtherNightVisionVisible() {
      return this.mode.getSelectedValue().equals("NightVision");
   }

   private void onModeChanged() {
      if (this.isToggled()) {
         if (this.mode.getSelectedValue().equals("Gamma")) {
            mc.gameSettings.gammaSetting = 10.0F;
         } else if (mc.gameSettings.gammaSetting > 1.0F) {
            mc.gameSettings.gammaSetting = Math.min(this.savedGamma, 1.0F);
         }
      } else if (mc.gameSettings.gammaSetting > 1.0F) {
         mc.gameSettings.gammaSetting = Math.min(this.savedGamma, 1.0F);
      }
   }

}
