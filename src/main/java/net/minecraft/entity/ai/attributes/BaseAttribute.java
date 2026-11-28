package net.minecraft.entity.ai.attributes;

public abstract class BaseAttribute implements IAttribute {
   private final IAttribute parent;
   private final String unlocalizedName;
   private final double defaultValue;
   private boolean shouldWatch;

   @Override
   public String getAttributeUnlocalizedName() {
      return this.unlocalizedName;
   }

   public BaseAttribute(IAttribute attribute, String str, double d) {
      this.parent = attribute;
      this.unlocalizedName = str;
      this.defaultValue = d;
      if (str == null) {
         throw new IllegalArgumentException("Name cannot be null!");
      }
   }

   @Override
   public double getDefaultValue() {
      return this.defaultValue;
   }

   @Override
   public boolean getShouldWatch() {
      return this.shouldWatch;
   }

   @Override
   public IAttribute getParent() {
      return this.parent;
   }

   @Override
   public int hashCode() {
      return this.unlocalizedName.hashCode();
   }

   public BaseAttribute setShouldWatch(boolean flag) {
      this.shouldWatch = flag;
      return this;
   }

   @Override
   public boolean equals(Object obj) {
      return obj instanceof IAttribute && this.unlocalizedName.equals(((IAttribute)obj).getAttributeUnlocalizedName());
   }
}
