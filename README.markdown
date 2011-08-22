ATDD Sample Application bwerter
===============================

A simple sample application demonstration ATDD.

* `start-db` starts a H2 database service on port 9092. Hit Ctrl-C to stop the service.
* `start-jetty` starts a Jetty web server on port 8080. Hit Ctrl-C to stop the service.

You can also deploy to CloudFoundry. Run `mvn clean package`, then `cd target; vmc push [application name]`.
Make sure to provision a MySQL service for the application.