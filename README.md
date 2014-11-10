QuickUtils  
============
[![QuickUtils Maven Central](http://img.shields.io/badge/QuickUtils%20Maven%20Central-0.2.0-brightgreen.svg?style=flat)](http://search.maven.org/#search%7Cga%7C1%7Cg%3A%22com.cesarferreira.quickutils%22) [![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-AndroidQuickUtils-brightgreen.svg?style=flat)](https://android-arsenal.com/details/1/870)

This repository offers a set of random useful classes to deal with repetitive tasks in the Android Framework. Intended to help you getting your Android applications off the ground quickly, by offering ready-to-use components and utility classes that wrap a lot of the boilerplate that’s involved when writing Android apps.


![Header](https://raw.github.com/cesarferreira/AndroidQuickUtils/master/images/header-crop.png)
<!--
## Features

- **More than 1000 preloaded Colors in the project** - check the list [here](https://github.com/cesarferreira/AndroidQuickUtils/wiki/Colors.xml)
- **Log Category** - Write logs without needing tags, etc.
- **TextViews** - TypeWriter effect, Roboto bold, thin, condensed, etc.
- **Animation Category** - With classes for blinking views, etc.
- **Math Category** - Sin, arcsin, tang, random numbers, etc.
- **SDCard Category** - SDCard availability, write files, etc.
- **Security Category** - EncodeBase64, DecodeBase64, Sha1, MD5, etc.
- **System Category** - Sleep, vibrate, toasts, navigate to activity, etc.
- **Voice Category** - Get speech recognition results, etc.
- **Date Category** - Get current time in seconds and miliseconds, etc.
- **Image Category** - Apply blur to a bitmap, image brightness, etc.
- **Web Category** - has internet connection, change wireless state, GET requests, etc
- **Image Views** - Top crop image views, circular image views etc.
- ...
-->

## Installation

Including in your project via Gradle:

```groovy
dependencies {
    compile 'com.cesarferreira.quickutils:library:2.+'
}
```

## Usage

Init the library in your Application class for instance

```java
public class SampleApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        QuickUtils.init(context);
    }
}

```

All you need to do is to specify the category and the method you want to use.

```java
QuickUtils.__category__.__method__
```

E.g.
```java
// Log something
QuickUtils.log.e("this is an error");
// Make the smartphone vibrate for the amount of time you want
QuickUtils.system.vibrate(1000);
// Convert pounds to KG
QuickUtils.math.poundsToKg(weight);
// Does that file exists?
QuickUtils.sdcard.exists(someFile);
// Encode a string
QuickUtils.security.encodeBase64(someString);
// Save data
QuickUtils.prefs.save(key, value);
// Retrieve saved data
QuickUtils.prefs.getString(key, defaultValue);
QuickUtils.prefs.getInt(key, defaultValue);
// Remove saved data
QuickUtils.prefs.remove(key);
//  Etc. (hundreds more methods)
```
<!--A small sample of the shared preferences wrapper to save/read data with only 1 line of code.-->

More Documentation
------------------
Take a look at our [wiki](https://github.com/cesarferreira/AndroidQuickUtils/wiki).

# Contributing
Contributions welcome via Github pull requests.

## License
QuickUtils is available under the MIT license. See the LICENSE file for more info.


## Contributors <sup>([graph](https://github.com/cesarferreira/AndroidQuickUtils/graphs/contributors "link"))</sup>

* [@cesarferreira](https://github.com/cesarferreira "link")
* [@nunofeliciano](https://github.com/nunofeliciano "link")
* [@luispereira](https://github.com/luispereira "link")
* [@telmomarques](https://github.com/telmomarques "link")
