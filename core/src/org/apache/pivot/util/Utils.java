/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except in
 * compliance with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.pivot.util;

/**
 * Utility methods for other parts of the code.
 */
public class Utils {
    /**
     * Decide if two strings are the same content (not just the same reference).
     * <p> Works properly for either string being <tt>null</tt>.
     * @param s1 First string to compare (can be {@code null}).
     * @param s2 Second string to compare (can also be {@code null}).
     * @return  <tt>true</tt> if both strings are <tt>null</tt> or if
     * <code>s1.equals(s2)</code>.
     */
    public static boolean stringsAreEqual(String s1, String s2) {
        if (s1 == null && s2 == null) {
            return true;
        }
        if ((s1 != null && s2 == null) || (s1 == null && s2 != null)) {
            return false;
        }
        return s1.equals(s2);
    }

    /**
     * Check if the input argument is {@code null} and throw an
     * {@link IllegalArgumentException} if so, with an optional
     * message derived from the given string.
     *
     * @param value The argument value to check for {@code null}.
     * @param description A description for the value used to
     * construct a message like {@code "xxx must not be null."}. Can be
     * {@code null} or an empty string, in which case a plain
     * {@link IllegalArgumentException} is thrown without any detail message.
     * @throws IllegalArgumentException if the value is {@code null}.
     */
    public static void checkNull(Object value, String description) {
        if (value == null) {
            if (description == null || description.isEmpty()) {
                throw new IllegalArgumentException();
            } else {
                throw new IllegalArgumentException(description + " must not be null.");
            }
        }
    }

}