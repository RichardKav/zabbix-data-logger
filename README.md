# Zabbix Data Logger

The Zabbix data logger is a program that is designed to collect data from Zabbix and output it to disk. 

## Configuration

The default for the application is to read values directly in from the underlying Zabbix database. The database configuration details will need to be changed to match your installation details and are located in file zabbix_db_adaptor.properties.

## Usage

Its usage is as follows: 

```
java -jar Zabbix_Data_Logger.jar <Hostname> [optional_parameters]
```

An example of this is: 

```
java -jar Zabbix_Data_Logger.jar testnode1
```
The optional parameters are as follows:

- silent or s : this makes the program run without attempting to read the stop command from the console.
- json or j : this makes the program gather data from a json interface instead of via the database. The configuration file used in this case is ascetic-zabbix-api.properties.

Once running the tool can be closed by writing the word quit, unless the silent flag was set, where the application will instead need to be killed manually.

## File Output
The output of the application is a tab separated file with the following name:

```
Dataset_<Hostname>.txt
```

The output for the previous would therefore be:

```
Dataset_testnode1.txt
```
