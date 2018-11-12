/*
 * Office.java
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

import java.util.*;

/**
 * Represents an office
 * @author Ryan Cuprak
 */
public class Office {

    /**
     * Name of the office
     */
    private String name;

    /**
     * Office id
     */
    private int officeId;

    /**
     * Office Revenue
     */
    private long officeRevenue;

    /**
     * Double check
     */
    private double doubleCheck;

    /**
     * Whether office is open
     */
    private boolean isOpen;

    /**
     * Persons working at this office
     */
    private Map<String,Person> employees = new HashMap<>();

    /**
     * Office phone extensions
     */
    private List<String> phoneNumbers = new LinkedList<>();

    /**
     * Conference rooms
     */
    private Vector<String> conferenceRooms = new Vector<>();

    /**
     * Nearby offices
     */
    private List<Office> nearbyOffices = new LinkedList<>();

    /**
     * Coffee options
     */
    private Set<String> coffeeSelection = new HashSet<>();

    /**
     * Cube grid
     */
    private Cube[][] cubeGrid = new Cube[9][9];

    /**
     * Cubes
     */
    private Set<Cube> cubes = new HashSet<>();

    /**
     * Instantiates an empty office with a name
     * @param officeName - office name
     * @param officeId - identifier
     * @param officeRevenue - revenue
     * @param doubleCheck - double check
     * @param isOpen - is open
     */
    public Office(String officeName,Set<String> coffeeSelection, int officeId, long officeRevenue,double doubleCheck, boolean isOpen) {
        this.name = officeName;
        this.coffeeSelection = coffeeSelection;
        this.officeId = officeId;
        this.officeRevenue = officeRevenue;
        this.doubleCheck = doubleCheck;
        this.isOpen = isOpen;
    }

    /**
     * Adds conference rooms
     * @param rooms - rooms
     */
    public void addConferenceRoom(String ...rooms) {
        Collections.addAll(conferenceRooms, rooms);
    }

    /**
     * Adds a nearby office
     * @param nearbyOffice - nearby office
     */
    public void addNearbyOffice(Office nearbyOffice) {
        nearbyOffices.add(nearbyOffice);
    }

    /**
     * Adds a person and stuffs them in a cube
     * @param userId - user id of the employee
     * @param person - person
     * @param cubeX - x coordinate
     * @param cubeY - y coordinate
     */

    public void addEmployee(String userId, Person person, int cubeX, int cubeY, String phoneNumber) {
        employees.put(userId,person);
        person.setUserId(userId);
        Cube cube = new Cube(person,true);
        cubes.add(cube);
        person.setCube(cube);
        cubeGrid[cubeX][cubeY] = cube;
        phoneNumbers.add(phoneNumber);
    }

    /**
     * Returns the name of the office
     * @return office name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the employees at the office
     * @return office employees
     */
    public Map<String, Person> getEmployees() {
        return employees;
    }

    /**
     * Returns the phone numbers of this office
     * @return phone numbers
     */
    public List<String> getPhoneNumbers() {
        return phoneNumbers;
    }

    /**
     * Returns the list of conference rooms
     * @return conference rooms
     */
    public Vector<String> getConferenceRooms() {
        return conferenceRooms;
    }

    /**
     * Returns the nearby office
     * @return nearby offices
     */
    public List<Office> getNearbyOffices() {
        return nearbyOffices;
    }

    /**
     * Returns the coffee selection
     * @return coffee selection
     */
    public Set<String> getCoffeeSelection() {
        return coffeeSelection;
    }

    /**
     * Returns the cube grid
     * @return cube grid
     */
    public Cube[][] getCubeGrid() {
        return cubeGrid;
    }

    /**
     * Returns the office id
     * @return office id
     */
    public int getOfficeId() {
        return officeId;
    }

    /**
     * Returns the office revenue
     * @return office revenue
     */
    public long getOfficeRevenue() {
        return officeRevenue;
    }

    /**
     * Returns double check
     * @return double check
     */
    public double getDoubleCheck() {
        return doubleCheck;
    }

    /**
     * Returns a flag signalling whether the office is open
     * @return is open
     */
    public boolean isOpen() {
        return isOpen;
    }

    /**
     * Fires an employee
     * @param userId - employee id
     */
    public void fireEmployee(String userId) {
        employees.remove(userId);
    }
}
