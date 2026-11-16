package client.setting;

import client.event.PrimaryListener;
import com.google.common.base.Predicate;
import com.google.common.collect.Maps;
import java.util.Map;
import net.minecraft.util.EnumFacing;

public enum EnumFacingXType implements Predicate, PrimaryListener {
   X("x", MovementFactorProperty.HORIZONTAL),
   Y("y", MovementFactorProperty.VERTICAL),
   Z("z", MovementFactorProperty.HORIZONTAL);

   public static final Map map = Maps.newHashMap();
   public final String labelText;
   public final MovementFactorProperty movementFactorProperty;
   public static final EnumFacingXType[] ENUM_VALUES = values();

   public String getLabelText() {
      return this.labelText;
   }

   @Override
   public String getText() {
      return this.labelText;
   }

   public boolean isActiveAsFlag() {
      return this.movementFactorProperty == MovementFactorProperty.HORIZONTAL;
   }

   public static EnumFacingXType resolveEnumFacingXType(String text) {
      return text == null ? null : (EnumFacingXType)map.get(text.toLowerCase());
   }

   public boolean matchesEnumFacing(EnumFacing enumFacing) {
      return enumFacing != null && enumFacing.getEnumFacingXType() == this;
   }

   public boolean isActive() {
      return this.movementFactorProperty == MovementFactorProperty.VERTICAL;
   }

   public MovementFactorProperty getMovementFactorProperty() {
      return this.movementFactorProperty;
   }

   private EnumFacingXType(String text, MovementFactorProperty movementFactorProperty2) {
      this.labelText = text;
      this.movementFactorProperty = movementFactorProperty2;
   }

   static {
      for (EnumFacingXType enumFacingXType : values()) {
         map.put(enumFacingXType.getLabelText().toLowerCase(), enumFacingXType);
      }
   }

   @Override
   public String toString() {
      return this.labelText;
   }

   public boolean apply(Object entry) {
      return this.matchesEnumFacing((EnumFacing)entry);
   }
}
