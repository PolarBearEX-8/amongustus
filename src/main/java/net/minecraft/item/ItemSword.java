package net.minecraft.item;

import com.google.common.collect.Multimap;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class ItemSword extends Item {
   private float attackDamage;
   private final Item.ToolMaterial material;

   public ItemSword(Item.ToolMaterial toolMaterial) {
      this.attackCooldownTicks = 12;
      this.material = toolMaterial;
      this.maxStackSize = 1;
      this.setMaxDamage(toolMaterial.getMaxUses());
      this.setCreativeTab(CreativeTabs.tabCombat);
      this.attackDamage = 4.0F + toolMaterial.getDamageVsEntity();
   }

   @Override
   public boolean isFull3D() {
      return true;
   }

   @Override
   public EnumAction getItemUseAction(ItemStack stack) {
      return EnumAction.BLOCK;
   }

   @Override
   public Multimap getItemAttributeModifiers() {
      Multimap itemAttributeModifiers = super.getItemAttributeModifiers();
      itemAttributeModifiers.put(
         SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(),
         new AttributeModifier(itemModifierUUID, "Weapon modifier", (double)this.attackDamage, 0)
      );
      return itemAttributeModifiers;
   }

   public float getAttackDamage() {
      return this.attackDamage;
   }

   @Override
   public float getStrVsBlock(ItemStack stack, Block block) {
      if (block == Blocks.web) {
         return 15.0F;
      } else {
         Material material2 = block.getMaterial();
         return material2 != Material.plants && material2 != Material.vine && material2 != Material.coral && material2 != Material.leaves && material2 != Material.gourd ? 1.0F : 1.5F;
      }
   }

   @Override
   public boolean onBlockDestroyed(ItemStack stack, World world, Block block, BlockPos pos, EntityLivingBase entity) {
      if ((double)block.getBlockHardness(world, pos) != 0.0) {
         stack.damageItem(2, entity);
      }

      return true;
   }

   @Override
   public int getMaxItemUseDuration(ItemStack stack) {
      return 72000;
   }

   public float getDamageVsEntity() {
      return this.material.getDamageVsEntity();
   }

   @Override
   public boolean getIsRepairable(ItemStack stack, ItemStack stack2) {
      return this.material.getRepairItem() == stack2.getItem() ? true : super.getIsRepairable(stack, stack2);
   }

   @Override
   public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
      player.setItemInUse(stack, this.getMaxItemUseDuration(stack));
      return stack;
   }

   @Override
   public boolean canHarvestBlock(Block block) {
      return block == Blocks.web;
   }

   @Override
   public int getItemEnchantability() {
      return this.material.getEnchantability();
   }

   public String getToolMaterialName() {
      return this.material.toString();
   }

   @Override
   public boolean hitEntity(ItemStack stack, EntityLivingBase entity, EntityLivingBase entity2) {
      stack.damageItem(1, entity2);
      return true;
   }
}
