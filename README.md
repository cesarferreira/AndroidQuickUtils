## Synopsis

This repository offers a set of random useful classes to deal with repetitive tasks in the Android Framework, a simple API for the utils and small snippets of code that you can never recall.
 helps you getting your Android applications off the ground quickly, by offering ready-to-use components and utility classes that wrap a lot of the boilerplate that's involved when writing Android apps.


-----

## Instalation
As simple as going to your project's properties and include the `QuickUtils.jar` library.

 ![](http://developer.android.com/images/developing/adt-props-libRef.png)



## Setup the environment
Set the default TAG for logcat debug purposes

```java
QuickUtils.setTAG("DESIRED_TAG");
```

AndroidManifest.xml
-------------------

If you intend to use the vibration util don't forget to add the vibrate permission, if you haven't already, in your `<manifest>`:

```xml
<uses-permission android:name="android.permission.VIBRATE" />   
```

Import
------
```java
import com.Quick.android;
```


## Usage
Really simple usage, you just need to specify the category and the method you want to use.

```java
QuickUtils.__category__.__method__
```
-------------------
### LOG <sub><sup>`category`</sup></sub>
For Log methods.

Advantages: 
  you don't need to set the TAG varible in every class of your project

```java
QuickUtils.log.__method__
```


##### Error Log `Log.e("TAG","ERROR DESCRIPTION")`

```java
QuickUtils.log.e("error description");
```

##### Verbose Log `Log.v("TAG","VERBOSE DESCRIPTION")`

```java
QuickUtils.log.v("verbose description");
```

##### Information Log `Log.i("information description")`

```java
QuickUtils.log.i("TAG","INFORMATION DESCRIPTION");
```

##### Debug Log `Log.d("TAG","DEBUG DESCRIPTION")`

```java
QuickUtils.log.d("debug description");
```

##### Debug Log `Log.d("TAG","DEBUG DESCRIPTION", Trowable t)`

```java
QuickUtils.log.d("debug description", Throwable t);
```

## Examples


```java
// SET ENVIRONMENT
QuickUtils.setDebugMode(QuickUtils.DEVELOPER_MODE);

// LOGGING
QuickUtils.log.e("error");

QuickUtils.log.v("verbose");
QuickUtils.log.i("info");

QuickUtils.log.d("debug");
QuickUtils.log.d("debug", new Throwable("Throwable Error object"));

// MISC
QuickUtils.misc.getCurrentTime();

// SDCARD
QuickUtils.sdcard.isSDCardAvailable();
```

## Download

QuickUtils latest version [here](TestReadme.java)

---------------
QuickUtils v0.1 [download](java-classmate/blob/master/src/test/java/com/fasterxml/classmate/TestReadme.java)


## Contributing
Feel like giving back? We'll happily take contributions via GitHub. For questions, please turn to [QuickUtils developers](mailto:cesar.manuel.ferreira@gmail.com)

## License
Apache License, Version 2.0 (http://www.apache.org/licenses/LICENSE-2.0.html)

## Authors
 * César Ferreira (cesar.manuel.ferreira@gmail.com)
