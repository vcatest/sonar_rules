import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/example")
public class RSPEC_7518 {
    private final OpenAIClient client = new OpenAIClient();

    @PostMapping("/example")
    public ResponseEntity<?> example(@RequestBody Map<String, String> payload) {
        String promptText = payload.get("prompt_text");
        String systemText = payload.get("sys_text");
        String developerText = payload.get("dev_text");
        ChatCompletionCreateParams request = ChatCompletionCreateParams.builder()
            .model(ChatModel.GPT_3_5_TURBO)
            .maxCompletionTokens(2048)
            .addSystemMessage(systemText)
            .addDeveloperMessage(developerText)
            .addUserMessage(promptText)
            .build();
        var completion = client.chat().completions().create(request);
        return ResponseEntity.ok(
            Map.of(
                "response",
                completion.choices().stream()
                    .flatMap(choice -> choice.message().content().stream())
                    .collect(Collectors.joining(" | "))
            )
        );
    }
}

// Dummy classes for illustration
class OpenAIClient {
    public Chat chat() { return new Chat(); }
    class Chat {
        public Completions completions() { return new Completions(); }
        class Completions {
            public CompletionResult create(ChatCompletionCreateParams params) { return new CompletionResult(); }
        }
    }
}
class ChatCompletionCreateParams {
    public static Builder builder() { return new Builder(); }
    static class Builder {
        public Builder model(String model) { return this; }
        public Builder maxCompletionTokens(int tokens) { return this; }
        public Builder addSystemMessage(String msg) { return this; }
        public Builder addDeveloperMessage(String msg) { return this; }
        public Builder addUserMessage(String msg) { return this; }
        public ChatCompletionCreateParams build() { return new ChatCompletionCreateParams(); }
    }
}
class ChatModel {
    public static final String GPT_3_5_TURBO = "gpt-3.5-turbo";
}
class CompletionResult {
    public java.util.List<Choice> choices() { return java.util.Collections.emptyList(); }
    class Choice {
        public Message message() { return new Message(); }
    }
    class Message {
        public java.util.List<String> content() { return java.util.Collections.emptyList(); }
    }
}
