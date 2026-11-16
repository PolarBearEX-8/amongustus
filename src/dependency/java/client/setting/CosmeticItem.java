package client.setting;

import client.util.AdvancedClientManager;
import client.util.CosmeticsItemData;

public class CosmeticItem {
   public final ColorSetting[] colorSettingArray;
   public final CosmeticsItemData cosmeticsItemData;
   public final BooleanSetting booleanSetting;
   public final NumberSetting numberSetting;
   public final NumberSetting numberSetting2;
   public int[] countArray;
   public boolean[] enabledArray;
   public boolean[] enabledArray2;
   public double[] valueArray;
   public double[] valueArray2;
   public boolean enabled = false;
   public float factor = 1.0F;
   public float factor2 = 0.0F;

   public double getDouble() {
      return this.numberSetting2.getDouble2(true);
   }

   public void processAction(double offset) {
      this.numberSetting2.applyDouble(offset);
   }

   public CosmeticsItemData getCustomStringEntry() {
      return this.cosmeticsItemData;
   }

   public BooleanSetting getBooleanSetting() {
      return this.booleanSetting;
   }

   public void processDouble(double offset) {
      this.numberSetting.applyDouble(offset);
   }

   public double getDouble2() {
      return this.numberSetting.getDouble2(true);
   }

   public boolean canExecute() {
      boolean flag = false;

      for (int index = 0; index < this.colorSettingArray.length; index++) {
         ColorSetting setting = this.colorSettingArray[index];
         int intItem = setting.getInt12(0);
         boolean toggled = setting.isToggled();
         boolean flag2 = setting.checkCondition();
         double doubleItem = setting.getDouble3();
         double doubleItem2 = setting.getDouble();
         int count = this.countArray[index];
         boolean flag3 = this.enabledArray[index];
         boolean flag4 = this.enabledArray2[index];
         double offset = this.valueArray[index];
         double offset2 = this.valueArray2[index];
         boolean flag5 = toggled && flag3 || flag2 && flag4;
         if (intItem != count && !flag5 || toggled != flag3 || flag2 != flag4 || doubleItem != offset || doubleItem2 != offset2) {
            flag = true;
         }

         this.countArray[index] = intItem;
         this.enabledArray[index] = toggled;
         this.enabledArray2[index] = flag2;
         this.valueArray[index] = doubleItem;
         this.valueArray2[index] = doubleItem2;
      }

      boolean enabled2 = this.getBooleanSetting().isEnabled();
      float doubleItem3 = (float)this.getDouble2();
      float doubleItem4 = (float)this.getDouble();
      if (enabled2 != this.enabled || doubleItem3 != this.factor || doubleItem4 != this.factor2) {
         flag = true;
      }

      this.enabled = enabled2;
      this.factor = doubleItem3;
      this.factor2 = doubleItem4;
      return flag;
   }

   public ColorSetting[] getColorSettingArray() {
      return this.colorSettingArray;
   }

   public CosmeticItem(CosmeticsItemData cosmeticsItemData2, BooleanSetting setting, NumberSetting setting2, NumberSetting setting3, ColorSetting[] settingArray, AdvancedClientManager advancedClientManager) {
      this(cosmeticsItemData2, setting, setting2, setting3, settingArray);
   }

   private CosmeticItem(CosmeticsItemData cosmeticsItemData2, BooleanSetting setting, NumberSetting setting2, NumberSetting setting3, ColorSetting[] settingArray) {
      this.cosmeticsItemData = cosmeticsItemData2;
      this.booleanSetting = setting;
      this.numberSetting = setting2;
      this.numberSetting2 = setting3;
      this.colorSettingArray = settingArray;
      this.countArray = new int[settingArray.length];
      this.enabledArray = new boolean[settingArray.length];
      this.enabledArray2 = new boolean[settingArray.length];
      this.valueArray = new double[settingArray.length];
      this.valueArray2 = new double[settingArray.length];
   }
}
