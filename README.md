# demo-elevenlabs
This is a demo of the ElevenLabs API https://docs.elevenlabs.io/api-reference/quick-start/introduction

## Choose a text to use
If you want to change the text that the ElevenLabs API will process, just go to the `HelloController.java` file and in the `textToSpeech` method you will find a var call `text`. Modify it to set the text you want.

You will see down that line that you can uncomment the assigment of `text` var from the `text-long.txt` file for wider tests.

## Run
```shell
./gradlew run
```

## Endpoints available
|endpoint|description|
|--------|-----------|
|/hello/tts|Gives you the MPEG file for a text|
|/hello/voices|Gives you the list of available voices|
|/hello/models|Gives you the list of available voices|
|/hello/user|Gives you the information about your user account|

### Example call
```shell
curl -X GET http://localhost:8080/hello/user
```

## Micronaut 3.9.2 Documentation

- [User Guide](https://docs.micronaut.io/3.9.2/guide/index.html)
- [API Reference](https://docs.micronaut.io/3.9.2/api/index.html)
- [Configuration Reference](https://docs.micronaut.io/3.9.2/guide/configurationreference.html)
- [Micronaut Guides](https://guides.micronaut.io/index.html)
---

- [Shadow Gradle Plugin](https://plugins.gradle.org/plugin/com.github.johnrengelman.shadow)
- [Micronaut Gradle Plugin documentation](https://micronaut-projects.github.io/micronaut-gradle-plugin/latest/)
- [GraalVM Gradle Plugin documentation](https://graalvm.github.io/native-build-tools/latest/gradle-plugin.html)
## Feature http-client documentation

- [Micronaut HTTP Client documentation](https://docs.micronaut.io/latest/guide/index.html#httpClient)


