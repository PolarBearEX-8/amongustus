package baritone.api.pathing.calc;

import baritone.api.process.IBaritoneProcess;
import java.util.Optional;

public interface IPathingControlManager {
   void registerProcess(IBaritoneProcess baritoneProcess);

   Optional mostRecentInControl();

   Optional mostRecentCommand();
}
