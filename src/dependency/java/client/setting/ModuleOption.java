package client.setting;

public class ModuleOption implements NamedOption {
   public final Class memberRef;
   public final String labelText;
   public boolean enabled = true;

   @Override
   public void setActive(boolean active) {
      this.enabled = active;
   }

   public static boolean checkModuleOption(ModuleOption moduleOption) {
      return moduleOption.enabled;
   }

   public static Class getClass(ModuleOption moduleOption) {
      return moduleOption.memberRef;
   }

   @Override
   public String getText() {
      return this.labelText;
   }

   @Override
   public boolean isSelected() {
      return this.enabled;
   }

   public ModuleOption(String text, Class clazz) {
      this.labelText = text;
      this.memberRef = clazz;
   }
}
