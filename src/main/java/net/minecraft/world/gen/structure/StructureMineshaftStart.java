package net.minecraft.world.gen.structure;

import java.util.Random;
import net.minecraft.world.World;

public class StructureMineshaftStart extends StructureStart {
   public StructureMineshaftStart(World world, Random rand, int n, int n2) {
      super(n, n2);
      StructureMineshaftPieces.Room room = new StructureMineshaftPieces.Room(0, rand, (n << 4) + 2, (n2 << 4) + 2);
      this.components.add(room);
      room.buildComponent(room, this.components, rand);
      this.updateBoundingBox();
      this.markAvailableHeight(world, rand, 10);
   }

   public StructureMineshaftStart() {
   }
}
