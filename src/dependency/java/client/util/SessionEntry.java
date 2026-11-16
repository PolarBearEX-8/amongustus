package client.util;

import com.mojang.authlib.properties.PropertyMap;
import java.net.Proxy;
import net.minecraft.util.Session;

public class SessionEntry {
   public final Session session;
   public final PropertyMap propertyMap;
   public final PropertyMap propertyMap2;
   public final Proxy proxy;

   public SessionEntry(Session session2, PropertyMap propertyMap3, PropertyMap propertyMap4, Proxy proxy2) {
      this.session = session2;
      this.propertyMap = propertyMap3;
      this.propertyMap2 = propertyMap4;
      this.proxy = proxy2;
   }
}
