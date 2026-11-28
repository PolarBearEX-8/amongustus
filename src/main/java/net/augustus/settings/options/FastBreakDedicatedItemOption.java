package net.augustus.settings.options;

import net.augustus.settings.MultiOptionSetting;
import net.augustus.settings.SelectableOption;
import net.augustus.settings.SettingVisibilityCondition;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import net.augustus.modules.Module;
import net.augustus.modules.world.FastBreak;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.StringValue;
import net.augustus.settings.SettingGroup;
import net.augustus.settings.DoubleValue;
import net.minecraft.block.Block;

public class FastBreakDedicatedItemOption implements SelectableOption {
   private boolean selected;
   private final String name;
   private final Module parentModule;
   private final HashMap blockOptions;
   private StringValue modeSetting;
   private SettingGroup simpleSettingsGroup;
   private BooleanValue customInAirMultiplierSetting;
   private DoubleValue inAirMultiplierSetting;
   private DoubleValue multiplierSetting;
   private DoubleValue artificialHasteSetting;
   private BooleanValue instantBreakSetting;
   public final FastBreak fastBreak;

   public FastBreakDedicatedItemOption(FastBreak fastBreak2, Module module, String str) {
      this.fastBreak = fastBreak2;
      this.selected = false;
      this.blockOptions = new HashMap();
      this.name = str;
      this.parentModule = module;
   }

   public void initSettings() {
      SettingGroup settingGroup = new SettingGroup(this.parentModule, this.name, true, FastBreak.accessDedicatedItemsSetting(this.fastBreak)).visibleWhen(() -> this.isItemGroupVisible());
      this.modeSetting = (StringValue)((StringValue)new StringValue(this.parentModule, this.name + "Mode", "Simple", new String[]{"Simple", "Expert"}, settingGroup).withDisplayName("Mode")).withVisibility(() -> this.selected);
      this.simpleSettingsGroup = ((SettingGroup)new SettingGroup(this.parentModule, this.name + "SimpleSettings", true, settingGroup).withDisplayName("SimpleSettings")).visibleWhen(() -> this.isSimpleModeVisible());
      this.customInAirMultiplierSetting = (BooleanValue)new BooleanValue(this.parentModule, this.name + "CustomInAirMultiplier", false, this.simpleSettingsGroup).withDisplayName("CustomInAirMultiplier");
      DoubleValue doubleSetting = new DoubleValue(this.parentModule, this.name + "InAirMultiplier", 0.2, 0.0, 1.0, 2, this.simpleSettingsGroup).named("InAirMultiplier");
      BooleanValue booleanValue2 = this.customInAirMultiplierSetting;
      this.inAirMultiplierSetting = doubleSetting.visibleWhen(() -> booleanValue2.getValue());
      this.multiplierSetting = new DoubleValue(this.parentModule, this.name + "Multiplier", 2.0, 1.0, 2.0, 2, this.simpleSettingsGroup).named("Multiplier");
      this.artificialHasteSetting = new DoubleValue(this.parentModule, this.name + "ArtificialMiningSpeedEffect", 0.0, 0.0, 3.0, 0, this.simpleSettingsGroup).named("ArtificialMiningSpeedEffect");
      this.instantBreakSetting = (BooleanValue)new BooleanValue(this.parentModule, this.name + "Instant", false, this.simpleSettingsGroup).withDisplayName("Instant");
      HashMap blockOptions2 = this.blockOptions;
      FastBreak.accessPopulateBlockOptions(this.fastBreak, blockOptions2);
      FastBreakBlockOption[] fastBreakBlockOptions = (FastBreakBlockOption[])this.blockOptions.values().toArray(new FastBreakBlockOption[0]);
      Arrays.sort(fastBreakBlockOptions, Comparator.comparing(fastBreakBlockOption -> fastBreakBlockOption.getName()));
      new MultiOptionSetting(this.parentModule, this.name + "BlocksToFastbreak", fastBreakBlockOptions, settingGroup).named("BlocksToFastbreak").visibleWhen(() -> this.selected);
      ((Collection<FastBreakBlockOption>)this.blockOptions.values()).stream().sorted(Comparator.comparing(fastBreakBlockOption2 -> fastBreakBlockOption2.getName())).forEach(fastBreakBlockOption3 -> this.initBlockOptionSettings(settingGroup, fastBreakBlockOption3));
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

   public boolean hasCustomInAirMultiplier(Block block) {
      return this.modeSetting.getSelectedValue().equals("Simple") ? this.customInAirMultiplierSetting.getValue() : FastBreakBlockOption.getCustomInAirMultiplierSetting(this.getBlockOption(block)).getValue();
   }

   public double getInAirMultiplier(Block block) {
      return this.modeSetting.getSelectedValue().equals("Simple") ? this.inAirMultiplierSetting.getValue() : FastBreakBlockOption.getInAirMultiplierSetting(this.getBlockOption(block)).getValue();
   }

   public double getSpeedMultiplier(Block block) {
      return this.modeSetting.getSelectedValue().equals("Simple") ? this.multiplierSetting.getValue() : FastBreakBlockOption.getMultiplierSetting(this.getBlockOption(block)).getValue();
   }

   public double getArtificialHasteEffect(Block block) {
      return this.modeSetting.getSelectedValue().equals("Simple") ? this.artificialHasteSetting.getValue() : FastBreakBlockOption.getArtificialHasteSetting(this.getBlockOption(block)).getValue();
   }

   public boolean isInstantBreak(Block block) {
      return this.modeSetting.getSelectedValue().equals("Simple") ? this.instantBreakSetting.getValue() : FastBreakBlockOption.getInstantBreakSetting(this.getBlockOption(block)).getValue();
   }

   public boolean isBlockEnabled(Block block) {
      FastBreakBlockOption blockOption = this.getBlockOption(block);
      return blockOption != null && FastBreakBlockOption.isOptionSelected(blockOption);
   }

   private FastBreakBlockOption getBlockOption(Block block) {
      return (FastBreakBlockOption)this.blockOptions.get(block);
   }

   private void initBlockOptionSettings(SettingGroup doubleValue, FastBreakBlockOption fastBreakBlockOption) {
      String name2 = this.name;
      StringValue modeSetting2 = this.modeSetting;
      SettingVisibilityCondition condition = () -> {
         return this.selected && this.modeSetting.isVisible();
      };
      fastBreakBlockOption.initSettings(name2, modeSetting2, doubleValue, condition);
   }

   private boolean isSimpleModeVisible() {
      return this.modeSetting.getSelectedValue().equals("Simple") && this.selected;
   }

   private boolean isItemGroupVisible() {
      return this.selected && FastBreak.accessDedicatedItemHandlingSetting(this.fastBreak).getValue();
   }

   public static boolean isOptionSelected(FastBreakDedicatedItemOption option) {
      return option.selected;
   }

}
