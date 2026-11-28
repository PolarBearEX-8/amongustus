package baritone.api.schematic;

import baritone.api.command.registry.Registry;
import java.io.File;
import java.util.Optional;

public interface ISchematicSystem {
   Registry getFileFormats();

   Optional getByFile(File file);
}
