import com.azure.core.credential.AzureKeyCredential;
import com.azure.ai.textanalytics.models.*;
import com.azure.ai.textanalytics.TextAnalyticsClientBuilder;
import com.azure.ai.textanalytics.TextAnalyticsClient;

public class PII {

    private static String KEY = "251e83b5ca3f42899068b8bdebebe64c";
    private static String ENDPOINT = "https://piiexample.cognitiveservices.azure.com/";

    public static void main(String[] args) {
        TextAnalyticsClient client = authenticateClient(KEY, ENDPOINT);
        recognizePiiEntitiesExample(client);
    }
    // Method to authenticate the client object with your key and endpoint
    static TextAnalyticsClient authenticateClient(String key, String endpoint) {
        return new TextAnalyticsClientBuilder()
                .credential(new AzureKeyCredential(key))
                .endpoint(endpoint)
                .buildClient();
    }

    // Example method for detecting sensitive information (PII) from text 
    static void recognizePiiEntitiesExample(TextAnalyticsClient client)
    {
        // The text that need be analyzed.
        String document = "SSN: 859-98-0987, phone number: 206-307-0177, name: tzuyu, birthday: 1995/11/26, ages:56,  ";
        PiiEntityCollection piiEntityCollection = client.recognizePiiEntities(document);
        System.out.printf("Redacted Text: %s%n", piiEntityCollection.getRedactedText());
        piiEntityCollection.forEach(entity -> System.out.printf(
                "Recognized Personally Identifiable Information entity: %s, entity category: %s, entity subcategory: %s,"
                        + " confidence score: %f.%n",
                entity.getText(), entity.getCategory(), entity.getSubcategory(), entity.getConfidenceScore()));
    }
}