package net.augustus.settings.options;

import net.augustus.settings.SelectableOption;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.modules.Module;
import net.augustus.modules.world.FastBreak;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.StringValue;
import net.augustus.settings.SettingGroup;
import net.augustus.settings.DoubleValue;

public class FastBreakBlockOption implements SelectableOption {
   private boolean selected;
   private final String name;
   private final Module parentModule;
   private BooleanValue customInAirMultiplierSetting;
   private DoubleValue inAirMultiplierSetting;
   private DoubleValue multiplierSetting;
   private DoubleValue artificialHasteSetting;
   private BooleanValue instantBreakSetting;
   public final FastBreak fastBreak;

   public FastBreakBlockOption(FastBreak fastBreak2, Module module, String str) {
      this.fastBreak = fastBreak2;
      this.selected = true;
      this.name = str;
      this.parentModule = module;
   }

   public void initSettings(String str, StringValue booleansSetting, SettingGroup doubleValue, SettingVisibilityCondition condition) {
      SettingGroup settingGroup;
      if (doubleValue == null) {
         settingGroup = new SettingGroup(this.parentModule, str + this.name);
         settingGroup = settingGroup.visibleWhen(() -> this.isExpertGroupVisible(booleansSetting, condition));
         settingGroup = (SettingGroup)settingGroup.withDisplayName(this.name);
      } else {
         settingGroup = new SettingGroup(this.parentModule, str + this.name, doubleValue);
         settingGroup = settingGroup.visibleWhen(() -> this.isNestedExpertGroupVisible(booleansSetting, condition));
         settingGroup = (SettingGroup)settingGroup.withDisplayName(this.name);
      }

      SettingGroup settingGroup2 = settingGroup;
      this.customInAirMultiplierSetting = (BooleanValue)new BooleanValue(this.parentModule, str + this.name + "CustomInAirMultiplier", false, settingGroup2).withDisplayName("CustomInAirMultiplier");
      DoubleValue doubleSetting = new DoubleValue(this.parentModule, str + this.name + "InAirMultiplier", 0.2, 0.0, 1.0, 2, settingGroup2).named("InAirMultiplier");
      BooleanValue booleanValue2 = this.customInAirMultiplierSetting;
      this.inAirMultiplierSetting = doubleSetting.visibleWhen(() -> booleanValue2.getValue());
      this.multiplierSetting = new DoubleValue(this.parentModule, str + this.name + "Multiplier", 1.0, 1.0, 2.0, 2, settingGroup2).named("Multiplier");
      this.artificialHasteSetting = new DoubleValue(this.parentModule, str + this.name + "ArtificialMiningSpeedEffect", 0.0, 0.0, 3.0, 0, settingGroup2).named("ArtificialMiningSpeedEffect");
      this.instantBreakSetting = (BooleanValue)new BooleanValue(this.parentModule, str + this.name + "Instant", false, settingGroup2).withDisplayName("Instant");
   }

   @Override
   public String getName() {
      return this.name;
   }

   @Override
   public boolean isSelected() {
      return this.selected;
   }

   @Override
   public void setSelected(boolean flag) {
      this.selected = flag;
   }

   private boolean isNestedExpertGroupVisible(StringValue booleansSetting, SettingVisibilityCondition condition) {
      return this.selected && booleansSetting.getSelectedValue().equals("Expert") && condition.isVisible();
   }

   private boolean isExpertGroupVisible(StringValue booleansSetting, SettingVisibilityCondition condition) {
      return this.selected && booleansSetting.getSelectedValue().equals("Expert") && condition.isVisible();
   }

   public static BooleanValue getCustomInAirMultiplierSetting(FastBreakBlockOption fastBreakBlockOption) {
      return fastBreakBlockOption.customInAirMultiplierSetting;
   }

   public static DoubleValue getInAirMultiplierSetting(FastBreakBlockOption fastBreakBlockOption) {
      return fastBreakBlockOption.inAirMultiplierSetting;
   }

   public static DoubleValue getMultiplierSetting(FastBreakBlockOption fastBreakBlockOption) {
      return fastBreakBlockOption.multiplierSetting;
   }

   public static DoubleValue getArtificialHasteSetting(FastBreakBlockOption fastBreakBlockOption) {
      return fastBreakBlockOption.artificialHasteSetting;
   }

   public static BooleanValue getInstantBreakSetting(FastBreakBlockOption fastBreakBlockOption) {
      return fastBreakBlockOption.instantBreakSetting;
   }

   public static boolean isOptionSelected(FastBreakBlockOption fastBreakBlockOption) {
      return fastBreakBlockOption.selected;
   }

}
