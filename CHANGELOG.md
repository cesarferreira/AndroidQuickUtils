## Changelog

### 0.3 <sub><sup>`2012/08/26`</sup></sub>
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