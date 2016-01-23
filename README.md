![SimpleWebApp SimpleWebApp](http://i.giphy.com/l7DFGwayfiil2.gif)

# Who is

SimpleWebApp is a small Android Studio Project that helps you to deploy Web Apps without know java!.

You can:
  - Setup remote URL (like http:chefly.co)
  - Setup local files
  - Setup custom Android M permissions error page

# How to
It's to easy.
### 1. Clone this repo and import to Android Studio
### 2. Go to app/res/values/strings.xml

```xml
<resources>
    <string name="app_name"><!-- YOUR CUSTOM APP NAME--></string>
    <string name="base_url"><!-- YOUR WEB URL OR FILE --></string>
    <string name="no_permissions_url"> <!-- YOUR PERMISSION FAILED PAGE --></string>
    <string name="action_settings">SETTINGS</string>
    <string name="go_settings">Go settings for grant permissions!</string>
    <string name="permission">Permissions</string>
    <string name="permission_description">We need to use this permissions in this app! :(</string>
    <string name="first_run">first_run</string>
</resources>
```
### 2a. Local files
If you don't have public Web, you can put your web files in app/assets/, for example app/assets/index.html

And... can you setup this file in strings.xml? easy and simple -> file:///android_asset/index.html

For example
```xml
<resources>
    <string name="app_name">Chefly</string>
    <string name="base_url">http://chefly.co</string>
    <string name="no_permissions_url">file:///android_asset/error.html</string>
    <string name="action_settings">SETTINGS</string>
    <string name="go_settings">Go settings for grant permissions!</string>
    <string name="permission">Permissions</string>
    <string name="permission_description">We need to use this permissions in this app! :(</string>
    <string name="first_run">first_run</string>
</resources>
```

### 3a. Setup your permissions in app/manifest/AndroidManifest.xml

This project by default have:
```xml
<!-- INTERNET PERMISSIONS -->
<uses-permission android:name="android.permission.INTERNET"></uses-permission>
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"></uses-permission>
<uses-permission android:name="android.permission.ACCESS_WIFI_STATE"></uses-permission>
<!-- MULTIMEDIA PERMISSIONS -->
<uses-permission android:name="android.permission.CAMERA"></uses-permission>
<!-- LOCATION PERMISSIONS -->
<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION"></uses-permission>
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION"></uses-permission>
```

### 3b. App compatible with Android 6.0 Marshmallow (thanks to [Karumi])
If you want make your app compatible with Android 6.0, you want to do this extra easy step:
Put the sensible permissions in app/res/values/arrays.xml with the same permission name that AndroidManifest.

Example:
```xml
<resources>
    <string-array name="permissions">
        <!-- MULTIMEDIA PERMISSIONS -->
        <item>android.permission.CAMERA</item>

        <!-- LOCATION PERMISSIONS -->
        <item>android.permission.ACCESS_COARSE_LOCATION</item>
        <item>android.permission.ACCESS_FINE_LOCATION</item>
    </string-array>
</resources>
```
### 4. Put your custom icon
Put your icon in app/res/mimpap with the ic_launcher.png

### 5. That's all, Run it! :)

# Version
1.0.0

# Do you want to contribute?

Feel free to add any useful feature to the library.

# Thanks to
* [Karumi dexter] - Amazing library!
* [Chefly] - The best food ever!
* [Crosswalk] - a super fast port of Markdown to JavaScript

**Free Software, Hell Yeah!**


[Karumi]: <http://github.com/Karumi>
[Karumi dexter]: <https://github.com/Karumi/Dexter>
[Chefly]: <http://chefly.co>
[Crosswalk]: <https://crosswalk-project.org/>

# License

```
Copyright 2016 Sergio Casero

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
