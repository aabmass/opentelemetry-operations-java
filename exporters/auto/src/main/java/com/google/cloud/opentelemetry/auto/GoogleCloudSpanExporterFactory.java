/*
 * Copyright 2021 Google
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
package com.google.cloud.opentelemetry.auto;

import static com.google.cloud.opentelemetry.auto.Constants.CLOUD_TRACE_EXPORTER_NAMES;

import com.google.auto.service.AutoService;
import com.google.cloud.opentelemetry.trace.TraceExporter;
import io.opentelemetry.javaagent.spi.exporter.SpanExporterFactory;
import io.opentelemetry.sdk.trace.export.SpanExporter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

@AutoService(SpanExporterFactory.class)
public class GoogleCloudSpanExporterFactory implements SpanExporterFactory {
  @Override
  public SpanExporter fromConfig(Properties _config) {
    try {
      return TraceExporter.createWithDefaultConfiguration();
    } catch (IOException ex) {
      throw new RuntimeException(ex);
    }
  }

  @Override
  public Set<String> getNames() {
    return new HashSet<>(CLOUD_TRACE_EXPORTER_NAMES);
  }
}
