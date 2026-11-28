package net.minecraft.world.storage;

import net.minecraft.network.play.server.S34PacketMaps;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.List;
import java.util.Map;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.Packet;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec4b;
import net.minecraft.world.World;
import net.minecraft.world.WorldSavedData;

public class MapData extends WorldSavedData {
   public int xCenter;
   public int zCenter;
   public byte dimension;
   public byte scale;
   public byte[] colors = new byte[16384];
   public List playersArrayList = Lists.newArrayList();
   private Map playersHashMap = Maps.newHashMap();
   public Map mapDecorations = Maps.newLinkedHashMap();

   @Override
   public void writeToNBT(NBTTagCompound tag) {
      tag.setByte("dimension", this.dimension);
      tag.setInteger("xCenter", this.xCenter);
      tag.setInteger("zCenter", this.zCenter);
      tag.setByte("scale", this.scale);
      tag.setShort("width", (short)128);
      tag.setShort("height", (short)128);
      tag.setByteArray("colors", this.colors);
   }

   private void updateDecorations(int n, World world, String str, double d, double d2, double d3) {
      int n2 = 1 << this.scale;
      float f = (float)(d - (double)this.xCenter) / (float)n2;
      float f2 = (float)(d2 - (double)this.zCenter) / (float)n2;
      byte b = (byte)((int)((double)(f * 2.0F) + 0.5));
      byte b2 = (byte)((int)((double)(f2 * 2.0F) + 0.5));
      byte b3 = 63;
      byte b4;
      if (f >= (float)(-b3) && f2 >= (float)(-b3) && f <= (float)b3 && f2 <= (float)b3) {
         d3 += d3 < 0.0 ? -8.0 : 8.0;
         b4 = (byte)((int)(d3 * 16.0 / 360.0));
         if (this.dimension < 0) {
            int n3 = (int)(world.getWorldInfo().getWorldTime() / 10L);
            b4 = (byte)(n3 * n3 * 34187121 + n3 * 121 >> 15 & 15);
         }
      } else {
         if (Math.abs(f) >= 320.0F || Math.abs(f2) >= 320.0F) {
            this.mapDecorations.remove(str);
            return;
         }

         n = 6;
         b4 = 0;
         if (f <= (float)(-b3)) {
            b = (byte)((int)((double)(b3 * 2) + 2.5));
         }

         if (f2 <= (float)(-b3)) {
            b2 = (byte)((int)((double)(b3 * 2) + 2.5));
         }

         if (f >= (float)b3) {
            b = (byte)(b3 * 2 + 1);
         }

         if (f2 >= (float)b3) {
            b2 = (byte)(b3 * 2 + 1);
         }
      }

      this.mapDecorations.put(str, new Vec4b((byte)n, b, b2, b4));
   }

   public void updateMapData(int n, int n2) {
      super.markDirty();

      for (MapData.MapInfo mapInfo : (Iterable<MapData.MapInfo>)(this.playersArrayList)) {
         mapInfo.update(n, n2);
      }
   }

   public void calculateMapCenter(double d, double d2, int n) {
      int n2 = 128 * (1 << n);
      int n3 = MathHelper.floor_double((d + 64.0) / (double)n2);
      int n4 = MathHelper.floor_double((d2 + 64.0) / (double)n2);
      this.xCenter = n3 * n2 + n2 / 2 - 64;
      this.zCenter = n4 * n2 + n2 / 2 - 64;
   }

   public MapData(String str) {
      super(str);
   }

   public MapData.MapInfo getMapInfo(EntityPlayer player) {
      MapData.MapInfo mapInfo = (MapData.MapInfo)this.playersHashMap.get(player);
      if (mapInfo == null) {
         mapInfo = new MapData.MapInfo(this, player);
         this.playersHashMap.put(player, mapInfo);
         this.playersArrayList.add(mapInfo);
      }

      return mapInfo;
   }

   public Packet getMapPacket(ItemStack stack, World world, EntityPlayer player) {
      MapData.MapInfo mapInfo = (MapData.MapInfo)this.playersHashMap.get(player);
      return mapInfo == null ? null : mapInfo.getPacket(stack);
   }

   public void updateVisiblePlayers(EntityPlayer player, ItemStack stack) {
      if (!this.playersHashMap.containsKey(player)) {
         MapData.MapInfo mapInfo = new MapData.MapInfo(this, player);
         this.playersHashMap.put(player, mapInfo);
         this.playersArrayList.add(mapInfo);
      }

      if (!player.inventory.hasItemStack(stack)) {
         this.mapDecorations.remove(player.getName());
      }

      for (int i = 0; i < this.playersArrayList.size(); i++) {
         MapData.MapInfo mapInfo2 = (MapData.MapInfo)this.playersArrayList.get(i);
         if (!mapInfo2.entityplayerObj.isDead && (mapInfo2.entityplayerObj.inventory.hasItemStack(stack) || stack.isOnItemFrame())) {
            if (!stack.isOnItemFrame() && mapInfo2.entityplayerObj.dimension == this.dimension) {
               this.updateDecorations(
                  0,
                  mapInfo2.entityplayerObj.entityWorld,
                  mapInfo2.entityplayerObj.getName(),
                  mapInfo2.entityplayerObj.posX,
                  mapInfo2.entityplayerObj.posZ,
                  (double)mapInfo2.entityplayerObj.rotationYaw
               );
            }
         } else {
            this.playersHashMap.remove(mapInfo2.entityplayerObj);
            this.playersArrayList.remove(mapInfo2);
         }
      }

      if (stack.isOnItemFrame()) {
         EntityItemFrame itemFrame = stack.getItemFrame();
         BlockPos hangingPosition = itemFrame.getHangingPosition();
         this.updateDecorations(
            1, player.entityWorld, "frame-" + itemFrame.getEntityId(), (double)hangingPosition.getX(), (double)hangingPosition.getZ(), (double)(itemFrame.facingDirection.getHorizontalIndex() * 90)
         );
      }

      if (stack.hasTagCompound() && stack.getTagCompound().hasKey("Decorations", 9)) {
         NBTTagList tagList = stack.getTagCompound().getTagList("Decorations", 10);

         for (int j = 0; j < tagList.tagCount(); j++) {
            NBTTagCompound compoundTagAt = tagList.getCompoundTagAt(j);
            if (!this.mapDecorations.containsKey(compoundTagAt.getString("id"))) {
               this.updateDecorations(compoundTagAt.getByte("type"), player.entityWorld, compoundTagAt.getString("id"), compoundTagAt.getDouble("x"), compoundTagAt.getDouble("z"), compoundTagAt.getDouble("rot"));
            }
         }
      }
   }

   @Override
   public void readFromNBT(NBTTagCompound tag) {
      this.dimension = tag.getByte("dimension");
      this.xCenter = tag.getInteger("xCenter");
      this.zCenter = tag.getInteger("zCenter");
      this.scale = tag.getByte("scale");
      this.scale = (byte)MathHelper.clamp_int(this.scale, 0, 4);
      short s = tag.getShort("width");
      short s2 = tag.getShort("height");
      if (s == 128 && s2 == 128) {
         this.colors = tag.getByteArray("colors");
      } else {
         byte[] byteArray = tag.getByteArray("colors");
         this.colors = new byte[16384];
         int n = (128 - s) / 2;
         int n2 = (128 - s2) / 2;

         for (int i = 0; i < s2; i++) {
            int n3 = i + n2;
            if (n3 >= 0 || n3 < 128) {
               for (int j = 0; j < s; j++) {
                  int n4 = j + n;
                  if (n4 >= 0 || n4 < 128) {
                     this.colors[n4 + n3 * 128] = byteArray[j + i * s];
                  }
               }
            }
         }
      }
   }


   public static class MapInfo {
      public final EntityPlayer entityplayerObj;
      private boolean isDirty;
      private int minX;
      private int minY;
      private int maxX;
      private int maxY;
      private int tick;
      public int step;
      public final MapData mapData;

      public Packet getPacket(ItemStack stack) {
         if (this.isDirty) {
            this.isDirty = false;
            return new S34PacketMaps(
               stack.getMetadata(),
               this.mapData.scale,
               this.mapData.mapDecorations.values(),
               this.mapData.colors,
               this.minX,
               this.minY,
               this.maxX + 1 - this.minX,
               this.maxY + 1 - this.minY
            );
         } else {
            return this.tick++ % 5 == 0
               ? new S34PacketMaps(stack.getMetadata(), this.mapData.scale, this.mapData.mapDecorations.values(), this.mapData.colors, 0, 0, 0, 0)
               : null;
         }
      }

      public MapInfo(MapData mapData2, EntityPlayer player) {
         this.mapData = mapData2;
         this.isDirty = true;
         this.minX = 0;
         this.minY = 0;
         this.maxX = 127;
         this.maxY = 127;
         this.entityplayerObj = player;
      }

      public void update(int n, int n2) {
         if (this.isDirty) {
            this.minX = Math.min(this.minX, n);
            this.minY = Math.min(this.minY, n2);
            this.maxX = Math.max(this.maxX, n);
            this.maxY = Math.max(this.maxY, n2);
         } else {
            this.isDirty = true;
            this.minX = n;
            this.minY = n2;
            this.maxX = n;
            this.maxY = n2;
         }
      }
   }
}
