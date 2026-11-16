package client.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ByteArrayOutputStreamAdapter extends ByteArrayOutputStream {
   public int count2;
   public int count3;
   public final ExtraByteArrayEntry extraByteArrayEntry;

   @Override
   public void close() throws IOException {
      this.extraByteArrayEntry.applyByteArray(this.count2, this.count3, this.buf, this.count);
   }

   public ByteArrayOutputStreamAdapter(ExtraByteArrayEntry extraByteArrayEntry2, int count, int count4) {
      super(8096);
      this.extraByteArrayEntry = extraByteArrayEntry2;
      this.count2 = count;
      this.count3 = count4;
   }
}
