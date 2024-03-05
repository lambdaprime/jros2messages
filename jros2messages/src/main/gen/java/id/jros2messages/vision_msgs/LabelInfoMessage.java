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
 * Definition for vision_msgs/LabelInfo
 *
 * <p>Provides meta-information about a visual pipeline.
 *
 * <p>This message serves a similar purpose to sensor_msgs/CameraInfo, but instead of being tied to
 * hardware, it represents information about a specific computer vision pipeline. This information
 * stays constant (or relatively constant) over time, and so it is wasteful to send it with each
 * individual result. By listening to these messages, subscribers will receive the context in which
 * published vision messages are to be interpreted. Each vision pipeline should publish its
 * LabelInfo messages to its own topic, in a manner similar to CameraInfo. This message is meant to
 * allow converting data from vision pipelines that return id based classifications back to human
 * readable string class names.
 */
@MessageMetadata(
        name = LabelInfoMessage.NAME,
        fields = {"header", "class_map", "threshold"})
public class LabelInfoMessage implements Message {

    static final String NAME = "vision_msgs/LabelInfo";

    /** Used for sequencing */
    public HeaderMessage header = new HeaderMessage();

    /**
     * An array of uint16 keys and string values containing the association between class
     * identifiers and their names. According to the amount of classes and the datatype used to
     * store their ids internally, the maxiumum class id allowed (65535 for uint16 and 255 for
     * uint8) belongs to the "UNLABELED" class.
     */
    public VisionClassMessage[] class_map = new VisionClassMessage[0];

    /** The value between 0-1 used as confidence threshold for the inference. */
    public float threshold;

    public LabelInfoMessage withHeader(HeaderMessage header) {
        this.header = header;
        return this;
    }

    public LabelInfoMessage withClassMap(VisionClassMessage... class_map) {
        this.class_map = class_map;
        return this;
    }

    public LabelInfoMessage withThreshold(float threshold) {
        this.threshold = threshold;
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(header, Arrays.hashCode(class_map), threshold);
    }

    @Override
    public boolean equals(Object obj) {
        var other = (LabelInfoMessage) obj;
        return Objects.equals(header, other.header)
                && Arrays.equals(class_map, other.class_map)
                && threshold == other.threshold;
    }

    @Override
    public String toString() {
        return XJson.asString(
                "header", header,
                "class_map", class_map,
                "threshold", threshold);
    }
}
