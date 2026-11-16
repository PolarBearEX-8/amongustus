package augustus.modes.world;

import augustus.modules.world.FastBreak;
import client.module.Module;
import client.setting.BooleanSetting;
import client.setting.ModeSetting;
import client.setting.MultiSelectSetting;
import client.setting.NamedOption;
import client.setting.NumberSetting;
import client.setting.SettingGroup;
import client.util.BooleanSupplier;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import net.minecraft.block.Block;

public class FastBreakBlockMode implements NamedOption {
   public boolean enabled;
   public final String labelText;
   public final Module module;
   public final HashMap hashMap;
   public ModeSetting modeSetting;
   public SettingGroup settingGroup;
   public BooleanSetting booleanSetting2;
   public NumberSetting numberSetting;
   public NumberSetting numberSetting3;
   public NumberSetting numberSetting2;
   public BooleanSetting booleanSetting;
   public final FastBreak fastBreak;

   private boolean isEnabledAsFlag() {
      return this.enabled;
   }

   @Override
   public void setActive(boolean active) {
      this.enabled = active;
   }

   public double getCoordinate(Block block) {
      return this.modeSetting.getSelected().equals("Simple") ? this.numberSetting.getValue() : FastBreakToolEntry.getNumberSetting2(this.get(block)).getValue();
   }

   private boolean isActive() {
      return this.modeSetting.getSelected().equals("Simple") && this.enabled;
   }

   private FastBreakToolEntry get(Block block) {
      return (FastBreakToolEntry)this.hashMap.get(block);
   }

   public double getCoordinate2(Block block) {
      return this.modeSetting.getSelected().equals("Simple") ? this.numberSetting3.getValue() : FastBreakToolEntry.getNumberSetting3(this.get(block)).getValue();
   }

   private boolean isEnabled() {
      return this.enabled;
   }

   @Override
   public String getText() {
      return this.labelText;
   }

   private boolean isActiveAsFlag() {
      return this.enabled && this.modeSetting.isVisible();
   }

   private void applySettingGroup(SettingGroup settingGroup2, FastBreakToolEntry fastBreakToolEntry) {
      String labelText2 = this.labelText;
      ModeSetting modeSetting2 = this.modeSetting;
      BooleanSupplier booleanSupplier = this::isActiveAsFlag;
      ModeSetting setting = modeSetting2;
      String text = labelText2;
      fastBreakToolEntry.applyModeSetting(text, setting, settingGroup2, booleanSupplier);
   }

   public void updateState() {
      SettingGroup settingGroup2 = new SettingGroup(this.module, this.labelText, true, FastBreak.getSettingGroup(this.fastBreak));
      BooleanSupplier booleanSupplier = this::isActive2;
      BooleanSupplier booleanSupplier2 = booleanSupplier;
      SettingGroup settingGroup3 = settingGroup2.getSettingGroup(booleanSupplier2);
      ModeSetting setting = new ModeSetting(this.module, this.labelText + "Mode", "Simple", new String[]{"Simple", "Expert"}, settingGroup3);
      String text = "Mode";
      ModeSetting modeSetting2 = setting.getModeSetting(text);
      BooleanSupplier booleanSupplier3 = this::isEnabledAsFlag;
      this.modeSetting = modeSetting2.getModeSetting(booleanSupplier3);
      SettingGroup settingGroup4 = new SettingGroup(this.module, this.labelText + "SimpleSettings", true, settingGroup3);
      String text2 = "SimpleSettings";
      SettingGroup setting2 = (SettingGroup)settingGroup4.getSetting(text2);
      BooleanSupplier booleanSupplier4 = this::isActive;
      BooleanSupplier booleanSupplier5 = booleanSupplier4;
      this.settingGroup = setting2.getSettingGroup(booleanSupplier5);
      BooleanSetting setting3 = new BooleanSetting(this.module, this.labelText + "CustomInAirMultiplier", false, this.settingGroup);
      String text3 = "CustomInAirMultiplier";
      this.booleanSetting2 = (BooleanSetting)setting3.getSetting(text3);
      NumberSetting setting4 = new NumberSetting(this.module, this.labelText + "InAirMultiplier", 0.2, 0.0, 1.0, 2, this.settingGroup);
      String text4 = "InAirMultiplier";
      NumberSetting numberSetting4 = setting4.getNumberSetting(text4);
      BooleanSupplier booleanSupplier6 = this.booleanSetting2::isEnabled;
      this.numberSetting = numberSetting4.getNumberSetting(booleanSupplier6);
      NumberSetting setting5 = new NumberSetting(this.module, this.labelText + "Multiplier", 2.0, 1.0, 2.0, 2, this.settingGroup);
      String text5 = "Multiplier";
      this.numberSetting3 = setting5.getNumberSetting(text5);
      NumberSetting setting6 = new NumberSetting(this.module, this.labelText + "ArtificialMiningSpeedEffect", 0.0, 0.0, 3.0, 0, this.settingGroup);
      String text6 = "ArtificialMiningSpeedEffect";
      this.numberSetting2 = setting6.getNumberSetting(text6);
      BooleanSetting setting7 = new BooleanSetting(this.module, this.labelText + "Instant", false, this.settingGroup);
      String text7 = "Instant";
      this.booleanSetting = (BooleanSetting)setting7.getSetting(text7);
      HashMap hashMap2 = this.hashMap;
      FastBreak.applyFastBreak(this.fastBreak, hashMap2);
      FastBreakToolEntry[] fastBreakToolEntryArray = ((FastBreakToolEntry[])this.hashMap.values().toArray(new FastBreakToolEntry[0]));
      Arrays.sort(fastBreakToolEntryArray, Comparator.comparing(FastBreakToolEntry::getText));
      MultiSelectSetting setting8 = new MultiSelectSetting(this.module, this.labelText + "BlocksToFastbreak", fastBreakToolEntryArray, settingGroup3);
      String text8 = "BlocksToFastbreak";
      MultiSelectSetting multiSelectSetting = setting8.getMultiSelectSetting(text8);
      BooleanSupplier booleanSupplier7 = this::isEnabled;
      multiSelectSetting.getMultiSelectSetting(booleanSupplier7);
      this.hashMap.values().forEach(entry -> this.applySettingGroup(settingGroup3, (FastBreakToolEntry)entry));
   }

   public boolean checkBlock(Block block) {
      return this.modeSetting.getSelected().equals("Simple")
         ? this.booleanSetting2.isEnabled()
         : FastBreakToolEntry.getBooleanSetting2(this.get(block)).isEnabled();
   }

   public double getCoordinate3(Block block) {
      return this.modeSetting.getSelected().equals("Simple") ? this.numberSetting2.getValue() : FastBreakToolEntry.getNumberSetting(this.get(block)).getValue();
   }

   private boolean isActive2() {
      return this.enabled && FastBreak.getBooleanSetting(this.fastBreak).isEnabled();
   }

   public boolean checkBlock2(Block block) {
      return this.modeSetting.getSelected().equals("Simple")
         ? this.booleanSetting.isEnabled()
         : FastBreakToolEntry.getBooleanSetting(this.get(block)).isEnabled();
   }

   @Override
   public boolean isSelected() {
      return this.enabled;
   }

   public FastBreakBlockMode(FastBreak fastBreak2, Module module2, String text) {
      this.fastBreak = fastBreak2;
      this.enabled = false;
      this.hashMap = new HashMap();
      this.labelText = text;
      this.module = module2;
   }
}
