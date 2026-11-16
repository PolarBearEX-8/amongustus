package client.setting;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.ArrayList;
import java.util.Map;

public enum AttackEffectsExplosionNormalType {
    EXPLOSION_NORMAL("explode", 0, true),
    EXPLOSION_LARGE("largeexplode", 1, true),
    EXPLOSION_HUGE("hugeexplosion", 2, true),
    FIREWORKS_SPARK("fireworksSpark", 3, false),
    WATER_BUBBLE("bubble", 4, false),
    WATER_SPLASH("splash", 5, false),
    WATER_WAKE("wake", 6, false),
    SUSPENDED("suspended", 7, false),
    SUSPENDED_DEPTH("depthsuspend", 8, false),
    CRIT("crit", 9, false),
    CRIT_MAGIC("magicCrit", 10, false),
    SMOKE_NORMAL("smoke", 11, false),
    SMOKE_LARGE("largesmoke", 12, false),
    SPELL("spell", 13, false),
    SPELL_INSTANT("instantSpell", 14, false),
    SPELL_MOB("mobSpell", 15, false),
    SPELL_MOB_AMBIENT("mobSpellAmbient", 16, false),
    SPELL_WITCH("witchMagic", 17, false),
    DRIP_WATER("dripWater", 18, false),
    DRIP_LAVA("dripLava", 19, false),
    VILLAGER_ANGRY("angryVillager", 20, false),
    VILLAGER_HAPPY("happyVillager", 21, false),
    TOWN_AURA("townaura", 22, false),
    NOTE("note", 23, false),
    PORTAL("portal", 24, false),
    ENCHANTMENT_TABLE("enchantmenttable", 25, false),
    FLAME("flame", 26, false),
    LAVA("lava", 27, false),
    FOOTSTEP("footstep", 28, false),
    CLOUD("cloud", 29, false),
    REDSTONE("reddust", 30, false),
    SNOWBALL("snowballpoof", 31, false),
    SNOW_SHOVEL("snowshovel", 32, false),
    SLIME("slime", 33, false),
    HEART("heart", 34, false),
    BARRIER("barrier", 35, false),
    ITEM_CRACK("iconcrack_", 36, false, 2),
    BLOCK_CRACK("blockcrack_", 37, false, 1),
    BLOCK_DUST("blockdust_", 38, false, 1),
    WATER_DROP("droplet", 39, false),
    ITEM_TAKE("take", 40, false),
    MOB_APPEARANCE("mobappearance", 41, true);

   public final String labelText;
   public final int count2;
   public final boolean enabled;
   public final int count;
   public static final Map map = Maps.newHashMap();
   public static final String[] stringArray;

   static {
      ArrayList list = Lists.newArrayList();
      for (AttackEffectsExplosionNormalType type : values()) {
         map.put(type.getIndex(), type);
         if (!type.getLabelText().endsWith("_")) {
            list.add(type.getLabelText());
         }
      }
      stringArray = (String[])list.toArray(new String[list.size()]);
   }

   public boolean isEnabled() {
      return this.enabled;
   }

   public static AttackEffectsExplosionNormalType resolveAttackEffectsExplosionNormal(int count3) {
      return (AttackEffectsExplosionNormalType)map.get(count3);
   }

   public int getCount() {
      return this.count;
   }

   public static String[] getStringArray() {
      return stringArray;
   }

   public String getLabelText() {
      return this.labelText;
   }

   public int getIndex() {
      return this.count2;
   }

   public boolean isActive() {
      return this.count > 0;
   }

   private AttackEffectsExplosionNormalType(String text, int count3, boolean flag) {
      this(text, count3, flag, 0);
   }

   private AttackEffectsExplosionNormalType(String text, int count3, boolean flag, int count4) {
      this.labelText = text;
      this.count2 = count3;
      this.enabled = flag;
      this.count = count4;
   }


}
