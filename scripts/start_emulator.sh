#!/bin/bash

DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"

if ! $ANDROID_HOME/tools/android list avd | grep -q screenshot-test; then
    echo "No emulator for screenshot tests found, creating one..."
    $DIR/create_emulator.sh
fi

if $ANDROID_HOME/platform-tools/adb devices -l | grep -q emulator; then
    echo "Emulator already running"
    exit 0
fi

echo "Starting emulator..."
echo "no" | $ANDROID_HOME/emulator/emulator "-avd" "screenshot-test" "-skin" "480x800" "-no-audio" "-no-boot-anim" "-port" "5554" "-no-snapshot" "-partition-size" "1024" &

$DIR/wait_for_emulator.sh
sleep 30

$ANDROID_HOME/platform-tools/adb wait-for-device
$ANDROID_HOME/platform-tools/adb shell settings put global window_animation_scale 0
$ANDROID_HOME/platform-tools/adb shell settings put global transition_animation_scale 0
$ANDROID_HOME/platform-tools/adb shell settings put global animator_duration_scale 0
$ANDROID_HOME/platform-tools/adb shell am broadcast -a android.intent.action.BOOT_COMPLETED &

sleep 5


echo "Emulator started!"
