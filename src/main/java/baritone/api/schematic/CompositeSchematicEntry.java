package baritone.api.schematic;

public class CompositeSchematicEntry {
   public final ISchematic schematic;
   public final int x;
   public final int y;
   public final int z;

   public CompositeSchematicEntry(ISchematic schematic2, int n, int n2, int n3) {
      this.schematic = schematic2;
      this.x = n;
      this.y = n2;
      this.z = n3;
   }
}
