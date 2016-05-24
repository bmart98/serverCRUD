# serverCRUD
Uses Java and Jersey 1.19.1 to create a sample RESTful service that creates and updates servers with a JSON payload.

## Installation
For this example you will need Tomcat 7 or 8 to run this service. Using Maven, perform an install on the application root. 
Then, under target folder, load the serverCRUD.war into tomcat.

For information on how to setup tomcat please visit https://tomcat.apache.org/tomcat-7.0-doc/setup.html or https://tomcat.apache.org/tomcat-8.0-doc/setup.html.
For information on how to install and use Maven please visit https://maven.apache.org/install.html and https://maven.apache.org/guides/getting-started/

## API
####The URI to display a list of servers 
GET
/serverCRUD/ws/servers

####The URI to display a single server
GET
/serverCRUD/ws/servers/{id}

####URI to create a server
POST
/serverCRUD/ws/servers

Sample JSON to create a server:
{"name":"Echo","cpus":12,"ram":64,"disSpace":2000}

If the user includes the ID and server state in the JSON, it will be overridden with the ID the server assigns and will default
to the "Building" state. Once it is done building it will change to the "Running" state. This process takes 35 seconds. Only builds one server per POST.

####URI to update a server
PUT
/serverCRUD/ws/servers

Sample JSON to update a server:
{"name":"Alfa","serverState":"Running","id":1,"cpus":4,"ram":16,"disSpace":100}

This will only update a server if the ID exists. It will not allow the user to change the state of a running 
or destroyed server to "Building".

####URI to destroy a server
PUT
/serverCRUD/ws/servers/destroy/{id}

Easily change the state of a server to "Destroyed" by just the ID.

####URI to permanently delete a server
DELETE
/serverCRUD/ws/servers/{id}


## Testing
The easiest way to test this service is to use a browser plugin like RESTeasy for firefox https://addons.mozilla.org/en-US/firefox/addon/rest-easy/




