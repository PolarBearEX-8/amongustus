package baritone.utils.pathing;

import net.minecraft.world.border.WorldBorder;

public class BetterWorldBorder {
   private final double minX;
   private final double maxX;
   private final double minZ;
   private final double maxZ;

   public boolean entirelyContains(int n, int n2) {
      return (double)(n + 1) > this.minX && (double)n < this.maxX && (double)(n2 + 1) > this.minZ && (double)n2 < this.maxZ;
   }

   public BetterWorldBorder(WorldBorder worldBorder) {
      this.minX = worldBorder.minX();
      this.maxX = worldBorder.maxX();
      this.minZ = worldBorder.minZ();
      this.maxZ = worldBorder.maxZ();
   }

   public boolean canPlaceAt(int n, int n2) {
      return (double)n > this.minX && (double)(n + 1) < this.maxX && (double)n2 > this.minZ && (double)(n2 + 1) < this.maxZ;
   }
}
