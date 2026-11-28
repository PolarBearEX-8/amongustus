package net.augustus.cosmetics.model;

import net.labymod.ModelProperties;

public interface GeoModelBuilder {
   GeoBone constructBone(RawBoneGroup rawBoneGroup, ModelProperties modelProperties, GeoBone geoBone);

   GeoModel constructGeoModel(RawGeometryTree rawGeometryTree);
}
