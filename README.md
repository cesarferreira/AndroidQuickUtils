QuickUtils  
============
[![QuickUtils Maven Central](http://img.shields.io/badge/QuickUtils%20Maven%20Central-0.2.0-brightgreen.svg?style=flat)](http://search.maven.org/#search%7Cga%7C1%7Cg%3A%22com.cesarferreira.quickutils%22) [![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-AndroidQuickUtils-brightgreen.svg?style=flat)](https://android-arsenal.com/details/1/870)

This repository offers a set of random useful classes to deal with repetitive tasks in the Android Framework. Intended to help you getting your Android applications off the ground quickly, by offering ready-to-use components and utility classes that wrap a lot of the boilerplate that’s involved when writing Android apps.

![Header](https://raw.github.com/cesarferreira/AndroidQuickUtils/master/images/header-crop.png)

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

        QuickUtils.init(this);
    }
}

```

REST
------------------
Simple REST requests and automatic parse

### Gson Entity

```java
public class Tweet {
    @SerializedName("title")
    public String title;
    @SerializedName("amount_of_retweets")
    public long retweetsTotal;
}
```
### The Request
```java
 QuickUtils.rest.connect()
            .GET() // POST() // PUT()
            .load("https://path/to/the/tweets")
            .as(new TypeToken<List<Tweet>>() {})
            .withCallback(callback);
```

### Post with Header and Body params

```java
Header requestHeader = new Header.Builder()
                .add("Authorization", "Bearer Jhahdau2819ajsbdkasdkasdkashjdkahs")
                .build();

Body requestBody = new Body.Builder()
            .add(DEVICE_TYPE_KEY, "")
            .add(DEVICE_OS_KEY, "")
            .add(DEVICE_UUID_KEY, "")
            .add(DEVICE_PUSH_TOKEN, "")
            .build();

QuickUtils.rest.connect()
            .POST(requestHeader, requestBody)
            .load(url)
            .as(new TypeToken<List<PostEntity>>() {})
            .withCallback(callback);
```


Async Image Loader
---------------------
Image downloading and caching
```java
// Simple
QuickUtils.imageCache.load(IMAGE_URL, imageView);

// or more complete
QuickUtils.imageCache.load(IMAGE_URL, imageView, R.drawable.dummy, R.drawable.error);
```

## Using the util methods

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
