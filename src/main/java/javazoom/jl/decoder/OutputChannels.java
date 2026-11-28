package javazoom.jl.decoder;


public class OutputChannels {
   public static final int BOTH_CHANNELS = 0;
   public static final int LEFT_CHANNEL = 1;
   public static final int RIGHT_CHANNEL = 2;
   public static final int DOWNMIX_CHANNELS = 3;
   public static final OutputChannels LEFT = new OutputChannels(1);
   public static final OutputChannels RIGHT = new OutputChannels(2);
   public static final OutputChannels BOTH = new OutputChannels(0);
   public static final OutputChannels DOWNMIX = new OutputChannels(3);
   private int outputChannels;

   public int getChannelCount() {
      return this.outputChannels == 0 ? 2 : 1;
   }

   public int getOutputChannels() {
      return this.outputChannels;
   }

   private OutputChannels(int n) {
      this.outputChannels = n;
      if (n < 0 || n > 3) {
         throw new IllegalArgumentException("channels");
      }
   }

   @Override
   public int hashCode() {
      return this.outputChannels;
   }

   @Override
   public boolean equals(Object obj) {
      boolean flag = false;
      if (obj instanceof OutputChannels) {
         flag = ((OutputChannels)obj).outputChannels == this.outputChannels;
      }

      return flag;
   }

   public static OutputChannels fromInt(int n) {
      switch (n) {
         case 0:
            return BOTH;
         case 1:
            return LEFT;
         case 2:
            return RIGHT;
         case 3:
            return DOWNMIX;
         default:
            throw new IllegalArgumentException("Invalid channel code: " + n);
      }
   }
}
