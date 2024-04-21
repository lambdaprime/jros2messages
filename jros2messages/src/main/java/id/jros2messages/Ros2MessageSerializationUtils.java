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
import id.jrosmessages.impl.AbstractMessageSerializationUtils;
import id.kineticstreamer.KineticStreamReader;
import id.kineticstreamer.KineticStreamWriter;
import id.xfunction.logging.TracingToken;
import java.io.DataOutputStream;
import java.nio.ByteBuffer;
import java.util.Map;

/**
 * Performs message (de)serialization (from)to stream of bytes.
 *
 * <p>Thread safe.
 *
 * @author lambdaprime intid@protonmail.com
 */
public class Ros2MessageSerializationUtils extends AbstractMessageSerializationUtils {
    private static final TracingToken TRACING_TOKEN =
            new TracingToken(Ros2MessageSerializationUtils.class.getSimpleName());

    public Ros2MessageSerializationUtils() {
        super(TRACING_TOKEN, Map.of("RosVersion", "ROS2"));
    }

    @Override
    protected KineticStreamReader newKineticStreamReader(ByteBuffer buf) {
        var controller = new Ros2KineticStreamController().withFieldsProvider(FIELDS_PROVIDER);
        return new KineticStreamReader(new DdsDataInput(TRACING_TOKEN, buf, controller))
                .withController(controller);
    }

    @Override
    protected KineticStreamWriter newKineticStreamWriter(DataOutputStream dos) {
        var controller = new Ros2KineticStreamController().withFieldsProvider(FIELDS_PROVIDER);
        return new KineticStreamWriter(new DdsDataOutput(TRACING_TOKEN, dos, controller))
                .withController(controller);
    }
}
