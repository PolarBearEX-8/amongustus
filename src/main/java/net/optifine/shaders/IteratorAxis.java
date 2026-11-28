package net.optifine.shaders;

import java.util.Iterator;
import java.util.NoSuchElementException;
import net.minecraft.util.BlockPos;
import net.optifine.BlockPosM;

public class IteratorAxis implements Iterator {
   private double yDelta;
   private double zDelta;
   private int xStart;
   private int xEnd;
   private double yStart;
   private double yEnd;
   private double zStart;
   private double zEnd;
   private int xNext;
   private double yNext;
   private double zNext;
   private BlockPosM pos = new BlockPosM(0, 0, 0);
   private boolean next = false;

   public BlockPos nextPos() {
      if (!this.next) {
         throw new NoSuchElementException();
      } else {
         this.pos.setXyz2((double)this.xNext, this.yNext, this.zNext);
         this.advancePos();
         this.next = this.xNext < this.xEnd && this.yNext < this.yEnd && this.zNext < this.zEnd;
         return this.pos;
      }
   }

   @Override
   public boolean hasNext() {
      return this.next;
   }

   private void advancePos() {
      this.zNext++;
      if (this.zNext >= this.zEnd) {
         this.zNext = this.zStart;
         this.yNext++;
         if (this.yNext >= this.yEnd) {
            this.yNext = this.yStart;
            this.yStart = this.yStart + this.yDelta;
            this.yEnd = this.yEnd + this.yDelta;
            this.yNext = this.yStart;
            this.zStart = this.zStart + this.zDelta;
            this.zEnd = this.zEnd + this.zDelta;
            this.zNext = this.zStart;
            this.xNext++;
            if (this.xNext >= this.xEnd) {
            }
         }
      }
   }

   public static void main(String[] args) throws Exception {
      BlockPos pos2 = new BlockPos(-2, 10, 20);
      BlockPos pos3 = new BlockPos(2, 12, 22);
      double d = -0.5;
      double d2 = 0.5;
      IteratorAxis iteratorAxis = new IteratorAxis(pos2, pos3, d, d2);
      System.out.println("Start: " + pos2 + ", end: " + pos3 + ", yDelta: " + d + ", zDelta: " + d2);

      while (iteratorAxis.hasNext()) {
         BlockPos pos4 = iteratorAxis.nextPos();
         System.out.println("" + pos4);
      }
   }

   @Override
   public void remove() {
      throw new RuntimeException("Not implemented");
   }

   @Override
   public Object next() {
      return this.nextPos();
   }

   public IteratorAxis(BlockPos pos2, BlockPos pos3, double d, double d2) {
      this.yDelta = d;
      this.zDelta = d2;
      this.xStart = pos2.getX();
      this.xEnd = pos3.getX();
      this.yStart = (double)pos2.getY();
      this.yEnd = (double)pos3.getY() - 0.5;
      this.zStart = (double)pos2.getZ();
      this.zEnd = (double)pos3.getZ() - 0.5;
      this.xNext = this.xStart;
      this.yNext = this.yStart;
      this.zNext = this.zStart;
      this.next = this.xNext < this.xEnd && this.yNext < this.yEnd && this.zNext < this.zEnd;
   }
}
