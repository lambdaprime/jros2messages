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

import id.jrosmessages.Message;
import id.jrosmessages.MessageMetadata;
import id.jrosmessages.std_msgs.StringMessage;
import id.xfunction.XJson;
import java.util.Objects;

/**
 * Definition for vision_msgs/VisionClass A key value pair that maps an integer class_id to a string
 * class label # in computer vision systems.
 */
@MessageMetadata(name = VisionClassMessage.NAME)
public class VisionClassMessage implements Message {

    static final String NAME = "vision_msgs/VisionClass";

    /**
     * The int value that identifies the class. Elements identified with 65535, the maximum uint16
     * value are assumed to belong to the "UNLABELED" class. For vision pipelines using less than
     * 255 classes the "UNLABELED" is the maximum value in the uint8 range.
     */
    public short class_id;

    /** The name of the class represented by the class_id */
    public StringMessage class_name = new StringMessage();

    public VisionClassMessage withClassId(short class_id) {
        this.class_id = class_id;
        return this;
    }

    public VisionClassMessage withClassName(StringMessage class_name) {
        this.class_name = class_name;
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(class_id, class_name);
    }

    @Override
    public boolean equals(Object obj) {
        var other = (VisionClassMessage) obj;
        return Objects.equals(class_id, other.class_id)
                && Objects.equals(class_name, other.class_name);
    }

    @Override
    public String toString() {
        return XJson.asString(
                "class_id", class_id,
                "class_name", class_name);
    }
}
