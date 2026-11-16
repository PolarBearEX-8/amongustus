package cc.baritone.utils;

@Annotation
public enum BreadthFirstRegistry {
    ENTITY(InstanceTypeImpl.INSTANCE),
    PLAYER(INSTANCEEnumBridge.INSTANCE);

   public final AuxiliaryListener10Extension auxiliaryListener10Extension;
   public static final BreadthFirstRegistry[] ENUM_VALUES = new BreadthFirstRegistry[]{ENTITY, PLAYER};

   private BreadthFirstRegistry(AuxiliaryListener10Extension auxiliaryListener10Extension2) {
      this.auxiliaryListener10Extension = auxiliaryListener10Extension2;
   }
}
