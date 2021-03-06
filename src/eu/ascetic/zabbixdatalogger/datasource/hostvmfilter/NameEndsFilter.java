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
package eu.ascetic.zabbixdatalogger.datasource.hostvmfilter;

import static eu.ascetic.zabbixdatalogger.datasource.hostvmfilter.NameBeginsFilter.CONFIG_FILE;
import eu.ascetic.asceticarchitecture.iaas.zabbixApi.datamodel.Host;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

/**
 * This is a way of filtering deciding between if a Zabbix Host is a VM or a real
 * physical host. It is based upon the end of the hosts name following a specified pattern.
 * @author Richard Kavanagh
 */
public class NameEndsFilter implements ZabbixHostVMFilter {

    private String ends = "testnode";
    private boolean isHost = true;

    /**
     * This creates a name filter that checks to see if the end of a host name
     * matches particular criteria or not. if it does then it will indicate accordingly
     * that the "Zabbix JSON API host" is a host or VM.
     */
    public NameEndsFilter() {
        try {
            PropertiesConfiguration config;
            if (new File(CONFIG_FILE).exists()) {
                config = new PropertiesConfiguration(CONFIG_FILE);
            } else {
                config = new PropertiesConfiguration();
                config.setFile(new File(CONFIG_FILE));
            }
            config.setAutoSave(true); //This will save the configuration file back to disk. In case the defaults need setting.
            ends = config.getString("data.logger.filter.begins", ends);
            config.setProperty("data.logger.filter.begins", ends);
            isHost = config.getBoolean("data.logger.filter.isHost", isHost);
            config.setProperty("data.logger.filter.isHost", isHost);
        } catch (ConfigurationException ex) {
            Logger.getLogger(NameBeginsFilter.class.getName()).log(Level.INFO, "Error loading the configuration of the name ends filter");
        }
    }

    @Override
    public boolean isHost(Host host) {
        if (isHost) { //Testing by giving a common name to hosts
            return (host.getHost().endsWith(ends));
        } else { //testing for by providing a common name to VMs
            return (!host.getHost().endsWith(ends));
        }
    }
    
}
