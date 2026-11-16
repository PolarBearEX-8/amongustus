package client.setting;

import com.google.common.collect.Maps;
import java.util.Map;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

public enum OptionType {
    X0_Y0(0, 0),
    X0_Y90(0, 90),
    X0_Y180(0, 180),
    X0_Y270(0, 270),
    X90_Y0(90, 0),
    X90_Y90(90, 90),
    X90_Y180(90, 180),
    X90_Y270(90, 270),
    X180_Y0(180, 0),
    X180_Y90(180, 90),
    X180_Y180(180, 180),
    X180_Y270(180, 270),
    X270_Y0(270, 0),
    X270_Y90(270, 90),
    X270_Y180(270, 180),
    X270_Y270(270, 270);

   public static final Map map = Maps.newHashMap();
   public final int count2;
   public final Matrix4f matrix4f;
   public final int count;
   public final int count3;
   public static final OptionType[] ENUM_VALUES = new OptionType[]{
      X0_Y0, X0_Y90, X0_Y180, X0_Y270, X90_Y0, X90_Y90, X90_Y180, X90_Y270, X180_Y0, X180_Y90, X180_Y180, X180_Y270, X270_Y0, X270_Y90, X270_Y180, X270_Y270
   };

   public EnumFacing resolveEnumFacingByEnumFacing(EnumFacing enumFacing) {
      return this.resolveEnumFacing(enumFacing);
   }

   public EnumFacing resolveEnumFacing(EnumFacing enumFacing) {
      EnumFacing enumFacing2 = enumFacing;

      for (int index = 0; index < this.count; index++) {
         enumFacing2 = enumFacing2.resolveEnumFacing(EnumFacingXType.X);
      }

      if (enumFacing2.getEnumFacingXType() != EnumFacingXType.Y) {
         for (int index2 = 0; index2 < this.count3; index2++) {
            enumFacing2 = enumFacing2.resolveEnumFacing(EnumFacingXType.Y);
         }
      }

      return enumFacing2;
   }

   public Matrix4f getMatrix4fAsMatrix4f() {
      return this.matrix4f;
   }

   public int computeCountByEnumFacing(EnumFacing enumFacing, int count4) {
      return this.computeCount(enumFacing, count4);
   }

   public int computeCount(EnumFacing enumFacing, int count4) {
      int count5 = count4;
      if (enumFacing.getEnumFacingXType() == EnumFacingXType.X) {
         count5 = (count4 + this.count) % 4;
      }

      EnumFacing enumFacing2 = enumFacing;

      for (int index = 0; index < this.count; index++) {
         enumFacing2 = enumFacing2.resolveEnumFacing(EnumFacingXType.X);
      }

      if (enumFacing2.getEnumFacingXType() == EnumFacingXType.Y) {
         count5 = (count5 + this.count3) % 4;
      }

      return count5;
   }

   private static int computeCount(int count4, int count5) {
      return count4 * 360 + count5;
   }

   public static OptionType resolveOptionType(int count4, int count5) {
      return (OptionType)map.get(computeCount(MathHelper.processAction2(count4, 360), MathHelper.processAction2(count5, 360)));
   }

   public javax.vecmath.Matrix4f getMatrix4f() {
      return new javax.vecmath.Matrix4f();
   }

   private OptionType(int count4, int count5) {
      this.count2 = computeCount(count4, count5);
      this.matrix4f = new Matrix4f();
      Matrix4f matrix = new Matrix4f();
      matrix.setIdentity();
      Matrix4f.rotate((float)(-count4) * (float) (Math.PI / 180.0), new Vector3f(1.0F, 0.0F, 0.0F), matrix, matrix);
      this.count = MathHelper.computeCount(count4 / 90);
      Matrix4f matrix2 = new Matrix4f();
      matrix2.setIdentity();
      Matrix4f.rotate((float)(-count5) * (float) (Math.PI / 180.0), new Vector3f(0.0F, 1.0F, 0.0F), matrix2, matrix2);
      this.count3 = MathHelper.computeCount(count5 / 90);
      Matrix4f.mul(matrix2, matrix, this.matrix4f);
   }



   static {
      for (OptionType entry : values()) {
               map.put(entry.count2, entry);
            }
   }
}
