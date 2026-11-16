package net.minecraft.block;

import client.setting.AltOptionType;
import client.setting.BlockSolidType;
import client.setting.CombatFactorProperty;
import client.setting.CombatProfileProperty;
import client.setting.MovementColorProperty;
import client.setting.MovementProperty;
import client.util.AuxiliaryImmutableSetEntry;
import client.util.DampenerCalculator;
import client.util.IBlockState;
import client.util.ImmutableSetEntry;
import client.util.StatCraftItemManager;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFlowerpot;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockFlowerPot extends AdvancedBlockAdapter {
   public static final AuxiliaryImmutableSetEntry auxiliaryImmutableSetEntry = AuxiliaryImmutableSetEntry.resolveAuxiliaryImmutableSetEntry(
      "legacy_data", 0, 15
   );
   public static final ImmutableSetEntry immutableSetEntry = ImmutableSetEntry.resolveImmutableSetEntry("contents", AltOptionType.class);

   @Override
   public void processAction2() {
      float factor = 0.375F;
      float factor2 = factor / 2.0F;
      this.setBlockBounds(0.5F - factor2, 0.0F, 0.5F - factor2, 0.5F + factor2, factor, 0.5F + factor2);
   }

   @Override
   public BlockSolidType processAction3() {
      return BlockSolidType.CUTOUT;
   }

   @Override
   public boolean processAction4() {
      return true;
   }

   @Override
   public void processAction(World world, BlockPos blockPos, IBlockState blockState, EntityPlayer player) {
      super.processAction(world, blockPos, blockState, player);
      if (player.invulnerableManager.enabled2) {
         TileEntityFlowerpot tileEntityFlowerpot = this.resolveTileEntityFlowerpotByWorld(world, blockPos);
         if (tileEntityFlowerpot != null) {
            tileEntityFlowerpot.applyItem((Item)null, 0);
         }
      }
   }

   @Override
   public IBlockState processAction(IBlockState blockState, IBlockAccess iBlockAccess, int count, int count2, int count3) {
      AltOptionType altOptionType = AltOptionType.EMPTY;
      TileEntity tileEntity = iBlockAccess.resolveTileEntity(new BlockPos(count, count2, count3));
      if (tileEntity instanceof TileEntityFlowerpot) {
         TileEntityFlowerpot tileEntityFlowerpot = (TileEntityFlowerpot)tileEntity;
         Item item = tileEntityFlowerpot.getItem();
         if (item instanceof ItemBlock) {
            int count4 = tileEntityFlowerpot.getCount2();
            Block block = Block.resolveBlock(item);
            if (block == Blocks.block83) {
               switch (DampenerCalculator.countArray[MovementProperty.resolveMovementProperty(count4).ordinal()]) {
                  case 1:
                     altOptionType = AltOptionType.OAK_SAPLING;
                     break;
                  case 2:
                     altOptionType = AltOptionType.SPRUCE_SAPLING;
                     break;
                  case 3:
                     altOptionType = AltOptionType.BIRCH_SAPLING;
                     break;
                  case 4:
                     altOptionType = AltOptionType.JUNGLE_SAPLING;
                     break;
                  case 5:
                     altOptionType = AltOptionType.ACACIA_SAPLING;
                     break;
                  case 6:
                     altOptionType = AltOptionType.DARK_OAK_SAPLING;
                     break;
                  default:
                     altOptionType = AltOptionType.EMPTY;
               }
            } else if (block == Blocks.blockTallgrass) {
               switch (count4) {
                  case 0:
                     altOptionType = AltOptionType.DEAD_BUSH;
                     break;
                  case 2:
                     altOptionType = AltOptionType.FERN;
                     break;
                  default:
                     altOptionType = AltOptionType.EMPTY;
               }
            } else if (block == Blocks.immutableSetEntry2Entry) {
               altOptionType = AltOptionType.DANDELION;
            } else if (block == Blocks.immutableSetEntry2Entry2) {
               switch (DampenerCalculator.countArray2[CombatFactorProperty.resolveCombatFactorProperty(CombatProfileProperty.RED, count4).ordinal()]) {
                  case 1:
                     altOptionType = AltOptionType.POPPY;
                     break;
                  case 2:
                     altOptionType = AltOptionType.BLUE_ORCHID;
                     break;
                  case 3:
                     altOptionType = AltOptionType.ALLIUM;
                     break;
                  case 4:
                     altOptionType = AltOptionType.HOUSTONIA;
                     break;
                  case 5:
                     altOptionType = AltOptionType.RED_TULIP;
                     break;
                  case 6:
                     altOptionType = AltOptionType.ORANGE_TULIP;
                     break;
                  case 7:
                     altOptionType = AltOptionType.WHITE_TULIP;
                     break;
                  case 8:
                     altOptionType = AltOptionType.PINK_TULIP;
                     break;
                  case 9:
                     altOptionType = AltOptionType.OXEYE_DAISY;
                     break;
                  default:
                     altOptionType = AltOptionType.EMPTY;
               }
            } else if (block == Blocks.secondaryBlockAdapter) {
               altOptionType = AltOptionType.MUSHROOM_RED;
            } else if (block == Blocks.secondaryBlockAdapter2) {
               altOptionType = AltOptionType.MUSHROOM_BROWN;
            } else if (block == Blocks.blockDeadbush) {
               altOptionType = AltOptionType.DEAD_BUSH;
            } else if (block == Blocks.blockCactus) {
               altOptionType = AltOptionType.CACTUS;
            }
         }
      }

      return blockState.resolveIBlockState(immutableSetEntry, altOptionType);
   }

   @Override
   public int processAction2(World world, BlockPos blockPos) {
      TileEntityFlowerpot tileEntityFlowerpot = this.resolveTileEntityFlowerpotByWorld(world, blockPos);
      return tileEntityFlowerpot != null && tileEntityFlowerpot.getItem() != null ? tileEntityFlowerpot.getCount2() : 0;
   }

   @Override
   public boolean processAction(World world, BlockPos blockPos, IBlockState blockState, EntityPlayer player, EnumFacing enumFacing, float posX, float posY, float posZ) {
      ItemStack slot = player.inventoryPlayer.getCurrentItem();
      if (slot != null && slot.getItem() instanceof ItemBlock) {
         TileEntityFlowerpot tileEntityFlowerpot = this.resolveTileEntityFlowerpotByWorld(world, blockPos);
         if (tileEntityFlowerpot == null) {
            return false;
         } else if (tileEntityFlowerpot.getItem() != null) {
            return false;
         } else {
            Block block = Block.resolveBlock(slot.getItem());
            return this.matchesBlock(block, slot.getIndexOffset());
         }
      } else {
         return false;
      }
   }

   @Override
   public Item processAction4(World world, BlockPos blockPos) {
      TileEntityFlowerpot tileEntityFlowerpot = this.resolveTileEntityFlowerpotByWorld(world, blockPos);
      return tileEntityFlowerpot != null && tileEntityFlowerpot.getItem() != null ? tileEntityFlowerpot.getItem() : Items.flowerPot;
   }

   @Override
   public void onBlockAdded(World world, BlockPos blockPos, IBlockState blockState) {
      TileEntityFlowerpot tileEntityFlowerpot = this.resolveTileEntityFlowerpotByWorld(world, blockPos);
      if (tileEntityFlowerpot != null && tileEntityFlowerpot.getItem() != null) {
         applyWorld(world, blockPos, new ItemStack(tileEntityFlowerpot.getItem(), 1, tileEntityFlowerpot.getCount2()));
      }

      super.onBlockAdded(world, blockPos, blockState);
   }

   @Override
   public Item processAction(IBlockState blockState, Random random, int count) {
      return Items.flowerPot;
   }

   @Override
   public int processAction(IBlockAccess iBlockAccess, BlockPos blockPos, int count) {
      TileEntity tileEntity = iBlockAccess.resolveTileEntity(blockPos);
      if (tileEntity instanceof TileEntityFlowerpot) {
         Item item = ((TileEntityFlowerpot)tileEntity).getItem();
         if (item instanceof ItemBlock) {
            return Block.resolveBlock(item).processAction(iBlockAccess, blockPos, count);
         }
      }

      return 16777215;
   }

   @Override
   public void processAction(World world, BlockPos blockPos, IBlockState blockState, Block block) {
      if (!World.matchesIBlockAccess(world, blockPos.getX(), blockPos.getY() - 1, blockPos.getZ())) {
         this.processAction(world, blockPos, blockState, 0);
         world.matchesBlockPos8(blockPos);
      }
   }

   @Override
   public boolean onBlockActivated(World world, BlockPos blockPos, IBlockState blockState, EntityPlayer player, EnumFacing enumFacing, float posX, float posY, float posZ) {
      ItemStack slot = player.inventoryPlayer.getCurrentItem();
      if (slot != null && slot.getItem() instanceof ItemBlock) {
         TileEntityFlowerpot tileEntityFlowerpot = this.resolveTileEntityFlowerpotByWorld(world, blockPos);
         if (tileEntityFlowerpot == null) {
            return false;
         } else if (tileEntityFlowerpot.getItem() != null) {
            return false;
         } else {
            Block block = Block.resolveBlock(slot.getItem());
            if (!this.matchesBlock(block, slot.getIndexOffset())) {
               return false;
            } else {
               tileEntityFlowerpot.applyItem(slot.getItem(), slot.getIndexOffset());
               tileEntityFlowerpot.processAction5();
               world.applyBlockPos2(blockPos);
               player.applyDuplicateStatIdManagerByDuplicateStatIdM(StatCraftItemManager.duplicateStatIdManager28);
               if (!player.invulnerableManager.enabled2 && --slot.animationsToGo <= 0) {
                  player.inventoryPlayer.processAction(player.inventoryPlayer.currentItem, (ItemStack)null);
               }

               return true;
            }
         }
      } else {
         return false;
      }
   }

   @Override
   public TileEntity resolveTileEntityByWorld(World world, int count) {
      Object entry = null;
      int count2 = 0;
      switch (count) {
         case 1:
            entry = Blocks.immutableSetEntry2Entry2;
            count2 = CombatFactorProperty.POPPY.getCount();
            break;
         case 2:
            entry = Blocks.immutableSetEntry2Entry;
            break;
         case 3:
            entry = Blocks.block83;
            count2 = MovementProperty.OAK.getCount();
            break;
         case 4:
            entry = Blocks.block83;
            count2 = MovementProperty.SPRUCE.getCount();
            break;
         case 5:
            entry = Blocks.block83;
            count2 = MovementProperty.BIRCH.getCount();
            break;
         case 6:
            entry = Blocks.block83;
            count2 = MovementProperty.JUNGLE.getCount();
            break;
         case 7:
            entry = Blocks.secondaryBlockAdapter;
            break;
         case 8:
            entry = Blocks.secondaryBlockAdapter2;
            break;
         case 9:
            entry = Blocks.blockCactus;
            break;
         case 10:
            entry = Blocks.blockDeadbush;
            break;
         case 11:
            entry = Blocks.blockTallgrass;
            count2 = MovementColorProperty.FERN.getCount();
            break;
         case 12:
            entry = Blocks.block83;
            count2 = MovementProperty.ACACIA.getCount();
            break;
         case 13:
            entry = Blocks.block83;
            count2 = MovementProperty.DARK_OAK.getCount();
      }

      return new TileEntityFlowerpot(Item.processAction2((Block)entry), count2);
   }

   @Override
   public boolean processAction7(World world, BlockPos blockPos) {
      return super.processAction7(world, blockPos) && World.matchesIBlockAccess(world, blockPos.getX(), blockPos.getY() - 1, blockPos.getZ());
   }

   @Override
   public BlockManager processAction11() {
      return new BlockManager(this, new IProperty[]{immutableSetEntry, auxiliaryImmutableSetEntry});
   }

   @Override
   public int processAction13() {
      return 3;
   }

   @Override
   public int getIndex(IBlockState blockState) {
      return (Integer)blockState.getComparable(auxiliaryImmutableSetEntry);
   }

   private boolean matchesBlock(Block block, int count) {
      return block != Blocks.immutableSetEntry2Entry
            && block != Blocks.immutableSetEntry2Entry2
            && block != Blocks.blockCactus
            && block != Blocks.secondaryBlockAdapter2
            && block != Blocks.secondaryBlockAdapter
            && block != Blocks.block83
            && block != Blocks.blockDeadbush
         ? block == Blocks.blockTallgrass && count == MovementColorProperty.FERN.getCount()
         : true;
   }

   private TileEntityFlowerpot resolveTileEntityFlowerpotByWorld(World world, BlockPos blockPos) {
      TileEntity tileEntity = world.resolveTileEntity(blockPos);
      return tileEntity instanceof TileEntityFlowerpot ? (TileEntityFlowerpot)tileEntity : null;
   }

   @Override
   public String getText() {
      return I18n.getString2("item.flowerPot.name");
   }

   @Override
   public boolean processAction14() {
      return false;
   }

   @Override
   public boolean processAction15() {
      return false;
   }

   public BlockFlowerPot() {
      super(Material.material22);
      this.setIBlockState(this.blockManager.get().resolveIBlockState(immutableSetEntry, AltOptionType.EMPTY).resolveIBlockState(auxiliaryImmutableSetEntry, 0));
      this.processAction2();
   }
}
