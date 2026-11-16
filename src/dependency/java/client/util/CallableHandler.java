package client.util;

import java.util.concurrent.Callable;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class CallableHandler implements Callable {
   public final BlockPos blockPos;
   public final World world;

   public String getText() throws Exception {
      return CrashReportCategory.resolveText(this.blockPos);
   }

   public CallableHandler(World world2, BlockPos blockPos2) {
      this.world = world2;
      this.blockPos = blockPos2;
   }

   @Override
   public Object call() throws Exception {
      return this.getText();
   }
}
