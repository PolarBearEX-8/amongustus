package net.augustus.modules.render.blocktrace;

import java.util.ArrayList;
import java.util.List;
import net.augustus.utils.TimeHelper;
import net.augustus.utils.interfaces.MC;
import net.minecraft.util.BlockPos;

public class BlockTraceNode implements Comparable, MC {
   private float hCost;
   private float blockHardness;
   private final List neighbors;
   private float fCost;
   private final int minY;
   private float gCost;
   private BlockTraceNode blockTraceNode;
   private final TraceBlockPos traceBlockPos;

   public void setBlockTraceNode(BlockTraceNode blockTraceNode2) {
      this.blockTraceNode = blockTraceNode2;
   }

   public void setGCost(float gcost) {
      this.gCost = gcost;
      this.fCost = gcost + this.hCost;
   }

   public BlockTraceNode getBlockTraceNode() {
      return this.blockTraceNode;
   }

   public TraceBlockPos getTraceBlockPos() {
      return this.traceBlockPos;
   }

   public void setHCost(float hcost) {
      this.hCost = hcost;
      this.fCost = this.gCost + hcost;
   }

   public float getFCost() {
      return this.fCost;
   }

   public float getHCost() {
      return this.hCost;
   }

   public float getGCost() {
      return this.gCost;
   }

   public BlockTraceNode(TraceBlockPos traceBlockPos2, float f, float f2, BlockTraceNode blockTraceNode2, int n) {
       super();
      this.neighbors = new ArrayList();
      this.hCost = f2;
      this.traceBlockPos = traceBlockPos2;
      this.minY = n;
      this.blockHardness = mc.theWorld.getBlockState(traceBlockPos2.getBlockPos()).getBlock().getBlockHardness(null, null);
      this.gCost = f;
      this.blockTraceNode = blockTraceNode2;
      this.neighbors.clear();
      this.fCost = f + f2;
      this.addNeighborOffset((byte)1, (byte)0, (byte)0);
      this.addNeighborOffset((byte)-1, (byte)0, (byte)0);
      this.addNeighborOffset((byte)0, (byte)0, (byte)1);
      this.addNeighborOffset((byte)0, (byte)0, (byte)-1);
      this.addNeighborOffset((byte)0, (byte)1, (byte)0);
      this.addNeighborOffset((byte)0, (byte)-1, (byte)0);
   }

   private void addNeighborOffset(int n, int n2, int n3) {
      BlockPos pos = this.traceBlockPos.getBlockPos().add(n, n2, n3);
      if (pos.getY() >= this.minY) {
         if (!pos.equalsPos(this.traceBlockPos.getBlockPos())) {
            this.neighbors.add(new TraceBlockPos(pos));
         }
      }
   }

   @Override
   public int compareTo(Object other) {
      if (other instanceof BlockTraceNode) {
         if (((BlockTraceNode)other).getGCost() > this.getGCost()) {
            return -2;
         } else {
            return ((BlockTraceNode)other).getGCost() < this.getGCost() ? 2 : Double.compare((double)this.getHCost(), (double)((BlockTraceNode)other).getHCost());
         }
      } else {
         return 0;
      }
   }

   public List getNeighbors() {
      return this.neighbors;
   }
}
