### About
java-payments-api is a simple api that uses the payment gateways rest-api transforming them into simple methods that you can use.<br>
Be sure to check how the payment gateways you use work

### Repository (Maven or Gradle)
```xml
<repository>
  <id>eternalcode-repository-releases</id>
  <name>EternalCode Repository</name>
  <url>https://repo.eternalcode.pl/releases</url>
</repository>
```
```groovy
maven { url "https://repo.eternalcode.pl/releases" }
```

### Dependencies (Maven or Gradle)
```xml
<dependency>
  <groupId>dev.vetther</groupId>
  <artifactId>java-payments-api</artifactId>
  <version>1.0.8</version>
</dependency>
```
```groovy
implementation "dev.vetther:java-payments-api:1.0.8"
```

### Example Usage
###### Create payment (You need to create PayPal app, <a href="https://developer.paypal.com/developer/applications/">check this</a> and <a href="https://www.youtube.com/watch?v=OZeuvESoqIU">this tutorial</a>)
```java
PaypalApi paypalApi = new PaypalApi("CLIENT_ID", "CLIENT_SECRET", false);

PaypalApiCreateOrder order = paypalApi.createOrder(
      2.00, // amount
      "USD", // currency
      "https://github.com/Vetther/java-payments-api", // redirect after payment
      "https://myapp.com/notify" // webhook, POST request after payment
);
```

###### Check payment status
```java
PaypalApiOrderInfo orderInfo = paypalApi.getOrderInfo("ORDER_ID");

if (orderInfo.getOrder().isPaid()) {
      // payment was successfully authorized
}
```
