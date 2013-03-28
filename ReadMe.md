# analytics-facade

[![Build Status](https://buildhive.cloudbees.com/job/sheng168/job/analytics-facade/badge/icon)](https://buildhive.cloudbees.com/job/sheng168/job/analytics-facade/)

analytics-facade allows you to send analytics data from your Android app to multiple services using a single API.
The following services are supported:

* Google Analytics
* Google Analytics V2
* Flurry
* Localytics
* and it's easy to support additional services

I created this while I was evaluating analytics services trying to pick one to use in my Android apps.
I didn't want to implement each vender's SDK while peppering my classes with their API.
I also didn't like some of the vender's API 
so creating this facade allow me to improve on it before instructmenting  my classes.
I was inspired by slf4j(logging facade).
Using the facade, I was able to enable all four analytics services in my app while developing and testing
and compare the reports that are generated with each service before picking one to leave in place for production.


```java
// setup to send data to both flurry and google in Application.onCreate
AnalyticsFlurry.init(this, "PWBYBRZKPRJKZVMQ****");
AnalyticsGoogleV2.init(this, 1); // one second dispatch for development

Analytics.track(this); // track action linked to object class name
Analytics.track(this, "onCreate"); // add method or other label
Analytics.time(1, "sample"); // track timing data
Analytics.time(123, "category", "name", "label");		
```
