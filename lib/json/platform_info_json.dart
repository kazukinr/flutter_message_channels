import 'package:freezed_annotation/freezed_annotation.dart';

part 'platform_info_json.freezed.dart';
part 'platform_info_json.g.dart';

@freezed
class PlatformInfoJson with _$PlatformInfoJson {
  factory PlatformInfoJson({
    String? version,
  }) = _PlatformInfoJson;

  factory PlatformInfoJson.fromJson(Map<String, dynamic> json) =>
      _$PlatformInfoJsonFromJson(json);
}
