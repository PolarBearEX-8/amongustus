package javazoom.jl.decoder;

public class BitstreamException extends JavaLayerException implements BitstreamErrors {
   private int errorcode = 256;

   public static String getErrorString(int n) {
      return "Bitstream errorcode " + Integer.toHexString(n);
   }

   public int getErrorcode() {
      return this.errorcode;
   }

   public BitstreamException(String str, Throwable t) {
      super(str, t);
   }

   public BitstreamException(int n, Throwable t) {
      this(getErrorString(n), t);
      this.errorcode = n;
   }
}
