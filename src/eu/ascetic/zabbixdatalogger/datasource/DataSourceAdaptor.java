/**
 * Copyright 2014 University of Leeds
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package eu.ascetic.zabbixdatalogger.datasource;

import eu.ascetic.zabbixdatalogger.datasource.types.Host;
import eu.ascetic.zabbixdatalogger.datasource.types.MonitoredEntity;
import eu.ascetic.zabbixdatalogger.datasource.types.VmDeployed;
import java.util.List;

/**
 * This is the interface for all data sources that the Zabbix data logger uses..
 *
 * @author Richard Kavanagh
 */
public interface DataSourceAdaptor {

    /**
     * This returns a host given its unique name.
     *
     * @param hostname The name of the host to get.
     * @return The object representation of a host.
     */
    public Host getHostByName(String hostname);

    /**
     * This returns a host given its unique name.
     *
     * @param name The name of the host to get.
     * @return The object representation of a host.
     */
    public VmDeployed getVmByName(String name);

    /**
     * This provides a list of hosts
     *
     * @return A list of hosts.
     */
    public List<Host> getHostList();

    /**
     * This provides a list of hosts and VMs
     * @return A list of monitored entities.
     */
    public List<MonitoredEntity> getHostAndVmList();
    
    /**
     * This provides a list of VMs
     *
     * @return A list of vms.
     */
    public List<VmDeployed> getVmList();

    /**
     * This provides for the named host all the information that is available.
     *
     * @param host The host to get the measurement data for.
     * @return The host measurement data
     */
    public HostMeasurement getHostData(Host host);

    /**
     * This lists for all host all the metric data on them.
     *
     * @return A list of host measurements
     */
    public List<HostMeasurement> getHostData();

    /**
     * This takes a list of hosts and provides all the metric data on them.
     *
     * @param hostList The list of hosts to get the data from
     * @return A list of host measurements
     */
    public List<HostMeasurement> getHostData(List<Host> hostList);

    /**
     * This provides for the named vm all the information that is available.
     *
     * @param vm The vm to get the measurement data for.
     * @return The vm measurement data
     */
    public VmMeasurement getVmData(VmDeployed vm);

    /**
     * This lists for all vms all the metric data on them.
     *
     * @return A list of vm measurements
     */
    public List<VmMeasurement> getVmData();

    /**
     * This takes a list of vms and provides all the metric data on them.
     *
     * @param vmList The list of vms to get the data from
     * @return A list of vm measurements
     */
    public List<VmMeasurement> getVmData(List<VmDeployed> vmList);

    /**
     * This finds the lowest/resting power usage by a client.
     *
     * @param host The host to get the lowest power usage data for.
     * @return The lowest i.e. resting power usage of a host
     */
    public double getLowestHostPowerUsage(Host host);

    /**
     * This finds the highest power usage by a host.
     *
     * @param host The host to get the highest power usage data for.
     * @return The highest power usage of a host
     */
    public double getHighestHostPowerUsage(eu.ascetic.zabbixdatalogger.datasource.types.Host host);
    
    /**
     * This finds the cpu utilisation of a host, over the last n minutes.
     * @param host The host to get the cpu utilisation data for.
     * @param durationSeconds The amount of seconds to get the data for
     * @return The average utilisation of the host.
     */
    public double getCpuUtilisation(eu.ascetic.zabbixdatalogger.datasource.types.Host host, int durationSeconds);
    
}
