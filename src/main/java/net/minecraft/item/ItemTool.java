package net.minecraft.item;

import com.google.common.collect.Multimap;
import java.util.Set;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class ItemTool extends Item {
   private Set effectiveBlocks;
   public float efficiencyOnProperMaterial = 4.0F;
   private float damageVsEntity;
   public Item.ToolMaterial toolMaterial;

   public String getToolMaterialName() {
      return this.toolMaterial.toString();
   }

   public Item.ToolMaterial getToolMaterial() {
      return this.toolMaterial;
   }

   @Override
   public boolean onBlockDestroyed(ItemStack stack, World world, Block block, BlockPos pos, EntityLivingBase entity) {
      if ((double)block.getBlockHardness(world, pos) != 0.0) {
         stack.damageItem(1, entity);
      }

      return true;
   }

   @Override
   public boolean hitEntity(ItemStack stack, EntityLivingBase entity, EntityLivingBase entity2) {
      stack.damageItem(2, entity2);
      return true;
   }

   public float getDamageVsEntity() {
      return this.damageVsEntity;
   }

   @Override
   public boolean getIsRepairable(ItemStack stack, ItemStack stack2) {
      return this.toolMaterial.getRepairItem() == stack2.getItem() ? true : super.getIsRepairable(stack, stack2);
   }

   @Override
   public boolean isFull3D() {
      return true;
   }

   @Override
   public Multimap getItemAttributeModifiers() {
      Multimap itemAttributeModifiers = super.getItemAttributeModifiers();
      itemAttributeModifiers.put(
         SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(),
         new AttributeModifier(itemModifierUUID, "Tool modifier", (double)this.damageVsEntity, 0)
      );
      return itemAttributeModifiers;
   }

   @Override
   public float getStrVsBlock(ItemStack stack, Block block) {
      return this.effectiveBlocks.contains(block) ? this.efficiencyOnProperMaterial : 1.0F;
   }

   @Override
   public int getItemEnchantability() {
      return this.toolMaterial.getEnchantability();
   }

   public ItemTool(float f, Item.ToolMaterial toolMaterial2, Set set) {
      this.attackCooldownTicks = 20;
      this.toolMaterial = toolMaterial2;
      this.effectiveBlocks = set;
      this.maxStackSize = 1;
      this.setMaxDamage(toolMaterial2.getMaxUses());
      this.efficiencyOnProperMaterial = toolMaterial2.getEfficiencyOnProperMaterial();
      this.damageVsEntity = f + toolMaterial2.getDamageVsEntity();
      this.setCreativeTab(CreativeTabs.tabTools);
   }
}
