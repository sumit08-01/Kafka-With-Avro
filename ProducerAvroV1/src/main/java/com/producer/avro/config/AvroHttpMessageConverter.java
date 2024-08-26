package com.producer.avro.config;

import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.EncoderFactory;
import org.apache.avro.io.JsonEncoder;
import org.springframework.http.converter.HttpMessageConverter;

import org.apache.avro.generic.GenericRecord;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;

public class AvroHttpMessageConverter implements HttpMessageConverter<GenericRecord> {

    @Override
    public boolean canRead(Class<?> clazz, MediaType mediaType) {
        // Specify if the converter can read the input data
        return GenericRecord.class.isAssignableFrom(clazz) && (mediaType == null || mediaType.equals(MediaType.APPLICATION_JSON));
    }

    @Override
    public boolean canWrite(Class<?> clazz, MediaType mediaType) {
        // Specify if the converter can write the output data
        return GenericRecord.class.isAssignableFrom(clazz) && (mediaType == null || mediaType.equals(MediaType.APPLICATION_JSON));
    }

    @Override
    public List<MediaType> getSupportedMediaTypes() {
        // Specify the supported media types (e.g., JSON)
        return Collections.singletonList(MediaType.APPLICATION_JSON);
    }

    @Override
    public GenericRecord read(Class<? extends GenericRecord> clazz, HttpInputMessage inputMessage) throws IOException {
        // Implement the logic to read and deserialize an Avro record from the input message
        // This is typically where you would use Avro's deserialization mechanisms
        // Example (pseudo-code):
        // return AvroUtils.deserialize(inputMessage.getBody());
        throw new UnsupportedOperationException("Deserialization not implemented");
    }

    @Override
    public void write(GenericRecord genericRecord, MediaType contentType, HttpOutputMessage outputMessage)
            throws IOException, HttpMessageNotWritableException {

        try (OutputStream outputStream = outputMessage.getBody()) {
            JsonEncoder encoder = EncoderFactory.get().jsonEncoder(genericRecord.getSchema(), outputStream);
            GenericDatumWriter<GenericRecord> writer = new GenericDatumWriter<>(genericRecord.getSchema());
            writer.write(genericRecord, encoder);
            encoder.flush();
        } catch (IOException e) {
            throw new HttpMessageNotWritableException("Error writing Avro data", e);
        }
    }
}

