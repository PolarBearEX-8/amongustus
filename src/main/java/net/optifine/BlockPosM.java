package net.optifine;

import com.google.common.collect.AbstractIterator;
import java.util.Iterator;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3i;

public class BlockPosM extends BlockPos {
   private int x2;
   private int y2;
   private int z2;
   private int level;
   private BlockPosM[] facings;
   private boolean needsUpdate;

   public BlockPosM(int n, int n2, int n3) {
      this(n, n2, n3, 0);
   }

   public void setXyz2(double d, double d2, double d3) {
      this.setXyz(MathHelper.floor_double(d), MathHelper.floor_double(d2), MathHelper.floor_double(d3));
   }

   public BlockPosM(double d, double d2, double d3) {
      this(MathHelper.floor_double(d), MathHelper.floor_double(d2), MathHelper.floor_double(d3));
   }

   public static Iterable getAllInBoxMutable(int n, int n2, int n3, int n4, int n5, int n6) {
      BlockPos pos = new BlockPos(Math.min(n, n4), Math.min(n2, n5), Math.min(n3, n6));
      BlockPos pos2 = new BlockPos(Math.max(n, n4), Math.max(n2, n5), Math.max(n3, n6));
      return new BlockPosM.EntryIterable(pos, pos2);
   }

   @Override
   public BlockPos offset(EnumFacing facing) {
      if (this.level <= 0) {
         return super.offset(facing, 1);
      } else {
         if (this.facings == null) {
            this.facings = new BlockPosM[EnumFacing.VALUES.length];
         }

         if (this.needsUpdate) {
            this.update();
         }

         int index = facing.getIndex();
         BlockPosM blockPosM = this.facings[index];
         if (blockPosM == null) {
            int n = this.x2 + facing.getFrontOffsetX();
            int n2 = this.y2 + facing.getFrontOffsetY();
            int n3 = this.z2 + facing.getFrontOffsetZ();
            blockPosM = new BlockPosM(n, n2, n3, this.level - 1);
            this.facings[index] = blockPosM;
         }

         return blockPosM;
      }
   }

   public BlockPosM setPos(int n, int n2, int n3) {
      this.setXyz(n, n2, n3);
      return this;
   }

   @Override
   public int getX() {
      return this.x2;
   }

   private void update() {
      for (int i = 0; i < 6; i++) {
         BlockPosM blockPosM = this.facings[i];
         if (blockPosM != null) {
            EnumFacing facing = EnumFacing.VALUES[i];
            blockPosM.setXyz(this.x2 + facing.getFrontOffsetX(), this.y2 + facing.getFrontOffsetY(), this.z2 + facing.getFrontOffsetZ());
         }
      }

      this.needsUpdate = false;
   }

   @Override
   public int getY() {
      return this.y2;
   }

   public BlockPos offsetMutable(EnumFacing facing) {
      return this.offset(facing);
   }

   @Override
   public int getZ() {
      return this.z2;
   }

   public BlockPosM setXyz(Vec3i vec) {
      this.setXyz(vec.getX(), vec.getY(), vec.getZ());
      return this;
   }

   @Override
   public BlockPos offset(EnumFacing facing, int n) {
      return n == 1 ? this.offset(facing) : super.offset(facing, n);
   }

   public void setXyz(int n, int n2, int n3) {
      this.x2 = n;
      this.y2 = n2;
      this.z2 = n3;
      this.needsUpdate = true;
   }

   public BlockPosM(int n, int n2, int n3, int n4) {
      super(0, 0, 0);
      this.x2 = n;
      this.y2 = n2;
      this.z2 = n3;
      this.level = n4;
   }

   public BlockPos toImmutable() {
      return new BlockPos(this.x2, this.y2, this.z2);
   }


   public static final class EntryIterable implements Iterable {
      public final BlockPos posFrom;
      public final BlockPos posTo;

      @Override
      public Iterator iterator() {
         return new BlockPosM.EntryIterable.EntryIterator(this);
      }

      public EntryIterable(BlockPos pos, BlockPos pos2) {
         this.posFrom = pos;
         this.posTo = pos2;
      }


      public static class EntryIterator extends AbstractIterator {
         private BlockPosM theBlockPosM;
         public final BlockPosM.EntryIterable iterable;

         public EntryIterator(BlockPosM.EntryIterable obj) {
            this.iterable = obj;
            this.theBlockPosM = null;
         }

         public BlockPosM computeNext0() {
            if (this.theBlockPosM == null) {
               this.theBlockPosM = new BlockPosM(this.iterable.posFrom.getX(), this.iterable.posFrom.getY(), this.iterable.posFrom.getZ(), 3);
               return this.theBlockPosM;
            } else if (this.theBlockPosM.equals(this.iterable.posTo)) {
               return (BlockPosM)this.endOfData();
            } else {
               int x = this.theBlockPosM.getX();
               int y = this.theBlockPosM.getY();
               int z = this.theBlockPosM.getZ();
               if (x < this.iterable.posTo.getX()) {
                  x++;
               } else if (y < this.iterable.posTo.getY()) {
                  x = this.iterable.posFrom.getX();
                  y++;
               } else if (z < this.iterable.posTo.getZ()) {
                  x = this.iterable.posFrom.getX();
                  y = this.iterable.posFrom.getY();
                  z++;
               }

               this.theBlockPosM.setXyz(x, y, z);
               return this.theBlockPosM;
            }
         }

         public Object computeNext() {
            return this.computeNext0();
         }
      }
   }
}
