package client.util;

public class AngleFormatter {
   public int count;
   public int count2;
   public boolean enabled2;
   public boolean enabled3;
   public static AngleFormatter angleFormatter = new AngleFormatter();
   public int count3;
   public boolean enabled4;
   public boolean enabled5 = true;
   public int count4;
   public boolean enabled;

   public static AngleFormatter getAngleFormatter() {
      return angleFormatter;
   }

   private AngleFormatter() {
      this.enabled2 = true;
      this.enabled3 = true;
      this.enabled = false;
      this.count2 = 10;
      this.count = 5;
      this.count4 = 50;
      this.count3 = RecordNode20.count;
   }
}
