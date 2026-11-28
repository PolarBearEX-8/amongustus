package baritone.api.process;

import java.nio.file.Path;

public interface IExploreProcess extends IBaritoneProcess {
   void applyJsonFilter(Path path, boolean flag) throws Exception;

   void explore(int n, int n2);
}
