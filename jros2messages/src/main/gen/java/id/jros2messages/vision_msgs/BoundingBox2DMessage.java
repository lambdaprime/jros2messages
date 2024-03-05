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
 * Definition for vision_msgs/BoundingBox2D
 *
 * <p>A 2D bounding box that can be rotated about its center. All dimensions are in pixels, but
 * represented using floating-point values to allow sub-pixel precision. If an exact pixel crop is
 * required for a rotated bounding box, it can be calculated using Bresenham's line algorithm.
 */
@MessageMetadata(
        name = BoundingBox2DMessage.NAME,
        fields = {"center", "size_x", "size_y"})
public class BoundingBox2DMessage implements Message {

    static final String NAME = "vision_msgs/BoundingBox2D";

    /** The 2D position (in pixels) and orientation of the bounding box center. */
    public Pose2DMessage center = new Pose2DMessage();

    /**
     * The total size (in pixels) of the bounding box surrounding the object relative to the pose of
     * its center.
     */
    public double size_x;

    public double size_y;

    public BoundingBox2DMessage withCenter(Pose2DMessage center) {
        this.center = center;
        return this;
    }

    public BoundingBox2DMessage withSizeX(double size_x) {
        this.size_x = size_x;
        return this;
    }

    public BoundingBox2DMessage withSizeY(double size_y) {
        this.size_y = size_y;
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(center, size_x, size_y);
    }

    @Override
    public boolean equals(Object obj) {
        var other = (BoundingBox2DMessage) obj;
        return Objects.equals(center, other.center)
                && size_x == other.size_x
                && size_y == other.size_y;
    }

    @Override
    public String toString() {
        return XJson.asString(
                "center", center,
                "size_x", size_x,
                "size_y", size_y);
    }
}
