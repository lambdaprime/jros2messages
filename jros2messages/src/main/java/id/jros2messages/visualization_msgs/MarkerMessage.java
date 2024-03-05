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
package id.jros2messages.visualization_msgs;

import id.jros2messages.sensor_msgs.CompressedImageMessage;
import id.jros2messages.std_msgs.HeaderMessage;
import id.jrosmessages.Message;
import id.jrosmessages.MessageMetadata;
import id.jrosmessages.geometry_msgs.PointMessage;
import id.jrosmessages.geometry_msgs.PoseMessage;
import id.jrosmessages.geometry_msgs.Vector3Message;
import id.jrosmessages.primitives.Duration;
import id.jrosmessages.std_msgs.ColorRGBAMessage;
import id.jrosmessages.std_msgs.StringMessage;
import id.xfunction.XJson;
import java.util.Arrays;
import java.util.Objects;

/**
 * Definition for visualization_msgs/Marker
 *
 * <p>See: - http://www.ros.org/wiki/rviz/DisplayTypes/Marker -
 * http://www.ros.org/wiki/rviz/Tutorials/Markers%3A%20Basic%20Shapes
 *
 * <p>for more information on using this message with rviz.
 */
@MessageMetadata(
        name = MarkerMessage.NAME,
        fields = {
            "header",
            "ns",
            "id",
            "type",
            "action",
            "pose",
            "scale",
            "color",
            "lifetime",
            "frame_locked",
            "points",
            "colors",
            "texture_resource",
            "texture",
            "uv_coordinates",
            "text",
            "mesh_resource",
            "mesh_file",
            "mesh_use_embedded_materials"
        })
public class MarkerMessage implements Message {

    static final String NAME = "visualization_msgs/Marker";

    public enum Type {
        ARROW,
        CUBE,
        SPHERE,
        CYLINDER,
        LINE_STRIP,
        LINE_LIST,
        CUBE_LIST,
        SPHERE_LIST,
        POINTS,
        TEXT_VIEW_FACING,
        MESH_RESOURCE,
        TRIANGLE_LIST
    }

    public enum Action {
        ADD,
        MODIFY,
        DELETE,
        DELETEALL,
    }

    /** Header for time/frame information */
    public HeaderMessage header = new HeaderMessage();

    /**
     * Namespace to place this object in... used in conjunction with id to create a unique name for
     * the object
     */
    public StringMessage ns = new StringMessage();

    /**
     * Object ID useful in conjunction with the namespace for manipulating and deleting the object
     * later
     */
    public int id;

    /** Type of object */
    public int type;

    /** 0 add/modify an object, 1 (deprecated), 2 deletes an object, 3 deletes all objects */
    public int action;

    /** Pose of the object */
    public PoseMessage pose = new PoseMessage();

    /** Scale of the object 1,1,1 means default (usually 1 meter square) */
    public Vector3Message scale = new Vector3Message();

    /** Color [0.0-1.0] */
    public ColorRGBAMessage color = new ColorRGBAMessage();

    /** How long the object should last before being automatically deleted. 0 means forever */
    public Duration lifetime = new Duration();

    /** If this marker should be frame-locked, i.e. retransformed into its frame every timestep */
    public boolean frame_locked;

    /** Only used if the type specified has some use for them (eg. POINTS, LINE_STRIP, ...) */
    public PointMessage[] points = new PointMessage[0];

    /**
     * Only used if the type specified has some use for them (eg. POINTS, LINE_STRIP, ...) number of
     * colors must either be 0 or equal to the number of points NOTE: alpha is not yet used
     */
    public ColorRGBAMessage[] colors = new ColorRGBAMessage[0];

    /**
     * Texture resource is a special URI that can either reference a texture file in a format
     * acceptable to (resource retriever)[https://index.ros.org/p/resource_retriever/] or an
     * embedded texture via a string matching the format: "embedded://texture_name"
     */
    public StringMessage texture_resource = new StringMessage();

    /**
     * An image to be loaded into the rendering engine as the texture for this marker. This will be
     * used iff texture_resource is set to embedded.
     */
    public CompressedImageMessage texture = new CompressedImageMessage();

    /** Location of each vertex within the texture; in the range: [0.0-1.0] */
    public UVCoordinateMessage[] uv_coordinates = new UVCoordinateMessage[0];

    /** Only used for text markers */
    public StringMessage text = new StringMessage();

    /** Only used for MESH_RESOURCE markers */
    public StringMessage mesh_resource = new StringMessage();

    /**
     * Optionally, a mesh file can be sent in-message via the mesh_file field. If doing so, use the
     * following format for mesh_resource: "embedded://mesh_name"
     */
    public MeshFileMessage mesh_file = new MeshFileMessage();

    /** If this marker should be frame-locked, i.e. retransformed into its frame every timestep */
    public boolean mesh_use_embedded_materials;

    public MarkerMessage withHeader(HeaderMessage header) {
        this.header = header;
        return this;
    }

    public MarkerMessage withNs(StringMessage ns) {
        this.ns = ns;
        return this;
    }

    public MarkerMessage withId(int id) {
        this.id = id;
        return this;
    }

    public MarkerMessage withType(Type type) {
        this.type = type.ordinal();
        return this;
    }

    public MarkerMessage withAction(Action action) {
        this.action = action.ordinal();
        return this;
    }

    public MarkerMessage withPose(PoseMessage pose) {
        this.pose = pose;
        return this;
    }

    public MarkerMessage withScale(Vector3Message scale) {
        this.scale = scale;
        return this;
    }

    public MarkerMessage withColor(ColorRGBAMessage color) {
        this.color = color;
        return this;
    }

    public MarkerMessage withLifetime(Duration lifetime) {
        this.lifetime = lifetime;
        return this;
    }

    public MarkerMessage withFrameLocked(boolean frame_locked) {
        this.frame_locked = frame_locked;
        return this;
    }

    public MarkerMessage withText(StringMessage text) {
        this.text = text;
        return this;
    }

    public MarkerMessage withTextureResource(StringMessage texture_resource) {
        this.texture_resource = texture_resource;
        return this;
    }

    public MarkerMessage withTexture(CompressedImageMessage texture) {
        this.texture = texture;
        return this;
    }

    public MarkerMessage withUvCoordinates(UVCoordinateMessage[] uv_coordinates) {
        this.uv_coordinates = uv_coordinates;
        return this;
    }

    public MarkerMessage withMeshUseEmbeddedMaterials(boolean mesh_use_embedded_materials) {
        this.mesh_use_embedded_materials = mesh_use_embedded_materials;
        return this;
    }

    public MarkerMessage withMeshFile(MeshFileMessage mesh_file) {
        this.mesh_file = mesh_file;
        return this;
    }

    @Override
    public String toString() {
        return XJson.asString(
                "header", header,
                "ns", ns,
                "id", id,
                "type", type,
                "action", action,
                "pose", pose,
                "scale", scale,
                "color", color,
                "lifetime", lifetime,
                "frame_locked", frame_locked,
                "points", Arrays.toString(points),
                "colors", Arrays.toString(colors),
                "uv_coordinates", Arrays.toString(uv_coordinates),
                "texture_resource", texture_resource,
                "texture", texture,
                "mesh_resource", mesh_resource,
                "mesh_file", mesh_file,
                "text", text,
                "mesh_use_embedded_materials", mesh_use_embedded_materials);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                header,
                ns,
                id,
                type,
                action,
                pose,
                scale,
                color,
                lifetime,
                frame_locked,
                Arrays.hashCode(points),
                Arrays.hashCode(colors),
                Arrays.hashCode(uv_coordinates),
                texture_resource,
                texture,
                mesh_resource,
                mesh_file,
                text,
                mesh_use_embedded_materials);
    }

    @Override
    public boolean equals(Object obj) {
        MarkerMessage other = (MarkerMessage) obj;
        return Objects.equals(header, other.header)
                && Objects.equals(ns, other.ns)
                && Objects.equals(id, other.id)
                && Objects.equals(type, other.type)
                && Objects.equals(action, other.action)
                && Objects.equals(pose, other.pose)
                && Objects.equals(scale, other.scale)
                && Objects.equals(color, other.color)
                && Objects.equals(lifetime, other.lifetime)
                && Objects.equals(frame_locked, other.frame_locked)
                && Arrays.equals(points, other.points)
                && Arrays.equals(colors, other.colors)
                && Arrays.equals(uv_coordinates, other.uv_coordinates)
                && Objects.equals(texture_resource, other.texture_resource)
                && Objects.equals(texture, other.texture)
                && Objects.equals(mesh_resource, other.mesh_resource)
                && Objects.equals(mesh_file, other.mesh_file)
                && Objects.equals(text, other.text)
                && mesh_use_embedded_materials == other.mesh_use_embedded_materials;
    }
}
