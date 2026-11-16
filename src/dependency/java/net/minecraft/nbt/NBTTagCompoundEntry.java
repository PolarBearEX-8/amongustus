package net.minecraft.nbt;

import client.util.IntegratedServer;
import client.util.MovingManager;
import client.util.VanillaRunnable;
import com.mojang.authlib.GameProfile;
import java.net.SocketAddress;
import net.minecraft.entity.player.EntityPlayerAdapter;

public class NBTTagCompoundEntry extends MovingManager {
   public NBTTagCompound nBTTagCompound;

   @Override
   public void applyEntityPlayerAdapter(EntityPlayerAdapter entityPlayerAdapter) {
      if (entityPlayerAdapter.getText().equals(this.getIntegratedServer().getLabelText3())) {
         this.nBTTagCompound = new NBTTagCompound();
         entityPlayerAdapter.applyNBTTagCompound3(this.nBTTagCompound);
      }

      super.applyEntityPlayerAdapter(entityPlayerAdapter);
   }

   public IntegratedServer getIntegratedServer() {
      return (IntegratedServer)super.getVanillaRunnable();
   }

   @Override
   public NBTTagCompound getNBTTagCompound() {
      return this.nBTTagCompound;
   }

   @Override
   public VanillaRunnable getVanillaRunnable() {
      return this.getIntegratedServer();
   }

   @Override
   public String resolveText(SocketAddress socketAddress, GameProfile gameProfile) {
      return gameProfile.getName().equalsIgnoreCase(this.getIntegratedServer().getLabelText3()) && this.resolveEntityPlayerAdapter(gameProfile.getName()) != null
         ? "That name is already taken."
         : super.resolveText(socketAddress, gameProfile);
   }

   public NBTTagCompoundEntry(IntegratedServer integratedServer) {
      super(integratedServer);
      this.applyIndex(10);
   }
}
