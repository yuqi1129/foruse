## Important thing when deploy sloth server for a different yarn cluster.

Cause ```core-site.xml```, ```hdfs-site.xml```, ```yarn-site.xml``` are useful
when you submit job to a flink cluster in HA mode. So there three files should be
coordinated to the yarn cluster that your flink cluster deployed.


## More important for ```yarn-site.xml```

Currently (2017/12/21), ```yarn-site.xml``` is indeed wrong, if you want to change it for
a new yarn cluster. You should do the following things:

- 1. Copy ```yarn-site.xml``` form ```$YARN_CONF_DIR/conf```
- 2. Add the follow configuration entry in it:
```xml
    <!-- rm1, rm2 is the resourcemanager's ids, this may be different for every yarn -->
    <!-- (sloth1|sloth3).hz.163.org:8032 is the resourcemanger's listened address -->
    <property>
        <name>yarn.resourcemanager.address.rm1</name>
        <value>sloth3.hz.163.org:8032</value>
    </property>

    <property>
        <name>yarn.resourcemanager.address.rm2</name>
        <value>sloth1.hz.163.org:8032</value>
    </property>
```

