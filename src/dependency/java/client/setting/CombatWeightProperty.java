package client.setting;

import com.google.common.collect.Maps;
import java.util.Map;
import net.minecraft.item.ItemCookedFish;
import net.minecraft.item.ItemStack;

public enum CombatWeightProperty {
    COD(0, "cod", 2, 0.1F, 5, 0.6F),
    SALMON(1, "salmon", 2, 0.1F, 6, 0.8F),
    CLOWNFISH(2, "clownfish", 1, 0.1F),
    PUFFERFISH(3, "pufferfish", 1, 0.1F);

   public static final Map map = Maps.newHashMap();
   public final int count2;
   public final String labelText;
   public final int count;
   public final float factor;
   public final int count3;
   public final float factor2;
   public boolean enabled = false;
   public static final CombatWeightProperty[] ENUM_VALUES = new CombatWeightProperty[]{COD, SALMON, CLOWNFISH, PUFFERFISH};

   public int getCount3() {
      return this.count3;
   }

   public float getFactor2() {
      return this.factor2;
   }

   public int getCount2() {
      return this.count2;
   }

   public int getCount() {
      return this.count;
   }

   public float getFactor() {
      return this.factor;
   }

   public static CombatWeightProperty resolveCombatWeightProperty(ItemStack stack) {
      return stack.getItem() instanceof ItemCookedFish ? resolveCombatWeightProperty(stack.getIndexOffset()) : COD;
   }

   public boolean isEnabled() {
      return this.enabled;
   }

   public static CombatWeightProperty resolveCombatWeightProperty(int count4) {
      CombatWeightProperty combatWeightProperty = (CombatWeightProperty)map.get(count4);
      return combatWeightProperty == null ? COD : combatWeightProperty;
   }

   public String getLabelText() {
      return this.labelText;
   }

   private CombatWeightProperty(int count4, String text, int count5, float factor3) {
      this.count2 = count4;
      this.labelText = text;
      this.count = count5;
      this.factor = factor3;
      this.count3 = 0;
      this.factor2 = 0.0F;
      this.enabled = false;
   }

   private CombatWeightProperty(int count4, String text, int count5, float factor3, int count6, float factor4) {
      this.count2 = count4;
      this.labelText = text;
      this.count = count5;
      this.factor = factor3;
      this.count3 = count6;
      this.factor2 = factor4;
      this.enabled = true;
   }



   static {
      for (CombatWeightProperty entry : values()) {
               map.put(entry.getCount2(), entry);
            }
   }
}
