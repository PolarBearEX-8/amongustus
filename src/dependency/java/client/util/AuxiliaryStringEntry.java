package client.util;

import client.Client;
import client.setting.Setting;

public abstract class AuxiliaryStringEntry implements MinecraftAccessor {
   public String labelText;

   public void applyText(String text) {
      String text2 = "§6[§9" + Client.getClient().getString4() + "§6] ";
      MinecraftAccessor.minecraft.ingameGUI.getInternalLoggerEntry().applyIChatComponent(RayTraceUtil.processAction(text2 + "§7" + text));
   }

   public abstract void updateState();

   public void setLabelText(String labelText2) {
      this.labelText = labelText2;
   }

   public static Setting[] getSettingArray() {
      return null;
   }

   public String getString() {
      return Client.getClient().getModuleManager().clientSettings.settingCommandPrefixes.getSelected() + this.labelText;
   }

   public abstract void printStackTrace(String[] textArray, String text);

   public AuxiliaryStringEntry(String text) {
      this.labelText = text;
   }
}
