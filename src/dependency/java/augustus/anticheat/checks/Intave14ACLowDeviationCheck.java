package augustus.anticheat.checks;

import client.setting.BridgeOptionType;
import client.setting.DirectOptionType;
import client.util.CompositeDoubleEntry;
import client.util.StringEntryBridge;
import java.util.List;

public class Intave14ACLowDeviationCheck extends StringEntryBridge {
   public final CompositeDoubleEntry compositeDoubleEntry = new CompositeDoubleEntry(310, "Clicking with low deviation", DirectOptionType.AUTOCLICKER);

   @Override
   public void applyList(List list) {
      double offset = this.computeValue(list);
      if (offset < 0.45) {
         double offset2 = 1.0;
         this.compositeDoubleEntry.applyDouble(offset2);
         if (this.compositeDoubleEntry.getValue2() >= 3.0) {
            String text = offset + "";
            BridgeOptionType bridgeOptionType = BridgeOptionType.NONE;
            this.compositeDoubleEntry.applyBridgeOptionType(bridgeOptionType, text);
         }
      } else {
         double offset3 = -0.5;
         this.compositeDoubleEntry.applyDouble(offset3);
         if (this.compositeDoubleEntry.getValue2() < 0.0) {
            CompositeDoubleEntry compositeDoubleEntry2 = this.compositeDoubleEntry;
            double offset4 = -this.compositeDoubleEntry.getValue2();
            compositeDoubleEntry2.applyDouble(offset4);
         }
      }
   }

   public Intave14ACLowDeviationCheck(String text, AntiCheatManager antiCheatManager) {
      super(text, antiCheatManager, 100);
   }
}
