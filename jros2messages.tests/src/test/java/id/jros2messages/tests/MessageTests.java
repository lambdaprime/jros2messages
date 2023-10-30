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

import static java.util.stream.Collectors.joining;
import static org.junit.jupiter.api.Assertions.assertEquals;

import id.jros2messages.MessageSerializationUtils;
import id.jros2messages.geometry_msgs.PolygonStampedMessage;
import id.jros2messages.sensor_msgs.JointStateMessage;
import id.jros2messages.sensor_msgs.JoyMessage;
import id.jros2messages.sensor_msgs.PointCloud2Message;
import id.jros2messages.std_msgs.HeaderMessage;
import id.jros2messages.unique_identifier_msgs.UUIDMessage;
import id.jros2messages.visualization_msgs.MarkerArrayMessage;
import id.jros2messages.visualization_msgs.MarkerMessage;
import id.jrosmessages.Message;
import id.jrosmessages.geometry_msgs.Point32Message;
import id.jrosmessages.geometry_msgs.PointMessage;
import id.jrosmessages.geometry_msgs.PolygonMessage;
import id.jrosmessages.geometry_msgs.PoseMessage;
import id.jrosmessages.geometry_msgs.QuaternionMessage;
import id.jrosmessages.geometry_msgs.Vector3Message;
import id.jrosmessages.primitives.Duration;
import id.jrosmessages.primitives.Time;
import id.jrosmessages.sensor_msgs.PointFieldMessage;
import id.jrosmessages.sensor_msgs.PointFieldMessage.DataType;
import id.jrosmessages.std_msgs.ColorRGBAMessage;
import id.jrosmessages.std_msgs.StringMessage;
import id.xfunction.ResourceUtils;
import id.xfunction.XByte;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class MessageTests {
    private static final ResourceUtils resourceUtils = new ResourceUtils();
    private MessageSerializationUtils serializationUtils = new MessageSerializationUtils();

    static Stream<List> dataProvider() {
        return Stream.of(
                // 1
                List.of(readResource("string-ros2"), new StringMessage("Hello World: 2767")),
                // 2
                List.of(readResource("string-empty-ros2"), new StringMessage()),
                List.of(readResource("point-empty"), new PointMessage()),
                List.of(readResource("point"), new PointMessage().withX(1.0).withY(1.0).withZ(1.0)),
                // 5
                List.of(
                        readResource("point32"),
                        new Point32Message().withX(1.0F).withY(1.0F).withZ(1.0F)),
                List.of(readResource("quaternion-empty"), new QuaternionMessage()),
                List.of(
                        readResource("quaternion"),
                        new QuaternionMessage().withX(1.0).withY(1.0).withZ(1.0).withW(3.0)),
                List.of(readResource("pose-empty"), new PoseMessage()),
                List.of(
                        readResource("pose"),
                        new PoseMessage()
                                .withPosition(new PointMessage().withX(1.0).withY(1.0).withZ(1.0))
                                .withQuaternion(
                                        new QuaternionMessage()
                                                .withX(1.0)
                                                .withY(1.0)
                                                .withZ(1.0)
                                                .withW(3.0))),
                // 10
                List.of(readResource("colorrgba-empty"), new ColorRGBAMessage()),
                List.of(
                        readResource("colorrgba"),
                        new ColorRGBAMessage().withR(.12F).withG(.13F).withB(.14F).withA(.15F)),
                List.of(readResource("vector3-empty"), new Vector3Message()),
                List.of(
                        readResource("vector3"),
                        new Vector3Message().withX(.12).withY(.13).withZ(.14)),
                // 14
                List.of(
                        readResource("polygonstamped"),
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
                // 15
                List.of(readResource("header-empty"), new HeaderMessage()),
                // 16
                List.of(
                        readResource("header"),
                        new HeaderMessage().withStamp(new Time(0, 1111)).withFrameId("aaaa")),
                // 17
                List.of(readResource("marker-empty"), new MarkerMessage()),
                // 18
                List.of(
                        readResource("marker-array"),
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
                // 19
                List.of(
                        readResource("pointcloud2"),
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
                // 20
                List.of(
                        readResource("joint-state"),
                        new JointStateMessage()
                                .withHeader(
                                        new HeaderMessage()
                                                .withStamp(new Time(1621056685, 970860000)))
                                .withNames("joint_0", "joint_1", "joint_2", "joint_3", "joint_4")
                                .withPositions(
                                        new double[] {0.0, 0.0, 0.0, 0.767944870877505, 0.0})),
                // 21
                List.of(readResource("uuid"), new UUIDMessage(new UUID(0x10101020, 0x30101040))),
                // 22
                List.of(
                        readResource("joy"),
                        new JoyMessage()
                                .withHeader(
                                        new HeaderMessage()
                                                .withStamp(new Time(1621056685, 970860000)))
                                .withAxes(3.3F, 3.2F, 3.1F)
                                .withButtons(5, 6, 7)));
    }

    /** Read resource removing new lines if any */
    private static String readResource(String resourceName) {
        var resource =
                resourceUtils
                        .readResourceAsStream(MessageTests.class, resourceName)
                        .filter(s -> !s.isBlank())
                        .collect(joining(" "));
        return resource;
    }

    @ParameterizedTest
    @MethodSource("dataProvider")
    public void testRead(List testData) throws Exception {
        Object expected = testData.get(1);
        Object actual =
                serializationUtils.read(
                        XByte.fromHexPairs((String) testData.get(0)),
                        (Class<? extends Message>) expected.getClass());
        System.out.println(actual);
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("dataProvider")
    public void testWrite(List testData) throws Exception {
        var b = (Message) testData.get(1);
        var out = serializationUtils.write(b);
        assertEquals(testData.get(0), XByte.toHexPairs(out));
    }
}
