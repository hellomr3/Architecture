# Architecture
Architecture for android Mvvm

# Dependency

Add this in your root build.gradle file (not your module build.gradle file):
``` groovy
allprojects {
    repositories {
        maven { url "https://www.jitpack.io" }
    }
}

buildscript {
    repositories {
        maven { url "https://www.jitpack.io" }
    }	
}
```

Then, add the library to your module build.gradle
``` groovy
dependencies {
  implementation 'com.github.hellomr3:Architecture:1.0.8'
}
```

if mavenLocal
``` groovy
    implementation 'com.looptry.architecture:architecture:1.0.8'
```
