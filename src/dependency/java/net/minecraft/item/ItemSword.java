package net.minecraft.item;

import client.setting.GlobalOptionType;
import client.setting.ItemNoneType;
import client.util.InvalidOperationManager;
import com.google.common.collect.Multimap;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class ItemSword extends Item {
   public float scaleFactor;
   public final GlobalOptionType globalOptionType;

   @Override
   public int processAction(ItemStack stack) {
      return 72000;
   }

   @Override
   public float processAction(ItemStack stack, Block block) {
      if (block == Blocks.web) {
         return 15.0F;
      } else {
         Material material = block.getMaterial();
         return material != Material.material24
               && material != Material.material7
               && material != Material.material16
               && material != Material.material9
               && material != Material.material13
            ? 1.0F
            : 1.5F;
      }
   }

   @Override
   public boolean isState4() {
      return true;
   }

   public float getFactor() {
      return this.globalOptionType.getFactor2();
   }

   public float getScaleFactor() {
      return this.scaleFactor;
   }

   @Override
   public boolean processAction(ItemStack stack, EntityLivingBase livingEntity, EntityLivingBase livingEntity2) {
      stack.applyEntityLivingBaseByEntityLivingBase(1, livingEntity2);
      return true;
   }

   @Override
   public boolean processAction(Block block) {
      return block == Blocks.web;
   }

   @Override
   public int processAction2() {
      return this.globalOptionType.getCount2();
   }

   @Override
   public boolean processAction(ItemStack stack, ItemStack stack2) {
      return this.globalOptionType.getItem() == stack2.getItem() ? true : super.processAction(stack, stack2);
   }

   @Override
   public boolean processAction(ItemStack stack, World world, Block block, BlockPos blockPos, EntityLivingBase livingEntity) {
      if ((double)block.processAction8(world, blockPos) != 0.0) {
         stack.applyEntityLivingBaseByEntityLivingBase(2, livingEntity);
      }

      return true;
   }

   @Override
   public Multimap processAction5() {
      Multimap multimap = super.processAction5();
      multimap.put(SharedMonsterAttributes.extendedListener.getLabelText(), new InvalidOperationManager(uuid, "Weapon modifier", (double)this.scaleFactor, 0));
      return multimap;
   }

   public String getText() {
      return this.globalOptionType.toString();
   }

   @Override
   public ItemNoneType processAction4(ItemStack stack) {
      return ItemNoneType.BLOCK;
   }

   @Override
   public ItemStack processAction3(ItemStack stack, World world, EntityPlayer player) {
      player.applyItemStack(stack, this.processAction(stack));
      return stack;
   }

   public ItemSword(GlobalOptionType globalOptionType2) {
      this.indexOffset = 12;
      this.globalOptionType = globalOptionType2;
      this.indexOffset1 = 1;
      this.processAction3(globalOptionType2.getCount3());
      this.processAction(ItemGroupManager.itemGroupManager4);
      this.scaleFactor = 4.0F + globalOptionType2.getFactor2();
   }
}
