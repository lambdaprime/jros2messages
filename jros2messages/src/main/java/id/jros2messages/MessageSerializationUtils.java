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
import id.jrosmessages.Message;
import id.kineticstreamer.KineticStreamReader;
import id.kineticstreamer.KineticStreamWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;

/**
 * Performs message (de)serialization (from)to stream of bytes.
 *
 * <p>Thread safe.
 *
 * @author lambdaprime intid@protonmail.com
 */
public class MessageSerializationUtils {

    /**
     * Deserialize message from byte stream
     *
     * @param <M> type of the message
     * @param data byte array with the message
     * @param clazz message class
     */
    public <M extends Message> M read(byte[] data, Class<M> clazz) {
        try {
            var dis = new DataInputStream(new ByteArrayInputStream(data));
            var ks =
                    new KineticStreamReader(new DdsDataInput(dis))
                            .withController(new Ros2KineticStreamReaderController());
            Object obj = ks.read(clazz);
            return (M) obj;
        } catch (Exception e) {
            throw new RuntimeException("Problem reading " + clazz.getName(), e);
        }
    }

    /**
     * Serialize message to byte stream
     *
     * @param message message to serialize
     */
    public byte[] write(Message message) {
        try {
            var bos = new ByteArrayOutputStream();
            var dos = new DataOutputStream(bos);
            var ks =
                    new KineticStreamWriter(new DdsDataOutput(dos))
                            .withController(new Ros2KineticStreamWriterController());
            ks.write(message);
            return bos.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("Problem writing " + message.getClass().getName(), e);
        }
    }
}
