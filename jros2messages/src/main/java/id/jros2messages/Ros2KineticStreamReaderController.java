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
package id.jros2messages;

import id.jros2messages.impl.DdsDataInput;
import id.kineticstreamer.InputKineticStream;
import id.kineticstreamer.KineticStreamReaderController;
import java.util.UUID;

/**
 * @author lambdaprime intid@protonmail.com
 */
class Ros2KineticStreamReaderController extends KineticStreamReaderController {

    @Override
    public Result onNextObject(InputKineticStream in, Object obj, Class<?> fieldType)
            throws Exception {
        var rtpsStream = (DdsDataInput) in;
        if (fieldType == UUID.class) {
            return new Result(true, rtpsStream.readUUID());
        }
        return Result.CONTINUE;
    }
}
