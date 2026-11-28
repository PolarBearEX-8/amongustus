package net.minecraft.client.entity;

import net.minecraft.world.WorldSettings;
import com.mojang.authlib.GameProfile;
import java.util.HashMap;
import java.util.Map;
import net.augustus.events.listeners.SprintListener;
import net.augustus.events.SprintEventType;
import net.augustus.Augustus;
import net.augustus.events.EventSprint;
import net.minecraft.client.Minecraft;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.client.renderer.ImageBufferDownload;
import net.minecraft.client.renderer.ThreadDownloadImageData;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StringUtils;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.optifine.config.Config;
import net.optifine.player.CapeUtils;
import net.optifine.player.PlayerConfigurations;

public abstract class AbstractClientPlayer extends EntityPlayer {
   private final Map cosmetics = new HashMap();
   private NetworkPlayerInfo playerInfo = null;
   private ResourceLocation locationOfCape = null;
   private String nameClear = null;
   private long reloadCapeTimeMs = 0L;
   private boolean elytraOfCape = false;

   @Override
   public Vec3 getLook(float f) {
      return this.getVectorForRotation(this.rotationPitch, this.rotationYaw);
   }

   public boolean hasCosmetics() {
      return this.cosmetics != null && !this.cosmetics.isEmpty();
   }

   public long getReloadCapeTimeMs() {
      return this.reloadCapeTimeMs;
   }

   public ResourceLocation getLocationCape() {
      if (!Config.isShowCapes()) {
         return null;
      } else {
         if (this.reloadCapeTimeMs != 0L && System.currentTimeMillis() > this.reloadCapeTimeMs) {
            CapeUtils.reloadCape(this);
            this.reloadCapeTimeMs = 0L;
         }

         if (this.locationOfCape != null) {
            return this.locationOfCape;
         } else {
            NetworkPlayerInfo playerInfo2 = this.getPlayerInfo();
            return playerInfo2 == null ? null : playerInfo2.getLocationCape();
         }
      }
   }

   @Override
   public boolean isSpectator() {
      if (Augustus.getInstance().getModuleManager().fakeGamemode.isToggled()) {
         return Minecraft.getMinecraft().playerController.getCurrentGameType() == WorldSettings.GameType.SPECTATOR;
      } else {
         NetworkPlayerInfo playerInfo2 = Minecraft.getMinecraft().getNetHandler().getPlayerInfo(this.getGameProfile().getId());
         return playerInfo2 != null && playerInfo2.getGameType() == WorldSettings.GameType.SPECTATOR;
      }
   }

   public String getSkinType() {
      NetworkPlayerInfo playerInfo2 = this.getPlayerInfo();
      return playerInfo2 == null ? DefaultPlayerSkin.getSkinType(this.getUniqueID()) : playerInfo2.getSkinType();
   }

   public Map getCosmetics() {
      return this.cosmetics;
   }

   public boolean isElytraOfCape() {
      return this.elytraOfCape;
   }

   public static ResourceLocation getLocationSkin(String str) {
      return new ResourceLocation("skins/" + StringUtils.stripControlCodes(str));
   }

   public void setReloadCapeTimeMs(long l) {
      this.reloadCapeTimeMs = l;
   }

   public ResourceLocation getLocationOfCape() {
      return this.locationOfCape;
   }

   public AbstractClientPlayer(World world, GameProfile profile) {
      super(world, profile);
      this.nameClear = profile.getName();
      if (this.nameClear != null && !this.nameClear.isEmpty()) {
         this.nameClear = StringUtils.stripControlCodes(this.nameClear);
      }

      CapeUtils.downloadCape(this);
      PlayerConfigurations.getPlayerConfiguration(this);
   }

   public NetworkPlayerInfo getPlayerInfo() {
      if (this.playerInfo == null) {
         this.playerInfo = Minecraft.getMinecraft().getNetHandler().getPlayerInfo(this.getUniqueID());
      }

      if (this.playerInfo != null) {
         this.setCustomDisplayName(this.playerInfo.getProtectedName());
      }

      return this.playerInfo;
   }

   public void setLocationOfCape(ResourceLocation location) {
      this.locationOfCape = location;
   }

   public ResourceLocation getLocationSkin() {
      NetworkPlayerInfo playerInfo2 = this.getPlayerInfo();
      return playerInfo2 == null ? DefaultPlayerSkin.getDefaultSkin(this.getUniqueID()) : playerInfo2.getLocationSkin();
   }

   public boolean hasElytraCape() {
      ResourceLocation locationCape = this.getLocationCape();
      return locationCape != null && (locationCape != this.locationOfCape || this.elytraOfCape);
   }

   public void setElytraOfCape(boolean flag) {
      this.elytraOfCape = flag;
   }

   public float getFovModifier() {
      float f = 1.0F;
      if (this.capabilities.isFlying) {
         f *= 1.1F;
      }

      double attributeValue = this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue();
      if (SprintListener.postSprint(SprintEventType.MOTION_SPRINT).isMotionSprint()) {
         attributeValue = 0.1300000101327896;
      }

      f = (float)((double)f * ((attributeValue / (double)this.capabilities.getWalkSpeed() + 1.0) / 2.0));
      if (this.capabilities.getWalkSpeed() == 0.0F || Float.isNaN(f) || Float.isInfinite(f)) {
         f = 1.0F;
      }

      if (this.isUsingItem() && this.getItemInUse().getItem() == Items.bow) {
         float f2 = (float)this.getItemInUseDuration() / 20.0F;
         if (f2 > 1.0F) {
            f2 = 1.0F;
         } else {
            f2 *= f2;
         }

         f *= 1.0F - f2 * 0.15F;
      }

      return f;
   }

   public String getNameClear() {
      return this.nameClear;
   }

   public boolean hasSkin() {
      NetworkPlayerInfo playerInfo2 = this.getPlayerInfo();
      return playerInfo2 != null && playerInfo2.hasLocationSkin();
   }

   public static ThreadDownloadImageData getDownloadImageSkin(ResourceLocation location, String str) {
      TextureManager textureManager = Minecraft.getMinecraft().getTextureManager();
      Object texture = textureManager.getTexture(location);
      if (texture == null) {
         texture = new ThreadDownloadImageData(
            null,
            String.format("http://skins.minecraft.net/MinecraftSkins/%s.png", StringUtils.stripControlCodes(str)),
            DefaultPlayerSkin.getDefaultSkin(getOfflineUUID(str)),
            new ImageBufferDownload()
         );
         textureManager.loadTexture(location, (ITextureObject)texture);
      }

      return (ThreadDownloadImageData)texture;
   }

   public boolean hasPlayerInfo() {
      return this.getPlayerInfo() != null;
   }

}
