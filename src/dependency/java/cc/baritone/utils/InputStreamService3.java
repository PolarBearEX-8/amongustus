package cc.baritone.utils;

import java.io.IOException;
import java.io.InputStream;

public class InputStreamService3 implements Service3 {
   public final InputStream inputStream;

   public InputStreamService3(InputStream inputStream2) {
      if (inputStream2 == null) {
         throw new NullPointerException("in");
      } else {
         this.inputStream = inputStream2;
      }
   }

   @Override
   public long getTimestamp() {
      return -1L;
   }

   @Override
   public boolean isActiveAsFlag() {
      return true;
   }

   @Override
   public long getTimestampAsTime() {
      return -1L;
   }

   @Override
   public int read(byte[] byteValueArray, int count, int count2) throws IOException {
      return this.inputStream.read(byteValueArray, count, count2);
   }

   @Override
   public boolean isActive() {
      return false;
   }

   @Override
   public long getTimestamp2() {
      return -1L;
   }
}
