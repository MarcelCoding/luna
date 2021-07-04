package com.github.marcelcoding.luna.cacti.converter;

import com.github.marcelcoding.luna.cacti.api.Cactus.State;
import com.github.marcelcoding.luna.cacti.model.CactusModel.StateModel;
import net.getnova.framework.core.Converter;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.stereotype.Component;

@Component
public class CactusStateConverter implements Converter<StateModel, State> {

  @Override
  public StateModel toModel(final State dto) {
    return new StateModel(
      dto.getNoLongerInPossessionTimestamp(),
      dto.getNoLongerInPossessionReason(),
      dto.getVitality()
    );
  }

  @Override
  public State toDto(final StateModel model) {
    return new State(
      model.getNoLongerInPossessionTimestamp(),
      model.getNoLongerInPossessionReason(),
      model.getVitality()
    );
  }

  @Override
  public void override(final StateModel model, final State dto) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void merge(final StateModel model, final State dto) {
    throw new NotImplementedException();
  }
}
