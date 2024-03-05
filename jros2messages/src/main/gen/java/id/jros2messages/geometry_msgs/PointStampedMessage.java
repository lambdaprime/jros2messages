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
import id.jrosmessages.geometry_msgs.PointMessage;
import id.xfunction.XJson;
import java.util.Objects;

/**
 * Definition for geometry_msgs/PointStamped
 *
 * <p>This represents a Point with reference coordinate frame and timestamp This represents a Point
 * with reference coordinate frame and timestamp
 */
@MessageMetadata(
        name = PointStampedMessage.NAME,
        fields = {"header", "point"})
public class PointStampedMessage implements Message {

    static final String NAME = "geometry_msgs/PointStamped";

    public HeaderMessage header = new HeaderMessage();

    public PointMessage point = new PointMessage();

    public PointStampedMessage withHeader(HeaderMessage header) {
        this.header = header;
        return this;
    }

    public PointStampedMessage withPoint(PointMessage point) {
        this.point = point;
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(header, point);
    }

    @Override
    public boolean equals(Object obj) {
        var other = (PointStampedMessage) obj;
        return Objects.equals(header, other.header) && Objects.equals(point, other.point);
    }

    @Override
    public String toString() {
        return XJson.asString(
                "header", header,
                "point", point);
    }
}
