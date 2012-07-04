## Overview

CesarUtils is a simple API for the utils and small snippets of code that you can never recall.

Project is licensed under [Apache License 2.0](http://www.apache.org/licenses/LICENSE-2.0.txt)

-----

## Instalation
As simple as going to your project's properties and include the QuickUtils jar library.

 ![](http://developer.android.com/images/developing/adt-props-libRef.png)



## Setup the environment
Set the default TAG for logcat debug purposes

```java
CesarUtils.setTAG("DESIRED_TAG");
```

AndroidManifest.xml
-------------------

If you intend to use the vibration util don't forget to add the vibrate permission.


Require the following permissions, if you haven't already, in your `<manifest>`:

```xml
<uses-permission android:name="android.permission.VIBRATE" />   
```

Import
------
```java
import com.cesar.android;
```


## Usage
Really simple usage, you just need to specify the category and the method you want to use.

```java
CesarUtils.__category__.__method__
```
-------------------
### LOG <sub><sup>`category`</sup></sub>
For Log methods.

Advantages: 
  you don't need to set the TAG varible in every class of your project

```java
CesarUtils.log.__method__
```


##### Error Log `Log.e("TAG","ERROR DESCRIPTION")`

```java
CesarUtils.log.e("error description");
```

##### Verbose Log `Log.v("TAG","VERBOSE DESCRIPTION")`

```java
CesarUtils.log.v("verbose description");
```

##### Information Log `Log.i("information description")`

```java
CesarUtils.log.i("TAG","INFORMATION DESCRIPTION");
```

##### Debug Log `Log.d("TAG","DEBUG DESCRIPTION")`

```java
CesarUtils.log.d("debug description");
```

##### Debug Log `Log.d("TAG","DEBUG DESCRIPTION", Trowable t)`

```java
CesarUtils.log.d("debug description", Throwable t);
```

## Examples


```java
// SET ENVIRONMENT
CesarUtils.setDebugMode(CesarUtils.DEVELOPER_MODE);

// LOGGING
CesarUtils.log.e("error");

CesarUtils.log.v("verbose");
CesarUtils.log.i("info");

CesarUtils.log.d("debug");
CesarUtils.log.d("debug", new Throwable("Throwable Error object"));

// MISC
CesarUtils.misc.getCurrentTime();

// SDCARD
CesarUtils.sdcard.isSDCardAvailable();
```

## Download

QuickUtils latest version [here](TestReadme.java)

---------------
QuickUtils v0.1 [download](java-classmate/blob/master/src/test/java/com/fasterxml/classmate/TestReadme.java)


## Notes
Feel free to fork this project or suggest features via [email](mailto:cesar.manuel.ferreira@gmail.com)
