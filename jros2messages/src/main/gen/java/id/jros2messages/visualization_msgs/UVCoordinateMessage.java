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
package id.jros2messages.visualization_msgs;

import id.jrosmessages.Message;
import id.jrosmessages.MessageMetadata;
import id.xfunction.XJson;
import java.util.Objects;

/**
 * Definition for visualization_msgs/UVCoordinate
 *
 * <p>Location of the pixel as a ratio of the width of a 2D texture. Values should be in range:
 * [0.0-1.0].
 */
@MessageMetadata(
        name = UVCoordinateMessage.NAME,
        fields = {"u", "v"})
public class UVCoordinateMessage implements Message {

    static final String NAME = "visualization_msgs/UVCoordinate";

    public float u;

    public float v;

    public UVCoordinateMessage withU(float u) {
        this.u = u;
        return this;
    }

    public UVCoordinateMessage withV(float v) {
        this.v = v;
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(u, v);
    }

    @Override
    public boolean equals(Object obj) {
        var other = (UVCoordinateMessage) obj;
        return u == other.u && v == other.v;
    }

    @Override
    public String toString() {
        return XJson.asString(
                "u", u,
                "v", v);
    }
}
