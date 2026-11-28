package net.augustus.settings.options;

import net.augustus.settings.SelectableOption;
import net.augustus.utils.target.TargetConditionOperator;
import java.util.function.BiPredicate;
import net.minecraft.entity.Entity;

public class EntityTargetFilterOption implements SelectableOption {
   private boolean visible = true;
   private boolean selected;
   public final TargetConditionOperator operator;
   private final BiPredicate predicate;
   private final String name;

   public EntityTargetFilterOption(String str, boolean flag, TargetConditionOperator targetConditionOperator, BiPredicate biPredicate) {
      this.name = str;
      this.selected = flag;
      this.predicate = biPredicate;
      this.operator = targetConditionOperator;
   }

   @Override
   public boolean isVisible() {
      return this.visible;
   }

   @Override
   public String getName() {
      return this.name;
   }

   @Override
   public boolean isSelected() {
      return this.selected;
   }

   public boolean matches(Entity entity) {
      return this.predicate.test(entity, this.selected && this.visible);
   }

   @Override
   public void setSelected(boolean flag) {
      this.selected = flag;
   }

   public EntityTargetFilterOption withVisible(boolean flag) {
      this.visible = flag;
      return this;
   }
}
