package client.network;

import client.util.URLStreamHandlerAdapter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import net.minecraft.client.Minecraft;

public class URLConnectionAdapter extends URLConnection {
   public final URLStreamHandlerAdapter uRLStreamHandlerAdapter;

   public URLConnectionAdapter(URLStreamHandlerAdapter uRLStreamHandlerAdapter2, URL url) {
      super(url);
      this.uRLStreamHandlerAdapter = uRLStreamHandlerAdapter2;
   }

   @Override
   public void connect() throws IOException {
   }

   @Override
   public InputStream getInputStream() throws IOException {
      return Minecraft.getMinecraft().getIResourceManager().resolveIResourceByResourceLocation(this.uRLStreamHandlerAdapter.resourceLocation).getInputStream();
   }
}
