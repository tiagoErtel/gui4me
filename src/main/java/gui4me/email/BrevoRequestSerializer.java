package gui4me.email;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
import java.util.Map;

@JsonComponent
public class BrevoRequestSerializer extends JsonSerializer<BrevoRequest> {

    @Override
    public void serialize(BrevoRequest request, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();

        gen.writeArrayFieldStart("to");
        for (String receiver : request.getReceivers()) {
            gen.writeStartObject();
            gen.writeStringField("email", receiver);
            gen.writeEndObject();
        }
        gen.writeEndArray();

        gen.writeNumberField("templateId", request.getTemplate().getTemplateId());

        Map<String, Object> params = request.getTemplate().getParams();
        if (!params.isEmpty()) {
            gen.writeObjectFieldStart("params");
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                gen.writeStringField(entry.getKey(), entry.getValue().toString());
            }
            gen.writeEndObject();
        }

        gen.writeEndObject();
    }
}
