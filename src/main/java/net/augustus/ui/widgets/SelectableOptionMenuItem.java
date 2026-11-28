package net.augustus.ui.widgets;

import net.augustus.ui.menu.ContextMenuItem;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import net.minecraft.util.EnumChatFormatting;

public class SelectableOptionMenuItem implements ContextMenuItem {
   private final Function labelToValueMapper;
   private final Function valueToLabelMapper;
   private final List options;
   private final Consumer selectionConsumer;
   private final Supplier currentSelectionSupplier;

   public SelectableOptionMenuItem(List list, Supplier supplier, Consumer consumer, Function function, Function function2) {
      this.options = new LinkedList(list);
      this.currentSelectionSupplier = supplier;
      this.selectionConsumer = consumer;
      this.valueToLabelMapper = function;
      this.labelToValueMapper = function2;
   }

   @Override
   public void onClick(String str) {
      this.selectionConsumer.accept(this.labelToValueMapper.apply(EnumChatFormatting.getTextWithoutFormattingCodes(str)));
   }

   @Override
   public Set getDisplayOptions() {
      LinkedHashSet set = new LinkedHashSet();

      for (Object obj : this.options) {
         String str = (String)this.valueToLabelMapper.apply(obj);
         if (obj.equals(this.currentSelectionSupplier.get())) {
            set.add("§c" + str);
         } else {
            set.add(str);
         }
      }

      return set;
   }
}
