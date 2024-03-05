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
import id.jrosmessages.std_msgs.StringMessage;
import id.xfunction.XJson;
import java.util.Arrays;
import java.util.Objects;

/**
 * Definition for vision_msgs/Detection2D
 *
 * <p>Defines a 2D detection result.
 *
 * <p>This is similar to a 2D classification, but includes position information, allowing a
 * classification result for a specific crop or image point to to be located in the larger image.
 */
@MessageMetadata(
        name = Detection2DMessage.NAME,
        fields = {"header", "results", "bbox", "id"})
public class Detection2DMessage implements Message {

    static final String NAME = "vision_msgs/Detection2D";

    public HeaderMessage header = new HeaderMessage();

    /** Class probabilities */
    public ObjectHypothesisWithPoseMessage[] results = new ObjectHypothesisWithPoseMessage[0];

    /** 2D bounding box surrounding the object. */
    public BoundingBox2DMessage bbox = new BoundingBox2DMessage();

    /**
     * ID used for consistency across multiple detection messages. Detections of the same object in
     * different detection messages should have the same id. This field may be empty.
     */
    public StringMessage id = new StringMessage();

    public Detection2DMessage withHeader(HeaderMessage header) {
        this.header = header;
        return this;
    }

    public Detection2DMessage withResults(ObjectHypothesisWithPoseMessage... results) {
        this.results = results;
        return this;
    }

    public Detection2DMessage withBbox(BoundingBox2DMessage bbox) {
        this.bbox = bbox;
        return this;
    }

    public Detection2DMessage withId(StringMessage id) {
        this.id = id;
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(header, Arrays.hashCode(results), bbox, id);
    }

    @Override
    public boolean equals(Object obj) {
        var other = (Detection2DMessage) obj;
        return Objects.equals(header, other.header)
                && Arrays.equals(results, other.results)
                && Objects.equals(bbox, other.bbox)
                && Objects.equals(id, other.id);
    }

    @Override
    public String toString() {
        return XJson.asString(
                "header", header,
                "results", results,
                "bbox", bbox,
                "id", id);
    }
}
