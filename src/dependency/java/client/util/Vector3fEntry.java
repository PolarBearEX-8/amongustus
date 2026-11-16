package client.util;

import client.setting.EnumFacingXType;
import org.lwjgl.util.vector.Vector3f;

public class Vector3fEntry {
   public final Vector3f vector3f;
   public final EnumFacingXType enumFacingXType;
   public final float factor;
   public final boolean enabled;

   public Vector3fEntry(Vector3f vector3f2, EnumFacingXType enumFacingXType2, float factor2, boolean flag) {
      this.vector3f = vector3f2;
      this.enumFacingXType = enumFacingXType2;
      this.factor = factor2;
      this.enabled = flag;
   }
}
