package client.util;

import client.gui.TexturesGuiIconsPngManager;
import client.network.NetworkPlayerInfo;
import com.mojang.authlib.GameProfile;
import java.util.UUID;
import net.minecraft.util.ResourceLocation;

public class NetworkPlayerInfoMinecraftAccessor implements MinecraftAccessor {
   public final NetworkPlayerInfo networkPlayerInfo;

   public NetworkPlayerInfoMinecraftAccessor(String text, String text2) {
      this.networkPlayerInfo = new NetworkPlayerInfo(new GameProfile(UUID.fromString(text2), text));
   }

   public void applyIndex(int count, int count2) {
      float factor = 64.0F;
      this.applyIndex(count, count2, factor);
   }

   public ResourceLocation getResourceLocation() {
      return this.networkPlayerInfo.getResourceLocationAsResourceLocation();
   }

   public ResourceLocation getResourceLocationAsResourceLocation() {
      return this.networkPlayerInfo.getResourceLocation();
   }

   public void applyIndex(int count, int count2, float factor) {
      minecraft.getTextureManager().applyResourceLocation3(this.networkPlayerInfo.getResourceLocation());
      TexturesGuiIconsPngManager.processAction(count, count2, 8.0F, 8.0F, 8, 8, 8, 8, factor, factor);
   }
}
