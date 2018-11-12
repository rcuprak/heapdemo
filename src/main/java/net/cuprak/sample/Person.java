/*
 * Person.java
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
 * Represents a person
 * @author Ryan Cuprak
 */
public class Person {

    /**
     * Cube in which the person resides
     */
    private Cube cube;

    /**
     * User id
     */
    private String userId;

    /**
     * First name
     */
    private String firstname;

    /**
     * Last name
     */
    private String lastname;

    /**
     * Age
     */
    private int age;

    /**
     * Creates a new person
     * @param firstname - first name
     * @param lastname - last name
     */
    public Person(String firstname, String lastname, int age) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
    }

    void setUserId(String userId) {

        this.userId = userId;
    }

    /**
     * Returns their first name
     * @return first name
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * Sets the their first name
     * @param firstname - firstname
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * Returns last name
     * @return last name
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * Sets last name
     * @param lastname - last name
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * Returns person's age
     * @return age
     */
    public int getAge() { return age; }

    /**
     * Sets the person's cube
     * @param cube - cube
     */
    public void setCube(Cube cube) {
        this.cube = cube;
    }

    /**
     * Returns their cube
     * @return cube
     */
    public Cube getCube() {
        return cube;
    }

}
