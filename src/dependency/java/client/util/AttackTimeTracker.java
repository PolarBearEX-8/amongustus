package client.util;

import client.setting.SettingContainer;

public class AttackTimeTracker {
   public int count;
   public long timestamp = 0L;
   public long timestamp2 = 20L;
   public long timestamp3 = 0L;

   public Pair getPair(int count2, double offset, double offset2, boolean flag, boolean flag2, double offset3) {
      if (count2 <= 0) {
         return new Pair(0, false);
      } else {
         int count3 = 1000 / count2;
         int count4 = 0;
         int count5 = count3 / 2;

         while (this.timestamp >= this.timestamp2) {
            if (flag) {
               int count6 = (int)(this.timestamp - this.timestamp2);
               if (count6 > count5) {
                  double offset4 = (double)count6;
                  double offset5 = (double)count6;
                  double offset6 = 7.0;
                  double offset7 = 6.0;
                  count3 = (int)(offset4 - offset5 / SettingContainer.settingProvider.getDouble4(offset7, offset6));
                  this.count++;
               } else if (flag2 && this.count % 4 == 0) {
                  count3 = (int)((double)count3 * offset3);
               }
            }

            int count7 = (int)((double)count3 * SettingContainer.settingProvider.getDouble4(offset, offset2));
            count4++;
            this.timestamp3 = this.timestamp2;
            this.timestamp3 += (long)count7;
            this.timestamp2 += (long)count3;
         }

         boolean flag3 = this.timestamp3 > this.timestamp;
         this.timestamp += 50L;
         return new Pair(count4, flag3);
      }
   }

   public AttackTimeTracker() {
      this.count = 0;
   }
}
