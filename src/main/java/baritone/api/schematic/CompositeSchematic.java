package baritone.api.schematic;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.state.IBlockState;

public class CompositeSchematic extends AbstractSchematic {
   private final List schematics = new ArrayList();
   private CompositeSchematicEntry[] schematicArr;

   private void recalcArr() {
      this.schematicArr = (CompositeSchematicEntry[])this.schematics.toArray(new CompositeSchematicEntry[0]);

      for (CompositeSchematicEntry compositeSchematicEntry : this.schematicArr) {
         this.x = Math.max(this.x, compositeSchematicEntry.x + compositeSchematicEntry.schematic.widthX());
         this.y = Math.max(this.y, compositeSchematicEntry.y + compositeSchematicEntry.schematic.heightY());
         this.z = Math.max(this.z, compositeSchematicEntry.z + compositeSchematicEntry.schematic.lengthZ());
      }
   }

   private CompositeSchematicEntry getSchematic(int n, int n2, int n3, IBlockState state) {
      for (CompositeSchematicEntry compositeSchematicEntry : this.schematicArr) {
         if (n >= compositeSchematicEntry.x && n2 >= compositeSchematicEntry.y && n3 >= compositeSchematicEntry.z && compositeSchematicEntry.schematic.inSchematic(n - compositeSchematicEntry.x, n2 - compositeSchematicEntry.y, n3 - compositeSchematicEntry.z, state)) {
            return compositeSchematicEntry;
         }
      }

      return null;
   }

   public void put(ISchematic schematic, int n, int n2, int n3) {
      this.schematics.add(new CompositeSchematicEntry(schematic, n, n2, n3));
      this.recalcArr();
   }

   @Override
   public IBlockState desiredState(int n, int n2, int n3, IBlockState state, List list) {
      CompositeSchematicEntry schematic = this.getSchematic(n, n2, n3, state);
      if (schematic == null) {
         throw new IllegalStateException("couldn't find schematic for this position");
      } else {
         return schematic.schematic.desiredState(n - schematic.x, n2 - schematic.y, n3 - schematic.z, state, list);
      }
   }

   public CompositeSchematic(int n, int n2, int n3) {
      super(n, n2, n3);
      this.recalcArr();
   }

   @Override
   public void reset() {
      for (CompositeSchematicEntry compositeSchematicEntry : this.schematicArr) {
         compositeSchematicEntry.schematic.reset();
      }
   }

   @Override
   public boolean inSchematic(int n, int n2, int n3, IBlockState state) {
      CompositeSchematicEntry schematic = this.getSchematic(n, n2, n3, state);
      return schematic != null && schematic.schematic.inSchematic(n - schematic.x, n2 - schematic.y, n3 - schematic.z, state);
   }
}
