package client.network;

import client.Client;
import client.ClientGlobals;
import client.module.Module;
import client.setting.WorldSettingsGameType;
import client.util.LatencyManager;
import client.util.SlimManager;
import client.util.TertiaryHashMapEntry;
import client.util.TertiaryMapEntry;
import com.google.common.base.MoreObjects;
import com.mojang.authlib.GameProfile;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.AuxiliaryModelManager;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ResourceLocation;

public class NetworkPlayerInfo {
   public String text4 = "";
   public boolean state = false;
   public final GameProfile gameProfile;
   public WorldSettingsGameType worldSettingsGameType;
   public int indexOffset3;
   public boolean state6 = false;
   public ResourceLocation resourceLocation;
   public ResourceLocation resourceLocation2;
   public String text;
   public String text2 = "";
   public IChatComponent chatComponent;
   public int indexOffset = 0;
   public int indexOffset1 = 0;
   public long epochMillis = 0L;
   public long epochMillis5 = 0L;
   public long epochMillis7 = 0L;

   public void setChatComponent(IChatComponent chatComponent2) {
      this.chatComponent = chatComponent2;
   }

   public static ResourceLocation resolveResourceLocation2(NetworkPlayerInfo networkPlayerInfo, ResourceLocation location) {
      return networkPlayerInfo.resourceLocation = location;
   }

   public long getEpochMillis7() {
      return this.epochMillis7;
   }

   public void setResourceLocation2(ResourceLocation resourceLocation3) {
      this.resourceLocation2 = resourceLocation3;
   }

   public static String processAction(NetworkPlayerInfo networkPlayerInfo) {
      return networkPlayerInfo.text2;
   }

   public static ResourceLocation resolveResourceLocation(NetworkPlayerInfo networkPlayerInfo, ResourceLocation location) {
      return networkPlayerInfo.resourceLocation2 = location;
   }

   public ScorePlayerTeam getScorePlayerTeam() {
      return Minecraft.getMinecraft().theWorld.getThePlayerNameManager().getScorePlayerTeam(this.getGameProfile().getName());
   }

   public String getText() {
      return this.text == null ? SlimManager.resolveText(this.gameProfile.getId()) : this.text;
   }

   public ResourceLocation getResourceLocation() {
      if (this.resourceLocation == null) {
         this.updateState();
      }

      return (ResourceLocation)MoreObjects.firstNonNull(this.resourceLocation, SlimManager.resolveResourceLocationByUUID(this.gameProfile.getId()));
   }

   public int getIndexOffset1() {
      return this.indexOffset1;
   }

   public void setEpochMillis(long epochMillis2) {
      this.epochMillis = epochMillis2;
   }

   public long getEpochMillis() {
      return this.epochMillis;
   }

   public void setText4(String text3) {
      this.text4 = text3;
   }

   public IChatComponent getChatComponent() {
      return this.chatComponent;
   }

   public void setIndexOffset(int indexOffset2) {
      this.indexOffset = indexOffset2;
   }

   public void updateState() {
      synchronized (this) {
         if (!this.state6) {
            this.state6 = true;
            GameProfile gameProfile2 = this.gameProfile;
            if (((Module)ClientGlobals.moduleManager.customSkin).isToggled() && this.text2.equals(Minecraft.getMinecraft().session.getText1())) {
               this.resourceLocation = TertiaryMapEntry.get(ClientGlobals.moduleManager.customSkin.settingCustomName.getSelected());
            }

            Minecraft.getMinecraft().getSkinsManager().submit(gameProfile2, new AuxiliaryModelManager(this), true);
         }
      }
   }

   public void setWorldSettingsGameType(WorldSettingsGameType worldSettingsGameType2) {
      this.worldSettingsGameType = worldSettingsGameType2;
   }

   public ResourceLocation getResourceLocationAsResourceLocation() {
      if (this.resourceLocation2 == null) {
         this.updateState();
      }

      return this.resourceLocation2;
   }

   public int getIndexOffset3() {
      return this.indexOffset3;
   }

   public WorldSettingsGameType getWorldSettingsGameType() {
      return this.worldSettingsGameType;
   }

   public String getText2() {
      return this.text2;
   }

   public void setIndexOffset3(int indexOffset2) {
      this.indexOffset3 = indexOffset2;
   }

   public void setEpochMillis7(long epochMillis2) {
      this.epochMillis7 = epochMillis2;
   }

   public long getEpochMillis5() {
      return this.epochMillis5;
   }

   public void setEpochMillis5(long epochMillis2) {
      this.epochMillis5 = epochMillis2;
   }

   public static String processAction2(NetworkPlayerInfo networkPlayerInfo) {
      return networkPlayerInfo.text;
   }

   public int getIndexOffset() {
      return this.indexOffset;
   }

   public static String resolveText(NetworkPlayerInfo networkPlayerInfo, String text3) {
      return networkPlayerInfo.text = text3;
   }

   public String getText4() {
      return this.text4;
   }

   public GameProfile getGameProfile() {
      return this.gameProfile;
   }

   public boolean isActive() {
      return this.resourceLocation != null;
   }

   public void setIndexOffset1(int indexOffset2) {
      this.indexOffset1 = indexOffset2;
   }

   public NetworkPlayerInfo(GameProfile gameProfile2) {
      this.gameProfile = gameProfile2;
      TertiaryHashMapEntry tertiaryHashMapEntry = Client.getClient().getTertiaryHashMapEntry();
      String name = this.gameProfile.getName();
      this.text4 = tertiaryHashMapEntry.resolveText(name);
      this.text2 = this.gameProfile.getName();
   }

   public NetworkPlayerInfo(LatencyManager latencyManager) {
      this.gameProfile = latencyManager.getGameProfile();
      this.text2 = this.gameProfile.getName();
      TertiaryHashMapEntry tertiaryHashMapEntry = Client.getClient().getTertiaryHashMapEntry();
      String name = this.gameProfile.getName();
      this.text4 = tertiaryHashMapEntry.resolveText(name);
      this.worldSettingsGameType = latencyManager.getWorldSettingsGameType();
      this.indexOffset3 = latencyManager.getCount();
      this.chatComponent = latencyManager.getIChatComponent();
   }
}
