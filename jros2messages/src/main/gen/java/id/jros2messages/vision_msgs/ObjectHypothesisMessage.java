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
 * Definition for vision_msgs/ObjectHypothesis An object hypothesis that contains no pose
 * information. # If you would like to define an array of ObjectHypothesis messages, # please see
 * the Classification message type.
 */
@MessageMetadata(name = ObjectHypothesisMessage.NAME)
public class ObjectHypothesisMessage implements Message {

    static final String NAME = "vision_msgs/ObjectHypothesis";

    /**
     * The unique ID of the object class. To get additional information about this ID, such as its
     * human-readable class name, listeners should perform a lookup in a metadata database. See
     * vision_msgs/VisionInfo.msg for more detail.
     */
    public StringMessage class_id = new StringMessage();

    /**
     * The probability or confidence value of the detected object. By convention, this value should
     * lie in the range [0-1].
     */
    public double score;

    public ObjectHypothesisMessage withClassId(StringMessage class_id) {
        this.class_id = class_id;
        return this;
    }

    public ObjectHypothesisMessage withScore(double score) {
        this.score = score;
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(class_id, score);
    }

    @Override
    public boolean equals(Object obj) {
        var other = (ObjectHypothesisMessage) obj;
        return Objects.equals(class_id, other.class_id) && score == other.score;
    }

    @Override
    public String toString() {
        return XJson.asString(
                "class_id", class_id,
                "score", score);
    }
}
