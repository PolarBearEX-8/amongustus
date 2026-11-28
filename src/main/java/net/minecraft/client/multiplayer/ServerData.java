package net.minecraft.client.multiplayer;

import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;
import net.minecraft.nbt.NBTTagCompound;

public class ServerData {
   public String serverName;
   public String serverIP;
   public String populationInfo;
   public String serverMOTD;
   public long pingToServer;
   public int version = 47;
   public String gameVersion;
   public boolean pinged;
   public String playerList;
   private ServerData.ServerResourceMode resourceMode = ServerData.ServerResourceMode.PROMPT;
   private String serverIcon;
   private boolean lanServer;

   public static ServerData getServerDataFromNBTCompound(NBTTagCompound tag) {
      ServerData serverData = new ServerData(tag.getString("name"), tag.getString("ip"), false);
      if (tag.hasKey("icon", 8)) {
         serverData.setBase64EncodedIconData(tag.getString("icon"));
      }

      if (tag.hasKey("acceptTextures", 1)) {
         if (tag.getBoolean("acceptTextures")) {
            serverData.setResourceMode(ServerData.ServerResourceMode.ENABLED);
         } else {
            serverData.setResourceMode(ServerData.ServerResourceMode.DISABLED);
         }
      } else {
         serverData.setResourceMode(ServerData.ServerResourceMode.PROMPT);
      }

      return serverData;
   }

   public NBTTagCompound getNBTCompound() {
      NBTTagCompound tag = new NBTTagCompound();
      tag.setString("name", this.serverName);
      tag.setString("ip", this.serverIP);
      if (this.serverIcon != null) {
         tag.setString("icon", this.serverIcon);
      }

      if (this.resourceMode == ServerData.ServerResourceMode.ENABLED) {
         tag.setBoolean("acceptTextures", true);
      } else if (this.resourceMode == ServerData.ServerResourceMode.DISABLED) {
         tag.setBoolean("acceptTextures", false);
      }

      return tag;
   }

   public void setBase64EncodedIconData(String str) {
      this.serverIcon = str;
   }

   public boolean isLanServer() {
      return this.lanServer;
   }

   public ServerData.ServerResourceMode getResourceMode() {
      return this.resourceMode;
   }

   public String getBase64EncodedIconData() {
      return this.serverIcon;
   }

   public ServerData(String str, String str2, boolean flag) {
      this.serverName = str;
      this.serverIP = str2;
      this.lanServer = flag;
   }

   public void setResourceMode(ServerData.ServerResourceMode serverResourceMode) {
      this.resourceMode = serverResourceMode;
   }

   public void copyFrom(ServerData serverData) {
      this.serverIP = serverData.serverIP;
      this.serverName = serverData.serverName;
      this.setResourceMode(serverData.getResourceMode());
      this.serverIcon = serverData.serverIcon;
      this.lanServer = serverData.lanServer;
   }


   public enum ServerResourceMode {
      ENABLED("enabled"),
      DISABLED("disabled"),
      PROMPT("prompt");

   private final IChatComponent motd;
   public IChatComponent getMotd() {
         return this.motd;
      }

      private ServerResourceMode(String str) {
         this.motd = new ChatComponentTranslation("addServer.resourcePack." + str, new Object[0]);
      }
   }
}
