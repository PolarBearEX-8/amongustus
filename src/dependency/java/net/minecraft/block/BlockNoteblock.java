package net.minecraft.block;

import client.setting.AttackEffectsExplosionNormalType;
import client.util.IBlockState;
import client.util.StatCraftItemManager;
import com.google.common.collect.Lists;
import java.util.List;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemGroupManager;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityMusic;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class BlockNoteblock extends AdvancedBlockAdapter {
   public static final List list = Lists.newArrayList(new String[]{"harp", "bd", "snare", "hat", "bassattack"});

   @Override
   public void onBlockClicked(World world, BlockPos blockPos, EntityPlayer player) {
      if (!world.state) {
         TileEntity tileEntity = world.resolveTileEntity(blockPos);
         if (tileEntity instanceof TileEntityMusic) {
            ((TileEntityMusic)tileEntity).applyWorldByWorld(world, blockPos);
            player.applyDuplicateStatIdManagerByDuplicateStatIdM(StatCraftItemManager.duplicateStatIdManager24);
         }
      }
   }

   @Override
   public boolean processAction(World world, BlockPos blockPos, IBlockState blockState, EntityPlayer player, EnumFacing enumFacing, float posX, float posY, float posZ) {
      return true;
   }

   private String resolveText(int count) {
      int count2 = count;
      if (count < 0 || count >= list.size()) {
         count2 = 0;
      }

      return (String)list.get(count2);
   }

   @Override
   public void processAction(World world, BlockPos blockPos, IBlockState blockState, Block block) {
      boolean flag = world.matchesBlockPos7(blockPos);
      TileEntity tileEntity = world.resolveTileEntity(blockPos);
      if (tileEntity instanceof TileEntityMusic) {
         TileEntityMusic tileEntityMusic = (TileEntityMusic)tileEntity;
         if (tileEntityMusic.enabled2 != flag) {
            if (flag) {
               tileEntityMusic.applyWorldByWorld(world, blockPos);
            }

            tileEntityMusic.enabled2 = flag;
         }
      }
   }

   @Override
   public TileEntity resolveTileEntityByWorld(World world, int count) {
      return new TileEntityMusic();
   }

   @Override
   public boolean onBlockActivated(World world, BlockPos blockPos, IBlockState blockState, EntityPlayer player, EnumFacing enumFacing, float posX, float posY, float posZ) {
      if (!world.state) {
         TileEntity tileEntity = world.resolveTileEntity(blockPos);
         if (tileEntity instanceof TileEntityMusic) {
            TileEntityMusic tileEntityMusic = (TileEntityMusic)tileEntity;
            tileEntityMusic.updateState();
            tileEntityMusic.applyWorldByWorld(world, blockPos);
            player.applyDuplicateStatIdManagerByDuplicateStatIdM(StatCraftItemManager.duplicateStatIdManager13);
         }
      }

      return true;
   }

   @Override
   public int processAction13() {
      return 3;
   }

   @Override
   public boolean processAction(World world, BlockPos blockPos, IBlockState blockState, int count, int count2) {
      float factor = (float)Math.pow(2.0, (double)(count2 - 12) / 12.0);
      world.applyText((double)blockPos.getX() + 0.5, (double)blockPos.getY() + 0.5, (double)blockPos.getZ() + 0.5, "note." + this.resolveText(count), 3.0F, factor);
      world.applyAttackEffectsExplosionNorm(
         AttackEffectsExplosionNormalType.NOTE,
         (double)blockPos.getX() + 0.5,
         (double)blockPos.getY() + 1.2,
         (double)blockPos.getZ() + 0.5,
         (double)count2 / 24.0,
         0.0,
         0.0,
         new int[0]
      );
      return true;
   }

   public BlockNoteblock() {
      super(Material.material);
      this.processAction(ItemGroupManager.itemGroupManager9);
   }
}
