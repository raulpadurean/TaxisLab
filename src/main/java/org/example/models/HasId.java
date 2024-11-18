package org.example.models;


import java.io.Serializable;

/**
 * A functional interface that represents an object with a unique identifier.
 */
@FunctionalInterface
public interface HasId extends Serializable {
    /**
     * Gets the unique identifier of the object.
     *
     * @return The unique identifier.
     */
    Integer getId();
}
