#import "FlutterMessageChannelsPlugin.h"
#if __has_include(<flutter_message_channels/flutter_message_channels-Swift.h>)
#import <flutter_message_channels/flutter_message_channels-Swift.h>
#else
// Support project import fallback if the generated compatibility header
// is not copied when this plugin is created as a library.
// https://forums.swift.org/t/swift-static-libraries-dont-copy-generated-objective-c-header/19816
#import "flutter_message_channels-Swift.h"
#endif

@implementation FlutterMessageChannelsPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftFlutterMessageChannelsPlugin registerWithRegistrar:registrar];
}
@end
