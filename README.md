# gdx-puremvc

This is a minimal implementation of [PureMVC](https://puremvc.org/) design pattern using [libGDX](https://libgdx.com) data structures.
Currently, only the `Core` version using [Singletons](https://en.wikipedia.org/wiki/Singleton_pattern).
### Integration

#### Gradle
![maven-central](https://img.shields.io/maven-central/v/games.rednblack.puremvc/core?color=blue&label=release)
![sonatype-nexus](https://img.shields.io/nexus/s/games.rednblack.puremvc/core?label=snapshot&server=https%3A%2F%2Foss.sonatype.org)

```groovy
dependencies {
    api "games.rednblack.puremvc:core:$pureMvcVersion"
}
```

#### Maven
```xml
<dependency>
  <groupId>games.rednblack.puremvc</groupId>
  <artifactId>core</artifactId>
  <version>1.0.0</version>
  <type>pom</type>
</dependency>
```

### License
The PureMVC Framework is available under the BSD 3-Clause Open Source License. Copyright © 2006-2021 [Futurescale, Inc](http://futurescale.com).

`gdx-puremvc` is available under the Apache 2.0 Open Source License.
```
Copyright (c) 2022 Francesco Marongiu.

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