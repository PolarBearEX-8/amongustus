package client.render;

import client.event.PostUpdateObserver;
import client.setting.BlockSolidType;
import client.util.BuildManager;
import client.util.CurveChecker;
import client.util.InterpolationCollector;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.chunk.RenderChunk;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class RenderGlobalEntry {
   public final RenderGlobal renderGlobal;
   public final World world;
   public int count;
   public int count3;
   public int count2;
   public RenderChunk[] renderChunkArray;
   public Map map = new HashMap();

   public void applyIndex(int count4) {
      int count5 = count4 * 2 + 1;
      this.count3 = count5;
      this.count = 16;
      this.count2 = count5;
   }

   public RenderChunk resolveRenderChunk(int count4, int count5, int count6) {
      int count7 = count4 >> 4;
      int count8 = count5 >> 4;
      int count9 = count6 >> 4;
      if (count8 >= 0 && count8 < this.count) {
         int count10 = count7 % this.count3;
         if (count10 < 0) {
            count10 += this.count3;
         }

         int count11 = count9 % this.count2;
         if (count11 < 0) {
            count11 += this.count2;
         }

         int count12 = (count11 * this.count + count8) * this.count3 + count10;
         return this.renderChunkArray[count12];
      } else {
         return null;
      }
   }

   public RenderChunk resolveRenderChunk(BlockPos blockPos) {
      int count4 = blockPos.getX() >> 4;
      int count5 = blockPos.getY() >> 4;
      int count6 = blockPos.getZ() >> 4;
      if (count5 >= 0 && count5 < this.count) {
         int count7 = count4 % this.count3;
         if (count7 < 0) {
            count7 += this.count3;
         }

         int count8 = count6 % this.count2;
         if (count8 < 0) {
            count8 += this.count2;
         }

         int count9 = (count8 * this.count + count5) * this.count3 + count7;
         return this.renderChunkArray[count9];
      } else {
         return null;
      }
   }

   public void applyPostUpdateObserver(PostUpdateObserver postUpdateObserver) {
      int count4 = this.count3 * this.count * this.count2;
      this.renderChunkArray = new RenderChunk[count4];
      int count5 = 0;

      for (int index = 0; index < this.count3; index++) {
         for (int index2 = 0; index2 < this.count; index2++) {
            for (int index3 = 0; index3 < this.count2; index3++) {
               int count6 = (index3 * this.count + index2) * this.count3 + index;
               BlockPos blockPos = new BlockPos(index * 16, index2 * 16, index3 * 16);
               this.renderChunkArray[count6] = postUpdateObserver.resolveRenderChunk(this.world, this.renderGlobal, blockPos, count5++);
               if (BuildManager.isActive26() && BuildManager.isActive55()) {
                  this.applyRenderChunk(this.renderChunkArray[count6]);
               }
            }
         }
      }

      for (int index4 = 0; index4 < this.renderChunkArray.length; index4++) {
         RenderChunk renderChunk = this.renderChunkArray[index4];

         for (int index5 = 0; index5 < EnumFacing.VALUES.length; index5++) {
            EnumFacing enumFacing = EnumFacing.VALUES[index5];
            BlockPos blockPos2 = renderChunk.resolveBlockPosByEnumFacing(enumFacing);
            RenderChunk renderChunk2 = this.resolveRenderChunk(blockPos2);
            renderChunk.applyEnumFacing(enumFacing, renderChunk2);
         }
      }
   }

   public void renderElement() {
      RenderChunk[] renderChunkArray2 = this.renderChunkArray;
      int length = renderChunkArray2.length;

      for (int index = 0; index < length; index++) {
         renderChunkArray2[index].updateState6();
      }

      this.renderElement2();
   }

   private int computeCount(int count4, int count5, int count6) {
      int count7 = count6 * 16;
      int count8 = count7 - count4 + count5 / 2;
      if (count8 < 0) {
         count8 -= count5 - 1;
      }

      return count7 - count8 / count5 * count5;
   }

   public void applyDouble(double offset, double offset2) {
      int count4 = MathHelper.ceiling_double_int(offset) - 8;
      int count5 = MathHelper.ceiling_double_int(offset2) - 8;
      int count6 = this.count3 * 16;

      for (int index = 0; index < this.count3; index++) {
         int count7 = this.computeCount(count4, count6, index);

         for (int index2 = 0; index2 < this.count2; index2++) {
            int count8 = this.computeCount(count5, count6, index2);

            for (int index3 = 0; index3 < this.count; index3++) {
               int count9 = index3 * 16;
               RenderChunk renderChunk = this.renderChunkArray[(index2 * this.count + index3) * this.count3 + index];
               BlockPos blockPos = renderChunk.getBlockPos();
               if (blockPos.getX() != count7 || blockPos.getY() != count9 || blockPos.getZ() != count8) {
                  BlockPos blockPos2 = new BlockPos(count7, count9, count8);
                  if (!blockPos2.equals(renderChunk.getBlockPos())) {
                     renderChunk.applyBlockPos(blockPos2);
                  }
               }
            }
         }
      }
   }

   private void applyRenderChunk(RenderChunk renderChunk) {
      BlockPos blockPos = renderChunk.getBlockPos();
      int count4 = blockPos.getX() >> 8 << 8;
      int count5 = blockPos.getZ() >> 8 << 8;
      InterpolationCollector interpolationCollector = new InterpolationCollector(count4, count5);
      BlockSolidType[] blockSolidTypeArray = RenderChunk.blockSolidTypeArray2;
      CurveChecker[] curveCheckerArray = (CurveChecker[])this.map.get(interpolationCollector);
      if (curveCheckerArray == null) {
         curveCheckerArray = new CurveChecker[blockSolidTypeArray.length];

         for (int index = 0; index < blockSolidTypeArray.length; index++) {
            curveCheckerArray[index] = new CurveChecker(blockSolidTypeArray[index]);
         }

         this.map.put(interpolationCollector, curveCheckerArray);
      }

      for (int index2 = 0; index2 < blockSolidTypeArray.length; index2++) {
         CurveChecker curveChecker = curveCheckerArray[index2];
         if (curveChecker != null) {
            renderChunk.resolveDiamondChestplate(index2).applyCurveChecker(curveChecker);
         }
      }
   }

   public void renderElement2() {
      for (Object interpolationCollectorObj : this.map.keySet()) {
         InterpolationCollector interpolationCollector = (InterpolationCollector)interpolationCollectorObj;
         CurveChecker[] curveCheckerArray = (CurveChecker[])this.map.get(interpolationCollector);

         for (int index = 0; index < curveCheckerArray.length; index++) {
            CurveChecker curveChecker = curveCheckerArray[index];
            if (curveChecker != null) {
               curveChecker.updateState3();
            }

            curveCheckerArray[index] = null;
         }
      }

      this.map.clear();
   }

   public void applyIndex(int count4, int count5, int count6, int count7, int count8, int count9) {
      int count10 = MathHelper.computeCountByIndex(count4, 16);
      int count11 = MathHelper.computeCountByIndex(count5, 16);
      int count12 = MathHelper.computeCountByIndex(count6, 16);
      int count13 = MathHelper.computeCountByIndex(count7, 16);
      int count14 = MathHelper.computeCountByIndex(count8, 16);

      for (int index = MathHelper.computeCountByIndex(count9, 16); count10 <= count13; count10++) {
         int count15 = count10 % this.count3;
         if (count15 < 0) {
            count15 += this.count3;
         }

         for (int index2 = count11; index2 <= count14; index2++) {
            int count16 = index2 % this.count;
            if (count16 < 0) {
               count16 += this.count;
            }

            for (int index3 = count12; index3 <= index; index3++) {
               int count17 = index3 % this.count2;
               if (count17 < 0) {
                  count17 += this.count2;
               }

               int count18 = (count17 * this.count + count16) * this.count3 + count15;
               this.renderChunkArray[count18].setEnabled(true);
            }
         }
      }
   }

   public RenderGlobalEntry(World world2, int count4, RenderGlobal renderGlobal2, PostUpdateObserver postUpdateObserver) {
      this.renderGlobal = renderGlobal2;
      this.world = world2;
      this.applyIndex(count4);
      this.applyPostUpdateObserver(postUpdateObserver);
   }
}
