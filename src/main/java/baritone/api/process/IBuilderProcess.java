package baritone.api.process;

import baritone.api.schematic.ISchematic;
import java.io.File;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.util.BlockPos;
import net.minecraft.util.Vec3i;

public interface IBuilderProcess extends IBaritoneProcess {
   String f = "schematics";

   boolean isPaused();

   List getApproxPlaceable();

   void pause();

   boolean build(String str, File file, Vec3i vec);

   void resume();

   @Deprecated
   default boolean build(String str, BlockPos pos) {
      return this.build(str, new File(new File(Minecraft.getMinecraft().mcDataDir, f), str), pos);
   }

   void clearArea(BlockPos pos, BlockPos pos2);

   void build2(String str, ISchematic schematic, Vec3i vec);
}
