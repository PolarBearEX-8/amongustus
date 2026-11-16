package client.gui;

import client.setting.FactoryOptionType;

public class ValueSliderControl extends AuxiliaryTexturesGuiIconsPngManagerAdapter {
   public final FactoryOptionType factoryOptionType;

   public FactoryOptionType getFactoryOptionType() {
      return this.factoryOptionType;
   }

   public ValueSliderControl(int count, int count2, int count3, FactoryOptionType factoryOptionType2, String text) {
      super(count, count2, count3, 150, 20, text);
      this.factoryOptionType = factoryOptionType2;
   }

   public ValueSliderControl(int count, int count2, int count3, int count4, int count5, String text) {
      super(count, count2, count3, count4, count5, text);
      this.factoryOptionType = null;
   }

   public ValueSliderControl(int count, int count2, int count3, String text) {
      this(count, count2, count3, (FactoryOptionType)null, text);
   }
}
