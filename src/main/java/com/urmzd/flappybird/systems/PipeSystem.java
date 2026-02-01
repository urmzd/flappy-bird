package com.urmzd.flappybird.systems;

import com.urmzd.flappybird.state.PipeState;
import java.util.List;

public interface PipeSystem {
  List<PipeState> update(List<PipeState> pipes);

  List<PipeState> spawnIfNeeded(List<PipeState> pipes);

  List<PipeState> removeOffscreen(List<PipeState> pipes);

  List<PipeState> stopAll(List<PipeState> pipes);
}
