package baritone.api.schematic;

public abstract class AbstractSchematic implements ISchematic {
   public int x;
   public int y;
   public int z;

   @Override
   public int lengthZ() {
      return this.z;
   }

   @Override
   public int widthX() {
      return this.x;
   }

   public AbstractSchematic(int n, int n2, int n3) {
      this.x = n;
      this.y = n2;
      this.z = n3;
   }

   public AbstractSchematic() {
      this(0, 0, 0);
   }

   @Override
   public int heightY() {
      return this.y;
   }
}
