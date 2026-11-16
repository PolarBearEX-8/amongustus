package net.raphimc.viaproxy;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class VarIntTooDataInputStream extends DataInputStream {
   public int getCount() throws IOException {
      int count = 0;
      int count2 = 0;

      byte byteValue;
      do {
         byteValue = this.readByte();
         count |= (byteValue & 127) << count2++ * 7;
         if (count2 > 5) {
            throw new RuntimeException("VarInt too big");
         }
      } while ((byteValue & 128) == 128);

      return count;
   }

   public VarIntTooDataInputStream(InputStream inputStream) {
      super(inputStream);
   }

   public String resolveText(int count) throws IOException {
      int count2 = this.getCount();
      if (count2 > count * 4) {
         throw new IllegalStateException("The received encoded string buffer length is longer than maximum allowed (" + count2 + " > " + count * 4 + ")");
      } else if (count2 < 0) {
         throw new IllegalStateException("The received encoded string buffer length is less than zero! Weird string!");
      } else {
         byte[] byteValueArray = new byte[count2];
         this.readFully(byteValueArray);
         String text = new String(byteValueArray, StandardCharsets.UTF_8);
         if (text.length() > count) {
            throw new IllegalStateException("The received string length is longer than maximum allowed (" + count2 + " > " + count + ")");
         } else {
            return text;
         }
      }
   }

   public byte[] getByteArray() throws IOException {
      byte[] byteValueArray = new byte[this.getCount()];
      this.readFully(byteValueArray);
      return byteValueArray;
   }
}
