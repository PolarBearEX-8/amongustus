package client.setting;

public class AuxiliaryNamedOptionAdapter implements NamedOption {
   public final String labelText;
   public boolean enabled;
   public final TargetFilterSetting targetFilterSetting;

   @Override
   public void setActive(boolean active) {
      this.enabled = active;
   }

   @Override
   public String getText() {
      return this.labelText;
   }

   @Override
   public boolean isSelected() {
      return this.enabled;
   }

   public AuxiliaryNamedOptionAdapter(TargetFilterSetting setting, String text) {
      this.targetFilterSetting = setting;
      this.labelText = text;
      this.enabled = true;
   }
}
