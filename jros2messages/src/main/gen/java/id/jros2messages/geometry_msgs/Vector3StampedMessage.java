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
import id.jrosmessages.geometry_msgs.Vector3Message;
import id.xfunction.XJson;
import java.util.Objects;

/**
 * Definition for geometry_msgs/Vector3Stamped This represents a Vector3 with reference coordinate
 * frame and timestamp
 */
@MessageMetadata(name = Vector3StampedMessage.NAME, md5sum = "25a0f208694a205ef85c426c089ebf78")
public class Vector3StampedMessage implements Message {

    static final String NAME = "geometry_msgs/Vector3Stamped";

    public HeaderMessage header = new HeaderMessage();

    public Vector3Message vector = new Vector3Message();

    public Vector3StampedMessage withHeader(HeaderMessage header) {
        this.header = header;
        return this;
    }

    public Vector3StampedMessage withVector(Vector3Message vector) {
        this.vector = vector;
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(header, vector);
    }

    @Override
    public boolean equals(Object obj) {
        var other = (Vector3StampedMessage) obj;
        return Objects.equals(header, other.header) && Objects.equals(vector, other.vector);
    }

    @Override
    public String toString() {
        return XJson.asString(
                "header", header,
                "vector", vector);
    }
}
