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
package id.jros2messages.std_msgs;

import id.jrosmessages.Message;
import id.jrosmessages.MessageMetadata;
import id.jrosmessages.primitives.Time;
import id.xfunction.XJson;
import java.util.Objects;

/** Definition for std_msgs/Header */
@MessageMetadata(name = "std_msgs/Header", md5sum = "2176decaecbce78abc3b96ef049fabed")
public class HeaderMessage implements Message {

    public Time stamp = new Time();

    public String frame_id = "";

    public HeaderMessage withStamp(Time stamp) {
        this.stamp = stamp;
        return this;
    }

    public HeaderMessage withFrameId(String frame_id) {
        this.frame_id = frame_id;
        return this;
    }

    @Override
    public String toString() {
        return XJson.asString("stamp", stamp, "frame_id", frame_id).toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(stamp, frame_id);
    }

    @Override
    public boolean equals(Object obj) {
        HeaderMessage other = (HeaderMessage) obj;
        return Objects.equals(stamp, other.stamp) && Objects.equals(frame_id, other.frame_id);
    }
}
