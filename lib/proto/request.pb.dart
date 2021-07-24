///
//  Generated code. Do not modify.
//  source: proto/request.proto
//
// @dart = 2.12
// ignore_for_file: annotate_overrides,camel_case_types,unnecessary_const,non_constant_identifier_names,library_prefixes,unused_import,unused_shown_name,return_of_invalid_type,unnecessary_this,prefer_final_fields

import 'dart:core' as $core;

import 'package:protobuf/protobuf.dart' as $pb;

class RequestProto extends $pb.GeneratedMessage {
  static final $pb.BuilderInfo _i = $pb.BuilderInfo(const $core.bool.fromEnvironment('protobuf.omit_message_names') ? '' : 'RequestProto', createEmptyInstance: create)
    ..aOS(1, const $core.bool.fromEnvironment('protobuf.omit_field_names') ? '' : 'param')
    ..hasRequiredFields = false
  ;

  RequestProto._() : super();
  factory RequestProto({
    $core.String? param,
  }) {
    final _result = create();
    if (param != null) {
      _result.param = param;
    }
    return _result;
  }
  factory RequestProto.fromBuffer($core.List<$core.int> i, [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) => create()..mergeFromBuffer(i, r);
  factory RequestProto.fromJson($core.String i, [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) => create()..mergeFromJson(i, r);
  @$core.Deprecated(
  'Using this can add significant overhead to your binary. '
  'Use [GeneratedMessageGenericExtensions.deepCopy] instead. '
  'Will be removed in next major version')
  RequestProto clone() => RequestProto()..mergeFromMessage(this);
  @$core.Deprecated(
  'Using this can add significant overhead to your binary. '
  'Use [GeneratedMessageGenericExtensions.rebuild] instead. '
  'Will be removed in next major version')
  RequestProto copyWith(void Function(RequestProto) updates) => super.copyWith((message) => updates(message as RequestProto)) as RequestProto; // ignore: deprecated_member_use
  $pb.BuilderInfo get info_ => _i;
  @$core.pragma('dart2js:noInline')
  static RequestProto create() => RequestProto._();
  RequestProto createEmptyInstance() => create();
  static $pb.PbList<RequestProto> createRepeated() => $pb.PbList<RequestProto>();
  @$core.pragma('dart2js:noInline')
  static RequestProto getDefault() => _defaultInstance ??= $pb.GeneratedMessage.$_defaultFor<RequestProto>(create);
  static RequestProto? _defaultInstance;

  @$pb.TagNumber(1)
  $core.String get param => $_getSZ(0);
  @$pb.TagNumber(1)
  set param($core.String v) { $_setString(0, v); }
  @$pb.TagNumber(1)
  $core.bool hasParam() => $_has(0);
  @$pb.TagNumber(1)
  void clearParam() => clearField(1);
}

