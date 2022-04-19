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
import id.jrosmessages.geometry_msgs.PoseMessage;
import id.kineticstreamer.annotations.Streamed;
import id.xfunction.XJson;
import java.util.Objects;

/** Definition for geometry_msgs/PoseStamped A Pose with reference coordinate frame and timestamp */
@MessageMetadata(type = PoseStampedMessage.NAME, md5sum = "43f3760283d49766c9fdd69c4461f732")
public class PoseStampedMessage implements Message {

    static final String NAME = "geometry_msgs/PoseStamped";

    @Streamed public HeaderMessage header = new HeaderMessage();

    @Streamed public PoseMessage pose = new PoseMessage();

    public PoseStampedMessage withHeader(HeaderMessage header) {
        this.header = header;
        return this;
    }

    public PoseStampedMessage withPose(PoseMessage pose) {
        this.pose = pose;
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(header, pose);
    }

    @Override
    public boolean equals(Object obj) {
        var other = (PoseStampedMessage) obj;
        return Objects.equals(header, other.header) && Objects.equals(pose, other.pose);
    }

    @Override
    public String toString() {
        return XJson.asString(
                "header", header,
                "pose", pose);
    }
}
