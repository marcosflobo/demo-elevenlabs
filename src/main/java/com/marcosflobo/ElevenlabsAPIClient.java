package com.marcosflobo;


import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Header;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.client.annotation.Client;

@Client("https://api.elevenlabs.io/")
@Header(name = "xi-api-key", value = "foo")
@Header(name = "content-type", value = "application/json")
public interface ElevenlabsAPIClient {

  @Get("/v1/voices")
  String getVoices();

  @Get("/v1/models")
  String getModels();

  @Get("/v1/user")
  String getUser();

  @Post("/v1/text-to-speech/21m00Tcm4TlvDq8ikWAM")
  byte[] getTextToSpeech(@Body String request);

}