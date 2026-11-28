package net.augustus.cosmetics.model;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.Validate;

public class GeoVectorUtil {

   public static GeoVector3f copyVector(GeoVector3f geoVector3f) {
      return new GeoVector3f(geoVector3f.getX(), geoVector3f.getY(), geoVector3f.getZ());
   }

   public static GeoVector3f arrayToVector(float[] floats) {
      Validate.validIndex(ArrayUtils.toObject(floats), 2);
      return new GeoVector3f(floats[0], floats[1], floats[2]);
   }

   public static GeoVector3f convertDoubleToFloat(GeoVector3d geoVector3d) {
      return new GeoVector3f((float)geoVector3d.x, (float)geoVector3d.y, (float)geoVector3d.z);
   }

   public static GeoVector3d arrayToVector(double[] doubles) {
      Validate.validIndex(ArrayUtils.toObject(doubles), 2);
      return new GeoVector3d(doubles[0], doubles[1], doubles[2]);
   }



}
