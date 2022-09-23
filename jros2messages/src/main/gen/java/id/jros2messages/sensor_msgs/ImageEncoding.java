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
package id.jros2messages.sensor_msgs;

import id.jrosmessages.std_msgs.StringMessage;

/**
 * @author lambdaprime intid@protonmail.com
 */
public enum ImageEncoding {
    RGB8("rgb8"),
    RGBA8("rgba8"),
    RGB16("rgb16"),
    RGBA16("rgba16"),
    BGR8("bgr8"),
    BGRA8("bgra8"),
    BGR16("bgr16"),
    BGRA16("bgra16"),
    MONO8("mono8"),
    MONO16("mono16"),

    /** OpenCV CvMat types */
    TYPE_8UC1("8UC1"),
    TYPE_8UC2("8UC2"),
    TYPE_8UC3("8UC3"),
    TYPE_8UC4("8UC4"),
    TYPE_8SC1("8SC1"),
    TYPE_8SC2("8SC2"),
    TYPE_8SC3("8SC3"),
    TYPE_8SC4("8SC4"),
    TYPE_16UC1("16UC1"),
    TYPE_16UC2("16UC2"),
    TYPE_16UC3("16UC3"),
    TYPE_16UC4("16UC4"),
    TYPE_16SC1("16SC1"),
    TYPE_16SC2("16SC2"),
    TYPE_16SC3("16SC3"),
    TYPE_16SC4("16SC4"),
    TYPE_32SC1("32SC1"),
    TYPE_32SC2("32SC2"),
    TYPE_32SC3("32SC3"),
    TYPE_32SC4("32SC4"),
    TYPE_32FC1("32FC1"),
    TYPE_32FC2("32FC2"),
    TYPE_32FC3("32FC3"),
    TYPE_32FC4("32FC4"),
    TYPE_64FC1("64FC1"),
    TYPE_64FC2("64FC2"),
    TYPE_64FC3("64FC3"),
    TYPE_64FC4("64FC4"),

    /** Bayer encodings */
    BAYER_RGGB8("bayer_rggb8"),
    BAYER_BGGR8("bayer_bggr8"),
    BAYER_GBRG8("bayer_gbrg8"),
    BAYER_GRBG8("bayer_grbg8"),
    BAYER_RGGB16("bayer_rggb16"),
    BAYER_BGGR16("bayer_bggr16"),
    BAYER_GBRG16("bayer_gbrg16"),
    BAYER_GRBG16("bayer_grbg16");

    private StringMessage name;

    private ImageEncoding(String name) {
        this.name = new StringMessage(name);
    }

    public StringMessage getName() {
        return name;
    }
}
