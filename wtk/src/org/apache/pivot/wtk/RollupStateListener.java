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
package org.apache.pivot.wtk;

import org.apache.pivot.util.ListenerList;
import org.apache.pivot.util.Vote;
import org.apache.pivot.util.VoteResult;

/**
 * Defines event listener methods that pertain to rollup state. Developers
 * register for such events by adding themselves to a rollup's list of "rollup
 * state listeners" (see {@link Rollup#getRollupStateListeners()}).
 */
public interface RollupStateListener {
    /**
     * Rollup state listeners.
     */
    public static class Listeners extends ListenerList<RollupStateListener> implements RollupStateListener {
        @Override
        public Vote previewExpandedChange(Rollup rollup) {
            VoteResult result = new VoteResult();

            forEach(listener -> result.tally(listener.previewExpandedChange(rollup)));

            return result.get();
        }

        @Override
        public void expandedChangeVetoed(Rollup rollup, Vote reason) {
            forEach(listener -> listener.expandedChangeVetoed(rollup, reason));
        }

        @Override
        public void expandedChanged(Rollup rollup) {
            forEach(listener -> listener.expandedChanged(rollup));
        }
    }

    /**
     * Rollup state listener adapter.
     * @deprecated Since 2.1 and Java 8 the interface itself has default implementations.
     */
    @Deprecated
    public static class Adapter implements RollupStateListener {
        @Override
        public Vote previewExpandedChange(Rollup rollup) {
            return Vote.APPROVE;
        }

        @Override
        public void expandedChangeVetoed(Rollup rollup, Vote reason) {
            // empty block
        }

        @Override
        public void expandedChanged(Rollup rollup) {
            // empty block
        }
    }

    /**
     * Called to preview a rollup expansion event.
     *
     * @param rollup The rollup that might change.
     * @return The result of this listener voting on the expansion.
     */
    default Vote previewExpandedChange(Rollup rollup) {
        return Vote.APPROVE;
    }

    /**
     * Called when a rollup expansion event has been vetoed.
     *
     * @param rollup The rollup that didn't change.
     * @param reason The reason the expansion was vetoed.
     */
    default void expandedChangeVetoed(Rollup rollup, Vote reason) {
    }

    /**
     * Called when a rollup's expanded state changed.
     *
     * @param rollup The rollup that did change.
     */
    default void expandedChanged(Rollup rollup) {
    }
}
