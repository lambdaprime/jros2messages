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
import id.jrosmessages.vision_msgs.BoundingBox3DMessage;
import id.xfunction.XJson;
import java.util.Arrays;
import java.util.Objects;

/** Definition for vision_msgs/BoundingBox3DArray */
@MessageMetadata(
        name = BoundingBox3DArrayMessage.NAME,
        fields = {"header", "boxes"})
public class BoundingBox3DArrayMessage implements Message {

    static final String NAME = "vision_msgs/BoundingBox3DArray";

    public HeaderMessage header = new HeaderMessage();

    public BoundingBox3DMessage[] boxes = new BoundingBox3DMessage[0];

    public BoundingBox3DArrayMessage withHeader(HeaderMessage header) {
        this.header = header;
        return this;
    }

    public BoundingBox3DArrayMessage withBoxes(BoundingBox3DMessage... boxes) {
        this.boxes = boxes;
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(header, Arrays.hashCode(boxes));
    }

    @Override
    public boolean equals(Object obj) {
        var other = (BoundingBox3DArrayMessage) obj;
        return Objects.equals(header, other.header) && Arrays.equals(boxes, other.boxes);
    }

    @Override
    public String toString() {
        return XJson.asString(
                "header", header,
                "boxes", boxes);
    }
}
