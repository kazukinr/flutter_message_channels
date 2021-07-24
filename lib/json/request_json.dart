import 'package:freezed_annotation/freezed_annotation.dart';

part 'request_json.freezed.dart';

part 'request_json.g.dart';

@freezed
class RequestJson with _$RequestJson {
  factory RequestJson({
    String? param,
  }) = _RequestJson;

  factory RequestJson.fromJson(Map<String, dynamic> json) =>
      _$RequestJsonFromJson(json);
}
