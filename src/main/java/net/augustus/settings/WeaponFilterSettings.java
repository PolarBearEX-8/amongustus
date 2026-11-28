package net.augustus.settings;

import net.augustus.settings.options.WeaponFilterOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import net.augustus.modules.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemFishingRod;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;

public class WeaponFilterSettings extends SettingComposite {
   private WeaponFilterOption rodsOption;
   private WeaponFilterOption swordsOption;
   private WeaponFilterOption fistsOption;
   private WeaponFilterOption sticksOption;
   private WeaponFilterOption axesOption;
   private WeaponFilterOption restOption;

   public void setOptionEnabled(String str, boolean flag) {
      switch (str) {
         case "Swords":
            this.swordsOption.setSelected(flag);
            break;
         case "Axes":
            this.axesOption.setSelected(flag);
            break;
         case "Sticks":
            this.sticksOption.setSelected(flag);
            break;
         case "Rods":
            this.rodsOption.setSelected(flag);
            break;
         case "Fists":
            this.fistsOption.setSelected(flag);
            break;
         case "Rest":
            this.restOption.setSelected(flag);
      }
   }

   public WeaponFilterSettings(Module module, SettingGroup doubleValue) {
       super();
      this.initOptions(module, "Weapons", doubleValue);
   }

   public WeaponFilterSettings(Module module) {
       super();
      this.initOptions(module, "Weapons", (SettingGroup)null);
   }

   public WeaponFilterSettings(Module module, String str) {
       super();
      this.initOptions(module, str, (SettingGroup)null);
   }

   public boolean isItemAllowed(ItemStack stack) {
      if (stack == null) {
         return this.fistsOption.isSelected();
      } else {
         Item item = stack.getItem();
         if (item instanceof ItemSword) {
            return this.swordsOption.isSelected();
         } else if (item instanceof ItemAxe) {
            return this.axesOption.isSelected();
         } else if (item.equals(Items.stick)) {
            return this.sticksOption.isSelected();
         } else {
            return item instanceof ItemFishingRod ? this.rodsOption.isSelected() : this.restOption.isSelected();
         }
      }
   }

   public boolean isHeldItemAllowed() {
      return this.isItemAllowed(Minecraft.getMinecraft().thePlayer.getCurrentEquippedItem());
   }

   public WeaponFilterSettings(Module module, String str, SettingGroup doubleValue) {
       super();
      this.initOptions(module, str, doubleValue);
   }

   private void initOptions(Module module, String str, SettingGroup doubleValue) {
      ArrayList list = new ArrayList();
      list.add(this.swordsOption = new WeaponFilterOption("Swords"));
      list.add(this.axesOption = new WeaponFilterOption("Axes"));
      list.add(this.sticksOption = new WeaponFilterOption("Sticks"));
      list.add(this.rodsOption = new WeaponFilterOption("Rods"));
      list.add(this.fistsOption = new WeaponFilterOption("Fists"));
      list.add(this.restOption = new WeaponFilterOption("Rest"));
      WeaponFilterOption[] weaponFilterOptions = (WeaponFilterOption[])list.toArray(new WeaponFilterOption[0]);
      Arrays.sort(weaponFilterOptions, Comparator.comparing(WeaponFilterOption::getName));
      if (doubleValue == null) {
         MultiOptionSetting multiOptionSetting2 = (new MultiOptionSetting(module, str, weaponFilterOptions)).named(str);
         this.registerNamedSetting("", multiOptionSetting2);
      } else {
         MultiOptionSetting multiOptionSetting4 = (new MultiOptionSetting(module, str, weaponFilterOptions, doubleValue)).named(str);
         this.registerNamedSetting("", multiOptionSetting4);
      }
   }


}
