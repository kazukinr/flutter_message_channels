package com.github.kazukinr.flutter_message_channels

import androidx.annotation.NonNull
import com.github.kazukinr.flutter_message_channels.json.PlatformInfoJson
import com.github.kazukinr.flutter_message_channels.json.RequestJson
import com.github.kazukinr.flutter_message_channels.pigeon.MessagePigeon
import com.github.kazukinr.flutter_message_channels.proto.PlatformInfoProto
import com.github.kazukinr.flutter_message_channels.proto.RequestProto
import io.flutter.Log

import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.plugin.common.*
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.nio.ByteBuffer

/** FlutterMessageChannelsPlugin */
class FlutterMessageChannelsPlugin : FlutterPlugin, MethodCallHandler {
    /// The MethodChannel that will the communication between Flutter and native Android
    ///
    /// This local reference serves to register the plugin with the Flutter Engine and unregister it
    /// when the Flutter Engine is detached from the Activity
    private lateinit var channel: MethodChannel
    private lateinit var jsonChannel: BasicMessageChannel<String>
    private lateinit var protoChannel: BasicMessageChannel<ByteBuffer>

    override fun onAttachedToEngine(@NonNull flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
        // MethodChannel
        channel = MethodChannel(flutterPluginBinding.binaryMessenger, "flutter_message_channels")
        channel.setMethodCallHandler(this)

        // BasicMessageChannel via JSON
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

        // BasicMessageChannel via protobuf
        protoChannel = BasicMessageChannel(
                flutterPluginBinding.binaryMessenger,
                "flutter_message_channels_proto",
                BinaryCodec.INSTANCE
        )
        protoChannel.setMessageHandler { message, reply ->
            val request = message?.let {
                val byteArray = ByteArray(it.capacity())
                it.get(byteArray)
                RequestProto.ADAPTER.decode(byteArray)
            }
            Log.i("proto", "Request = $request")

            val info = PlatformInfoProto(version = "Android ${android.os.Build.VERSION.RELEASE} via proto")
            val byteArray = info.encode();
            val buffer = ByteBuffer.allocateDirect(byteArray.size)
            buffer.put(byteArray)
            reply.reply(buffer)
        }

        // pigeon
        val pigeonApiImpl = object : MessagePigeon.PigeonHostApi {
            override fun getPlatformInfo(
                    arg: MessagePigeon.RequestPigeon?,
                    result: MessagePigeon.Result<MessagePigeon.PlatformInfoPigeon>?
            ) {
                Log.i("pigeon", "Request = $arg")
                val info: MessagePigeon.PlatformInfoPigeon = MessagePigeon.PlatformInfoPigeon()
                info.version = "Android ${android.os.Build.VERSION.RELEASE} via pigeon"
                result?.success(info)
            }
        }
        MessagePigeon.PigeonHostApi.setup(
                flutterPluginBinding.binaryMessenger,
                pigeonApiImpl
        )
    }

    override fun onMethodCall(@NonNull call: MethodCall, @NonNull result: Result) {
        if (call.method == "getPlatformVersion") {
            result.success("Android ${android.os.Build.VERSION.RELEASE} via MethodChannel")
        } else {
            result.notImplemented()
        }
    }

    override fun onDetachedFromEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
        channel.setMethodCallHandler(null)
        jsonChannel.setMessageHandler(null)
        protoChannel.setMessageHandler(null)
        MessagePigeon.PigeonHostApi.setup(binding.binaryMessenger, null)
    }
}
