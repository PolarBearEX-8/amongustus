package net.minecraft.block;

import augustus.modules.movement.NoWeb;
import client.event.EventBlockCollision;
import client.event.EventStateUpdate;
import client.setting.SettingGroup;
import client.util.CommonUpdateStateListenerAdapter;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;

public class BlockCollisionHandler extends CommonUpdateStateListenerAdapter implements BlockCollisionListener {
   @Override
   public void onBlockCollision(EventBlockCollision event) {
      if (event.getBlock() instanceof BlockWeb) {
         BlockPos blockPos = event.getBlockPos();
         AxisAlignedBB boundingBox = new AxisAlignedBB(
            (double)blockPos.getX(), (double)blockPos.getY(), (double)blockPos.getZ(), (double)(blockPos.getX() + 1), (double)(blockPos.getY() + 1), (double)(blockPos.getZ() + 1)
         );
         event.setBoundingBox(boundingBox);
      }
   }

   @Override
   public void onUpdateState(EventStateUpdate event) {
   }

   @Override
   public void clear() {
   }

   @Override
   public void updateState2() {
      super.updateState2();
      byte byteValue = 3;
      this.applyIndex(byteValue);
   }

   public BlockCollisionHandler(NoWeb noWeb, SettingGroup settingGroup) {
      super(noWeb, settingGroup, "Solid");
   }
}
