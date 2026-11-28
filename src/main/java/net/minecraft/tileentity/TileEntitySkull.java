package net.minecraft.tileentity;

import com.google.common.collect.Iterables;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import java.util.UUID;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StringUtils;

public class TileEntitySkull extends TileEntity {
   private int skullType;
   private int skullRotation;
   private GameProfile playerProfile = null;
   private ResourceLocation skinLocation = null;

   public static GameProfile updateGameprofile(GameProfile profile) {
      if (profile != null && !StringUtils.isNullOrEmpty(profile.getName())) {
         if (profile.isComplete() && profile.getProperties().containsKey("textures")) {
            return profile;
         } else if (MinecraftServer.getServer() == null) {
            return profile;
         } else {
            GameProfile gameProfileForUsername = MinecraftServer.getServer().getPlayerProfileCache().getGameProfileForUsername(profile.getName());
            if (gameProfileForUsername == null) {
               return profile;
            } else {
               if (((Property)Iterables.getFirst(gameProfileForUsername.getProperties().get("textures"), null)) == null) {
                  gameProfileForUsername = MinecraftServer.getServer().getMinecraftSessionService().fillProfileProperties(gameProfileForUsername, true);
               }

               return gameProfileForUsername;
            }
         }
      } else {
         return profile;
      }
   }

   @Override
   public void readFromNBT(NBTTagCompound tag) {
      super.readFromNBT(tag);
      this.skullType = tag.getByte("SkullType");
      this.skullRotation = tag.getByte("Rot");
      if (this.skullType == 3) {
         if (tag.hasKey("Owner", 10)) {
            this.playerProfile = NBTUtil.readGameProfileFromNBT(tag.getCompoundTag("Owner"));
         } else if (tag.hasKey("ExtraType", 8)) {
            String string = tag.getString("ExtraType");
            if (!StringUtils.isNullOrEmpty(string)) {
               this.playerProfile = new GameProfile((UUID)null, string);
               this.updatePlayerProfile();
            }
         }
      }
   }

   @Override
   public Packet getDescriptionPacket() {
      NBTTagCompound tag = new NBTTagCompound();
      this.writeToNBT(tag);
      return new S35PacketUpdateTileEntity(this.pos, 4, tag);
   }

   public GameProfile getPlayerProfile() {
      return this.playerProfile;
   }

   public ResourceLocation getSkinLocation() {
      return this.skinLocation;
   }

   public void setType(int n) {
      this.skullType = n;
      this.playerProfile = null;
   }

   @Override
   public void writeToNBT(NBTTagCompound tag) {
      super.writeToNBT(tag);
      tag.setByte("SkullType", (byte)(this.skullType & 0xFF));
      tag.setByte("Rot", (byte)(this.skullRotation & 0xFF));
      if (this.playerProfile != null) {
         NBTTagCompound tag2 = new NBTTagCompound();
         NBTUtil.writeGameProfile(tag2, this.playerProfile);
         tag.setTag("Owner", tag2);
      }
   }

   public int getSkullType() {
      return this.skullType;
   }

   public void setSkullRotation(int n) {
      this.skullRotation = n;
   }

   public int getSkullRotation() {
      return this.skullRotation;
   }

   public void setSkinLocation(ResourceLocation location) {
      this.skinLocation = location;
   }

   private void updatePlayerProfile() {
      this.playerProfile = updateGameprofile(this.playerProfile);
      this.markDirty();
   }

   public void setPlayerProfile(GameProfile profile) {
      this.skullType = 3;
      this.playerProfile = profile;
      this.updatePlayerProfile();
   }
}
