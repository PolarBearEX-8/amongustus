package net.augustus.settings;

public interface SelectableOption {
   boolean isSelected();

   default boolean isVisible() {
      return true;
   }

   void setSelected(boolean flag);

   String getName();
}
