package client.util;

import client.setting.MovementConfigProperty;

public class FloatEntryBridge extends PrimaryFloatEntry {
   public int count2;

   public void setCount2(int count) {
      this.count2 = count;
   }

   public int getCount2() {
      return this.count2;
   }

   public FloatEntryBridge(int count) {
      super(MovementConfigProperty.FREE);
      this.count2 = count;
   }
}
