package baritone.process;


public interface IChunkFilter {
   ExploreProcess.Status isAlreadyExplored(int n, int n2);

   int countRemain();
}
