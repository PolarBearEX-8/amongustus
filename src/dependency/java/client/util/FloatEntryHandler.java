package client.util;

import client.event.ModularListener;
import client.setting.FactoryOptionType;

public class FloatEntryHandler extends DynamicFloatEntry implements ModularListener {
   public FactoryOptionType factoryOptionType2 = null;

   @Override
   public FactoryOptionType getFactoryOptionType2() {
      return this.factoryOptionType2;
   }

   public FloatEntryHandler(int count, int count2, int count3, FactoryOptionType factoryOptionType) {
      super(count, count2, count3, factoryOptionType);
      this.factoryOptionType2 = factoryOptionType;
   }
}
