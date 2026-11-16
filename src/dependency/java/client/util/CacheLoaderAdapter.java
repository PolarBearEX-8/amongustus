package client.util;

import com.google.common.cache.CacheLoader;
import com.mojang.authlib.GameProfile;
import java.util.Map;
import net.minecraft.client.Minecraft;

public class CacheLoaderAdapter extends CacheLoader {
   public final SkinsManager skinsManager;

   public Map resolveMap(GameProfile gameProfile) throws Exception {
      return Minecraft.getMinecraft().getMinecraftSessionService().getTextures(gameProfile, false);
   }

   public CacheLoaderAdapter(SkinsManager skinsManager2) {
      this.skinsManager = skinsManager2;
   }

   public Object load(Object entry) throws Exception {
      return this.resolveMap((GameProfile)entry);
   }
}
