package net.optifine.shaders;

import java.util.Iterator;
import net.minecraft.util.BlockPos;
import net.minecraft.util.Vec3;
import net.optifine.BlockPosM;

public class Iterator3d implements Iterator {
   private IteratorAxis iteratorAxis;
   private BlockPosM blockPos = new BlockPosM(0, 0, 0);
   private int axis = 0;
   private int kX;
   private int kY;
   private int kZ;
   private static final int AXIS_X = 0;
   private static final int AXIS_Y = 1;
   private static final int AXIS_Z = 2;

   public static void main(String[] args) {
      BlockPos pos = new BlockPos(10, 20, 30);
      BlockPos pos2 = new BlockPos(30, 40, 20);
      Iterator3d iterator3d = new Iterator3d(pos, pos2, 1, 1);

      while (iterator3d.hasNext()) {
         BlockPos pos3 = iterator3d.nextPos();
         System.out.println("" + pos3);
      }
   }

   @Override
   public void remove() {
      throw new RuntimeException("Not supported");
   }

   private BlockPos reverseCoord(BlockPos pos, boolean flag, boolean flag2, boolean flag3) {
      if (flag) {
         pos = new BlockPos(-pos.getX(), pos.getY(), pos.getZ());
      }

      if (flag2) {
         pos = new BlockPos(pos.getX(), -pos.getY(), pos.getZ());
      }

      if (flag3) {
         pos = new BlockPos(pos.getX(), pos.getY(), -pos.getZ());
      }

      return pos;
   }

   @Override
   public boolean hasNext() {
      return this.iteratorAxis.hasNext();
   }

   public BlockPos nextPos() {
      BlockPos pos = this.iteratorAxis.nextPos();
      switch (this.axis) {
         case 1:
            this.blockPos.setXyz(pos.getY() * this.kX, pos.getX() * this.kY, pos.getZ() * this.kZ);
            return this.blockPos;
         case 2:
            this.blockPos.setXyz(pos.getZ() * this.kX, pos.getY() * this.kY, pos.getX() * this.kZ);
            return this.blockPos;
         default:
            this.blockPos.setXyz(pos.getX() * this.kX, pos.getY() * this.kY, pos.getZ() * this.kZ);
            return this.blockPos;
      }
   }

   @Override
   public Object next() {
      return this.nextPos();
   }

   public Iterator3d(BlockPos pos, BlockPos pos2, int n, int n2) {
      boolean flag = pos.getX() > pos2.getX();
      boolean flag2 = pos.getY() > pos2.getY();
      boolean flag3 = pos.getZ() > pos2.getZ();
      pos = this.reverseCoord(pos, flag, flag2, flag3);
      pos2 = this.reverseCoord(pos2, flag, flag2, flag3);
      this.kX = flag ? -1 : 1;
      this.kY = flag2 ? -1 : 1;
      this.kZ = flag3 ? -1 : 1;
      Vec3 vec2 = new Vec3((double)(pos2.getX() - pos.getX()), (double)(pos2.getY() - pos.getY()), (double)(pos2.getZ() - pos.getZ())).normalize();
      double d2 = Math.abs(vec2.dotProduct(new Vec3(1.0, 0.0, 0.0)));
      double d4 = Math.abs(vec2.dotProduct(new Vec3(0.0, 1.0, 0.0)));
      double d6 = Math.abs(vec2.dotProduct(new Vec3(0.0, 0.0, 1.0)));
      if (d6 >= d4 && d6 >= d2) {
         this.axis = 2;
         BlockPos pos3 = new BlockPos(pos.getZ(), pos.getY() - n, pos.getX() - n2);
         BlockPos pos4 = new BlockPos(pos2.getZ(), pos.getY() + n + 1, pos.getX() + n2 + 1);
         int n3 = pos2.getZ() - pos.getZ();
         double d7 = (double)(pos2.getY() - pos.getY()) / (1.0 * (double)n3);
         double d8 = (double)(pos2.getX() - pos.getX()) / (1.0 * (double)n3);
         this.iteratorAxis = new IteratorAxis(pos3, pos4, d7, d8);
      } else if (d4 >= d2 && d4 >= d6) {
         this.axis = 1;
         BlockPos pos5 = new BlockPos(pos.getY(), pos.getX() - n, pos.getZ() - n2);
         BlockPos pos6 = new BlockPos(pos2.getY(), pos.getX() + n + 1, pos.getZ() + n2 + 1);
         int n4 = pos2.getY() - pos.getY();
         double d9 = (double)(pos2.getX() - pos.getX()) / (1.0 * (double)n4);
         double d10 = (double)(pos2.getZ() - pos.getZ()) / (1.0 * (double)n4);
         this.iteratorAxis = new IteratorAxis(pos5, pos6, d9, d10);
      } else {
         this.axis = 0;
         BlockPos pos7 = new BlockPos(pos.getX(), pos.getY() - n, pos.getZ() - n2);
         BlockPos pos8 = new BlockPos(pos2.getX(), pos.getY() + n + 1, pos.getZ() + n2 + 1);
         int n5 = pos2.getX() - pos.getX();
         double d11 = (double)(pos2.getY() - pos.getY()) / (1.0 * (double)n5);
         double d12 = (double)(pos2.getZ() - pos.getZ()) / (1.0 * (double)n5);
         this.iteratorAxis = new IteratorAxis(pos7, pos8, d11, d12);
      }
   }
}
