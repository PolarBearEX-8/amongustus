package baritone.selection;

import baritone.api.selection.ISelection;
import baritone.api.utils.BetterBlockPos;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Vec3i;

public class Selection implements ISelection {
   private final BetterBlockPos pos1;
   private final BetterBlockPos pos2;
   private final BetterBlockPos min;
   private final BetterBlockPos max;
   private final Vec3i size;
   private final AxisAlignedBB aabb;

   @Override
   public ISelection contract(EnumFacing facing, int n) {
      return this.isPos2(facing) ? new Selection(this.pos1.offset2(facing, n), this.pos2) : new Selection(this.pos1, this.pos2.offset2(facing, n));
   }

   @Override
   public Vec3i size() {
      return this.size;
   }

   @Override
   public ISelection shift(EnumFacing facing, int n) {
      return new Selection(this.pos1.offset2(facing, n), this.pos2.offset2(facing, n));
   }

   public Selection(BetterBlockPos betterBlockPos, BetterBlockPos betterBlockPos2) {
      this.pos1 = betterBlockPos;
      this.pos2 = betterBlockPos2;
      this.min = new BetterBlockPos(Math.min(betterBlockPos.x2, betterBlockPos2.x2), Math.min(betterBlockPos.y2, betterBlockPos2.y2), Math.min(betterBlockPos.z2, betterBlockPos2.z2));
      this.max = new BetterBlockPos(Math.max(betterBlockPos.x2, betterBlockPos2.x2), Math.max(betterBlockPos.y2, betterBlockPos2.y2), Math.max(betterBlockPos.z2, betterBlockPos2.z2));
      this.size = new Vec3i(this.max.x2 - this.min.x2 + 1, this.max.y2 - this.min.y2 + 1, this.max.z2 - this.min.z2 + 1);
      this.aabb = new AxisAlignedBB(this.min, this.max.add(1, 1, 1));
   }

   private boolean isPos2(EnumFacing facing) {
      boolean flag = facing.getAxisDirection().getOffset() < 0;
      switch (facing.getAxis()) {
         case X:
            return this.pos2.x2 > this.pos1.x2 ^ flag;
         case Y:
            return this.pos2.y2 > this.pos1.y2 ^ flag;
         case Z:
            return this.pos2.z2 > this.pos1.z2 ^ flag;
         default:
            throw new IllegalStateException("Bad EnumFacing$Axis");
      }
   }

   @Override
   public BetterBlockPos pos2() {
      return this.pos2;
   }

   @Override
   public ISelection expand(EnumFacing facing, int n) {
      return this.isPos2(facing) ? new Selection(this.pos1, this.pos2.offset2(facing, n)) : new Selection(this.pos1.offset2(facing, n), this.pos2);
   }

   @Override
   public AxisAlignedBB aabb() {
      return this.aabb;
   }

   @Override
   public BetterBlockPos max() {
      return this.max;
   }

   @Override
   public BetterBlockPos min() {
      return this.min;
   }

   @Override
   public int hashCode() {
      return this.pos1.hashCode() ^ this.pos2.hashCode();
   }

   @Override
   public String toString() {
      return String.format("Selection{pos1=%s,pos2=%s}", this.pos1, this.pos2);
   }

   @Override
   public BetterBlockPos pos1() {
      return this.pos1;
   }

}
