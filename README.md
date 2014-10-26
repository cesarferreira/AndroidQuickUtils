QuickUtils  
============
[![QuickUtils Maven Central](http://img.shields.io/badge/QuickUtils%20Maven%20Central-0.2.0-brightgreen.svg?style=flat)](http://search.maven.org/#search%7Cga%7C1%7Cg%3A%22com.cesarferreira.quickutils%22) [![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-AndroidQuickUtils-brightgreen.svg?style=flat)](https://android-arsenal.com/details/1/870)

QuickUtils is a development library for the Android platform.
It is intended to make application development easier and consistent throughout your applications.


![Header](https://raw.github.com/cesarferreira/AndroidQuickUtils/master/images/header-crop.png)

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
QuickUtils.init(context);
```

Really simple usage, you just need to specify the category and the method you want to use.

```java
QuickUtils.__category__.__method__
```

e.g.
```java
QuickUtils.log.e("this is an error");
QuickUtils.system.vibrate(1000);
QuickUtils.math.poundsToKg(weight);
QuickUtils.sdcard.exists(someFile);
QuickUtils.security.encodeBase64(someString);
// Etc.
```

Quick Sample
-------------------
A small sample of the shared preferences wrapper to save/read data with only 1 line of code.


```JAVA
// save data
QuickUtils.prefs.save(key, value);

// retrieve saved data
QuickUtils.prefs.getString(key, defaultValue);
QuickUtils.prefs.getInt(key, defaultValue);

// remove
QuickUtils.prefs.remove(key);
```

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
