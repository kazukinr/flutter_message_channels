package com.github.kazukinr.flutter_message_channels

import androidx.annotation.NonNull
import com.github.kazukinr.flutter_message_channels.json.PlatformInfoJson
import com.github.kazukinr.flutter_message_channels.json.RequestJson
import io.flutter.Log

import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.plugin.common.BasicMessageChannel
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import io.flutter.plugin.common.StringCodec
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

/** FlutterMessageChannelsPlugin */
class FlutterMessageChannelsPlugin: FlutterPlugin, MethodCallHandler {
  /// The MethodChannel that will the communication between Flutter and native Android
  ///
  /// This local reference serves to register the plugin with the Flutter Engine and unregister it
  /// when the Flutter Engine is detached from the Activity
  private lateinit var channel : MethodChannel
  private lateinit var jsonChannel : BasicMessageChannel<String>

  override fun onAttachedToEngine(@NonNull flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
    channel = MethodChannel(flutterPluginBinding.binaryMessenger, "flutter_message_channels")
    channel.setMethodCallHandler(this)

    jsonChannel = BasicMessageChannel(
            flutterPluginBinding.binaryMessenger,
            "flutter_message_channels_json",
            StringCodec.INSTANCE
    )
    jsonChannel.setMessageHandler { message, reply ->
      val request = message?.let { Json.decodeFromString<RequestJson>(it) }
      Log.i("JSON", "Request = $request")

      val info = PlatformInfoJson(version = "Android ${android.os.Build.VERSION.RELEASE} via JSON")
      reply.reply(Json.encodeToString(info))
    }
  }

  override fun onMethodCall(@NonNull call: MethodCall, @NonNull result: Result) {
    if (call.method == "getPlatformVersion") {
      result.success("Android ${android.os.Build.VERSION.RELEASE}")
    } else {
      result.notImplemented()
    }
  }

  override fun onDetachedFromEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
    channel.setMethodCallHandler(null)
    jsonChannel.setMessageHandler(null)
  }
}
