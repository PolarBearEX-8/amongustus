package net.augustus.utils.file;

import net.augustus.Augustus;
import java.util.Locale;
import net.augustus.utils.interfaces.MC;

public class ClientPaths implements MC {
   public static final String CLIENT_DIR;

   static {
      CLIENT_DIR = ClientFileUtil.WORKING_DIR + Augustus.getInstance().getName().toLowerCase(Locale.ROOT);
   }

   public static void init() {
   }
}
