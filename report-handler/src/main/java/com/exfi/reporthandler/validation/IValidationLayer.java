package com.exfi.reporthandler.validation;

import java.util.List;

public interface IValidationLayer {

    boolean userIsValid();

    boolean groupIdsIsValid(List<Long> groupIds);

}
