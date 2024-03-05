/*
 * Copyright 2022 jrosclient project
 * 
 * Website: https://github.com/lambdaprime/jros2messages
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package id.jros2messages;

import id.jros2messages.impl.DdsDataInput;
import id.jros2messages.impl.DdsDataOutput;
import id.jros2messages.impl.JRos2MessagesConstants;
import id.jrosmessages.JRosMessageMetrics;
import id.jrosmessages.Message;
import id.jrosmessages.MessageMetadataAccessor;
import id.kineticstreamer.KineticStreamReader;
import id.kineticstreamer.KineticStreamWriter;
import id.kineticstreamer.PublicStreamedFieldsProvider;
import id.kineticstreamer.StreamedFieldsProvider;
import io.opentelemetry.api.GlobalOpenTelemetry;
import io.opentelemetry.api.metrics.LongHistogram;
import io.opentelemetry.api.metrics.Meter;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.nio.ByteBuffer;
import java.time.Duration;
import java.time.Instant;

/**
 * Performs message (de)serialization (from)to stream of bytes.
 *
 * <p>Thread safe.
 *
 * @author lambdaprime intid@protonmail.com
 */
public class MessageSerializationUtils {
    private final Meter METER =
            GlobalOpenTelemetry.getMeter(MessageSerializationUtils.class.getSimpleName());
    private final LongHistogram MESSAGE_SERIALIZATION_TIME_METER =
            METER.histogramBuilder(JRosMessageMetrics.MESSAGE_SERIALIZATION_TIME_METRIC)
                    .setDescription(
                            JRosMessageMetrics.MESSAGE_SERIALIZATION_TIME_METRIC_DESCRIPTION)
                    .ofLongs()
                    .build();
    private final LongHistogram MESSAGE_DESERIALIZATION_TIME_METER =
            METER.histogramBuilder(JRosMessageMetrics.MESSAGE_DESERIALIZATION_TIME_METRIC)
                    .setDescription(
                            JRosMessageMetrics.MESSAGE_DESERIALIZATION_TIME_METRIC_DESCRIPTION)
                    .ofLongs()
                    .build();

    private static final MessageMetadataAccessor METADATA_ACCESSOR = new MessageMetadataAccessor();
    private static final StreamedFieldsProvider FIELDS_PROVIDER =
            new PublicStreamedFieldsProvider(
                    clazz -> METADATA_ACCESSOR.getFields((Class<Message>) clazz));

    /**
     * Deserialize message from byte stream
     *
     * @param <M> type of the message
     * @param data byte array with the message
     * @param clazz message class
     */
    public <M extends Message> M read(byte[] data, Class<M> clazz) {
        var startAt = Instant.now();
        try {
            var buf = ByteBuffer.wrap(data);
            var ks =
                    new KineticStreamReader(new DdsDataInput(buf))
                            .withController(
                                    new Ros2KineticStreamController()
                                            .withFieldsProvider(FIELDS_PROVIDER));
            Object obj = ks.read(clazz);
            return (M) obj;
        } catch (Exception e) {
            throw new RuntimeException("Problem reading " + clazz.getName(), e);
        } finally {
            MESSAGE_DESERIALIZATION_TIME_METER.record(
                    Duration.between(startAt, Instant.now()).toMillis(),
                    JRos2MessagesConstants.JROS2MESSAGES_ATTRS);
        }
    }

    /**
     * Serialize message to byte stream
     *
     * @param message message to serialize
     */
    public byte[] write(Message message) {
        var startAt = Instant.now();
        try {
            var bos = new ByteArrayOutputStream();
            var dos = new DataOutputStream(bos);
            var controller = new Ros2KineticStreamController().withFieldsProvider(FIELDS_PROVIDER);
            var ks =
                    new KineticStreamWriter(new DdsDataOutput(dos, controller))
                            .withController(controller);
            ks.write(message);
            return bos.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("Problem writing " + message.getClass().getName(), e);
        } finally {
            MESSAGE_SERIALIZATION_TIME_METER.record(
                    Duration.between(startAt, Instant.now()).toMillis(),
                    JRos2MessagesConstants.JROS2MESSAGES_ATTRS);
        }
    }
}
