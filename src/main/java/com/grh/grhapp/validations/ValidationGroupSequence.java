package com.grh.grhapp.validations;

import javax.validation.GroupSequence;
import javax.validation.groups.Default;

@GroupSequence({ Default.class, ValidationGroupOne.class, ValidationGroupTwo.class })
public interface ValidationGroupSequence {

}
