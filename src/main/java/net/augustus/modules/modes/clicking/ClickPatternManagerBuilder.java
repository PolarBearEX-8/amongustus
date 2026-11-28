package net.augustus.modules.modes.clicking;

import java.util.function.Consumer;
import net.augustus.settings.StringValue;

public class ClickPatternManagerBuilder {
   private Consumer onPatternChanged;
   private StringValue patternSelectorSetting = null;

   public ClickPatternManager build() {
      return new ClickPatternManager(this.patternSelectorSetting, this.onPatternChanged, null);
   }

   public ClickPatternManagerBuilder() {
      this.onPatternChanged = null;
   }

   public ClickPatternManagerBuilder withSelectorAndCallback(StringValue booleansSetting, Consumer consumer) {
      this.patternSelectorSetting = booleansSetting;
      this.onPatternChanged = consumer;
      return this;
   }
}
