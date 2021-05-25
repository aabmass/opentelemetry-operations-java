#!/bin/sh
# 
# Copyright 2021 Google
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#      https://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

while true; do
    docker pull $_TEST_SERVER_IMAGE
    pull_success=$?

    if [ $pull_success -ne 0 ]; then
        echo "Image couldn't be pulled yet, will continue to retry"
    else
        echo "Image pulled successfully, continuing onto test"
        break
    fi
    sleep 5
done