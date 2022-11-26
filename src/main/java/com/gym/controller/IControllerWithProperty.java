package com.gym.controller;

import javafx.beans.property.ObjectProperty;

public interface IControllerWithProperty {
    ObjectProperty<Object> selectedProperty();
}
