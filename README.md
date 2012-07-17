## Synopsis

This repository offers a set of random useful classes to deal with repetitive tasks in the Android Framework.
Intended to help you getting your Android applications off the ground quickly, by offering ready-to-use components and utility classes that wrap a lot of the boilerplate that's involved when writing Android apps.


-----

## Instalation
As simple as going to your project's properties and include the `QuickUtils.jar` library.

 ![](https://dl.dropbox.com/u/86831/cesarferreira/goanswer.png)



## Setup the environment
Set the default TAG for logcat debug purposes

```java
QuickUtils.setTAG("DESIRED_TAG");
```


### Debug mode

To enable the log outputs (This is the default behavior of the library so you don't need to set this up).

```java
QuickUtils.setDebugMode(QuickUtils.DEVELOPER_MODE);
```

When the application is ready to go and you want to disable the log outputs.

```java
QuickUtils.setDebugMode(QuickUtils.PRODUCTION_MODE);
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
import com.cesar.android;
```


## Usage
Really simple usage, you just need to specify the category and the method you want to use.

```java
QuickUtils.__category__.__method__
```
-------------------

### LOG <sub><sup>`category`</sup></sub>

With this methods you don't need to set the TAG variable in every class of your project and you can disable the logs everywhere without deleting/commenting the log lines by setting the debug mode to PRODUCTION (explained in the previous section).

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

##### Information Log `Log.i("TAG","INFORMATION DESCRIPTION")`

```java
QuickUtils.log.i("information description");
```

##### Debug Log `Log.d("TAG","DEBUG DESCRIPTION")`

```java
QuickUtils.log.d("debug description");
```

##### Debug Log `Log.d("TAG","DEBUG DESCRIPTION", Trowable t)`

```java
QuickUtils.log.d("debug description", Throwable t);
```

------------

### MISC <sub><sup>`category`</sup></sub>

With this methods you don't need to set....

```java
QuickUtils.misc.__method__
```


##### Get the current time in miliseconds `long`

```java
QuickUtils.misc.getCurrentTime();
```

##### Sleep `void`
Causes the thread which sent this message to sleep for the given interval of time (given in milliseconds). The precision is not guaranteed - the Thread may sleep more or less than requested.


```java
QuickUtils.misc.sleep(durationInSeconds);
```

##### Toast method with short duration `void`

```java
QuickUtils.misc.toast(context, "This is a short toast");
```

##### Toast with non specified duration `void`
Either `Toast.LENGTH_SHORT` or `Toast.LENGTH_LONG`

```java
QuickUtils.misc.toast(context, "This is a short toast", Toast.LENGTH_LONG);
```
------------

### SDCARD <sub><sup>`category`</sup></sub>

SDCard Utils.

```java
QuickUtils.sdcard.__method__
```

##### Check if the SD Card is Available `boolean`
True if the sd card is available and false if it is not

```java
QuickUtils.sdcard.isSDCardAvailable();
```

##### Check if the SD Card is Writable `boolean`
True if the sd card is writable and false if it is not

```java
QuickUtils.sdcard.isSDCardWritable();
```

## Examples


```java
// SET ENVIRONMENT
QuickUtils.setDebugMode(QuickUtils.DEVELOPER_MODE);

// LOGGING
QuickUtils.log.e("error description");

QuickUtils.log.v("verbose description");
QuickUtils.log.i("information description");

QuickUtils.log.d("debug description");
QuickUtils.log.d("debug description", new Throwable("Throwable Error object"));

// MISC
QuickUtils.misc.getCurrentTime();

QuickUtils.misc.sleep(durationInSeconds);

QuickUtils.misc.toast(this, "This is a short toast");
QuickUtils.misc.toast(this, "This is a short toast", durationInSeconds);

// SDCARD
QuickUtils.sdcard.isSDCardAvailable();
QuickUtils.sdcard.isSDCardWritable();
```



## Contributing
Feel like giving back? We'll happily take contributions via GitHub. For questions, please turn to [QuickUtils developers](mailto:cesar.manuel.ferreira@gmail.com)

## License
Apache License, Version 2.0 (http://www.apache.org/licenses/LICENSE-2.0.html)

## Authors
 * César Ferreira (cesar.manuel.ferreira@gmail.com)
