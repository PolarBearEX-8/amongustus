package client.util;

import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import com.mojang.authlib.minecraft.MinecraftProfileTexture.Type;
import java.util.Map;

public class AuxiliaryRunnableHandler implements Runnable {
   public final Map map;
   public final AdvancedRunnableAdapter advancedRunnableAdapter;

   public AuxiliaryRunnableHandler(AdvancedRunnableAdapter advancedRunnableAdapter2, Map map2) {
      this.advancedRunnableAdapter = advancedRunnableAdapter2;
      this.map = map2;
   }

   @Override
   public void run() {
      if (this.map.containsKey(Type.SKIN)) {
         this.advancedRunnableAdapter
            .skinsManager
            .resolveResourceLocationByMinecraftProfile(
               (MinecraftProfileTexture)this.map.get(Type.SKIN), Type.SKIN, this.advancedRunnableAdapter.preMotionSubscriber
            );
      }

      if (this.map.containsKey(Type.CAPE)) {
         this.advancedRunnableAdapter
            .skinsManager
            .resolveResourceLocationByMinecraftProfile(
               (MinecraftProfileTexture)this.map.get(Type.CAPE), Type.CAPE, this.advancedRunnableAdapter.preMotionSubscriber
            );
      }
   }
}
