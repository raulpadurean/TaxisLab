package org.example.models;

import java.io.Serializable;

/**
 * A functional interface that represents an object with a unique identifier.
 *
 * <p>This interface can be implemented by classes that have a unique ID field
 * to ensure a consistent way of accessing the ID. Since it extends {@code Serializable},
 * objects implementing this interface can be serialized.</p>
 */
@FunctionalInterface
public interface HasId extends Serializable {

    /**
     * Gets the unique identifier of the object.
     *
     * @return The unique identifier as an {@code Integer}.
     */
    Integer getId();
}
