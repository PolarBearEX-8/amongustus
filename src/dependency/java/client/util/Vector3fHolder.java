package client.util;

import java.util.Map;
import java.util.Map.Entry;
import net.minecraft.util.EnumFacing;
import org.lwjgl.util.vector.Vector3f;

public class Vector3fHolder {
   public final Vector3f vector3f2;
   public final Vector3f vector3f;
   public final Map map;
   public final Vector3fEntry vector3fEntry;
   public final boolean enabled;

   private float[] resolveFloatArray(EnumFacing enumFacing) {
      float[] factorArray;
      switch (FactoryIntArrayEntry.countArray[enumFacing.ordinal()]) {
         case 1:
         case 2:
            factorArray = new float[]{this.vector3f2.x, this.vector3f2.z, this.vector3f.x, this.vector3f.z};
            break;
         case 3:
         case 4:
            factorArray = new float[]{this.vector3f2.x, 16.0F - this.vector3f.y, this.vector3f.x, 16.0F - this.vector3f2.y};
            break;
         case 5:
         case 6:
            factorArray = new float[]{this.vector3f2.z, 16.0F - this.vector3f.y, this.vector3f.z, 16.0F - this.vector3f2.y};
            break;
         default:
            throw new NullPointerException();
      }

      return factorArray;
   }

   private void updateState() {
      for (Entry entry : (Iterable<Entry>)(Iterable<?>)this.map.entrySet()) {
         float[] factorArray = this.resolveFloatArray((EnumFacing)entry.getKey());
         ((EnumFacingHolder)entry.getValue()).tertiaryFloatArrayEntry.applyFloatArray(factorArray);
      }
   }

   public Vector3fHolder(Vector3f vector3f3, Vector3f vector3f4, Map map2, Vector3fEntry vector3fEntry2, boolean flag) {
      this.vector3f2 = vector3f3;
      this.vector3f = vector3f4;
      this.map = map2;
      this.vector3fEntry = vector3fEntry2;
      this.enabled = flag;
      this.updateState();
   }
}
