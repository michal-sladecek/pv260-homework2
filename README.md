# Tasks

## Single Responsibility Principle



## Open/Closed Principle

I refactored the project by creating interface `PersistenceInterface` and 
classes `PersistenceXML`,`PersistenceCSV` that implement the interface.
This structure enables easy adding of new persistence methods by simply implementing the
common interface.

Interface only contains one method `persist(Comment)`.

## Liskov Substitution Principle


`JSONSerializer`  allows serialization of arrays of arrays, therefore it breaks the contract.

`CompressingSerializer` does not follow this property *Y.equals(deserialize(serialize(Y))) == true*, because it first converts serialized text to ASCII, 
and if it can not convert some characted, it puts `?` in its place. Therefore,
when something is deserialized, the original UTF-8 characters can not be restored.



## Interface Segregation Principle

Bad interface might  require implementors to implement methods they do not need - for example if we have
interface called `Animal` with methods `fly`, `swim`, `walk`, `getAge`, `getWeight`. Not all animals can fly, therefore some animals
would need to just return some kind of error on their implementation of `fly`.

It would be better to create interface `AnimalWithWings` (some mammals can also fly). This interface would 
contain method `fly`. The interface `Animal` would only contain the methods `getAge`,`getWeight`.

There would also be interfaces `AnimalWithWings`, `AnimalWithLegs`... that would contain methods for flying
or walking, and the final class would look like `class Eagle implements AnimalWithLegs,AnimalWithWings`, so it would have both methods
for walking and for flying.


## Dependency Inversion Principle

1. Both recommendation classes use some weather forecast service.
The weather service class and weather service response class is directly specified in the recommendation classes interface.
   This means that if we would want to change the weather forecast service,
   we would need to also change recommendation classes.
    
2. We need to create interface for Weather service `WeatherForecastServiceInterface` and also
for the forecast response `WeatherForecastResponseInterface`.
   I also moved most functions from Recommendations into WeatherForecastResponse, leaving only as minimal interface
   as recommendation services need. 
   
3. We can now change the used weather API without changing anything inside
the WeatherForecast. Also, if implementation of some API changes, we just need to adjust it inside the Weather service and response
   classes.
   
