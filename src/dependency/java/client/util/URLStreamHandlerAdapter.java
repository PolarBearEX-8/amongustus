package client.util;

import client.network.URLConnectionAdapter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;
import net.minecraft.util.ResourceLocation;

public final class URLStreamHandlerAdapter extends URLStreamHandler {
   public final ResourceLocation resourceLocation;

   public URLStreamHandlerAdapter(ResourceLocation location) {
      this.resourceLocation = location;
   }

   @Override
   public URLConnection openConnection(URL url) {
      return new URLConnectionAdapter(this, url);
   }
}
