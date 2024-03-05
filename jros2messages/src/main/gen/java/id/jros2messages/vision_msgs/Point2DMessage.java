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
import id.xfunction.XJson;
import java.util.Objects;

/**
 * Definition for vision_msgs/Point2D
 *
 * <p>Represents a 2D point in pixel coordinates. XY matches the sensor_msgs/Image convention: X is
 * positive right and Y is positive down. Represents a 2D point in pixel coordinates. XY matches the
 * sensor_msgs/Image convention: X is positive right and Y is positive down.
 */
@MessageMetadata(
        name = Point2DMessage.NAME,
        fields = {"x", "y"})
public class Point2DMessage implements Message {

    static final String NAME = "vision_msgs/Point2D";

    public double x;

    public double y;

    public Point2DMessage withX(double x) {
        this.x = x;
        return this;
    }

    public Point2DMessage withY(double y) {
        this.y = y;
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public boolean equals(Object obj) {
        var other = (Point2DMessage) obj;
        return x == other.x && y == other.y;
    }

    @Override
    public String toString() {
        return XJson.asString(
                "x", x,
                "y", y);
    }
}
