package net.minecraft.item;

import client.setting.GlobalOptionType;
import client.util.InvalidOperationManager;
import com.google.common.collect.Multimap;
import java.util.Set;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class ItemTool extends Item {
   public Set set;
   public float factor = 4.0F;
   public float factor2;
   public GlobalOptionType globalOptionType;

   @Override
   public float processAction(ItemStack stack, Block block) {
      return this.set.contains(block) ? this.factor : 1.0F;
   }

   @Override
   public boolean isState4() {
      return true;
   }

   @Override
   public int processAction2() {
      return this.globalOptionType.getCount2();
   }

   @Override
   public boolean processAction(ItemStack stack, EntityLivingBase livingEntity, EntityLivingBase livingEntity2) {
      stack.applyEntityLivingBaseByEntityLivingBase(2, livingEntity2);
      return true;
   }

   public GlobalOptionType getGlobalOptionType() {
      return this.globalOptionType;
   }

   @Override
   public Multimap processAction5() {
      Multimap multimap = super.processAction5();
      multimap.put(SharedMonsterAttributes.extendedListener.getLabelText(), new InvalidOperationManager(uuid, "Tool modifier", (double)this.factor2, 0));
      return multimap;
   }

   @Override
   public boolean processAction(ItemStack stack, ItemStack stack2) {
      return this.globalOptionType.getItem() == stack2.getItem() ? true : super.processAction(stack, stack2);
   }

   @Override
   public boolean processAction(ItemStack stack, World world, Block block, BlockPos blockPos, EntityLivingBase livingEntity) {
      if ((double)block.processAction8(world, blockPos) != 0.0) {
         stack.applyEntityLivingBaseByEntityLivingBase(1, livingEntity);
      }

      return true;
   }

   public float getScaleFactor() {
      return this.factor2;
   }

   public String getText() {
      return this.globalOptionType.toString();
   }

   public ItemTool(float factor3, GlobalOptionType globalOptionType2, Set set2) {
      this.indexOffset = 20;
      this.globalOptionType = globalOptionType2;
      this.set = set2;
      this.indexOffset1 = 1;
      this.processAction3(globalOptionType2.getCount3());
      this.factor = globalOptionType2.getFactor();
      this.factor2 = factor3 + globalOptionType2.getFactor2();
      this.processAction(ItemGroupManager.itemGroupManager2);
   }
}
