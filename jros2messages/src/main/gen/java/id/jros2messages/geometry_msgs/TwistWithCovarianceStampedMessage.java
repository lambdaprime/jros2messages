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
import id.jrosmessages.geometry_msgs.TwistWithCovarianceMessage;
import id.kineticstreamer.annotations.Streamed;
import id.xfunction.XJson;
import java.util.Objects;

/**
 * Definition for geometry_msgs/TwistWithCovarianceStamped This represents an estimated twist with
 * reference coordinate frame and timestamp.
 */
@MessageMetadata(
        type = TwistWithCovarianceStampedMessage.NAME,
        md5sum = "f59b87b044187f26ef66329003c3d275")
public class TwistWithCovarianceStampedMessage implements Message {

    static final String NAME = "geometry_msgs/TwistWithCovarianceStamped";

    @Streamed public HeaderMessage header = new HeaderMessage();

    @Streamed public TwistWithCovarianceMessage twist = new TwistWithCovarianceMessage();

    public TwistWithCovarianceStampedMessage withHeader(HeaderMessage header) {
        this.header = header;
        return this;
    }

    public TwistWithCovarianceStampedMessage withTwist(TwistWithCovarianceMessage twist) {
        this.twist = twist;
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(header, twist);
    }

    @Override
    public boolean equals(Object obj) {
        var other = (TwistWithCovarianceStampedMessage) obj;
        return Objects.equals(header, other.header) && Objects.equals(twist, other.twist);
    }

    @Override
    public String toString() {
        return XJson.asString(
                "header", header,
                "twist", twist);
    }
}
