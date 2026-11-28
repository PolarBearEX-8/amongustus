package javazoom.jl.decoder;

public class DecoderException extends JavaLayerException implements DecoderErrors {
   private int errorcode = 512;

   public int getErrorcode() {
      return this.errorcode;
   }

   public DecoderException(String str, Throwable t) {
      super(str, t);
   }

   public static String getErrorString(int n) {
      return "Decoder errorcode " + Integer.toHexString(n);
   }

   public DecoderException(int n, Throwable t) {
      this(getErrorString(n), t);
      this.errorcode = n;
   }
}
