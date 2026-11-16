package net.minecraft.tileentity;

import client.util.AuxiliaryNameManager;
import client.util.ClientBrandHelper;
import client.util.Packet;
import client.util.VanillaRunnable;
import com.google.common.collect.Iterables;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import java.util.UUID;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.util.ResourceLocation;

public class TileEntitySkull extends TileEntity {
   public int count2;
   public int count3;
   public GameProfile gameProfile = null;
   public ResourceLocation resourceLocation = null;

   public void setResourceLocation(ResourceLocation resourceLocation2) {
      this.resourceLocation = resourceLocation2;
   }

   public void applyGameProfile(GameProfile gameProfile2) {
      this.count2 = 3;
      this.gameProfile = gameProfile2;
      this.updateState();
   }

   public GameProfile getGameProfile() {
      return this.gameProfile;
   }

   public int getCount2() {
      return this.count2;
   }

   public void setCount3(int count) {
      this.count3 = count;
   }

   private void updateState() {
      this.gameProfile = resolveGameProfile(this.gameProfile);
      this.processAction5();
   }

   public void applyIndex(int count) {
      this.count2 = count;
      this.gameProfile = null;
   }

   @Override
   public void applyNBTTagCompoundByNBTTagCompound(NBTTagCompound tagCompound) {
      super.applyNBTTagCompoundByNBTTagCompound(tagCompound);
      this.count2 = tagCompound.computeByteCode("SkullType");
      this.count3 = tagCompound.computeByteCode("Rot");
      if (this.count2 == 3) {
         if (tagCompound.matchesText("Owner", 10)) {
            this.gameProfile = AuxiliaryNameManager.resolveGameProfile(tagCompound.getNBTTagCompound("Owner"));
         } else if (tagCompound.matchesText("ExtraType", 8)) {
            String text = tagCompound.resolveText("ExtraType");
            if (!ClientBrandHelper.isEmpty(text)) {
               this.gameProfile = new GameProfile((UUID)null, text);
               this.updateState();
            }
         }
      }
   }

   public ResourceLocation getResourceLocation() {
      return this.resourceLocation;
   }

   @Override
   public Packet getPacket() {
      NBTTagCompound tagCompound = new NBTTagCompound();
      this.applyNBTTagCompound(tagCompound);
      return new S35PacketUpdateTileEntity(this.blockPos, 4, tagCompound);
   }

   public static GameProfile resolveGameProfile(GameProfile gameProfile2) {
      if (gameProfile2 != null && !ClientBrandHelper.isEmpty(gameProfile2.getName())) {
         if (gameProfile2.isComplete() && gameProfile2.getProperties().containsKey("textures")) {
            return gameProfile2;
         } else if (VanillaRunnable.getVanillaRunnable() == null) {
            return gameProfile2;
         } else {
            GameProfile gameProfile3 = VanillaRunnable.getVanillaRunnable().getYyyyMMDdHHMmSsZManager().resolveGameProfile(gameProfile2.getName());
            if (gameProfile3 == null) {
               return gameProfile2;
            } else {
               if ((Property)Iterables.getFirst(gameProfile3.getProperties().get("textures"), null) == null) {
                  gameProfile3 = VanillaRunnable.getVanillaRunnable().getMinecraftSessionService().fillProfileProperties(gameProfile3, true);
               }

               return gameProfile3;
            }
         }
      } else {
         return gameProfile2;
      }
   }

   public int getCount3() {
      return this.count3;
   }

   @Override
   public void applyNBTTagCompound(NBTTagCompound tagCompound) {
      super.applyNBTTagCompound(tagCompound);
      tagCompound.put("SkullType", (byte)(this.count2 & 0xFF));
      tagCompound.put("Rot", (byte)(this.count3 & 0xFF));
      if (this.gameProfile != null) {
         NBTTagCompound tagCompound2 = new NBTTagCompound();
         AuxiliaryNameManager.resolveNBTTagCompound(tagCompound2, this.gameProfile);
         tagCompound.applyText("Owner", tagCompound2);
      }
   }
}
