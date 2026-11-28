package net.augustus.ui.menu;

import net.augustus.ui.sidebar.BooleanSidebarItem;
import java.util.function.Supplier;
import net.augustus.settings.BooleanValue;

public class BooleanSettingMenuItem extends BooleanSidebarItem {

   public BooleanSettingMenuItem(String str, BooleanValue booleanValue) {
       super(str, (() -> (booleanValue).getValue()), obj -> (booleanValue).setValue((boolean)obj));
       Supplier supplier = () -> booleanValue.getValue();
       BooleanValue booleanValue3 = booleanValue;
   }
}
