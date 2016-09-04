# WeaverLite

Simple plugin for AspectJ weaving. For example please check this 
library: [Pury](https://github.com/NikitaKozlov/Pury). It uses plugin for weaving library, 
and in the example it uses this plugin for weaving project itself. 

## How to use.

```groovy
buildscript {
    repositories {
        jcenter()
    }
    dependencies {
        classpath 'com.nikitakozlov:weaverlite:1.0.0'
    }
}

apply plugin: 'com.nikitakozlov.weaverlite'
```

## Configuration.

You can enabled/disable it on a debug and/or release build. Default configuration looks like this.
```groovy
weaverLite {
    enabledForDebug = true
    enabledForRelease = false
}
```

## How to start using AspectJ.

1. Add dependency on AspectJ.
       ```
       dependencies {
           compile 'org.aspectj:aspectjrt:1.8.6'
       }
       ```
2. Apply WeaverLite.
3. Add `@AspectJ` annotation to your aspect class.
4. Define pointcut.
5. Enjoy