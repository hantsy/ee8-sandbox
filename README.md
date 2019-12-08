#  Java EE 8/Jakarta EE 8 Sandbox

Java EE  is finally moved to the biggest Java community -  Eclipse Foundation.  It is great for Java developers. But due to some issues of Java EE brand,  we have to  use a new name to continue it.  Jakarta EE is elected from  Java communities. 



## Migration to Jakarta EE 8

In our project, when migrating the existing project to Jakarta EE 8, use the following new Jakarta EE API instead.

```xml
<dependency>
    <groupId>jakarta.platform</groupId>
    <artifactId>jakarta.jakartaee-api</artifactId>
    <version>${jakartaee-api.version}</version>
</dependency>
```

Due to the historic reason, the existing classes and other resources in these jars still use *javax* as package prefix, it is good for backward compatibility.  There is no need to change the codes at all.

But in future Jakarta EE 9 or x , it will use *jakarta*  for newly-added APIs.

## What is new in Java EE 8

There tow new specifications were introduced in Java EE 8.

* JSR 375 – Java EE Security API 1.0
* JSR 367 – The Java API for JSON Binding (JSON-B) 1.0

Some specifications have been updated to align with Java 8 and CDI or involved as a maintainance release.

* JSR 365 – Contexts and Dependency Injection (CDI) 2.0
* JSR 369 – Java Servlet 4.0
* JSR 370 – Java API for RESTful Web Services (JAX-RS) 2.1
* JSR 372 – JavaServer Faces (JSF) 2.3
* JSR 374 – Java API for JSON Processing (JSON-P)1.1
* JSR 380 – Bean Validation 2.0
* JSR 250 – Common Annotations 1.3
* JSR 338 – Java Persistence 2.2
* JSR 356 – Java API for WebSocket 1.1
* JSR 919 – JavaMail 1.6

The other specifications such as JMS, Batch have no updates in this version.

Unfortunately, MVC(JSR 371) is vetoed in the final stage, but it is still existed as a community based specification. And JCache(JSR 107) which had missed the last train of Java EE 7, and also lost its attractiveness in Java EE 8.

## Understand the Source Codes

If you want to know the details of the codes,  please read [my notes for Java EE 8 migration](https://hantsy.gitbooks.io/java-ee-8-by-example/content/), it also [open-sourced as a Github project](https://github.com/hantsy/javaee8-by-example-gitbook).

BTW, I maintained a [Java EE 8/Jatarta EE 8 resource checklist](https://github.com/hantsy/awesome-javaee8).

Welcome to contribute.


