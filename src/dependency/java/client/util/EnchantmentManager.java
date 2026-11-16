package client.util;

import client.setting.DisablerUndefinedType;
import client.setting.EnhancedOptionType;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;

public abstract class EnchantmentManager {
   public static final EnchantmentManager[] enchantmentManagerArray = new EnchantmentManager[256];
   public static final EnchantmentManager[] enchantmentManagerArray2;
   public static final Map map = Maps.newHashMap();
   public static final EnchantmentManager enchantmentManager6 = new WMAZOA32EnchantmentManager(0, new ResourceLocation("protection"), 10, 0);
   public static final EnchantmentManager enchantmentManager14 = new WMAZOA32EnchantmentManager(1, new ResourceLocation("fire_protection"), 5, 1);
   public static final EnchantmentManager enchantmentManager20 = new WMAZOA32EnchantmentManager(2, new ResourceLocation("feather_falling"), 5, 2);
   public static final EnchantmentManager enchantmentManager18 = new WMAZOA32EnchantmentManager(3, new ResourceLocation("blast_protection"), 2, 3);
   public static final EnchantmentManager enchantmentManager16 = new WMAZOA32EnchantmentManager(4, new ResourceLocation("projectile_protection"), 5, 4);
   public static final EnchantmentManager enchantmentManager21 = new ExtendedEnchantmentManagerAdapter(5, new ResourceLocation("respiration"), 2);
   public static final EnchantmentManager enchantmentManager9 = new RotationRegistry(6, new ResourceLocation("aqua_affinity"), 2);
   public static final EnchantmentManager enchantmentManager12 = new DamageThornsEnchantmentManager(7, new ResourceLocation("thorns"), 1);
   public static final EnchantmentManager enchantmentManager22 = new AngleCalculator(8, new ResourceLocation("depth_strider"), 2);
   public static final EnchantmentManager enchantmentManager2 = new JS7CManager(16, new ResourceLocation("sharpness"), 10, 0);
   public static final EnchantmentManager enchantmentManager13 = new JS7CManager(17, new ResourceLocation("smite"), 5, 1);
   public static final EnchantmentManager enchantmentManager7 = new JS7CManager(18, new ResourceLocation("bane_of_arthropods"), 5, 2);
   public static final EnchantmentManager enchantmentManager = new VectorPicker(19, new ResourceLocation("knockback"), 5);
   public static final EnchantmentManager enchantmentManager3 = new VectorSelector(20, new ResourceLocation("fire_aspect"), 2);
   public static final EnchantmentManager enchantmentManager23 = new VectorValidator(21, new ResourceLocation("looting"), 2, EnhancedOptionType.WEAPON);
   public static final EnchantmentManager enchantmentManager5 = new VectorChecker(32, new ResourceLocation("efficiency"), 10);
   public static final EnchantmentManager enchantmentManager4 = new RotationPipeline(33, new ResourceLocation("silk_touch"), 1);
   public static final EnchantmentManager enchantmentManager1 = new AuxiliaryEnchantmentManagerAdapter(34, new ResourceLocation("unbreaking"), 5);
   public static final EnchantmentManager enchantmentManager11 = new VectorValidator(35, new ResourceLocation("fortune"), 2, EnhancedOptionType.DIGGER);
   public static final EnchantmentManager enchantmentManager10 = new InternalEnchantmentManagerAdapter(48, new ResourceLocation("power"), 10);
   public static final EnchantmentManager enchantmentManager8 = new EnchantmentManagerAdapter(49, new ResourceLocation("punch"), 2);
   public static final EnchantmentManager enchantmentManager17 = new VectorReader(50, new ResourceLocation("flame"), 2);
   public static final EnchantmentManager enchantmentManager24 = new VectorWriter(51, new ResourceLocation("infinity"), 1);
   public static final EnchantmentManager enchantmentManager19 = new VectorValidator(
      61, new ResourceLocation("luck_of_the_sea"), 2, EnhancedOptionType.FISHING_ROD
   );
   public static final EnchantmentManager enchantmentManager15 = new VectorFilter(62, new ResourceLocation("lure"), 2, EnhancedOptionType.FISHING_ROD);
   public final int indexOffset;
   public final int count;
   public EnhancedOptionType enhancedOptionType;
   public String labelText;

   public String getString() {
      return "enchantment." + this.labelText;
   }

   public int getCountAsIndex() {
      return this.count;
   }

   public EnchantmentManager withLabelText(String text) {
      this.labelText = text;
      return this;
   }

   public void applyEntityLivingBase(EntityLivingBase livingEntity, Entity entity, int count2) {
   }

   public int getCount() {
      return 1;
   }

   public int computeCount(int count2, DamageSource damageSource) {
      return 0;
   }

   public void applyEntityLivingBase2(EntityLivingBase livingEntity, Entity entity, int count2) {
   }

   public boolean matchesEnchantmentManager(EnchantmentManager enchantmentManager25) {
      return this != enchantmentManager25;
   }

   public static EnchantmentManager resolveEnchantmentManager(int count2) {
      return count2 >= 0 && count2 < enchantmentManagerArray.length ? enchantmentManagerArray[count2] : null;
   }

   public int computeCount(int count2) {
      return this.computeCountByIndex(count2) + 5;
   }

   public static EnchantmentManager get(String text) {
      return (EnchantmentManager)map.get(new ResourceLocation(text));
   }

   public static Set keySet() {
      return map.keySet();
   }

   public boolean matchesItemStack(ItemStack stack) {
      return this.enhancedOptionType.matchesItem(stack.getItem());
   }

   public String resolveText(int count2) {
      String string = I18n.getString2(this.getString());
      return string + " " + I18n.getString2("enchantment.level." + count2);
   }

   public float computeFactor(int count2, DisablerUndefinedType disablerUndefinedType) {
      return 0.0F;
   }

   public int getCount2() {
      return 1;
   }

   public int computeCountByIndex(int count2) {
      return 1 + count2 * 10;
   }

   public EnchantmentManager(int count2, ResourceLocation location, int count3, EnhancedOptionType enhancedOptionType2) {
      this.indexOffset = count2;
      this.count = count3;
      this.enhancedOptionType = enhancedOptionType2;
      if (enchantmentManagerArray[count2] != null) {
         throw new IllegalArgumentException("Duplicate enchantment id!");
      } else {
         enchantmentManagerArray[count2] = this;
         map.put(location, this);
      }
   }

   static {
      ArrayList list = Lists.newArrayList();

      for (EnchantmentManager enchantmentManager25 : enchantmentManagerArray) {
         if (enchantmentManager25 != null) {
            list.add(enchantmentManager25);
         }
      }

      enchantmentManagerArray2 = ((EnchantmentManager[])list.toArray(new EnchantmentManager[list.size()]));
   }
}
