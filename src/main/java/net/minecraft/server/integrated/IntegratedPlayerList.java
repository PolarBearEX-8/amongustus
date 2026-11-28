package net.minecraft.server.integrated;

import com.mojang.authlib.GameProfile;
import java.net.SocketAddress;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.ServerConfigurationManager;

public class IntegratedPlayerList extends ServerConfigurationManager {
   private NBTTagCompound hostPlayerData;

   @Override
   public String allowUserToConnect(SocketAddress socketAddress, GameProfile profile) {
      return profile.getName().equalsIgnoreCase(this.getIntegratedServer().getServerOwner()) && this.getPlayerByUsername(profile.getName()) != null
         ? "That name is already taken."
         : super.allowUserToConnect(socketAddress, profile);
   }

   @Override
   public void writePlayerData(EntityPlayerMP entityPlayerMP) {
      if (entityPlayerMP.getName().equals(this.getIntegratedServer().getServerOwner())) {
         this.hostPlayerData = new NBTTagCompound();
         entityPlayerMP.writeToNBT(this.hostPlayerData);
      }

      super.writePlayerData(entityPlayerMP);
   }

   public IntegratedPlayerList(IntegratedServer integratedServer) {
      super(integratedServer);
      this.setViewDistance(10);
   }

   @Override
   public NBTTagCompound getHostPlayerData() {
      return this.hostPlayerData;
   }

   @Override
   public MinecraftServer getServerInstance() {
      return this.getIntegratedServer();
   }

   public IntegratedServer getIntegratedServer() {
      return (IntegratedServer)super.getServerInstance();
   }
}
