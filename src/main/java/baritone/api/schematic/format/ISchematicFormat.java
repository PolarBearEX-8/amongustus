package baritone.api.schematic.format;

import baritone.api.schematic.IStaticSchematic;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public interface ISchematicFormat {
   boolean isFileType(File file);

   IStaticSchematic parse(InputStream in) throws IOException;
}
