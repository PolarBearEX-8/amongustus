package net.augustus.utils.pathfinding;

import net.augustus.Augustus;
import net.augustus.events.listeners.RenderListener;
import net.augustus.render.RenderUtil;
import net.augustus.utils.math.MathStatisticsUtil;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import net.augustus.utils.interfaces.MC;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLadder;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.init.Blocks;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.util.Vec3i;
import net.minecraft.world.border.WorldBorder;
import net.minecraft.world.chunk.Chunk;
import net.optifine.config.Config;
import net.optifine.shaders.Shaders;
import org.lwjgl.opengl.GL11;

public class AStarPathFinder implements MC, RenderListener {
   public boolean allowDiagonals;
   public PathfinderResultRef groundCheckCached;
   public double fallDamagePenalty;
   public double maxFallDistance;
   public final boolean allowSwimming;
   public PathfinderResultRef groundCheckResult;
   private double borderMinX;
   public Long2ObjectOpenHashMap allNodes;
   public final PathBlockPos startPos;
   public double goalTolerance;
   private boolean chunkEmptyAbort;
   private PathfinderResultRef cachedChunk;
   public final boolean walkOnWater;
   public final PathBlockPos goalPos;
   private double borderMaxX;
   private double borderMinZ;
   public boolean avoidFallDamage;
   public boolean smoothPath;
   private double borderMaxZ;
   public final PriorityQueue openSet = new PriorityQueue();
   private ArrayList debugBoxes;
   public Long2ObjectOpenHashMap closedSet;
   private PathfinderResultRef parentChunkCache;
   private final boolean flyMode;
   public double closestHeuristic;
   private boolean useCachedChunk;

   private boolean hasSolidFloorAlongSegment(Vec3 vec, Vec3 vec2, int n) {
      if (n == 0) {
         return true;
      } else {
         for (int i = 0; i <= n; i++) {
            double d = (double)i / (double)n;
            int n2 = MathHelper.floor_double(MathStatisticsUtil.lerp(vec.xCoord, vec2.xCoord, d));
            int n3 = MathHelper.floor_double(MathStatisticsUtil.lerp(vec.yCoord, vec2.yCoord, d)) - 1;
            int n4 = MathHelper.floor_double(MathStatisticsUtil.lerp(vec.zCoord, vec2.zCoord, d));
            IBlockState blockState2 = mc.theWorld.getBlockState2(n2, n3, n4);
            boolean allowSwimming2 = this.allowSwimming;
            boolean walkOnWater2 = this.walkOnWater;
            if (!PathBlockValidator.isStandableBlock(mc.theWorld, n2, n3, n4, walkOnWater2, allowSwimming2, blockState2)
               || blockState2.getBlock() == Blocks.soul_sand
               || blockState2.getBlock() == Blocks.slime_block) {
               return false;
            }
         }

         return true;
      }
   }

   private void evaluateNeighbor(PathNode pathNode, int n, int n2, int n3, boolean flag) {
      if (n2 >= 0 && n2 <= 255 && !this.chunkEmptyAbort) {
         long l;
         PathNode pathNode2 = (PathNode)this.allNodes.get(l = packBlockKey(n, n2, n3));
         if (pathNode2 == null) {
            this.createAndQueueNode(pathNode, n, n2, n3, l, flag);
         } else {
            double d = pathNode.getGCost() + pathNode.getPos().distanceTo(n, n2, n3) / pathNode2.speedMultiplier;
            if (pathNode2.getParent() != null) {
               if (pathNode2.getGCost() > d) {
                  if (this.closedSet.containsKey(l)) {
                     return;
                  }

                  PathBlockPos pos = pathNode.getPos();
                  PathBlockPos pathBlockPos;
                  if (pathNode.getParent() != null) {
                     pathBlockPos = pathNode.getParent().getPos();
                  } else {
                     pathBlockPos = null;
                  }

                  PathBlockPos pathBlockPos2 = pathBlockPos;
                  double d2;
                  if ((d2 = this.evaluateMoveSpeed(n, n2, n3, pos, pathBlockPos2, flag)) > 0.0
                     && pathNode2.getGCost() > pathNode.getGCost() + pathNode.getPos().distanceTo(n, n2, n3) / d2) {
                     pathNode2.setParent(pathNode);
                     pathNode2.setGCost(d);
                     pathNode2.speedMultiplier = d2;
                     if (this.openSet.remove(pathNode2)) {
                        this.openSet.add(pathNode2);
                     } else {
                        pathNode2.recalculated = true;
                        LinkedList list = new LinkedList();
                        collectSubtreeNodes(list, pathNode2);

                        for (PathNode pathNode3 : (Iterable<PathNode>)(list)) {
                           pathNode3.recalculateCost();
                        }

                        for (PathNode pathNode4 : (Iterable<PathNode>)(list)) {
                           if (this.openSet.remove(pathNode4)) {
                              this.openSet.add(pathNode4);
                           }

                           pathNode4.recalculated = false;
                        }

                        pathNode2.recalculated = false;
                     }
                  }
               }
            }
         }
      }
   }

   private static double lerp(double start, double end, double progress) {
      return start + (end - start) * progress;
   }

   private int findFurthestVisibleIndex(ArrayList list, Vec3 vec, int n, boolean flag, boolean flag2) {
      Vec3 vec2 = new Vec3(vec.xCoord + 0.5, vec.yCoord, vec.zCoord + 0.5);
      int n2 = n;

      while (true) {
         if (n2 >= list.size()) {
            return list.size() - 1;
         }

         Vec3 vec3 = ((PathNode)list.get(n2)).getPos().toVec3();
         Vec3 vec4 = vec3.addVector(0.5, 0.0, 0.5);
         if (vec4.yCoord != vec.yCoord) {
            break;
         }

         if (!this.hasSolidFloorAlongSegment(vec2, vec4, n2 - n)) {
            break;
         }

         if (this.isSegmentObstructed(vec, vec3)) {
            break;
         }

         n2++;
      }

      return n == n2 ? n : n2 - 1;
   }

   public AStarPathFinder(PathBlockPos pathBlockPos, PathBlockPos pathBlockPos2, boolean flag, boolean flag2, boolean flag3) {
      this.allowDiagonals = true;
      this.closestHeuristic = 1.7976931348623157E308;
      this.goalTolerance = 1.0;
      this.debugBoxes = new ArrayList();
      this.smoothPath = false;
      this.avoidFallDamage = false;
      this.fallDamagePenalty = 0.1;
      this.maxFallDistance = 1.0 / 0.0;
      this.cachedChunk = new PathfinderResultRef(null);
      this.parentChunkCache = new PathfinderResultRef(null);
      this.useCachedChunk = false;
      this.borderMinX = -3.0E7;
      this.borderMaxX = 3.0E7;
      this.borderMinZ = -3.0E7;
      this.borderMaxZ = 3.0E7;
      this.groundCheckResult = new PathfinderResultRef(false);
      this.groundCheckCached = new PathfinderResultRef(false);
      this.chunkEmptyAbort = false;
      Block block = mc.theWorld.getBlockState2(pathBlockPos.x, pathBlockPos.y, pathBlockPos.z).getBlock();
      if (block != Blocks.ladder && block.hasCollisionBox()) {
         pathBlockPos = new PathBlockPos(pathBlockPos.x, pathBlockPos.y + 1, pathBlockPos.z);
      }

      if (mc.theWorld.getBlockState2(pathBlockPos2.x, pathBlockPos2.y, pathBlockPos2.z).getBlock().hasCollisionBox()) {
         pathBlockPos2 = new PathBlockPos(pathBlockPos2.x, pathBlockPos2.y + 1, pathBlockPos2.z);
      }

      if (mc.theWorld.getWorldBorder() != null) {
         WorldBorder worldBorder = mc.theWorld.getWorldBorder();
         this.borderMinX = worldBorder.minX();
         this.borderMinZ = worldBorder.minZ();
         this.borderMaxX = worldBorder.maxX();
         this.borderMaxZ = worldBorder.maxZ();
      }

      this.allNodes = new Long2ObjectOpenHashMap();
      this.closedSet = new Long2ObjectOpenHashMap(Math.min(10, (int)pathBlockPos.distanceTo(pathBlockPos2)));
      this.startPos = pathBlockPos;
      this.goalPos = pathBlockPos2;
      this.walkOnWater = flag;
      this.allowSwimming = flag2;
      this.flyMode = flag3;
   }

   public void onRender3D(float partialTicks) {
      GL11.glPushMatrix();
      GlStateManager.enableBlend();
      GlStateManager.blendFunc(770, 771);
      GL11.glEnable(2848);
      GlStateManager.disableTexture2D();
      GlStateManager.disableAlpha();
      if (Config.isShaderPackLoaded()) {
         Shaders.disableTexture2D();
      }

      GlStateManager.disableCull();
      GlStateManager.disableLighting();
      GlStateManager.color(1.0F, 1.0F, 1.0F, 0.7F);
      GL11.glTranslated(-mc.getRenderManager().getRenderPosX(), -mc.getRenderManager().getRenderPosY(), -mc.getRenderManager().getRenderPosZ());

      for (Object[] array : (Iterable<Object[]>)(this.debugBoxes)) {
         AxisAlignedBB box = (AxisAlignedBB)array[0];
         if (!((box.minX - mc.thePlayer.posX) * (box.minX - mc.thePlayer.posX) + (box.minZ - mc.thePlayer.posZ) * (box.minZ - mc.thePlayer.posZ) > 625.0)) {
            PathBlockPos pathBlockPos = (PathBlockPos)array[1];
            if (pathBlockPos != null) {
               GlStateManager.color(1.0F, 0.0F, 0.0F, 0.7F);
               GL11.glLineWidth(3.0F);
               GL11.glBegin(3);
               GL11.glVertex3d((box.minX + box.maxX) / 2.0, (box.minY + box.maxY) / 2.0, (box.minZ + box.maxZ) / 2.0);
               GL11.glVertex3d((double)pathBlockPos.x + 0.5, (double)pathBlockPos.y + 0.5, (double)pathBlockPos.z + 0.5);
               GL11.glEnd();
               GlStateManager.color(1.0F, 1.0F, 1.0F, 0.7F);
               GL11.glLineWidth(1.0F);
            }

            RenderUtil.drawOutlinedBoundingBox(box);
         }
      }

      RenderUtil.end3DRender();
      GlStateManager.enableAlpha();
      RenderUtil.setGlColor(-1);
      GL11.glPopMatrix();
   }

   private static void collectSubtreeNodes(List list, PathNode pathNode) {
      list.add(pathNode);

      for (PathNode pathNode2 : (Iterable<PathNode>)(pathNode.getChildren())) {
         collectSubtreeNodes(list, pathNode2);
      }
   }

   public ArrayList simplifyPath(ArrayList list, boolean flag, boolean flag2) {
      if (list.isEmpty()) {
         return list;
      } else {
         ArrayList list2 = new ArrayList();
         PathBlockPos pos = ((PathNode)list.get(0)).getPos();
         Vec3 vec = pos.toVec3();
         list2.add(list.get(0));
         int n = 1;

         while (list.size() > n + 1) {
            n = this.findFurthestVisibleIndex(list, vec, n + 1, flag, flag2);
            pos = ((PathNode)list.get(n)).getPos();
            vec = pos.toVec3();
            list2.add(list.get(n));
         }

         return list2;
      }
   }

   public boolean isSegmentObstructed(Vec3 vec, Vec3 vec2) {
      Vec3[][] vecsArr = new Vec3[6][];
      Vec3 vec3 = vec2.subtract(vec).crossProduct(0.0, 1.0, 0.0).normalize().scale(0.6);
      vecsArr[0] = new Vec3[]{vec, vec2};
      vecsArr[1] = new Vec3[]{vec.addVector(0.0, 1.5, 0.0), vec2.addVector(0.0, 1.5, 0.0)};
      vecsArr[2] = new Vec3[]{vecsArr[0][0].add(vec3), vecsArr[0][1].add(vec3)};
      vecsArr[3] = new Vec3[]{vecsArr[1][0].add(vec3), vecsArr[1][1].add(vec3)};
      vecsArr[4] = new Vec3[]{vecsArr[0][0].subtract(vec3), vecsArr[0][1].subtract(vec3)};
      vecsArr[5] = new Vec3[]{vecsArr[1][0].subtract(vec3), vecsArr[1][1].subtract(vec3)};

      for (Vec3[] vecs : vecsArr) {
         Vec3 vec4 = vecs[0];
         Vec3 vec5 = vecs[1];
         MovingObjectPosition hit = mc.theWorld
            .rayTraceBlocksCustom(vec4.xCoord + 0.5, vec4.yCoord, vec4.zCoord + 0.5, vec5.xCoord + 0.5, vec5.yCoord, vec5.zCoord + 0.5, false, true, false);
         if (hit != null && hit.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
            return true;
         }

         hit = mc.theWorld
            .rayTraceBlocksCustom(vec4.xCoord + 0.5, vec4.yCoord, vec4.zCoord + 0.5, vec5.xCoord + 0.5, vec5.yCoord, vec5.zCoord + 0.5, true, false, false, true);
         if (hit != null && hit.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
            IBlockState blockState = mc.theWorld.getBlockState(hit.getBlockPos());
            if (blockState.getBlock() == Blocks.web || blockState.getBlock() instanceof BlockLiquid) {
               return true;
            }
         }
      }

      return false;
   }

   public static long packBlockKey(int n, int n2, int n3) {
      return ((long)n + 30000000L) & 67108863L | (((long)n2) & 255L) << 26 | (((long)n3 + 30000000L) & 67108863L) << 34;
   }

   public void expandNode(PathNode pathNode) {
      this.closedSet.put(pathNode.packedKey, pathNode);
      if ((pathNode.getPos().x & 15) != 15 && (pathNode.getPos().x & 15) != 0 && (pathNode.getPos().z & 15) != 15 && (pathNode.getPos().z & 15) != 0) {
         this.cachedChunk.value = null;
         this.useCachedChunk = true;
      } else {
         this.useCachedChunk = false;
         this.cachedChunk.value = null;
      }

      this.chunkEmptyAbort = false;
      this.parentChunkCache.value = null;
      int n = pathNode.getPos().x;
      int n2 = pathNode.getPos().y;
      int n3 = pathNode.getPos().z;
      this.evaluateCardinalNeighbor(pathNode, n, n2, n3 + 1);
      this.evaluateCardinalNeighbor(pathNode, n, n2, n3 - 1);
      this.evaluateCardinalNeighbor(pathNode, n + 1, n2, n3);
      this.evaluateCardinalNeighbor(pathNode, n - 1, n2, n3);
      if (this.allowDiagonals) {
         int n8 = n + 1;
         int n9 = n3 + 1;
         this.evaluateNeighbor(pathNode, n8, n2, n9, true);
         n8 = n + 1;
         n9 = n3 - 1;
         this.evaluateNeighbor(pathNode, n8, n2, n9, true);
         n8 = n - 1;
         n9 = n3 + 1;
         this.evaluateNeighbor(pathNode, n8, n2, n9, true);
         n8 = n - 1;
         n9 = n3 - 1;
         this.evaluateNeighbor(pathNode, n8, n2, n9, true);
      }

      this.useCachedChunk = true;
      this.evaluateCardinalNeighbor(pathNode, n, n2 + 1, n3);
      this.evaluateCardinalNeighbor(pathNode, n, n2 - 1, n3);
   }

   private void evaluateCardinalNeighbor(PathNode pathNode, int n, int n2, int n3) {
      this.evaluateNeighbor(pathNode, n, n2, n3, false);
   }

   public ArrayList reconstructPath(PathNode pathNode) {
      ArrayList list = new ArrayList();
      list.add(pathNode);

      for (PathNode i = pathNode.getParent(); i != null; i = i.getParent()) {
         list.add(i);
      }

      return list;
   }

   private static AxisAlignedBB createPlayerBoundingBox(Vec3 vec) {
      float f = 0.3F;
      return new AxisAlignedBB(
         vec.xCoord - (double)f,
         vec.yCoord - 0.10000000149011612,
         vec.zCoord - (double)f,
         vec.xCoord + (double)f,
         vec.yCoord + 1.7999999523162842,
         vec.zCoord + (double)f
      );
   }

   public ArrayList computePath(boolean flag, long l) {
      if (!mc.theWorld.isChunkLoaded(this.goalPos.x >> 4, this.goalPos.z >> 4, false)) {
         return new ArrayList();
      } else {
         long currentTimeMs = System.currentTimeMillis();
         int n = this.startPos.x;
         int n2 = this.startPos.y;
         int n3 = this.startPos.z;
         long l3 = packBlockKey(this.startPos.x, this.startPos.y, this.startPos.z);
         this.createAndQueueNode((PathNode)null, n, n2, n3, l3, false);

         while (!flag || System.currentTimeMillis() - currentTimeMs <= l) {
            if (this.openSet.isEmpty()) {
               return new ArrayList();
            }

            PathNode pathNode = (PathNode)this.openSet.poll();
            if (this.closestHeuristic > pathNode.getHCost()) {
               this.closestHeuristic = pathNode.getHCost();
            }

            if (pathNode.getHCost() < this.goalTolerance) {
               ArrayList list = this.reconstructPath(pathNode);
               if (this.smoothPath) {
                  boolean walkOnWater2 = this.walkOnWater;
                  list = this.simplifyPath(list, this.allowSwimming, walkOnWater2);
               }

               return list;
            }

            this.expandNode(pathNode);
         }

         return null;
      }
   }

   private double getBlockTopY(IBlockState state, int n, int n2, int n3) {
      state.getBlock().setBlockBoundsBasedOnState(mc.theWorld, n, n2, n3);
      return state.getBlock().getBlockBoundsMaxY();
   }

   private boolean isWithinWorldBorder(int n, int n2) {
      return mc.theWorld.getWorldBorder() == null
         ? true
         : this.borderMinX <= (double)n && this.borderMaxX >= (double)n && this.borderMinZ <= (double)n2 && this.borderMaxZ >= (double)n2;
   }

   private double evaluateMoveSpeed(int n, int n2, int n3, PathBlockPos pathBlockPos, PathBlockPos pathBlockPos2, boolean flag) {
      if (!this.isWithinWorldBorder(n, n3)) {
         return -1.0;
      } else {
         int n4 = 2147483647;
         int n5 = 0;
         int n6 = 0;
         double d = 1.0;
         if (pathBlockPos != null) {
            n4 = pathBlockPos.x;
            n5 = pathBlockPos.y;
            n6 = pathBlockPos.z;
         }

         Chunk chunk;
         if (this.useCachedChunk) {
            chunk = (Chunk)this.cachedChunk.value;
            if (chunk == null) {
               this.cachedChunk.value = chunk = mc.theWorld.getChunkFromBlockCoords(n, n3);
               if (chunk.isEmpty()) {
                  this.chunkEmptyAbort = true;
                  return -1.0;
               }
            }
         } else {
            chunk = mc.theWorld.getChunkFromBlockCoords(n, n3);
            if (chunk.isEmpty()) {
               return -1.0;
            }
         }

         IBlockState blockState = chunk.getBlockState(n, n2, n3);
         if (blockState.getBlock() == Blocks.ladder) {
            if (pathBlockPos == null || n == n4 && n3 == n6) {
               return pathBlockPos2 == null
                     || pathBlockPos2.y == n2
                     || chunk.getBlockState(n4, n5, n6).getBlock() == Blocks.ladder
                     || chunk.getBlockState(pathBlockPos2.x, pathBlockPos2.y, pathBlockPos2.z).getBlock() != Blocks.ladder
                        && chunk.getBlockState(pathBlockPos2.x, pathBlockPos2.y - 1, pathBlockPos2.z).getBlock() != Blocks.ladder
                  ? 0.36
                  : -1.0;
            }

            Vec3i vec = new Vec3i(n4 - n, 0, n6 - n3);
            if (((EnumFacing)blockState.getValue(BlockLadder.FACING)).getDirectionVec().compareTo(vec) == 0) {
               return d;
            }
         }

         if (!this.flyMode) {
            boolean flag2 = false;
            if (PathBlockValidator.isWater(blockState.getBlock())) {
               flag2 = true;
               d = 0.4;
            } else if (PathBlockValidator.isLava(blockState.getBlock())) {
               d *= 0.001;
               flag2 = true;
            }

            if (pathBlockPos == null) {
               return d;
            } else {
               this.groundCheckCached.value = false;
               boolean allowSwimming2 = this.allowSwimming;
               boolean walkOnWater2 = this.walkOnWater;
               PathfinderResultRef groundCheckCached2 = this.groundCheckCached;
               PathfinderResultRef groundCheckResult2 = this.groundCheckResult;
               if (!PathBlockValidator.canStandAt(mc.theWorld, n, n2, n3, blockState, chunk, groundCheckResult2, groundCheckCached2, n4, n5, n6, walkOnWater2, allowSwimming2, flag)) {
                  return -1.0;
               } else {
                  boolean flag3 = false;
                  if (blockState.getBlock() == Blocks.web || chunk.getBlockState(n, n2 + 1, n3).getBlock() == Blocks.web) {
                     d *= 0.11;
                     flag3 = true;
                  }

                  if (n2 < n5) {
                     if (!flag2) {
                        boolean allowSwimming3 = this.allowSwimming;
                        boolean walkOnWater3 = this.walkOnWater;
                        if (PathBlockValidator.isStandableBlock(mc.theWorld, n, n2, n3, walkOnWater3, allowSwimming3, blockState)) {
                           return -1.0;
                        }
                     }

                     return d;
                  } else {
                     IBlockState blockState2 = chunk.getBlockState(n, n2 - 1, n3);
                     if (!flag && blockState2.getBlock() == Blocks.ladder) {
                        return d;
                     } else {
                        if (blockState2.getBlock() == Blocks.slime_block) {
                           d *= 0.27;
                        }

                        if (blockState2.getBlock() == Blocks.soul_sand) {
                           d *= 0.5;
                        }

                        if (blockState2.getBlock() == Blocks.cactus) {
                           d *= 0.9;
                        }

                        boolean flag4 = n2 > n5;
                        boolean flag5 = pathBlockPos2 != null && n5 > pathBlockPos2.y;
                        if (flag4 && chunk.getBlockState(n, n2 + 1, n3).getBlock().hasCollisionBox()) {
                           return -1.0;
                        } else if (flag4 && flag3) {
                           return -1.0;
                        } else if (!flag4
                           || !flag5
                           || chunk.getBlockState(n, n2 - 2, n3).getBlock() == Blocks.ladder
                           || flag2 && PathBlockValidator.isWater(blockState2.getBlock())) {
                           if (flag4) {
                              d = 0.8;
                           }

                           Chunk value2 = (Chunk)this.parentChunkCache.value;
                           if (value2 == null) {
                              value2 = (Chunk)(this.parentChunkCache.value = mc.theWorld.getChunkFromBlockCoords(n4, n6));
                           }

                           IBlockState state;
                           boolean flag6;
                           IBlockState state2 = null;
                           state = value2.getBlockState(n4, n5 - 1, n6);
                           flag6 = false;
                           int n7 = n5 - 1;
                           boolean allowSwimming4 = this.allowSwimming;
                           boolean walkOnWater4 = this.walkOnWater;
                           int n8 = n7;
                           if (!PathBlockValidator.isStandableBlock(mc.theWorld, n4, n8, n6, walkOnWater4, allowSwimming4, state)) {
                              n7 = n2 - 1;
                              if (!PathBlockValidator.isStandableBlock(mc.theWorld, n, n7, n3, this.walkOnWater, this.allowSwimming, blockState2)) {
                                 return -1.0;
                              }

                              boolean skipStepDownCheck = false;
                              boolean prevBlockStandable = pathBlockPos2 == null
                                 || PathBlockValidator.isStandableBlock(
                                    mc.theWorld,
                                    pathBlockPos2.x,
                                    pathBlockPos2.y - 1,
                                    pathBlockPos2.z,
                                    this.walkOnWater,
                                    this.allowSwimming,
                                    state2 = mc.theWorld.getBlockState2(pathBlockPos2.x, pathBlockPos2.y - 1, pathBlockPos2.z)
                                 );
                              if (prevBlockStandable && flag5) {
                                 double d2 = (double)n2 + this.getBlockTopY(blockState2, n, n2 - 1, n3);
                                 double d3 = (double)pathBlockPos2.y + this.getBlockTopY(state2, pathBlockPos2.x, pathBlockPos2.y - 1, pathBlockPos2.z);
                                 if (d2 - d3 <= 1.0) {
                                    skipStepDownCheck = true;
                                 }
                              }

                              if (!skipStepDownCheck) {
                                 boolean flag9 = false;
                                 if (!flag && !flag4) {
                                    IBlockState state4 = value2.getBlockState(n4, n5 - 2, n6);
                                    if (PathBlockValidator.isStandableBlock(mc.theWorld, n4, n5 - 2, n6, this.walkOnWater, this.allowSwimming, state4)
                                       && this.getBlockTopY(state4, n4, n5 - 2, n6) >= 1.0
                                       && state.getBlock() != Blocks.web
                                       && state.getBlock().getMaterial() != Material.lava) {
                                       flag9 = true;
                                    }
                                 }

                                 flag6 = flag9;
                                 if (!flag9) {
                                    return -1.0;
                                 }
                              }
                           }

                           if (n5 == n2 && (this.avoidFallDamage || this.maxFallDistance < 255.0)) {
                              int n22 = (int)Math.max((double)(n2 + 1), Math.min(255.0, this.maxFallDistance));
                              int n23 = 0;

                              for (int i = 0; i < n22; i++) {
                                 IBlockState blockState3 = chunk.getBlockState(n, n2 - i, n3);
                                 Block block = blockState3.getBlock();
                                 if (PathBlockValidator.isWater(block) || block == Blocks.web || block == Blocks.slime_block) {
                                    break;
                                 }

                                 n7 = n2 - i;
                                 boolean allowSwimming8 = this.allowSwimming;
                                 boolean walkOnWater8 = this.walkOnWater;
                                 int n24 = n7;
                                 if (PathBlockValidator.isStandableBlock(mc.theWorld, n, n24, n3, walkOnWater8, allowSwimming8, blockState3)) {
                                    n23 = i;
                                    break;
                                 }
                              }

                              if ((double)n23 > this.maxFallDistance) {
                                 return -1.0;
                              }

                              PotionEffect activePotionEffect = mc.thePlayer.getActivePotionEffect(Potion.jump);
                              int n25 = 0;
                              if (activePotionEffect != null) {
                                 n25 = activePotionEffect.getAmplifier() + 1;
                              }

                              if ((double)n23 - 4.07 - (double)n25 > 0.0) {
                                 d = this.fallDamagePenalty / (double)MathHelper.ceiling_double_int((double)n23 - 4.07 - (double)n25);
                              }
                           }

                           if (flag6) {
                              d *= 0.25;
                           }

                           if (flag) {
                              boolean allowSwimming9 = this.allowSwimming;
                              boolean walkOnWater9 = this.walkOnWater;
                              PathfinderResultRef groundCheckCached3 = this.groundCheckCached;
                              boolean isStandingGroundCached = isStandingGroundCached(this.groundCheckResult, groundCheckCached3, n4, n5, n6, walkOnWater9, allowSwimming9, state);
                              WorldClient theWorld3 = mc.theWorld;
                              int n26 = MathHelper.floor_double((double)(pathBlockPos.x + (n - pathBlockPos.x)));
                              n7 = MathHelper.floor_double((double)pathBlockPos.y);
                              int n27 = MathHelper.floor_double((double)pathBlockPos.z);
                              boolean flag12 = !flag2;
                              boolean flag13 = true;
                              boolean walkOnWater10 = this.walkOnWater;
                              if (PathBlockValidator.canPassThroughCorner(theWorld3, n26, n7, n27, n4, n5, n6, isStandingGroundCached, walkOnWater10, flag13, flag12)) {
                                 theWorld3 = mc.theWorld;
                                 int n31 = MathHelper.floor_double((double)pathBlockPos.x);
                                 n7 = MathHelper.floor_double((double)pathBlockPos.y);
                                 n27 = MathHelper.floor_double((double)(pathBlockPos.z + (n3 - pathBlockPos.z)));
                                 flag12 = !flag2;
                                 flag13 = true;
                                 walkOnWater10 = this.walkOnWater;
                                 if (PathBlockValidator.canPassThroughCorner(theWorld3, n31, n7, n27, n4, n5, n6, isStandingGroundCached, walkOnWater10, flag13, flag12)) {
                                    return d;
                                 }
                              }

                              return -1.0;
                           } else {
                              return d;
                           }
                        } else {
                           return -1.0;
                        }
                     }
                  }
               }
            }
         } else {
            return !blockState.getBlock().hasCollisionBox() && !chunk.getBlockState(n, n2 + 1, n3).getBlock().hasCollisionBox() ? 1.0 : -1.0;
         }
      }
   }

   public static boolean isStandingGroundCached(PathfinderResultRef pathfinderResultRef, PathfinderResultRef pathfinderResultRef2, int n, int n2, int n3, boolean flag, boolean flag2, IBlockState state) {
      if ((Boolean)pathfinderResultRef2.value) {
         return (Boolean)pathfinderResultRef.value;
      } else {
         int n5 = n2 - 1;
         pathfinderResultRef.value = PathBlockValidator.isStandableBlock(mc.theWorld, n, n5, n3, flag, flag2, state);
         pathfinderResultRef2.value = true;
         return (Boolean)pathfinderResultRef.value;
      }
   }

   public PathNode createAndQueueNode(PathNode pathNode, int n, int n2, int n3, long l, boolean flag) {
      if (this.closedSet.containsKey(l)) {
         return null;
      } else {
         PathBlockPos pathBlockPos = null;
         if (pathNode != null) {
            pathBlockPos = pathNode.getPos();
         }

         double d = this.evaluateMoveSpeed(n, n2, n3, pathBlockPos, pathNode != null && pathNode.getParent() != null ? pathNode.getParent().getPos() : null, flag);
         if (d == -1.0) {
            return null;
         } else {
            double d2 = 0.0;
            if (pathNode != null) {
               d2 = pathNode.getGCost() + pathNode.getPos().distanceTo(n, n2, n3) / d;
            }

            PathNode pathNode2 = new PathNode(pathNode, new PathBlockPos(n, n2, n3), d2, this.goalPos.distanceTo(n, n2, n3), l);
            pathNode2.speedMultiplier = d;
            if (pathNode != null) {
               pathNode.getChildren().add(pathNode2);
            }

            this.allNodes.put(l, pathNode2);
            this.openSet.add(pathNode2);
            return pathNode2;
         }
      }
   }
}
