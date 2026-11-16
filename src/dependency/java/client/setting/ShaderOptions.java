package client.setting;

import client.Client;
import client.ClientGlobals;
import client.event.SupplementalListener;
import client.module.Module;
import client.render.RenderPass;
import client.util.BooleanSupplier;
import com.google.gson.JsonObject;
import java.awt.Color;

public class ShaderOptions extends Setting {
   public ColorSetting colorSetting;
   public BooleanSetting settingBlur;
   public BooleanSetting settingShaderValue;
   public String labelText2;
   public BooleanSetting settingShadow;
   public static int count10 = 0;
   public SettingGroup settingGroup;

   public ShaderOptions resolveShaderOptions(String text) {
      super.setText4(text);
      if (this.settingShadow != null) {
         this.settingShadow.setText4(text);
      }

      if (this.settingBlur != null) {
         this.settingBlur.setText4(text);
      }

      if (this.settingShaderValue != null) {
         this.settingShaderValue.setText4(text);
      }

      if (this.colorSetting != null) {
         this.colorSetting.setText4(text);
      }

      return this;
   }

   private boolean isActiveAsFlag() {
      return this.settingShadow.isEnabled()
         && !Client.getClient().getModuleManager().shadow.settingCustomColor.isEnabled()
         && this.settingShaderValue.isEnabled();
   }

   public int computeCount(int count, int count2) {
      return this.settingShaderValue != null && this.settingShaderValue.isEnabled() ? this.colorSetting.getInt12(count2) : count;
   }

   public ShaderOptions getShaderOptions() {
      ClientGlobals.auxiliaryCopyOnWriteArrayListEntry.getList().remove(this.settingBlur);
      if (this.settingGroup != null) {
         this.settingGroup.getItemList3().remove(this.settingBlur);
      } else {
         this.getModule().getSettings().remove(this.settingBlur);
      }

      this.settingBlur = null;
      return this;
   }

   public int getIndex(RenderPass renderPass, int count, int count2) {
      return renderPass == RenderPass.SHADOW && this.settingShaderValue != null && this.settingShaderValue.isEnabled() ? this.colorSetting.getInt12(count2) : count;
   }

   public ShaderOptions getShaderOptions2() {
      if (this.settingShadow != null) {
         this.settingShadow.setState3(false);
         this.settingShadow.setEnabled(false);
      }

      if (this.settingBlur != null) {
         this.settingBlur.setState3(false);
         this.settingBlur.setEnabled(false);
      }

      return this;
   }

   private boolean isActive() {
      return this.settingShadow.isEnabled() && !Client.getClient().getModuleManager().shadow.settingCustomColor.isEnabled();
   }

   @Override
   public JsonObject resolveJsonObject(SupplementalListener supplementalListener) {
      return null;
   }

   @Override
   public void updateState() {
      if (this.settingShadow != null) {
         this.settingShadow.updateState();
      }

      if (this.settingBlur != null) {
         this.settingBlur.updateState();
      }

      if (this.settingShaderValue != null) {
         this.settingShaderValue.updateState();
      }

      if (this.colorSetting != null) {
         this.colorSetting.updateState();
      }
   }

   @Override
   public Setting getSetting(String text) {
      return this.resolveShaderOptions(text);
   }

   public int getIndex(RenderPass renderPass, int count) {
      return renderPass == RenderPass.SHADOW && this.settingShaderValue != null && this.settingShaderValue.isEnabled() ? this.colorSetting.getInt12(0) : count;
   }

   public ShaderOptions getShaderOptions(Color color) {
      long value = 0x3e2ad4ac564eL;
      BooleanSetting setting = new BooleanSetting(this.getModule(), this.labelText2 + "CustomShadowColor", false);
      BooleanSupplier booleanSupplier = this::isActive;
      this.settingShaderValue = (BooleanSetting)setting.getSetting(booleanSupplier);
      ColorSetting setting2 = new ColorSetting(this.getModule(), this.labelText2 + "CustomShadowColorValue", color);
      String text = "CustomShadowColor";
      ColorSetting setting3 = (ColorSetting)setting2.getSetting(text);
      BooleanSupplier booleanSupplier2 = this::isActiveAsFlag;
      this.colorSetting = (ColorSetting)setting3.getSetting(booleanSupplier2);
      return this;
   }

   public int getInt3(int count) {
      return this.settingShaderValue != null && this.settingShaderValue.isEnabled() ? this.colorSetting.getInt12(0) : count;
   }

   @Override
   public void updateSupplementalListener(SupplementalListener supplementalListener, JsonObject jsonObject) {
   }

   public boolean canExecute() {
      return this.settingBlur.isEnabled() && ((Module)ClientGlobals.moduleManager.blur).isToggled();
   }

   public ShaderOptions getShaderOptions3() {
      if (this.settingShadow != null) {
         this.settingShadow.setState3(false);
         this.settingShadow.setEnabled(false);
      }

      return this;
   }

   public ShaderOptions getShaderOptionsAsShaderOptions() {
      ClientGlobals.auxiliaryCopyOnWriteArrayListEntry.getList().remove(this.settingShadow);
      if (this.settingGroup != null) {
         this.settingGroup.getItemList3().remove(this.settingShadow);
      } else {
         this.getModule().getSettings().remove(this.settingShadow);
      }

      this.settingShadow = null;
      return this;
   }

   public ShaderOptions getShaderOptions5() {
      if (this.settingBlur != null) {
         this.settingBlur.setState3(false);
         this.settingBlur.setEnabled(false);
      }

      return this;
   }

   public boolean canExecute2() {
      return this.settingShadow.isEnabled() && ((Module)ClientGlobals.moduleManager.shadow).isToggled();
   }

   @Override
   public void updateState3() {
   }

   @Override
   public int getCount10() {
      return 0;
   }

   public ShaderOptions(Module module, SettingGroup settingGroup2) {
        super(module, "ShaderValue" + count10);
      byte byteValue = 0;
      int count = 1331503962;
      short shortValue = -15386;
      
      this.settingShaderValue = null;
      this.colorSetting = null;
      this.settingGroup = null;
      this.labelText2 = "";
      count10++;
      this.settingGroup = settingGroup2;
      settingGroup2.getItemList3().add(this);
      BooleanSetting setting = new BooleanSetting(module, "Blur", true, settingGroup2);
      BooleanSupplier booleanSupplier = this::isVisible;
      this.settingBlur = (BooleanSetting)setting.getSetting(booleanSupplier);
      setting = new BooleanSetting(module, "Shadow", true, settingGroup2);
      BooleanSupplier booleanSupplier2 = this::isVisible;
      this.settingShadow = (BooleanSetting)setting.getSetting(booleanSupplier2);
   }

   public ShaderOptions(Module module, String text, SettingGroup settingGroup2) {
        super(module, "ShaderValue" + count10 + text);
      short shortValue = 2588;
      int count = 1113798;
      byte byteValue = -44;
      
      this.settingShaderValue = null;
      this.colorSetting = null;
      this.settingGroup = null;
      this.labelText2 = "";
      count10++;
      this.settingGroup = settingGroup2;
      settingGroup2.getItemList3().add(this);
      this.labelText2 = text;
      BooleanSetting setting = new BooleanSetting(module, text + "Blur", true, settingGroup2);
      BooleanSupplier booleanSupplier = this::isVisible;
      this.settingBlur = (BooleanSetting)setting.getSetting(booleanSupplier);
      setting = new BooleanSetting(module, text + "Shadow", true, settingGroup2);
      BooleanSupplier booleanSupplier2 = this::isVisible;
      this.settingShadow = (BooleanSetting)setting.getSetting(booleanSupplier2);
   }

   public ShaderOptions(Module module) {
        super(module, "ShaderValue" + count10);
      long value = 0x41614767e503L;
      
      this.settingShaderValue = null;
      this.colorSetting = null;
      this.settingGroup = null;
      this.labelText2 = "";
      count10++;
      module.getSettings().add(this);
      BooleanSetting setting = new BooleanSetting(module, "Blur", true);
      BooleanSupplier booleanSupplier = this::isVisible;
      this.settingBlur = (BooleanSetting)setting.getSetting(booleanSupplier);
      setting = new BooleanSetting(module, "Shadow", true);
      BooleanSupplier booleanSupplier2 = this::isVisible;
      this.settingShadow = (BooleanSetting)setting.getSetting(booleanSupplier2);
   }

   public ShaderOptions(Module module, String text) {
        super(module, "ShaderValue" + count10 + text);
      long value = 0x47d5d7c5ee07L;
      
      this.settingShaderValue = null;
      this.colorSetting = null;
      this.settingGroup = null;
      this.labelText2 = "";
      count10++;
      module.getSettings().add(this);
      this.labelText2 = text;
      BooleanSetting setting = new BooleanSetting(module, text + "Blur", true);
      BooleanSupplier booleanSupplier = this::isVisible;
      this.settingBlur = (BooleanSetting)setting.getSetting(booleanSupplier);
      setting = new BooleanSetting(module, text + "Shadow", true);
      BooleanSupplier booleanSupplier2 = this::isVisible;
      this.settingShadow = (BooleanSetting)setting.getSetting(booleanSupplier2);
   }
}
