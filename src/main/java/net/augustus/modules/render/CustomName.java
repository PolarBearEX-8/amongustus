package net.augustus.modules.render;

import java.awt.Color;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.settings.TextInputSetting;
import net.augustus.events.listeners.LoadWorldListener;
import net.augustus.events.EventLoadWorld;
import net.augustus.Augustus;
import net.augustus.settings.SettingChangeListener;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.settings.StringValue;

public class CustomName extends Module implements LoadWorldListener {
   public final StringValue modes;
   public final StringValue nameFormatting;
   public final TextInputSetting customName;
   public final String pcUserName;
   public String customDisplayName;

   public CustomName() {
       super("CustomName", "KundenspezifischerName", new Color(63, 21, 21), Categorys.RENDER);
      this.modes = (StringValue)new StringValue(this, "Modes", "AugustusName", new String[]{"AugustusName", "IngameName", "PCName", "Custom"}).withChangeListener(() -> this.refreshCustomName());
      this.nameFormatting = (StringValue)new StringValue(
         this, "Name-Formatting", "Xanax", new String[]{"Xanax", "Xanax2", "Centaurus", "Centaurus2", "Skid", "Simple"}
      ).withChangeListener(() -> this.refreshCustomName());
      this.customName = (TextInputSetting)new TextInputSetting(this, "Custom name", "Example Name").withVisibility(() -> this.isCustomNameVisible());
      this.pcUserName = System.getProperty("user.name");
   }

   @Override
   public void onEnable() {
   }

   @Override
   public void onDisable() {
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(17);
   }

   public void refreshCustomName() {
      switch (this.modes.getSelectedValue()) {
         case "AugustusName":
            this.customDisplayName = Augustus.getInstance().getUsername();
            break;
         case "IngameName":
            this.customDisplayName = mc.getSession().getUsername();
            break;
         case "PCName":
            this.customDisplayName = this.pcUserName;
            break;
         case "Custom":
            this.customDisplayName = this.customName.getValue();
      }
   }

   @Override
   public void onLoadWorld(EventLoadWorld event) {
      this.refreshCustomName();
   }

   private boolean isCustomNameVisible() {
      return this.modes.getSelectedValue().equals("Custom");
   }

}
