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
 * Definition for vision_msgs/Pose2D Represents a 2D pose (coordinates and a radian rotation).
 * Rotation is positive counterclockwise.
 */
@MessageMetadata(name = Pose2DMessage.NAME)
public class Pose2DMessage implements Message {

    static final String NAME = "vision_msgs/Pose2D";

    public Point2DMessage position = new Point2DMessage();

    public double theta;

    public Pose2DMessage withPosition(Point2DMessage position) {
        this.position = position;
        return this;
    }

    public Pose2DMessage withTheta(double theta) {
        this.theta = theta;
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, theta);
    }

    @Override
    public boolean equals(Object obj) {
        var other = (Pose2DMessage) obj;
        return Objects.equals(position, other.position) && theta == other.theta;
    }

    @Override
    public String toString() {
        return XJson.asString(
                "position", position,
                "theta", theta);
    }
}
