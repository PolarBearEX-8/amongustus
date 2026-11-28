package net.augustus.settings;

import net.augustus.Augustus;
import net.augustus.ClientManagers;
import net.augustus.utils.render.ShaderRenderPass;
import java.awt.Color;
import net.augustus.modules.Module;

public class BlurShadowSettings extends SettingComposite {
   private final SettingGroup parentGroup;
   private String namePrefix;
   private final Module parentModule;
   private BooleanValue shadow;
   private ColorSetting customShadowColor;
   private BooleanValue blur;
   private BooleanValue useCustomShadowColor;

   public BlurShadowSettings withCustomShadowColor(Color color) {
      Setting setting = new BooleanValue(this.parentModule, this.namePrefix + "CustomShadowColor", false, this.parentGroup).withVisibility(() -> this.isCustomShadowToggleVisible());
      this.useCustomShadowColor = (BooleanValue)this.registerNamedSetting("CustomShadowColor", setting);
      Setting setting2 = ((ColorSetting)new ColorSetting(this.parentModule, this.namePrefix + "CustomShadowColorValue", color, this.parentGroup).withDisplayName("CustomShadowColor")).withVisibility(() -> this.isCustomShadowColorVisible());
      this.customShadowColor = (ColorSetting)this.registerNamedSetting("CustomShadowColorValue", setting2);
      return this;
   }

   public BlurShadowSettings(Module module, SettingGroup doubleValue) {
       this(module, "", doubleValue);
   }

   public BlurShadowSettings(Module module, String str, SettingGroup doubleValue) {
       super();
      this.useCustomShadowColor = null;
      this.customShadowColor = null;
      this.namePrefix = "";
      this.namePrefix = str;
      this.parentGroup = doubleValue;
      this.parentModule = module;
      BooleanValue booleanValue = new BooleanValue(module, str + "Blur", true, doubleValue);
      this.blur = (BooleanValue)this.registerNamedSetting("Blur", booleanValue);
      BooleanValue booleanValue2 = new BooleanValue(module, str + "Shadow", true, doubleValue);
      this.shadow = (BooleanValue)this.registerNamedSetting("Shadow", booleanValue2);
   }

   public BlurShadowSettings(Module module) {
       this(module, "", null);
   }

   public int resolveShadowColor(int n) {
      int n2;
      if (this.useCustomShadowColor != null && this.useCustomShadowColor.getValue()) {
         n2 = this.customShadowColor.getColorInt((byte)0);
      } else {
         n2 = n;
      }

      return n2;
   }

   public boolean isBlurActive() {
      return this.blur.getValue() && ClientManagers.moduleManager.blur.isToggled();
   }

   public boolean isShadowActive() {
      return this.shadow.getValue() && ClientManagers.moduleManager.shadow.isToggled();
   }

   public BlurShadowSettings withCustomShadowColor(int n) {
      return this.withCustomShadowColor(new Color(n));
   }

   public BlurShadowSettings disableBlurByDefault() {
      if (this.blur != null) {
         this.blur.setValue(false);
         this.blur.setDefaultValue(false);
      }

      return this;
   }

   public int resolvePassColor(ShaderRenderPass shaderRenderPass, int n) {
      int n2;
      if (shaderRenderPass == ShaderRenderPass.SHADOW && this.useCustomShadowColor != null && this.useCustomShadowColor.getValue()) {
         n2 = this.customShadowColor.getColorInt((byte)0);
      } else {
         n2 = n;
      }

      return n2;
   }

   public int resolvePassColor(ShaderRenderPass shaderRenderPass, int n, int n2) {
      return shaderRenderPass == ShaderRenderPass.SHADOW && this.useCustomShadowColor != null && this.useCustomShadowColor.getValue() ? this.customShadowColor.getColorInt(n2) : n;
   }

   public BlurShadowSettings removeShadowSetting() {
      ClientManagers.settingManager.getSettings().remove(this.shadow);
      if (this.parentGroup != null) {
         this.parentGroup.getChildren().remove(this.shadow);
      } else {
         this.parentModule.getSettings().remove(this.shadow);
      }

      this.shadow = null;
      return this;
   }

   public BlurShadowSettings disableShadowByDefault() {
      if (this.shadow != null) {
         this.shadow.setValue(false);
         this.shadow.setDefaultValue(false);
      }

      return this;
   }

   public BlurShadowSettings disableBothByDefault() {
      if (this.shadow != null) {
         this.shadow.setValue(false);
         this.shadow.setDefaultValue(false);
      }

      if (this.blur != null) {
         this.blur.setValue(false);
         this.blur.setDefaultValue(false);
      }

      return this;
   }

   public BlurShadowSettings(Module module, String str) {
       this(module, str, null);
   }

   private boolean isCustomShadowToggleVisible() {
      return this.shadow.getValue() && !Augustus.getInstance().getModuleManager().shadow.customColor.getValue();
   }

   public int resolveShadowColor(int n, int n2) {
      return this.useCustomShadowColor != null && this.useCustomShadowColor.getValue() ? this.customShadowColor.getColorInt(n2) : n;
   }

   private boolean isCustomShadowColorVisible() {
      return this.shadow.getValue() && !Augustus.getInstance().getModuleManager().shadow.customColor.getValue() && this.useCustomShadowColor.getValue();
   }

   public BlurShadowSettings removeBlurSetting() {
      ClientManagers.settingManager.getSettings().remove(this.blur);
      if (this.parentGroup != null) {
         this.parentGroup.getChildren().remove(this.blur);
      } else {
         this.parentModule.getSettings().remove(this.blur);
      }

      this.blur = null;
      return this;
   }


}
