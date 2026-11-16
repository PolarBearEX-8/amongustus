package client.util;

public class DampenerScheduler extends DampenerEngine {
   public final CommonMapEntry commonMapEntry;

   @Override
   public ResourceLocationAdapter resolveResourceLocationAdapter(IBlockState blockState) {
      return new ResourceLocationAdapter("dead_bush", "normal");
   }

   public DampenerScheduler(CommonMapEntry commonMapEntry2) {
      this.commonMapEntry = commonMapEntry2;
   }
}
