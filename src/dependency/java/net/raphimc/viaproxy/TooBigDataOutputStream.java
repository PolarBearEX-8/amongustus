package net.raphimc.viaproxy;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class TooBigDataOutputStream extends DataOutputStream {
   public void applyText(String text) throws IOException {
      byte[] bytes = text.getBytes(StandardCharsets.UTF_8);
      if (bytes.length > 32767) {
         throw new IllegalStateException("String too big (was " + bytes.length + " bytes encoded, max " + 32767 + ")");
      } else {
         this.applyIndex(bytes.length);
         this.write(bytes);
      }
   }

   public void applyIndex(int count) throws IOException {
      int count2;
      for (count2 = count; (count2 & -128) != 0; count2 >>>= 7) {
         this.writeByte(count2 & 127 | 128);
      }

      this.writeByte(count2);
   }

   public void applyByteArray(byte[] byteValueArray) throws IOException {
      this.applyIndex(byteValueArray.length);
      this.write(byteValueArray);
   }

   public TooBigDataOutputStream(OutputStream outputStream) {
      super(outputStream);
   }
}
