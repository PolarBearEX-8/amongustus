package client.util;

import client.event.ModularListener;
import client.gui.ValueSliderControl;
import client.setting.FactoryOptionType;

public class ListenerAdapter14 extends ValueSliderControl implements ModularListener {
   public FactoryOptionType factoryOptionType2 = null;

   @Override
   public FactoryOptionType getFactoryOptionType2() {
      return this.factoryOptionType2;
   }

   public ListenerAdapter14(int count, int count2, int count3, FactoryOptionType factoryOptionType, String text) {
      super(count, count2, count3, factoryOptionType, text);
      this.factoryOptionType2 = factoryOptionType;
   }
}
