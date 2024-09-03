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
package id.jros2messages.tests;

import id.jros2messages.Ros2MessageSerializationUtils;
import id.jros2messages.geometry_msgs.PolygonStampedMessage;
import id.jros2messages.sensor_msgs.JointStateMessage;
import id.jros2messages.sensor_msgs.JoyMessage;
import id.jros2messages.sensor_msgs.MultiDOFJointStateMessage;
import id.jros2messages.sensor_msgs.PointCloud2Message;
import id.jros2messages.std_msgs.HeaderMessage;
import id.jros2messages.trajectory_msgs.JointTrajectoryMessage;
import id.jros2messages.unique_identifier_msgs.UUIDMessage;
import id.jros2messages.vision_msgs.ObjectHypothesisWithPoseMessage;
import id.jros2messages.visualization_msgs.MarkerArrayMessage;
import id.jros2messages.visualization_msgs.MarkerMessage;
import id.jrosmessages.geometry_msgs.Point32Message;
import id.jrosmessages.geometry_msgs.PointMessage;
import id.jrosmessages.geometry_msgs.PolygonMessage;
import id.jrosmessages.geometry_msgs.PoseMessage;
import id.jrosmessages.geometry_msgs.PoseWithCovarianceMessage;
import id.jrosmessages.geometry_msgs.QuaternionMessage;
import id.jrosmessages.geometry_msgs.TransformMessage;
import id.jrosmessages.geometry_msgs.TwistMessage;
import id.jrosmessages.geometry_msgs.Vector3Message;
import id.jrosmessages.geometry_msgs.WrenchMessage;
import id.jrosmessages.primitives.Duration;
import id.jrosmessages.primitives.Time;
import id.jrosmessages.sensor_msgs.PointFieldMessage;
import id.jrosmessages.sensor_msgs.PointFieldMessage.DataType;
import id.jrosmessages.std_msgs.ColorRGBAMessage;
import id.jrosmessages.std_msgs.EmptyMessage;
import id.jrosmessages.std_msgs.StringMessage;
import id.jrosmessages.tests.MessageTests;
import id.jrosmessages.tests.msgs.TestMessage;
import id.jrosmessages.trajectory_msgs.JointTrajectoryPointMessage;
import java.util.UUID;
import java.util.stream.Stream;

/**
 * @author lambdaprime intid@protonmail.com
 */
public class JRos2MessageTests extends MessageTests {

    public JRos2MessageTests() {
        super(new Ros2MessageSerializationUtils());
    }

    static Stream<TestCase> dataProvider() {
        return Stream.of(
                new TestCase(
                        "Test",
                        new TestMessage()
                                .withData(123)
                                .withBoolArray(new boolean[] {true, false})
                                .withBoolFixedArray(new boolean[] {true, true, true})),
                new TestCase("string-empty-ros2", new StringMessage()),
                // point-empty
                new TestCase("point-empty", new PointMessage()),
                new TestCase("point", new PointMessage().withX(1.0).withY(1.0).withZ(1.0)),
                new TestCase("point32", new Point32Message().withX(1.0F).withY(1.0F).withZ(1.0F)),
                new TestCase("quaternion-empty", new QuaternionMessage()),
                new TestCase(
                        "quaternion",
                        new QuaternionMessage().withX(1.0).withY(1.0).withZ(1.0).withW(3.0)),
                new TestCase("pose-empty", new PoseMessage()),
                new TestCase(
                        "pose",
                        new PoseMessage()
                                .withPosition(new PointMessage().withX(1.0).withY(1.0).withZ(1.0))
                                .withQuaternion(
                                        new QuaternionMessage()
                                                .withX(1.0)
                                                .withY(1.0)
                                                .withZ(1.0)
                                                .withW(3.0))),
                new TestCase("colorrgba-empty", new ColorRGBAMessage()),
                new TestCase(
                        "colorrgba",
                        new ColorRGBAMessage().withR(.12F).withG(.13F).withB(.14F).withA(.15F)),
                new TestCase("vector3-empty", new Vector3Message()),
                new TestCase("vector3", new Vector3Message().withX(.12).withY(.13).withZ(.14)),
                new TestCase(
                        "polygonstamped",
                        new PolygonStampedMessage()
                                .withHeader(
                                        new HeaderMessage()
                                                .withStamp(new Time(0, 1111))
                                                .withFrameId("aaaa"))
                                .withPolygon(
                                        new PolygonMessage()
                                                .withPoints(
                                                        new Point32Message[] {
                                                            new Point32Message(2F, 2F, 0F),
                                                            new Point32Message(1F, 2F, 3F),
                                                            new Point32Message(0F, 0F, 0F)
                                                        }))),
                new TestCase("header-empty", new HeaderMessage()),
                new TestCase(
                        "header",
                        new HeaderMessage().withStamp(new Time(0, 1111)).withFrameId("aaaa")),
                new TestCase("marker-empty", new MarkerMessage()),
                new TestCase(
                        "marker-array",
                        new MarkerArrayMessage()
                                .withMarkers(
                                        new MarkerMessage()
                                                .withHeader(new HeaderMessage().withFrameId("/map"))
                                                .withNs(
                                                        new StringMessage()
                                                                .withData("basic_shapes"))
                                                .withType(MarkerMessage.Type.CUBE)
                                                .withAction(MarkerMessage.Action.ADD)
                                                .withPose(
                                                        new PoseMessage()
                                                                .withPosition(
                                                                        new PointMessage()
                                                                                .withX(1.0)
                                                                                .withY(0.0)
                                                                                .withZ(2.0))
                                                                .withQuaternion(
                                                                        new QuaternionMessage()
                                                                                .withX(0.0)
                                                                                .withY(0.0)
                                                                                .withZ(0.0)
                                                                                .withW(1.0)))
                                                .withScale(
                                                        new Vector3Message()
                                                                .withX(0.05)
                                                                .withY(0.05)
                                                                .withZ(0.05))
                                                .withColor(
                                                        new ColorRGBAMessage()
                                                                .withR(0.8F)
                                                                .withG(0.1F)
                                                                .withB(0.1F)
                                                                .withA(1.0F))
                                                .withText(new StringMessage().withData("aa"))
                                                .withLifetime(new Duration())
                                                .withFrameLocked(true)
                                                .withMeshUseEmbeddedMaterials(true))),
                new TestCase(
                        "pointcloud2",
                        new PointCloud2Message()
                                .withHeader(
                                        new HeaderMessage()
                                                .withFrameId("map")
                                                .withStamp(new Time(1616650098, 493819000)))
                                .withHeight(1)
                                .withIsDense(true)
                                .withPointStep(12)
                                .withFields(
                                        new PointFieldMessage()
                                                .withName("x")
                                                .withOffset(0)
                                                .withCount(1)
                                                .withDataType(DataType.FLOAT64),
                                        new PointFieldMessage()
                                                .withName("y")
                                                .withOffset(4)
                                                .withCount(1)
                                                .withDataType(DataType.FLOAT64),
                                        new PointFieldMessage()
                                                .withName("z")
                                                .withOffset(8)
                                                .withCount(1)
                                                .withDataType(DataType.FLOAT64))
                                .withData("a".repeat(96).getBytes())
                                .withRowStep(96)
                                .withWidth(8)),
                new TestCase(
                        "joint-state",
                        new JointStateMessage()
                                .withHeader(
                                        new HeaderMessage()
                                                .withStamp(new Time(1621056685, 970860000)))
                                .withNames("joint_0", "joint_1", "joint_2", "joint_3", "joint_4")
                                .withPositions(
                                        new double[] {0.0, 0.0, 0.0, 0.767944870877505, 0.0})),
                new TestCase("uuid", new UUIDMessage(new UUID(0x10101020, 0x30101040))),
                new TestCase(
                        "joy",
                        new JoyMessage()
                                .withHeader(
                                        new HeaderMessage()
                                                .withStamp(new Time(1621056685, 970860000)))
                                .withAxes(3.3F, 3.2F, 3.1F)
                                .withButtons(5, 6, 7)),
                new TestCase(
                        "obj_hypothesis",
                        new ObjectHypothesisWithPoseMessage()
                                .withPose(
                                        new PoseWithCovarianceMessage()
                                                .withCovariance(
                                                        1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13,
                                                        14, 15.56, 16, 17, 18, 19, 20, 21, 22, 23,
                                                        24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34,
                                                        35, 36))),
                /*
                 *
                ros2 topic pub -r 10 helloRos "sensor_msgs/MultiDOFJointState" '
                header:
                  stamp: {sec: 123}
                  frame_id: "aaa"
                joint_names: ["joint1", "joint2"]
                transforms:
                  - translation: {x: 1, y: 2, z: 3}
                    rotation: {x: 4,y: 5,z: 6,w: 7}
                  - translation: {x: 8, y: 9, z: 10}
                    rotation: {x: 11,y: 12,z: 13,w: 14}
                twist:
                  - linear: {x: 11, y: 12, z: 13}
                    angular: {x: 14, y: 15, z: 16}
                  - linear: {x: 18, y: 19, z: 110}
                    angular: {x: 111, y: 112, z: 113}
                wrench:
                  - force: {x: 11, y: 12, z: 13}
                    torque: {x: 14, y: 15, z: 16}
                  - force: {x: 18, y: 19, z: 110}
                    torque: {x: 111, y: 112, z: 113}
                '
                *
                */
                new TestCase(
                        "MultiDOFJointState",
                        new MultiDOFJointStateMessage()
                                .withHeader(new HeaderMessage().withFrameId("aaa"))
                                .withJointNames(
                                        new StringMessage("joint1"), new StringMessage("joint2"))
                                .withTransforms(
                                        new TransformMessage()
                                                .withTranslation(new Vector3Message(1, 2, 3))
                                                .withRotation(
                                                        new QuaternionMessage(4., 5., 6., 7.)),
                                        new TransformMessage()
                                                .withTranslation(new Vector3Message(8, 9, 10))
                                                .withRotation(
                                                        new QuaternionMessage(11, 12, 13, 14)))
                                .withTwist(
                                        new TwistMessage()
                                                .withLinear(new Vector3Message(11, 12, 13))
                                                .withAngular(new Vector3Message(14, 15, 16)),
                                        new TwistMessage()
                                                .withLinear(new Vector3Message(18, 19, 110))
                                                .withAngular(new Vector3Message(111, 112, 113)))
                                .withWrench(
                                        new WrenchMessage()
                                                .withForce(new Vector3Message(11, 12, 13))
                                                .withTorque(new Vector3Message(14, 15, 16)),
                                        new WrenchMessage()
                                                .withForce(new Vector3Message(18, 19, 110))
                                                .withTorque(new Vector3Message(111, 112, 113)))),
                /*
                 *
                 ros2 topic pub -r 10 helloRos "trajectory_msgs/JointTrajectory" '
                 header:
                   stamp: {sec: 123}
                   frame_id: "aaa"
                 joint_names: ["joint1", "joint2"]
                 points:
                   - positions: [1,2,3]
                     velocities: [4,5,6,7]
                     accelerations: [8,9,10]
                     effort: [11,12,13,14]
                     time_from_start:  {sec: 333}
                   - positions: [11,12,13]
                     velocities: [14,15,16,17]
                     accelerations: [18,19,10]
                     effort: [1,2,3,4]
                     time_from_start:  {sec: 54}
                 '
                *
                */
                new TestCase(
                        "JointTrajectory",
                        new JointTrajectoryMessage()
                                .withHeader(
                                        new HeaderMessage()
                                                .withStamp(new Time(123, 0))
                                                .withFrameId("aaa"))
                                .withJointNames(
                                        new StringMessage("joint1"), new StringMessage("joint2"))
                                .withPoints(
                                        new JointTrajectoryPointMessage()
                                                .withPositions(1, 2, 3)
                                                .withVelocities(4, 5, 6, 7)
                                                .withAccelerations(8, 9, 10)
                                                .withEffort(11, 12, 13, 14)
                                                .withTimeFromStart(new Duration(333)),
                                        new JointTrajectoryPointMessage()
                                                .withPositions(11, 12, 13)
                                                .withVelocities(14, 15, 16, 17)
                                                .withAccelerations(18, 19, 10)
                                                .withEffort(1, 2, 3, 4)
                                                .withTimeFromStart(new Duration(54)))),
                /** ros2 topic pub -r 10 helloRos "std_msgs/Empty" '{}' */
                new TestCase("empty", new EmptyMessage()));
    }
}
