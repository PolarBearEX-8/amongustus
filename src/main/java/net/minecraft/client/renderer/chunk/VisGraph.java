package net.minecraft.client.renderer.chunk;

import java.util.ArrayDeque;
import java.util.BitSet;
import java.util.EnumSet;
import java.util.Set;
import net.augustus.modules.render.XRay;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IntegerCache;

public class VisGraph {
   private static final int DX = (int)Math.pow(16.0, 0.0);
   private static final int DZ = (int)Math.pow(16.0, 1.0);
   private static final int DY = (int)Math.pow(16.0, 2.0);
   private final BitSet bitSet = new BitSet(4096);
   private static final int[] INDEX_OF_EDGES = new int[1352];
   private int empty = 4096;

   static {
      int n = 0;

      for (int i = 0; i < 16; i++) {
         for (int j = 0; j < 16; j++) {
            for (int k = 0; k < 16; k++) {
               if (i == 0 || i == 15 || j == 0 || j == 15 || k == 0 || k == 15) {
                  INDEX_OF_EDGES[n++] = getIndex(i, j, k);
               }
            }
         }
      }
   }

   private void addEdges(int n, Set set) {
      int n2 = n >> 0 & 15;
      if (n2 == 0) {
         set.add(EnumFacing.WEST);
      } else if (n2 == 15) {
         set.add(EnumFacing.EAST);
      }

      int n3 = n >> 8 & 15;
      if (n3 == 0) {
         set.add(EnumFacing.DOWN);
      } else if (n3 == 15) {
         set.add(EnumFacing.UP);
      }

      int n4 = n >> 4 & 15;
      if (n4 == 0) {
         set.add(EnumFacing.NORTH);
      } else if (n4 == 15) {
         set.add(EnumFacing.SOUTH);
      }
   }

   public Set getVisibleFacings(BlockPos pos) {
      return this.floodFill(getIndex(pos));
   }

   public SetVisibility computeVisibility() {
      SetVisibility setVisibility = new SetVisibility();
      if (4096 - this.empty < 256) {
         setVisibility.setAllVisible(true);
      } else if (this.empty == 0) {
         setVisibility.setAllVisible(false);
      } else {
         for (int n : INDEX_OF_EDGES) {
            if (!this.bitSet.get(n)) {
               setVisibility.setManyVisible(this.floodFill(n));
            }
         }
      }

      return setVisibility;
   }

   public void setOpaqueCube(BlockPos pos) {
      if (!XRay.enabled) {
         this.bitSet.set(getIndex(pos), true);
         this.empty--;
      }
   }

   private Set floodFill(int n) {
      EnumSet enumSet = EnumSet.noneOf(EnumFacing.class);
      ArrayDeque arrayDeque = new ArrayDeque(384);
      arrayDeque.add(IntegerCache.getInteger(n));
      this.bitSet.set(n, true);

      while (!arrayDeque.isEmpty()) {
         int n2 = (Integer)arrayDeque.poll();
         this.addEdges(n2, enumSet);

         for (EnumFacing facing : EnumFacing.VALUES) {
            int neighborIndexAtFace = this.getNeighborIndexAtFace(n2, facing);
            if (neighborIndexAtFace >= 0 && !this.bitSet.get(neighborIndexAtFace)) {
               this.bitSet.set(neighborIndexAtFace, true);
               arrayDeque.add(IntegerCache.getInteger(neighborIndexAtFace));
            }
         }
      }

      return enumSet;
   }

   private static int getIndex(int n, int n2, int n3) {
      return n << 0 | n2 << 8 | n3 << 4;
   }

   private int getNeighborIndexAtFace(int n, EnumFacing facing) {
      switch (facing) {
         case DOWN:
            if ((n >> 8 & 15) == 0) {
               return -1;
            }

            return n - DY;
         case UP:
            if ((n >> 8 & 15) == 15) {
               return -1;
            }

            return n + DY;
         case NORTH:
            if ((n >> 4 & 15) == 0) {
               return -1;
            }

            return n - DZ;
         case SOUTH:
            if ((n >> 4 & 15) == 15) {
               return -1;
            }

            return n + DZ;
         case WEST:
            if ((n >> 0 & 15) == 0) {
               return -1;
            }

            return n - DX;
         case EAST:
            if ((n >> 0 & 15) == 15) {
               return -1;
            }

            return n + DX;
         default:
            return -1;
      }
   }

   private static int getIndex(BlockPos pos) {
      return getIndex(pos.getX() & 15, pos.getY() & 15, pos.getZ() & 15);
   }

}
