/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import type { Column } from 'druid-query-toolkit';

import type { Measure } from './models';

export class DragHelper {
  static dragColumn: Column | undefined;
  static dragMeasure: Measure | undefined;

  static createDragGhost(dataTransfer: DataTransfer, text: string): void {
    const dragGhost = document.createElement('div');
    dragGhost.className = 'drag-ghost';

    const dragGhostInner = document.createElement('div');
    dragGhostInner.className = 'drag-ghost-inner';
    dragGhostInner.textContent = text;
    dragGhost.appendChild(dragGhostInner);

    document.body.appendChild(dragGhost);

    dataTransfer.setDragImage(dragGhost, 0, 0);

    // Remove the host after a ms because it is no longer needed
    setTimeout(() => document.body.removeChild(dragGhost), 1);
  }
}
