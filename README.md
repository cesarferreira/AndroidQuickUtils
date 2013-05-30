## QuickUtils

QuickUtils is a development library for the Android platform.
It is intended to make application development easier and consistent through your applications.


![](https://dl.dropbox.com/u/86831/cesarferreira/nexus_header2.jpg)

<!--
Keep in mind this is still a work in progress. There are thousands of things to do and I hope we'll have enough time to develop all of the features we have in mind :)

[![](http://lh4.ggpht.com/_OHO4y8YcQbs/TQFQZTft6OI/AAAAAAAAMpY/hAloaii6kRA/s288/gd7.png)](http://lh4.ggpht.com/_OHO4y8YcQbs/TQFQZTft6OI/AAAAAAAAMpY/hAloaii6kRA/s800/gd7.png)
[![](https://lh6.googleusercontent.com/-DuebAvhlkYo/Ted7VA4WzoI/AAAAAAAAMvc/fs9IRD4ZtgU/s288/gd8.png)](https://lh6.googleusercontent.com/-DuebAvhlkYo/Ted7VA4WzoI/AAAAAAAAMvc/fs9IRD4ZtgU/s800/gd8.png)
[![](https://lh6.googleusercontent.com/-oQoXEMJcO0E/Ted7UeGFemI/AAAAAAAAMvY/HpfJVIVE6-4/s288/gd9.png)](https://lh6.googleusercontent.com/-oQoXEMJcO0E/Ted7UeGFemI/AAAAAAAAMvY/HpfJVIVE6-4/s800/gd9.png)
[![](http://lh6.ggpht.com/_OHO4y8YcQbs/TQFQTfazM2I/AAAAAAAAMpQ/lgPpIKImdZw/s288/gd5.png)](http://lh6.ggpht.com/_OHO4y8YcQbs/TQFQTfazM2I/AAAAAAAAMpQ/lgPpIKImdZw/s800/gd5.png)
[![](http://lh3.ggpht.com/_OHO4y8YcQbs/TQFQS5hyrKI/AAAAAAAAMpM/4hqL9y_tVgM/s288/gd4.png)](http://lh3.ggpht.com/_OHO4y8YcQbs/TQFQS5hyrKI/AAAAAAAAMpM/4hqL9y_tVgM/s800/gd4.png)
-->
A sample application can be downloaded on the [Android Market](blabla)



## Install

Via **github**:

1. Check a clone of this repo: `git@github.com:cesarferreira/AndroidQuickUtils.git`
2. Import the **`QuickUtils`** project into Eclipse
3. Open your project properties
4. Go to Android
5. Add **QuickUtils** as a `Library`

Or via **download**:

1. Download the latest `quickutils.jar` from [here](https://github.com/cesarferreira/AndroidQuickUtils/tags)
2. Create a `lib/` folder in your project and add the `jar`
3. Inside Eclipse Right Click the jar and `Build Path -> Add to Build Path`



**Note:**
if you use the `install via github`, all you need to do to get the latest version of **QuickUtills** is `git pull` on the root of its directory. If you use the second method you need to download the newest version and re-add the `.jar`.

## Downloads
All the versions can be found [here](https://github.com/cesarferreira/AndroidQuickUtils/tags)


## How to Use
**Note:** There is a sample application in the folder **demos** with examples for all the methods.



Really simple usage, you just need to specify the category and the method you want to use.

```java
QuickUtils.__category__.__method__
```
-------------------

## LOG <sub><sup>`category`</sup></sub>

With this methods you don't need to set the TAG variable in every class of your project and you can disable the logs everywhere without deleting/commenting the log lines by setting the debug mode to PRODUCTION (explained in the previous section).

```java
QuickUtils.log.__method__
```


### Error Log 

```java
QuickUtils.log.e("error description");
```

```java
QuickUtils.log.e("error description", throwable);
```

### Verbose Log 

```java
QuickUtils.log.v("verbose description");
```

```java
QuickUtils.log.v("verbose description", throwable);
```

### Information Log 

```java
QuickUtils.log.i("information description");
```
```java
QuickUtils.log.i("information description", throwable);
```

### Warning Log 

```java
QuickUtils.log.w("warning description");
```
```java
QuickUtils.log.w("warning description", throwable);
```

### Debug Log 

```java
QuickUtils.log.d("debug description");
```

```java
QuickUtils.log.d("debug description", throwable);
```

------------

## DATE <sub><sup>`category`</sup></sub>

Date Utils

```java
QuickUtils.date.__method__
```

### Get the current time in milliseconds `long`

```java
QuickUtils.date.getCurrentTimeInMiliseconds();
```

### Get the current time in seconds `long`

```java
QuickUtils.date.getCurrentTimeInSeconds();
```

### Gets a date with a desired format as a String `String`
They "day" parameter can be provided as: 
- `QuickUtils.date.YESTERDAY`, 
- `QuickUtils.date.TODAY` or 
- `QuickUtils.date.TOMORROW`

The format can be provided as e.g. "yyyy-MM-dd HH:mm:ss"

```java
QuickUtils.date.getDayAsString(int day, String format);
```

### Gets the desired day as a Date `Date`
They "day" parameter can be provided as:
- `QuickUtils.date.YESTERDAY`, 
- `QuickUtils.date.TODAY` or 
- `QuickUtils.date.TOMORROW`

```java
QuickUtils.date.getDayAsDate(int day);
```


------------

## MISC <sub><sup>`category`</sup></sub>

Misc utils


```java
QuickUtils.misc.__method__
```

### Toast method with short duration `void`

```java
QuickUtils.misc.toast(context, "This is a short toast");
```

### Toast with non specified duration `void`
Either `Toast.LENGTH_SHORT` or `Toast.LENGTH_LONG`

```java
QuickUtils.misc.toast(context, "This is a short toast", Toast.LENGTH_LONG);
```

### Sleep `void`
Causes the thread which sent this message to sleep for the given interval of time (given in milliseconds). The precision is not guaranteed - the Thread may sleep more or less than requested.

```java
QuickUtils.misc.sleep(durationInMilliseconds);
```

------------

## MATH <sub><sup>`category`</sup></sub>

Math Utils.

```java
QuickUtils.math.__method__
```

### Returns a random number `int`
A random int between MIN inclusive and MAX exclusive.

```java
QuickUtils.math.getRandomNumber(int min, int max);
```

### Check if a number is Odd `boolean`
True if the num is odd and false if it's even

```java
QuickUtils.math.isOdd(int num);
```

### Check if a number is Even `boolean`
True if the num is even and false if it's odd

```java
QuickUtils.math.isEven(int num);
```

### Degrees to radians `float`
Returns the converted value

```java
QuickUtils.math.degreesToRadians(float degrees);
```

### Radians to degrees `float`
Returns the converted value

```java
QuickUtils.math.radiansTdoDegrees(float radians);
```

### Arc cosine `float`
Returns the closest double approximation of the arc cosine of the argument within the range [0..pi]. The returned result is within 1 ulp (unit in the last place) of the real result.

```java
QuickUtils.math.acos(float value);
```

### Arc sine `float`
Returns the closest double approximation of the arc sine of the argument within the range [-pi/2..pi/2]. The returned result is within 1 ulp (unit in the last place) of the real result.

```java
QuickUtils.math.asin(float value);
```

### Arc tangent `float`
Returns the closest double approximation of the arc tangent of the argument within the range [-pi/2..pi/2]. The returned result is within 1 ulp (unit in the last place) of the real result.

```java
QuickUtils.math.atan(float value);
```
### Arc tangent of y/x within the range [-pi..pi] `float`
Returns the closest double approximation of the arc tangent of y/x within the range [-pi..pi]. This is the angle of the polar representation of the rectangular coordinates (x,y). The returned result is within 2 ulps (units in the last place) of the real result.

```java
QuickUtils.math.atan2(float value);
```

### Tangent of an angle `float`
Returns the tangent

```java
QuickUtils.math.tan(float angle);
```
### Absolute value `float`
Returns the absolute value

```java
QuickUtils.math.abs(float v);
```

### Number's logarithm `float`
Returns the closest double approximation of the natural logarithm of the argument. The returned result is within 1 ulp (unit in the last place) of the real result.

```java
QuickUtils.math.logarithm(float number);
```
### Number's Exponencial `float`
Returns the closest double approximation of the natural logarithm of the argument. The returned result is within 1  ulp (unit in the last place) of the real result.

```java
QuickUtils.math.exponencial(float number);
```

### Gets the higher number `float` `int`
the higher number between a and b

```java
QuickUtils.math.max(float a, float b);
```

```java
QuickUtils.math.max(int a, int b);
```

### Gets the lower number `float` `int`
the lower number between a and b

```java
QuickUtils.math.min(float a, float b);
```

```java
QuickUtils.math.min(int a, int b);
```

------------

## VOICE <sub><sup>`category`</sup></sub>

Voice utils


```java
QuickUtils.voice.__method__
```

### Start activity of speechRecognition (Void)
Start activity of Google Voice Recognition

```java
QuickUtils.misc.speechRecognition(final Activity activity, int maxResults, String text);
```

### results from Google Voice Recognition (ArrayList<String>)
To be called onActivityResult()
Return an ArrayList<String> with all results or null if was not possible to get any results

```java
QuickUtils.misc.getSpeechRecognitionResults(int requestCode, int resultCode, Intent data);
```

### First result from Google Speech Recognition matching the Dictionary given (String)
To be called onActivityResult() 
Return a String with the first result matched or null if was not possible to get any result

```java
QuickUtils.misc.getSpeechRecognitionResultFromDicionary(int requestCode, int resultCode, Intent data, ArrayList<String> array);
```

### First result from Google Speech Recognition (String)
To be called onActivityResult() 
Return a string containing the first result of what was recognized

```java
QuickUtils.misc.getSpeechRecognitionFirstResult(int requestCode, int resultCode, Intent data);
```

------------

## WEB <sub><sup>`category`</sup></sub>

Web Utils.

```java
QuickUtils.web.__method__
```


### Set wireless connectivity On
also this method will need the permissions "android.permission.CHANGE_WIFI_STATE" and "android.permission.ACCESS_WIFI_STATE"
true if was set successfully and false if it wasn't

```java
QuickUtils.web.changeWirelessState(Context context, boolean state);
```

### Check if can connect to the server
also this method will need the permissions "android.permission.INTERNET"
true if the connection returned a successful code

```java
QuickUtils.web.checkServerConnection(URL u);
```

### Check if can connect to the server
also this method will need the permissions "android.permission.INTERNET"
true if the connection returned a successful code

```java
QuickUtils.web.checkServerConnection(String serverURL);
```

###

```java
QuickUtils.web.hasInternetConnection(Context context);
```

### Does a GET request to a given url `String`
Note: Please use this method on an AsyncTask in order not to freeze the application unnecessarely  (http://developer.android.com/guide/practices/responsiveness.html)

```java
QuickUtils.web.HTTPGetRequest(String url);
```

------------

## SDCARD <sub><sup>`category`</sup></sub>

SDCard Utils.

```java
QuickUtils.sdcard.__method__
```

### Check if the SD Card is Available `boolean`
True if the sd card is available and false if it is not

```java
QuickUtils.sdcard.isSDCardAvailable();
```

### Check if the SD Card is Writable `boolean`
True if the sd card is writable and false if it is not

```java
QuickUtils.sdcard.isSDCardWritable();
```

### Get the path to the SDCard `String`
Return the complete path to the SDCard

```java
QuickUtils.sdcard.getSDCardPath();
```

### Check if file exists on SDCard or not
boolean - if file exist on SDCard or not

```java
QuickUtils.sdcard.checkIfFileExists(String filePath)
```

### Copy a file from a place to another `void`
Creates the specified `toFile` as a byte for byte copy of  the `fromFile`. If `toFile` already exists, then it will be replaced with a copy of `fromFile`. The name and path of`toFile` will be that of `toFile`.

Note: `fromFile` and `toFile` will be closed by this function.


As Files

```java
QuickUtils.sdcard.copyFile(fromFile, toFile);
```

or with Input and Output Streams:

```java
QuickUtils.sdcard.copyFile(fromFileInputStream, toFileOutputStream);
```


## Setting up the environment
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

If you intend to use the vibration util don't forget to add the vibration permission, if you haven't already, in your `<manifest>`:

```xml
<uses-permission android:name="android.permission.VIBRATE" />   
```

If you intend to use the `hasConnectivity` method don't forget to add the network state access permission, if you haven't already, in your `<manifest>`:

```xml
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
```

If you intend to use the `HTTPGetRequest` method, if you haven't already, in your `<manifest>`:

```xml
<uses-permission android:name="android.permission.INTERNET" />
```




## Contributing

Want to contribute? Great! 

1. Fork it.
2. Create a branch (`git checkout -b my_branch`)
3. Commit your changes (`git commit -am "Added changes"`)
4. Push to the branch (`git push origin my_branch`)
5. Create an [Issue](https://github.com/cesarferreira/AndroidQuickUtils/issues) with a link to your branch
6. Enjoy a refreshing Diet Coke and wait


## License
Apache License, Version 2.0 (http://www.apache.org/licenses/LICENSE-2.0.html)

## Authors
 * César Ferreira (cesar.manuel.ferreira@gmail.com)

## Contributors <sup>([graph](https://github.com/cesarferreira/AndroidQuickUtils/graphs/contributors "link"))</sup>
 * César Ferreira (cesar.manuel.ferreira@gmail.com)
 * Luís Pereira (luispereira268@gmail.com)
