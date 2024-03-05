/*
 * Copyright 2021 jrosclient project
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
package id.jros2messages.vision_msgs;

import id.jros2messages.std_msgs.HeaderMessage;
import id.jrosmessages.Message;
import id.jrosmessages.MessageMetadata;
import id.xfunction.XJson;
import java.util.Arrays;
import java.util.Objects;

/**
 * Definition for vision_msgs/Detection3DArray
 *
 * <p>A list of 3D detections, for a multi-object 3D detector.
 */
@MessageMetadata(
        name = Detection3DArrayMessage.NAME,
        fields = {"header", "detections"})
public class Detection3DArrayMessage implements Message {

    static final String NAME = "vision_msgs/Detection3DArray";

    public HeaderMessage header = new HeaderMessage();

    /**
     * A list of the detected proposals. A multi-proposal detector might generate this list with
     * many candidate detections generated from a single input.
     */
    public Detection3DMessage[] detections = new Detection3DMessage[0];

    public Detection3DArrayMessage withHeader(HeaderMessage header) {
        this.header = header;
        return this;
    }

    public Detection3DArrayMessage withDetections(Detection3DMessage... detections) {
        this.detections = detections;
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(header, Arrays.hashCode(detections));
    }

    @Override
    public boolean equals(Object obj) {
        var other = (Detection3DArrayMessage) obj;
        return Objects.equals(header, other.header) && Arrays.equals(detections, other.detections);
    }

    @Override
    public String toString() {
        return XJson.asString(
                "header", header,
                "detections", detections);
    }
}
