package net.minecraft.block;

import com.viaversion.viaversion.api.protocol.version.ProtocolVersion;
import java.util.List;
import net.augustus.viaversion.ViaAugustus;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockLilyPad extends BlockBush {
   @Override
   public AxisAlignedBB getCollisionBoundingBox(World world, IBlockState state, int n, int n2, int n3) {
      return ViaAugustus.targetProtocolVersion.newerThanOrEqualTo(ProtocolVersion.v1_9)
         ? new AxisAlignedBB(
            (double)n + 0.0625, (double)n2 + 0.0, (double)n3 + 0.0625, (double)n + 0.9375, (double)n2 + 0.09375, (double)n3 + 0.9375
         )
         : new AxisAlignedBB(
            (double)n + this.minX,
            (double)n2 + this.minY,
            (double)n3 + this.minZ,
            (double)n + this.maxX,
            (double)n2 + this.maxY,
            (double)n3 + this.maxZ
         );
   }

   @Override
   public int colorMultiplier(IBlockAccess world, BlockPos pos, int n) {
      return 2129968;
   }

   @Override
   public boolean canPlaceBlockOn(Block block) {
      return block == Blocks.water;
   }

   @Override
   public int getBlockColor() {
      return 7455580;
   }

   @Override
   public boolean canBlockStay(World world, BlockPos pos, IBlockState state) {
      if (pos.getY() >= 0 && pos.getY() < 256) {
         IBlockState blockState2 = world.getBlockState(pos.down());
         return blockState2.getBlock().getMaterial() == Material.water && (Integer)blockState2.getValue(BlockLiquid.LEVEL) == 0;
      } else {
         return false;
      }
   }

   @Override
   public int getRenderColor(IBlockState state) {
      return 7455580;
   }

   public BlockLilyPad() {
      this.setCreativeTab(CreativeTabs.tabDecorations);
   }

   @Override
   public int getMetaFromState(IBlockState state) {
      return 0;
   }

   @Override
   public void addCollisionBoxesToList(World world, BlockPos pos, IBlockState state, AxisAlignedBB box, List list, Entity entity) {
      if (ViaAugustus.targetProtocolVersion.newerThanOrEqualTo(ProtocolVersion.v1_9)) {
         this.setBlockBounds2(0.0625, 0.0, 0.0625, 0.9375, 0.09375, 0.9375);
      } else {
         float f = 0.5F;
         float f2 = 0.015625F;
         this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f2, 0.5F + f);
      }

      if (!(entity instanceof EntityBoat)) {
         super.addCollisionBoxesToList(world, pos, state, box, list, entity);
      }
   }
}
