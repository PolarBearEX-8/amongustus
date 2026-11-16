package client.util;

import client.event.PreMotionSubscriber;
import com.google.common.collect.Maps;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.minecraft.InsecureTextureException;
import java.util.HashMap;
import net.minecraft.client.Minecraft;

public class AdvancedRunnableAdapter implements Runnable {
   public final GameProfile gameProfile;
   public final boolean enabled;
   public final PreMotionSubscriber preMotionSubscriber;
   public final SkinsManager skinsManager;

   public AdvancedRunnableAdapter(SkinsManager skinsManager2, GameProfile gameProfile2, boolean flag, PreMotionSubscriber preMotionSubscriber2) {
      this.skinsManager = skinsManager2;
      this.gameProfile = gameProfile2;
      this.enabled = flag;
      this.preMotionSubscriber = preMotionSubscriber2;
   }

   @Override
   public void run() {
      HashMap map = Maps.newHashMap();

      try {
         map.putAll(SkinsManager.resolveMinecraftSessionService(this.skinsManager).getTextures(this.gameProfile, this.enabled));
      } catch (InsecureTextureException insecureTextureException) {
      }

      if (map.isEmpty() && this.gameProfile.getId().equals(Minecraft.getMinecraft().getSession().getGameProfile().getId())) {
         this.gameProfile.getProperties().clear();
         this.gameProfile.getProperties().putAll(Minecraft.getMinecraft().getPropertyMap());
         map.putAll(SkinsManager.resolveMinecraftSessionService(this.skinsManager).getTextures(this.gameProfile, false));
      }

      Minecraft mc = Minecraft.getMinecraft();
      AuxiliaryRunnableHandler auxiliaryRunnableHandler = new AuxiliaryRunnableHandler(this, map);
      mc.resolveListenableFuture(auxiliaryRunnableHandler);
   }
}
