package baritone.pathing.calc.openset;

import baritone.pathing.calc.PathNode;
import java.util.Arrays;

public final class BinaryHeapOpenSet implements IOpenSet {
   private static final int INITIAL_CAPACITY = 1024;
   private PathNode[] array;
   private int size = 0;

   public int getSize() {
      return this.size;
   }

   @Override
   public final PathNode removeLowest() {
      if (this.size == 0) {
         throw new IllegalStateException();
      } else {
         PathNode pathNode = this.array[1];
         PathNode pathNode2 = this.array[this.size];
         this.array[1] = pathNode2;
         pathNode2.heapPosition = 1;
         this.array[this.size] = null;
         this.size--;
         pathNode.heapPosition = -1;
         if (this.size < 2) {
            return pathNode;
         } else {
            int n = 1;
            int n2 = 2;
            double combinedCost = pathNode2.combinedCost;

            do {
               PathNode pathNode3 = this.array[n2];
               double combinedCost2 = pathNode3.combinedCost;
               if (n2 < this.size) {
                  PathNode pathNode4 = this.array[n2 + 1];
                  double combinedCost3 = pathNode4.combinedCost;
                  if (combinedCost2 > combinedCost3) {
                     n2++;
                     combinedCost2 = combinedCost3;
                     pathNode3 = pathNode4;
                  }
               }

               if (combinedCost <= combinedCost2) {
                  break;
               }

               this.array[n] = pathNode3;
               this.array[n2] = pathNode2;
               pathNode2.heapPosition = n2;
               pathNode3.heapPosition = n;
               n = n2;
            } while ((n2 <<= 1) <= this.size);

            return pathNode;
         }
      }
   }

   @Override
   public final void update(PathNode pathNode) {
      int heapPosition = pathNode.heapPosition;
      int n = heapPosition >>> 1;
      double combinedCost = pathNode.combinedCost;

      for (PathNode i = this.array[n]; heapPosition > 1 && i.combinedCost > combinedCost; i = this.array[n]) {
         this.array[heapPosition] = i;
         this.array[n] = pathNode;
         pathNode.heapPosition = n;
         i.heapPosition = heapPosition;
         heapPosition = n;
         n >>>= 1;
      }
   }

   public BinaryHeapOpenSet() {
      this(1024);
   }

   @Override
   public final void insert(PathNode pathNode) {
      if (this.size >= this.array.length - 1) {
         this.array = Arrays.copyOf(this.array, this.array.length << 1);
      }

      this.size++;
      pathNode.heapPosition = this.size;
      this.array[this.size] = pathNode;
      this.update(pathNode);
   }

   public BinaryHeapOpenSet(int n) {
      this.array = new PathNode[n];
   }

   @Override
   public final boolean isEmpty() {
      return this.size == 0;
   }
}
