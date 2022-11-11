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
package id.jros2messages.geometry_msgs;

import id.jros2messages.std_msgs.HeaderMessage;
import id.jrosmessages.Message;
import id.jrosmessages.MessageMetadata;
import id.jrosmessages.geometry_msgs.TransformMessage;
import id.jrosmessages.std_msgs.StringMessage;
import id.xfunction.XJson;
import java.util.Objects;

/**
 * Definition for geometry_msgs/TransformStamped This expresses a transform from coordinate frame
 * header.frame_id # to the coordinate frame child_frame_id # # This message is mostly used by the #
 * <a href="http://wiki.ros.org/tf">tf</a> package. # See its documentation for more information.
 */
@MessageMetadata(name = TransformStampedMessage.NAME)
public class TransformStampedMessage implements Message {

    static final String NAME = "geometry_msgs/TransformStamped";

    public HeaderMessage header = new HeaderMessage();

    /** the frame id of the child frame */
    public StringMessage child_frame_id = new StringMessage();

    public TransformMessage transform = new TransformMessage();

    public TransformStampedMessage withHeader(HeaderMessage header) {
        this.header = header;
        return this;
    }

    public TransformStampedMessage withChildFrameId(StringMessage child_frame_id) {
        this.child_frame_id = child_frame_id;
        return this;
    }

    public TransformStampedMessage withTransform(TransformMessage transform) {
        this.transform = transform;
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(header, child_frame_id, transform);
    }

    @Override
    public boolean equals(Object obj) {
        var other = (TransformStampedMessage) obj;
        return Objects.equals(header, other.header)
                && Objects.equals(child_frame_id, other.child_frame_id)
                && Objects.equals(transform, other.transform);
    }

    @Override
    public String toString() {
        return XJson.asString(
                "header", header,
                "child_frame_id", child_frame_id,
                "transform", transform);
    }
}
