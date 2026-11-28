package net.augustus.cosmetics.model;

import java.util.HashMap;
import java.util.Map;
import net.labymod.Bone;
import net.labymod.Cube;
import net.labymod.ModelProperties;
import org.apache.commons.lang3.ArrayUtils;

public class DefaultGeoModelBuilder implements GeoModelBuilder {
   private static Map registeredBuilders = new HashMap();
   private static GeoModelBuilder defaultBuilder = new DefaultGeoModelBuilder();

   public static GeoModelBuilder getGeoBuilder(String str) {
      GeoModelBuilder geoModelBuilder = (GeoModelBuilder)registeredBuilders.get(str);
      return geoModelBuilder == null ? defaultBuilder : geoModelBuilder;
   }

   public static void registerGeoBuilder(String str, GeoModelBuilder geoModelBuilder) {
      registeredBuilders.put(str, geoModelBuilder);
   }


   public GeoBone constructBone(RawBoneGroup rawBoneGroup, ModelProperties modelProperties, GeoBone geoBone) {
      GeoBone geoBone2 = new GeoBone();
      Bone selfBone = rawBoneGroup.selfBone;
      GeoVector3f geoVector3f = GeoVectorUtil.convertDoubleToFloat(GeoVectorUtil.arrayToVector(selfBone.getRotation()));
      GeoVector3f geoVector3f2 = GeoVectorUtil.convertDoubleToFloat(GeoVectorUtil.arrayToVector(selfBone.getPivot()));
      geoVector3f.x *= -1.0F;
      geoVector3f.y *= -1.0F;
      geoBone2.mirror = selfBone.getMirror();
      geoBone2.neverRender = selfBone.getNeverRender();
      geoBone2.reset = selfBone.getReset();
      geoBone2.inflate = selfBone.getInflate();
      geoBone2.parent = geoBone;
      geoBone2.setName(selfBone.getName());
      geoBone2.setRotationX((float)Math.toRadians((double)geoVector3f.getX()));
      geoBone2.setRotationY((float)Math.toRadians((double)geoVector3f.getY()));
      geoBone2.setRotationZ((float)Math.toRadians((double)geoVector3f.getZ()));
      geoBone2.rotationPointX = -geoVector3f2.getX();
      geoBone2.rotationPointY = geoVector3f2.getY();
      geoBone2.rotationPointZ = geoVector3f2.getZ();
      if (!ArrayUtils.isEmpty(selfBone.getCubes())) {
         for (Cube cube : selfBone.getCubes()) {
            Double d = geoBone2.inflate == null ? null : geoBone2.inflate / 16.0;
            Boolean mirror = geoBone2.mirror;
            geoBone2.childCubes.add(GeoCube.createFromPojoCube(cube, modelProperties, d, mirror));
         }
      }

      for (RawBoneGroup rawBoneGroup2 : (Iterable<RawBoneGroup>)(rawBoneGroup.children.values())) {
         geoBone2.childBones.add(this.constructBone(rawBoneGroup2, modelProperties, geoBone2));
      }

      return geoBone2;
   }

   public GeoModel constructGeoModel(RawGeometryTree rawGeometryTree) {
      GeoModel geoModel = new GeoModel();
      geoModel.properties = rawGeometryTree.properties;

      for (RawBoneGroup rawBoneGroup : (Iterable<RawBoneGroup>)(rawGeometryTree.topLevelBones.values())) {
         GeoBone geoBone = (GeoBone)null;
         ModelProperties properties = rawGeometryTree.properties;
         geoModel.topLevelBones.add(this.constructBone(rawBoneGroup, properties, geoBone));
      }

      return geoModel;
   }



}
