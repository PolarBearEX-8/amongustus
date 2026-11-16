package client.util;

import client.setting.OptionManager;
import java.util.Comparator;

public final class CommonComparatorAdapter implements Comparator {
   public int computeCount(OptionManager optionManager, OptionManager optionManager2) {
      return optionManager.getLabelText().compareToIgnoreCase(optionManager2.getLabelText());
   }

   @Override
   public int compare(Object entry, Object entry2) {
      return this.computeCount((OptionManager)entry, (OptionManager)entry2);
   }
}
