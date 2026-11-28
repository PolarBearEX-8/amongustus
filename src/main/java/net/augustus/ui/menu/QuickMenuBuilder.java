package net.augustus.ui.menu;

import net.augustus.ui.sidebar.BooleanSidebarItem;
import net.augustus.ui.sidebar.CycleOptionSidebarItem;
import net.augustus.ui.sidebar.DynamicActionSidebarItem;
import net.augustus.ui.sidebar.HeaderSidebarItem;
import net.augustus.ui.widgets.QuickActionsSidebar;
import net.augustus.ui.widgets.SelectableOptionMenuItem;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import net.augustus.settings.BooleanValue;

public class QuickMenuBuilder {
   private final List items = new LinkedList();

   public QuickMenuBuilder addSpacer() {
      this.items.add(new SpacerContextMenuItem());
      return this;
   }

   public QuickMenuBuilder addAction(String str, Runnable runnable) {
      this.items.add(new ActionContextMenuItem(str, runnable));
      return this;
   }

   @Deprecated
   public QuickMenuBuilder addDynamicAction(Supplier supplier, Runnable runnable) {
      this.items.add(new DynamicActionSidebarItem(supplier, runnable));
      return this;
   }

   public QuickMenuBuilder addHeader(String str) {
      this.items.add(new HeaderSidebarItem(str));
      return this;
   }

   public QuickMenuBuilder addSelectableOptions(List list, Supplier supplier, Consumer consumer, Function function, Function function2) {
      this.items.add(new SelectableOptionMenuItem(list, supplier, consumer, function, function2));
      return this;
   }

   public QuickMenuBuilder addStringOptions(List list, Supplier supplier, Consumer consumer) {
      this.items.add(new SelectableOptionMenuItem(list, supplier, consumer, Function.identity(), Function.identity()));
      return this;
   }

   public QuickMenuBuilder addCycleOption(String str, List list, Supplier supplier, Consumer consumer) {
      this.items.add(new CycleOptionSidebarItem(str, list, supplier, consumer));
      return this;
   }

   public QuickMenuBuilder addBooleanSetting(String str, BooleanValue booleanValue) {
      this.items.add(new BooleanSettingMenuItem(str, booleanValue));
      return this;
   }

   public QuickMenuBuilder addBooleanToggle(String str, Supplier supplier, Consumer consumer) {
      this.items.add(new BooleanSidebarItem(str, supplier, consumer));
      return this;
   }

   public QuickActionsSidebar build() {
      return new QuickActionsSidebar(this.items);
   }
}
