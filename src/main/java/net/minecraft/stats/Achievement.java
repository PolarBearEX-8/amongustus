package net.minecraft.stats;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.StatCollector;

public class Achievement extends StatBase {
   public final int displayColumn;
   public final int displayRow;
   public final Achievement parentAchievement;
   private final String achievementDescription;
   private IStatStringFormat statStringFormatter;
   public final ItemStack theItemStack;
   private boolean isSpecial;

   @Override
   public IChatComponent getStatName() {
      IChatComponent statName2 = super.getStatName();
      statName2.getChatStyle().setColor(this.getSpecial() ? EnumChatFormatting.DARK_PURPLE : EnumChatFormatting.GREEN);
      return statName2;
   }

   public Achievement setAchievementSerializableClass(Class clazz) {
      return (Achievement)super.setSerializableClazz(clazz);
   }

   public boolean getSpecial() {
      return this.isSpecial;
   }

   public Achievement setSpecial() {
      this.isSpecial = true;
      return this;
   }

   public Achievement(String str, String str2, int n, int n2, Item item, Achievement achievement) {
      this(str, str2, n, n2, new ItemStack(item), achievement);
   }

   public Achievement(String str, String str2, int n, int n2, Block block, Achievement achievement) {
      this(str, str2, n, n2, new ItemStack(block), achievement);
   }

   public Achievement registerAchievement() {
      super.registerStat();
      AchievementList.achievementList.add(this);
      return this;
   }

   @Override
   public StatBase setSerializableClazz(Class clazz) {
      return this.setAchievementSerializableClass(clazz);
   }

   public Achievement setStatStringFormatter(IStatStringFormat statStringFormat) {
      this.statStringFormatter = statStringFormat;
      return this;
   }

   public Achievement(String str, String str2, int n, int n2, ItemStack stack, Achievement achievement) {
      super(str, new ChatComponentTranslation("achievement." + str2, new Object[0]));
      this.theItemStack = stack;
      this.achievementDescription = "achievement." + str2 + ".desc";
      this.displayColumn = n;
      this.displayRow = n2;
      if (n < AchievementList.minDisplayColumn) {
         AchievementList.minDisplayColumn = n;
      }

      if (n2 < AchievementList.minDisplayRow) {
         AchievementList.minDisplayRow = n2;
      }

      if (n > AchievementList.maxDisplayColumn) {
         AchievementList.maxDisplayColumn = n;
      }

      if (n2 > AchievementList.maxDisplayRow) {
         AchievementList.maxDisplayRow = n2;
      }

      this.parentAchievement = achievement;
   }

   @Override
   public boolean isAchievement() {
      return true;
   }

   @Override
   public StatBase initIndependentStat() {
      return this.initIndependentAchievement();
   }

   public Achievement initIndependentAchievement() {
      this.isIndependent = true;
      return this;
   }

   public String getDescription() {
      return this.statStringFormatter != null
         ? this.statStringFormatter.formatString(StatCollector.translateToLocal(this.achievementDescription))
         : StatCollector.translateToLocal(this.achievementDescription);
   }

   @Override
   public StatBase registerStat() {
      return this.registerAchievement();
   }
}
