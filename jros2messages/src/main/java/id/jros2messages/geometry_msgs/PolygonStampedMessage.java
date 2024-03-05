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
package id.jros2messages.geometry_msgs;

import id.jros2messages.std_msgs.HeaderMessage;
import id.jrosmessages.Message;
import id.jrosmessages.MessageMetadata;
import id.jrosmessages.geometry_msgs.PolygonMessage;
import id.xfunction.XJson;
import java.util.Objects;

/**
 * Definition for geometry_msgs/PolygonStamped
 *
 * <p>This represents a Polygon with reference coordinate frame and timestamp This represents a
 * Polygon with reference coordinate frame and timestamp
 */
@MessageMetadata(
        name = PolygonStampedMessage.NAME,
        fields = {"header", "polygon"})
public class PolygonStampedMessage implements Message {

    static final String NAME = "geometry_msgs/PolygonStamped";

    public HeaderMessage header = new HeaderMessage();

    public PolygonMessage polygon = new PolygonMessage();

    public PolygonStampedMessage withPolygon(PolygonMessage polygon) {
        this.polygon = polygon;
        return this;
    }

    public PolygonStampedMessage withHeader(HeaderMessage header) {
        this.header = header;
        return this;
    }

    @Override
    public String toString() {
        return XJson.asString("header", header, "polygon", polygon);
    }

    @Override
    public int hashCode() {
        return Objects.hash(header, polygon);
    }

    @Override
    public boolean equals(Object obj) {
        PolygonStampedMessage other = (PolygonStampedMessage) obj;
        return Objects.equals(header, other.header) && Objects.equals(polygon, other.polygon);
    }
}
