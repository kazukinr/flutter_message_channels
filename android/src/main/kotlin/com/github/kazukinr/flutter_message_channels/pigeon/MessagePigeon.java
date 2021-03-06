// Autogenerated from Pigeon (v0.3.0), do not edit directly.
// See also: https://pub.dev/packages/pigeon

package com.github.kazukinr.flutter_message_channels.pigeon;

import io.flutter.plugin.common.BasicMessageChannel;
import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.StandardMessageCodec;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/** Generated class from Pigeon. */
@SuppressWarnings({"unused", "unchecked", "CodeBlock2Expr", "RedundantSuppression"})
public class MessagePigeon {

  /** Generated class from Pigeon that represents data sent in messages. */
  public static class RequestPigeon {
    private String param;
    public String getParam() { return param; }
    public void setParam(String setterArg) { this.param = setterArg; }

    Map<String, Object> toMap() {
      Map<String, Object> toMapResult = new HashMap<>();
      toMapResult.put("param", param);
      return toMapResult;
    }
    static RequestPigeon fromMap(Map<String, Object> map) {
      RequestPigeon fromMapResult = new RequestPigeon();
      Object param = map.get("param");
      fromMapResult.param = (String)param;
      return fromMapResult;
    }
  }

  /** Generated class from Pigeon that represents data sent in messages. */
  public static class PlatformInfoPigeon {
    private String version;
    public String getVersion() { return version; }
    public void setVersion(String setterArg) { this.version = setterArg; }

    Map<String, Object> toMap() {
      Map<String, Object> toMapResult = new HashMap<>();
      toMapResult.put("version", version);
      return toMapResult;
    }
    static PlatformInfoPigeon fromMap(Map<String, Object> map) {
      PlatformInfoPigeon fromMapResult = new PlatformInfoPigeon();
      Object version = map.get("version");
      fromMapResult.version = (String)version;
      return fromMapResult;
    }
  }

  public interface Result<T> {
    void success(T result);
  }

  /** Generated interface from Pigeon that represents a handler of messages from Flutter.*/
  public interface PigeonHostApi {
    void getPlatformInfo(RequestPigeon arg, Result<PlatformInfoPigeon> result);

    /** Sets up an instance of `PigeonHostApi` to handle messages through the `binaryMessenger`. */
    static void setup(BinaryMessenger binaryMessenger, PigeonHostApi api) {
      {
        BasicMessageChannel<Object> channel =
            new BasicMessageChannel<>(binaryMessenger, "dev.flutter.pigeon.PigeonHostApi.getPlatformInfo", new StandardMessageCodec());
        if (api != null) {
          channel.setMessageHandler((message, reply) -> {
            Map<String, Object> wrapped = new HashMap<>();
            try {
              @SuppressWarnings("ConstantConditions")
              RequestPigeon input = RequestPigeon.fromMap((Map<String, Object>)message);
              api.getPlatformInfo(input, result -> { wrapped.put("result", result.toMap()); reply.reply(wrapped); });
            }
            catch (Error | RuntimeException exception) {
              wrapped.put("error", wrapError(exception));
              reply.reply(wrapped);
            }
          });
        } else {
          channel.setMessageHandler(null);
        }
      }
    }
  }
  private static Map<String, Object> wrapError(Throwable exception) {
    Map<String, Object> errorMap = new HashMap<>();
    errorMap.put("message", exception.toString());
    errorMap.put("code", exception.getClass().getSimpleName());
    errorMap.put("details", null);
    return errorMap;
  }
}
