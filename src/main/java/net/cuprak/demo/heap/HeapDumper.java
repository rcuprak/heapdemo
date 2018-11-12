/*
 * HeapDumper.java
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

import com.sun.management.HotSpotDiagnosticMXBean;

import javax.management.MBeanServer;
import java.io.IOException;
import java.lang.management.ManagementFactory;

/**
 * Dumps the heap of the current JVM to disk.
 * @author Ryan Cuprak
 */
public class HeapDumper {

    /**
     * Hotspot bean name
     */
    private static final String HOTSTPOT_BEAN_NAME = "com.sun.management:type=HotSpotDiagnostic";

    /**
     * Hotspot bean instance
     */
    private static volatile HotSpotDiagnosticMXBean hotSpotDiagnosticMXBean;

    /**
     * Dumps the heap, note generally you only want live objects (does garage collection first)
     * @param filename - name of the file
     * @param live - only live or include uncollected objects
     * @throws IOException - thrown if there is an error
     */
    public static void dumpHeap(String filename, boolean live) throws IOException {
        initHotspotMBean();
        hotSpotDiagnosticMXBean.dumpHeap(filename,live);
    }

    /**
     * Initializes this utility
     * @throws IOException - thrown if there is an error
     */
    private static void initHotspotMBean() throws IOException {
        if(hotSpotDiagnosticMXBean == null) {
            synchronized (HeapDumper.class) {
                hotSpotDiagnosticMXBean = getHotSpotDiagnosticMXBean();
            }
        }
    }

    /**
     * Retrieves the mbean
     * @return HotSpotDiagnosticMXBean
     * @throws IOException - thrown if there is an error
     */
    private static HotSpotDiagnosticMXBean getHotSpotDiagnosticMXBean() throws IOException {
        MBeanServer server = ManagementFactory.getPlatformMBeanServer();
        return ManagementFactory.newPlatformMXBeanProxy(server,HOTSTPOT_BEAN_NAME,
                HotSpotDiagnosticMXBean.class);
    }

}