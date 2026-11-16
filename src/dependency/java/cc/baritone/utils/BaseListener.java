package cc.baritone.utils;

import java.util.stream.Stream;

public interface BaseListener {
   Stream empty(HeuristicSampler heuristicSampler) throws ExceptionAdapter;
}
