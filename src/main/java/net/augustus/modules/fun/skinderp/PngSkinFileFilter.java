package net.augustus.modules.fun.skinderp;

import java.io.File;
import javax.swing.filechooser.FileFilter;
import net.augustus.modules.fun.SkinDerp;
import net.augustus.modules.fun.Spammer;

public class PngSkinFileFilter extends FileFilter {
   public final SkinDerp skinDerp;

   public PngSkinFileFilter(SkinDerp skinDerp2) {
      this.skinDerp = skinDerp2;
   }

   @Override
   public boolean accept(File file) {
      return file.isDirectory() || file.getName().toLowerCase().endsWith(".png");
   }

   @Override
   public String getDescription() {
      return "PNG Skin Files (*.png)";
   }
}
