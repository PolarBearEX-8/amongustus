package augustus.modes.world;

import augustus.modules.world.FastBreak;
import client.module.Module;
import client.setting.BooleanSetting;
import client.setting.ModeSetting;
import client.setting.NamedOption;
import client.setting.NumberSetting;
import client.setting.SettingGroup;
import client.util.BooleanSupplier;

public class FastBreakToolEntry implements NamedOption {
   public boolean enabled;
   public final String labelText;
   public final Module module;
   public BooleanSetting booleanSetting2;
   public NumberSetting numberSetting;
   public NumberSetting numberSetting3;
   public NumberSetting numberSetting2;
   public BooleanSetting booleanSetting;
   public final FastBreak fastBreak;

   private boolean matchesModeSettingByModeSetting(ModeSetting setting, BooleanSupplier booleanSupplier) {
      return this.enabled && setting.getSelected().equals("Expert") && booleanSupplier.getAsBoolean();
   }

   @Override
   public void setActive(boolean active) {
      this.enabled = active;
   }

   @Override
   public String getText() {
      return this.labelText;
   }

   public static NumberSetting getNumberSetting(FastBreakToolEntry fastBreakToolEntry) {
      return fastBreakToolEntry.numberSetting2;
   }

   public static NumberSetting getNumberSetting2(FastBreakToolEntry fastBreakToolEntry) {
      return fastBreakToolEntry.numberSetting;
   }

   public void applyModeSetting(String text, ModeSetting setting, SettingGroup settingGroup, BooleanSupplier booleanSupplier) {
      SettingGroup settingGroup2;
      if (settingGroup == null) {
         settingGroup2 = new SettingGroup(this.module, text + this.labelText);
         BooleanSupplier booleanSupplier2 = () -> this.matchesModeSetting3(setting, booleanSupplier);
         BooleanSupplier booleanSupplier3 = booleanSupplier2;
         settingGroup2 = settingGroup2.getSettingGroup(booleanSupplier3);
         String labelText2 = this.labelText;
         settingGroup2 = (SettingGroup)settingGroup2.getSetting(labelText2);
      } else {
         settingGroup2 = new SettingGroup(this.module, text + this.labelText, settingGroup);
         BooleanSupplier booleanSupplier4 = () -> this.matchesModeSettingByModeSetting(setting, booleanSupplier);
         BooleanSupplier booleanSupplier5 = booleanSupplier4;
         settingGroup2 = settingGroup2.getSettingGroup(booleanSupplier5);
         String labelText3 = this.labelText;
         settingGroup2 = (SettingGroup)settingGroup2.getSetting(labelText3);
      }

      SettingGroup settingGroup3 = settingGroup2;
      BooleanSetting setting2 = new BooleanSetting(this.module, text + this.labelText + "CustomInAirMultiplier", false, settingGroup3);
      String text2 = "CustomInAirMultiplier";
      this.booleanSetting2 = (BooleanSetting)setting2.getSetting(text2);
      NumberSetting setting3 = new NumberSetting(this.module, text + this.labelText + "InAirMultiplier", 0.2, 0.0, 1.0, 2, settingGroup3);
      String text3 = "InAirMultiplier";
      NumberSetting numberSetting4 = setting3.getNumberSetting(text3);
      BooleanSupplier booleanSupplier6 = this.booleanSetting2::isEnabled;
      this.numberSetting = numberSetting4.getNumberSetting(booleanSupplier6);
      NumberSetting setting4 = new NumberSetting(this.module, text + this.labelText + "Multiplier", 1.0, 1.0, 2.0, 2, settingGroup3);
      String text4 = "Multiplier";
      this.numberSetting3 = setting4.getNumberSetting(text4);
      NumberSetting setting5 = new NumberSetting(this.module, text + this.labelText + "ArtificialMiningSpeedEffect", 0.0, 0.0, 3.0, 0, settingGroup3);
      String text5 = "ArtificialMiningSpeedEffect";
      this.numberSetting2 = setting5.getNumberSetting(text5);
      BooleanSetting setting6 = new BooleanSetting(this.module, text + this.labelText + "Instant", false, settingGroup3);
      String text6 = "Instant";
      this.booleanSetting = (BooleanSetting)setting6.getSetting(text6);
   }

   public static BooleanSetting getBooleanSetting(FastBreakToolEntry fastBreakToolEntry) {
      return fastBreakToolEntry.booleanSetting;
   }

   private boolean matchesModeSetting3(ModeSetting setting, BooleanSupplier booleanSupplier) {
      return this.enabled && setting.getSelected().equals("Expert") && booleanSupplier.getAsBoolean();
   }

   public static NumberSetting getNumberSetting3(FastBreakToolEntry fastBreakToolEntry) {
      return fastBreakToolEntry.numberSetting3;
   }

   public static BooleanSetting getBooleanSetting2(FastBreakToolEntry fastBreakToolEntry) {
      return fastBreakToolEntry.booleanSetting2;
   }

   @Override
   public boolean isSelected() {
      return this.enabled;
   }

   public FastBreakToolEntry(FastBreak fastBreak2, Module module2, String text) {
      this.fastBreak = fastBreak2;
      this.enabled = true;
      this.labelText = text;
      this.module = module2;
   }
}
