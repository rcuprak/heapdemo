/*
 * HeapUtils.java
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

import org.netbeans.lib.profiler.heap.Instance;
import org.netbeans.lib.profiler.heap.ObjectArrayInstance;
import org.netbeans.lib.profiler.heap.PrimitiveArrayInstance;

import java.util.List;

/**
 * Sample set of utilities for processing internals of some key JDK classes including
 * Strings and Lists.
 * @author Ryan Cuprak
 */
@SuppressWarnings("unchecked")
public class HeapUtils {

    /**
     * Processes a LinkedList
     * @param instance - LinkedList instance
     */
    public void processLinkedList(Instance instance) {
        Instance listEntry = (Instance) instance.getValueOfField("first");
        int i = 0;
        while (listEntry != null) {
            i++;
            Object item = listEntry.getValueOfField("item");
            System.out.println("Type: " + item);
            // process the item in the list
            listEntry = (Instance) listEntry.getValueOfField("next");
        }
    }

    /**
     * Processes an ArrayList
     * @param instance - ArrayList instance
     * @return instance list
     */
    public List<Instance> processArrayList(Instance instance) {
        ObjectArrayInstance data =
                (ObjectArrayInstance) instance.getValueOfField("elementData");
        return data.getValues();
    }

    static String processString(Instance instance) {
        if(instance.getValueOfField("value") instanceof PrimitiveArrayInstance) {
            PrimitiveArrayInstance pi =
                    (PrimitiveArrayInstance) instance.getValueOfField("value");
            if (pi != null) {
                List<Object> entries = pi.getValues();
                StringBuilder builder = new StringBuilder();
                for (Object obj : entries) {
                    if(obj instanceof Character) {
                        builder.append((char)obj);
                    } else if (obj instanceof Integer) {
                        int charCode = Integer.valueOf((String) obj);
                        builder.append(Character.toString((char) charCode));
                    }
                }
                return builder.toString();
            }
        } else {
            return instance.getValueOfField("value").toString();
        }
        return "null";
    }
}
