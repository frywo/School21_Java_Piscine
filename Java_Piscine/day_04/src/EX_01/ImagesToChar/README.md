# **START Directory:**
```
 ../ImagesToChar
```

# ** COPY RESOURCES:**
```
xcopy .\src\resources\ .\target\resources /e
```

# **COMPILE:** 
```
javac -d ./target/ src/javas/edu/school21/printer/app/main.java src/javas/edu/school21/printer/logic/Logic.java
```

# **TO JAR:**
```
jar cmf ./src/manifest.txt ./target/images-to-chars-printer.jar -C ./target .
```

# **Start app:** 
```
java -jar ./target/images-to-chars-printer.jar . 0

```





