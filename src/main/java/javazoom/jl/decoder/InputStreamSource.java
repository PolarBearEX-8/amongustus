package javazoom.jl.decoder;

import java.io.IOException;
import java.io.InputStream;

public class InputStreamSource implements Source {
   private final InputStream in;

   public InputStreamSource(InputStream in2) {
      if (in2 == null) {
         throw new NullPointerException("in");
      } else {
         this.in = in2;
      }
   }

   @Override
   public boolean willReadBlock() {
      return true;
   }

   @Override
   public long tell() {
      return -1L;
   }

   @Override
   public boolean isSeekable() {
      return false;
   }

   @Override
   public long seek(long l) {
      return -1L;
   }

   @Override
   public long length() {
      return -1L;
   }

   @Override
   public int read(byte[] bytes, int n, int n2) throws IOException {
      return this.in.read(bytes, n, n2);
   }
}
