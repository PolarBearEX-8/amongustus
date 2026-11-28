package net.augustus.utils.pathfinding;

import java.util.ArrayList;

public class PathNode implements Comparable {
   private double fCost;
   public boolean recalculated;
   public double speedMultiplier;
   private PathNode parent;
   private double gCost;
   private double hCost;
   public long packedKey;
   private PathBlockPos pos;
   private ArrayList children = new ArrayList(8);

   public PathBlockPos getPos() {
      return this.pos;
   }

   public ArrayList getChildren() {
      return this.children;
   }

   public double getHCost() {
      return this.hCost;
   }

   public PathNode(PathNode pathNode, PathBlockPos pathBlockPos, double d, double d2, long l) {
      this.recalculated = false;
      this.parent = pathNode;
      this.pos = pathBlockPos;
      this.gCost = d;
      this.hCost = d2;
      this.fCost = d + d2;
      this.packedKey = l;
   }

   public PathNode getParent() {
      return this.parent;
   }

   public void setParent(PathNode pathNode) {
      this.parent = pathNode;
   }

   public double getGCost() {
      return this.gCost;
   }

   public void setGCost(double gcost) {
      this.gCost = gcost;
      this.fCost = gcost + this.hCost;
   }

   public void recalculateCost() {
      if (!this.recalculated) {
         this.parent.recalculateCost();
         PathBlockPos pos2 = this.pos;
         this.setGCost(this.parent.gCost + this.parent.pos.distanceTo(pos2) / this.speedMultiplier);
         this.recalculated = true;
      }
   }

   @Override
   public int compareTo(Object other) {
      return this.compareTo2((PathNode)other);
   }

   public double getFCost() {
      return this.fCost;
   }

   public int compareTo2(PathNode pathNode) {
      if (pathNode.fCost > this.fCost) {
         return -1;
      } else {
         return pathNode.fCost < this.fCost ? 1 : Double.compare(this.hCost, pathNode.hCost);
      }
   }
}
