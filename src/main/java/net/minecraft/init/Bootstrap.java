package net.minecraft.init;

import com.mojang.authlib.GameProfile;
import java.util.Random;
import java.util.UUID;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.BlockPumpkin;
import net.minecraft.block.BlockSkull;
import net.minecraft.block.BlockTNT;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.dispenser.BehaviorDefaultDispenseItem;
import net.minecraft.dispenser.BehaviorProjectileDispense;
import net.minecraft.dispenser.IBehaviorDispenseItem;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.dispenser.IPosition;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.item.EntityExpBottle;
import net.minecraft.entity.item.EntityFireworkRocket;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityEgg;
import net.minecraft.entity.projectile.EntityPotion;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemMonsterPlacer;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityDispenser;
import net.minecraft.tileentity.TileEntitySkull;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.StringUtils;
import net.minecraft.world.World;
import java.io.PrintStream;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDispenser;
import net.minecraft.block.BlockFire;
import net.minecraft.item.Item;
import net.minecraft.stats.StatList;
import net.minecraft.util.LoggingPrintStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Bootstrap {
   private static final PrintStream SYSOUT = System.out;
   private static boolean alreadyRegistered = false;
   private static final Logger LOGGER = LogManager.getLogger();

   public static void register() {
      if (!alreadyRegistered) {
         alreadyRegistered = true;
         if (LOGGER.isDebugEnabled()) {
            redirectOutputToLog();
         }

         Block.registerBlocks();
         BlockFire.init();
         Item.registerItems();
         StatList.init();
         registerDispenserBehaviors();
      }
   }

   public static void printToSYSOUT(String str) {
      SYSOUT.println(str);
   }

   public static boolean isRegistered() {
      return alreadyRegistered;
   }

   private static void redirectOutputToLog() {
      System.setErr(new LoggingPrintStream("STDERR", System.err));
      System.setOut(new LoggingPrintStream("STDOUT", SYSOUT));
   }

   public static void registerDispenserBehaviors() {
      BlockDispenser.dispenseBehaviorRegistry.putObject(Items.arrow, new Bootstrap.DefaultBehaviorProjectileDispense());
      BlockDispenser.dispenseBehaviorRegistry.putObject(Items.egg, new Bootstrap.DefaultBehaviorProjectileDispense2());
      BlockDispenser.dispenseBehaviorRegistry.putObject(Items.snowball, new Bootstrap.DefaultBehaviorProjectileDispense3());
      BlockDispenser.dispenseBehaviorRegistry.putObject(Items.experience_bottle, new Bootstrap.DefaultBehaviorProjectileDispense4());
      BlockDispenser.dispenseBehaviorRegistry.putObject(Items.potionitem, new Bootstrap.DefaultBehaviorDispenseItem());
      BlockDispenser.dispenseBehaviorRegistry.putObject(Items.spawn_egg, new Bootstrap.DefaultBehaviorDefaultDispenseItem());
      BlockDispenser.dispenseBehaviorRegistry.putObject(Items.fireworks, new Bootstrap.DefaultBehaviorDefaultDispenseItem2());
      BlockDispenser.dispenseBehaviorRegistry.putObject(Items.fire_charge, new Bootstrap.DefaultBehaviorDefaultDispenseItem3());
      BlockDispenser.dispenseBehaviorRegistry.putObject(Items.boat, new Bootstrap.DefaultBehaviorDefaultDispenseItem4());
      Bootstrap.DefaultBehaviorDefaultDispenseItem5 obj = new Bootstrap.DefaultBehaviorDefaultDispenseItem5();
      BlockDispenser.dispenseBehaviorRegistry.putObject(Items.lava_bucket, obj);
      BlockDispenser.dispenseBehaviorRegistry.putObject(Items.water_bucket, obj);
      BlockDispenser.dispenseBehaviorRegistry.putObject(Items.bucket, new Bootstrap.DefaultBehaviorDefaultDispenseItem6());
      BlockDispenser.dispenseBehaviorRegistry.putObject(Items.flint_and_steel, new Bootstrap.DefaultBehaviorDefaultDispenseItem7());
      BlockDispenser.dispenseBehaviorRegistry.putObject(Items.dye, new Bootstrap.DefaultBehaviorDefaultDispenseItem8());
      BlockDispenser.dispenseBehaviorRegistry.putObject(Item.getItemFromBlock(Blocks.tnt), new Bootstrap.DefaultBehaviorDefaultDispenseItem9());
      BlockDispenser.dispenseBehaviorRegistry.putObject(Items.skull, new Bootstrap.DefaultBehaviorDefaultDispenseItem10());
      BlockDispenser.dispenseBehaviorRegistry.putObject(Item.getItemFromBlock(Blocks.pumpkin), new Bootstrap.DefaultBehaviorDefaultDispenseItem11());
   }


   public static final class DefaultBehaviorProjectileDispense extends BehaviorProjectileDispense {
      @Override
      public IProjectile getProjectileEntity(World world, IPosition position) {
         EntityArrow entityArrow = new EntityArrow(world, position.getX(), position.getY(), position.getZ());
         entityArrow.canBePickedUp = 1;
         return entityArrow;
      }
   }

   public static final class DefaultBehaviorProjectileDispense2 extends BehaviorProjectileDispense {
      @Override
      public IProjectile getProjectileEntity(World world, IPosition position) {
         return new EntityEgg(world, position.getX(), position.getY(), position.getZ());
      }
   }

   public static final class DefaultBehaviorProjectileDispense3 extends BehaviorProjectileDispense {
      @Override
      public IProjectile getProjectileEntity(World world, IPosition position) {
         return new EntitySnowball(world, position.getX(), position.getY(), position.getZ());
      }
   }

   public static final class DefaultBehaviorProjectileDispense4 extends BehaviorProjectileDispense {
      @Override
      public float getProjectileInaccuracy() {
         return super.getProjectileInaccuracy() * 0.5F;
      }

      @Override
      public IProjectile getProjectileEntity(World world, IPosition position) {
         return new EntityExpBottle(world, position.getX(), position.getY(), position.getZ());
      }

      @Override
      public float getProjectileVelocity() {
         return super.getProjectileVelocity() * 1.25F;
      }
   }

   public static final class DefaultBehaviorDispenseItem implements IBehaviorDispenseItem {
      private final BehaviorDefaultDispenseItem defaultBehavior = new BehaviorDefaultDispenseItem();

      @Override
      public ItemStack dispense(IBlockSource blockSource, ItemStack stack) {
         return ItemPotion.isSplash(stack.getMetadata()) ? new Bootstrap.DefaultBehaviorDispenseItem.DefaultBehaviorProjectileDispense(this, stack).dispense(blockSource, stack) : this.defaultBehavior.dispense(blockSource, stack);
      }


      public static class DefaultBehaviorProjectileDispense extends BehaviorProjectileDispense {
         public final ItemStack potionStack;
         public final Bootstrap.DefaultBehaviorDispenseItem parentBehavior;

         @Override
         public float getProjectileInaccuracy() {
            return super.getProjectileInaccuracy() * 0.5F;
         }

         public DefaultBehaviorProjectileDispense(Bootstrap.DefaultBehaviorDispenseItem obj, ItemStack stack) {
            this.parentBehavior = obj;
            this.potionStack = stack;
         }

         @Override
         public IProjectile getProjectileEntity(World world, IPosition position) {
            return new EntityPotion(world, position.getX(), position.getY(), position.getZ(), this.potionStack.copy());
         }

         @Override
         public float getProjectileVelocity() {
            return super.getProjectileVelocity() * 1.25F;
         }
      }
   }

   public static final class DefaultBehaviorDefaultDispenseItem extends BehaviorDefaultDispenseItem {
      @Override
      public ItemStack dispenseStack(IBlockSource blockSource, ItemStack stack) {
         EnumFacing facing = BlockDispenser.getFacing(blockSource.getBlockMetadata());
         double d = blockSource.getX() + (double)facing.getFrontOffsetX();
         double d2 = (double)((float)blockSource.getBlockPos().getY() + 0.2F);
         double d3 = blockSource.getZ() + (double)facing.getFrontOffsetZ();
         Entity entity = ItemMonsterPlacer.spawnCreature(blockSource.getWorld(), stack.getMetadata(), d, d2, d3);
         if (entity instanceof EntityLivingBase && stack.hasDisplayName()) {
            ((EntityLiving)entity).setCustomNameTag(stack.getDisplayName());
         }

         stack.splitStack(1);
         return stack;
      }
   }

   public static final class DefaultBehaviorDefaultDispenseItem2 extends BehaviorDefaultDispenseItem {
      @Override
      public ItemStack dispenseStack(IBlockSource blockSource, ItemStack stack) {
         EnumFacing facing = BlockDispenser.getFacing(blockSource.getBlockMetadata());
         double d = blockSource.getX() + (double)facing.getFrontOffsetX();
         double d2 = (double)((float)blockSource.getBlockPos().getY() + 0.2F);
         double d3 = blockSource.getZ() + (double)facing.getFrontOffsetZ();
         EntityFireworkRocket entityFireworkRocket = new EntityFireworkRocket(blockSource.getWorld(), d, d2, d3, stack);
         blockSource.getWorld().spawnEntityInWorld(entityFireworkRocket);
         stack.splitStack(1);
         return stack;
      }

      @Override
      public void playDispenseSound(IBlockSource blockSource) {
         blockSource.getWorld().playAuxSFX(1002, blockSource.getBlockPos(), 0);
      }
   }

   public static final class DefaultBehaviorDefaultDispenseItem3 extends BehaviorDefaultDispenseItem {
      @Override
      public void playDispenseSound(IBlockSource blockSource) {
         blockSource.getWorld().playAuxSFX(1009, blockSource.getBlockPos(), 0);
      }

      @Override
      public ItemStack dispenseStack(IBlockSource blockSource, ItemStack stack) {
         EnumFacing facing = BlockDispenser.getFacing(blockSource.getBlockMetadata());
         IPosition dispensePosition = BlockDispenser.getDispensePosition(blockSource);
         double d = dispensePosition.getX() + (double)((float)facing.getFrontOffsetX() * 0.3F);
         double d2 = dispensePosition.getY() + (double)((float)facing.getFrontOffsetY() * 0.3F);
         double d3 = dispensePosition.getZ() + (double)((float)facing.getFrontOffsetZ() * 0.3F);
         World world = blockSource.getWorld();
         Random rand = world.rand;
         double d4 = rand.nextGaussian() * 0.05 + (double)facing.getFrontOffsetX();
         double d5 = rand.nextGaussian() * 0.05 + (double)facing.getFrontOffsetY();
         double d6 = rand.nextGaussian() * 0.05 + (double)facing.getFrontOffsetZ();
         world.spawnEntityInWorld(new EntitySmallFireball(world, d, d2, d3, d4, d5, d6));
         stack.splitStack(1);
         return stack;
      }
   }

   public static final class DefaultBehaviorDefaultDispenseItem4 extends BehaviorDefaultDispenseItem {
      private final BehaviorDefaultDispenseItem defaultBehavior = new BehaviorDefaultDispenseItem();

      @Override
      public void playDispenseSound(IBlockSource blockSource) {
         blockSource.getWorld().playAuxSFX(1000, blockSource.getBlockPos(), 0);
      }

      @Override
      public ItemStack dispenseStack(IBlockSource blockSource, ItemStack stack) {
         EnumFacing facing = BlockDispenser.getFacing(blockSource.getBlockMetadata());
         World world = blockSource.getWorld();
         double d = blockSource.getX() + (double)((float)facing.getFrontOffsetX() * 1.125F);
         double d2 = blockSource.getY() + (double)((float)facing.getFrontOffsetY() * 1.125F);
         double d3 = blockSource.getZ() + (double)((float)facing.getFrontOffsetZ() * 1.125F);
         BlockPos pos = blockSource.getBlockPos().offset(facing);
         Material material = world.getBlockState(pos).getBlock().getMaterial();
         double d4;
         if (Material.water.equals(material)) {
            d4 = 1.0;
         } else {
            if (!Material.air.equals(material) || !Material.water.equals(world.getBlockState(pos.down()).getBlock().getMaterial())) {
               return this.defaultBehavior.dispense(blockSource, stack);
            }

            d4 = 0.0;
         }

         world.spawnEntityInWorld(new EntityBoat(world, d, d2 + d4, d3));
         stack.splitStack(1);
         return stack;
      }
   }

   public static final class DefaultBehaviorDefaultDispenseItem5 extends BehaviorDefaultDispenseItem {
      private final BehaviorDefaultDispenseItem defaultBehavior = new BehaviorDefaultDispenseItem();

      @Override
      public ItemStack dispenseStack(IBlockSource blockSource, ItemStack stack) {
         ItemBucket item = (ItemBucket)stack.getItem();
         BlockPos pos = blockSource.getBlockPos().offset(BlockDispenser.getFacing(blockSource.getBlockMetadata()));
         if (item.tryPlaceContainedLiquid(blockSource.getWorld(), pos)) {
            stack.setItem(Items.bucket);
            stack.stackSize = 1;
            return stack;
         } else {
            return this.defaultBehavior.dispense(blockSource, stack);
         }
      }
   }

   public static final class DefaultBehaviorDefaultDispenseItem6 extends BehaviorDefaultDispenseItem {
      private final BehaviorDefaultDispenseItem defaultBehavior = new BehaviorDefaultDispenseItem();

      @Override
      public ItemStack dispenseStack(IBlockSource blockSource, ItemStack stack) {
         World world = blockSource.getWorld();
         BlockPos pos = blockSource.getBlockPos().offset(BlockDispenser.getFacing(blockSource.getBlockMetadata()));
         IBlockState blockState = world.getBlockState(pos);
         Block block = blockState.getBlock();
         Material material = block.getMaterial();
         Item item;
         if (Material.water.equals(material) && block instanceof BlockLiquid && (Integer)blockState.getValue(BlockLiquid.LEVEL) == 0) {
            item = Items.water_bucket;
         } else {
            if (!Material.lava.equals(material) || !(block instanceof BlockLiquid) || (Integer)blockState.getValue(BlockLiquid.LEVEL) != 0) {
               return super.dispenseStack(blockSource, stack);
            }

            item = Items.lava_bucket;
         }

         world.setBlockToAir(pos);
         if (--stack.stackSize == 0) {
            stack.setItem(item);
            stack.stackSize = 1;
         } else if (((TileEntityDispenser)blockSource.getBlockTileEntity()).addItemStack(new ItemStack(item)) < 0) {
            this.defaultBehavior.dispense(blockSource, new ItemStack(item));
         }

         return stack;
      }
   }

   public static final class DefaultBehaviorDefaultDispenseItem7 extends BehaviorDefaultDispenseItem {
      private boolean succeeded = true;

      @Override
      public void playDispenseSound(IBlockSource blockSource) {
         if (this.succeeded) {
            blockSource.getWorld().playAuxSFX(1000, blockSource.getBlockPos(), 0);
         } else {
            blockSource.getWorld().playAuxSFX(1001, blockSource.getBlockPos(), 0);
         }
      }

      @Override
      public ItemStack dispenseStack(IBlockSource blockSource, ItemStack stack) {
         World world = blockSource.getWorld();
         BlockPos pos = blockSource.getBlockPos().offset(BlockDispenser.getFacing(blockSource.getBlockMetadata()));
         if (world.isAirBlock(pos)) {
            world.setBlockState(pos, Blocks.fire.getDefaultState());
            if (stack.attemptDamageItem(1, world.rand)) {
               stack.stackSize = 0;
            }
         } else if (world.getBlockState(pos).getBlock() == Blocks.tnt) {
            Blocks.tnt.onBlockDestroyedByPlayer(world, pos, Blocks.tnt.getDefaultState().withProperty(BlockTNT.EXPLODE, Boolean.TRUE));
            world.setBlockToAir(pos);
         } else {
            this.succeeded = false;
         }

         return stack;
      }
   }

   public static final class DefaultBehaviorDefaultDispenseItem8 extends BehaviorDefaultDispenseItem {
      private boolean succeeded = true;

      @Override
      public ItemStack dispenseStack(IBlockSource blockSource, ItemStack stack) {
         if (EnumDyeColor.WHITE == EnumDyeColor.byDyeDamage(stack.getMetadata())) {
            World world = blockSource.getWorld();
            BlockPos pos = blockSource.getBlockPos().offset(BlockDispenser.getFacing(blockSource.getBlockMetadata()));
            if (ItemDye.applyBonemeal(stack, world, pos)) {
               if (!world.isRemote) {
                  world.playAuxSFX(2005, pos, 0);
               }
            } else {
               this.succeeded = false;
            }

            return stack;
         } else {
            return super.dispenseStack(blockSource, stack);
         }
      }

      @Override
      public void playDispenseSound(IBlockSource blockSource) {
         if (this.succeeded) {
            blockSource.getWorld().playAuxSFX(1000, blockSource.getBlockPos(), 0);
         } else {
            blockSource.getWorld().playAuxSFX(1001, blockSource.getBlockPos(), 0);
         }
      }
   }

   public static final class DefaultBehaviorDefaultDispenseItem9 extends BehaviorDefaultDispenseItem {
      @Override
      public ItemStack dispenseStack(IBlockSource blockSource, ItemStack stack) {
         World world = blockSource.getWorld();
         BlockPos pos = blockSource.getBlockPos().offset(BlockDispenser.getFacing(blockSource.getBlockMetadata()));
         EntityTNTPrimed entityTNTPrimed = new EntityTNTPrimed(world, (double)pos.getX() + 0.5, (double)pos.getY(), (double)pos.getZ() + 0.5, (EntityLivingBase)null);
         world.spawnEntityInWorld(entityTNTPrimed);
         world.playSoundAtEntity(entityTNTPrimed, "game.tnt.primed", 1.0F, 1.0F);
         stack.stackSize--;
         return stack;
      }
   }

   public static final class DefaultBehaviorDefaultDispenseItem10 extends BehaviorDefaultDispenseItem {
      private boolean succeeded = true;

      @Override
      public ItemStack dispenseStack(IBlockSource blockSource, ItemStack stack) {
         World world = blockSource.getWorld();
         EnumFacing facing = BlockDispenser.getFacing(blockSource.getBlockMetadata());
         BlockPos pos = blockSource.getBlockPos().offset(facing);
         BlockSkull skull = Blocks.skull;
         if (!world.isAirBlock(pos) || !skull.canDispenserPlace(world, pos, stack)) {
            this.succeeded = false;
         } else if (!world.isRemote) {
            world.setBlockState(pos, skull.getDefaultState().withProperty(BlockSkull.FACING, EnumFacing.UP), 3);
            TileEntity tileEntity = world.getTileEntity(pos);
            if (tileEntity instanceof TileEntitySkull) {
               if (stack.getMetadata() == 3) {
                  GameProfile profile = null;
                  if (stack.hasTagCompound()) {
                     NBTTagCompound tagCompound = stack.getTagCompound();
                     if (tagCompound.hasKey("SkullOwner", 10)) {
                        profile = NBTUtil.readGameProfileFromNBT(tagCompound.getCompoundTag("SkullOwner"));
                     } else if (tagCompound.hasKey("SkullOwner", 8)) {
                        String string = tagCompound.getString("SkullOwner");
                        if (!StringUtils.isNullOrEmpty(string)) {
                           profile = new GameProfile((UUID)null, string);
                        }
                     }
                  }

                  ((TileEntitySkull)tileEntity).setPlayerProfile(profile);
               } else {
                  ((TileEntitySkull)tileEntity).setType(stack.getMetadata());
               }

               ((TileEntitySkull)tileEntity).setSkullRotation(facing.getOpposite().getHorizontalIndex() * 4);
               Blocks.skull.checkWitherSpawn(world, pos, (TileEntitySkull)tileEntity);
            }

            stack.stackSize--;
         }

         return stack;
      }

      @Override
      public void playDispenseSound(IBlockSource blockSource) {
         if (this.succeeded) {
            blockSource.getWorld().playAuxSFX(1000, blockSource.getBlockPos(), 0);
         } else {
            blockSource.getWorld().playAuxSFX(1001, blockSource.getBlockPos(), 0);
         }
      }
   }

   public static final class DefaultBehaviorDefaultDispenseItem11 extends BehaviorDefaultDispenseItem {
      private boolean succeeded = true;

      @Override
      public void playDispenseSound(IBlockSource blockSource) {
         if (this.succeeded) {
            blockSource.getWorld().playAuxSFX(1000, blockSource.getBlockPos(), 0);
         } else {
            blockSource.getWorld().playAuxSFX(1001, blockSource.getBlockPos(), 0);
         }
      }

      @Override
      public ItemStack dispenseStack(IBlockSource blockSource, ItemStack stack) {
         World world = blockSource.getWorld();
         BlockPos pos = blockSource.getBlockPos().offset(BlockDispenser.getFacing(blockSource.getBlockMetadata()));
         BlockPumpkin pumpkin = (BlockPumpkin)Blocks.pumpkin;
         if (world.isAirBlock(pos) && pumpkin.canDispenserPlace(world, pos)) {
            if (!world.isRemote) {
               world.setBlockState(pos, pumpkin.getDefaultState(), 3);
            }

            stack.stackSize--;
         } else {
            this.succeeded = false;
         }

         return stack;
      }
   }
}
