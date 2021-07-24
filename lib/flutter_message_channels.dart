import 'dart:async';
import 'dart:convert';
import 'dart:typed_data';

import 'package:flutter/services.dart';
import 'package:flutter_message_channels/json/platform_info_json.dart';
import 'package:flutter_message_channels/json/request_json.dart';
import 'package:flutter_message_channels/proto/platform_info.pbserver.dart';
import 'package:flutter_message_channels/proto/request.pb.dart';

export 'json/platform_info_json.dart';
export 'proto/platform_info.pb.dart';

class FlutterMessageChannels {
  static const MethodChannel _channel =
      const MethodChannel('flutter_message_channels');

  static const BasicMessageChannel _jsonChannel = const BasicMessageChannel(
    'flutter_message_channels_json',
    StringCodec(),
  );

  static const BasicMessageChannel _protoChannel = const BasicMessageChannel(
    'flutter_message_channels_proto',
    BinaryCodec(),
  );

  static Future<String?> get platformVersion async {
    final String? version = await _channel.invokeMethod('getPlatformVersion');
    return version;
  }

  static Future<PlatformInfoJson?> get platformInfoJson async {
    final req = jsonEncode(RequestJson(param: 'json').toJson());
    final String? jsonString = await _jsonChannel.send(req);
    final json = jsonString != null
        ? jsonDecode(jsonString) as Map<String, dynamic>
        : null;
    if (json == null) {
      throw PlatformException(code: 'json-decode-failed');
    }
    return PlatformInfoJson.fromJson(json);
  }

  static Future<PlatformInfoProto?> get platformInfoProto async {
    final req =
        ByteData.sublistView(RequestProto(param: 'proto').writeToBuffer());
    final ByteData? byteData = await _protoChannel.send(req);
    if (byteData == null) {
      throw PlatformException(code: 'proto-decode-failed');
    }
    return PlatformInfoProto.fromBuffer(Uint8List.sublistView(byteData));
  }
}
