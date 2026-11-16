package client.util;

import client.event.McmetaListener;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import net.minecraft.util.ResourceLocation;

public class LeakedResourceInputStream extends InputStream {
   public final InputStream inputStream;
   public final String labelText;
   public boolean enabled = false;

   public LeakedResourceInputStream(InputStream inputStream2, ResourceLocation location, String text) {
      this.inputStream = inputStream2;
      ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
      new Exception().printStackTrace(new PrintStream(byteArrayOutputStream));
      this.labelText = "Leaked resource: '" + location + "' loaded from pack: '" + text + "'\n" + byteArrayOutputStream;
   }

   @Override
   public void finalize() throws Throwable {
      if (!this.enabled) {
         McmetaListener.getLogger().warn(this.labelText);
      }

      super.finalize();
   }

   @Override
   public int read() throws IOException {
      return this.inputStream.read();
   }

   @Override
   public void close() throws IOException {
      this.inputStream.close();
      this.enabled = true;
   }
}
