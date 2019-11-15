# `@Nested` Tests Filtering Bug in Surefire

`<exclude>` over source file names does not exclude
the `@Nested` test classes, defined inside the matching
source files, if they end with `Tests`: https://issues.apache.org/jira/browse/SUREFIRE-1720

See `dt.AppIntegrationTest.ApiTests`: all tests inside this
file must be excluded, but `ApiTests` are included.

## How to Reproduce

Surefire configuration:
```xml
<plugin>
  <artifactId>maven-surefire-plugin</artifactId>
  <version>${surefire.version}</version>
  <configuration>
    <excludes>
      <!-- (1) Such syntax operates on file names, according to the docs:
           https://maven.apache.org/surefire/maven-surefire-plugin/examples/inclusion-exclusion.html
           but does not exclude the JUnit 5 @Nested classes, defined in *matching* files. -->
      <exclude>**/*IntegrationTest.java</exclude>
<!--              (2) Regex-syntax operates on classes, according to the docs;
           and excludes the @Nested classes properly: -->
<!--              <exclude>%regex[.*IntegrationTest\$.*]</exclude>-->
    </excludes>
  </configuration>
</plugin>
```

With 3.0.0-M3:
```shell script
mvn test
```

With a particular surefire version:

```shell script
mvn verify -Dsurefire.version=2.22.2
```

## Expected Behaviour

When a pattern over source file names is used (`<exclude>*Pattern.java<exclude>`),
it must apply to all classes inside that file.

## Actual Behaviour

The exclusion does not apply to some classes (e.g., ending with `Tests`).