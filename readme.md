# analytics-facade

analytics-facade allows you to send analytics data to multiple services using a single API.

* Google Analytics
* Google Analytics V2
* Flurry
* Localytics

```java
// setup to send data to both flurry and google
AnalyticsFlurry.init(this, "PWBYBRZKPRJKZVMQ****");
AnalyticsGoogleV2.init(this, 1); // one second dispatch for development

Analytics.track(this);
Analytics.track(this, "onCreate");
Analytics.time(1, "sample");
Analytics.time(123, "category", "name", "label");		
```
