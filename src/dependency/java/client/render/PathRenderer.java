package client.render;

import client.Client;
import client.event.RenderListener;
import client.network.CosmeticsPayload;
import client.util.AlertNotificationSound;
import client.util.BuildManager;
import client.util.IBlockState;
import client.util.MinecraftAccessor;
import client.util.MovingObjectType;
import client.util.PathNode;
import client.util.PlayerUtil;
import client.util.PreEndHandManager;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLadder;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.BlockPlaceData;
import net.minecraft.block.Blocks;
import net.minecraft.block.WorldHelper;
import net.minecraft.block.material.Material;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.util.Vec3i;
import net.minecraft.world.storage.WorldInfo;
import org.lwjgl.opengl.GL11;

public class PathRenderer implements MinecraftAccessor, RenderListener {
   public double value;
   public RecordNode recordNode;
   public RecordNode recordNode2;
   public boolean state3;
   public double value2;
   public boolean enabled;
   public double value3;
   public final boolean enabled2;
   public final boolean enabled3;
   public RecordNode recordNode3;
   public Long2ObjectOpenHashMap long2ObjectOpenHashMap;
   public final PriorityQueue priorityQueue = new PriorityQueue();
   public RecordNode recordNode4;
   public boolean enabled4;
   public ArrayList arrayList;
   public double coordOffset;
   public final BlockPlaceData blockPlaceData;
   public double value4;
   public Long2ObjectOpenHashMap long2ObjectOpenHashMap2;
   public double coordOffset2;
   public boolean state1;
   public double coordOffset4;
   public final boolean enabled5;
   public final BlockPlaceData blockPlaceData2;
   public double value5;
   public boolean state = true;

   @Override
   public void onRender3D(float factor) {
      GL11.glPushMatrix();
      GL11.glEnable(3042);
      GL11.glBlendFunc(770, 771);
      GL11.glEnable(2848);
      GL11.glDisable(3553);
      GL11.glDisable(3008);
      if (BuildManager.canExecute()) {
         PreEndHandManager.updateState();
      }

      GlStateManager.disableAlpha();
      GlStateManager.applyState3();
      GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 0.7F);
      GL11.glTranslated(
         -minecraft.getRenderManager().getRenderPosX(), -minecraft.getRenderManager().getRenderPosY(), -minecraft.getRenderManager().getRenderPosZ()
      );

      for (Object[] entryArray : (Iterable<Object[]>)(Iterable)(this.arrayList)) {
         AxisAlignedBB boundingBox = (AxisAlignedBB)entryArray[0];
         if (!(
            (boundingBox.minX - minecraft.thePlayer.posX) * (boundingBox.minX - minecraft.thePlayer.posX)
                  + (boundingBox.minZ - minecraft.thePlayer.posZ) * (boundingBox.minZ - minecraft.thePlayer.posZ)
               > 625.0
         )) {
            BlockPlaceData blockPlaceData3 = (BlockPlaceData)entryArray[1];
            if (blockPlaceData3 != null) {
               GlStateManager.color4f2(1.0F, 0.0F, 0.0F, 0.7F);
               GL11.glLineWidth(3.0F);
               GL11.glBegin(3);
               GL11.glVertex3d((boundingBox.minX + boundingBox.maxX) / 2.0, (boundingBox.minY + boundingBox.maxY) / 2.0, (boundingBox.minZ + boundingBox.maxZ) / 2.0);
               GL11.glVertex3d((double)blockPlaceData3.indexOffset1 + 0.5, (double)blockPlaceData3.indexOffset2 + 0.5, (double)blockPlaceData3.indexOffset + 0.5);
               GL11.glEnd();
               GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 0.7F);
               GL11.glLineWidth(1.0F);
            }

            RenderUtils.processAction(boundingBox);
         }
      }

      RenderUtils.prepareScissor();
      GL11.glEnable(3008);
      RenderUtils.setColor(-1);
      GL11.glPopMatrix();
   }

   private static AxisAlignedBB resolveAxisAlignedBB(Vec3 vec) {
      float factor = 0.3F;
      return new AxisAlignedBB(
         vec.xCoord - (double)factor, vec.yCoord - 0.1F, vec.zCoord - (double)factor, vec.xCoord + (double)factor, vec.yCoord + 1.8F, vec.zCoord + (double)factor
      );
   }

   public static boolean matchesRecordNode(RecordNode recordNode5, RecordNode recordNode6, int count, int count2, int count3, boolean flag, boolean flag2, IBlockState blockState) {
      if ((Boolean)recordNode6.dataRef) {
         return (Boolean)recordNode5.dataRef;
      } else {
         int count4 = count2 - 1;
         recordNode5.dataRef = WorldHelper.matchesIBlockAccess(minecraft.theWorld, count, count4, count3, flag, flag2, blockState);
         recordNode6.dataRef = true;
         return (Boolean)recordNode5.dataRef;
      }
   }

   private static void applyList(List list, PathNode pathNode) {
      list.add(pathNode);

      for (PathNode pathNode2 : (Iterable<PathNode>)(Iterable)(pathNode.getArrayList())) {
         applyList(list, pathNode2);
      }
   }

   public PathRenderer(BlockPlaceData blockPlaceData3, BlockPlaceData blockPlaceData4, boolean flag, boolean flag2, boolean flag3) {
      this.value5 = Double.MAX_VALUE;
      this.coordOffset = 1.0;
      this.arrayList = new ArrayList();
      this.state1 = false;
      this.state3 = false;
      this.coordOffset4 = 0.1;
      this.coordOffset2 = Double.POSITIVE_INFINITY;
      this.recordNode2 = new RecordNode(null);
      this.recordNode4 = new RecordNode(null);
      this.enabled4 = false;
      this.value3 = -3.0E7;
      this.value2 = 3.0E7;
      this.value = -3.0E7;
      this.value4 = 3.0E7;
      this.recordNode3 = new RecordNode(false);
      this.recordNode = new RecordNode(false);
      this.enabled = false;
      Block block = minecraft.theWorld.getIBlockState(blockPlaceData3.indexOffset1, blockPlaceData3.indexOffset2, blockPlaceData3.indexOffset).getBlock();
      if (block != Blocks.block81 && block.processAction17()) {
         blockPlaceData3 = new BlockPlaceData(blockPlaceData3.indexOffset1, blockPlaceData3.indexOffset2 + 1, blockPlaceData3.indexOffset);
      }

      if (minecraft.theWorld.getIBlockState(blockPlaceData4.indexOffset1, blockPlaceData4.indexOffset2, blockPlaceData4.indexOffset).getBlock().processAction17()) {
         blockPlaceData4 = new BlockPlaceData(blockPlaceData4.indexOffset1, blockPlaceData4.indexOffset2 + 1, blockPlaceData4.indexOffset);
      }

      if (minecraft.theWorld.getInternalListEntry() != null) {
         WorldInfo internalListEntry = minecraft.theWorld.getInternalListEntry();
         this.value3 = internalListEntry.getValue9();
         this.value = internalListEntry.getValue();
         this.value2 = internalListEntry.getValue2();
         this.value4 = internalListEntry.getValueAsDouble();
      }

      this.long2ObjectOpenHashMap2 = new Long2ObjectOpenHashMap();
      this.long2ObjectOpenHashMap = new Long2ObjectOpenHashMap(Math.min(10, (int)blockPlaceData3.getCoordinate(blockPlaceData4)));
      this.blockPlaceData2 = blockPlaceData3;
      this.blockPlaceData = blockPlaceData4;
      this.enabled5 = flag;
      this.enabled2 = flag2;
      this.enabled3 = flag3;
   }

   public void applyPathNode(PathNode pathNode) {
      this.long2ObjectOpenHashMap.put(pathNode.timestamp, pathNode);
      if ((pathNode.getBlockPlaceData().indexOffset1 & 15) != 15
         && (pathNode.getBlockPlaceData().indexOffset1 & 15) != 0
         && (pathNode.getBlockPlaceData().indexOffset & 15) != 15
         && (pathNode.getBlockPlaceData().indexOffset & 15) != 0) {
         this.recordNode2.dataRef = null;
         this.enabled4 = true;
      } else {
         this.enabled4 = false;
         this.recordNode2.dataRef = null;
      }

      this.enabled = false;
      this.recordNode4.dataRef = null;
      int indexOffset = pathNode.getBlockPlaceData().indexOffset1;
      int indexOffset2 = pathNode.getBlockPlaceData().indexOffset2;
      int indexOffset3 = pathNode.getBlockPlaceData().indexOffset;
      int count = indexOffset3 + 1;
      this.applyPathNode(pathNode, indexOffset, indexOffset2, count);
      int count2 = indexOffset3 - 1;
      this.applyPathNode(pathNode, indexOffset, indexOffset2, count2);
      int count3 = indexOffset + 1;
      this.applyPathNode(pathNode, count3, indexOffset2, indexOffset3);
      int count4 = indexOffset - 1;
      this.applyPathNode(pathNode, count4, indexOffset2, indexOffset3);
      if (this.state) {
         int count5 = indexOffset + 1;
         int count6 = indexOffset3 + 1;
         int count7 = count5;
         this.applyPathNode(pathNode, count7, indexOffset2, count6, true);
         count5 = indexOffset + 1;
         int count8 = indexOffset3 - 1;
         int count9 = count5;
         this.applyPathNode(pathNode, count9, indexOffset2, count8, true);
         count5 = indexOffset - 1;
         int count10 = indexOffset3 + 1;
         int count11 = count5;
         this.applyPathNode(pathNode, count11, indexOffset2, count10, true);
         count5 = indexOffset - 1;
         int count12 = indexOffset3 - 1;
         int count13 = count5;
         this.applyPathNode(pathNode, count13, indexOffset2, count12, true);
      }

      this.enabled4 = true;
      int count14 = indexOffset2 + 1;
      this.applyPathNode(pathNode, indexOffset, count14, indexOffset3);
      int count15 = indexOffset2 - 1;
      this.applyPathNode(pathNode, indexOffset, count15, indexOffset3);
   }

   public PathNode resolvePathNode(PathNode pathNode, int count, int count2, int count3, long value6, boolean flag) {
      if (this.long2ObjectOpenHashMap.containsKey(value6)) {
         return null;
      } else {
         BlockPlaceData blockPlaceData3 = null;
         if (pathNode != null) {
            blockPlaceData3 = pathNode.getBlockPlaceData();
         }

         BlockPlaceData blockPlaceData4 = pathNode != null && pathNode.getPathNode() != null ? pathNode.getPathNode().getBlockPlaceData() : null;
         double offset = this.computeValue(count, count2, count3, blockPlaceData3, blockPlaceData4, flag);
         if (offset == -1.0) {
            return null;
         } else {
            double offset2 = 0.0;
            if (pathNode != null) {
               offset2 = pathNode.getDouble() + pathNode.getBlockPlaceData().computeValue(count, count2, count3) / offset;
            }

            PathNode pathNode2 = new PathNode(pathNode, new BlockPlaceData(count, count2, count3), offset2, this.blockPlaceData.computeValue(count, count2, count3), value6);
            pathNode2.value = offset;
            if (pathNode != null) {
               pathNode.getArrayList().add(pathNode2);
            }

            this.long2ObjectOpenHashMap2.put(value6, pathNode2);
            this.priorityQueue.add(pathNode2);
            return pathNode2;
         }
      }
   }

   private double computeValue(int count, int count2, int count3, BlockPlaceData blockPlaceData3, BlockPlaceData blockPlaceData4, boolean flag2) {
        boolean flag = false;
      if (!this.checkIndex(count, count3)) {
         return -1.0;
      } else {
         int count10 = Integer.MAX_VALUE;
         int count11 = 0;
         int count12 = 0;
         double offset = 1.0;
         if (blockPlaceData3 != null) {
            count10 = blockPlaceData3.indexOffset1;
            count11 = blockPlaceData3.indexOffset2;
            count12 = blockPlaceData3.indexOffset;
         }

         AlertNotificationSound alertNotificationSound;
         if (this.enabled4) {
            alertNotificationSound = (AlertNotificationSound)this.recordNode2.dataRef;
            if (alertNotificationSound == null) {
               this.recordNode2.dataRef = alertNotificationSound = minecraft.theWorld.getLoggerEntry(count, count3);
               if (alertNotificationSound.canExecute()) {
                  this.enabled = true;
                  return -1.0;
               }
            }
         } else {
            alertNotificationSound = minecraft.theWorld.getLoggerEntry(count, count3);
            if (alertNotificationSound.canExecute()) {
               return -1.0;
            }
         }

         IBlockState blockState = alertNotificationSound.resolveIBlockState(count, count2, count3);
         if (blockState.getBlock() == Blocks.block81) {
            if (blockPlaceData3 == null || count == count10 && count3 == count12) {
               return blockPlaceData4 == null
                     || blockPlaceData4.indexOffset2 == count2
                     || alertNotificationSound.resolveIBlockState(count10, count11, count12).getBlock() == Blocks.block81
                     || alertNotificationSound.resolveIBlockState(blockPlaceData4.indexOffset1, blockPlaceData4.indexOffset2, blockPlaceData4.indexOffset).getBlock() != Blocks.block81
                        && alertNotificationSound.resolveIBlockState(blockPlaceData4.indexOffset1, blockPlaceData4.indexOffset2 - 1, blockPlaceData4.indexOffset).getBlock() != Blocks.block81
                  ? 0.36
                  : -1.0;
            }

            Vec3i vec = new Vec3i(count10 - count, 0, count12 - count3);
            if (((EnumFacing)blockState.getComparable(BlockLadder.autoWalkPath)).getVec3i().computeCount(vec) == 0) {
               return offset;
            }
         }

         if (!this.enabled3) {
            boolean flag3 = false;
            if (WorldHelper.matchesBlockByBlock(blockState.getBlock())) {
               flag3 = true;
               offset = 0.4;
            } else if (WorldHelper.matchesBlock(blockState.getBlock())) {
               offset *= 0.001;
               flag3 = true;
            }

            if (blockPlaceData3 == null) {
               return offset;
            } else {
               this.recordNode.dataRef = false;
               boolean enabled6 = this.enabled2;
               boolean enabled7 = this.enabled5;
               RecordNode recordNode5 = this.recordNode;
               RecordNode recordNode6 = this.recordNode3;
               if (!WorldHelper.matchesWorld(minecraft.theWorld, count, count2, count3, blockState, alertNotificationSound, recordNode6, recordNode5, count10, count11, count12, enabled7, enabled6, flag2)) {
                  return -1.0;
               } else {
                  boolean flag4 = false;
                  if (blockState.getBlock() == Blocks.web || alertNotificationSound.resolveIBlockState(count, count2 + 1, count3).getBlock() == Blocks.web) {
                     offset *= 0.11;
                     flag4 = true;
                  }

                  if (count2 < count11) {
                     if (!flag3) {
                        boolean enabled8 = this.enabled2;
                        boolean enabled9 = this.enabled5;
                        if (WorldHelper.matchesIBlockAccess(minecraft.theWorld, count, count2, count3, enabled9, enabled8, blockState)) {
                           return -1.0;
                        }
                     }

                     return offset;
                  } else {
                     IBlockState blockState2 = alertNotificationSound.resolveIBlockState(count, count2 - 1, count3);
                     if (!flag2 && blockState2.getBlock() == Blocks.block81) {
                        return offset;
                     } else {
                        if (blockState2.getBlock() == Blocks.block71) {
                           offset *= 0.27;
                        }

                        if (blockState2.getBlock() == Blocks.block126) {
                           offset *= 0.5;
                        }

                        if (blockState2.getBlock() == Blocks.blockCactus) {
                           offset *= 0.9;
                        }

                        boolean flag5 = count2 > count11;
                        boolean flag6 = blockPlaceData4 != null && count11 > blockPlaceData4.indexOffset2;
                        if (flag5 && alertNotificationSound.resolveIBlockState(count, count2 + 1, count3).getBlock().processAction17()) {
                           return -1.0;
                        } else if (flag5 && flag4) {
                           return -1.0;
                        } else if (!flag5
                           || !flag6
                           || alertNotificationSound.resolveIBlockState(count, count2 - 2, count3).getBlock() == Blocks.block81
                           || flag3 && WorldHelper.matchesBlockByBlock(blockState2.getBlock())) {
                           if (flag5) {
                              offset = 0.8;
                           }

                           AlertNotificationSound dataRef = (AlertNotificationSound)this.recordNode4.dataRef;
                           if (dataRef == null) {
                              dataRef = (AlertNotificationSound)(this.recordNode4.dataRef = minecraft.theWorld.getLoggerEntry(count10, count12));
                           }

                           boolean flag7;
                           IBlockState blockState3;
                           IBlockState blockState4 = null;
                           blockState3 = dataRef.resolveIBlockState(count10, count11 - 1, count12);
                           flag7 = false;
                           int count13 = count11 - 1;
                           short shortValue = (short)0;
                           short shortValue2 = (short)3269;
                           boolean enabled10 = this.enabled2;
                           boolean enabled11 = this.enabled5;
                           int count14 = count13;
                           checkBlock:
                           if (!WorldHelper.matchesIBlockAccess(minecraft.theWorld, count10, count14, count12, enabled11, enabled10, blockState3)) {
                              count13 = count2 - 1;
                              shortValue = (short)0;
                              shortValue2 = (short)3269;
                              boolean enabled12 = this.enabled2;
                              boolean enabled13 = this.enabled5;
                              int count15 = count13;
                              if (!WorldHelper.matchesIBlockAccess(minecraft.theWorld, count, count15, count3, enabled13, enabled12, blockState2)) {
                                 return -1.0;
                              }

                              searchBlock: {
                                 if (blockPlaceData4 != null) {
                                    WorldClient world = minecraft.theWorld;
                                    int indexOffset = blockPlaceData4.indexOffset1;
                                    count13 = blockPlaceData4.indexOffset2 - 1;
                                    int indexOffset2 = blockPlaceData4.indexOffset;
                                    boolean enabled14 = this.enabled5;
                                    boolean enabled15 = this.enabled2;
                                    IBlockState blockState5 = blockState4 = minecraft.theWorld.getIBlockState(blockPlaceData4.indexOffset1, blockPlaceData4.indexOffset2 - 1, blockPlaceData4.indexOffset);
                                    boolean flag8 = enabled15;
                                    boolean flag9 = enabled14;
                                    int count16 = indexOffset2;
                                    int count17 = count13;
                                    int count18 = indexOffset;
                                    if (!WorldHelper.matchesIBlockAccess(world, count18, count17, count16, flag9, flag8, blockState5)) {
                                       break searchBlock;
                                    }
                                 }

                                 if (flag6) {
                                    double offset2 = (double)count2;
                                    int count19 = count2 - 1;
                                    double offset3 = offset2 + this.computeValue(blockState2, count, count19, count3);
                                    double indexOffset3 = (double)blockPlaceData4.indexOffset2;
                                    int count20 = blockPlaceData4.indexOffset2 - 1;
                                    int indexOffset4 = blockPlaceData4.indexOffset;
                                    int count21 = count20;
                                    int indexOffset5 = blockPlaceData4.indexOffset1;
                                    if (offset3 - (indexOffset3 + this.computeValue(blockState4, indexOffset5, count21, indexOffset4)) <= 1.0) {
                                       break checkBlock;
                                    }
                                 }
                              }

                              guardBlock: {
                                 if (!flag2 && !flag5) {
                                    WorldClient world2 = minecraft.theWorld;
                                    count13 = count11 - 2;
                                    boolean enabled16 = this.enabled5;
                                    boolean enabled17 = this.enabled2;
                                    IBlockState blockState6;
                                    IBlockState blockState7 = blockState6 = dataRef.resolveIBlockState(count10, count11 - 2, count12);
                                    boolean flag10 = enabled17;
                                    boolean flag11 = enabled16;
                                    int count22 = count13;
                                    if (WorldHelper.matchesIBlockAccess(world2, count10, count22, count12, flag11, flag10, blockState7)) {
                                       int count23 = count11 - 2;
                                       if (this.computeValue(blockState6, count10, count23, count12) >= 1.0
                                          && blockState3.getBlock() != Blocks.web
                                          && blockState3.getBlock().getMaterial() != Material.material29) {
                                          flag = true;
                                          break guardBlock;
                                       }
                                    }
                                 }

                                 flag = false;
                              }

                              flag7 = flag;
                              if (!flag) {
                                 return -1.0;
                              }
                           }

                           if (count11 == count2 && (this.state3 || this.coordOffset2 < 255.0)) {
                              int count24 = (int)Math.max((double)(count2 + 1), Math.min(255.0, this.coordOffset2));
                              byte byteValue = 0;

                              for (int byteValue2 = 0; byteValue2 < count24; byteValue2 += 1) {
                                 IBlockState blockState8 = alertNotificationSound.resolveIBlockState(count, count2 - byteValue2, count3);
                                 Block block = blockState8.getBlock();
                                 if (WorldHelper.matchesBlockByBlock(block) || block == Blocks.web || block == Blocks.block71) {
                                    break;
                                 }

                                 count13 = count2 - byteValue2;
                                 shortValue = (short)0;
                                 shortValue2 = (short)3269;
                                 boolean enabled18 = this.enabled2;
                                 boolean enabled19 = this.enabled5;
                                 int count25 = count13;
                                 if (WorldHelper.matchesIBlockAccess(minecraft.theWorld, count, count25, count3, enabled19, enabled18, blockState8)) {
                                    byteValue = (byte)byteValue2;
                                    break;
                                 }
                              }

                              if ((double)byteValue > this.coordOffset2) {
                                 return -1.0;
                              }

                              PotionEffect potionEffect = minecraft.thePlayer.getPotionEffect(Potion.potion9);
                              int count26 = 0;
                              if (potionEffect != null) {
                                 count26 = potionEffect.getIndexOffset2() + 1;
                              }

                              if ((double)byteValue - 4.07 - (double)count26 > 0.0) {
                                 offset = this.coordOffset4 / (double)MathHelper.truncateDoubleToInt((double)byteValue - 4.07 - (double)count26);
                              }
                           }

                           if (flag7) {
                              offset *= 0.25;
                           }

                           if (flag2) {
                              boolean enabled20 = this.enabled2;
                              boolean enabled21 = this.enabled5;
                              RecordNode recordNode7 = this.recordNode;
                              boolean flag12 = matchesRecordNode(this.recordNode3, recordNode7, count10, count11, count12, enabled21, enabled20, blockState3);
                              WorldClient world3 = minecraft.theWorld;
                              int count27 = MathHelper.ceiling_double_int((double)(blockPlaceData3.indexOffset1 + (count - blockPlaceData3.indexOffset1)));
                              count13 = MathHelper.ceiling_double_int((double)blockPlaceData3.indexOffset2);
                              int count28 = MathHelper.ceiling_double_int((double)blockPlaceData3.indexOffset);
                              boolean flag13 = !flag3;
                              boolean flag14 = true;
                              boolean enabled22 = this.enabled5;
                              int count29 = count28;
                              int count30 = count13;
                              int count31 = count27;
                              WorldClient world4 = world3;
                              short shortValue3 = (short)25698;
                              short shortValue4 = (short)-27570;
                              short shortValue5 = shortValue3;
                              if (WorldHelper.matchesWorld(
                                 30814, world4, count31, count30, count29, count10, count11, shortValue5, count12, flag12, enabled22, flag14, shortValue4, flag13
                              )) {
                                 WorldClient world5 = minecraft.theWorld;
                                 int count32 = MathHelper.ceiling_double_int((double)blockPlaceData3.indexOffset1);
                                 count13 = MathHelper.ceiling_double_int((double)blockPlaceData3.indexOffset2);
                                 count28 = MathHelper.ceiling_double_int((double)(blockPlaceData3.indexOffset + (count3 - blockPlaceData3.indexOffset)));
                                 boolean flag15 = !flag3;
                                 boolean flag16 = true;
                                 boolean enabled23 = this.enabled5;
                                 int count33 = count28;
                                 int count34 = count13;
                                 int count35 = count32;
                                 WorldClient world6 = world5;
                                 shortValue3 = (short)25698;
                                 short shortValue6 = (short)-27570;
                                 short shortValue7 = shortValue3;
                                 if (WorldHelper.matchesWorld(
                                    30814, world6, count35, count34, count33, count10, count11, shortValue7, count12, flag12, enabled23, flag16, shortValue6, flag15
                                 )) {
                                    return offset;
                                 }
                              }

                              return -1.0;
                           } else {
                              return offset;
                           }
                        } else {
                           return -1.0;
                        }
                     }
                  }
               }
            }
         } else {
            return !blockState.getBlock().processAction17() && !alertNotificationSound.resolveIBlockState(count, count2 + 1, count3).getBlock().processAction17() ? 1.0 : -1.0;
         }
      }
   }

   public ArrayList resolveArrayList(ArrayList list, boolean flag, boolean flag2) {
      if (list.isEmpty()) {
         return list;
      } else {
         ArrayList list2 = new ArrayList();
         Vec3 vec3 = ((PathNode)list.get(0)).getBlockPlaceData().getVec3();
         list2.add(list.get(0));
         int count = 1;

         while (list.size() > count + 1) {
            int count2 = count + 1;
            count = this.computeCount(list, vec3, count2, flag, flag2);
            vec3 = ((PathNode)list.get(count)).getBlockPlaceData().getVec3();
            list2.add(list.get(count));
         }

         return list2;
      }
   }

   private int computeCount(ArrayList list, Vec3 vec, int count, boolean flag, boolean flag2) {
      Vec3 vec2 = new Vec3(vec.xCoord + 0.5, vec.yCoord, vec.zCoord + 0.5);
      int count2 = count;

      while (true) {
         if (count2 >= list.size()) {
            return list.size() - 1;
         }

         Vec3 vec3 = ((PathNode)list.get(count2)).getBlockPlaceData().getVec3();
         Vec3 vec32 = vec3.getVec312(0.5, 0.0, 0.5);
         if (vec32.yCoord != vec.yCoord) {
            break;
         }

         int count3 = count2 - count;
         if (!this.matchesVec3(vec2, vec32, count3) || this.matchesVec3(vec, vec3)) {
            break;
         }

         count2++;
      }

      return count == count2 ? count : count2 - 1;
   }

   private boolean matchesVec3(Vec3 vec, Vec3 vec2, int count) {
      if (count == 0) {
         return true;
      } else {
         for (int index = 0; index <= count; index++) {
            double offset = (double)index / (double)count;
            int count4 = MathHelper.ceiling_double_int(PlayerUtil.getDouble(vec.xCoord, vec2.xCoord, offset));
            int count5 = MathHelper.ceiling_double_int(PlayerUtil.getDouble(vec.yCoord, vec2.yCoord, offset)) - 1;
            int count6 = MathHelper.ceiling_double_int(PlayerUtil.getDouble(vec.zCoord, vec2.zCoord, offset));
            IBlockState iBlockState = minecraft.theWorld.getIBlockState(count4, count5, count6);
            boolean enabled6 = this.enabled2;
            boolean enabled7 = this.enabled5;
            if (!WorldHelper.matchesIBlockAccess(minecraft.theWorld, count4, count5, count6, enabled7, enabled6, iBlockState)
               || iBlockState.getBlock() == Blocks.block126
               || iBlockState.getBlock() == Blocks.block71) {
               return false;
            }
         }

         return true;
      }
   }

   private boolean checkIndex(int count, int count2) {
      return minecraft.theWorld.getInternalListEntry() == null
         ? true
         : this.value3 <= (double)count && this.value2 >= (double)count && this.value <= (double)count2 && this.value4 >= (double)count2;
   }

   public ArrayList resolveArrayList(PathNode pathNode) {
      ArrayList list = new ArrayList();
      list.add(pathNode);

      for (PathNode pathNode2 = pathNode.getPathNode(); pathNode2 != null; pathNode2 = pathNode2.getPathNode()) {
         list.add(pathNode2);
      }

      return list;
   }

   public boolean matchesVec3(Vec3 vec, Vec3 vec2) {
      Vec3[][] vecMatrix = new Vec3[6][];
      Vec3 vec3 = vec2.getVec311(vec).addVector(0.0, 1.0, 0.0).normalize().getVec3(0.6);
      vecMatrix[0] = new Vec3[]{vec, vec2};
      vecMatrix[1] = new Vec3[]{vec.getVec312(0.0, 1.5, 0.0), vec2.getVec312(0.0, 1.5, 0.0)};
      vecMatrix[2] = new Vec3[]{vecMatrix[0][0].getVec36(vec3), vecMatrix[0][1].getVec36(vec3)};
      vecMatrix[3] = new Vec3[]{vecMatrix[1][0].getVec36(vec3), vecMatrix[1][1].getVec36(vec3)};
      vecMatrix[4] = new Vec3[]{vecMatrix[0][0].getVec311(vec3), vecMatrix[0][1].getVec311(vec3)};
      vecMatrix[5] = new Vec3[]{vecMatrix[1][0].getVec311(vec3), vecMatrix[1][1].getVec311(vec3)};

      for (Vec3[] vecArray : vecMatrix) {
         Vec3 vec4 = vecArray[0];
         Vec3 vec5 = vecArray[1];
         MovingObjectPosition movingObjectPosition = minecraft.theWorld
            .getMovingObjectPosition5(
               vec4.xCoord + 0.5, vec4.yCoord, vec4.zCoord + 0.5, vec5.xCoord + 0.5, vec5.yCoord, vec5.zCoord + 0.5, false, true, false
            );
         if (movingObjectPosition != null && movingObjectPosition.typeOfHit == MovingObjectType.BLOCK) {
            return true;
         }

         MovingObjectPosition hitResult = minecraft.theWorld
            .resolveMovingObjectPosition(
               vec4.xCoord + 0.5, vec4.yCoord, vec4.zCoord + 0.5, vec5.xCoord + 0.5, vec5.yCoord, vec5.zCoord + 0.5, true, false, false, true
            );
         if (hitResult != null && hitResult.typeOfHit == MovingObjectType.BLOCK) {
            IBlockState blockState = minecraft.theWorld.getBlockState(hitResult.getBlockPos());
            if (blockState.getBlock() == Blocks.web || blockState.getBlock() instanceof BlockLiquid) {
               return true;
            }
         }
      }

      return false;
   }

   private void applyPathNode(PathNode pathNode, int count, int count2, int count3, boolean flag) {
      if (count2 >= 0 && count2 <= 255 && !this.enabled) {
         long value6;
         PathNode pathNode2 = (PathNode)this.long2ObjectOpenHashMap2.get(value6 = computeTimestamp(count, count2, count3));
         if (pathNode2 != null) {
            double offset = pathNode.getDouble() + pathNode.getBlockPlaceData().computeValue(count, count2, count3) / pathNode2.value;
            if (pathNode2.getPathNode() != null && pathNode2.getDouble() > offset) {
               if (this.long2ObjectOpenHashMap.containsKey(value6)) {
                  return;
               }

               BlockPlaceData blockPlaceData3 = pathNode.getPathNode() != null ? pathNode.getPathNode().getBlockPlaceData() : null;
               BlockPlaceData blockPlaceData4 = pathNode.getBlockPlaceData();
               double offset2;
               if ((offset2 = this.computeValue(count, count2, count3, blockPlaceData4, blockPlaceData3, flag)) > 0.0
                  && pathNode2.getDouble() > pathNode.getDouble() + pathNode.getBlockPlaceData().computeValue(count, count2, count3) / offset2) {
                  pathNode2.setPathNode(pathNode);
                  pathNode2.applyDouble(offset);
                  pathNode2.value = offset2;
                  if (this.priorityQueue.remove(pathNode2)) {
                     this.priorityQueue.add(pathNode2);
                  } else {
                     pathNode2.enabled = true;
                     LinkedList list = new LinkedList();
                     applyList(list, pathNode2);
                     Iterator iterator = list.iterator();

                     while (iterator.hasNext()) {
                        ((PathNode)iterator.next()).updateState();
                     }

                     for (PathNode pathNode3 : (Iterable<PathNode>)(Iterable)(list)) {
                        if (this.priorityQueue.remove(pathNode3)) {
                           this.priorityQueue.add(pathNode3);
                        }

                        pathNode3.enabled = false;
                     }

                     pathNode2.enabled = false;
                  }
               }
            }
         } else {
            this.resolvePathNode(pathNode, count, count2, count3, value6, flag);
         }
      }
   }

   private static double computeValue(double posX, double posY, double posZ) {
      return posX + (posY - posX) * posZ;
   }

   public ArrayList getArrayList2(boolean flag, long value6) {
      if (CosmeticsPayload.checkCondition(6)) {
         return null;
      } else if (!minecraft.theWorld.checkIndex(this.blockPlaceData.indexOffset1 >> 4, this.blockPlaceData.indexOffset >> 4, false)) {
         return new ArrayList();
      } else {
         long timestamp = System.currentTimeMillis();
         int indexOffset = this.blockPlaceData2.indexOffset1;
         int indexOffset2 = this.blockPlaceData2.indexOffset2;
         int indexOffset3 = this.blockPlaceData2.indexOffset;
         long value7 = computeTimestamp(this.blockPlaceData2.indexOffset1, this.blockPlaceData2.indexOffset2, this.blockPlaceData2.indexOffset);
         int count = indexOffset3;
         int count2 = indexOffset2;
         int count3 = indexOffset;
         Object entry = null;
         this.resolvePathNode((PathNode)entry, count3, count2, count, value7, false);

         while (!flag || System.currentTimeMillis() - timestamp <= value6) {
            if (this.priorityQueue.isEmpty()) {
               Client.getClient().getClass();
               return new ArrayList();
            }

            PathNode pathNode = (PathNode)this.priorityQueue.poll();
            if (this.value5 > pathNode.getValue4()) {
               this.value5 = pathNode.getValue4();
            }

            if (pathNode.getValue4() < this.coordOffset) {
               Client.getClient().getClass();
               ArrayList list = this.resolveArrayList(pathNode);
               if (this.state1) {
                  boolean enabled6 = this.enabled5;
                  boolean enabled7 = this.enabled2;
                  list = this.resolveArrayList(list, enabled7, enabled6);
               }

               return list;
            }

            this.applyPathNode(pathNode);
         }

         return null;
      }
   }

   private double computeValue(IBlockState blockState, int count, int count2, int count3) {
      blockState.getBlock().processAction(minecraft.theWorld, count, count2, count3);
      return blockState.getBlock().getCoordOffset6();
   }

   public static long computeTimestamp(int count, int count2, int count3) {
      long value6 = (long)count + 30000000L;
      long value7 = (long)count2;
      long value8 = (long)count3 + 30000000L;
      return value6 & 67108863L | (value7 & 255L) << 26 | (value8 & 67108863L) << 34;
   }

   private void applyPathNode(PathNode pathNode, int count, int count2, int count3) {
      this.applyPathNode(pathNode, count, count2, count3, false);
   }
}
