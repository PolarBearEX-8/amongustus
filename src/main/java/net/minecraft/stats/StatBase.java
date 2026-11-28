package net.minecraft.stats;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import net.minecraft.event.HoverEvent;
import net.minecraft.scoreboard.IScoreObjectiveCriteria;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;

public class StatBase {
   public final String statId;
   private final IChatComponent statName;
   public boolean isIndependent;
   private final IStatType type;
   private final IScoreObjectiveCriteria objectiveCriteria;
   private Class serializableClazz;
   private static NumberFormat numberFormat = NumberFormat.getIntegerInstance(Locale.US);
   public static IStatType simpleStatType = new StatBase.DefaultStatType();
   private static DecimalFormat decimalFormat = new DecimalFormat("########0.00");
   public static IStatType timeStatType = new StatBase.DefaultStatType2();
   public static IStatType distanceStatType = new StatBase.DefaultStatType3();
   public static IStatType divideByTen = new StatBase.DefaultStatType4();

   @Override
   public int hashCode() {
      return this.statId.hashCode();
   }

   public boolean isAchievement() {
      return false;
   }

   public static NumberFormat accessNumberFormat() {
      return numberFormat;
   }

   public StatBase(String str, IChatComponent component, IStatType statType) {
      this.statId = str;
      this.statName = component;
      this.type = statType;
      this.objectiveCriteria = new ObjectiveStat(this);
      IScoreObjectiveCriteria.INSTANCES.put(this.objectiveCriteria.getName(), this.objectiveCriteria);
   }

   public StatBase setSerializableClazz(Class clazz) {
      this.serializableClazz = clazz;
      return this;
   }

   public StatBase registerStat() {
      if (StatList.oneShotStats.containsKey(this.statId)) {
         throw new RuntimeException(
            "Duplicate stat id: \"" + ((StatBase)StatList.oneShotStats.get(this.statId)).statName + "\" and \"" + this.statName + "\" at id " + this.statId
         );
      } else {
         StatList.allStats.add(this);
         StatList.oneShotStats.put(this.statId, this);
         return this;
      }
   }

   public static DecimalFormat accessDecimalFormat() {
      return decimalFormat;
   }

   public IChatComponent getStatName() {
      IChatComponent component = this.statName.copy();
      component.getChatStyle().setColor(EnumChatFormatting.GRAY);
      component.getChatStyle().setChatHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_ACHIEVEMENT, new ChatComponentText(this.statId)));
      return component;
   }

   public StatBase initIndependentStat() {
      this.isIndependent = true;
      return this;
   }

   public IChatComponent createChatComponent() {
      IChatComponent statName2 = this.getStatName();
      IChatComponent component = new ChatComponentText("[").appendSibling(statName2).appendText("]");
      component.setChatStyle(statName2.getChatStyle());
      return component;
   }

   public String format(int n) {
      return this.type.format(n);
   }

   @Override
   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (obj != null && this.getClass() == obj.getClass()) {
         StatBase statBase = (StatBase)obj;
         return this.statId.equals(statBase.statId);
      } else {
         return false;
      }
   }

   public Class getSerializableClazz() {
      return this.serializableClazz;
   }

   @Override
   public String toString() {
      return "Stat{id="
         + this.statId
         + ", nameId="
         + this.statName
         + ", awardLocallyOnly="
         + this.isIndependent
         + ", formatter="
         + this.type
         + ", objectiveCriteria="
         + this.objectiveCriteria
         + '}';
   }

   public StatBase(String str, IChatComponent component) {
      this(str, component, simpleStatType);
   }

   public IScoreObjectiveCriteria getObjectiveCriteria() {
      return this.objectiveCriteria;
   }


   public static final class DefaultStatType implements IStatType {
      @Override
      public String format(int n) {
         return StatBase.accessNumberFormat().format((long)n);
      }
   }

   public static final class DefaultStatType2 implements IStatType {

      @Override
      public String format(int n) {
         double d = (double)n / 20.0;
         double d2 = d / 60.0;
         double d3 = d2 / 60.0;
         double d4 = d3 / 24.0;
         double d5 = d4 / 365.0;
         return d5 > 0.5
            ? StatBase.accessDecimalFormat().format(d5) + " y"
            : (
               d4 > 0.5
                  ? StatBase.accessDecimalFormat().format(d4) + " d"
                  : (d3 > 0.5 ? StatBase.accessDecimalFormat().format(d3) + " h" : (d2 > 0.5 ? StatBase.accessDecimalFormat().format(d2) + " m" : d + " s"))
            );
      }
   }

   public static final class DefaultStatType3 implements IStatType {

      @Override
      public String format(int n) {
         double d = (double)n / 100.0;
         double d2 = d / 1000.0;
         return d2 > 0.5 ? StatBase.accessDecimalFormat().format(d2) + " km" : (d > 0.5 ? StatBase.accessDecimalFormat().format(d) + " m" : n + " cm");
      }
   }

   public static final class DefaultStatType4 implements IStatType {
      @Override
      public String format(int n) {
         return StatBase.accessDecimalFormat().format((double)n * 0.1);
      }
   }
}
