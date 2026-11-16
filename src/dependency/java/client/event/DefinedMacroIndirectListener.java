package client.event;

import client.util.AimSorterAimValidatorImpl;
import client.util.BuildManager;
import client.util.PlusMinusType;
import client.util.PreUpdateObserverTargetObserver;
import java.util.Map;

public class DefinedMacroIndirectListener implements IndirectListener {
   public Map map = null;

   @Override
   public PreUpdateObserver get(String text) {
      String text2 = text;
      String text3 = "defined_";
      if (text.startsWith(text3)) {
         String text4 = text.substring(text3.length());
         return this.map.containsKey(text4)
            ? new PreUpdateObserverTargetObserver(PlusMinusType.TRUE, (PreUpdateObserver[])null)
            : new PreUpdateObserverTargetObserver(PlusMinusType.FALSE, (PreUpdateObserver[])null);
      } else {
         while (this.map.containsKey(text2)) {
            String text5 = (String)this.map.get(text2);
            if (text5 == null || text5.equals(text2)) {
               break;
            }

            text2 = text5;
         }

         int count = BuildManager.computeCount(text2, Integer.MIN_VALUE);
         if (count == Integer.MIN_VALUE) {
            BuildManager.applyText3("Unknown macro value: " + text2);
            return new AimSorterAimValidatorImpl(0.0F);
         } else {
            return new AimSorterAimValidatorImpl((float)count);
         }
      }
   }

   public DefinedMacroIndirectListener(Map map2) {
      this.map = map2;
   }
}
