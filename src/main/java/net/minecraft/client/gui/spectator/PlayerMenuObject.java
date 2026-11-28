package net.minecraft.client.gui.spectator;

import com.mojang.authlib.GameProfile;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.network.play.client.C18PacketSpectate;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ResourceLocation;

public class PlayerMenuObject implements ISpectatorMenuObject {
   private final GameProfile profile;
   private final ResourceLocation resourceLocation;

   @Override
   public IChatComponent getSpectatorName() {
      return new ChatComponentText(this.profile.getName());
   }

   @Override
   public void selectItem(SpectatorMenu spectatorMenu) {
      Minecraft.getMinecraft().getNetHandler().addToSendQueue(new C18PacketSpectate(this.profile.getId()));
   }

   public PlayerMenuObject(GameProfile profile2) {
      this.profile = profile2;
      this.resourceLocation = AbstractClientPlayer.getLocationSkin(profile2.getName());
      AbstractClientPlayer.getDownloadImageSkin(this.resourceLocation, profile2.getName());
   }

   @Override
   public void renderIcon(float f, int n) {
      Minecraft.getMinecraft().getTextureManager().bindTexture(this.resourceLocation);
      GlStateManager.color(1.0F, 1.0F, 1.0F, (float)n / 255.0F);
      Gui.drawScaledCustomSizeModalRect(2, 2, 8.0F, 8.0F, 8, 8, 12, 12, 64.0F, 64.0F);
      Gui.drawScaledCustomSizeModalRect(2, 2, 40.0F, 8.0F, 8, 8, 12, 12, 64.0F, 64.0F);
   }

   @Override
   public boolean isEnabled() {
      return true;
   }
}
