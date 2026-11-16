package cc.baritone.utils;

import net.minecraft.util.BlockPos;

public interface WAITINGCANCELEDEnumService {
   WAITINGCANCELEDEnum getWAITINGCANCELEDEnum();

   boolean booleanValue();

   void updateState2();

   BlockPos getBlockPos();

   boolean isActive();

   void updateState();

   BetterBlockPosXSYSZSBlockPos getBetterBlockPosXSYSZSBlockPos2();

   BetterBlockPosXSYSZSBlockPos getBetterBlockPosXSYSZSBlockPos();

   double doubleValue();
}
