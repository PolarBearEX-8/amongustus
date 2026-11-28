package baritone.api.cache;


public interface ICachedRegion extends IBlockLookup {
   int getZ();

   int getX();

   boolean isCached(int n, int n2);
}
