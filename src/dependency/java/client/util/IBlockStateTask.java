package client.util;

import java.util.concurrent.Callable;

public final class IBlockStateTask implements Callable {
   public final IBlockState iBlockState;

   @Override
   public Object call() throws Exception {
      return this.getText();
   }

   public String getText() throws Exception {
      return this.iBlockState.toString();
   }

   public IBlockStateTask(IBlockState blockState) {
      this.iBlockState = blockState;
   }
}
