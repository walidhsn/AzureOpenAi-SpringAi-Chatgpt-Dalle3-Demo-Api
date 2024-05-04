package com.tn.esprit.springaiproject.controller;

import com.tn.esprit.springaiproject.service.ImageService;


import org.springframework.ai.azure.openai.AzureOpenAiChatClient;
import org.springframework.ai.chat.ChatResponse;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.Map;


@RestController
@RequestMapping("/api")
public class azureAiController {

    private final ImageService imageService;
    private final AzureOpenAiChatClient chatClient;
    @Autowired
    public azureAiController(ImageService imageService,AzureOpenAiChatClient chatClient) {
        this.chatClient = chatClient;
        this.imageService = imageService;
    }



    @GetMapping("/ai/generate/image")
    public String generateImage() throws InterruptedException {
        imageService.generateImage();
        return "check console";
    }
    @GetMapping("/ai/generate")
    public Map generate(@RequestParam(value = "message", defaultValue = "Tell me a joke") String message) {
        return Map.of("generation", chatClient.call(message));
    }

    @GetMapping("/ai/generateStream")
    public Flux<ChatResponse> generateStream(@RequestParam(value = "message", defaultValue = "Tell me a joke") String message) {
        Prompt prompt = new Prompt(new UserMessage(message));
        return chatClient.stream(prompt);
    }

}
