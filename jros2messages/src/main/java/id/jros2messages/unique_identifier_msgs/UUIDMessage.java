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
package id.jros2messages.unique_identifier_msgs;

import id.jrosmessages.Message;
import id.jrosmessages.MessageMetadata;
import java.util.Objects;
import java.util.UUID;

/**
 * Definition for unique_identifier_msgs/UUID
 *
 * @author aeon_flux aeon_flux@eclipso.ch
 */
@MessageMetadata(name = UUIDMessage.NAME)
public class UUIDMessage implements Message {

    static final String NAME = "unique_identifier_msgs/UUID";

    public UUID uuid;

    public UUIDMessage() {}

    public UUIDMessage(UUID uuid) {
        this.uuid = uuid;
    }

    @Override
    public String toString() {
        return Objects.toString(uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid);
    }

    @Override
    public boolean equals(Object obj) {
        UUIDMessage other = (UUIDMessage) obj;
        return Objects.equals(uuid, other.uuid);
    }
    
    public static UUIDMessage generate() {
        return new UUIDMessage(UUID.randomUUID());
    }
}
