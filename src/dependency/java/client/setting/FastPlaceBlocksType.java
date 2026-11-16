package client.setting;

import client.util.SmoothDamper;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemEgg;
import net.minecraft.item.ItemExperienceBottle;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemSnowball;

public enum FastPlaceBlocksType implements NamedOption {
    BLOCKS("Blocks", ItemBlock.class),
    XP_BOTTLES("XP-Bottles", ItemExperienceBottle.class),
    SNOWBALLS("SnowBalls", ItemSnowball.class),
    EGGS("Eggs", ItemEgg.class),
    POTIONS("Potions", ItemPotion.class);

   public String text;
   public boolean state;
   public Object entry;
   public RangeSetting rangeSetting;
   public RangeSetting rangeSetting2;
   public final SmoothDamper smoothDamper = new SmoothDamper(20, 0.0);
   public final SmoothDamper smoothDamper2 = new SmoothDamper(20, 0.0);
   public static final FastPlaceBlocksType[] ENUM_VALUES = new FastPlaceBlocksType[]{BLOCKS, XP_BOTTLES, SNOWBALLS, EGGS, POTIONS};

   @Override
   public void setActive(boolean active) {
      this.state = active;
   }

   @Override
   public String getText() {
      return this.text;
   }

   @Override
   public boolean isSelected() {
      return this.state;
   }

   private FastPlaceBlocksType(String text2, Object entry2) {
      this.text = text2;
      this.entry = entry2;
   }


}
