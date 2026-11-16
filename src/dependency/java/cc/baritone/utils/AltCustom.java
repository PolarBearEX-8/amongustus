package cc.baritone.utils;

import client.util.IBlockState;
import java.util.stream.Stream;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Vec3i;

public class AltCustom extends HeuristicResolver {
   public final ManagedListener[] managedListenerArray;

   @Override
   public boolean matchesIBlockStateByIBlockState(int count, int count2, int count3, IBlockState blockState) {
      for (ManagedListener managedListener : this.managedListenerArray) {
         if (count >= managedListener.getBetterBlockPosXSYSZSBlockPos3().count9
            && count2 >= managedListener.getBetterBlockPosXSYSZSBlockPos3().count12
            && count3 >= managedListener.getBetterBlockPosXSYSZSBlockPos3().count10
            && count <= managedListener.getBetterBlockPosXSYSZSBlockPos4().count9
            && count2 <= managedListener.getBetterBlockPosXSYSZSBlockPos4().count12
            && count3 <= managedListener.getBetterBlockPosXSYSZSBlockPos4().count10) {
            return true;
         }
      }

      return false;
   }

   private static ManagedListener[] resolveManagedListenerArray(int count) {
      return new ManagedListener[count];
   }

   public AltCustom(InternalListener internalListener, Vec3i vec, ManagedListener[] managedListenerArray2) {
      super(internalListener);
      this.managedListenerArray = Stream.of(managedListenerArray2).map(listener -> AltCustom.resolveManagedListener(vec, listener)).toArray(AltCustom::resolveManagedListenerArray);
   }

   private static ManagedListener resolveManagedListener(Vec3i vec, ManagedListener managedListener) {
      return managedListener.resolveManagedListener(EnumFacing.WEST, vec.getX())
         .resolveManagedListener(EnumFacing.DOWN, vec.getY())
         .resolveManagedListener(EnumFacing.NORTH, vec.getZ());
   }
}
