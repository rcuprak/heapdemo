/*
 * DemoApplication.java
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
package net.cuprak.demo.heap;

import net.cuprak.sample.Office;
import net.cuprak.sample.Person;
import org.netbeans.lib.profiler.heap.Heap;
import org.netbeans.lib.profiler.heap.HeapFactory;
import org.netbeans.lib.profiler.heap.Instance;
import org.netbeans.lib.profiler.heap.JavaClass;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * This demo application creates data model, dumps it to disk, and then opens it.
 * This demostrates how to create an read a heap dump in one code snippet. Use
 * this as a starting point to better understand the NetBeans Profiler API.
 * @author Ryan Cuprak
 */
public class DemoApplication {

    /**
     * Creates a data model in memory which we then dump
     */
    private static void createDataModelAndDump() throws IOException {
        Set<String> availableCoffee = new HashSet<>();
        availableCoffee.add("Pike's Place Roast");
        availableCoffee.add("Morning Blend");
        availableCoffee.add("Hazelnut");
        Office branford = new Office("Branford",availableCoffee,1,100000,10,true);
        branford.addConferenceRoom("Room A", "Room B", "Room C");
        Office stamford = new Office("Waltham HQ",availableCoffee,2,200000,10,true);
        stamford.addConferenceRoom("2113", "Room 2114");
        Office norwalk = new Office("Norwark",availableCoffee,3,300000,10,true);
        norwalk.addConferenceRoom("A1", "Room B1");

        branford.addEmployee("1",new Person("Bob","Smith",34),1,1,"203-888-5555");
        branford.addEmployee("2",new Person("John","Doe",34),1,2,"203-647-0000");

        stamford.addEmployee("3",new Person("Karen","Kostner",20),1,2,"781-000-0001");


        stamford.addEmployee("4",new Person("Adam","Appleton",55),
                5,5,"781-000-0001");

        norwalk.addEmployee("3",new Person("Lomax","Donner",20),
                1,2,"401-000-0001");
        norwalk.addEmployee("4",new Person("Nick","Islington",55),
                5,5,"401-000-0002");

        branford.addNearbyOffice(stamford);
        branford.addNearbyOffice(norwalk);

        stamford.addNearbyOffice(branford);
        stamford.addNearbyOffice(norwalk);

        norwalk.addNearbyOffice(stamford);
        norwalk.addNearbyOffice(branford);

        norwalk.fireEmployee("4");
        File f = new File("test2.hprof");
        if(f.exists()) {
            f.delete();
        }
        HeapDumper.dumpHeap("test2.hprof",true);
    }

    /**
     * Analyzes the heap dump using the NetBeans API code
     */
    private static void analyzeHeapDump() throws IOException {
        Heap heap = HeapFactory.createHeap(new File("test2.hprof"));
        JavaClass strClass = heap.getJavaClassByName("net.cuprak.sample.Office");
        List<Instance> instances = strClass.getInstances();
        for(Instance instance : instances) {
            Instance nameFld = (Instance)instance.getValueOfField("name");
            String fieldValue = HeapUtils.processString(nameFld);
            System.out.println("Office name: " + fieldValue);
        }
    }

    public static void main(String args[]) throws IOException {
        // Dump a data model to disk
        createDataModelAndDump();
        // Read in the heap dump and analyze it
        analyzeHeapDump();
    }
}
