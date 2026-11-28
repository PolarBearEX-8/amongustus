package net.minecraft.util;

import com.google.common.collect.AbstractIterator;
import java.util.Iterator;
import net.minecraft.entity.Entity;

public class BlockPos extends Vec3i {
   public static final BlockPos ORIGIN = new BlockPos(0, 0, 0);
   private static final int NUM_X_BITS = 1 + MathHelper.calculateLogBaseTwo(MathHelper.roundUpToPowerOfTwo(30000000));
   private static final int NUM_Z_BITS = NUM_X_BITS;
   private static final int NUM_Y_BITS = 64 - NUM_X_BITS - NUM_Z_BITS;
   private static final int Y_SHIFT = 0 + NUM_Z_BITS;
   private static final int X_SHIFT = Y_SHIFT + NUM_Y_BITS;
   private static final long X_MASK = (1L << NUM_X_BITS) - 1L;
   private static final long Y_MASK = (1L << NUM_Y_BITS) - 1L;
   private static final long Z_MASK = (1L << NUM_Z_BITS) - 1L;

   public BlockPos subtract(Vec3i vec) {
      return vec.getX() == 0 && vec.getY() == 0 && vec.getZ() == 0
         ? this
         : new BlockPos(this.getX() - vec.getX(), this.getY() - vec.getY(), this.getZ() - vec.getZ());
   }

   public BlockPos offset(EnumFacing facing) {
      return new BlockPos(this.getX() + facing.getFrontOffsetX(), this.getY() + facing.getFrontOffsetY(), this.getZ() + facing.getFrontOffsetZ());
   }

   public BlockPos down(int n) {
      return n == 0 ? this : new BlockPos(this.getX(), this.getY() - n, this.getZ());
   }

   public static Iterable getAllInBoxMutable(BlockPos pos, BlockPos pos2) {
      BlockPos pos3 = new BlockPos(Math.min(pos.getX(), pos2.getX()), Math.min(pos.getY(), pos2.getY()), Math.min(pos.getZ(), pos2.getZ()));
      BlockPos pos4 = new BlockPos(Math.max(pos.getX(), pos2.getX()), Math.max(pos.getY(), pos2.getY()), Math.max(pos.getZ(), pos2.getZ()));
      return new BlockPos.EntryIterable2(pos3, pos4);
   }

   public long toLong() {
      return ((long)this.getX() & X_MASK) << X_SHIFT | ((long)this.getY() & Y_MASK) << Y_SHIFT | ((long)this.getZ() & Z_MASK) << 0;
   }

   public BlockPos up(int n) {
      return n == 0 ? this : new BlockPos(this.getX(), this.getY() + n, this.getZ());
   }

   public BlockPos(double d, double d2, double d3) {
      super(d, d2, d3);
   }

   public BlockPos west() {
      return new BlockPos(this.getX() - 1, this.getY(), this.getZ());
   }

   public BlockPos(Entity entity) {
      this(entity.posX, entity.posY, entity.posZ);
   }

   public BlockPos east() {
      return new BlockPos(this.getX() + 1, this.getY(), this.getZ());
   }

   public boolean equalsPos(BlockPos pos) {
      return this.getX() == pos.getX() && this.getY() == pos.getY() && this.getZ() == pos.getZ();
   }

   public BlockPos(Vec3 vec) {
      this(vec.xCoord, vec.yCoord, vec.zCoord);
   }

   public BlockPos(int n, int n2, int n3) {
      super(n, n2, n3);
   }

   public BlockPos crossProduct(Vec3i vec) {
      return new BlockPos(
         this.getY() * vec.getZ() - this.getZ() * vec.getY(),
         this.getZ() * vec.getX() - this.getX() * vec.getZ(),
         this.getX() * vec.getY() - this.getY() * vec.getX()
      );
   }

   public BlockPos upWest() {
      return new BlockPos(this.getX() - 1, this.getY() + 1, this.getZ());
   }

   public BlockPos west(int n) {
      return n == 0 ? this : new BlockPos(this.getX() - n, this.getY(), this.getZ());
   }

   public boolean equalsXZ(BlockPos pos) {
      return this.getX() == pos.getX() && this.getZ() == pos.getZ();
   }

   public BlockPos upNorth() {
      return new BlockPos(this.getX(), this.getY() + 1, this.getZ() - 1);
   }

   public BlockPos add(double d, double d2, double d3) {
      return d == 0.0 && d2 == 0.0 && d3 == 0.0
         ? this
         : new BlockPos((double)this.getX() + d, (double)this.getY() + d2, (double)this.getZ() + d3);
   }

   public BlockPos up() {
      return new BlockPos(this.getX(), this.getY() + 1, this.getZ());
   }

   public static BlockPos fromLong(long l) {
      int n = (int)(l << 64 - X_SHIFT - NUM_X_BITS >> 64 - NUM_X_BITS);
      int n2 = (int)(l << 64 - Y_SHIFT - NUM_Y_BITS >> 64 - NUM_Y_BITS);
      int n3 = (int)(l << 64 - NUM_Z_BITS >> 64 - NUM_Z_BITS);
      return new BlockPos(n, n2, n3);
   }

   public BlockPos south() {
      return new BlockPos(this.getX(), this.getY(), this.getZ() + 1);
   }

   public BlockPos offset(EnumFacing facing, int n) {
      return n == 0
         ? this
         : new BlockPos(this.getX() + facing.getFrontOffsetX() * n, this.getY() + facing.getFrontOffsetY() * n, this.getZ() + facing.getFrontOffsetZ() * n);
   }

   public BlockPos north(int n) {
      return n == 0 ? this : new BlockPos(this.getX(), this.getY(), this.getZ() - n);
   }

   public BlockPos(Vec3i vec) {
      this(vec.getX(), vec.getY(), vec.getZ());
   }

   public BlockPos add(Vec3i vec) {
      return vec.getX() == 0 && vec.getY() == 0 && vec.getZ() == 0
         ? this
         : new BlockPos(this.getX() + vec.getX(), this.getY() + vec.getY(), this.getZ() + vec.getZ());
   }

   public static Iterable getAllInBox(BlockPos pos, BlockPos pos2) {
      BlockPos pos3 = new BlockPos(Math.min(pos.getX(), pos2.getX()), Math.min(pos.getY(), pos2.getY()), Math.min(pos.getZ(), pos2.getZ()));
      BlockPos pos4 = new BlockPos(Math.max(pos.getX(), pos2.getX()), Math.max(pos.getY(), pos2.getY()), Math.max(pos.getZ(), pos2.getZ()));
      return new BlockPos.EntryIterable(pos3, pos4);
   }

   public BlockPos upSouth() {
      return new BlockPos(this.getX(), this.getY() + 1, this.getZ() + 1);
   }

   public BlockPos east(int n) {
      return n == 0 ? this : new BlockPos(this.getX() + n, this.getY(), this.getZ());
   }

   public BlockPos south(int n) {
      return n == 0 ? this : new BlockPos(this.getX(), this.getY(), this.getZ() + n);
   }

   public BlockPos upEast() {
      return new BlockPos(this.getX() + 1, this.getY() + 1, this.getZ());
   }

   public BlockPos north() {
      return new BlockPos(this.getX(), this.getY(), this.getZ() - 1);
   }

   public BlockPos down() {
      return new BlockPos(this.getX(), this.getY() - 1, this.getZ());
   }

   public BlockPos add(int n, int n2, int n3) {
      return n == 0 && n2 == 0 && n3 == 0 ? this : new BlockPos(this.getX() + n, this.getY() + n2, this.getZ() + n3);
   }

   @Override
   public Vec3i crossProductVec(Vec3i vec) {
      return this.crossProduct(vec);
   }


   public static final class EntryIterable implements Iterable {
      public final BlockPos minPos;
      public final BlockPos maxPos;

      @Override
      public Iterator iterator() {
         return new BlockPos.EntryIterable.EntryIterator(this);
      }

      public EntryIterable(BlockPos pos, BlockPos pos2) {
         this.minPos = pos;
         this.maxPos = pos2;
      }


      public static class EntryIterator extends AbstractIterator {
         private BlockPos lastReturned;
         public final BlockPos.EntryIterable iterable;

         public BlockPos computeNextBlockPos() {
            if (this.lastReturned == null) {
               this.lastReturned = this.iterable.minPos;
               return this.lastReturned;
            } else if (this.lastReturned.equals(this.iterable.maxPos)) {
               return (BlockPos)this.endOfData();
            } else {
               int x = this.lastReturned.getX();
               int y = this.lastReturned.getY();
               int z = this.lastReturned.getZ();
               if (x < this.iterable.maxPos.getX()) {
                  x++;
               } else if (y < this.iterable.maxPos.getY()) {
                  x = this.iterable.minPos.getX();
                  y++;
               } else if (z < this.iterable.maxPos.getZ()) {
                  x = this.iterable.minPos.getX();
                  y = this.iterable.minPos.getY();
                  z++;
               }

               this.lastReturned = new BlockPos(x, y, z);
               return this.lastReturned;
            }
         }

         public EntryIterator(BlockPos.EntryIterable obj) {
            this.iterable = obj;
            this.lastReturned = null;
         }

         public Object computeNext() {
            return this.computeNextBlockPos();
         }
      }
   }

   public static final class EntryIterable2 implements Iterable {
      public final BlockPos minPos;
      public final BlockPos maxPos;

      @Override
      public Iterator iterator() {
         return new BlockPos.EntryIterable2.EntryIterator(this);
      }

      public EntryIterable2(BlockPos pos, BlockPos pos2) {
         this.minPos = pos;
         this.maxPos = pos2;
      }


      public static class EntryIterator extends AbstractIterator {
         private BlockPos.MutableBlockPos theBlockPos;
         public final BlockPos.EntryIterable2 iterable;

         public EntryIterator(BlockPos.EntryIterable2 obj) {
            this.iterable = obj;
            this.theBlockPos = null;
         }

         public Object computeNext() {
            return this.computeNextMutableBlockPos();
         }

         public BlockPos.MutableBlockPos computeNextMutableBlockPos() {
            if (this.theBlockPos == null) {
               this.theBlockPos = new BlockPos.MutableBlockPos(this.iterable.minPos.getX(), this.iterable.minPos.getY(), this.iterable.minPos.getZ());
               return this.theBlockPos;
            } else if (this.theBlockPos.equals(this.iterable.maxPos)) {
               return (BlockPos.MutableBlockPos)this.endOfData();
            } else {
               int x = this.theBlockPos.getX();
               int y = this.theBlockPos.getY();
               int z = this.theBlockPos.getZ();
               if (x < this.iterable.maxPos.getX()) {
                  x++;
               } else if (y < this.iterable.maxPos.getY()) {
                  x = this.iterable.minPos.getX();
                  y++;
               } else if (z < this.iterable.maxPos.getZ()) {
                  x = this.iterable.minPos.getX();
                  y = this.iterable.minPos.getY();
                  z++;
               }

               BlockPos.MutableBlockPos.accessSetX(this.theBlockPos, x);
               BlockPos.MutableBlockPos.accessSetY(this.theBlockPos, y);
               BlockPos.MutableBlockPos.accessSetZ(this.theBlockPos, z);
               return this.theBlockPos;
            }
         }
      }
   }

   public static final class MutableBlockPos extends BlockPos {
      private int x;
      private int y;
      private int z;

      public MutableBlockPos set(int n, int n2, int n3) {
         this.x = n;
         this.y = n2;
         this.z = n3;
         return this;
      }

      public MutableBlockPos(int n, int n2, int n3) {
         super(0, 0, 0);
         this.x = n;
         this.y = n2;
         this.z = n3;
      }

      public static int accessSetZ(MutableBlockPos pos, int n) {
         return pos.z = n;
      }

      @Override
      public int getX() {
         return this.x;
      }

      public static int accessSetX(MutableBlockPos pos, int n) {
         return pos.x = n;
      }

      @Override
      public Vec3i crossProductVec(Vec3i vec) {
         return super.crossProduct(vec);
      }

      @Override
      public int getY() {
         return this.y;
      }

      public static int accessSetY(MutableBlockPos pos, int n) {
         return pos.y = n;
      }

      @Override
      public int getZ() {
         return this.z;
      }

      public MutableBlockPos() {
         this(0, 0, 0);
      }
   }
}
