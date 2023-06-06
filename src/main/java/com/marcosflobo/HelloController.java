package com.marcosflobo;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import lombok.extern.slf4j.Slf4j;
import io.micronaut.core.io.scan.ClassPathResourceLoader;
import io.micronaut.core.io.ResourceResolver;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.Scanner;
import java.net.URL;


@Slf4j
@Controller("/hello")
class HelloController {

    private final ElevenlabsAPIClient elevenlabsAPIClient;

    private String outputFileName = "run1.mp3";

    public HelloController(ElevenlabsAPIClient elevenlabsAPIClient) {

        this.elevenlabsAPIClient = elevenlabsAPIClient;
    }

    @Get("/voices")
    public HttpResponse<String> getVoices() {

        return HttpResponse.ok(elevenlabsAPIClient.getVoices());
    }

    @Get("/models")
    public HttpResponse<String> getModels() {

        return HttpResponse.ok(elevenlabsAPIClient.getModels());
    }

    @Get("/user")
    public HttpResponse<String> getUser() {

        return HttpResponse.ok(elevenlabsAPIClient.getUser());
    }

    @Get("/tts")
    public HttpResponse<String> textToSpeech() {

        String text = "10 years ago, I started to be more active in the Tech Events thing. Some of the biggest events I had the opportunity to attend were OpenStack Summit 2015, DockerCon Europe 2018 (2.200 attendees), and KubeCon Europe 2019 (7.700 attendees).";
        //text = "If you are interested in the most technical part of this, I wrote down an article in my blog, on which you can read about how to set up the usage of the API and a small project for using it. Are you using any Text-To-Speech AI in your services? What was your experience? Looking forward to reading your comments!";
        //text = readLongText();
        String request = "{" +
            "\"text\": \"" + text + "\"," +
            "\"model_id\": \"eleven_multilingual_v1\"," +
            "\"voice_settings\": {" +
              "\"stability\": 0.5," +
              "\"similarity_boost\": 0.5" +
            "}" +
          "}";
        log.info("Calling ElevenLabs API...");
        long start = System.currentTimeMillis();
        byte[] audioResponse = elevenlabsAPIClient.getTextToSpeech(request);
        long stop = System.currentTimeMillis();
        log.info("Calling /v1/text-to-speech/ with a '{}' bytes size took '{}' milliseconds", text.length(), (stop - start));
        log.info("Saving mpeg file...");
        saveMpegBytes(audioResponse);
        log.info("File '{}' saved!", outputFileName);
        return HttpResponse.ok();
    }

    private void saveMpegBytes(byte[] audio) {

        try (FileOutputStream fos = new FileOutputStream(outputFileName)) {
            fos.write(audio);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Please note that the number of characters that the file resources/long-text.txt has is 487 and the limit of the free account in 10000 characters
     * @return
     */
    private String readLongText() {
        String text = "empty";
        ClassPathResourceLoader loader = new ResourceResolver().getLoader(ClassPathResourceLoader.class).get();
        Optional<URL> resource = loader.getResource("classpath:long-text.txt");
        try {
            text = new Scanner(resource.get().openStream(), "UTF-8").useDelimiter("\\A").next();
        } catch (Exception e) {
            log.error("The file could not be read: {}", e.getMessage());
        }
        log.info("Text from file ({} bytes): {}", text.length(), text);
        return text;
    }

}