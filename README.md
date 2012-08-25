## QuickUtils


<!--
<div style="float: left"><img src="http://cesarferreira.com/wp-content/uploads/2012/07/dribbble.png"  
style="width:200px" /></div>  
-->
<div style="float: left"><img src="https://dl.dropbox.com/u/86831/cesarferreira/nexus_header2.jpg" /></div>  
This repository offers a set of random useful classes to deal with repetitive tasks in the Android Framework.
Intended to help you getting your Android applications off the ground quickly, by offering ready-to-use components and utility classes that wrap a lot of the boilerplate that's involved when writing Android apps.


## Changelog

### 0.3 <sub><sup>`2012/08/27`</sup></sub>
- Added Javadocs to the project
- All the `log` methods now can handle `Throwable` objects, thanks @joelrodrigues for the suggestion
- Added `copyFile(fromFile, toFile)` to the `sdcard` category
- Added `copyFile(fromFileInputStream, toFileOutputStream)` to the `sdcard` category
- Added `getSDCardPath` to the `sdcard`category
- Added `web` category 
- `hasInternetConnection()` moved from `misc` to `web` category
- Added `HTTPGetRequest()` to the `web`category
- Added `degreesToRadians`, `radiansTdoDegrees`, `acos`, `asin`, `atan`, `atan2`, `tan`, `max`, `min`, `abs`, `logarithm`, `exponencial` and `isEven`

### 0.2 <sub><sup>`2012/08/02`</sup></sub>
- Checks if the app has connectivity to the Internet
- `getCurrentTime()` is now divided into `getCurrentTimeInSeconds()` and `getCurrentTimeInMilliseconds()`
- public static long getCurrentTimeInSeconds()
- `sleep` method now accepts milliseconds instead of seconds
- `log` now has a warning method
- Updated max SDK version to 4.0.3
- Added `math` category
- Get a random number between a given range
- Check if a number is odd

### 0.1 <sub><sup>`2012/07/10`</sup></sub>
- Added `log` category
- Added error log method
- Added information log method
- Added verbose log method
- Added debug log method
- Added `sdcard` category
- Added isSDCardAvailable method
- Added isSDCardWritable method
- Added `misc` category
- Added vibrate method
- Added sleep method
- Added toast method with custom lenght time
- Added get current time in miliseconds method



-----




## Usage
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

## MISC <sub><sup>`category`</sup></sub>

With this methods you don't need to set....

```java
QuickUtils.misc.__method__
```


### Get the current time in milliseconds `long`

```java
QuickUtils.misc.getCurrentTimeInMiliseconds();
```

### Get the current time in seconds `long`

```java
QuickUtils.misc.getCurrentTimeInSeconds();
```

### Sleep `void`
Causes the thread which sent this message to sleep for the given interval of time (given in milliseconds). The precision is not guaranteed - the Thread may sleep more or less than requested.


```java
QuickUtils.misc.sleep(durationInMilliseconds);
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

## WEB <sub><sup>`category`</sup></sub>

Web Utils.

```java
QuickUtils.web.__method__
```


### Checks if the app has connectivity to the Internet `boolean`
True if has connection to the Internet and false if it doesn't.

```java
QuickUtils.misc.hasInternetConnection(Context context);
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


## Example


```java
@Override
 public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// SET ENVIRONMENT
		QuickUtils.setDebugMode(QuickUtils.DEVELOPER_MODE); // can be omited

		try {
			QuickUtils.log.w("This is dangerous code");

			// do DANGEROUS STUFF
			// ...
			// ...

			QuickUtils.misc.toast(this,
					"The result of your dangerous calculations is: " + number);

		} catch (Exception exception) {
			QuickUtils.log.d("Exception thrown", exception);
		}

		// Check if you can write on your sdcard
		if (QuickUtils.sdcard.isSDCardAvailable()
				&& QuickUtils.sdcard.isSDCardWritable()) {

			// you can write safely on your sdcard
			// ...
			// ...
		}

	}
```

## Downloads
All the versions can be found [here](https://github.com/cesarferreira/AndroidQuickUtils/tags)


## Instalation
As simple as going to your project's properties and include the `QuickUtils.jar` library or add the QuickUtils project as a library as shown below.

 ![](https://dl.dropbox.com/u/86831/cesarferreira/goanswer.png)


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

## Contributors
 * César Ferreira (cesar.manuel.ferreira@gmail.com)
 * Joel Rodrigues (j.rod.fernandes@gmail.com)
