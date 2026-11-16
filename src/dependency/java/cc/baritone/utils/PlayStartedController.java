package cc.baritone.utils;

public class PlayStartedController extends AbstractController {
   public final RuntimeComponentImpl runtimeComponentImpl;

   @Override
   public void applyExceptionDecodingConContro(ExceptionDecodingConController exceptionDecodingConController) {
      System.out.println("Play started from frame " + exceptionDecodingConController.getCount3());
   }

   @Override
   public void applyExceptionDecodingConControByExceptionDecodin(ExceptionDecodingConController exceptionDecodingConController) {
      System.out.println("Play completed at frame " + exceptionDecodingConController.getCount3());
      System.exit(0);
   }

   public PlayStartedController(RuntimeComponentImpl runtimeComponentImpl2) {
      this.runtimeComponentImpl = runtimeComponentImpl2;
   }
}
