package com.tn.esprit.springaiproject.service;


import com.azure.ai.openai.OpenAIClient;
import com.azure.ai.openai.OpenAIClientBuilder;
import com.azure.ai.openai.models.ImageGenerationData;
import com.azure.ai.openai.models.ImageGenerationOptions;
import com.azure.ai.openai.models.ImageGenerations;
import com.azure.core.credential.AzureKeyCredential;
import org.springframework.stereotype.Service;



@Service
public class ImageService{

    public void generateImage(){
        String azureOpenaiKey = "your own key ";
        String endpoint = "your own azure open ai service deployment endpoint";

        OpenAIClient client = new OpenAIClientBuilder()
                .endpoint(endpoint)
                .credential(new AzureKeyCredential(azureOpenaiKey))
                .buildClient();

        String deploymentOrModelName = "Dalle3";
        ImageGenerationOptions imageGenerationOptions = new ImageGenerationOptions(
                "A drawing of the Seattle skyline in the style of Van Gogh");
        ImageGenerations images = client.getImageGenerations(deploymentOrModelName, imageGenerationOptions);

        for (ImageGenerationData imageGenerationData : images.getData()) {
            System.out.printf(
                    "Image location URL that provides temporary access to download the generated image is %s.%n",
                    imageGenerationData.getUrl());
        }
    }
}