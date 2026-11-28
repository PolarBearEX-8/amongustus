package net.minecraft.client.multiplayer;

import com.google.common.collect.Lists;
import java.io.File;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ServerList {
   private static final Logger LOGGER = LogManager.getLogger();
   private final Minecraft mc;
   private final List servers = Lists.newArrayList();

   public void loadServerList() {
      try {
         this.servers.clear();
         NBTTagCompound tag = CompressedStreamTools.read(new File(this.mc.mcDataDir, "servers.dat"));
         if (tag == null) {
            return;
         }

         NBTTagList tagList = tag.getTagList("servers", 10);

         for (int i = 0; i < tagList.tagCount(); i++) {
            this.servers.add(ServerData.getServerDataFromNBTCompound(tagList.getCompoundTagAt(i)));
         }
      } catch (Exception e) {
         LOGGER.error("Couldn't load server list", e);
      }
   }

   public static void saveSingleServer(ServerData serverData) {
      ServerList serverList = new ServerList(Minecraft.getMinecraft());
      serverList.loadServerList();

      for (int i = 0; i < serverList.countServers(); i++) {
         ServerData serverData2 = serverList.getServerData(i);
         if (serverData2 != null && serverData != null && serverData2.serverName.equals(serverData.serverName) && serverData2.serverIP.equals(serverData.serverIP)) {
            serverList.setServerData(i, serverData);
            break;
         }
      }

      serverList.saveServerList();
   }

   public ServerList(Minecraft mc2) {
      this.mc = mc2;
      this.loadServerList();
   }

   public void saveServerList() {
      try {
         NBTTagList tagList = new NBTTagList();

         for (ServerData serverData : (Iterable<ServerData>)(this.servers)) {
            tagList.appendTag(serverData.getNBTCompound());
         }

         NBTTagCompound tag = new NBTTagCompound();
         tag.setTag("servers", tagList);
         CompressedStreamTools.safeWrite(tag, new File(this.mc.mcDataDir, "servers.dat"));
      } catch (Exception e) {
         LOGGER.error("Couldn't save server list", e);
      }
   }

   public void addServerData(ServerData serverData) {
      this.servers.add(serverData);
   }

   public int countServers() {
      return this.servers.size();
   }

   public void swapServers(int n, int n2) {
      ServerData serverData = this.getServerData(n);
      this.servers.set(n, this.getServerData(n2));
      this.servers.set(n2, serverData);
      this.saveServerList();
   }

   public void removeServerData(int n) {
      this.servers.remove(n);
   }

   public ServerData getServerData(int n) {
      return (ServerData)this.servers.get(n);
   }

   public void setServerData(int n, ServerData serverData) {
      this.servers.set(n, serverData);
   }
}
