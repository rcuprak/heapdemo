/*
 * Cube.java
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.cuprak.sample;

/**
 * Represents a cube in the office
 * @author Ryan Cuprak
 */
public class Cube {

    /**
     * An employee in a cube
     */
    private Person occupant;

    /**
     * Flag indicating if there is a desk lamp
     */
    private Boolean hasDesklamp;

    /**
     * Creates a new cube
     * @param employee - employee
     */
    Cube(Person employee, Boolean hasDesklamp) {
        this.occupant = employee;
        this.hasDesklamp = hasDesklamp;
    }

    /**
     * Returns if there is a desk lamp present
     * @return desk lamp
     */
    public Boolean getHasDesklamp() {
        return hasDesklamp;
    }

    /**
     * Returns the occupant
     * @return Occupant
     */
    public Person getOccupant() {
        return occupant;
    }

}
