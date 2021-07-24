import 'package:pigeon/pigeon.dart';

@ConfigurePigeon(PigeonOptions(
  javaOptions: JavaOptions(
    className: 'MessagePigeon',
    package: 'com.github.kazukinr.flutter_message_channels.pigeon',
  ),
  objcOptions: ObjcOptions(
    prefix: 'FMC',
  ),
))

class RequestPigeon {
  String? param;
}

class PlatformInfoPigeon {
  String? version;
}

@HostApi()
abstract class PigeonHostApi {
  @async
  PlatformInfoPigeon getPlatformInfo(RequestPigeon request);
}
