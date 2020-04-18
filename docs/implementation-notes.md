# Implementation Notes

### Organization

Although the Design document represents this platform as being composed of various micro services, this implementation aggregates all of the components into one Spring Boot Project. However, the way the packages are structured should clarify the distinction between the various components.

#### Packages
- `com.chronicle.communications.common`: Common models, configurations, utility classes ... etc that can be used across components.
- `com.chronicle.communications.microservice`: Every package directly under this represents a stand alone component in the platform. Classes under each package here are specific to that component although some are shared. For instance `com.chronicle.communications.microservice.delivery` contains code shared across the 4 different delivery services.

#### Naming Convention
- `*MicroService`: A Class that ends with `MicroService` represents the external touch point for a Component. If this were built as specified in the design doc, these classes represent the Gateways by which the given microservice communicates with components outside of it. For example, `CommunicationMicroService` encapsulates the APIs other services will call to create/update a `Communication`.
- `*Service`: Classes that end with `Service` (exlcuding the above) are internal to an Application representing actions that happen at the 'service' layer.

### In Memory and Stub Implementations

Some classes within this implementation have been either stubbed out or implemented in memory.

