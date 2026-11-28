package baritone.utils.type;

import it.unimi.dsi.fastutil.bytes.ByteArrayList;

public final class VarInt {
   private final int value;
   private final byte[] serialized;
   private final int size;

   public static VarInt read(byte[] bytes, int n) {
      int n2 = 0;
      int n3 = 0;
      int n4 = n;

      byte b;
      do {
         b = bytes[n4++];
         n2 |= (b & 127) << n3++ * 7;
         if (n3 > 5) {
            throw new IllegalArgumentException("VarInt size cannot exceed 5 bytes");
         }
      } while ((b & 128) != 0);

      return new VarInt(n2);
   }

   public VarInt(int n) {
      this.value = n;
      this.serialized = serialize0(this.value);
      this.size = this.serialized.length;
   }

   private static byte[] serialize0(int n) {
      ByteArrayList byteArrayList = new ByteArrayList();

      int n2;
      for (n2 = n; (n2 & 128) != 0; n2 >>>= 7) {
         byteArrayList.add((byte)(n2 & 127 | 128));
      }

      byteArrayList.add((byte)(n2 & 0xFF));
      return byteArrayList.toByteArray();
   }

   public final int getSize() {
      return this.size;
   }

   public final int getValue() {
      return this.value;
   }

   public static VarInt read(byte[] bytes) {
      return read(bytes, 0);
   }

   public final byte[] getSerialized() {
      return this.serialized;
   }
}
