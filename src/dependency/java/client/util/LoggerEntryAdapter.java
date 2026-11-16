package client.util;

import client.setting.CombatConfigProperty;
import client.setting.CombatGroupProperty;
import com.google.common.base.Predicate;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class LoggerEntryAdapter extends AlertNotificationSound {
   @Override
   public void applyEntity3(Entity entity) {
   }

   @Override
   public int computeCount(int count, int count2, int count3, int count4) {
      return 0;
   }

   @Override
   public void applyBlockPos(BlockPos blockPos) {
   }

   @Override
   public void applyClass(Class clazz, AxisAlignedBB boundingBox, List list, Predicate predicate) {
   }

   @Override
   public boolean checkIndex(int count, int count2) {
      return true;
   }

   @Override
   public boolean canExecute() {
      return true;
   }

   @Override
   public void applyTileEntity(TileEntity tileEntity) {
   }

   @Override
   public int computeCount(CombatConfigProperty combatConfigProperty, int count, int count2, int count3) {
      return combatConfigProperty.defaultLightValue;
   }

   @Override
   public void updateState2() {
   }

   @Override
   public boolean checkFlag(boolean flag) {
      return false;
   }

   @Override
   public void updateState5() {
   }

   @Override
   public void applyEntityByEntity(Entity entity, int count) {
   }

   @Override
   public void applyBlockPos(BlockPos blockPos, TileEntity tileEntity) {
   }

   @Override
   public void updateState8() {
   }

   @Override
   public boolean checkIndex2(int count, int count2) {
      return count == this.indexOffset1 && count2 == this.indexOffset;
   }

   @Override
   public void updateState9() {
   }

   @Override
   public void applyEntityByEntity(Entity entity, AxisAlignedBB boundingBox, List list, Predicate predicate) {
   }

   @Override
   public int computeCount2(int count, int count2, int count3) {
      return 255;
   }

   @Override
   public int computeCountByBlockPos(BlockPos blockPos) {
      return 0;
   }

   @Override
   public void applyCombatConfigProperty(CombatConfigProperty combatConfigProperty, int count, int count2, int count3, int count4) {
   }

   @Override
   public void updateState6() {
   }

   @Override
   public Block resolveBlock(BlockPos blockPos) {
      return Blocks.air;
   }

   @Override
   public TileEntity resolveTileEntity(BlockPos blockPos, CombatGroupProperty combatGroupProperty) {
      return null;
   }

   @Override
   public Random getRandom() {
      long value = 987234911L;
      return new Random(
         this.getWorld().getTimestamp()
               + (long)(this.indexOffset1 * this.indexOffset1 * 4987142)
               + (long)(this.indexOffset1 * 5947611)
               + (long)(this.indexOffset * this.indexOffset) * 4392871L
               + (long)(this.indexOffset * 389711)
            ^ value
      );
   }

   public boolean matchesBlockPos(BlockPos blockPos) {
      return false;
   }

   @Override
   public int computeCount(int count, int count2) {
      return 0;
   }

   @Override
   public void applyEntityByEntity(Entity entity) {
   }

   public LoggerEntryAdapter(World world, int count, int count2) {
      super(world, count, count2);
   }
}
