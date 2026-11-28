package net.minecraft.client.network;

import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import com.mojang.authlib.minecraft.MinecraftProfileTexture.Type;
import net.minecraft.client.resources.SkinManager;
import net.minecraft.network.play.server.S38PacketPlayerListItem;
import net.minecraft.world.WorldSettings;
import com.google.common.base.MoreObjects;
import com.mojang.authlib.GameProfile;
import net.augustus.ClientManagers;
import net.augustus.Augustus;
import net.augustus.utils.ProtectorManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ResourceLocation;

public class NetworkPlayerInfo {
   private String protectedName;
   public boolean customSkinFlag;
   private final GameProfile gameProfile;
   public WorldSettings.GameType gameType;
   private int responseTime;
   public boolean playerTexturesLoaded;
   private ResourceLocation locationSkin;
   private ResourceLocation locationCape;
   private String skinType;
   private String playerName;
   private IChatComponent displayName;
   private int lastHealth;
   private int displayHealth;
   private long lastHealthTime;
   private long healthBlinkTime;
   private long renderVisibilityId;

   public void setLocationCape(ResourceLocation location) {
      this.locationCape = location;
   }

   public IChatComponent getDisplayName() {
      return this.displayName;
   }

   public static String getPlayerName(NetworkPlayerInfo networkPlayerInfo) {
      return networkPlayerInfo.playerName;
   }

   public ScorePlayerTeam getPlayerTeam() {
      return Minecraft.getMinecraft().theWorld.getScoreboard().getPlayersTeam(this.getGameProfile().getName());
   }

   public NetworkPlayerInfo(S38PacketPlayerListItem.AddPlayerData addPlayerData) {
       super();
      this.protectedName = "";
      this.customSkinFlag = false;
      this.playerTexturesLoaded = false;
      this.playerName = "";
      this.lastHealth = 0;
      this.displayHealth = 0;
      this.lastHealthTime = 0L;
      this.healthBlinkTime = 0L;
      this.renderVisibilityId = 0L;
      this.gameProfile = addPlayerData.getProfile();
      this.playerName = this.gameProfile.getName();
      this.protectedName = Augustus.getInstance().getProtectorManager().getProtectedName(this.gameProfile.getName());
      this.gameType = addPlayerData.getGameMode();
      this.responseTime = addPlayerData.getPing();
      this.displayName = addPlayerData.getDisplayName();
   }

   public static String getSkinTypeInternal(NetworkPlayerInfo networkPlayerInfo) {
      return networkPlayerInfo.skinType;
   }

   public void setRenderVisibilityId(long l) {
      this.renderVisibilityId = l;
   }

   public GameProfile getGameProfile() {
      return this.gameProfile;
   }

   public static ResourceLocation setLocationSkinInternal(NetworkPlayerInfo networkPlayerInfo, ResourceLocation location) {
      return networkPlayerInfo.locationSkin = location;
   }

   public static ResourceLocation setLocationCapeInternal(NetworkPlayerInfo networkPlayerInfo, ResourceLocation location) {
      return networkPlayerInfo.locationCape = location;
   }

   public String getSkinType() {
      return this.skinType == null ? DefaultPlayerSkin.getSkinType(this.gameProfile.getId()) : this.skinType;
   }

   public void loadPlayerTextures() {
      synchronized (this) {
         if (!this.playerTexturesLoaded) {
            this.playerTexturesLoaded = true;
            GameProfile gameProfile2 = this.gameProfile;
            if (ClientManagers.moduleManager.customSkin.isToggled() && this.playerName.equals(Minecraft.getMinecraft().session.getUsername())) {
               ResourceLocation selectedSkinTexture = ClientManagers.moduleManager.customSkin.getSelectedSkinTexture();
               if (selectedSkinTexture != null) {
                  this.locationSkin = selectedSkinTexture;
                  this.skinType = ClientManagers.moduleManager.customSkin.getSkinModelType();
               }
            }

            Minecraft.getMinecraft().getSkinManager().loadProfileTextures(gameProfile2, new NetworkPlayerInfo.DefaultSkinAvailableCallback(this), true);
         }
      }
   }

   public void setDisplayHealth(int n) {
      this.displayHealth = n;
   }

   public void setLastHealth(int n) {
      this.lastHealth = n;
   }

   public String getProtectedName() {
      return this.protectedName;
   }

   public long getHealthBlinkTime() {
      return this.healthBlinkTime;
   }

   public WorldSettings.GameType getGameType() {
      return this.gameType;
   }

   public long getRenderVisibilityId() {
      return this.renderVisibilityId;
   }

   public int getLastHealth() {
      return this.lastHealth;
   }

   public int getDisplayHealth() {
      return this.displayHealth;
   }

   public void setResponseTime(int n) {
      this.responseTime = n;
   }

   public void setLastHealthTime(long l) {
      this.lastHealthTime = l;
   }

   public int getResponseTime() {
      return this.responseTime;
   }

   public void setProtectedName(String str) {
      this.protectedName = str;
   }

   public boolean hasLocationSkin() {
      return this.locationSkin != null;
   }

   public ResourceLocation getLocationSkin() {
      if (this.locationSkin == null) {
         this.loadPlayerTextures();
      }

      return (ResourceLocation)MoreObjects.firstNonNull(this.locationSkin, DefaultPlayerSkin.getDefaultSkin(this.gameProfile.getId()));
   }

   public void setGameType(WorldSettings.GameType gameType2) {
      this.gameType = gameType2;
   }

   public ResourceLocation getLocationCape() {
      if (this.locationCape == null) {
         this.loadPlayerTextures();
      }

      return this.locationCape;
   }

   public static String assignSkinType(NetworkPlayerInfo networkPlayerInfo, String str) {
      return networkPlayerInfo.skinType = str;
   }

   public void setDisplayName(IChatComponent component) {
      this.displayName = component;
   }

   public long getLastHealthTime() {
      return this.lastHealthTime;
   }

   public void setHealthBlinkTime(long l) {
      this.healthBlinkTime = l;
   }

   public String getPlayerName() {
      return this.playerName;
   }

   public NetworkPlayerInfo(GameProfile profile) {
       super();
      this.protectedName = "";
      this.customSkinFlag = false;
      this.playerTexturesLoaded = false;
      this.playerName = "";
      this.lastHealth = 0;
      this.displayHealth = 0;
      this.lastHealthTime = 0L;
      this.healthBlinkTime = 0L;
      this.renderVisibilityId = 0L;
      this.gameProfile = profile;
      this.protectedName = Augustus.getInstance().getProtectorManager().getProtectedName(this.gameProfile.getName());
      this.playerName = this.gameProfile.getName();
   }

public static class DefaultSkinAvailableCallback implements SkinManager.SkinAvailableCallback {
      public final NetworkPlayerInfo networkPlayerInfo;

      @Override
      public void skinAvailable(Type type, ResourceLocation location, MinecraftProfileTexture minecraftProfileTexture) {
         switch (type) {
            case SKIN:
               NetworkPlayerInfo.setLocationSkinInternal(this.networkPlayerInfo, location);
               if (ClientManagers.moduleManager.protector.isToggled()) {
                  NetworkPlayerInfo.setLocationSkinInternal(this.networkPlayerInfo, Augustus.getInstance().getProtectorManager().getNextProtectorSkin());
               }

               if (ClientManagers.moduleManager.customSkin.isToggled() && NetworkPlayerInfo.getPlayerName(this.networkPlayerInfo).equals(Minecraft.getMinecraft().session.getUsername())) {
                  ResourceLocation selectedSkinTexture = ClientManagers.moduleManager.customSkin.getSelectedSkinTexture();
                  if (selectedSkinTexture != null) {
                     NetworkPlayerInfo.setLocationSkinInternal(this.networkPlayerInfo, selectedSkinTexture);
                     NetworkPlayerInfo.assignSkinType(this.networkPlayerInfo, ClientManagers.moduleManager.customSkin.getSkinModelType());
                  }
               } else {
                  NetworkPlayerInfo.assignSkinType(this.networkPlayerInfo, minecraftProfileTexture.getMetadata("model"));
                  if (NetworkPlayerInfo.getSkinTypeInternal(this.networkPlayerInfo) == null) {
                     NetworkPlayerInfo.assignSkinType(this.networkPlayerInfo, "default");
                  }
               }
               break;
            case CAPE:
               if (!ClientManagers.moduleManager.protector.isToggled()) {
                  NetworkPlayerInfo.setLocationCapeInternal(this.networkPlayerInfo, location);
               }
         }
      }

      public DefaultSkinAvailableCallback(NetworkPlayerInfo networkPlayerInfo2) {
         this.networkPlayerInfo = networkPlayerInfo2;
      }

}

}
