package net.minecraft.block;

import com.google.common.collect.Lists;
import java.util.List;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityNote;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;

public class BlockNote extends BlockContainer {
   private static final List INSTRUMENTS = Lists.newArrayList(new String[]{"harp", "bd", "snare", "hat", "bassattack"});

   @Override
   public void onNeighborBlockChange(World world, BlockPos pos, IBlockState state, Block block) {
      boolean isBlockPowered = world.isBlockPowered(pos);
      TileEntity tileEntity = world.getTileEntity(pos);
      if (tileEntity instanceof TileEntityNote) {
         TileEntityNote tileEntityNote = (TileEntityNote)tileEntity;
         if (tileEntityNote.previousRedstoneState != isBlockPowered) {
            if (isBlockPowered) {
               tileEntityNote.triggerNote(world, pos);
            }

            tileEntityNote.previousRedstoneState = isBlockPowered;
         }
      }
   }

   @Override
   public boolean canBlockBeActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing facing, float f, float f2, float f3) {
      return true;
   }

   private String getInstrument(int n) {
      if (n < 0 || n >= INSTRUMENTS.size()) {
         n = 0;
      }

      return (String)INSTRUMENTS.get(n);
   }

   @Override
   public TileEntity createNewTileEntity(World world, int n) {
      return new TileEntityNote();
   }

   @Override
   public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing facing, float f, float f2, float f3) {
      if (!world.isRemote) {
         TileEntity tileEntity = world.getTileEntity(pos);
         if (tileEntity instanceof TileEntityNote) {
            TileEntityNote tileEntityNote = (TileEntityNote)tileEntity;
            tileEntityNote.changePitch();
            tileEntityNote.triggerNote(world, pos);
            player.triggerAchievement(StatList.noteblockTuned);
         }
      }

      return true;
   }

   public BlockNote() {
      super(Material.wood);
      this.setCreativeTab(CreativeTabs.tabRedstone);
   }

   @Override
   public int getRenderType() {
      return 3;
   }

   @Override
   public boolean onBlockEventReceived(World world, BlockPos pos, IBlockState state, int n, int n2) {
      float f = (float)Math.pow(2.0, (double)(n2 - 12) / 12.0);
      world.playSoundEffect((double)pos.getX() + 0.5, (double)pos.getY() + 0.5, (double)pos.getZ() + 0.5, "note." + this.getInstrument(n), 3.0F, f);
      world.spawnParticle(
         EnumParticleTypes.NOTE, (double)pos.getX() + 0.5, (double)pos.getY() + 1.2, (double)pos.getZ() + 0.5, (double)n2 / 24.0, 0.0, 0.0, new int[0]
      );
      return true;
   }

   @Override
   public void onBlockClicked(World world, BlockPos pos, EntityPlayer player) {
      if (!world.isRemote) {
         TileEntity tileEntity = world.getTileEntity(pos);
         if (tileEntity instanceof TileEntityNote) {
            ((TileEntityNote)tileEntity).triggerNote(world, pos);
            player.triggerAchievement(StatList.noteblockPlayed);
         }
      }
   }
}
