/*
 * Copyright 2022 Google
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
package com.google.cloud.opentelemetry.example.sparkmicrometer;

import static com.google.cloud.opentelemetry.example.sparkmicrometer.DoWork.doFooWork;
import static com.google.cloud.opentelemetry.example.sparkmicrometer.DoWork.doHelloWork;
import static spark.Spark.afterAfter;
import static spark.Spark.get;
import static spark.Spark.port;

import io.micrometer.core.instrument.Metrics;

public class SparkMicrometerExample {

  public static void main(String[] args) throws InterruptedException {
    port(9999);
    get(
        "/hello",
        (req, res) -> {
          Metrics.timer("workduration", "path", req.matchedPath())
              .record(
                  () -> {
                    doHelloWork();
                  });
          return "/world";
        });
    get(
        "/foo",
        (req, res) -> {
          Metrics.timer("workduration", "path", req.matchedPath())
              .record(
                  () -> {
                    doFooWork();
                  });
          return "/bar";
        });

    // Count all requests, regardless of path
    afterAfter(
        (req, res) -> {
          Metrics.counter(
              "myrequestcount",
              "path",
              req.pathInfo(),
              "status",
              String.valueOf(res.status()))
              .increment();
        });

    System.out.println("Starting the spark micrometer example application");
  }
}
