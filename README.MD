# LocationApp
Login with your Facebook location and pick your favourite location.

### Known problems
####Frontend
* With the first opening of the start page there is an auto-redirect to Facebook which is blocked by CORS.
I don't want this to happen. Instead I want to show the login button to the user.
* MarkerController doesn't get initialized most likely because it's not defined properly. Both controllers refer to the same path
and defined in the same template.

* Break down into separate modules
* Extract data that is supposed to be shared across controllers in a separate service
* Fix the header problem which arises during the startup process
* Remove redundant features
* Implement "Share my location" feature
####Backend
* Break down the code into separate classes
* Add real database support
* Cascade all for JPA
* How the "Principal" magic works

####Miscellaneous
 * Read about how user session management should be done
 * Read about Gradle source sets