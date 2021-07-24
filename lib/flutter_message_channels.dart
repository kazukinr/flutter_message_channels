import 'dart:async';
import 'dart:convert';

import 'package:flutter/services.dart';
import 'package:flutter_message_channels/json/platform_info_json.dart';
import 'package:flutter_message_channels/json/request_json.dart';

export 'json/platform_info_json.dart';

class FlutterMessageChannels {
  static const MethodChannel _channel =
      const MethodChannel('flutter_message_channels');

  static const BasicMessageChannel _jsonChannel = const BasicMessageChannel(
    'flutter_message_channels_json',
    StringCodec(),
  );

  static Future<String?> get platformVersion async {
    final String? version = await _channel.invokeMethod('getPlatformVersion');
    return version;
  }

  static Future<PlatformInfoJson?> get platformInfoJson async {
    final req = jsonEncode(RequestJson().toJson());
    final String? jsonString = await _jsonChannel.send(req);
    final json = jsonString != null ? jsonDecode(jsonString) as Map<String, dynamic> : null;
    if (json == null) {
      throw PlatformException(code: 'json-decode-failed');
    }
    return PlatformInfoJson.fromJson(json);
  }
}
