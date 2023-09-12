package com.doomole.stockproject.baekjoon;

import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.service.OpenAiService;

public class TestChat {
    public static void main(String[] args) {
        OpenAiService service = new OpenAiService("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");
        CompletionRequest completionRequest = CompletionRequest.builder()
                .prompt("Somebody once told me the world is gonna roll me")
                .model("ada")
                .echo(true)
                .build();

        service.createCompletion(completionRequest).getChoices().forEach(System.out::println);
    }
}
