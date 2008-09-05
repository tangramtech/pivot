/*
 * Copyright (c) 2008 VMware, Inc.
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
package pivot.serialization;

/**
 * Thrown when an error is encountered during serialization.
 *
 * @author gbrown
 */
public class SerializationException extends Exception {
    public static final long serialVersionUID = 0;

    public SerializationException() {
        this(null, null);
    }

    public SerializationException(String message) {
        this(message, null);
    }

    public SerializationException(Throwable cause) {
        this(null, cause);
    }

    public SerializationException(String message, Throwable cause) {
        super(message, cause);
    }
}
