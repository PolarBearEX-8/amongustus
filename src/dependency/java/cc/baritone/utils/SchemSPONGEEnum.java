package cc.baritone.utils;

import client.util.LoadingNBTDataManager;
import java.io.IOException;
import java.io.InputStream;
import net.minecraft.nbt.NBTTagCompound;

import java.io.File;
import org.apache.commons.io.FilenameUtils;

public enum SchemSPONGEEnum implements HeuristicStrategy {
   MCEDIT("schematic") {
      @Override
      public AStarRegistry resolveAStarRegistry(InputStream inputStream) throws IOException {
         return new MaterialsAlphaIBlockStateInternalLis(LoadingNBTDataManager.resolveNBTTagCompound(inputStream));
      }
   },
   SPONGE("schem") {
      @Override
      public AStarRegistry resolveAStarRegistry(InputStream inputStream) throws IOException {
         NBTTagCompound tagCompound = LoadingNBTDataManager.resolveNBTTagCompound(inputStream);
         switch (tagCompound.computeCount("Version")) {
            case 1:
            case 2:
               return new WidthHeightIBlockStateInternalLis(tagCompound);
            default:
               throw new UnsupportedOperationException("Unsupported Version of a Sponge Schematic");
         }
      }
   },
   LITEMATICA("litematic") {
      @Override
      public AStarRegistry resolveAStarRegistry(InputStream inputStream) throws IOException {
         switch (LoadingNBTDataManager.resolveNBTTagCompound(inputStream).computeCount("Version")) {
            case 5:
            case 6:
               throw new UnsupportedOperationException("This litematic Verion is too new.");
            default:
               throw new UnsupportedOperationException("Unsuported Version of a Litematica Schematic");
         }
      }
   };

   public final String labelText;
   public static final SchemSPONGEEnum[] ENUM_VALUES = values();

   private SchemSPONGEEnum(String text) {
      this.labelText = text;
   }

   @Override
   public boolean matchesFile(File file) {
      return this.labelText.equalsIgnoreCase(FilenameUtils.getExtension(file.getAbsolutePath()));
   }


}
