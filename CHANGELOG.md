## Changelog

### 1.0.1
- Added `Animation` category


### 0.4
- Added `Demos` project for demonstration purposes
- Added to the `web`category:
	- changeWirelessState(Context context, boolean state)
	- method checkServer(Url url)
	- checkServerConnection(String serverURL)
	- checkServerConnection(Url url)
- Added to the `sdcard` category:
	- checkIfFileExists(String filePath)
	- String getSDCardPath()
    - File getSDCardPath()
	- appendTextToLog(String text, String logFilePath)
	- appendTextToFile(String text, String logFilePath)
- Added to the `date` category:
	- getCurrentDay()
	- getCurrentMonth()
	- getCurrentYear()
	- getTimeSinceMidnight()
- Added `security` category
	- calculateMD5(String string)
	- calculateSHA1(String string) 

### 0.3
- Added Javadocs to the project
- All the `log` methods now can handle `Throwable` objects, thanks @joelfernandes for the suggestion
- Added `copyFile(fromFile, toFile)` to the `sdcard` category
- Added `copyFile(fromFileInputStream, toFileOutputStream)` to the `sdcard` category
- Added `getSDCardPath` to the `sdcard`category
- Added `web` category 
- `hasInternetConnection()` moved from `misc` to `web` category
- Added `HTTPGetRequest()` to the `web`category
- Added `degreesToRadians`, `radiansTdoDegrees`, `acos`, `asin`, `atan`, `atan2`, `tan`, `max`, `min`, `abs`, `logarithm`, `exponencial` and `isEven` to the `math` category
- `getCurrentTimeInSeconds()` and `getCurrentTimeInMilliseconds()` moved to the `date` category
- Added`getDayAsString(int day, String format)` and `getDayAsDate(int day)` to the `date` category


### 0.2
- Checks if the app has connectivity to the Internet
- `getCurrentTime()` is now divided into `getCurrentTimeInSeconds()` and `getCurrentTimeInMilliseconds()`
- public static long getCurrentTimeInSeconds()
- `sleep` method now accepts milliseconds instead of seconds
- `log` now has a warning method
- Updated max SDK version to 4.0.3
- Added `math` category
- Get a random number between a given range
- Check if a number is odd

### 0.1
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