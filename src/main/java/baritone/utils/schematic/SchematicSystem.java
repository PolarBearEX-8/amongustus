package baritone.utils.schematic;

import baritone.api.command.registry.Registry;
import baritone.api.schematic.ISchematicSystem;
import baritone.api.schematic.format.ISchematicFormat;
import baritone.utils.schematic.format.DefaultSchematicFormats;
import java.io.File;
import java.util.Arrays;
import java.util.Optional;

public enum SchematicSystem implements ISchematicSystem {
   INSTANCE;
   private final Registry registry = new Registry();
   @Override
   public Optional getByFile(File file) {
      return this.registry.stream().filter(obj -> ((ISchematicFormat)obj).isFileType(file)).findFirst();
   }

private SchematicSystem() {
      Arrays.stream(DefaultSchematicFormats.values()).forEach(this.registry::register);
   }

   @Override
   public Registry getFileFormats() {
      return this.registry;
   }

}
