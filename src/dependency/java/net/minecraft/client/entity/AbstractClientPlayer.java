package net.minecraft.client.entity;

import client.Client;
import client.event.BridgeListener;
import client.event.MoveInputListener;
import client.module.Module;
import client.network.NetworkPlayerInfo;
import client.setting.CombatModeProperty;
import client.setting.WorldSettingsGameType;
import client.util.BuildManager;
import client.util.CapeofManager;
import client.util.ClientBrandHelper;
import client.util.LoggerEntryHandler;
import client.util.ServiceIntArrayEntry;
import client.util.SlimManager;
import client.util.UsersManager;
import com.mojang.authlib.GameProfile;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public abstract class AbstractClientPlayer extends EntityPlayer {
   public final Map map2 = new HashMap();
   public NetworkPlayerInfo networkPlayerInfo = null;
   public ResourceLocation resourceLocation = null;
   public String labelText = null;
   public long timestamp2 = 0L;
   public boolean enabled25 = false;

   public long getTimestamp2() {
      return this.timestamp2;
   }

   public String getLabelText() {
      return this.labelText;
   }

   public ResourceLocation getResourceLocation3() {
      if (!BuildManager.isActive68()) {
         return null;
      } else {
         if (this.timestamp2 != 0L && System.currentTimeMillis() > this.timestamp2) {
            CapeofManager.applyAbstractClientPlayer2(this);
            this.timestamp2 = 0L;
         }

         if (this.resourceLocation != null) {
            return this.resourceLocation;
         } else {
            NetworkPlayerInfo networkPlayerInfo2 = this.getNetworkPlayerInfo();
            return networkPlayerInfo2 == null ? null : networkPlayerInfo2.getResourceLocationAsResourceLocation();
         }
      }
   }

   public static LoggerEntryHandler resolveLoggerEntryHandler(ResourceLocation location, String text) {
      TextureManager textureManager = Minecraft.getMinecraft().getTextureManager();
      Object entry = textureManager.get(location);
      if (entry == null) {
         entry = new LoggerEntryHandler(
            null,
            String.format("http://skins.minecraft.net/MinecraftSkins/%s.png", ClientBrandHelper.getString(text)),
            SlimManager.resolveResourceLocationByUUID(resolveUUID(text)),
            new ServiceIntArrayEntry()
         );
         textureManager.matchesResourceLocation(location, (BridgeListener)entry);
      }

      return (LoggerEntryHandler)entry;
   }

   public void setTimestamp2(long timestamp) {
      this.timestamp2 = timestamp;
   }

   public Map getMap2() {
      return this.map2;
   }

   public ResourceLocation getResourceLocation2() {
      NetworkPlayerInfo networkPlayerInfo2 = this.getNetworkPlayerInfo();
      return networkPlayerInfo2 == null ? SlimManager.resolveResourceLocationByUUID(this.getEntityUniqueID()) : networkPlayerInfo2.getResourceLocation();
   }

   public static ResourceLocation resolveResourceLocation(String text) {
      return new ResourceLocation("skins/" + ClientBrandHelper.getString(text));
   }

   @Override
   public Vec3 getVec36(float factor) {
      return this.getVec34(this.rotationPitch, this.rotationYaw);
   }

   public NetworkPlayerInfo getNetworkPlayerInfo() {
      if (this.networkPlayerInfo == null) {
         this.networkPlayerInfo = Minecraft.getMinecraft().getNetHandlerPlayClient().getNetworkPlayerInfo(this.getEntityUniqueID());
      }

      if (this.networkPlayerInfo != null) {
         this.setText(this.networkPlayerInfo.getText4());
      }

      return this.networkPlayerInfo;
   }

   public void setResourceLocation(ResourceLocation resourceLocation2) {
      this.resourceLocation = resourceLocation2;
   }

   public ResourceLocation getResourceLocation() {
      return this.resourceLocation;
   }

   public boolean isActive29() {
      ResourceLocation resourceLocation2 = this.getResourceLocation3();
      return resourceLocation2 != null && (resourceLocation2 != this.resourceLocation || this.enabled25);
   }

   public boolean isEmpty() {
      return this.map2 != null && !this.map2.isEmpty();
   }

   public String getTextAsText() {
      NetworkPlayerInfo networkPlayerInfo2 = this.getNetworkPlayerInfo();
      return networkPlayerInfo2 == null ? SlimManager.resolveText(this.getEntityUniqueID()) : networkPlayerInfo2.getText();
   }

   public void setEnabled25(boolean enabled) {
      this.enabled25 = enabled;
   }

   public boolean isEnabled25() {
      return this.enabled25;
   }

   public boolean isActive28() {
      return this.getNetworkPlayerInfo() != null;
   }

   public boolean isActive27() {
      NetworkPlayerInfo networkPlayerInfo2 = this.getNetworkPlayerInfo();
      return networkPlayerInfo2 != null && networkPlayerInfo2.isActive();
   }

   @Override
   public boolean isActive23() {
      if (((Module)Client.getClient().getModuleManager().fakeGamemode).isToggled()) {
         return Minecraft.getMinecraft().playerController.getWorldSettingsGameType() == WorldSettingsGameType.SPECTATOR;
      } else {
         NetworkPlayerInfo networkPlayerInfo2 = Minecraft.getMinecraft().getNetHandlerPlayClient().getNetworkPlayerInfo(this.getGameProfile().getId());
         return networkPlayerInfo2 != null && networkPlayerInfo2.getWorldSettingsGameType() == WorldSettingsGameType.SPECTATOR;
      }
   }

   public float getFactor4() {
      float factor = 1.0F;
      if (this.invulnerableManager.state) {
         factor *= 1.1F;
      }

      double doubleItem = this.getAuxiliaryListener(SharedMonsterAttributes.extendedListener3).getDouble();
      if (MoveInputListener.resolveEventMoveInput(CombatModeProperty.MOTION_SPRINT).isEnabled()) {
         doubleItem = 0.13000001F;
      }

      float factor2 = (float)((double)factor * ((doubleItem / (double)this.invulnerableManager.getFactor2() + 1.0) / 2.0));
      if (this.invulnerableManager.getFactor2() == 0.0F || Float.isNaN(factor2) || Float.isInfinite(factor2)) {
         factor2 = 1.0F;
      }

      if (this.isUsingItem() && this.getItemStack2().getItem() == Items.bow) {
         float factor3 = (float)this.getInt10() / 20.0F;
         float factor4;
         if (factor3 > 1.0F) {
            factor4 = 1.0F;
         } else {
            factor4 = factor3 * factor3;
         }

         factor2 *= 1.0F - factor4 * 0.15F;
      }

      return factor2;
   }

   public AbstractClientPlayer(World world, GameProfile gameProfile) {
      super(world, gameProfile);
      this.labelText = gameProfile.getName();
      if (this.labelText != null && !this.labelText.isEmpty()) {
         this.labelText = ClientBrandHelper.getString(this.labelText);
      }

      CapeofManager.applyAbstractClientPlayer(this);
      UsersManager.resolveSpecialItemArrayEntryByAbstractClientPl(this);
   }
}
