package client.util;

import com.google.common.collect.Lists;
import java.io.File;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ServersManager {
   public static final Logger logger = LogManager.getLogger();
   public final Minecraft mc;
   public final List list = Lists.newArrayList();

   public void add(ServerData serverData) {
      this.list.add(serverData);
   }

   public static void applyServerData(ServerData serverData) {
      ServersManager serversManager = new ServersManager(Minecraft.getMinecraft());
      serversManager.updateState2();

      for (int index = 0; index < serversManager.size(); index++) {
         ServerData serverData2 = serversManager.get(index);
         if (serverData2 != null && serverData != null && serverData2.labelText3.equals(serverData.labelText3) && serverData2.text.equals(serverData.text)) {
            serversManager.set(index, serverData);
            break;
         }
      }

      serversManager.updateState();
   }

   public void updateState() {
      try {
         NBTTagList nBTTagList = new NBTTagList();

         for (ServerData serverData : (Iterable<ServerData>)(Iterable<?>)this.list) {
            nBTTagList.applyLISTManager(serverData.getNBTTagCompound());
         }

         NBTTagCompound tagCompound = new NBTTagCompound();
         tagCompound.applyText("servers", nBTTagList);
         LoadingNBTDataManager.applyNBTTagCompoundByNBTTagCompound(tagCompound, new File(this.mc.mcDataDir, "servers.dat"));
      } catch (Exception exception) {
         logger.error("Couldn't save server list", exception);
      }
   }

   public ServerData get(int count) {
      return (ServerData)this.list.get(count);
   }

   public void set(int count, ServerData serverData) {
      this.list.set(count, serverData);
   }

   public void applyIndex(int count, int count2) {
      ServerData serverData = this.get(count);
      this.list.set(count, this.get(count2));
      this.list.set(count2, serverData);
      this.updateState();
   }

   public void remove(int count) {
      this.list.remove(count);
   }

   public void updateState2() {
      try {
         this.list.clear();
         NBTTagCompound tagCompound = LoadingNBTDataManager.resolveNBTTagCompound(new File(this.mc.mcDataDir, "servers.dat"));
         if (tagCompound == null) {
            return;
         }

         NBTTagList extendedLoggerEntry = tagCompound.getExtendedLoggerEntry("servers", 10);

         for (int index = 0; index < extendedLoggerEntry.getInt(); index++) {
            this.list.add(ServerData.resolveServerData(extendedLoggerEntry.getNBTTagCompound(index)));
         }
      } catch (Exception exception) {
         logger.error("Couldn't load server list", exception);
      }
   }

   public int size() {
      return this.list.size();
   }

   public ServersManager(Minecraft mc2) {
      this.mc = mc2;
      this.updateState2();
   }
}
