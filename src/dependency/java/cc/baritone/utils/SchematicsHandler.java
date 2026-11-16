package cc.baritone.utils;

import cc.baritone.api.INACTIVEHandler;
import java.io.File;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.util.BlockPos;
import net.minecraft.util.Vec3i;

public interface SchematicsHandler extends INACTIVEHandler {
   void updateState2();

   List getList();

   boolean isEnabledAsFlag();

   void applyText(String text, InternalListener internalListener, Vec3i vec);

   boolean matchesText(String text, File file, Vec3i vec);

   void updateState3();

   void applyBlockPos(BlockPos blockPos, BlockPos blockPos2);

   @Deprecated
   default boolean matchesText(String text, BlockPos blockPos) {
      File file = new File(new File(Minecraft.getMinecraft().mcDataDir, "schematics"), text);
      return this.matchesText(text, file, blockPos);
   }
}
