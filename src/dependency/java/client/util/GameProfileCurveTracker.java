package client.util;

import client.gui.TexturesGuiIconsPngManager;
import com.mojang.authlib.GameProfile;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.network.play.client.C18PacketSpectate;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ResourceLocation;

public class GameProfileCurveTracker implements CurveTracker {
   public final GameProfile gameProfile;
   public final ResourceLocation resourceLocation;

   @Override
   public void applySplineConverter(SplineConverter splineConverter) {
      Minecraft.getMinecraft().getNetHandlerPlayClient().handlePacket(new C18PacketSpectate(this.gameProfile.getId()));
   }

   @Override
   public IChatComponent getName() {
      return new ChatComponentText(this.gameProfile.getName());
   }

   public GameProfileCurveTracker(GameProfile gameProfile2) {
      this.gameProfile = gameProfile2;
      this.resourceLocation = AbstractClientPlayer.resolveResourceLocation(gameProfile2.getName());
      AbstractClientPlayer.resolveLoggerEntryHandler(this.resourceLocation, gameProfile2.getName());
   }

   @Override
   public boolean isEmpty() {
      return true;
   }

   @Override
   public void applyFloat(float factor, int count) {
      Minecraft.getMinecraft().getTextureManager().applyResourceLocation3(this.resourceLocation);
      GlStateManager.color4f2(1.0F, 1.0F, 1.0F, (float)count / 255.0F);
      TexturesGuiIconsPngManager.processAction(2, 2, 8.0F, 8.0F, 8, 8, 12, 12, 64.0F, 64.0F);
      TexturesGuiIconsPngManager.processAction(2, 2, 40.0F, 8.0F, 8, 8, 12, 12, 64.0F, 64.0F);
   }
}
