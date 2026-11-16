package cc.baritone.utils;

public class ChannelCodeController {
   public static final int count = 0;
   public static final int count2 = 1;
   public static final int count3 = 2;
   public static final int count4 = 3;
   public static final ChannelCodeController channelCodeController = new ChannelCodeController(1);
   public static final ChannelCodeController channelCodeController2 = new ChannelCodeController(2);
   public static final ChannelCodeController channelCodeController3 = new ChannelCodeController(0);
   public static final ChannelCodeController channelCodeController4 = new ChannelCodeController(3);
   public int count5;

   public static ChannelCodeController resolveChannelCodeController(int count6) {
      switch (count6) {
         case 0:
            return channelCodeController3;
         case 1:
            return channelCodeController;
         case 2:
            return channelCodeController2;
         case 3:
            return channelCodeController4;
         default:
            throw new IllegalArgumentException("Invalid channel code: " + count6);
      }
   }

   public int getCount5() {
      return this.count5;
   }

   @Override
   public boolean equals(Object other) {
      boolean flag = false;
      if (other instanceof ChannelCodeController) {
         flag = ((ChannelCodeController)other).count5 == this.count5;
      }

      return flag;
   }

   public int getCount() {
      return this.count5 == 0 ? 2 : 1;
   }

   private ChannelCodeController(int count6) {
      this.count5 = count6;
      if (count6 < 0 || count6 > 3) {
         throw new IllegalArgumentException("channels");
      }
   }

   @Override
   public int hashCode() {
      return this.count5;
   }
}
