package augustus.anticheat.checks;

import client.setting.BridgeOptionType;
import client.setting.DirectOptionType;
import client.util.CompositeDoubleEntry;
import client.util.DecimalFormatter;
import client.util.StringEntryBridge;
import java.util.List;

public class Intave14ACDoubleClickCheck extends StringEntryBridge {
   public final CompositeDoubleEntry compositeDoubleEntry = new CompositeDoubleEntry(
      300, "clicking too fast without double clicks", DirectOptionType.AUTOCLICKER
   );

   @Override
   public void applyList(List list) {
      double offset = this.computeValue4(list);
      if (offset > 15.0 && this.count == 0) {
         double offset2 = 1.0;
         this.compositeDoubleEntry.applyDouble(offset2);
         CompositeDoubleEntry compositeDoubleEntry2 = this.compositeDoubleEntry;
         BridgeOptionType bridgeOptionType = BridgeOptionType.NONE;
         String text = DecimalFormatter.formatDecimal(offset, 2) + "";
         BridgeOptionType bridgeOptionType2 = bridgeOptionType;
         compositeDoubleEntry2.applyBridgeOptionType(bridgeOptionType2, text);
      }
   }

   public Intave14ACDoubleClickCheck(String text, AntiCheatManager antiCheatManager) {
      super(text, antiCheatManager, 60);
   }
}
