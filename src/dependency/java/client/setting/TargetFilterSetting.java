package client.setting;

import client.event.SupplementalListener;
import client.module.Module;
import client.util.BooleanSupplier;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFishingRod;
import net.minecraft.item.ItemGoldenAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.Items;

public class TargetFilterSetting extends Setting {
   public AuxiliaryNamedOptionAdapter auxiliaryNamedOptionAdapter3;
   public MultiSelectSetting multiSelectSetting;
   public static int count10 = 0;
   public AuxiliaryNamedOptionAdapter auxiliaryNamedOptionAdapter4;
   public AuxiliaryNamedOptionAdapter auxiliaryNamedOptionAdapter5;
   public AuxiliaryNamedOptionAdapter auxiliaryNamedOptionAdapter;
   public AuxiliaryNamedOptionAdapter auxiliaryNamedOptionAdapter6;
   public AuxiliaryNamedOptionAdapter auxiliaryNamedOptionAdapter2;

   @Override
   public JsonObject resolveJsonObject(SupplementalListener supplementalListener) {
      return null;
   }

   @Override
   public void updateState() {
      this.multiSelectSetting.updateState();
   }

   @Override
   public Setting getSetting(String text) {
      return this.resolveTargetFilterSetting(text);
   }

   private void applyModule(Module module, String text, SettingGroup settingGroup) {
      ArrayList list = new ArrayList();
      list.add(this.auxiliaryNamedOptionAdapter = new AuxiliaryNamedOptionAdapter(this, "Swords"));
      list.add(this.auxiliaryNamedOptionAdapter5 = new AuxiliaryNamedOptionAdapter(this, "Axes"));
      list.add(this.auxiliaryNamedOptionAdapter6 = new AuxiliaryNamedOptionAdapter(this, "Sticks"));
      list.add(this.auxiliaryNamedOptionAdapter2 = new AuxiliaryNamedOptionAdapter(this, "Rods"));
      list.add(this.auxiliaryNamedOptionAdapter3 = new AuxiliaryNamedOptionAdapter(this, "Fists"));
      list.add(this.auxiliaryNamedOptionAdapter4 = new AuxiliaryNamedOptionAdapter(this, "Rest"));
      AuxiliaryNamedOptionAdapter[] auxiliaryNamedOptionAdapterArray = (AuxiliaryNamedOptionAdapter[])list.toArray(new AuxiliaryNamedOptionAdapter[0]);
      Arrays.sort(auxiliaryNamedOptionAdapterArray, Comparator.comparing(AuxiliaryNamedOptionAdapter::getText));
      if (settingGroup == null) {
         MultiSelectSetting multiSelectSetting2 = (new MultiSelectSetting(module, module.getString() + text, auxiliaryNamedOptionAdapterArray)).getMultiSelectSetting(text);
         BooleanSupplier booleanSupplier = this::isVisible;
         this.multiSelectSetting = multiSelectSetting2.getMultiSelectSetting(booleanSupplier);
      } else {
         MultiSelectSetting multiSelectSetting3 = (new MultiSelectSetting(module, module.getString() + text, auxiliaryNamedOptionAdapterArray, settingGroup)).getMultiSelectSetting(text);
         BooleanSupplier booleanSupplier2 = this::isVisible;
         this.multiSelectSetting = multiSelectSetting3.getMultiSelectSetting(booleanSupplier2);
      }
   }

   public TargetFilterSetting resolveTargetFilterSetting(String text) {
      super.setText4(text);
      this.multiSelectSetting.setText4(text);
      return this;
   }

   public void applyString3(String text, boolean flag) {
      switch (text) {
         case "Swords":
            this.auxiliaryNamedOptionAdapter.setActive(flag);
            break;
         case "Axes":
            this.auxiliaryNamedOptionAdapter5.setActive(flag);
            break;
         case "Sticks":
            this.auxiliaryNamedOptionAdapter6.setActive(flag);
            break;
         case "Rods":
            this.auxiliaryNamedOptionAdapter2.setActive(flag);
            break;
         case "Fists":
            this.auxiliaryNamedOptionAdapter3.setActive(flag);
            break;
         case "Rest":
            this.auxiliaryNamedOptionAdapter4.setActive(flag);
      }
   }

   @Override
   public void updateSupplementalListener(SupplementalListener supplementalListener, JsonObject jsonObject) {
   }

   public boolean canExecute() {
      ItemStack stack = Minecraft.getMinecraft().thePlayer.getHeldItem();
      ItemStack stack2 = stack;
      return this.checkItemStack(stack2);
   }

   @Override
   public void updateState3() {
   }

   public boolean checkItemStack(ItemStack stack) {
      if (stack == null) {
         return this.auxiliaryNamedOptionAdapter3.isSelected();
      } else {
         Item item = stack.getItem();
         if (item instanceof ItemSword) {
            return this.auxiliaryNamedOptionAdapter.isSelected();
         } else if (item instanceof ItemGoldenAxe) {
            return this.auxiliaryNamedOptionAdapter5.isSelected();
         } else if (item.equals(Items.stick)) {
            return this.auxiliaryNamedOptionAdapter6.isSelected();
         } else {
            return item instanceof ItemFishingRod ? this.auxiliaryNamedOptionAdapter2.isSelected() : this.auxiliaryNamedOptionAdapter4.isSelected();
         }
      }
   }

   @Override
   public int getCount10() {
      return 0;
   }

   public TargetFilterSetting(Module module, SettingGroup settingGroup) {
      super(module, "WeaponValue" + count10);
      count10++;
      settingGroup.getItemList3().add(this);
      String text = "Weapons";
      this.applyModule(module, text, settingGroup);
   }

   public TargetFilterSetting(Module module, String text, SettingGroup settingGroup) {
      super(module, "WeaponValue" + count10 + text);
      count10++;
      settingGroup.getItemList3().add(this);
      this.applyModule(module, text, settingGroup);
   }

   public TargetFilterSetting(Module module, String text) {
      super(module, "WeaponValue" + count10 + text);
      count10++;
      module.getSettings().add(this);
      this.applyModule(module, text, (SettingGroup)null);
   }

   public TargetFilterSetting(Module module) {
      super(module, "WeaponValue" + count10);
      count10++;
      module.getSettings().add(this);
      String text = "Weapons";
      this.applyModule(module, text, (SettingGroup)null);
   }
}
