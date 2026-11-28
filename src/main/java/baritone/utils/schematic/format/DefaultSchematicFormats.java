package baritone.utils.schematic.format;


import baritone.api.schematic.IStaticSchematic;
import baritone.utils.schematic.format.defaults.MCEditSchematic;
import baritone.utils.schematic.format.defaults.SpongeSchematic;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import baritone.api.schematic.format.ISchematicFormat;
import java.io.File;
import org.apache.commons.io.FilenameUtils;

public enum DefaultSchematicFormats implements ISchematicFormat {
   MCEDIT("schematic") {
      @Override
         public IStaticSchematic parse(InputStream in) throws IOException {
            return new MCEditSchematic(CompressedStreamTools.readCompressed(in));
         }
   },
   SPONGE("schem") {
      @Override
         public IStaticSchematic parse(InputStream in) throws IOException {
            NBTTagCompound tag = CompressedStreamTools.readCompressed(in);
            switch (tag.getInteger("Version")) {
               case 1:
               case 2:
                  return new SpongeSchematic(tag);
               default:
                  throw new UnsupportedOperationException("Unsupported Version of a Sponge Schematic");
            }
         }
   },
   LITEMATICA("litematic") {
      @Override
         public IStaticSchematic parse(InputStream in) throws IOException {
            switch (CompressedStreamTools.readCompressed(in).getInteger("Version")) {
               case 5:
               case 6:
                  throw new UnsupportedOperationException("This litematic Verion is too new.");
               default:
                  throw new UnsupportedOperationException("Unsuported Version of a Litematica Schematic");
            }
         }
   };

   private final String fileExtension;
private DefaultSchematicFormats(String str) {
      this.fileExtension = str;
   }

   @Override
   public boolean isFileType(File file) {
      return this.fileExtension.equalsIgnoreCase(FilenameUtils.getExtension(file.getAbsolutePath()));
   }
}
