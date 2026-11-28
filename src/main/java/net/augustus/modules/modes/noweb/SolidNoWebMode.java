package net.augustus.modules.modes.noweb;

import net.augustus.events.EventBlockBoundingBox;
import net.augustus.events.EventUpdate;
import net.augustus.events.listeners.BlockBoundingBoxListener;
import net.augustus.modules.movement.NoWeb;
import net.augustus.settings.SettingGroup;
import net.minecraft.block.Block;
import net.minecraft.block.BlockWeb;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;

public class SolidNoWebMode extends AbstractNoWebMode implements BlockBoundingBoxListener {
   public SolidNoWebMode(NoWeb noWeb, SettingGroup doubleValue) {
      super(noWeb, doubleValue, "Solid");
   }

   @Override
   public void updateEventSubscriptions() {
      super.updateEventSubscriptions();
      this.toggleEventSubscription(3);
   }

   @Override
   public void onDisable() {
   }

   @Override
   public void onPreUpdate(EventUpdate event) {
   }

   @Override
   public void onBlockBoundingBox(EventBlockBoundingBox event) {
      if (event.getBlock() instanceof BlockWeb) {
         BlockPos blockPos = event.getBlockPos();
         event.setBoundingBox(new AxisAlignedBB(
            (double)blockPos.getX(), (double)blockPos.getY(), (double)blockPos.getZ(), (double)(blockPos.getX() + 1), (double)(blockPos.getY() + 1), (double)(blockPos.getZ() + 1)
         ));
      }
   }
}
