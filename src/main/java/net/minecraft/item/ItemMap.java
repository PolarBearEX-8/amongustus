package net.minecraft.item;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Iterables;
import com.google.common.collect.Multisets;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockStone;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.network.Packet;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.storage.MapData;

public class ItemMap extends ItemMapBase {

   @Override
   public Packet createMapDataPacket(ItemStack stack, World world, EntityPlayer player) {
      return this.getMapData(stack, world).getMapPacket(stack, world, player);
   }

   public MapData getMapData(ItemStack stack, World world) {
      String str = "map_" + stack.getMetadata();
      MapData mapData = (MapData)world.loadItemData(MapData.class, str);
      if (mapData == null && !world.isRemote) {
         stack.setItemDamage(world.getUniqueDataId("map"));
         str = "map_" + stack.getMetadata();
         mapData = new MapData(str);
         mapData.scale = 3;
         mapData.calculateMapCenter((double)world.getWorldInfo().getSpawnX(), (double)world.getWorldInfo().getSpawnZ(), mapData.scale);
         mapData.dimension = (byte)world.provider.getDimensionId();
         mapData.markDirty();
         world.setItemData(str, mapData);
      }

      return mapData;
   }

   @Override
   public void onUpdate(ItemStack stack, World world, Entity entity, int n, boolean flag) {
      if (!world.isRemote) {
         MapData mapData = this.getMapData(stack, world);
         if (entity instanceof EntityPlayer) {
            mapData.updateVisiblePlayers((EntityPlayer)entity, stack);
         }

         if (flag) {
            this.updateMapData(world, entity, mapData);
         }
      }
   }

   public ItemMap() {
      this.setHasSubtypes(true);
   }

   public void updateMapData(World world, Entity entity, MapData mapData) {
      if (world.provider.getDimensionId() == mapData.dimension && entity instanceof EntityPlayer) {
         int n = 1 << mapData.scale;
         int xCenter = mapData.xCenter;
         int zCenter = mapData.zCenter;
         int n2 = MathHelper.floor_double(entity.posX - (double)xCenter) / n + 64;
         int n3 = MathHelper.floor_double(entity.posZ - (double)zCenter) / n + 64;
         int n4 = 128 / n;
         if (world.provider.getHasNoSky()) {
            n4 /= 2;
         }

         MapData.MapInfo mapInfo = mapData.getMapInfo((EntityPlayer)entity);
         mapInfo.step++;
         boolean flag = false;

         for (int i = n2 - n4 + 1; i < n2 + n4; i++) {
            if ((i & 15) == (mapInfo.step & 15) || flag) {
               flag = false;
               double d = 0.0;

               for (int j = n3 - n4 - 1; j < n3 + n4; j++) {
                  if (i >= 0 && j >= -1 && i < 128 && j < 128) {
                     int n5 = i - n2;
                     int n6 = j - n3;
                     boolean flag2 = n5 * n5 + n6 * n6 > (n4 - 2) * (n4 - 2);
                     int n7 = (xCenter / n + i - 64) * n;
                     int n8 = (zCenter / n + j - 64) * n;
                     HashMultiset hashMultiset = HashMultiset.create();
                     Chunk chunkFromBlockCoords = world.getChunkFromBlockCoords(new BlockPos(n7, 0, n8));
                     if (!chunkFromBlockCoords.isEmpty()) {
                        int n9 = n7 & 15;
                        int n10 = n8 & 15;
                        int n11 = 0;
                        double d2 = 0.0;
                        if (world.provider.getHasNoSky()) {
                           int n12 = n7 + n8 * 231871;
                           n12 = n12 * n12 * 31287121 + n12 * 11;
                           if ((n12 >> 20 & 1) == 0) {
                              hashMultiset.add(Blocks.dirt.getMapColor(Blocks.dirt.getDefaultState().withProperty(BlockDirt.VARIANT, BlockDirt.DirtType.DIRT)), 10);
                           } else {
                              hashMultiset.add(
                                 Blocks.stone.getMapColor(Blocks.stone.getDefaultState().withProperty(BlockStone.VARIANT, BlockStone.EnumType.STONE)), 100
                              );
                           }

                           d2 = 100.0;
                        } else {
                           BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();

                           for (int k = 0; k < n; k++) {
                              for (int m = 0; m < n; m++) {
                                 int n13 = chunkFromBlockCoords.getHeightValue(k + n9, m + n10) + 1;
                                 IBlockState defaultState = Blocks.air.getDefaultState();
                                 if (n13 > 1) {
                                    do {
                                       defaultState = chunkFromBlockCoords.getBlockState(pos.set(k + n9, --n13, m + n10));
                                    } while (defaultState.getBlock().getMapColor(defaultState) == MapColor.airColor && n13 > 0);

                                    if (n13 > 0 && defaultState.getBlock().getMaterial().isLiquid()) {
                                       int n14 = n13 - 1;

                                       Block block;
                                       do {
                                          block = chunkFromBlockCoords.getBlock(k + n9, n14--, m + n10);
                                          n11++;
                                       } while (n14 > 0 && block.getMaterial().isLiquid());
                                    }
                                 }

                                 d2 += (double)n13 / (double)(n * n);
                                 hashMultiset.add(defaultState.getBlock().getMapColor(defaultState));
                              }
                           }
                        }

                        n11 /= n * n;
                        double d3 = (d2 - d) * 4.0 / (double)(n + 4) + ((double)(i + j & 1) - 0.5) * 0.4;
                        byte b = 1;
                        if (d3 > 0.6) {
                           b = 2;
                        }

                        if (d3 < -0.6) {
                           b = 0;
                        }

                        MapColor first = (MapColor)Iterables.getFirst(Multisets.copyHighestCountFirst(hashMultiset), MapColor.airColor);
                        if (first == MapColor.waterColor) {
                           d3 = (double)n11 * 0.1 + (double)(i + j & 1) * 0.2;
                           b = 1;
                           if (d3 < 0.5) {
                              b = 2;
                           }

                           if (d3 > 0.9) {
                              b = 0;
                           }
                        }

                        d = d2;
                        if (j >= 0 && n5 * n5 + n6 * n6 < n4 * n4 && (!flag2 || (i + j & 1) != 0)) {
                           byte b2 = mapData.colors[i + j * 128];
                           byte b3 = (byte)(first.colorIndex * 4 + b);
                           if (b2 != b3) {
                              mapData.colors[i + j * 128] = b3;
                              mapData.updateMapData(i, j);
                              flag = true;
                           }
                        }
                     }
                  }
               }
            }
         }
      }
   }

   public static MapData loadMapData(int n, World world) {
      String str = "map_" + n;
      MapData mapData = (MapData)world.loadItemData(MapData.class, str);
      if (mapData == null) {
         mapData = new MapData(str);
         world.setItemData(str, mapData);
      }

      return mapData;
   }

   @Override
   public void onCreated(ItemStack stack, World world, EntityPlayer player) {
      if (stack.hasTagCompound() && stack.getTagCompound().getBoolean("map_is_scaling")) {
         MapData mapData = Items.filled_map.getMapData(stack, world);
         stack.setItemDamage(world.getUniqueDataId("map"));
         MapData mapData2 = new MapData("map_" + stack.getMetadata());
         mapData2.scale = (byte)(mapData.scale + 1);
         if (mapData2.scale > 4) {
            mapData2.scale = 4;
         }

         mapData2.calculateMapCenter((double)mapData.xCenter, (double)mapData.zCenter, mapData2.scale);
         mapData2.dimension = mapData.dimension;
         mapData2.markDirty();
         world.setItemData("map_" + stack.getMetadata(), mapData2);
      }
   }

   @Override
   public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean flag) {
      MapData mapData = this.getMapData(stack, player.entityWorld);
      if (flag) {
         if (mapData == null) {
            list.add("Unknown map");
         } else {
            list.add("Scaling at 1:" + (1 << mapData.scale));
            list.add("(Level " + mapData.scale + "/" + 4 + ")");
         }
      }
   }
}
