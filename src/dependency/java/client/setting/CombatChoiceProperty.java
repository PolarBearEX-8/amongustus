package client.setting;

import client.event.SupplementalListener;
import client.module.Module;
import client.util.PlayerUtil;
import com.google.gson.JsonObject;

public class CombatChoiceProperty extends ExtendedSettingAdapter {
   public ActionSetting actionSetting;
   public NumberSetting numberSetting;
   public Exception exception;

   public CombatChoiceProperty resolveCombatChoiceProperty(double offset, double offset2) {
      this.actionSetting.applyDoubleByDouble(offset);
      this.actionSetting.applyDouble(offset2);
      return this;
   }

   public CombatChoiceProperty resolveCombatChoiceProperty(String text) {
      this.settingNoisegroup.getSetting(text);
      this.setText4(text);
      if (this.actionSetting != null) {
         this.actionSetting.getActionSetting(text);
      }

      return this;
   }

   @Override
   public JsonObject resolveJsonObject(SupplementalListener supplementalListener) {
      return null;
   }

   @Override
   public void updateState() {
   }

   @Override
   public Setting getSetting(String text) {
      return this.resolveCombatChoiceProperty(text);
   }

   public CombatChoiceProperty resolveCombatChoiceProperty(double posX, double posX2, double posY, double posZ, int count) {
      ActionSetting setting = new ActionSetting(this.getModule(), this.getString() + "_interval", posY, posZ, posX, posX2, count, this.settingNoisegroup);
      String text = this.getText4() + " ";
      String text2 = text;
      this.actionSetting = setting.getActionSetting(text2);
      return this;
   }

   @Override
   public void updateSupplementalListener(SupplementalListener supplementalListener, JsonObject jsonObject) {
   }

   @Override
   public void updateState3() {
   }

   public void applyIndexByIndex(int count) {
      NumberSetting setting = new NumberSetting(this.getModule(), this.getString() + "_sharpness", (double)count, 0.5, 10.0, 0, this.settingNoisegroup);
      String text = "Sharpness";
      this.numberSetting = setting.getNumberSetting(text);
   }

   @Override
   public double getValue() {
      if (this.actionSetting == null) {
         this.exception.printStackTrace();
         return 0.0;
      } else {
         double offset = 0.0;
         double offset2 = 0.0;

         for (InternalSettingAdapter internalSettingAdapter : (Iterable<InternalSettingAdapter>) (Iterable) (this.arrayList)) {
            double valueAsDouble = internalSettingAdapter.getValueAsDouble();
            if (valueAsDouble != 0.0) {
               float factor = internalSettingAdapter.getFactor3();
               offset += internalSettingAdapter.computeValue(factor) * valueAsDouble;
               offset2 += valueAsDouble;
            }
         }

         if (offset2 == 0.0) {
            return 0.0;
         } else {
            double offset3 = offset / offset2 * 0.5 + 0.5;
            if (this.numberSetting != null) {
               offset3 = Math.pow(offset3, this.numberSetting.getDouble2(true));
            }

            double doubleItem = PlayerUtil.getDouble(this.actionSetting.getDouble5(true), this.actionSetting.getDouble4(true), offset3);
            if (this.enabled3) {
               doubleItem /= this.actionSetting.getValue();
            }

            return doubleItem;
         }
      }
   }

   public CombatChoiceProperty(Module module, String text, SettingGroup settingGroup, boolean flag) {
      super(module, text, settingGroup, flag);
      this.enabled3 = flag;
      this.exception = new Exception();
   }
}
